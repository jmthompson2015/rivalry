//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for Illyriad.
 */
public class IllyriadInjector extends DefaultDataCollectorInjector
{
    @Override
    public DataCollector injectDataCollector()
    {
        final ValueStringParser<Integer> valueStringParser = injectValueStringParser();

        final DataCollector answer = new IllyriadDataCollector(
                valueStringParser);

        return answer;
    }

    @Override
    public boolean injectIsJavascriptEnabled()
    {
        return true;
    }

    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setPreferencePrefix("illyriad");

        return answer;
    }

    @Override
    public ValueStringParser<Integer> injectValueStringParser()
    {
        return new IllyriadValueStringParser();
    }
}
