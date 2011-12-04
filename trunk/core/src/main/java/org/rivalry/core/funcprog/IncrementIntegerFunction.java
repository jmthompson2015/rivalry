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
 * Provides an increment function.
 */
public class IncrementIntegerFunction extends MapFunction<Integer, Integer>
{
    @Override
    public Integer apply(final Integer x)
    {
        return x + 1;
    }
}
