//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Provides tests for the <code>CandidateValueComparator</code> class.
 */
public class CandidateValueComparatorTest
{
    /** Test data. */
    private final TestData _testData = new TestData();

    /** Rivalry data. */
    private final RivalryData _rivalryData = _testData.createRivalryData();

    /** Candidates. */
    private final List<Candidate> _candidates = new ArrayList<Candidate>(
            _rivalryData.getCandidates());

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compare0()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(0);

        final CandidateValueComparator comparator = new CandidateValueComparator(
                criterion);

        Collections.shuffle(_candidates);
        Collections.sort(_candidates, comparator);

        assertThat(_candidates.get(0).getName(), is("1"));
        assertThat(_candidates.get(1).getName(), is("2"));
        assertThat(_candidates.get(2).getName(), is("3"));
    }

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compare1()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(1);

        final CandidateValueComparator comparator = new CandidateValueComparator(
                criterion);

        Collections.shuffle(_candidates);
        Collections.sort(_candidates, comparator);

        assertThat(_candidates.get(0).getName(), is("1"));
        assertThat(_candidates.get(1).getName(), is("2"));
        assertThat(_candidates.get(2).getName(), is("3"));
    }

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compare2()
    {
        final Criterion criterion = _rivalryData.getCriteria().get(2);

        final CandidateValueComparator comparator = new CandidateValueComparator(
                criterion);

        Collections.shuffle(_candidates);
        Collections.sort(_candidates, comparator);

        assertThat(_candidates.get(0).getName(), is("1"));
        assertThat(_candidates.get(1).getName(), is("2"));
        assertThat(_candidates.get(2).getName(), is("3"));
    }
}
