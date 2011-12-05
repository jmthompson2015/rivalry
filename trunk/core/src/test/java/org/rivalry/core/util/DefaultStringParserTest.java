package org.rivalry.core.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>DefaultStringParser</code> class.
 */
public class DefaultStringParserTest
{
    /** String parser. */
    private StringParser _parser = new DefaultStringParser();

    /**
     * Test the <code>parseDouble()</code> method.
     */
    @Test
    public void parseDouble()
    {
        final Double result = _parser.parseDouble("12.3");
        assertNotNull(result);
        assertThat(result, is(12.3));
    }

    /**
     * Test the <code>parseDouble()</code> method.
     */
    @Test
    public void parseDoubleComma()
    {
        final Double result = _parser.parseDouble("12,345.67");
        assertNotNull(result);
        assertThat(result, is(12345.67));
    }

    /**
     * Test the <code>parseDouble()</code> method.
     */
    @Test
    public void parseDoubleMoney()
    {
        final Double result = _parser.parseDouble("$12,345.67");
        assertNotNull(result);
        assertThat(result, is(12345.67));
    }

    /**
     * Test the <code>parseDouble()</code> method.
     */
    @Test
    public void parseDoubleNull()
    {
        Double result = _parser.parseDouble(null);
        assertNull(result);

        result = _parser.parseDouble("");
        assertNull(result);

        result = _parser.parseDouble("a");
        assertNull(result);
    }

    /**
     * Test the <code>parseDouble()</code> method.
     */
    @Test
    public void parseDoublePercent()
    {
        final Double result = _parser.parseDouble("12.34%");
        assertNotNull(result);
        assertThat(result, is(0.1234));
    }
}
