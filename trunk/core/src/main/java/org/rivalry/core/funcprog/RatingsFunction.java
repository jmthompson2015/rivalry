//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.funcprog;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

/**
 * Provides a map function for candidate ratings.
 */
public class RatingsFunction extends MapFunction<Candidate, Double>
{
    /** Criterion. */
    private final Criterion _criterion;

    /**
     * Construct this object with the given parameter.
     * 
     * @param criterion Criterion.
     */
    public RatingsFunction(final Criterion criterion)
    {
        _criterion = criterion;
    }

    /**
     * @param x Value.
     * 
     * @return a mapped value.
     */
    @Override
    public Double apply(final Candidate x)
    {
        Double answer = null;

        if (x != null)
        {
            answer = x.getRating(_criterion);
        }

        return answer;
    }
}
