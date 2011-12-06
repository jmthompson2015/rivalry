//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.swing.table.TableModel;

import org.junit.Before;
import org.junit.Test;
import org.rivalry.swingui.MyTestTableModel;

/**
 * Provides tests for the <code>VisibleColumnsTableModel</code> class.
 */
public class VisibleColumnsTableModelTest
{
    /** Table model. */
    private VisibleColumnsTableModel _tableModel;

    /**
     * Test the <code>getColumnClass()</code> method.
     */
    @Test
    public void getColumnClass()
    {
        assertTrue(_tableModel.getColumnClass(0) == String.class);
        assertTrue(_tableModel.getColumnClass(1) == String.class);
        assertTrue(_tableModel.getColumnClass(2) == String.class);
        assertTrue(_tableModel.getColumnClass(3).getName(),
                _tableModel.getColumnClass(3) == Integer.class);
        assertTrue(_tableModel.getColumnClass(4).getName(),
                _tableModel.getColumnClass(4) == Boolean.class);

        _tableModel.setColumnVisible(2, false);

        assertTrue(_tableModel.getColumnClass(0) == String.class);
        assertTrue(_tableModel.getColumnClass(1) == String.class);
        assertTrue(_tableModel.getColumnClass(2) == Integer.class);
        assertTrue(_tableModel.getColumnClass(3) == Boolean.class);

        // Out of range
        try
        {
            assertTrue(_tableModel.getColumnClass(4) == Boolean.class);
            fail("Should have thrown an exception");
        }
        catch (final ArrayIndexOutOfBoundsException e)
        {
            assertThat(e.getMessage(), is("-1"));
        }
    }

    /**
     * Test the <code>getColumnCount()</code> method.
     */
    @Test
    public void getColumnCount()
    {
        assertThat(_tableModel.getColumnCount(), is(5));

        _tableModel.setColumnVisible(2, false);

        assertThat(_tableModel.getColumnCount(), is(4));
    }

    /**
     * Test the <code>getColumnName()</code> method.
     */
    @Test
    public void getColumnName()
    {
        assertThat(_tableModel.getColumnName(0), is("First Name"));
        assertThat(_tableModel.getColumnName(1), is("Last Name"));
        assertThat(_tableModel.getColumnName(2), is("Sport"));
        assertThat(_tableModel.getColumnName(3), is("# of Years"));
        assertThat(_tableModel.getColumnName(4), is("Vegetarian"));

        _tableModel.setColumnVisible(2, false);

        assertThat(_tableModel.getColumnName(0), is("First Name"));
        assertThat(_tableModel.getColumnName(1), is("Last Name"));
        assertThat(_tableModel.getColumnName(2), is("# of Years"));
        assertThat(_tableModel.getColumnName(3), is("Vegetarian"));

        // Out of range
        try
        {
            assertThat(_tableModel.getColumnName(4), is("Vegetarian"));
            fail("Should have thrown an exception");
        }
        catch (final ArrayIndexOutOfBoundsException e)
        {
            assertThat(e.getMessage(), is("-1"));
        }
    }

    /**
     * Test the <code>getValueAt()</code> method.
     */
    @Test
    public void getValueAt()
    {
        assertThat((String)_tableModel.getValueAt(0, 0), is("Kathy"));
        assertThat((String)_tableModel.getValueAt(0, 1), is("Smith"));
        assertThat((String)_tableModel.getValueAt(0, 2), is("Snowboarding"));
        assertThat((Integer)_tableModel.getValueAt(0, 3), is(5));
        assertThat((Boolean)_tableModel.getValueAt(0, 4), is(false));

        assertThat((String)_tableModel.getValueAt(4, 0), is("Joe"));
        assertThat((String)_tableModel.getValueAt(4, 1), is("Brown"));
        assertThat((String)_tableModel.getValueAt(4, 2), is("Pool"));
        assertThat((Integer)_tableModel.getValueAt(4, 3), is(10));
        assertThat((Boolean)_tableModel.getValueAt(4, 4), is(false));

        _tableModel.setColumnVisible(2, false);

        assertThat((String)_tableModel.getValueAt(0, 0), is("Kathy"));
        assertThat((String)_tableModel.getValueAt(0, 1), is("Smith"));
        assertThat((Integer)_tableModel.getValueAt(0, 2), is(5));
        assertThat((Boolean)_tableModel.getValueAt(0, 3), is(false));

        assertThat((String)_tableModel.getValueAt(4, 0), is("Joe"));
        assertThat((String)_tableModel.getValueAt(4, 1), is("Brown"));
        assertThat((Integer)_tableModel.getValueAt(4, 2), is(10));
        assertThat((Boolean)_tableModel.getValueAt(4, 3), is(false));
    }

    /**
     * Test the <code>isCellEditable()</code> method.
     */
    @Test
    public void isCellEditable()
    {
        assertThat(_tableModel.isCellEditable(0, 0), is(false));
        assertThat(_tableModel.isCellEditable(0, 1), is(false));
        assertThat(_tableModel.isCellEditable(0, 2), is(true));
        assertThat(_tableModel.isCellEditable(0, 3), is(true));
        assertThat(_tableModel.isCellEditable(0, 4), is(true));

        _tableModel.setColumnVisible(0, false);

        assertThat(_tableModel.isCellEditable(0, 0), is(false));
        assertThat(_tableModel.isCellEditable(0, 1), is(true));
        assertThat(_tableModel.isCellEditable(0, 2), is(true));
        assertThat(_tableModel.isCellEditable(0, 3), is(true));

        // Out of range
        assertThat(_tableModel.isCellEditable(0, 4), is(false));
    }

    /**
     * Test the <code>setColumnVisible()</code> method.
     */
    @Test
    public void setColumnVisible()
    {
        assertThat(_tableModel.getColumnCount(), is(5));
        _tableModel.setColumnVisible(0, false);
        assertThat(_tableModel.getColumnCount(), is(4));
        _tableModel.setColumnVisible(4, false);
        assertThat(_tableModel.getColumnCount(), is(3));
        _tableModel.setColumnVisible(0, true);
        assertThat(_tableModel.getColumnCount(), is(4));
        _tableModel.setColumnVisible(4, true);
        assertThat(_tableModel.getColumnCount(), is(5));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        final TableModel dataModel = new MyTestTableModel();
        _tableModel = new VisibleColumnsTableModel(dataModel);
    }

    /**
     * Test the <code>setValueAt()</code> method.
     */
    @Test
    public void setValueAt()
    {
        assertThat((String)_tableModel.getValueAt(0, 2), is("Snowboarding"));
        _tableModel.setValueAt("foo", 0, 2);
        assertThat((String)_tableModel.getValueAt(0, 2), is("foo"));

        assertThat((String)_tableModel.getValueAt(4, 2), is("Pool"));
        _tableModel.setColumnVisible(1, false);
        _tableModel.setValueAt("bar", 4, 1);
        assertThat((String)_tableModel.getValueAt(4, 1), is("bar"));
    }
}
