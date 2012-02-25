//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 * Provides a custom JTable which displays the column name as a tool tip, and
 * enables the user to double-click a column separator to auto-size the column.
 */
public class ColumnNameToolTipTable extends JTable
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object with the given parameter.
     * 
     * @param tableModel Table model.
     */
    public ColumnNameToolTipTable(final TableModel tableModel)
    {
        super(tableModel);

        setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    /**
     * Implement table header tool tips.
     * 
     * @return a new table header.
     */
    @Override
    protected JTableHeader createDefaultTableHeader()
    {
        return new ColumnNameToolTipTableHeader(columnModel);
    }
}
