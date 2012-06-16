//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import java.util.Calendar;

/**
 * Defines methods required by a snapshot of Grand Exchange data at a given date-time.
 */
public interface GrandExchange
{
    /**
     * @param item Item.
     * 
     * @return value over base cost expressed as a percentage.
     */
    Integer computeBasePremium(Item item);

    /**
     * @param item Item.
     * 
     * @return value over cost expressed as a percentage.
     */
    Integer computePremium(Item item);

    /**
     * @return the date-time of this data.
     */
    Calendar getDateTime();

    /**
     * @param item Item.
     * 
     * @return the current value of the given parameter.
     */
    Integer getValue(Item item);
}
