package org.rivalry.core.fitness;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>WeightedSumFitnessFunction</code> class.
 */
public class WeightedSumFitnessFunctionTest
{
    /** Fitness function. */
    private FitnessFunction _fitnessFunction;

    /** Test data. */
    private final TestData _testData = new TestData();

    /** Rivalry data. */
    private final RivalryData _rivalryData = _testData.createRivalryData();

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitness0()
    {
        final Candidate candidate = _rivalryData.getCandidates().get(0);
        final Double result = _fitnessFunction.computeFitness(candidate);
        assertNotNull(result);
        assertThat(result, is(7.4));
    }

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitness1()
    {
        final Candidate candidate = _rivalryData.getCandidates().get(1);
        Double result = _fitnessFunction.computeFitness(candidate);
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
        final Candidate candidate = _rivalryData.getCandidates().get(2);
        final Double result = _fitnessFunction.computeFitness(candidate);
        assertNotNull(result);
        assertThat(result, is(19.4));
    }

    /**
     * Test the <code>computeFitness()</code> method.
     */
    @Test
    public void computeFitnessNull()
    {
        final Candidate candidate = null;
        final Double result = _fitnessFunction.computeFitness(candidate);
        assertNull(result);
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        final String prefPrefix = "test";
        _fitnessFunction = _testData.createWeightedSumFitnessFunction(prefPrefix, _rivalryData);
    }
}
