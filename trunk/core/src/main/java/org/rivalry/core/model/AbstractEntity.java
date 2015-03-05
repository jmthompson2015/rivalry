//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Provides a base class for an <code>Entity</code>.
 */
public abstract class AbstractEntity implements Entity
{
    /** Property change support. */
    private transient PropertyChangeSupport propertyChangeSupport;

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener listener)
    {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener)
    {
        getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener listener)
    {
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener)
    {
        getPropertyChangeSupport().removePropertyChangeListener(propertyName, listener);
    }

    /**
     * @return propertyChangeSupport
     */
    private PropertyChangeSupport getPropertyChangeSupport()
    {
        if (propertyChangeSupport == null)
        {
            propertyChangeSupport = new PropertyChangeSupport(this);
        }

        return propertyChangeSupport;
    }
}
