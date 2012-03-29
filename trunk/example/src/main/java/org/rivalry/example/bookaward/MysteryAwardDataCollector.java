//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.bookaward;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.comparator.DefaultCandidateComparator;
import org.rivalry.core.comparator.DefaultCriterionComparator;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultCriterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector for mystery book award.
 */
public class MysteryAwardDataCollector implements DataCollector
{
    /** Base URL. */
    public static final String BASE_URL0 = "http://www.google.com/#hl=en&q=site:awards.omnimystery.com+%22";

    /** Base URL. */
    public static final String BASE_URL1 = "%22";

    @Override
    public void fetchData(final DCSpec dcSpec, final String username,
            final String password, final RivalryData rivalryData)
    {
        final long start = System.currentTimeMillis();

        final String author = username;
        final Criterion criterion = getCriterion(rivalryData, author);

        final WebDriver webDriver = createWebDriver();
        webDriver.get(dcSpec.getUrl());

        final List<WebElement> webElements = webDriver.findElements(By
                .className("r"));

        for (final WebElement webElement : webElements)
        {
            final WebElement anchor = webElement.findElement(By.tagName("a"));
            final String name = parseName(anchor.getText());
            final String page = anchor.getAttribute("href");

            final Candidate candidate = getCandidate(rivalryData, name, page);
            candidate.putValue(criterion, 1);
        }

        final long end = System.currentTimeMillis();
        logTiming("0 fetchData()", start, end);
    }

    /**
     * @param authors Authors.
     * @param rivalryData Rivalry data.
     */
    public void fetchData(final List<String> authors,
            final RivalryData rivalryData)
    {
        final long start = System.currentTimeMillis();

        final DCSpec dcSpec = new DCSpec();
        String username = null;
        final String password = null;

        for (final String author : authors)
        {
            String searchString;
            try
            {
                searchString = URLEncoder.encode(author, "UTF-8");
                final String url = BASE_URL0 + searchString + BASE_URL1;
                System.out
                        .println("searching for author " + author + " " + url);
                dcSpec.setUrl(url);
                username = author;
                fetchData(dcSpec, username, password, rivalryData);
            }
            catch (final UnsupportedEncodingException e)
            {
                throw new RuntimeException(e);
            }
        }

        Collections.sort(rivalryData.getCandidates(),
                new DefaultCandidateComparator());
        Collections.sort(rivalryData.getCriteria(),
                new DefaultCriterionComparator());

        final long end = System.currentTimeMillis();
        logTiming("fetchData()", start, end);
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
     * @param name Candidate name.
     * @param page Candidate page.
     * 
     * @return a candidate with the given name, creating it if necessary.
     */
    private Candidate getCandidate(final RivalryData rivalryData,
            final String name, final String page)
    {
        Candidate answer = rivalryData.findCandidateByName(name);

        if (answer == null)
        {
            answer = new DefaultCandidate();
            answer.setName(name);
            answer.setPage(page);

            rivalryData.getCandidates().add(answer);
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param name Criterion name.
     * 
     * @return a criterion with the given name, creating it if necessary.
     */
    private Criterion getCriterion(final RivalryData rivalryData,
            final String name)
    {
        Criterion answer = rivalryData.findCriterionByName(name);

        if (answer == null)
        {
            answer = new DefaultCriterion();
            answer.setName(name);

            rivalryData.getCriteria().add(answer);
        }

        return answer;
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
     * @param text Text.
     * 
     * @return the candidate name parsed from the given parameter.
     */
    private String parseName(final String text)
    {
        String answer = text;

        final int index = answer.indexOf(':');

        if (index >= 0)
        {
            answer = answer.substring(0, index);
        }

        return answer;
    }
}
