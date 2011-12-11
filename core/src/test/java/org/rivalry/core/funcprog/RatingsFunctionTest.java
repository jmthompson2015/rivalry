//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.funcprog;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>RatingsFunction</code> class.
 */
public class RatingsFunctionTest
{
    /** Rivalry data. */
    private final RivalryData _rivalryData = new TestData().createRivalryData();

    /**
     * Test the <code>apply()</code> method.
     */
    @Test
    public void apply()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(0);
        final RatingsFunction function = new RatingsFunction(criterion);

        {
            final Candidate candidate = _rivalryData.getCandidates().get(0);
            final Double result = function.apply(candidate);
            assertNotNull(result);
            assertThat(result, is(1.1));
        }

        {
            final Candidate candidate = _rivalryData.getCandidates().get(1);
            final Double result = function.apply(candidate);
            assertNotNull(result);
            assertThat(result, is(2.1));
        }

        {
            final Candidate candidate = _rivalryData.getCandidates().get(2);
            final Double result = function.apply(candidate);
            assertNotNull(result);
            assertThat(result, is(3.1));
        }
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void map0()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(0);
        final RatingsFunction function = new RatingsFunction(criterion);

        final List<Double> result = ListUtils.map(_rivalryData.getCandidates(),
                function);
        assertNotNull(result);
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(1.1));
        assertThat(result.get(1), is(2.1));
        assertThat(result.get(2), is(3.1));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void map1()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(1);
        final RatingsFunction function = new RatingsFunction(criterion);

        final List<Double> result = ListUtils.map(_rivalryData.getCandidates(),
                function);
        assertNotNull(result);
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(1.2));
        assertThat(result.get(1), is(2.2));
        assertThat(result.get(2), is(3.2));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void map2()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(2);
        final RatingsFunction function = new RatingsFunction(criterion);

        final List<Double> result = ListUtils.map(_rivalryData.getCandidates(),
                function);
        assertNotNull(result);
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(1.3));
        assertThat(result.get(1), is(2.3));
        assertThat(result.get(2), is(3.3));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapThenMinMax()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(0);
        final RatingsFunction function = new RatingsFunction(criterion);

        final List<Double> result = ListUtils.map(_rivalryData.getCandidates(),
                function);

        final MinFunction<Double> minFunction = new MinFunction<Double>();
        final Double minResult = ListUtils.reduce(result, minFunction);
        assertNotNull(minResult);
        assertThat(minResult, is(1.1));

        final MaxFunction<Double> maxFunction = new MaxFunction<Double>();
        final Double maxResult = ListUtils.reduce(result, maxFunction);
        assertNotNull(maxResult);
        assertThat(maxResult, is(3.1));
    }
}
