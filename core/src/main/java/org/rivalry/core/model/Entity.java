//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

/**
 * Defines methods required by an entity for a decision making application.
 */
public interface Entity
{
    /**
     * @return description
     */
    String getDescription();

    /**
     * @return name
     */
    String getName();

    /**
     * @param descriptionIn description
     */
    void setDescription(String descriptionIn);

    /**
     * @param nameIn name
     */
    void setName(String nameIn);
}
