//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.bestplace;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DefaultDataCollector;
import org.rivalry.core.datacollector.DefaultNameStringParser;
import org.rivalry.core.datacollector.DefaultValueStringParser;
import org.rivalry.core.datacollector.NameStringParser;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.datacollector.io.DCSpecReader;
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
    /** Category provider. */
    private final Provider<Category> _categoryProvider = new DefaultCategoryProvider();

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider = new DefaultCriterionProvider();

    /** Data collector. */
    private DataCollector _dataCollector;

    /** Data collector specification. */
    private DCSpec _dcSpec;

    /** Best place value string processor. */
    private final NameStringParser _nameStringParser = new DefaultNameStringParser();

    /** Best place value string processor. */
    private final ValueStringParser _valueStringParser = new DefaultValueStringParser();

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataDenverColorado()
    {
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("colorado/denver",
                _dcSpec.getUrl());

        _dataCollector.fetchData(_dcSpec, rivalryData, candidate);

        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(4));
        assertThat(rivalryData.getCriteriaList().size(), is(9 + 4 + 2 + 8));

        // Climate
        verifyRating(candidate, rivalryData, "Climate", "Rainfall (in.)", 12.6);
        verifyRating(candidate, rivalryData, "Climate", "Snowfall (in.)", 53.8);
        verifyRating(candidate, rivalryData, "Climate", "Precipitation Days",
                78.0);
        verifyRating(candidate, rivalryData, "Climate", "Sunny Days", 245.0);
        verifyRating(candidate, rivalryData, "Climate", "Avg. July High", 86.0);
        verifyRating(candidate, rivalryData, "Climate", "Avg. Jan. Low", 21.6);
        verifyRating(candidate, rivalryData, "Climate",
                "Comfort Index (higher=better)", 57.0);
        verifyRating(candidate, rivalryData, "Climate", "UV Index", 4.8);
        verifyRating(candidate, rivalryData, "Climate", "Elevation ft.", 5228.0);

        // Cost of Living
        verifyRating(candidate, rivalryData, "Cost of Living", "Overall", 110.0);
        verifyRating(candidate, rivalryData, "Cost of Living", "Food", 103.0);
        verifyRating(candidate, rivalryData, "Cost of Living", "Utilities",
                91.0);
        verifyRating(candidate, rivalryData, "Cost of Living", "Miscellaneous",
                108.0);

        // Crime
        verifyRating(candidate, rivalryData, "Crime", "Violent Crime", 6.0);
        verifyRating(candidate, rivalryData, "Crime", "Property Crime", 5.0);

        // Education
        verifyRating(candidate, rivalryData, "Education", "School Expend.",
                4375.0);
        verifyRating(candidate, rivalryData, "Education",
                "Pupil/Teacher Ratio", 18.8);
        verifyRating(candidate, rivalryData, "Education",
                "Students per Librarian", 551.0);
        verifyRating(candidate, rivalryData, "Education",
                "Students per Counselor", 713.0);
        verifyRating(candidate, rivalryData, "Education", "2 yr College Grad.",
                0.0492);
        verifyRating(candidate, rivalryData, "Education", "4 yr College Grad.",
                0.2270);
        verifyRating(candidate, rivalryData, "Education", "Graduate Degrees",
                0.1542);
        verifyRating(candidate, rivalryData, "Education", "High School Grads.",
                0.8256);
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataPortlandOregon()
    {
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("oregon/portland",
                _dcSpec.getUrl());

        _dataCollector.fetchData(_dcSpec, rivalryData, candidate);

        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(4));
        assertThat(rivalryData.getCriteriaList().size(), is(9 + 4 + 2 + 8));

        // Climate
        verifyRating(candidate, rivalryData, "Climate", "Rainfall (in.)", 42.4);
        verifyRating(candidate, rivalryData, "Climate", "Snowfall (in.)", 3.3);
        verifyRating(candidate, rivalryData, "Climate", "Precipitation Days",
                154.0);
        verifyRating(candidate, rivalryData, "Climate", "Sunny Days", 144.0);
        verifyRating(candidate, rivalryData, "Climate", "Avg. July High", 79.0);
        verifyRating(candidate, rivalryData, "Climate", "Avg. Jan. Low", 36.6);
        verifyRating(candidate, rivalryData, "Climate",
                "Comfort Index (higher=better)", 63.0);
        verifyRating(candidate, rivalryData, "Climate", "UV Index", 3.0);
        verifyRating(candidate, rivalryData, "Climate", "Elevation ft.", 79.0);

        // Cost of Living
        verifyRating(candidate, rivalryData, "Cost of Living", "Overall", 119.0);
        verifyRating(candidate, rivalryData, "Cost of Living", "Food", 108.0);
        verifyRating(candidate, rivalryData, "Cost of Living", "Utilities",
                92.0);
        verifyRating(candidate, rivalryData, "Cost of Living", "Miscellaneous",
                104.0);

        // Crime
        verifyRating(candidate, rivalryData, "Crime", "Violent Crime", 6.0);
        verifyRating(candidate, rivalryData, "Crime", "Property Crime", 7.0);

        // Education
        verifyRating(candidate, rivalryData, "Education", "School Expend.",
                5386.0);
        verifyRating(candidate, rivalryData, "Education",
                "Pupil/Teacher Ratio", 17.5);
        verifyRating(candidate, rivalryData, "Education",
                "Students per Librarian", 543.0);
        verifyRating(candidate, rivalryData, "Education",
                "Students per Counselor", 500.0);
        verifyRating(candidate, rivalryData, "Education", "2 yr College Grad.",
                0.0625);
        verifyRating(candidate, rivalryData, "Education", "4 yr College Grad.",
                0.2412);
        verifyRating(candidate, rivalryData, "Education", "Graduate Degrees",
                0.1541);
        verifyRating(candidate, rivalryData, "Education", "High School Grads.",
                0.8908);
    }

    /**
     * Set up the tests.
     */
    @Before
    public void setUp()
    {
        _dataCollector = new DefaultDataCollector(_nameStringParser,
                _valueStringParser, _categoryProvider, _criterionProvider);

        _dcSpec = readDcSpec();
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
     * @return a new data collector specification read from a file of the given
     *         name.
     */
    private DCSpec readDcSpec()
    {
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorBestPlaces.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec dcSpec = dcReader.read(reader);
        assertNotNull(dcSpec);

        return dcSpec;
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param categoryName Category name.
     * @param criterionName Criterion name.
     * @param expectedRating Expected rating.
     */
    private void verifyRating(final Candidate candidate,
            final RivalryData rivalryData, final String categoryName,
            final String criterionName, final Double expectedRating)
    {
        final Criterion criterion = rivalryData
                .findCriterionByName(criterionName);
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertEquals(rating, expectedRating, 0.0001);
        final Category category = rivalryData.findCategoryByName(categoryName);
        assertNotNull(category);
        assertThat(criterion.getCategory(), is(category));
    }
}
