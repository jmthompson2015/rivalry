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
 * Provides a map function.
 * 
 * @param <A> From type.
 * @param <B> To type.
 */
public class MapFunction<A, B>
{
    /**
     * @param x Value.
     * 
     * @return a mapped value.
     */
    public B apply(final A x)
    {
        return null;
    }

    /**
     * @param function Function.
     * 
     * @return a new composed map function.
     */
    public ComposedMapFunction<A, B> compose(final MapFunction<B, B> function)
    {
        return new ComposedMapFunction<A, B>(function, this);
    }
}
