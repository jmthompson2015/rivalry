//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.core.datacollector.io;

import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.rivalry.core.datacollector.DCSpec;

/**
 * Provides a writer for code generator XML documents.
 */
public class DCSpecWriter
{
    /**
     * Write a code generator XML document.
     * 
     * @param writer Writer.
     * @param dcSpec Data collector specification.
     */
    public void write(final Writer writer, final DCSpec dcSpec)
    {
        try
        {
            final JAXBContext jaxbContext = JAXBContext
                    .newInstance(Constants.PACKAGE_NAME);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(dcSpec, writer);
        }
        catch (final JAXBException e)
        {
            throw new IOWriterException(e);
        }
    }
}
