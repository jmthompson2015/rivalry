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
 * Provides an evens filter function for numbers.
 * 
 * @param <T> Type.
 */
public class EvensFunction<T extends Number> implements FilterFunction<T>
{
    @Override
    public boolean apply(final T x)
    {
        boolean answer = false;

        if (x != null)
        {
            answer = x.doubleValue() % 2 == 0;
        }

        return answer;
    }
}
