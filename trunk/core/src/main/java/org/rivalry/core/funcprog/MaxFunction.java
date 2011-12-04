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
 * Provides a maximum reduce function for numbers.
 * 
 * @param <T> Type.
 */
public class MaxFunction<T extends Number> implements ReduceFunction<T>
{
    @Override
    public T apply(final T x, final T y)
    {
        T answer = null;

        if (x == null)
        {
            answer = y;
        }
        else if (y == null)
        {
            answer = x;
        }
        else
        {
            answer = x.doubleValue() > y.doubleValue() ? x : y;
        }

        return answer;
    }
}
