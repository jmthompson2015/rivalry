//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.swingui.util;

import com.apple.eawt.Application;
import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;

/**
 * Provides an adapter for OS X applications.
 */
public class OSXAdapter extends ApplicationAdapter
{
    /**
     * Pseudo-singleton model; no point in making multiple instances of the EAWT
     * application or our adapter
     */
    private static OSXAdapter _theAdapter;

    /** Application. */
    private static Application _theApplication;

    /**
     * Another static entry point for EAWT functionality. Enables the
     * "Preferences..." menu item in the application menu.
     * 
     * @param enabled Flag indicating whether preferences are enabled.
     */
    public static void enablePrefs(final boolean enabled)
    {
        if (_theApplication == null)
        {
            _theApplication = new Application();
        }

        _theApplication.setEnabledPreferencesMenu(enabled);
    }

    /**
     * The main entry-point for this functionality. This is the only method that
     * needs to be called at runtime, and it can easily be done using reflection
     * (see MyApp.java)
     * 
     * @param inApp Application.
     */
    public static void registerMacOSXApplication(final OSXApp inApp)
    {
        if (_theApplication == null)
        {
            _theApplication = new Application();
        }

        if (_theAdapter == null)
        {
            _theAdapter = new OSXAdapter(inApp);
        }

        _theApplication.addApplicationListener(_theAdapter);
    }

    /** Reference to the app where the existing quit, about, prefs code is */
    private OSXApp _mainApp;

    /**
     * Construct this object with the given parameter.
     * 
     * @param inApp Application.
     * 
     * @since v0.1
     */
    private OSXAdapter(final OSXApp inApp)
    {
        _mainApp = inApp;
    }

    /**
     * Implemented handler methods. These are basically hooks into existing
     * functionality from the main app, as if it came over from another
     * platform.
     * 
     * @param event Event.
     */
    @Override
    public void handleAbout(final ApplicationEvent event)
    {
        if (_mainApp != null)
        {
            event.setHandled(true);
            _mainApp.aboutActionPerformed(null);
        }
        else
        {
            throw new IllegalStateException(
                    "handleAbout: MyApp instance detached from listener");
        }
    }

    /**
     * Implemented handler methods. These are basically hooks into existing
     * functionality from the main app, as if it came over from another
     * platform.
     * 
     * @param event Event.
     */
    @Override
    public void handlePreferences(final ApplicationEvent event)
    {
        if (_mainApp != null)
        {
            _mainApp.preferencesActionPerformed(null);
            event.setHandled(true);
        }
        else
        {
            throw new IllegalStateException(
                    "handlePreferences: MyApp instance detached from listener");
        }
    }

    /**
     * Implemented handler methods. These are basically hooks into existing
     * functionality from the main app, as if it came over from another
     * platform.
     * 
     * @param event Event.
     */
    @Override
    public void handleQuit(final ApplicationEvent event)
    {
        if (_mainApp != null)
        {
            /*
             * You MUST setHandled(false) if you want to delay or cancel the
             * quit. This is important for cross-platform development -- have a
             * universal quit routine that chooses whether or not to quit, so
             * the functionality is identical on all platforms. This example
             * simply cancels the AppleEvent-based quit and defers to that
             * universal method.
             */
            event.setHandled(false);
            _mainApp.quitActionPerformed(null);
        }
        else
        {
            throw new IllegalStateException(
                    "handleQuit: MyApp instance detached from listener");
        }
    }
}
