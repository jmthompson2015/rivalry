//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model.io;

import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.rivalry.core.datacollector.io.IOReaderException;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a reader for rivalry data.
 */
public class RivalryDataReader
{
    /**
     * @param reader Reader.
     *
     * @return a new populated rivalry data object.
     */
    public RivalryData read(final Reader reader)
    {
        RivalryData answer = null;

        try
        {
            final JAXBContext jc = JAXBContext.newInstance(Constants.PACKAGE_NAME);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            answer = (RivalryData)unmarshaller.unmarshal(reader);
        }
        catch (final JAXBException e)
        {
            throw new IOReaderException(e);
        }

        return answer;
    }
}
