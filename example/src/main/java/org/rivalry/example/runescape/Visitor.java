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
 * Defines methods required by a visitor.
 */
public interface Visitor
{
    /**
     * Visit the given item.
     * 
     * @param item Item.
     */
    void visit(Item item);
}
