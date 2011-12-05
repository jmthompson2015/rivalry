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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a Rivalry user interface.
 */
public class RivalryUI extends JPanel
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RivalryUI.class);

    /** Frame. */
    private static JFrame _frame;

    /**
     * @return the frame
     */
    public static JFrame getFrame()
    {
        return _frame;
    }

    /**
     * Application method.
     * 
     * @param args Application arguments.
     */
    public static final void main(final String[] args)
    {
        final RivalryUI panel = new RivalryUI();

        _frame = new JFrame("Rivalry");
        _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        _frame.getContentPane().add(panel, BorderLayout.CENTER);
        _frame.setSize(1900, 600);
        _frame.setVisible(true);
    }

    /** Rivalry main panel. */
    RivalryMainPanel _rivalryMainPanel;

    /** Tool bar panel. */
    JPanel _toolBarPanel;

    /**
     * Construct this object.
     */
    public RivalryUI()
    {
        setLayout(new BorderLayout());

        final RivalryData rivalryData = new RivalryData();
        _rivalryMainPanel = createRivalryMainPanel(rivalryData);

        _toolBarPanel = createToolBarPanel();
        _toolBarPanel.add(_rivalryMainPanel, BorderLayout.CENTER);

        add(_toolBarPanel, BorderLayout.CENTER);
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return a new Rivalry main panel.
     */
    RivalryMainPanel createRivalryMainPanel(final RivalryData rivalryData)
    {
        return new RivalryMainPanel(rivalryData);
    }

    /**
     * 
     * @param rivalryData Rivalry data.
     */
    void loadDataActionPerformed(final RivalryData rivalryData)
    {
        _toolBarPanel.remove(_rivalryMainPanel);
        _rivalryMainPanel = createRivalryMainPanel(rivalryData);
        _toolBarPanel.add(_rivalryMainPanel, BorderLayout.CENTER);
        _toolBarPanel.revalidate();
        _toolBarPanel.repaint();
    }

    /**
     * @param inputFile Input file.
     * 
     * @return rivalry data.
     */
    RivalryData readRivalryData(final String inputFile)
    {
        RivalryData answer = null;

        try
        {
            final RivalryDataReader rivalryDataReader = new RivalryDataReader();
            final FileReader reader = new FileReader(inputFile);
            answer = rivalryDataReader.read(reader);
        }
        catch (final FileNotFoundException e)
        {
            handleException(e);
        }

        return answer;
    }

    /**
     * @return a new best place action listener.
     */
    private ActionListener createBestPlaceActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final RivalryData rivalryData = readRivalryData("BestPlaceRivalryData.xml");
                loadDataActionPerformed(rivalryData);
            }
        };
    }

    /**
     * @param imageName Image name.
     * @param toolTipText Tool tip text.
     * @param altText Alternate text.
     * @param actionListener Action listener.
     * 
     * @return a new button.
     */
    private JButton createButton(final String imageName,
            final String toolTipText, final String altText,
            final ActionListener actionListener)
    {
        // Look for the image.
        final String imgLocation = "images/" + imageName + ".png";
        final URL imageURL = getClass().getClassLoader().getResource(
                imgLocation);

        // Create and initialize the button.
        final JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.addActionListener(actionListener);

        if (imageURL != null)
        {
            // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        }
        else
        {
            // no image found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    /**
     * @return a new dog breed action listener.
     */
    private ActionListener createDogBreedActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final RivalryData rivalryData = readRivalryData("DogBreedRivalryData.xml");
                loadDataActionPerformed(rivalryData);
            }
        };
    }

    /**
     * @return a new tool bar.
     */
    private JToolBar createToolBar()
    {
        final JButton dogButton = createButton("Dog24", "Load dog breed data",
                "Dog Breeds", createDogBreedActionListener());
        final JButton houseButton = createButton("House24",
                "Load best place data", "Best Places",
                createBestPlaceActionListener());

        final JToolBar answer = new JToolBar("Rivalry Tool Bar");

        answer.add(dogButton);
        answer.add(houseButton);

        return answer;
    }

    /**
     * @return a new tool bar panel.
     */
    private JPanel createToolBarPanel()
    {
        final JPanel answer = new JPanel(new BorderLayout());

        answer.add(createToolBar(), BorderLayout.PAGE_START);

        return answer;
    }

    /**
     * Handle the given exception.
     * 
     * @param e Exception.
     */
    private void handleException(final Exception e)
    {
        LOGGER.error(e.getMessage(), e);

        final String prefixMessage = "Exception thrown:";
        JOptionPane.showMessageDialog(getFrame(),
                prefixMessage + e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
