//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

/**
 * Provides an adapter for <code>SelectorType</code>.
 */
public class SelectorTypeAdapter
{
    /**
     * @param selectorType Selector type.
     * 
     * @return a string representation of the given parameter.
     */
    public static String marshal(final SelectorType selectorType)
    {
        return selectorType.name();
    }

    /**
     * @param value Value.
     * 
     * @return selector type.
     */
    public static SelectorType unmarshal(final String value)
    {
        return SelectorType.valueOf(value);
    }
}
