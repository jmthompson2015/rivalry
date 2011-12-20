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
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Provides an enumeration of buildings.
 */
public enum Building
{
    /** Lumberjacks: buildings[1][2] */
    /** Clay Pits: buildings[1][3] */
    /** Iron Mines: buildings[1][4] */
    /** Quarries: buildings[1][5] */
    /** Farmyards: buildings[1][6] */

    /** City wall. */
    CITY_WALL("City Wall", 10, -1L),
    /** Storehouse. buildings[0][1] */
    STOREHOUSE("Storehouse", 0, 1L),
    /** Library. buildings[0][7] */
    LIBRARY("Library", 1, 7L),
    /** Barracks. buildings[0][8] */
    BARRACKS("Barracks", 3, 8L),
    /** Marketplace. buildings[0][9] */
    MARKETPLACE("Marketplace", 6, 9L),
    /** Brewery. buildings[0][10] */
    BREWERY("Brewery", 32, 10L),
    /** Tavern. buildings[0][11] */
    TAVERN("Tavern", 39, 11L),
    /** Consulate. buildings[0][12] */
    CONSULATE("Consulate", 4, 12L),
    /** Carpentry. buildings[0][13] */
    CARPENTRY("Carpentry", 20, 13L),
    /** Kiln. buildings[0][14] */
    KILN("Kiln", 21, 14L),
    /** Foundry. buildings[0][15] */
    FOUNDRY("Foundry", 22, 15L),
    /** Stonemason. buildings[0][16] */
    STONEMASON("Stonemason", 23, 16L),
    /** Flourmill. buildings[0][17] */
    FLOURMILL("Flourmill", 24, 17L),
    /** Common ground. buildings[0][19] */
    COMMON_GROUND("Common Ground", 11, 19L),
    /** Paddock. buildings[0][21] */
    PADDOCK("Paddock", 12, 21L),
    /** Vault. buildings[0][24] */
    VAULT("Vault", 90, 24L),
    /** Mage tower. buildings[0][26] */
    MAGE_TOWER("Mage Tower", 5, 26L),
    /** Fletcher. buildings[0][28] */
    FLETCHER("Fletcher", 33, 28L),
    /** Spearmaker. buildings[0][31] */
    SPEARMAKER("Spearmaker", 37, 31L),
    /** Blacksmith. buildings[0][32] */
    BLACKSMITH("Blacksmith", 30, 32L),
    /** Forge. buildings[0][33] */
    FORGE("Forge", 34, 33L),
    /** Tannery. buildings[0][34] */
    TANNERY("Tannery", 38, 34L),
    /** Saddlemaker. buildings[0][35] */
    SADDLEMAKER("Saddlemaker", 35, 35L),
    /** Siege workshop. buildings[0][36] */
    SIEGE_WORKSHOP("Siege Workshop", 36, 36L),
    /** Book binder. buildings[0][37] */
    BOOK_BINDER("Book Binder", 31, 37L),
    /** Warehouse. buildings[0][39] */
    WAREHOUSE("Warehouse", 7, 39L),
    /** Runemasters' Grounding. buildings[0][42] */
    RUNEMASTERS_GROUNDING("Runemasters' Grounding", 70, 42L),
    /** Geomancers' retreat. buildings[0][43] */
    GEOMANCERS_RETREAT("Geomancers' Retreat", 71, 43L),
    /** Unknown. */
    UNKNOWN_44("Unknown 44", 72, 44L),
    /** Scouts' lookout. */
    SCOUTS_LOOKOUT("Scouts' Lookout", 73, 45L),
    /** Spies's hideout. */
    SPIES_HIDEOUT("Spies' Hideout", 74, 46L),
    /** Thieves' den. */
    THIEVES_DEN("Thieves' Den", 75, 47L),
    /** Unknown. */
    UNKNOWN_48("Unknown 48", 76, 48L),
    /** Unknown. */
    UNKNOWN_49("Unknown 49", 77, 49L),
    /** Spearmens' billets. buildings[0][50] */
    SPEARMENS_BILLETS("Spearmens' Billets", 78, 50L),
    /** Archers' field. */
    ARCHERS_FIELD("Archers' Field", 79, 51L),
    /** Infantry quarters. buildings[0][52] */
    INFANTRY_QUARTERS("Infantry Quarters", 80, 52L),
    /** Unknown. */
    UNKNOWN_53("Unknown 53", 81, 53L),
    /** Unknown. */
    UNKNOWN_54("Unknown 54", 82, 54L),
    /** Architects' office. buildings[0][55] */
    ARCHITECTS_OFFICE("Architects' Office", 2, 55L),
    /** Foreign office. */
    FOREIGN_OFFICE("Foreign Office", 83, 56L);

    /**
     * @return a new sorted list of display names.
     */
    public static List<String> getSortedDisplayNames()
    {
        final List<String> answer = new ArrayList<String>();

        for (final Building building : values())
        {
            answer.add(building.getDisplayName());
        }

        Collections.sort(answer);

        return answer;
    }

    /**
     * @param buildingNumber Building number.
     * 
     * @return building.
     */
    public static Building valueOfBuildingNumber(final Long buildingNumber)
    {
        Building answer = null;

        if (buildingNumber != null)
        {
            final Building[] buildings = values();
            final int size = buildings.length;

            for (int i = 0; answer == null && i < size; i++)
            {
                final Building building = buildings[i];

                if (buildingNumber.equals(building.getBuildingNumber()))
                {
                    answer = building;
                }
            }
        }

        return answer;
    }

    /**
     * @param displayName Display name.
     * 
     * @return building.
     */
    public static Building valueOfDisplayName(final String displayName)
    {
        Building answer = null;

        if (StringUtils.isNotEmpty(displayName))
        {
            final Building[] buildings = values();
            final int size = buildings.length;

            for (int i = 0; answer == null && i < size; i++)
            {
                final Building building = buildings[i];

                if (displayName.equals(building.getDisplayName()))
                {
                    answer = building;
                }
            }
        }

        return answer;
    }

    /** Display name. */
    private final String _displayName;

    /** Display order. */
    private final Integer _displayOrder;

    /** Building number. */
    private final Long _buildingNumber;

    /**
     * Construct this object with the given parameter.
     * 
     * @param displayName Display name.
     * @param displayOrder Display order.
     * @param buildingNumber Building number.
     */
    private Building(final String displayName, final Integer displayOrder,
            final Long buildingNumber)
    {
        _displayName = displayName;
        _buildingNumber = buildingNumber;
        _displayOrder = displayOrder;
    }

    /**
     * @return the buildingNumber
     */
    public Long getBuildingNumber()
    {
        return _buildingNumber;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName()
    {
        return _displayName;
    }

    /**
     * @return the displayOrder
     */
    public Integer getDisplayOrder()
    {
        Integer answer = _displayOrder;

        if (_displayOrder == null)
        {
            answer = ordinal();
        }

        return answer;
    }
}
