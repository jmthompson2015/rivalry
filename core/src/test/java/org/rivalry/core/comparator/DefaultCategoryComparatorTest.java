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
import org.rivalry.core.model.Category;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>DefaultCategoryComparator</code> class.
 */
public class DefaultCategoryComparatorTest
{
    /** Comparator. */
    private Comparator<Category> _comparator;

    /** Rivalry data. */
    private RivalryData _rivalryData;

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compare()
    {
        final List<Category> list = _rivalryData.getCategories();

        final Category element0 = list.get(0);
        final Category element1 = list.get(1);
        final Category element2 = list.get(2);

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

        assertThat(list.get(0).getName(), is("A"));
        assertThat(list.get(1).getName(), is("B"));
        assertThat(list.get(2).getName(), is("C"));
    }

    /**
     * Test the <code>compare()</code> method.
     */
    @Test
    public void compareNulls()
    {
        final List<Category> list = _rivalryData.getCategories();
        final Category element0 = list.get(0);

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
        _comparator = new DefaultCategoryComparator();
        final TestData testData = new TestData();
        _rivalryData = testData.createRivalryData();
    }
}
