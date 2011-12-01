//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.List;

/**
 */
public interface Selector
{
    /**
     * @return selectors
     */
    List<Selector> getSelectors();

    /**
     * @return type
     */
    SelectorType getType();

    /**
     * @return value
     */
    String getValue();

    /**
     * @param type to set
     */
    void setType(SelectorType type);

    /**
     * @param value to set
     */
    void setValue(String value);

}
