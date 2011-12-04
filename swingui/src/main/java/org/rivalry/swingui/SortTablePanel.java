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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Provides a sort table panel.
 * <ul>
 * <li>TODO Add column header text as tool tip</li>
 * <li>TODO Add decimal number format for weight and score columns</li>
 * <li>TODO Alternate table row colors</li>
 * <li>TODO Center column header text?</li>
 * <li>TODO Optionally control visible columns</li>
 * </ul>
 */
public class SortTablePanel extends JPanel
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Table. */
    private final JTable _table;

    /** Table row sorter. */
    private final TableRowSorter<TableModel> _sorter;

    /** Table model. */
    private final TableModel _tableModel;

    /** Row count widget. */
    private final JLabel _rowCountUI;

    /**
     * Construct this object with the given parameter.
     * 
     * @param tableModel Table model.
     */
    public SortTablePanel(final TableModel tableModel)
    {
        _tableModel = tableModel;

        _sorter = createTableRowSorter(_tableModel);
        _table = createTable(_tableModel);
        _rowCountUI = createRowCountUI(_tableModel);

        setLayout(new BorderLayout());

        add(createCenterPanel(_tableModel), BorderLayout.CENTER);
        add(createSouthPanel(_tableModel), BorderLayout.SOUTH);
    }

    /**
     * @return the table
     */
    public JTable getTable()
    {
        return _table;
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new main panel.
     */
    private JScrollPane createCenterPanel(final TableModel tableModel)
    {
        final JScrollPane answer = new JScrollPane(_table);

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
     * 
     * @return a new table.
     */
    private JTable createTable(final TableModel tableModel)
    {
        final JTable answer = new JTable(tableModel);

        answer.setRowSorter(_sorter);
        answer.setGridColor(Color.GRAY);

        return answer;
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new table row sorter.
     */
    private TableRowSorter<TableModel> createTableRowSorter(
            final TableModel tableModel)
    {
        final TableRowSorter<TableModel> answer = new TableRowSorter<TableModel>(
                tableModel);

        return answer;
    }
}
