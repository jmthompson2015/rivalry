package org.rivalry.example.boardgame;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCriterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>BoardGameDataPostProcessor</code> class.
 */
public class BoardgameDataPostProcessorTest
{
    /** Rivalry data. */
    private RivalryData _rivalryData;

    /** Criterion name. */
    private static final String CRITERION_NAME0 = "Playing Time";

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcess()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME0);
        assertNotNull(criterion);
        candidate0.putValue(criterion, "180 minutes");

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNotNull(candidate0.getValue(criterion));
        assertThat((Integer)candidate0.getValue(criterion), is(180));
        assertNotNull(candidate0.getRating(criterion));
        assertThat(candidate0.getRating(criterion), is(180.0));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNaN()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME0);
        assertNotNull(criterion);
        candidate0.putValue(criterion, "N/A minutes");

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNotNull(candidate0.getValue(criterion));
        assertThat((String)candidate0.getValue(criterion), is("N/A minutes"));
        assertNull(candidate0.getRating(criterion));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNull()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME0);
        assertNotNull(criterion);

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNull(candidate0.getValue(criterion));
        assertNull(candidate0.getRating(criterion));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        final TestData testData = new TestData();
        _rivalryData = testData.createRivalryData();
        final Criterion criterion = new DefaultCriterion();
        criterion.setName(CRITERION_NAME0);
        _rivalryData.getCriteria().add(criterion);
    }
}
