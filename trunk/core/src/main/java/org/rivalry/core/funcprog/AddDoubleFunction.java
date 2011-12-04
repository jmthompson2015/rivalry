//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.funcprog;

/**
 * Provides an add reduce function for doubles.
 */
public class AddDoubleFunction implements ReduceFunction<Double>
{
    @Override
    public Double apply(final Double x, final Double y)
    {
        Double answer = 0.0;

        if (x != null)
        {
            answer += x;
        }

        if (y != null)
        {
            answer += y;
        }

        return answer;
    }
}
