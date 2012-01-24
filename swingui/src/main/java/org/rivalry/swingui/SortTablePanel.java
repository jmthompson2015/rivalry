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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.rivalry.swingui.table.ColumnNameToolTipTable;
import org.rivalry.swingui.table.TableUserPreferences;

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
    final TableUserPreferences _userPreferences;

    /** Create date widget. */
    private final JLabel _createDateUI;

    /** Row count widget. */
    private final JLabel _rowCountUI;

    /** Table. */
    private final JTable _table;

    /**
     * Construct this object with the given parameter.
     * 
     * @param tableModel Table model.
     * @param createDate Create date.
     * @param tableUserPreferences User preferences.
     */
    public SortTablePanel(final TableModel tableModel, final Date createDate,
            final TableUserPreferences tableUserPreferences)
    {
        _userPreferences = tableUserPreferences;

        _table = createTable(tableModel);
        _rowCountUI = createRowCountUI(tableModel);
        _createDateUI = createCreateDateUI(createDate);

        setLayout(new BorderLayout());

        add(createCenterPanel(_table), BorderLayout.CENTER);
        add(createSouthPanel(tableModel), BorderLayout.SOUTH);

        final List<SortKey> sortKeys = createTableSortKeys();

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
     * @return the table row sorter.
     */
    public RowSorter<? extends TableModel> getTableRowSorter()
    {
        return getTable().getRowSorter();
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
     * @param createDate Create date.
     * 
     * @return a new create date widget.
     */
    private JLabel createCreateDateUI(final Date createDate)
    {
        JLabel answer = null;

        if (createDate != null)
        {
            final Format formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            final String createDateString = "Data collected: "
                    + formatter.format(createDate);

            answer = new JLabel(createDateString);
        }

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

        if (_createDateUI != null)
        {
            answer.add(_createDateUI, BorderLayout.EAST);
        }

        return answer;
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new table.
     */
    private JTable createTable(final TableModel tableModel)
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
                    _userPreferences.putSortKey(sortKeys.get(0));
                }
            }
        });

        return answer;
    }

    /**
     * @return table sort keys.
     */
    private List<SortKey> createTableSortKeys()
    {
        List<SortKey> answer = null;

        final SortKey sortKey = _userPreferences.getSortKey();

        if (sortKey != null)
        {
            answer = new ArrayList<SortKey>();
            answer.add(sortKey);
        }

        return answer;
    }
}
