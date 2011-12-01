//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Provides tests for the <code>DefaultDataCollector</code> class.
 */
public class DefaultDataCollectorTest
{
    /** Flag indicating whether to provide verbose output. */
    private final boolean _isVerbose = false;

    /** Category provider. */
    private final Provider<Category> _categoryProvider = new DefaultCategoryProvider();

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider = new DefaultCriterionProvider();

    /** Best places value string processor. */
    private final ValueStringParser _bestPlacesProcessor = new DefaultValueStringParser();

    /** Dog breeds value string processor. */
    private final ValueStringParser _dogBreedsProcessor = new DogBreedsParser();

    /** Yahoo! Finance value string processor. */
    private final ValueStringParser _yahooProcessor = new YahooFinanceParser();

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataBestPlaces()
    {
        final DataCollector dataCollector = new DefaultDataCollector(
                _bestPlacesProcessor, _categoryProvider, _criterionProvider);

        final DCSpec dcSpec = createDCSpecBestPlaces();
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("724690", dcSpec.getUrl());

        dataCollector.fetchData(dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(57));
        assertThat(rivalryData.getCriteriaList().size(), is(57));
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataDogBreeds()
    {
        final DataCollector dataCollector = new DefaultDataCollector(
                _dogBreedsProcessor, _categoryProvider, _criterionProvider);

        final DCSpec dcSpec = createDCSpecDogBreeds();
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("boston-terrier",
                dcSpec.getUrl());

        dataCollector.fetchData(dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(25));
        assertThat(rivalryData.getCriteriaList().size(), is(25));

        if (_isVerbose)
        {
            for (final Criterion criterion : rivalryData.getCriteriaList())
            {
                System.out.println("criterion = [" + criterion.getName() + "]");
            }
        }

        final Criterion criterion = rivalryData
                .findCriterionByName("Adapt well to apartment living");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(5.0));
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataYahooFinance()
    {
        final DataCollector dataCollector = new DefaultDataCollector(
                _yahooProcessor, _categoryProvider, _criterionProvider);

        final DCSpec dcSpec = createDCSpecYahooFinance();
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("AAPL", dcSpec.getUrl());

        dataCollector.fetchData(dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(57));
        assertThat(rivalryData.getCriteriaList().size(), is(57));

        if (_isVerbose)
        {
            for (final Criterion criterion : rivalryData.getCriteriaList())
            {
                System.out.println("criterion = [" + criterion.getName() + "]");
            }
        }

        final Criterion criterion = rivalryData
                .findCriterionByName("Profit Margin (ttm):");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(0.2353));
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataYahooFinanceChromeDriver()
    {
        System.setProperty("webdriver.chrome.driver",
                "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        final DataCollector dataCollector = new DefaultDataCollector(
                _yahooProcessor, _categoryProvider, _criterionProvider);

        final WebDriver webDriver = new ChromeDriver();
        final DCSpec dcSpec = createDCSpecYahooFinance();
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("INTC", dcSpec.getUrl());

        dataCollector.fetchData(webDriver, dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(57));
        assertThat(rivalryData.getCriteriaList().size(), is(57));

        final Criterion criterion = rivalryData
                .findCriterionByName("Profit Margin (ttm):");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(0.2529));
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataYahooFinanceFirefoxDriver()
    {
        final DataCollector dataCollector = new DefaultDataCollector(
                _yahooProcessor, _categoryProvider, _criterionProvider);

        final WebDriver webDriver = new FirefoxDriver();
        final DCSpec dcSpec = createDCSpecYahooFinance();
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("INTC", dcSpec.getUrl());

        dataCollector.fetchData(webDriver, dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(57));
        assertThat(rivalryData.getCriteriaList().size(), is(57));

        final Criterion criterion = rivalryData
                .findCriterionByName("Profit Margin (ttm):");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(0.2529));
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataYahooFinanceHtmlUnitDriver()
    {
        final DataCollector dataCollector = new DefaultDataCollector(
                _yahooProcessor, _categoryProvider, _criterionProvider);

        final WebDriver webDriver = new HtmlUnitDriver();
        final DCSpec dcSpec = createDCSpecYahooFinance();
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("INTC", dcSpec.getUrl());

        dataCollector.fetchData(webDriver, dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(57));
        assertThat(rivalryData.getCriteriaList().size(), is(57));

        final Criterion criterion = rivalryData
                .findCriterionByName("Profit Margin (ttm):");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(0.2475));
    }

    /**
     * 
     * @param candidateName Candidate name.
     * @param url URL.
     * 
     * @return a new candidate.
     */
    private Candidate createCandidate(final String candidateName,
            final String url)
    {
        String page = url;

        if (page.contains("$1"))
        {
            page = page.replaceAll("\\$1", candidateName);
        }

        final Candidate answer = new DefaultCandidate();

        answer.setName(candidateName);
        answer.setPage(page);

        return answer;
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpecBestPlaces()
    {
        final DCSpec answer = new DCSpec();

        answer.setUrl("http://www.bestplaces.net/climate/details.aspx?wmo=$1");
        final DCSelector selector0 = createSelectorBestPlaces0();
        System.out.println("selector0 = " + selector0);
        answer.getSelectors().add(selector0);

        return answer;
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpecDogBreeds()
    {
        final DCSpec answer = new DCSpec();

        answer.setUrl("http://dogtime.com/dog-breeds/$1");
        final DCSelector selector0 = createSelectorDogBreeds0();
        System.out.println("selector0 = " + selector0);
        answer.getSelectors().add(selector0);

        return answer;
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpecYahooFinance()
    {
        final DCSpec answer = new DCSpec();

        answer.setUrl("http://finance.yahoo.com/q/ks?s=$1+Key+Statistics");
        final DCSelector selector0 = createSelectorYahooFinance0();
        System.out.println("selector0 = " + selector0);
        answer.getSelectors().add(selector0);

        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorBestPlaces0()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.CLASS_NAME);
        answer.setValue("data");

        answer.getSelectors().add(createSelectorBestPlaces1());
        answer.getSelectors().add(createSelectorBestPlaces2());

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorBestPlaces1()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.XPATH);
        answer.setValue("something");

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorBestPlaces2()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.XPATH);
        answer.setValue("something");

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorDogBreeds0()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.CLASS_NAME);
        answer.setValue("characteristics");

        answer.getSelectors().add(createSelectorDogBreeds1());
        answer.getSelectors().add(createSelectorDogBreeds2());

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorDogBreeds1()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.TAG_NAME);
        answer.setValue("h3");

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorDogBreeds2()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.CLASS_NAME);
        answer.setValue("five-star-ratings");

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorYahooFinance0()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.CLASS_NAME);
        answer.setValue("yfnc_datamodoutline1");

        answer.getSelectors().add(createSelectorYahooFinance1());
        answer.getSelectors().add(createSelectorYahooFinance2());

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorYahooFinance1()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.CLASS_NAME);
        answer.setValue("yfnc_tablehead1");

        // return new SelectorDecorator(answer);
        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorYahooFinance2()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.CLASS_NAME);
        answer.setValue("yfnc_tabledata1");

        // return new SelectorDecorator(answer);
        return answer;
    }
}
