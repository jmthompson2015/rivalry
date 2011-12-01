//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.core.datacollector.io;

import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.rivalry.core.datacollector.DCSpec;

/**
 * Provides a reader for code generator XML documents.
 */
public class DCSpecReader
{
    /**
     * Read a code generator XML document.
     * 
     * @param reader Reader.
     * 
     * @return code generator root.
     */
    public DCSpec read(final Reader reader)
    {
        DCSpec answer = null;

        try
        {
            final JAXBContext jc = JAXBContext
                    .newInstance(Constants.PACKAGE_NAME);
            final Unmarshaller um = jc.createUnmarshaller();
            answer = (DCSpec)um.unmarshal(reader);
        }
        catch (final JAXBException e)
        {
            throw new IOReaderException(e);
        }

        return answer;
    }
}
