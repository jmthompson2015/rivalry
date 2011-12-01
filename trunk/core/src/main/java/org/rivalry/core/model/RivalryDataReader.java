package org.rivalry.core.model;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.Reader;

import org.apache.commons.io.input.ReaderInputStream;

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
        final XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new ReaderInputStream(reader)));
        final RivalryData answer = (RivalryData)decoder.readObject();
        decoder.close();

        return answer;
    }
}
