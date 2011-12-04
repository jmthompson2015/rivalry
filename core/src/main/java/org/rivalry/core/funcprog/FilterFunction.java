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
 * Defines methods required by a filter function.
 * 
 * @param <T> Type.
 */
public interface FilterFunction<T>
{
    /**
     * @param x Value.
     * 
     * @return true if the filter passes.
     */
    boolean apply(T x);
}
