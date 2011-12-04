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
 * Provides an add reduce function for integers.
 */
public class AddIntegerFunction implements ReduceFunction<Integer>
{
    @Override
    public Integer apply(final Integer x, final Integer y)
    {
        Integer answer = 0;

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
