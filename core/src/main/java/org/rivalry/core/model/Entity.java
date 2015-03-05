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

/**
 * Defines methods required by an entity for a decision making application.
 */
public interface Entity
{
    /**
     * Add a <code>PropertyChangeListener</code> to the listener list. The listener is registered for all properties.
     * This method will only add the listener if it is not already registered.
     *
     * @param listener The <code>PropertyChangeListener</code> to be added.
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Add a <code>PropertyChangeListener</code> for a specific property. The listener will be invoked only when a call
     * on <code>
     * firePropertyChange</code> names that specific property.
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The <code>PropertyChangeListener</code> to be added.
     */
    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    /**
     * @return description
     */
    String getDescription();

    /**
     * @return name
     */
    String getName();

    /**
     * Remove a <code>PropertyChangeListener</code> from the listener list. This removes a
     * <code>PropertyChangeListener</code> that was registered for all properties.
     *
     * @param listener The <code>PropertyChangeListener</code> to be removed.
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove a <code>PropertyChangeListener</code> for a specific property.
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The <code>PropertyChangeListener</code> to be removed.
     */
    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

    /**
     * @param descriptionIn description
     */
    void setDescription(String descriptionIn);

    /**
     * @param nameIn name
     */
    void setName(String nameIn);
}
