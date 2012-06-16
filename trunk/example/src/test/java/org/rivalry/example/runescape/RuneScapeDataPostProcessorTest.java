package org.rivalry.example.runescape;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides tests for the <code>RuneScapeDataPostProcessor</code> class.
 */
public class RuneScapeDataPostProcessorTest
{
    /** Test data. */
    private final TestData _testData = new TestData();

    /** Data post processor. */
    private final DataPostProcessor _dataPostProcessor = new RuneScapeDataPostProcessor();

    /**
     * Test the <code>postProcess()</code> method.
     */
    @Test
    public void postProcess()
    {
        final RivalryData rivalryData = _testData.createRivalryData();

        _dataPostProcessor.postProcess(rivalryData);

        // assertThat(rivalryData.getCandidates().size(), is(66));

        for (int i = 0; i < 64; i++)
        {
            final Candidate candidate = rivalryData.getCandidates().get(i);
            System.out.println(i + " " + candidate.getName());
        }

        final Criterion valueCriterion = rivalryData.findCriterionByName(Constants.VALUE_CRITERION);
        final Criterion costCriterion = rivalryData.findCriterionByName(Constants.COST_CRITERION);
        final Criterion baseCostCriterion = rivalryData.findCriterionByName(Constants.BASE_COST_CRITERION);
        final Criterion premiumCriterion = rivalryData.findCriterionByName(Constants.PREMIUM_CRITERION);
        final Criterion basePremiumCriterion = rivalryData.findCriterionByName(Constants.BASE_PREMIUM_CRITERION);

        {
            final Candidate candidate = rivalryData.getCandidates().get(5);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Gold ore"));
            assertThat(candidate.getRating(valueCriterion), is(600.0));
            assertThat(candidate.getRating(costCriterion), is(600.0));
            assertThat(candidate.getRating(baseCostCriterion), is(600.0));
            assertThat(candidate.getRating(premiumCriterion), is(0.0));
            assertThat(candidate.getRating(basePremiumCriterion), is(0.0));
        }

        {
            final Candidate candidate = rivalryData.getCandidates().get(13);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Gold bar"));
            assertThat(candidate.getRating(valueCriterion), is(550.0));
            assertThat(candidate.getRating(costCriterion), is(600.0));
            assertThat(candidate.getRating(baseCostCriterion), is(600.0));
            assertThat(candidate.getRating(premiumCriterion), is(-8.0));
            assertThat(candidate.getRating(basePremiumCriterion), is(-8.0));
        }

        {
            final Candidate candidate = rivalryData.getCandidates().get(18);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Uncut emerald"));
            assertThat(candidate.getRating(valueCriterion), is(120.0));
            assertThat(candidate.getRating(costCriterion), is(120.0));
            assertThat(candidate.getRating(baseCostCriterion), is(120.0));
            assertThat(candidate.getRating(premiumCriterion), is(0.0));
            assertThat(candidate.getRating(basePremiumCriterion), is(0.0));
        }

        {
            final Candidate candidate = rivalryData.getCandidates().get(24);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Emerald"));
            assertThat(candidate.getRating(valueCriterion), is(125.0));
            assertThat(candidate.getRating(costCriterion), is(120.0));
            assertThat(candidate.getRating(baseCostCriterion), is(120.0));
            assertThat(candidate.getRating(premiumCriterion), is(4.0));
            assertThat(candidate.getRating(basePremiumCriterion), is(4.0));
        }

        {
            final Candidate candidate = rivalryData.getCandidates().get(30);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Emerald ring"));
            assertThat(candidate.getRating(valueCriterion), is(987.0));
            assertThat(candidate.getRating(costCriterion), is(675.0));
            assertThat(candidate.getRating(baseCostCriterion), is(720.0));
            assertThat(candidate.getRating(premiumCriterion), is(46.0));
            assertThat(candidate.getRating(basePremiumCriterion), is(37.0));
        }

        {
            final Candidate candidate = rivalryData.getCandidates().get(56);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Ring of duelling"));
            assertThat(candidate.getRating(valueCriterion), is(2222.0));
            assertThat(candidate.getRating(costCriterion), is(1037.0));
            assertThat(candidate.getRating(baseCostCriterion), is(949.0));
            assertThat(candidate.getRating(premiumCriterion), is(114.0));
            assertThat(candidate.getRating(basePremiumCriterion), is(134.0));
        }
    }
}
