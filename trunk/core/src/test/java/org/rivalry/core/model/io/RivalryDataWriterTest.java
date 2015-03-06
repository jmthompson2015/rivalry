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
        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><DefaultRivalryData><createDate>2015-03-02T12:58:00-07:00</createDate><description>Macintosh Computers rivalry data</description><preferencePrefix>macComputers</preferencePrefix><criterion><name>release</name><description>Release</description><autoMinMax>true</autoMinMax></criterion><criterion><name>processorSpeed</name><description>Processor Speed (GHz)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>processor</name><description>Processor</description><autoMinMax>true</autoMinMax></criterion><criterion><name>ram</name><description>RAM (GB)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>harddrive</name><description>Harddrive (GB)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>harddriveSpeed</name><description>Harddrive Speed (rpm)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>flashStorage</name><description>Flash Storage (GB)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>graphicsCard</name><description>Graphics Card</description><autoMinMax>true</autoMinMax></criterion><criterion><name>graphicsRam</name><description>Graphics RAM (MB)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>price</name><description>Price ($)</description><autoMinMax>true</autoMinMax></criterion><criterion><name>geekbench3Score</name><description>Geekbench 3 Score</description><autoMinMax>true</autoMinMax></criterion><candidate><name>Mac Mini Late 2014 1.4 GHz</name><page>http://store.apple.com/us/buy-mac/mac-mini</page><values><entry><key>release</key><value>2014/10</value></entry><entry><key>processorSpeed</key><value>1.4</value></entry><entry><key>processor</key><value>dual-core Intel Core i5</value></entry><entry><key>ram</key><value>4</value></entry><entry><key>harddrive</key><value>500</value></entry><entry><key>harddriveSpeed</key><value>5400</value></entry><entry><key>graphicsCard</key><value>Intel HD Graphics 5000</value></entry><entry><key>graphicsRam</key><value>1024</value></entry><entry><key>price</key><value>499</value></entry><entry><key>geekbench3Score</key><value>5382</value></entry></values></candidate><candidate><name>Mac Mini Late 2014 2.6 GHz</name><page>http://store.apple.com/us/buy-mac/mac-mini</page><values><entry><key>release</key><value>2014/10</value></entry><entry><key>processorSpeed</key><value>2.6</value></entry><entry><key>processor</key><value>dual-core Intel Core i5</value></entry><entry><key>ram</key><value>8</value></entry><entry><key>harddrive</key><value>1024</value></entry><entry><key>harddriveSpeed</key><value>5400</value></entry><entry><key>graphicsCard</key><value>Intel Iris Graphics</value></entry><entry><key>graphicsRam</key><value>1024</value></entry><entry><key>price</key><value>699</value></entry><entry><key>geekbench3Score</key><value>6581</value></entry></values></candidate><candidate><name>Mac Mini Late 2014 2.8 GHz</name><page>http://store.apple.com/us/buy-mac/mac-mini</page><values><entry><key>release</key><value>2014/10</value></entry><entry><key>processorSpeed</key><value>2.6</value></entry><entry><key>processor</key><value>dual-core Intel Core i5</value></entry><entry><key>ram</key><value>8</value></entry><entry><key>harddrive</key><value>1024</value></entry><entry><key>harddriveSpeed</key><value>Fusion</value></entry><entry><key>flashStorage</key><value>Fusion</value></entry><entry><key>graphicsCard</key><value>Intel Iris Graphics</value></entry><entry><key>graphicsRam</key><value>1024</value></entry><entry><key>price</key><value>999</value></entry><entry><key>geekbench3Score</key><value>7057</value></entry></values></candidate><candidate><name>iMac 27-inch Late 2013 3.2 GHz</name><page>http://store.apple.com/us/buy-mac/imac</page><values><entry><key>release</key><value>2013/09</value></entry><entry><key>processorSpeed</key><value>3.2</value></entry><entry><key>processor</key><value>quad-core Intel Core i5</value></entry><entry><key>ram</key><value>8</value></entry><entry><key>harddrive</key><value>1024</value></entry><entry><key>harddriveSpeed</key><value>7200</value></entry><entry><key>graphicsCard</key><value>NVIDIA GeForce GT 755M</value></entry><entry><key>graphicsRam</key><value>1024</value></entry><entry><key>price</key><value>1799</value></entry><entry><key>geekbench3Score</key><value>11271</value></entry></values></candidate><candidate><name>iMac 27-inch Late 2013 3.4 GHz</name><page>http://store.apple.com/us/buy-mac/imac</page><values><entry><key>release</key><value>2013/09</value></entry><entry><key>processorSpeed</key><value>3.4</value></entry><entry><key>processor</key><value>quad-core Intel Core i5</value></entry><entry><key>ram</key><value>8</value></entry><entry><key>harddrive</key><value>1024</value></entry><entry><key>harddriveSpeed</key><value>7200</value></entry><entry><key>graphicsCard</key><value>NVIDIA GeForce GT 775M</value></entry><entry><key>graphicsRam</key><value>2048</value></entry><entry><key>price</key><value>1999</value></entry><entry><key>geekbench3Score</key><value>11796</value></entry></values></candidate><candidate><name>iMac 27-inch Retina 5K Late 2014 3.5 GHz</name><page>http://store.apple.com/us/buy-mac/imac</page><values><entry><key>release</key><value>2014/10</value></entry><entry><key>processorSpeed</key><value>3.5</value></entry><entry><key>processor</key><value>quad-core Intel Core i5</value></entry><entry><key>ram</key><value>8</value></entry><entry><key>harddrive</key><value>1024</value></entry><entry><key>harddriveSpeed</key><value>Fusion</value></entry><entry><key>flashStorage</key><value>Fusion</value></entry><entry><key>graphicsCard</key><value>AMD Radeon R9 M290X</value></entry><entry><key>graphicsRam</key><value>2048</value></entry><entry><key>price</key><value>2499</value></entry><entry><key>geekbench3Score</key><value>12231</value></entry></values></candidate></DefaultRivalryData>";
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
