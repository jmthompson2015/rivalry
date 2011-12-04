package org.rivalry.core.fitness;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>WeightedSumFitnessFunction</code> class.
 */
public class WeightedSumFitnessFunctionTest
{
    /** Fitness function. */
    private final FitnessFunction _fitnessFunction = new WeightedSumFitnessFunction();

    /** Test data. */
    private final TestData _testData = new TestData();

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitness0()
    {
        final RivalryData rivalryData = _testData.createRivalryData();
        final Candidate candidate = rivalryData.getCandidatesList().get(0);
        final List<Criterion> criteria = rivalryData.getCriteriaList();
        final Double result = _fitnessFunction.computeFitness(candidate,
                criteria);
        assertNotNull(result);
        assertThat(result, is(7.4));
    }

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitness1()
    {
        final RivalryData rivalryData = _testData.createRivalryData();
        final Candidate candidate = rivalryData.getCandidatesList().get(1);
        final List<Criterion> criteria = rivalryData.getCriteriaList();
        Double result = _fitnessFunction.computeFitness(candidate, criteria);
        assertNotNull(result);
        result = Math.round(result * 100.0) / 100.0;
        assertThat(result, is(13.40));
    }

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitness2()
    {
        final RivalryData rivalryData = _testData.createRivalryData();
        final Candidate candidate = rivalryData.getCandidatesList().get(2);
        final List<Criterion> criteria = rivalryData.getCriteriaList();
        final Double result = _fitnessFunction.computeFitness(candidate,
                criteria);
        assertNotNull(result);
        assertThat(result, is(19.4));
    }

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitnessNulls()
    {
        final Candidate candidate = null;
        List<Criterion> criteria = null;
        Double result = _fitnessFunction.computeFitness(candidate, criteria);
        assertNull(result);

        final RivalryData rivalryData = _testData.createRivalryData();
        criteria = rivalryData.getCriteriaList();
        result = _fitnessFunction.computeFitness(candidate, criteria);
        assertNull(result);
    }
}
