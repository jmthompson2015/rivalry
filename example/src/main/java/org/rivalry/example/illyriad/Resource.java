//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import org.apache.commons.lang.StringUtils;

/**
 * Provides an enumeration of resources.
 */
public enum Resource
{
    /** Gold. */
    GOLD("Gold"),
    /** Wood. */
    WOOD("Wood"),
    /** Clay. */
    CLAY("Clay"),
    /** Iron. */
    IRON("Iron"),
    /** Stone. */
    STONE("Stone"),
    /** Food. */
    FOOD("Food"),
    /** Mana. */
    MANA("Mana"),
    /** Research. */
    RESEARCH("Research"),
    /** Horse. */
    HORSE("Horse", true),
    /** Livestock. */
    LIVESTOCK("Livestock", true),
    /** Beer. */
    BEER("Beer", true),
    /** Book. */
    BOOK("Book", true),
    /** Spear. */
    SPEAR("Spear", true),
    /** Sword. */
    SWORD("Sword", true),
    /** Bow. */
    BOW("Bow", true),
    /** Saddle. */
    SADDLE("Saddle", true),
    /** Leather armour. */
    LEATHER_ARMOUR("Leather Armour", true),
    /** Chainmail armour. */
    CHAINMAIL_ARMOUR("Chainmail Armour", true),
    /** Plate armour. */
    PLATE_ARMOUR("Plate Armour", true),
    /** Siege block. */
    SIEGE_BLOCK("Siege Block", true);

    /**
     * @param displayName Display name.
     * 
     * @return resource.
     */
    public static Resource valueOfDisplayName(final String displayName)
    {
        Resource answer = null;

        if (StringUtils.isNotEmpty(displayName))
        {
            final Resource[] resources = values();
            final int size = resources.length;

            for (int i = 0; answer == null && i < size; i++)
            {
                final Resource resource = resources[i];

                if (displayName.equals(resource.getDisplayName()))
                {
                    answer = resource;
                }
            }
        }

        return answer;
    }

    /** Display name. */
    private final String _displayName;

    /** Flag indicating if this is an advanced resource. */
    private final boolean _isAdvanced;

    /**
     * Construct this object with the given parameter.
     * 
     * @param displayName Display name.
     */
    private Resource(final String displayName)
    {
        this(displayName, false);
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param displayName Display name.
     * @param isAdvanced Flag indicating if this is an advanced resource.
     */
    private Resource(final String displayName, final boolean isAdvanced)
    {
        _displayName = displayName;
        _isAdvanced = isAdvanced;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName()
    {
        return _displayName;
    }

    /**
     * @return a new production name.
     */
    public String getProductionName()
    {
        String answer = null;

        if (isBasic())
        {
            answer = getDisplayName() + " Rate";
        }
        else
        {
            answer = getDisplayName() + " Queued";
        }

        return answer;
    }

    /**
     * @return the isAdvanced
     */
    public boolean isAdvanced()
    {
        return _isAdvanced;
    }

    /**
     * @return the isBasic
     */
    public boolean isBasic()
    {
        return !_isAdvanced;
    }
}
