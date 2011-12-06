//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * Provides a popup menu listener.
 * 
 * @see <a
 *      href="http://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#popup">Bringing
 *      Up a Popup Menu</a>
 */
public class PopupListener extends MouseAdapter
{
    /** Popup menu. */
    private final JPopupMenu _popupMenu;

    /**
     * Construct this object with the given parameter.
     * 
     * @param popupMenu Popup menu.
     */
    public PopupListener(final JPopupMenu popupMenu)
    {
        _popupMenu = popupMenu;
    }

    @Override
    public void mousePressed(final MouseEvent event)
    {
        maybeShowPopup(event);
    }

    @Override
    public void mouseReleased(final MouseEvent event)
    {
        maybeShowPopup(event);
    }

    /**
     * @param event Mouse event.
     */
    private void maybeShowPopup(final MouseEvent event)
    {
        if (event.isPopupTrigger())
        {
            _popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }
    }
}
