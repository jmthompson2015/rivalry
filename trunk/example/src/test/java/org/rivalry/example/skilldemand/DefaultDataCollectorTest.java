//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.skilldemand;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultRivalryData;
import org.rivalry.core.model.RivalryData;

/**
 * Provides tests for the <code>DefaultDataCollector</code> class.
 */
public class DefaultDataCollectorTest
{
    /** Data collector. */
    private DataCollector _dataCollector;

    /** Data collector specification. */
    private DCSpec _dcSpec;

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataJava()
    {
        final String username = null;
        final String password = null;
        final RivalryData rivalryData = new DefaultRivalryData();
        final Candidate candidate = createCandidate("Java", _dcSpec.getUrl());
        rivalryData.getCandidates().add(candidate);

        _dataCollector.fetchData(_dcSpec, username, password, rivalryData);

        assertThat(rivalryData.getCandidates().size(), is(1));
        assertThat(rivalryData.getCategories().size(), is(0));
        assertThat(rivalryData.getCriteria().size(), is(1));

        final Candidate candidate0 = rivalryData.getCandidates().get(0);
        final Criterion criterion0 = rivalryData.getCriteria().get(0);

        assertThat(candidate0.getName(), is("Java"));
        assertThat(criterion0.getName(), is("Count"));

        final Integer value = (Integer)candidate0.getValue(criterion0);
        assertNotNull(value);
        System.out.println("value = [" + value + "] " + value.getClass().getName());
        // assertThat(value, is(17548));
        assertTrue(value > 0);

        final Double rating = candidate0.getRating(criterion0);
        assertNotNull(rating);
        // assertThat(rating, is(17548.0));
        assertTrue(rating > 0);
    }

    /**
     * Set up the tests.
     */
    @Before
    public void setUp()
    {
        final DataCollectorInjector injector = new SkillDemandInjector();
        _dataCollector = injector.injectDataCollector();

        _dcSpec = readDcSpec();
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
     * @return a new data collector specification read from a file of the given name.
     */
    private DCSpec readDcSpec()
    {
        final InputStream inputStream = getClass().getResourceAsStream("DataCollectorDice.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec dcSpec = dcReader.read(reader);
        assertNotNull(dcSpec);

        return dcSpec;
    }
}
