//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.core.datacollector.io;

/**
 * Provides a runtime exception for a code generator I/O reader.
 */
public class IOReaderException extends RuntimeException
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object.
     */
    public IOReaderException()
    {
        super();
    }

    /**
     * Construct this object with the given message.
     * 
     * @param message Message to include with the exception.
     */
    public IOReaderException(final String message)
    {
        super(message);
    }

    /**
     * Construct this object with the given message and cause.
     * 
     * @param message Message to include with the exception.
     * @param cause The cause.
     */
    public IOReaderException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construct this object with the given cause.
     * 
     * @param cause The cause.
     */
    public IOReaderException(final Throwable cause)
    {
        super(cause);
    }
}
