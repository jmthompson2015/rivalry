//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a default implementation of a grand exchange.
 */
public class RivalryDataGrandExchange extends AbstractGrandExchange
{
    /** Map of item to value. */
    // private final Map<Item, Integer> _itemToValue = new HashMap<Item, Integer>();

    /** Rivalry data. */
    private final RivalryData _rivalryData;

    /**
     * Construct this object with the given parameter.
     * 
     * @param rivalryData Rivalry data.
     */
    public RivalryDataGrandExchange(final RivalryData rivalryData)
    {
        _rivalryData = rivalryData;

        // final Criterion criterion = rivalryData.findCriterionByName(Constants.VALUE_CRITERION);
        //
        // for (final Candidate candidate : rivalryData.getCandidates())
        // {
        // final Item item = Item.valueOfNameIgnoreCase(candidate.getName());
        //
        // if (item != null)
        // {
        // final Double value = candidate.getRating(criterion);
        //
        // if (value != null)
        // {
        // putValue(item, value.intValue());
        // }
        // }
        // }
    }

    @Override
    public Integer getValue(final Item item)
    {
        // return _itemToValue.get(item);
        Integer answer = null;
        final Candidate candidate = _rivalryData.findCandidateByName(item.getName());

        if (candidate != null)
        {
            final Criterion valueCriterion = _rivalryData.findCriterionByName(Constants.VALUE_CRITERION);
            final Double value = candidate.getRating(valueCriterion);
            if (value != null)
            {
                answer = value.intValue();
            }
        }

        return answer;
    }

    /**
     * @param item Item.
     * @param value Value.
     */
    // private void putValue(final Item item, final Integer value)
    // {
    // _itemToValue.put(item, value);
    // }
}
