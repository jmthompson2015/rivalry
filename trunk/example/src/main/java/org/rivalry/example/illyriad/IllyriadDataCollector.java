//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.datacollector.WebElementTextCondition;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Provides a data collector for Illyriad.
 */
public class IllyriadDataCollector implements DataCollector
{
    /** Map of town ID to town name. */
    private static final Map<Long, String> TOWN_ID_TO_TOWN_NAME;

    static
    {
        final Map<Long, String> map = new LinkedHashMap<Long, String>();

        map.put(66410L, "Lockstone");
        map.put(71150L, "Locksley");
        map.put(79753L, "Lock Downs");
        map.put(90395L, "Lockand Key");
        map.put(108163L, "Lock Steppe");
        map.put(139512L, "Locks Heath");

        map.put(109395L, "Frakshush");
        map.put(134891L, "Fraktull");
        map.put(139164L, "Frakshure");

        TOWN_ID_TO_TOWN_NAME = map;
    }

    /** Category provider. */
    private final Provider<Category> _categoryProvider = new DefaultCategoryProvider();

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider = new DefaultCriterionProvider();

    /** Flag indicating whether to enable Javascript. */
    private boolean _isJavascriptEnabled;

    /** Value string parser. */
    private final ValueStringParser _valueStringParser;

    /** Web driver. */
    private WebDriver _webDriver;

    /**
     * Construct this object with the given parameter.
     * 
     * @param valueStringParser Value string parser.
     */
    public IllyriadDataCollector(final ValueStringParser valueStringParser)
    {
        _valueStringParser = valueStringParser;
    }

    /**
     * @param dcSpec Data collector specification.
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     */
    @Override
    public void fetchData(final DCSpec dcSpec, final RivalryData rivalryData,
            final Candidate candidate)
    {
        final long start = System.currentTimeMillis();

        final IllyriadCandidate illyriadCandidate = (IllyriadCandidate)candidate;
        final Long newFocusTownId = illyriadCandidate.getTownId();
        changeTownFocus(newFocusTownId);

        fetchResourceData(rivalryData, candidate);
        fetchProductionData(rivalryData, candidate);
        fetchBuildingData(rivalryData, candidate);

        final long end = System.currentTimeMillis();
        logTiming("1 fetchData()", start, end);
    }

    @Override
    public void fetchData(final DCSpec dcSpec, final String username,
            final String password, final RivalryData rivalryData)
    {
        final long start = System.currentTimeMillis();

        _webDriver = createWebDriver();

        try
        {
            login(dcSpec, username, password);

            fetchCandidates(dcSpec, rivalryData);

            for (final Candidate candidate : rivalryData.getCandidates())
            {
                fetchData(dcSpec, rivalryData, candidate);
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            logout(dcSpec);
        }

        final long end = System.currentTimeMillis();
        logTiming("0 fetchData()", start, end);
    }

    @Override
    public void fetchData(final WebDriver webDriver, final DCSpec dcSpec,
            final RivalryData rivalryData, final Candidate candidate)
    {
        // TODO Auto-generated method stub
    }

    /**
     * @return the isJavascriptEnabled
     */
    @Override
    public boolean isJavascriptEnabled()
    {
        return _isJavascriptEnabled;
    }

    @Override
    public void setJavascriptEnabled(final boolean isJavascriptEnabled)
    {
        _isJavascriptEnabled = isJavascriptEnabled;
    }

    /**
     * @param newFocusTownId New focus town ID.
     */
    private void changeTownFocus(final Long newFocusTownId)
    {
        final JavascriptExecutor js = (JavascriptExecutor)getWebDriver();
        System.out.println("changing town focus to [" + newFocusTownId + "]");
        js.executeScript("ChangeTownFocus(" + newFocusTownId + ")");

        if (!getCurrentTownId().equals(newFocusTownId))
        {
            while (!getCurrentTownId().equals(newFocusTownId))
            {
                synchronized (this)
                {
                    try
                    {
                        wait(500);
                    }
                    catch (final InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            // Reload.
            getWebDriver().get("http://uk1.illyriad.co.uk");

            // Open the advanced resources.
            getWebDriver().findElement(By.id("btnResource")).click();
        }
    }

    /**
     * @param name Name.
     * @param townId Town ID.
     * 
     * @return a new candidate.
     */
    private IllyriadCandidate createCandidate(final String name,
            final Long townId)
    {
        final IllyriadCandidate answer = new IllyriadCandidate();

        answer.setName(name);
        answer.setTownId(townId);

        return answer;
    }

    /**
     * @param name Name.
     * 
     * @return a new category.
     */
    private Category createCategory(final String name)
    {
        final Category answer = getCategoryProvider().newInstance();

        answer.setName(name);

        return answer;
    }

    /**
     * 
     * @param category Category.
     * @param name Name.
     * 
     * @return a new criterion.
     */
    private Criterion createCriterion(final Category category, final String name)
    {
        final Criterion answer = getCriterionProvider().newInstance();

        answer.setCategory(category);
        answer.setName(name);

        return answer;
    }

    /**
     * @return a new web driver.
     */
    private WebDriver createWebDriver()
    {
        final WebDriver answer = new HtmlUnitDriver();

        final Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        if (answer instanceof HtmlUnitDriver)
        {
            ((HtmlUnitDriver)answer).setJavascriptEnabled(true);
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     */
    private void fetchBuildingData(final RivalryData rivalryData,
            final Candidate candidate)
    {
        final long start = System.currentTimeMillis();

        final Category category = getCategory(rivalryData, "Building");

        final JavascriptExecutor js = (JavascriptExecutor)getWebDriver();
        @SuppressWarnings("unchecked")
        final List<List<List<?>>> list1 = (List<List<List<?>>>)js
                .executeScript("return buildings;");
        final int size1 = list1.size();

        for (int i = 0; i < size1; i++)
        {
            final List<List<?>> list2 = list1.get(i);
            final int size2 = list2.size();

            for (int j = 0; j < size2; j++)
            {
                final List<?> list3 = list2.get(j);
                if (list3 != null)
                {
                    final int size3 = list3.size();

                    if (size3 == 2)
                    {
                        if (list3.get(1) instanceof Long)
                        {
                            final Long buildingNumber = (long)j;
                            // final Long buildingPosition = (Long)list3.get(0);
                            final Double buildingLevel = ((Long)list3.get(1))
                                    .doubleValue();
                            final String criterionName = getBuildingName(buildingNumber);
                            putRating(rivalryData, candidate, category,
                                    criterionName, buildingLevel);
                        }
                    }
                }
            }
        }

        final long end = System.currentTimeMillis();
        logTiming("fetchBuildingData()", start, end);
    }

    /**
     * @param dcSpec Data collector specification.
     * @param rivalryData Rivalry data.
     */
    private void fetchCandidates(final DCSpec dcSpec,
            final RivalryData rivalryData)
    {
        final List<IllyriadCandidate> candidates = new ArrayList<IllyriadCandidate>();

        final Select select = new Select(getWebDriver().findElement(
                By.id("optTown")));

        for (final WebElement option : select.getOptions())
        {
            final String townIdString = option.getAttribute("value");
            final Long townId = Long.parseLong(townIdString);
            final String name = TOWN_ID_TO_TOWN_NAME.get(townId);
            System.out.println("town = " + townId + "\t" + name);

            final IllyriadCandidate candidate = createCandidate(name, townId);
            candidates.add(candidate);
        }

        final Comparator<IllyriadCandidate> comparator = new CandidateTownIdComparator();
        Collections.sort(candidates, comparator);

        rivalryData.getCandidates().addAll(candidates);
    }

    /**
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     */
    private void fetchProductionData(final RivalryData rivalryData,
            final Candidate candidate)
    {
        final long start = System.currentTimeMillis();

        final Category category = getCategory(rivalryData, "Production");

        final List<WebElement> elements = getWebDriver().findElements(
                By.className("resInc"));

        final int size = elements.size();

        for (int i = 0; i < size; i++)
        {
            final Resource resource = getResource(i);

            if (resource != null)
            {
                final WebElement element = elements.get(i);
                final Double value = getValueStringParser().parse(element);
                final String criterionName = resource.getProductionName();
                putRating(rivalryData, candidate, category, criterionName,
                        value);
            }
        }

        final long end = System.currentTimeMillis();
        logTiming("fetchProductionData()", start, end);
    }

    /**
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     */
    private void fetchResourceData(final RivalryData rivalryData,
            final Candidate candidate)
    {
        final long start = System.currentTimeMillis();

        final Category category = getCategory(rivalryData, "Resource");

        final List<WebElement> elements = getWebDriver().findElements(
                By.className("resTxt"));

        final int size = elements.size();

        for (int i = 0; i < size; i++)
        {
            final Resource resource = getResource(i);

            if (resource != null)
            {
                final WebElement element = elements.get(i);
                final Double value = getValueStringParser().parse(element);
                final String criterionName = resource.getDisplayName();
                putRating(rivalryData, candidate, category, criterionName,
                        value);
            }
        }

        final long end = System.currentTimeMillis();
        logTiming("fetchResourceData()", start, end);
    }

    /**
     * @param buildingNumber Building number.
     * 
     * @return building name.
     */
    private String getBuildingName(final Long buildingNumber)
    {
        String answer = null;

        final Building building = Building
                .valueOfBuildingNumber(buildingNumber);

        if (building != null)
        {
            answer = building.getDisplayName();
        }
        else
        {
            answer = "Unknown " + buildingNumber;
        }

        return answer;
    }

    /**
     * 
     * @param rivalryData Rivalry data.
     * @param name Name.
     * 
     * @return a category of the given name, creating it if necessary.
     */
    private Category getCategory(final RivalryData rivalryData,
            final String name)
    {
        Category answer = rivalryData.findCategoryByName(name);

        if (answer == null)
        {
            answer = createCategory(name);
            rivalryData.getCategories().add(answer);
        }

        return answer;
    }

    /**
     * @return the categoryProvider
     */
    private Provider<Category> getCategoryProvider()
    {
        return _categoryProvider;
    }

    /**
     * 
     * @param rivalryData Rivalry data.
     * @param category Category.
     * @param name Name.
     * 
     * @return a criterion of the given name, creating it if necessary.
     */
    private Criterion getCriterion(final RivalryData rivalryData,
            final Category category, final String name)
    {
        Criterion answer = rivalryData.findCriterionByName(name);

        if (answer == null)
        {
            answer = createCriterion(category, name);
            rivalryData.getCriteria().add(answer);
        }

        return answer;
    }

    /**
     * @return the criterionProvider
     */
    private Provider<Criterion> getCriterionProvider()
    {
        return _criterionProvider;
    }

    /**
     * @return town focus ID.
     */
    private Long getCurrentTownId()
    {
        final JavascriptExecutor js = (JavascriptExecutor)getWebDriver();
        final Long answer = (Long)js.executeScript("return CurrentTown");

        return answer;
    }

    /**
     * @param index Index.
     * 
     * @return resource name.
     */
    private Resource getResource(final int index)
    {
        Resource answer = null;

        if (0 <= index && index < Resource.values().length)
        {
            answer = Resource.values()[index];
        }

        return answer;
    }

    /**
     * @return the valueStringParser
     */
    private ValueStringParser getValueStringParser()
    {
        return _valueStringParser;
    }

    /**
     * @return the webDriver
     */
    private WebDriver getWebDriver()
    {
        return _webDriver;
    }

    /**
     * Login to the service.
     * 
     * @param dcSpec Data collector specification.
     * @param username Username.
     * @param password Password.
     */
    private void login(final DCSpec dcSpec, final String username,
            final String password)
    {
        final long start = System.currentTimeMillis();

        // Visit Illyriad.
        final String url = dcSpec.getUrl();

        if (StringUtils.isEmpty(url))
        {
            System.out.println("logging in as " + username);
            getWebDriver().get("http://uk1.illyriad.co.uk/Account/LogOn");

            getWebDriver().findElement(By.name("PlayerName"))
                    .sendKeys(username);
            final WebElement passwordElement = getWebDriver().findElement(
                    By.name("Password"));
            passwordElement.sendKeys(password);
            passwordElement.submit();
            waitForPageToLoad("The Herald");

            // Open the advanced resources.
            getWebDriver().findElement(By.id("btnResource")).click();
        }
        else
        {
            getWebDriver().get(url);
        }

        final long end = System.currentTimeMillis();
        logTiming("login()", start, end);
    }

    /**
     * Logout of the service.
     * 
     * @param dcSpec Data collector specification.
     */
    private void logout(final DCSpec dcSpec)
    {
        final long start = System.currentTimeMillis();

        final String url = dcSpec.getUrl();

        if (StringUtils.isEmpty(url))
        {
            getWebDriver().findElement(By.className("logout")).click();
        }

        getWebDriver().quit();

        final long end = System.currentTimeMillis();
        logTiming("logoff()", start, end);
    }

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
     * @param prefix Prefix.
     */
    private void printPageTitle(final String prefix)
    {
        System.out.println(prefix + " Page title = ["
                + getWebDriver().getTitle() + "]");
    }

    /**
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     * @param category Category.
     * @param criterionName Criterion name.
     * @param value Value.
     */
    private void putRating(final RivalryData rivalryData,
            final Candidate candidate, final Category category,
            final String criterionName, final Double value)
    {
        final Criterion criterion = getCriterion(rivalryData, category,
                criterionName);

        candidate.putRating(criterion, value);
    }

    /**
     * @param pageTitle Page title.
     */
    private void waitForPageToLoad(final String pageTitle)
    {
        final int timeOutInSeconds = 60;
        final int sleepInMillis = 5000;
        final WebDriverWait wait = new WebDriverWait(getWebDriver(),
                timeOutInSeconds, sleepInMillis);
        wait.until(new WebElementTextCondition(By.tagName("title"), pageTitle));
        printPageTitle("waitForPageToLoad()");
    }
}
