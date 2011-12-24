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

    /** Criterion name. */
    private static final String CRITERION_NAME1 = "# of Players";

    /** Criterion name. */
    private static final String CRITERION_NAME2 = "Min Players";

    /** Criterion name. */
    private static final String CRITERION_NAME3 = "Max Players";

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNumPlayers0()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME1);
        assertNotNull(criterion);
        candidate0.putValue(criterion, "2 - 4");

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        final Criterion criterion2 = _rivalryData
                .findCriterionByName(CRITERION_NAME2);
        assertNotNull(criterion2);
        final Criterion criterion3 = _rivalryData
                .findCriterionByName(CRITERION_NAME3);
        assertNotNull(criterion3);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNotNull(candidate0.getValue(criterion2));
        assertThat((Integer)candidate0.getValue(criterion2), is(2));
        assertNotNull(candidate0.getValue(criterion3));
        assertThat((Integer)candidate0.getValue(criterion3), is(4));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNumPlayers1()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME1);
        assertNotNull(criterion);
        candidate0.putValue(criterion, "2 ? 5");

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        final Criterion criterion2 = _rivalryData
                .findCriterionByName(CRITERION_NAME2);
        assertNotNull(criterion2);
        final Criterion criterion3 = _rivalryData
                .findCriterionByName(CRITERION_NAME3);
        assertNotNull(criterion3);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNotNull(candidate0.getValue(criterion2));
        assertThat((Integer)candidate0.getValue(criterion2), is(2));
        assertNotNull(candidate0.getValue(criterion3));
        assertThat((Integer)candidate0.getValue(criterion3), is(5));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNumPlayersNaN()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME1);
        assertNotNull(criterion);
        candidate0.putValue(criterion, "N/A - something");

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        final Criterion criterion2 = _rivalryData
                .findCriterionByName(CRITERION_NAME2);
        assertNotNull(criterion2);
        final Criterion criterion3 = _rivalryData
                .findCriterionByName(CRITERION_NAME3);
        assertNotNull(criterion3);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNull(candidate0.getValue(criterion2));
        assertNull(candidate0.getValue(criterion3));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNumPlayersNull()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME1);
        assertNotNull(criterion);

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        final Criterion criterion2 = _rivalryData
                .findCriterionByName(CRITERION_NAME2);
        assertNotNull(criterion2);
        final Criterion criterion3 = _rivalryData
                .findCriterionByName(CRITERION_NAME3);
        assertNotNull(criterion3);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNull(candidate0.getValue(criterion2));
        assertNull(candidate0.getValue(criterion3));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessNumPlayersSingle()
    {
        Candidate candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        final Criterion criterion = _rivalryData
                .findCriterionByName(CRITERION_NAME1);
        assertNotNull(criterion);
        candidate0.putValue(criterion, "2");

        final DataPostProcessor dataPostProcessor = new BoardgameDataPostProcessor();
        dataPostProcessor.postProcess(_rivalryData);

        final Criterion criterion2 = _rivalryData
                .findCriterionByName(CRITERION_NAME2);
        assertNotNull(criterion2);
        final Criterion criterion3 = _rivalryData
                .findCriterionByName(CRITERION_NAME3);
        assertNotNull(criterion3);

        candidate0 = _rivalryData.getCandidates().get(0);
        assertNotNull(candidate0);
        assertNotNull(candidate0.getValue(criterion2));
        assertThat((Integer)candidate0.getValue(criterion2), is(2));
        assertNotNull(candidate0.getValue(criterion3));
        assertThat((Integer)candidate0.getValue(criterion3), is(2));
    }

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcessPlayingTime()
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
    public void postProcessPlayingTimeNaN()
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
    public void postProcessPlayingTimeNull()
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

        final Criterion criterion0 = new DefaultCriterion();
        criterion0.setName(CRITERION_NAME0);
        _rivalryData.getCriteria().add(criterion0);

        final Criterion criterion1 = new DefaultCriterion();
        criterion1.setName(CRITERION_NAME1);
        _rivalryData.getCriteria().add(criterion1);
    }
}
