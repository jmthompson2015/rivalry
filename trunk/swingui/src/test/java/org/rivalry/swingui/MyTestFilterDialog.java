//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui;

import javax.swing.JDialog;
import javax.swing.RowFilter;

import org.junit.Test;
import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;
import org.rivalry.swingui.table.CandidateTableModel;

/**
 * Provides tests for the <code>FilterDialog</code> class.
 */
public class MyTestFilterDialog
{
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
        final CandidateTableModel candidateTableModel = new CandidateTableModel(
                rivalryData, fitnessFunction);

        final FilterDialog filterDialog = new FilterDialog(candidateTableModel);
        final JDialog dialog = filterDialog.createDialog("Filter Dialog");
        dialog.setVisible(true);

        final RowFilter<CandidateTableModel, Integer> rowFilter = filterDialog
                .getCandidateTableRowFilter();
        System.out.println("rowFilter = " + rowFilter);
    }
}
