//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * Provides tests for the <code>Building</code> class.
 */
public class BuildingTest
{
    /**
     * Print the buildings in building number order.
     */
    @Test
    public void printBuildingsByNumber()
    {
        final List<Building> list = new ArrayList<Building>();
        for (final Building building : Building.values())
        {
            list.add(building);
        }

        final Comparator<Building> buildingComparator = new BuildingNumberComparator();
        Collections.sort(list, buildingComparator);

        System.out.println("\nBuildings by number:\n");
        for (final Building building : Building.values())
        {
            System.out.println(building.getBuildingNumber() + "\t"
                    + building.getDisplayName());
        }
    }

    /**
     * Print the buildings in display order.
     */
    @Test
    public void printBuildingsByOrder()
    {
        final List<Building> list = new ArrayList<Building>();
        for (final Building building : Building.values())
        {
            list.add(building);
        }
        final Comparator<Building> buildingComparator = new BuildingDisplayOrderComparator();
        Collections.sort(list, buildingComparator);

        System.out.println("\nBuildings by display order:\n");
        for (final Building building : list)
        {
            System.out.println(building.getDisplayOrder() + "\t"
                    + building.getDisplayName());
        }
    }
}
