//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.util;

import java.io.Serializable;

/**
 * Defines methods required by an abstract factory.
 * 
 * @param <T> Type of object manufactured.
 */
public interface Provider<T> extends Serializable
{
    /**
     * @return a new object.
     */
    T newInstance();
}
