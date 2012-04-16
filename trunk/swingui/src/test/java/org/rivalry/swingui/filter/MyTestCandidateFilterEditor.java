//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.filter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.Test;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>VisibleColumnsPopupMenu</code> class.
 */
public class MyTestCandidateFilterEditor
{
    /**
     * Test the user interface.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    @Test
    public void testUI() throws InterruptedException
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();

        final boolean isNull = false;
        CandidateFilter filter = null;

        if (!isNull)
        {
            filter = new CandidateFilter();
            filter.add(new FilterClause(rivalryData.findCriterionByName("a"), Operator.LT, 1.0));
            filter.add(new FilterClause(rivalryData.findCriterionByName("b"), Operator.LT, 2.0));
            filter.add(new FilterClause(rivalryData.findCriterionByName("c"), Operator.LT, 3.0));
        }

        final CandidateFilterEditor editor = new CandidateFilterEditor(rivalryData.getCriteria(), filter);

        final JPanel panel = new JPanel();
        panel.add(editor);

        final int response = JOptionPane.showConfirmDialog(null, panel, "Edit Candidate Filter",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        System.out.println("response == OK_OPTION ? " + (response == JOptionPane.OK_OPTION));
        System.out.println("candidateFilter: [" + editor.getCandidateFilter() + "]");
    }
}
