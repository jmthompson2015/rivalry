//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.bestplace;

import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for dog breeds.
 */
public class BestPlaceInjector extends DefaultDataCollectorInjector
{
    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setDescription("Best places data \u00A9 2010 Best Places to Live & Retire. All rights reserved. Used without permission.");
        answer.setPreferencePrefix("bestPlace");

        return answer;
    }
}
