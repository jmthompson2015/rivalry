//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.util.HashMap;
import java.util.Map;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Provides a table model which manages visible columns in a table. It works by
 * channeling a normal table model, and adjusting the column index for
 * visibility.
 */
public class VisibleColumnsTableModel extends DefaultTableModel
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Inner table model. */
    private final TableModel _dataModel;

    /** Map of absolute column index to visibility. */
    private final Map<Integer, Boolean> _isColumnVisible = new HashMap<Integer, Boolean>();

    /**
     * Construct this object.
     * 
     * @param dataModel Table model.
     */
    public VisibleColumnsTableModel(final TableModel dataModel)
    {
        _dataModel = dataModel;

        _dataModel.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(final TableModelEvent event)
            {
                if (event.getType() == TableModelEvent.UPDATE)
                {
                    fireTableDataChanged();
                }
            }
        });
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex)
    {
        final int absoluteColumnIndex = columnIndexToAbsolute(columnIndex);

        return _dataModel.getColumnClass(absoluteColumnIndex);
    }

    @Override
    public int getColumnCount()
    {
        final int size = _dataModel.getColumnCount();
        int answer = size;

        // Subtract one for each hidden column.
        for (int i = 0; i < size; i++)
        {
            if (!isColumnVisible(i))
            {
                answer--;
            }
        }

        return answer;
    }

    @Override
    public String getColumnName(final int columnIndex)
    {
        final int absoluteColumnIndex = columnIndexToAbsolute(columnIndex);

        return _dataModel.getColumnName(absoluteColumnIndex);
    }

    /**
     * @return the dataModel
     */
    public TableModel getDataModel()
    {
        return _dataModel;
    }

    @Override
    public int getRowCount()
    {
        int answer = 0;

        if (_dataModel != null)
        {
            answer = _dataModel.getRowCount();
        }

        return answer;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex)
    {
        Object answer = null;

        final int absoluteColumnIndex = columnIndexToAbsolute(columnIndex);

        if (absoluteColumnIndex >= 0)
        {
            answer = _dataModel.getValueAt(rowIndex, absoluteColumnIndex);
        }

        return answer;
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex)
    {
        final int absoluteColumnIndex = columnIndexToAbsolute(columnIndex);

        return _dataModel.isCellEditable(rowIndex, absoluteColumnIndex);
    }

    /**
     * @param absoluteColumnIndex Absolute column index.
     * @param isVisible Flag indicating visibility.
     */
    public void setColumnVisible(final int absoluteColumnIndex,
            final boolean isVisible)
    {
        _isColumnVisible.put(absoluteColumnIndex, isVisible);

        fireTableStructureChanged();
    }

    @Override
    public void setValueAt(final Object value, final int rowIndex,
            final int columnIndex)
    {
        final int absoluteColumnIndex = columnIndexToAbsolute(columnIndex);

        _dataModel.setValueAt(value, rowIndex, absoluteColumnIndex);
    }

    /**
     * @param columnIndex Column index.
     * 
     * @return the absolute column index.
     */
    private int columnIndexToAbsolute(final int columnIndex)
    {
        int answer = -1;

        // The input refers to a visible column.
        final int size = _dataModel.getColumnCount();
        int count = 0;

        for (int i = 0; answer < 0 && i < size; i++)
        {
            if (isColumnVisible(i))
            {
                if (count == columnIndex)
                {
                    answer = i;
                }

                count++;
            }
        }

        return answer;
    }

    /**
     * @param columnIndex Column index.
     * 
     * @return true if the column is visible.
     */
    private Boolean isColumnVisible(final int columnIndex)
    {
        Boolean answer = _isColumnVisible.get(columnIndex);

        if (answer == null)
        {
            answer = Boolean.TRUE;
            _isColumnVisible.put(columnIndex, answer);
        }

        return answer;
    }
}
