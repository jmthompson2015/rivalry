//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;
import org.rivalry.core.model.RivalryData;

/**
 * Provides tests for the <code>RivalryDataWriter</code> class.
 */
public class RivalryDataWriterTest
{
    /**
     * Test the <code>write()</code> method.
     */
    @Test
    public void writeMacintoshComputers()
    {
        // Setup.
        final RivalryData rivalryData = createRivalryData();
        final Writer writer = new StringWriter();
        final RivalryDataWriter rdWriter = new RivalryDataWriter();

        // Run.
        rdWriter.write(writer, rivalryData);
        final String result = writer.toString();

        // Verify.
        assertNotNull(result);
        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><DefaultRivalryData><createDate>2015-03-02T12:58:00-07:00</createDate><description>Macintosh Computers rivalry data</description><preferencePrefix>macComputers</preferencePrefix><candidate><name>Mac Mini Late 2014 1.4 GHz</name><page>http://store.apple.com/us/buy-mac/mac-mini</page><values/></candidate><criterion><name>processorSpeed</name><description>Processor Speed (GHz)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>processor</name><description>Processor</description><autoMinMax>true</autoMinMax></criterion><criterion><name>ram</name><description>RAM (GB)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>geekbench3Score</name><description>Geekbench 3 Score</description><autoMinMax>true</autoMinMax></criterion></DefaultRivalryData>";
        assertThat(result, is(expected));
    }

    /**
     * @return a new data collector specification.
     */
    private RivalryData createRivalryData()
    {
        final InputStream inputStream = getClass().getResourceAsStream("/MacintoshComputersRivalryData.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final RivalryDataReader rdReader = new RivalryDataReader();
        final RivalryData answer = rdReader.read(reader);
        assertNotNull(answer);

        return answer;
    }
}
