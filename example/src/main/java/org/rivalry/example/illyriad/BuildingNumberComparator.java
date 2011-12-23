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
 * Provides a comparator for building number.
 */
public class BuildingNumberComparator implements Comparator<Building>
{
    @Override
    public int compare(final Building building0, final Building building1)
    {
        final Long number0 = building0.getBuildingNumber();
        final Long number1 = building1.getBuildingNumber();

        return number0.compareTo(number1);
    }
}
