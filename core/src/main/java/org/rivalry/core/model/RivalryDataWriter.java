//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.Writer;

import org.apache.commons.io.output.WriterOutputStream;

/**
 * Provides a writer for rivalry data.
 */
public class RivalryDataWriter
{
    /**
     * @param rivalryData Rivalry data.
     * @param writer Writer.
     */
    public void write(final RivalryData rivalryData, final Writer writer)
    {
        final XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new WriterOutputStream(writer)));
        encoder.writeObject(rivalryData);
        encoder.close();
    }
}
