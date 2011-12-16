//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.rivalry.core.util.UserPreferences;

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

    /** User preferences. */
    private final UserPreferences _userPreferences = new UserPreferences();

    /** Preference prefix. */
    private final String _prefPrefix;

    /**
     * Construct this object with the given parameters.
     * 
     * @param dataModel Table model.
     * @param preferencePrefix Preference prefix.
     */
    public VisibleColumnsTableModel(final TableModel dataModel,
            final String preferencePrefix)
    {
        _dataModel = dataModel;
        _prefPrefix = preferencePrefix;

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
     * 
     * @return true if the column is visible.
     */
    public Boolean isColumnVisible(final int absoluteColumnIndex)
    {
        final String columnName = _dataModel.getColumnName(absoluteColumnIndex);

        return isColumnVisible(columnName);
    }

    /**
     * @param columnName Column name.
     * 
     * @return true if the column is visible.
     */
    public Boolean isColumnVisible(final String columnName)
    {
        return _userPreferences.isColumnVisible(_prefPrefix, columnName);
    }

    /**
     * @param absoluteColumnIndex Absolute column index.
     * @param isVisible Flag indicating visibility.
     */
    public void setColumnVisible(final int absoluteColumnIndex,
            final boolean isVisible)
    {
        final String columnName = _dataModel.getColumnName(absoluteColumnIndex);
        _userPreferences.putColumnVisible(_prefPrefix, columnName, isVisible);

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
}
