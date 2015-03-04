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

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultCandidateProvider;
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Provides tests for the <code>DefaultDataCollector</code> class.
 */
public class DefaultDataCollectorTest
{
    /**
     * Provides a parser for double values.
     */
    static class YahooFinanceParser implements ValueStringParser<Double>
    {
        @Override
        public Double parse(final String valueString)
        {
            Double answer = null;

            if (StringUtils.isNotEmpty(valueString))
            {
                final String myString = valueString.trim();

                if (myString.endsWith("%"))
                {
                    answer = parseDoubleOnly(myString.substring(0, myString.length() - 1)) / 100.0;
                }
                else if (myString.endsWith("B"))
                {
                    answer = parseDoubleOnly(myString.substring(0, myString.length() - 1)) * 1000000000;
                }
                else
                {
                    answer = parseDoubleOnly(myString);
                }
            }

            return answer;
        }

        @Override
        public Double parse(final WebElement webElement)
        {
            Double answer = null;

            if (webElement != null)
            {
                final String valueString = webElement.getText();
                answer = parse(valueString);
            }

            return answer;
        }

        /**
         * @param valueString Value string.
         *
         * @return a double parsed from the given parameter, if possible.
         */
        private Double parseDoubleOnly(final String valueString)
        {
            Double answer = null;

            try
            {
                answer = Double.parseDouble(valueString);
            }
            catch (final NumberFormatException ignore)
            {
                // Nothing to do.
            }

            return answer;
        }
    }

    /** Best places value string processor. */
    private final ValueStringParser<?> _bestPlacesParser = new DefaultValueStringParser();

    /** Category provider. */
    private final Provider<Candidate> _candidateProvider = new DefaultCandidateProvider();

    /** Category provider. */
    private final Provider<Category> _categoryProvider = new DefaultCategoryProvider();

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider = new DefaultCriterionProvider();

    /** Rivalry data post processor. */
    private final DataPostProcessor _dataPostProcessor = new DefaultDataPostProcessor();

    /** Flag indicating whether to enable Javascript. */
    private final boolean _isJavascriptEnabled = false;

    /** Flag indicating whether to provide verbose output. */
    private final boolean _isVerbose = false;

    /** Name string parser. */
    private final NameStringParser _nameStringParser = new DefaultNameStringParser();

    /** Yahoo! Finance value string processor. */
    private final ValueStringParser<Double> _yahooParser = new YahooFinanceParser();

    /** Flag indicating whether to create an average candidate. */
    private final boolean isAverageCandidateCreated = false;

    /** Flag indicating whether to create a median candidate. */
    private final boolean isMedianCandidateCreated = false;

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataBestPlaces()
    {
        final DataCollector dataCollector = new DefaultDataCollector(_isJavascriptEnabled, 5, _nameStringParser,
                _bestPlacesParser, _candidateProvider, _categoryProvider, _criterionProvider, _dataPostProcessor,
                isAverageCandidateCreated, isMedianCandidateCreated);

        final DCSpec dcSpec = createDCSpecBestPlaces();
        final String username = null;
        final String password = null;
        final RivalryData rivalryData = new RivalryData();

        {
            final Candidate candidate = createCandidate("Denver CO",
                    "http://www.bestplaces.net/climate/city/colorado/denver");
            rivalryData.getCandidates().add(candidate);
        }

        {
            final Candidate candidate = createCandidate("San Diego CA",
                    "http://www.bestplaces.net/climate/city/california/san_diego");
            rivalryData.getCandidates().add(candidate);
        }

        {
            final Candidate candidate = createCandidate("West Lafayette IN",
                    "http://www.bestplaces.net/city/indiana/west_lafayette");
            rivalryData.getCandidates().add(candidate);
        }

        {
            final Candidate candidate = createCandidate("Clearwater FL",
                    "http://www.bestplaces.net/city/florida/clearwater");
            rivalryData.getCandidates().add(candidate);
        }

        {
            final Candidate candidate = createCandidate("Jeffersonville IN",
                    "http://www.bestplaces.net/city/indiana/jeffersonville");
            rivalryData.getCandidates().add(candidate);
        }

        dataCollector.fetchData(dcSpec, username, password, rivalryData);

        assertNotNull(rivalryData.getCandidates());
        assertNotNull(rivalryData.getCategories());
        assertNotNull(rivalryData.getCriteria());
        assertThat(rivalryData.getCandidates().size(), is(5));
        assertThat(rivalryData.getCategories().size(), is(0));
        assertThat(rivalryData.getCriteria().size(), is(9));

        final Criterion criterion = rivalryData.findCriterionByName("Snowfall (in.)");
        assertNotNull(criterion);
        {
            final Candidate candidate = rivalryData.getCandidates().get(0);
            final Double rating = candidate.getRating(criterion);
            assertNotNull(rating);
            assertThat(rating, is(53.8));
        }
        {
            final Candidate candidate = rivalryData.getCandidates().get(1);
            final Double rating = candidate.getRating(criterion);
            assertNotNull(rating);
            assertThat(rating, is(0.0));
        }
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataYahooFinance()
    {
        final DataCollector dataCollector = new DefaultDataCollector(_isJavascriptEnabled, 1, _nameStringParser,
                _yahooParser, _candidateProvider, _categoryProvider, _criterionProvider, _dataPostProcessor,
                isAverageCandidateCreated, isMedianCandidateCreated);

        final DCSpec dcSpec = createDCSpecYahooFinance();
        final String username = null;
        final String password = null;
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("AAPL", dcSpec.getUrl());
        rivalryData.getCandidates().add(candidate);

        dataCollector.fetchData(dcSpec, username, password, rivalryData);

        assertNotNull(rivalryData.getCandidates());
        assertNotNull(rivalryData.getCategories());
        assertNotNull(rivalryData.getCriteria());
        assertThat(rivalryData.getCandidates().size(), is(1));
        assertThat(rivalryData.getCategories().size(), is(0));
        assertThat(rivalryData.getCriteria().size(), is(57));

        if (_isVerbose)
        {
            for (final Criterion criterion : rivalryData.getCriteria())
            {
                System.out.println("criterion = [" + criterion.getName() + "]");
            }
        }

        final Criterion criterion = rivalryData.findCriterionByName("Profit Margin (ttm):");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(0.2225));
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataYahooFinanceHtmlUnitDriver()
    {
        final DataCollector dataCollector = new DefaultDataCollector(_isJavascriptEnabled, 1, _nameStringParser,
                _yahooParser, _candidateProvider, _categoryProvider, _criterionProvider, _dataPostProcessor,
                isAverageCandidateCreated, isMedianCandidateCreated);

        final DCSpec dcSpec = createDCSpecYahooFinance();
        final String username = null;
        final String password = null;
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("INTC", dcSpec.getUrl());
        rivalryData.getCandidates().add(candidate);

        dataCollector.fetchData(dcSpec, username, password, rivalryData);

        assertNotNull(rivalryData.getCandidates());
        assertNotNull(rivalryData.getCategories());
        assertNotNull(rivalryData.getCriteria());
        assertThat(rivalryData.getCandidates().size(), is(1));
        assertThat(rivalryData.getCategories().size(), is(0));
        assertThat(rivalryData.getCriteria().size(), is(57));

        final Criterion criterion = rivalryData.findCriterionByName("Profit Margin (ttm):");
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertThat(rating, is(0.2095));
    }

    /**
     *
     * @param candidateName Candidate name.
     * @param url URL.
     *
     * @return a new candidate.
     */
    private Candidate createCandidate(final String candidateName, final String url)
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

        answer.setType(SelectorType.ID);
        answer.setValue("mainContent_dgClimate");

        answer.getSelectors().add(createSelectorBestPlaces1());
        answer.getSelectors().add(createSelectorBestPlaces2());

        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorBestPlaces1()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.XPATH);
        answer.setValue("//td/a|//td/font/a");

        return answer;
    }

    /**
     * @return a new selector.
     */
    private DCSelector createSelectorBestPlaces2()
    {
        final DCSelector answer = new DCSelector();

        answer.setType(SelectorType.XPATH);
        answer.setValue("(//td/a|//td/font/a)/ancestor::td/following-sibling::td[1]");

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

        return answer;
    }
}
