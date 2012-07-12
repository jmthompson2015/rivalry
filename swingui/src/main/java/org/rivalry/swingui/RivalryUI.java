//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataReader;
import org.rivalry.swingui.util.OSXApp;
import org.rivalry.swingui.util.SystemUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a Rivalry user interface.
 */
public class RivalryUI extends JPanel implements OSXApp
{
    /** Frame. */
    static JFrame _frame;

    /** Base location of data files. */
    private final static String FILE_LOCATION = "http://dl.dropbox.com/u/1267954/rivalry/";

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RivalryUI.class);

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** User interface user preferences. */
    private static UIUserPreferences _uiUserPreferences = new DefaultUIUserPreferences();

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
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Rivalry UI");
        System.out.println("Applying look and feel: " + _uiUserPreferences.getLookAndFeel().getName());
        _uiUserPreferences.getLookAndFeel().apply();

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                final RivalryUI panel = new RivalryUI();

                _frame = new JFrame("Rivalry UI");
                _frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                _frame.getContentPane().add(panel, BorderLayout.CENTER);
                _frame.setSize(1900, 600);
                _frame.setLocationByPlatform(true);
                _frame.setVisible(true);
            }
        });
    }

    /** File chooser. */
    JFileChooser _fileChooser;

    /** Rivalry main panel. */
    RivalryMainPanel _rivalryMainPanel;

    /** Tool bar panel. */
    JPanel _toolBarPanel;

    /**
     * Construct this object.
     */
    public RivalryUI()
    {
        // Set up our application to respond to the Mac OS X application menu
        macOSXRegistration();

        setLayout(new BorderLayout());

        final String preferencePrefix = "empty";
        final RivalryData rivalryData = new RivalryData();
        rivalryData.setPreferencePrefix(preferencePrefix);
        _rivalryMainPanel = createRivalryMainPanel(rivalryData);

        _toolBarPanel = createToolBarPanel();
        _toolBarPanel.add(_rivalryMainPanel, BorderLayout.CENTER);

        add(_toolBarPanel, BorderLayout.CENTER);
    }

    /**
     * Perform the about action.
     */
    @Override
    public void aboutActionPerformed(final ActionEvent event)
    {
        final String description0 = "Rivalry is a comparison tool.";

        final String description1 = "\n\nFor more information or to contribute, please see the open source"
                + "\nproject site at http://code.google.com/p/rivalry/"
                + "\n\nCopyright \u00A9 2011-2012 Rivalry.org. All rights reserved.\n\n";

        final RivalryData rivalryData = _rivalryMainPanel.getRivalryData();
        String description2 = rivalryData.getDescription();
        if (StringUtils.isEmpty(description2))
        {
            description2 = "";
        }

        final String description = description0 + description1 + description2;

        final String title = "About Rivalry";
        final Icon icon = createImageIcon("images/crossedSabres64.png", "Rivalry.org");

        JOptionPane.showMessageDialog(getFrame(), description, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    /**
     * Display the preferences dialog. The OSXAdapter calls this method when "Preferences..." is selected from the
     * application menu.
     * 
     * @param event Event.
     */
    @Override
    public void preferencesActionPerformed(final ActionEvent event)
    {
        // Nothing to do.
    }

    /**
     * Callback for the quit button.
     * 
     * @param event Event.
     */
    @Override
    public void quitActionPerformed(final ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * @param imageLocation Image location.
     * @param altText Alternate text.
     * 
     * @return a new image icon.
     */
    ImageIcon createImageIcon(final String imageLocation, final String altText)
    {
        ImageIcon answer = null;

        final URL imageUrl = getClass().getClassLoader().getResource(imageLocation);

        if (imageUrl != null)
        {
            answer = new ImageIcon(imageUrl, altText);
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return a new Rivalry main panel.
     */
    RivalryMainPanel createRivalryMainPanel(final RivalryData rivalryData)
    {
        return new RivalryMainPanel(rivalryData, _uiUserPreferences);
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
     * @param inputFilename Input filename.
     * 
     * @return rivalry data.
     */
    RivalryData readRivalryData(final String inputFilename)
    {
        RivalryData answer = null;

        Reader reader = null;

        try
        {
            if (inputFilename.startsWith("/"))
            {
                reader = new FileReader(inputFilename);
            }
            else
            {
                final URL fileLocation = new URL(FILE_LOCATION + inputFilename);
                final InputStream inputStream = fileLocation.openStream();
                reader = new InputStreamReader(inputStream);
            }

            final RivalryDataReader rivalryDataReader = new RivalryDataReader();
            answer = rivalryDataReader.read(reader);
        }
        catch (final FileNotFoundException e)
        {
            handleException(e);
        }
        catch (final MalformedURLException e)
        {
            handleException(e);
        }
        catch (final IOException e)
        {
            handleException(e);
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (final IOException e)
                {
                    handleException(e);
                }
            }
        }

        return answer;
    }

    /**
     * @return a new about action listener.
     */
    private ActionListener createAboutActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                aboutActionPerformed(event);
            }
        };
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
     * @return a new boardgame action listener.
     */
    private ActionListener createBoardgameActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final RivalryData rivalryData = readRivalryData("BoardgameRivalryData.xml");
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
    private JButton createButton(final String imageName, final String toolTipText, final String altText,
            final ActionListener actionListener)
    {
        // Look for the image.
        final String imgLocation = "images/" + imageName;
        final URL imageURL = getClass().getClassLoader().getResource(imgLocation);

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
     * @return a new mystery book award action listener.
     */
    private ActionListener createMysteryAwardActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final RivalryData rivalryData = readRivalryData("MysteryAwardRivalryData.xml");
                loadDataActionPerformed(rivalryData);
            }
        };
    }

    /**
     * @return a new open action listener.
     */
    private ActionListener createOpenActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                if (_fileChooser == null)
                {
                    _fileChooser = new JFileChooser();
                }

                final int returnVal = _fileChooser.showOpenDialog(getFrame());

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    final File file = _fileChooser.getSelectedFile();
                    final RivalryData rivalryData = readRivalryData(file.getAbsolutePath());
                    loadDataActionPerformed(rivalryData);
                }
            }
        };
    }

    /**
     * @return a new skill demand action listener.
     */
    private ActionListener createSkillDemandActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final RivalryData rivalryData = readRivalryData("SkillDemandRivalryData.xml");
                loadDataActionPerformed(rivalryData);
            }
        };
    }

    /**
     * @return a new stock action listener.
     */
    private ActionListener createStockActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                final RivalryData rivalryData = readRivalryData("StockRivalryData.xml");
                loadDataActionPerformed(rivalryData);
            }
        };
    }

    /**
     * @return a new tool bar.
     */
    private JToolBar createToolBar()
    {
        final JButton openButton = createButton("Open24.gif", "Open a rivalry data file", "Open...",
                createOpenActionListener());

        final JButton bestPlaceButton = createButton("PalmTree24.png", "Load best place data", "Best Places",
                createBestPlaceActionListener());
        final JButton boardgameButton = createButton("Boardgame24.png", "Load board game data", "Boardgames",
                createBoardgameActionListener());
        final JButton dogButton = createButton("Dog24.png", "Load dog breed data", "Dog Breeds",
                createDogBreedActionListener());
        final JButton mysteryAwardButton = createButton("MysteryBook24.png", "Load mystery book award data",
                "Mystery Book Awards", createMysteryAwardActionListener());
        final JButton skillDemandButton = createButton("Brain24.png", "Load skill demand data", "Skill Demand",
                createSkillDemandActionListener());
        final JButton stockButton = createButton("Money24.png", "Load stock data", "Stocks",
                createStockActionListener());

        final JButton aboutButton = createButton("About24.gif", "View information about this application.", "About",
                createAboutActionListener());

        final JToolBar answer = new JToolBar("Rivalry Tool Bar");

        answer.add(openButton);
        answer.addSeparator();
        answer.add(bestPlaceButton);
        answer.add(boardgameButton);
        answer.add(dogButton);
        answer.add(mysteryAwardButton);
        answer.add(skillDemandButton);
        answer.add(stockButton);
        answer.addSeparator();
        answer.add(aboutButton);

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
        JOptionPane.showMessageDialog(getFrame(), prefixMessage + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Generic registration with the Mac OS X application menu. Checks the platform, then attempts to register with the
     * Apple EAWT. This method calls OSXAdapter.registerMacOSXApplication() and OSXAdapter.enablePrefs(). See
     * OSXAdapter.java for the signatures of these methods.
     */
    private void macOSXRegistration()
    {
        final SystemUtilities systemUtils = new SystemUtilities();

        if (systemUtils.isMacPlatform() && !systemUtils.isApplet())
        {
            try
            {
                final Class<?> osxAdapter = Class.forName("org.rivalry.swingui.util.OSXAdapter");

                final Class<?>[] defArgs = { OSXApp.class };
                final Method registerMethod = osxAdapter.getDeclaredMethod("registerMacOSXApplication", defArgs);

                if (registerMethod != null)
                {
                    final Object[] args = { this };
                    registerMethod.invoke(osxAdapter, args);
                }
            }
            catch (final NoClassDefFoundError e)
            {
                /*
                 * This will be thrown first if the OSXAdapter is loaded on a system without the EAWT because OSXAdapter
                 * extends ApplicationAdapter in its def
                 */
                LOGGER.error(
                        "This version of Mac OS X does not support the Apple EAWT.  Application Menu handling has been disabled.",
                        e);
            }
            catch (final ClassNotFoundException e)
            {
                /*
                 * This shouldn't be reached; if there's a problem with the OSXAdapter we should get the above
                 * NoClassDefFoundError first.
                 */
                LOGGER.error(
                        "This version of Mac OS X does not support the Apple EAWT.  Application Menu handling has been disabled.",
                        e);
            }
            catch (final Exception e)
            {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
