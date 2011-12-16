//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableModel;

import org.apache.commons.collections.CollectionUtils;
import org.rivalry.core.util.UserPreferences;
import org.rivalry.swingui.table.ColumnNameToolTipTable;

/**
 * Provides a sort table panel.
 * <ul>
 * <li>TODO Add decimal number format for weight and score columns</li>
 * <li>TODO Alternate table row colors</li>
 * <li>TODO Center column header text?</li>
 * </ul>
 */
public class SortTablePanel extends JPanel
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** User preferences. */
    final UserPreferences _userPreferences = new UserPreferences();

    /** Row count widget. */
    private final JLabel _rowCountUI;

    /** Table. */
    private final JTable _table;

    /**
     * Construct this object with the given parameter.
     * 
     * @param tableModel Table model.
     * @param sortKeys Sort keys. (optional)
     * @param preferencePrefix Preference prefix.
     */
    public SortTablePanel(final TableModel tableModel,
            final List<SortKey> sortKeys, final String preferencePrefix)
    {
        _table = createTable(tableModel, preferencePrefix);
        _rowCountUI = createRowCountUI(tableModel);

        setLayout(new BorderLayout());

        add(createCenterPanel(_table), BorderLayout.CENTER);
        add(createSouthPanel(tableModel), BorderLayout.SOUTH);

        if (CollectionUtils.isNotEmpty(sortKeys))
        {
            _table.getRowSorter().setSortKeys(sortKeys);
        }
    }

    /**
     * @return the table
     */
    public JTable getTable()
    {
        return _table;
    }

    /**
     * @param table Table.
     * 
     * @return a new main panel.
     */
    private JScrollPane createCenterPanel(final JTable table)
    {
        final JScrollPane answer = new JScrollPane(table);

        return answer;
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new row count widget.
     */
    private JLabel createRowCountUI(final TableModel tableModel)
    {
        final String rowCountString = String.valueOf(tableModel.getRowCount())
                + " rows";

        final JLabel answer = new JLabel(rowCountString);

        return answer;
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new bottom panel.
     */
    private JPanel createSouthPanel(final TableModel tableModel)
    {

        final int hgap = 20;
        final int vgap = 0;
        final JPanel answer = new JPanel(new BorderLayout(hgap, vgap));

        final int top = 5;
        final int left = top;
        final int bottom = top;
        final int right = top;
        answer.setBorder(BorderFactory.createEmptyBorder(top, left, bottom,
                right));

        answer.add(_rowCountUI, BorderLayout.WEST);

        return answer;
    }

    /**
     * @param tableModel Table model.
     * @param preferencePrefix Preference prefix.
     * 
     * @return a new table.
     */
    private JTable createTable(final TableModel tableModel,
            final String preferencePrefix)
    {
        final JTable answer = new ColumnNameToolTipTable(tableModel);

        answer.setAutoCreateRowSorter(true);
        answer.setGridColor(Color.GRAY);

        final RowSorter<? extends TableModel> rowSorter = answer.getRowSorter();
        rowSorter.addRowSorterListener(new RowSorterListener()
        {
            @Override
            public void sorterChanged(final RowSorterEvent event)
            {
                final List<? extends SortKey> sortKeys = rowSorter
                        .getSortKeys();

                if (CollectionUtils.isNotEmpty(sortKeys))
                {
                    _userPreferences.putSortKey(preferencePrefix,
                            sortKeys.get(0));
                }
            }
        });

        return answer;
    }
}
