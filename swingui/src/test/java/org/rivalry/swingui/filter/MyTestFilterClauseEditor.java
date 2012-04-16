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
public class MyTestFilterClauseEditor
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
        final FilterClause filterClause = null;
        final FilterClauseEditor editor = new FilterClauseEditor(rivalryData.getCriteria(), filterClause);

        final JPanel panel = new JPanel();
        panel.add(editor);

        final int response = JOptionPane.showConfirmDialog(null, panel, "Edit Filter Clause",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        System.out.println("response == OK_OPTION ? " + (response == JOptionPane.OK_OPTION));
        System.out.println("filterClause: [" + editor.getFilterClause() + "]");
    }
}
