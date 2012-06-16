//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCriterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector for RuneScape item values.
 */
public class RuneScapeDataCollector implements DataCollector
{
    /** Data post processor. */
    private final DataPostProcessor _dataPostProcessor;

    /**
     * Construct this object with the given parameter.
     * 
     * @param dataPostProcessor Data post processor.
     */
    public RuneScapeDataCollector(final DataPostProcessor dataPostProcessor)
    {
        _dataPostProcessor = dataPostProcessor;
    }

    @Override
    public void fetchData(final DCSpec dcSpec, final String username, final String password,
            final RivalryData rivalryData)
    {
        // final Item[] testItems = determineJewelryItems();
        // final Item[] testItems = determineArmourItems();
        // for (final Item item : testItems)
        // {
        // System.out.println("item = " + item.getName());
        // }
        //
        // if (true)
        // {
        // return;
        // }

        final long start = System.currentTimeMillis();

        final boolean isTest = false;
        Item[] items;

        if (isTest)
        {
            final Item[] myItems0 = { Item.GOLD_ORE, Item.GOLD_BAR, Item.UNCUT_EMERALD, Item.EMERALD,
                    Item.EMERALD_RING, Item.RING_OF_DUELLING, Item.WATER_RUNE, Item.COSMIC_RUNE, Item.RUNE_ESSENCE,
                    Item.PURE_ESSENCE, };
            final Item[] myItems1 = { Item.POT_OF_FLOUR, Item.PASTRY_DOUGH, Item.PIE_DISH, Item.PIE_SHELL,
                    Item.COOKING_APPLE, Item.UNCOOKED_APPLE_PIE, Item.APPLE_PIE, };
            final Item[] jewelryItems = determineJewelryItems();
            final Item[] armourItems = determineArmourItems();
            items = myItems1;
        }
        else
        {
            items = Item.values();
        }

        for (final Item item : items)
        {
            if (item.isLookupable())
            {
                final Candidate candidate = rivalryData.findCandidateByName(item.getName());

                if (candidate == null)
                {
                    if ("RuneScape".equals(username))
                    {
                        fetchDataRuneScape(rivalryData);
                    }
                    else if ("RuneHq".equals(username))
                    {
                        fetchDataRuneHq(rivalryData);
                    }
                    else if ("SalsRealm".equals(username))
                    {
                        fetchDataSalsRealm(rivalryData, item);
                    }
                    else if ("TipIt".equals(username))
                    {
                        fetchDataTipIt(rivalryData);
                    }
                    else
                    {
                        System.err.println("Please provide the data source you'd like to use.");
                    }
                }
            }
        }

        _dataPostProcessor.postProcess(rivalryData);

        final long end = System.currentTimeMillis();
        logTiming("0 fetchData()", start, end);
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     * @param value Value.
     * 
     * @return a new candidate.
     */
    private Candidate createCandidate(final RivalryData rivalryData, final Item item, final Double value)
    {
        final RuneScapeCandidate answer = new RuneScapeCandidate();

        answer.setItem(item);

        final Criterion valueCriterion = getValueCriterion(rivalryData);
        answer.putValue(valueCriterion, value);

        return answer;
    }

    /**
     * @param isJavascriptEnabled Flag indicating if Javascript is enabled.
     * 
     * @return a new web driver.
     */
    private WebDriver createWebDriver(final boolean isJavascriptEnabled)
    {
        final WebDriver answer = new HtmlUnitDriver();

        final Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        if (answer instanceof HtmlUnitDriver)
        {
            ((HtmlUnitDriver)answer).setJavascriptEnabled(isJavascriptEnabled);
        }

        return answer;
    }

    private Item[] determineArmourItems()
    {
        final List<Item> list = new ArrayList<Item>();

        for (final Item item : Item.values())
        {
            final String itemName = item.getName();

            if ((item == Item.COAL) || itemName.endsWith(" ore") || itemName.endsWith(" bar")
                    || itemName.endsWith(" full helm") || itemName.endsWith(" platebody")
                    || itemName.endsWith(" nails"))
            {
                list.add(item);
            }
        }

        return list.toArray(new Item[list.size()]);
    }

    private Item[] determineJewelryItems()
    {
        final List<Item> list = new ArrayList<Item>();

        for (final Item item : Item.values())
        {
            final String itemName = item.getName();

            if (itemName.equals("Gold ore") || itemName.equals("Gold bar") || itemName.contains("apphire")
                    || itemName.contains("merald") || itemName.contains("uby") || itemName.contains("iamond")
                    || itemName.contains("ragonstone") || itemName.contains("nyx") || itemName.endsWith(" amulet")
                    || itemName.endsWith(" bracelet") || itemName.endsWith(" necklace") || itemName.endsWith(" ring"))
            {
                list.add(item);
            }
        }

        return list.toArray(new Item[list.size()]);
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void fetchDataRuneHq(final RivalryData rivalryData)
    {
        final boolean isJavascriptEnabled = false;
        final WebDriver webDriver = createWebDriver(isJavascriptEnabled);
        final String url = "http://www.runehq.com/item";
        System.out.println("accessing URL " + url);
        webDriver.get(url);

        System.out.println("findElement query");
        final WebElement queryInput = webDriver.findElement(By.name("query"));

        if (queryInput != null)
        {
            System.out.println("queryInput = " + queryInput.getClass().getName());
            final HtmlUnitWebElement huwe = (HtmlUnitWebElement)queryInput;
            System.out.println("huwe.getText() = [" + huwe.getText() + "]");
        }
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void fetchDataRuneScape(final RivalryData rivalryData)
    {
        final boolean isJavascriptEnabled = false;
        final WebDriver webDriver = createWebDriver(isJavascriptEnabled);
        final String url = "http://services.runescape.com/m=itemdb_rs/frontpage.ws";
        System.out.println("accessing URL " + url);
        webDriver.get(url);

        System.out.println("findElement query");
        final WebElement queryInput = webDriver.findElement(By.name("query"));

        if (queryInput != null)
        {
            System.out.println("queryInput = " + queryInput.getClass().getName());
        }
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     */
    private void fetchDataSalsRealm(final RivalryData rivalryData, final Item item)
    {
        final boolean isJavascriptEnabled = false;
        final WebDriver webDriver = createWebDriver(isJavascriptEnabled);
        final String url = "http://runescape.salmoneus.net/item-database/";
        webDriver.get(url);

        final WebElement queryInput = webDriver.findElement(By.id("idbName"));

        if (queryInput != null)
        {
            final HtmlUnitWebElement huwe = (HtmlUnitWebElement)queryInput;
            huwe.sendKeys(item.getName());
            huwe.submit();

            try
            {
                final WebElement itemTable = webDriver.findElement(By.id("idbItems"));

                if (itemTable != null)
                {
                    // System.out.println(item.getName() + ": item table found");
                    final List<WebElement> data = itemTable.findElements(By.className("item0"));
                    boolean isFound = false;

                    for (final WebElement webItem : data)
                    {
                        if (item.getName().equalsIgnoreCase(webItem.getText()))
                        {
                            // System.out.println(item.getName() + ": item table entry found");
                            webItem.click();
                            // System.out.println(item.getName()
                            // + ": attempting to process detail table (with item table)");
                            processSalsRealmDetailTable(rivalryData, item, webDriver);
                            isFound = true;
                            break;
                        }
                    }

                    if (!isFound)
                    {
                        // System.out.println(item.getName() + ": didn't find item table entry");
                        System.out.println("==> Missing search data for: " + item.getName());
                    }
                }
            }
            catch (final NoSuchElementException e)
            {
                // System.out.println(item.getName() + ": attempting to process detail table (without item table)");
                processSalsRealmDetailTable(rivalryData, item, webDriver);
            }
        }
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void fetchDataTipIt(final RivalryData rivalryData)
    {
        final boolean isJavascriptEnabled = false;
        final WebDriver webDriver = createWebDriver(isJavascriptEnabled);
        final String url = "http://www.tip.it/runescape/?rs2item";
        System.out.println("accessing URL " + url);
        webDriver.get(url);

        System.out.println("findElement query");
        final WebElement queryInput = webDriver.findElement(By.name("query"));

        if (queryInput != null)
        {
            System.out.println("queryInput = " + queryInput.getClass().getName());
        }
    }

    /**
     * @param rivalryData Rivalry data.
     * @param criterionName Criterion name.
     * 
     * @return the value criterion, creating it if necessary.
     */
    private Criterion getCriterion(final RivalryData rivalryData, final String criterionName)
    {
        Criterion answer = rivalryData.findCriterionByName(criterionName);

        if (answer == null)
        {
            answer = new DefaultCriterion();
            answer.setName(criterionName);
            rivalryData.getCriteria().add(answer);
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the value criterion, creating it if necessary.
     */
    private Criterion getValueCriterion(final RivalryData rivalryData)
    {
        return getCriterion(rivalryData, Constants.VALUE_CRITERION);
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the value criterion, creating it if necessary.
     */
    // private Criterion getValueCriterion(final RivalryData rivalryData)
    // {
    // Criterion answer = rivalryData.findCriterionByName("value");
    // if (answer == null)
    // {
    // answer = new DefaultCriterion();
    // answer.setName("value");
    // rivalryData.getCriteria().add(answer);
    // }
    // return answer;
    // }

    /**
     * Log the given timing parameters.
     * 
     * @param title Title.
     * @param start Start time.
     * @param end End time.
     */
    private void logTiming(final String title, final long start, final long end)
    {
        System.out.println(title + " \telapsed: " + (end - start) + " ms");
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     * @param webDriver Web driver.
     */
    private void processSalsRealmDetailTable(final RivalryData rivalryData, final Item item, final WebDriver webDriver)
    {
        final WebElement detailTable = webDriver.findElement(By.id("idbItemGeneral"));

        if (detailTable != null)
        {
            final List<WebElement> details = detailTable.findElements(By.tagName("td"));
            String priceString = details.get(8).getText();

            // Remove gold piece suffix.
            final String key = " Gp";

            if (priceString.endsWith(key))
            {
                priceString = priceString.substring(0, priceString.length() - key.length());
            }

            // Remove comma.
            priceString = priceString.replaceAll(",", "");

            final Double value = Double.parseDouble(priceString);
            System.out.println(item.getName() + " value = " + value);
            final Candidate candidate = createCandidate(rivalryData, item, value);
            rivalryData.getCandidates().add(candidate);
        }
    }
}
