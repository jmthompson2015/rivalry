//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Provides a table header which enables the user to double-click a column
 * separator to auto-size the column.
 */
public class ColumnSizeTableHeader extends JTableHeader
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object with the given parameters.
     * 
     * @param tableColumnModel Column model.
     */
    public ColumnSizeTableHeader(final TableColumnModel tableColumnModel)
    {
        super(tableColumnModel);

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(final MouseEvent event)
            {
                doMouseClicked(event);
            }
        });
    }

    /**
     * @param event Mouse event.
     */
    void doMouseClicked(final MouseEvent event)
    {
        if (getResizingAllowed() && event.getClickCount() == 2)
        {
            final Point point = event.getPoint();
            final int columnIndex = columnAtPoint(event.getPoint());
            final TableColumn column = getResizingColumn(point, columnIndex);

            if (column != null)
            {
                final int oldMinWidth = column.getMinWidth();
                final int oldMaxWidth = column.getMaxWidth();

                final int newWidth = getRequiredColumnWidth(column);
                column.setMinWidth(newWidth);
                column.setMaxWidth(newWidth);
                setResizingColumn(column);
                getTable().doLayout();

                column.setMinWidth(oldMinWidth);
                column.setMaxWidth(oldMaxWidth);
            }
        }
    }

    /**
     * @param column Column.
     * 
     * @return column width.
     */
    private int getRequiredColumnWidth(final TableColumn column)
    {
        int answer = 0;

        final int modelIndex = column.getModelIndex();
        final JTable myTable = getTable();
        final int rows = myTable.getRowCount();

        for (int i = 0; i < rows; i++)
        {
            final TableCellRenderer renderer = myTable.getCellRenderer(i,
                    modelIndex);
            final Object valueAt = myTable.getValueAt(i, modelIndex);
            final Component component = renderer.getTableCellRendererComponent(
                    myTable, valueAt, false, false, i, modelIndex);
            answer = Math.max(answer, component.getPreferredSize().width + 2);
        }

        return answer;
    }

    /**
     * @param point Point.
     * @param column Column index.
     * 
     * @return column.
     */
    private TableColumn getResizingColumn(final Point point, final int column)
    {
        TableColumn answer = null;

        if (column >= 0)
        {
            final Rectangle r = getHeaderRect(column);
            r.grow(-3, 0);

            if (!r.contains(point))
            {
                final int midPoint = r.x + r.width / 2;
                int columnIndex;

                if (getComponentOrientation().isLeftToRight())
                {
                    columnIndex = point.x < midPoint ? column - 1 : column;
                }
                else
                {
                    columnIndex = point.x < midPoint ? column : column - 1;
                }

                if (columnIndex >= 0)
                {
                    answer = getColumnModel().getColumn(columnIndex);
                }
            }
        }

        return answer;
    }
}
