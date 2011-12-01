package org.rivalry.core.datacollector.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;
import org.rivalry.core.datacollector.DCSelector;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.SelectorType;

/**
 * Provides tests for the <code>DCSpecReader</code> class.
 */
public class DCSpecReaderTest
{
    /**
     * Test the <code>read()</code> method.
     */
    @Test
    public void readYahooFinance()
    {
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorYahooFinance.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec dcSpec = dcReader.read(reader);
        assertNotNull(dcSpec);

        assertThat(dcSpec.getUrl(),
                is("http://finance.yahoo.com/q/ks?s=$1+Key+Statistics"));
        assertThat(dcSpec.getSelectors().size(), is(1));
        final DCSelector dcSelector = dcSpec.getSelectors().get(0);
        assertNotNull(dcSelector);
        assertThat(dcSelector.getType(), is(SelectorType.CLASS_NAME));
        assertThat(dcSelector.getValue(), is("yfnc_datamodoutline1"));
        assertThat(dcSelector.getSelectors().size(), is(2));

        {
            final DCSelector selector = dcSelector.getSelectors().get(0);
            assertNotNull(selector);
            assertThat(selector.getType(), is(SelectorType.CLASS_NAME));
            assertThat(selector.getValue(), is("yfnc_tablehead1"));
            assertThat(selector.getSelectors().size(), is(0));
        }

        {
            final DCSelector selector = dcSelector.getSelectors().get(1);
            assertNotNull(selector);
            assertThat(selector.getType(), is(SelectorType.CLASS_NAME));
            assertThat(selector.getValue(), is("yfnc_tabledata1"));
            assertThat(selector.getSelectors().size(), is(0));
        }
    }
}
