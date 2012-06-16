//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.example.runescape;

/**
 * Defines methods required by a visitable object.
 */
public interface Visitable
{
    /**
     * Accept the given visitor in a breadth first manner.
     * 
     * @param visitor Visitor.
     */
    void acceptBreadthFirst(Visitor visitor);

    /**
     * Accept the given visitor in a depth first manner.
     * 
     * @param visitor Visitor.
     */
    void acceptDepthFirst(Visitor visitor);
}
