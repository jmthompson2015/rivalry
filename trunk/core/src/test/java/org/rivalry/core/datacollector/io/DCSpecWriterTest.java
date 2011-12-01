package org.rivalry.core.datacollector.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;
import org.rivalry.core.datacollector.DCSpec;

/**
 * Provides tests for the <code>DCSpecWriter</code> class.
 */
public class DCSpecWriterTest
{
    /**
     * Test the <code>read()</code> method.
     */
    @Test
    public void readYahooFinance()
    {
        final DCSpec dcSpec = createDcSpec();
        final Writer writer = new StringWriter();
        final DCSpecWriter dcWriter = new DCSpecWriter();
        dcWriter.write(writer, dcSpec);
        final String result = writer.toString();
        assertNotNull(result);

        final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:DCSpec xmlns:ns2=\"http://core.rivalry.org/datacollector\"><url>http://finance.yahoo.com/q/ks?s=$1+Key+Statistics</url><selector><type>CLASS_NAME</type><value>yfnc_datamodoutline1</value><selector><type>CLASS_NAME</type><value>yfnc_tablehead1</value></selector><selector><type>CLASS_NAME</type><value>yfnc_tabledata1</value></selector></selector></ns2:DCSpec>";
        assertThat(result, is(expected));
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDcSpec()
    {
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorYahooFinance.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec answer = dcReader.read(reader);
        assertNotNull(answer);

        return answer;
    }
}
