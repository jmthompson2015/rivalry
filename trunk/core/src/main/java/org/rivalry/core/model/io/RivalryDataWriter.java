//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model.io;

import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.rivalry.core.datacollector.io.IOWriterException;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a writer for rivalry data.
 */
public class RivalryDataWriter
{
    /**
     * @param writer Writer.
     * @param rivalryData Rivalry data.
     */
    public void write(final Writer writer, final RivalryData rivalryData)
    {
        try
        {
            final JAXBContext jaxbContext = JAXBContext.newInstance(Constants.PACKAGE_NAME);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(rivalryData, writer);
        }
        catch (final JAXBException e)
        {
            throw new IOWriterException(e);
        }
    }
}
