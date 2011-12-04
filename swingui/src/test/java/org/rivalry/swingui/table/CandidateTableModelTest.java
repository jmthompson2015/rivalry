//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>CandidateTableModel</code> class.
 */
public class CandidateTableModelTest
{
    /** Table model. */
    private CandidateTableModel _tableModel;

    /**
     * Test the <code>getColumnClass()</code> method.
     */
    @Test
    public void getColumnClass()
    {
        Class<?> result = _tableModel.getColumnClass(0);
        assertTrue(result == Double.class);
        result = _tableModel.getColumnClass(1);
        assertTrue(result == String.class);
    }

    /**
     * Test the <code>getColumnCount()</code> method.
     */
    @Test
    public void getColumnCount()
    {
        assertThat(_tableModel.getColumnCount(), is(5));
    }

    /**
     * Test the <code>getColumnName()</code> method.
     */
    @Test
    public void getColumnName()
    {
        String result = _tableModel.getColumnName(0);
        assertThat(result, is("Score"));
        result = _tableModel.getColumnName(1);
        assertThat(result, is("Candidate"));
        result = _tableModel.getColumnName(2);
        assertThat(result, is("a"));
    }

    /**
     * Test the <code>getRowCount()</code> method.
     */
    @Test
    public void getRowCount()
    {
        assertThat(_tableModel.getRowCount(), is(3));
    }

    /**
     * Test the <code>getValueAt()</code> method.
     */
    @Test
    public void getValueAt()
    {
        final Object result = _tableModel.getValueAt(0, 2);
        assertThat(result, instanceOf(Double.class));
        assertThat((Double)result, is(1.1));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        _tableModel = new CandidateTableModel(rivalryData);
    }
}
