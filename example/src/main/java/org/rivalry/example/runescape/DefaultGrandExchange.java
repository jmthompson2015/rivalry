//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a default implementation of a grand exchange.
 */
public class DefaultGrandExchange extends AbstractGrandExchange
{
    /** Map of item to value. */
    private final Map<Item, Integer> _itemToValue = new HashMap<Item, Integer>();

    @Override
    public Integer getValue(final Item item)
    {
        return _itemToValue.get(item);
    }

    /**
     * @param item Item.
     * @param value Value.
     */
    public void putValue(final Item item, final Integer value)
    {
        _itemToValue.put(item, value);
    }
}
