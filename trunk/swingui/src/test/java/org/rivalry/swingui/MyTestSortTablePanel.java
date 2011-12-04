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

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.junit.Ignore;
import org.junit.Test;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;
import org.rivalry.swingui.table.CandidateTableModel;
import org.rivalry.swingui.table.CriterionTableModel;

/**
 * Provides tests for the <code>SortTablePanel</code> class.
 * <p>
 * The first column (columnIndex 0) is the score, the second column (columnIndex
 * 1) is the candidate name, and the rest of the columns correspond to the
 * visible criteria.
 * </p>
 */
public class MyTestSortTablePanel
{
    class MyTableModel extends AbstractTableModel
    {
        private String[] columnNames = { "First Name", "Last Name", "Sport",
                "# of Years", "Vegetarian" };
        private Object[][] data = {
                { "Kathy", "Smith", "Snowboarding", new Integer(5),
                        new Boolean(false) },
                { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
                { "Sue", "Black", "Knitting", new Integer(2),
                        new Boolean(false) },
                { "Jane", "White", "Speed reading", new Integer(20),
                        new Boolean(true) },
                { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

        /*
         * JTable uses this method to determine the default renderer/ editor for
         * each cell. If we didn't implement this method, then the last column
         * would contain text ("true"/"false"), rather than a check box.
         */
        @Override
        public Class getColumnClass(final int c)
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

        /*
         * Don't need to implement this method unless your table's editable.
         */
        @Override
        public boolean isCellEditable(final int row, final int col)
        {
            // Note that the data/cell address is constant,
            // no matter where the cell appears onscreen.
            if (col < 2)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's data can
         * change.
         */
        @Override
        public void setValueAt(final Object value, final int row, final int col)
        {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        private void printDebugData()
        {
            final int numRows = getRowCount();
            final int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++)
            {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++)
                {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Test the user interface.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    @Test
    public void testCandidateUI() throws InterruptedException
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final TableModel tableModel = new CandidateTableModel(rivalryData);
        final SortTablePanel panel = new SortTablePanel(tableModel);

        showFrame("CandidateSortTablePanel Test", panel);
    }

    /**
     * Test the user interface.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    @Ignore
    @Test
    public void testCriterionUI() throws InterruptedException
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final TableModel tableModel = new CriterionTableModel(rivalryData);
        final SortTablePanel panel = new SortTablePanel(tableModel);

        showFrame("CriterionSortTablePanel Test", panel);
    }

    /**
     * Test the user interface.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    @Ignore
    @Test
    public void testUI0() throws InterruptedException
    {
        final SortTablePanel panel = new SortTablePanel(new MyTableModel());

        // final JFrame frame = new JFrame("TestSortTablePanel");
        // frame.getContentPane().add(panel, BorderLayout.CENTER);
        // frame.setSize(900, 480);
        // frame.setVisible(true);
        //
        // synchronized (this)
        // {
        // wait();
        // }

        showFrame("TestSortTablePanel", panel);
    }

    /**
     * 
     * @param title Frame title.
     * @param panel Panel.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    private void showFrame(final String title, final SortTablePanel panel)
            throws InterruptedException
    {
        final JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(900, 480);
        frame.setVisible(true);

        synchronized (this)
        {
            wait();
        }
    }
}
