//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Provides a custom JTable which displays the column name as a tool tip.
 */
public class ColumnNameToolTipTable extends JTable
{
    /**
     * Provides a custom JTableHeader which displays the column name as a tool
     * tip.
     */
    private final class MyJTableHeader extends JTableHeader
    {
        /** Serial version UID. */
        private static final long serialVersionUID = 1L;

        /**
         * Construct this object with the given parameter.
         * 
         * @param tableColumnModel Table column model.
         */
        public MyJTableHeader(final TableColumnModel tableColumnModel)
        {
            super(tableColumnModel);
        }

        @Override
        public String getToolTipText(final MouseEvent event)
        {
            final Point point = event.getPoint();
            final int index = columnModel.getColumnIndexAtX(point.x);
            final int realIndex = columnModel.getColumn(index).getModelIndex();
            return getColumnName(realIndex);
        }
    }

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
    }

    /**
     * Implement table header tool tips.
     * 
     * @return a new table header.
     */
    @Override
    protected JTableHeader createDefaultTableHeader()
    {
        return new MyJTableHeader(columnModel);
    }
}
