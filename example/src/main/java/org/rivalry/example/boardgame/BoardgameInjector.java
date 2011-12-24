//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.boardgame;

import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for boardgames.
 */
public class BoardgameInjector extends DefaultDataCollectorInjector
{
    @Override
    public DataPostProcessor injectDataPostProcessor()
    {
        return new BoardgameDataPostProcessor();
    }

    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setDescription("Boardgame data \u00A9 BoardGameGeek LLC. All rights reserved. Used without permission.");
        answer.setPreferencePrefix("boardgame");

        return answer;
    }
}
