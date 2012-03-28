//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.swingui.util;

import java.awt.event.ActionEvent;

/**
 * Defines methods required by Mac OS X apps, used by <code>OSXAdapter</code>.
 */
public interface OSXApp
{
    /**
     * Display the about box. OSXAdapter calls this method when "About XXX" is
     * selected from the application menu.
     * 
     * @param event Event.
     */
    void aboutActionPerformed(ActionEvent event);

    /**
     * Display the preferences dialog. The OSXAdapter calls this method when
     * "Preferences..." is selected from the application menu.
     * 
     * @param event Event.
     */
    void preferencesActionPerformed(ActionEvent event);

    /**
     * Handle the quit action. The OSXAdapter calls this method when "Quit XXX"
     * is selected from the application menu, Cmd-Q is pressed, or "Quit" is
     * selected from the Dock.
     * 
     * @param event Event.
     */
    void quitActionPerformed(ActionEvent event);
}
