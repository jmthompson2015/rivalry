//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui;

import javax.swing.table.AbstractTableModel;

/**
 * Provides a test table model.
 */
public class MyTestTableModel extends AbstractTableModel
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Column names. */
    private String[] columnNames = { "First Name", "Last Name", "Sport",
            "# of Years", "Vegetarian" };

    /** Data. */
    private Object[][] data = {
            { "Kathy", "Smith", "Snowboarding", new Integer(5),
                    new Boolean(false) },
            { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
            { "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
            { "Jane", "White", "Speed reading", new Integer(20),
                    new Boolean(true) },
            { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

    /**
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     */
    @Override
    public Class<?> getColumnClass(final int c)
    {
        return getValueAt(0, c).getClass();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public String getColumnName(final int col)
    {
        return columnNames[col];
    }

    @Override
    public int getRowCount()
    {
        return data.length;
    }

    @Override
    public Object getValueAt(final int row, final int col)
    {
        return data[row][col];
    }

    /**
     * Don't need to implement this method unless your table's editable.
     */
    @Override
    public boolean isCellEditable(final int row, final int col)
    {
        // Note that the data/cell address is constant,
        // no matter where the cell appears onscreen.
        return col > 1;
    }

    /**
     * Don't need to implement this method unless your table's data can change.
     */
    @Override
    public void setValueAt(final Object value, final int row, final int col)
    {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
