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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;
import org.rivalry.core.util.UserPreferences;

/**
 * Provides tests for the <code>CriterionTableModel</code> class.
 */
public class CriterionTableModelTest
{
    /** Preference prefix. */
    private static final String PREF_PREFIX = "test";
    /** Table model. */
    private CriterionTableModel _tableModel;

    /**
     * Test the <code>getColumnClass()</code> method.
     */
    @Test
    public void getColumnClass()
    {
        Class<?> result = _tableModel.getColumnClass(0);
        assertTrue(result == String.class);
        result = _tableModel.getColumnClass(1);
        assertTrue(result == String.class);
        result = _tableModel.getColumnClass(2);
        assertTrue(result == Integer.class);
    }

    /**
     * Test the <code>getColumnCount()</code> method.
     */
    @Test
    public void getColumnCount()
    {
        assertThat(_tableModel.getColumnCount(), is(3));
    }

    /**
     * Test the <code>getColumnName()</code> method.
     */
    @Test
    public void getColumnName()
    {
        String result = _tableModel.getColumnName(0);
        assertThat(result, is("Category"));
        result = _tableModel.getColumnName(1);
        assertThat(result, is("Criterion"));
        result = _tableModel.getColumnName(2);
        assertThat(result, is("Weight"));
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
        Object result = _tableModel.getValueAt(0, 0);
        assertThat(result, instanceOf(String.class));
        assertThat((String)result, is("A"));

        result = _tableModel.getValueAt(0, 1);
        assertThat(result, instanceOf(String.class));
        assertThat((String)result, is("a"));

        result = _tableModel.getValueAt(0, 2);
        assertThat(result, instanceOf(Integer.class));
        assertThat((Integer)result, is(0));
    }

    /**
     * Test the <code>isCellEditable()</code> method.
     */
    @Test
    public void isCellEditable()
    {
        assertFalse(_tableModel.isCellEditable(0, 0));
        assertFalse(_tableModel.isCellEditable(0, 1));
        assertTrue(_tableModel.isCellEditable(0, 2));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        final UserPreferences userPreferences = new UserPreferences(PREF_PREFIX);
        userPreferences.clearCriterionWeights();

        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData(PREF_PREFIX);
        _tableModel = new CriterionTableModel(rivalryData);
    }

    /**
     * Test the <code>setValueAt()</code> method.
     */
    @Test
    public void setValueAt()
    {
        Object result = _tableModel.getValueAt(0, 2);
        assertThat(result, instanceOf(Integer.class));
        assertThat((Integer)result, is(0));

        _tableModel.setValueAt(1, 0, 0);

        result = _tableModel.getValueAt(0, 2);
        assertThat(result, instanceOf(Integer.class));
        assertThat((Integer)result, is(1));
    }

    /**
     * Test the <code>setValueAt()</code> method.
     */
    @Test
    public void setValueAtNotEditable()
    {
        final String expected = "a";

        Object result = _tableModel.getValueAt(0, 1);
        assertThat(result, instanceOf(String.class));
        assertThat((String)result, is(expected));

        // This call fails (silently) because the cell is not editable.
        _tableModel.setValueAt("foobar", 0, 1);

        result = _tableModel.getValueAt(0, 1);
        assertThat(result, instanceOf(String.class));
        assertThat((String)result, is(expected));
    }
}
