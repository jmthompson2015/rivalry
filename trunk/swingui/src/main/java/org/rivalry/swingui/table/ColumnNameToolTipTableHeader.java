package org.rivalry.swingui.table;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.table.TableColumnModel;

/**
 * Provides a JTableHeader which displays the column name as a tool tip.
 */
public final class ColumnNameToolTipTableHeader extends ColumnSizeTableHeader
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object with the given parameter.
     * 
     * @param tableColumnModel Table column model.
     */
    public ColumnNameToolTipTableHeader(final TableColumnModel tableColumnModel)
    {
        super(tableColumnModel);
    }

    @Override
    public String getToolTipText(final MouseEvent event)
    {
        final Point point = event.getPoint();
        final int index = columnModel.getColumnIndexAtX(point.x);
        final int realIndex = columnModel.getColumn(index).getModelIndex();

        return getTable().getColumnName(realIndex);
    }
}
