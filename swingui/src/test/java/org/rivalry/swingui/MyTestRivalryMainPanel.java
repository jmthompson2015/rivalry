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

import org.junit.Test;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>RivalryMainPanel</code> class.
 */
public class MyTestRivalryMainPanel
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
        final UIUserPreferences uiUserPreferences = new DefaultUIUserPreferences();
        final RivalryMainPanel panel = new RivalryMainPanel(rivalryData, uiUserPreferences);

        showFrame("RivalryMainPanel Test", panel);
    }

    /**
     * 
     * @param title Frame title.
     * @param panel Panel.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    private void showFrame(final String title, final RivalryMainPanel panel) throws InterruptedException
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
