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
import javax.swing.table.TableModel;

import org.junit.Ignore;
import org.junit.Test;
import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;
import org.rivalry.swingui.table.CandidateTableModel;
import org.rivalry.swingui.table.CriterionTableModel;
import org.rivalry.swingui.table.DefaultTableUserPreferences;
import org.rivalry.swingui.table.TableUserPreferences;

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
    /** Preference prefix. */
    private static final String PREF_PREFIX = "test";

    /** User preferences. */
    private static final TableUserPreferences USER_PREFS = new DefaultTableUserPreferences(
            PREF_PREFIX);

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
        final String preferencePrefix = "test";
        final FitnessFunction fitnessFunction = testData
                .createWeightedSumFitnessFunction(preferencePrefix,
                        rivalryData.getCriteria());
        final TableModel tableModel = new CandidateTableModel(rivalryData,
                fitnessFunction);
        final SortTablePanel panel = new SortTablePanel(tableModel, null,
                USER_PREFS);

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
        final SortTablePanel panel = new SortTablePanel(tableModel, null,
                USER_PREFS);

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
        final SortTablePanel panel = new SortTablePanel(new MyTestTableModel(),
                null, USER_PREFS);

        showFrame("TestSortTablePanel", panel);
    }

    /**
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
