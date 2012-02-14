package org.rivalry.swingui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import org.junit.Test;

/**
 * Provides tests for the <code>LookAndFeel</code> class.
 */
public class MyTestLookAndFeel
{

    /**
     * Test the user interface.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    @Test
    public void testCandidateUI() throws InterruptedException
    {
        final JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        final ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton metalRadioButton = null;

        for (final LookAndFeel lookAndFeel : LookAndFeel
                .getAvailableLookAndFeels())
        {
            final JRadioButton radioButton = new JRadioButton(
                    lookAndFeel.getName());
            radioButton.putClientProperty("laf", lookAndFeel);
            radioButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(final ActionEvent event)
                {
                    final JRadioButton source = (JRadioButton)event.getSource();
                    System.out.println(source.getText() + " clicked.");
                    final LookAndFeel laf = (LookAndFeel)source
                            .getClientProperty("laf");
                    laf.apply();
                }
            });

            if ("Metal".equals(lookAndFeel.getName()))
            {
                metalRadioButton = radioButton;
            }

            buttonGroup.add(radioButton);
            buttonPanel.add(radioButton);
        }

        final JPanel panel = new JPanel();
        panel.add(buttonPanel);

        if (metalRadioButton != null)
        {
            metalRadioButton.setSelected(true);
        }

        showFrame("Look And Feel", panel);
    }

    /**
     * @param title Frame title.
     * @param panel Panel.
     * 
     * @throws InterruptedException if there is an interruption.
     */
    private void showFrame(final String title, final JPanel panel)
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
