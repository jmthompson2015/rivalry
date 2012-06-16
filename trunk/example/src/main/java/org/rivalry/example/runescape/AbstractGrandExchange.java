//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import java.util.Calendar;

/**
 * Provides base functionality for a grand exchange.
 */
public abstract class AbstractGrandExchange implements GrandExchange
{
    /** Date-time. */
    private final Calendar _dateTime = Calendar.getInstance();

    @Override
    public Integer computeBasePremium(final Item item)
    {
        final Integer value0 = getValue(item);
        final Integer cost0 = item.computeBaseCost(this);

        final double value = (value0 == null ? 0.0 : value0);
        final double cost = (cost0 == null ? 0.0 : cost0);

        Integer answer = 0;

        if (cost > 0.0)
        {
            final double premium = (value / cost) - 1.0;
            final double factor = 100.0;
            answer = (int)(Math.round(premium * factor));
        }

        return answer;
    }

    @Override
    public Integer computePremium(final Item item)
    {
        final Integer value0 = getValue(item);
        final Integer cost0 = item.computeCost(this);

        final double value = (value0 == null ? 0.0 : value0);
        final double cost = (cost0 == null ? 0.0 : cost0);

        Integer answer = 0;

        if (cost > 0.0)
        {
            final double premium = (value / cost) - 1.0;
            final double factor = 100.0;
            answer = (int)(Math.round(premium * factor));
        }

        return answer;
    }

    @Override
    public Calendar getDateTime()
    {
        return _dateTime;
    }
}
