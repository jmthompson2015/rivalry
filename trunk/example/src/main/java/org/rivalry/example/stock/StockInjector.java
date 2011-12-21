//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.stock;

import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.datacollector.NameStringParser;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for stock.
 */
public class StockInjector extends DefaultDataCollectorInjector
{
    @Override
    public NameStringParser injectNameStringParser()
    {
        return new YahooFinanceNameStringParser();
    }

    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setDescription("Stock data \u00A9 2011 Yahoo!. All rights reserved. Used without permission.");
        answer.setPreferencePrefix("stock");

        return answer;
    }

    @Override
    public ValueStringParser<?> injectValueStringParser()
    {
        return new YahooFinanceParser();
    }
}
