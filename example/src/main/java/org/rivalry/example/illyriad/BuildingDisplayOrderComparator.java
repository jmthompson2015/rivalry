//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import java.util.Comparator;

/**
 * Provides a comparator for building display order.
 */
public class BuildingDisplayOrderComparator implements Comparator<Building>
{
    @Override
    public int compare(final Building building0, final Building building1)
    {
        final Integer order0 = building0.getDisplayOrder();
        final Integer order1 = building1.getDisplayOrder();

        return order0.compareTo(order1);
    }
}
