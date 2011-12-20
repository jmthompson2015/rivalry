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
 * Provides a comparator for candidate town ID.
 */
public class CandidateTownIdComparator implements Comparator<IllyriadCandidate>
{
    @Override
    public int compare(final IllyriadCandidate candidate0,
            final IllyriadCandidate candidate1)
    {
        final Long townId0 = candidate0.getTownId();
        final Long townId1 = candidate1.getTownId();

        return townId0.compareTo(townId1);
    }
}
