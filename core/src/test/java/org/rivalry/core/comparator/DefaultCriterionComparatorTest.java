//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>DefaultCriterionComparator</code> class.
 */
public class DefaultCriterionComparatorTest
{
    /** Comparator. */
    private Comparator<Criterion> _comparator;

    /** Rivalry data. */
    private RivalryData _rivalryData;

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compare()
    {
        final List<Criterion> list = _rivalryData.getCriteria();

        final Criterion element0 = list.get(0);
        final Criterion element1 = list.get(1);
        final Criterion element2 = list.get(2);

        assertThat(_comparator.compare(element0, element0), is(0));
        assertThat(_comparator.compare(element0, element1), is(-1));
        assertThat(_comparator.compare(element0, element2), is(-2));

        assertThat(_comparator.compare(element1, element0), is(1));
        assertThat(_comparator.compare(element1, element1), is(0));
        assertThat(_comparator.compare(element1, element2), is(-1));

        assertThat(_comparator.compare(element2, element0), is(2));
        assertThat(_comparator.compare(element2, element1), is(1));
        assertThat(_comparator.compare(element2, element2), is(0));

        Collections.shuffle(list);
        Collections.sort(list, _comparator);

        assertThat(list.get(0).getName(), is("a"));
        assertThat(list.get(1).getName(), is("b"));
        assertThat(list.get(2).getName(), is("c"));
    }

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compareNulls()
    {
        final List<Criterion> list = _rivalryData.getCriteria();
        final Criterion element0 = list.get(0);

        assertThat(_comparator.compare(null, null), is(0));
        assertThat(_comparator.compare(null, element0), is(1));
        assertThat(_comparator.compare(element0, null), is(-1));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        _comparator = new DefaultCriterionComparator();
        final TestData testData = new TestData();
        _rivalryData = testData.createRivalryData();
    }
}
