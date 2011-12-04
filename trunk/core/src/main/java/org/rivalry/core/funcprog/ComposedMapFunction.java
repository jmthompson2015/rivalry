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
 * Provides a composed map function.
 * 
 * @param <A> From type.
 * @param <B> To type.
 */
public class ComposedMapFunction<A, B> extends MapFunction<A, B>
{
    /** Base function. */
    private MapFunction<B, B> _baseFunction;

    /** Composed function. */
    private MapFunction<A, B> _composedFunction;

    /**
     * Construct this object with the given parameters.
     * 
     * @param baseFunction Base function.
     * @param composedFunction Composed function.
     */
    public ComposedMapFunction(final MapFunction<B, B> baseFunction,
            final MapFunction<A, B> composedFunction)
    {
        _baseFunction = baseFunction;
        _composedFunction = composedFunction;
    }

    @Override
    public B apply(final A x)
    {
        return _baseFunction.apply(_composedFunction.apply(x));
    }
}
