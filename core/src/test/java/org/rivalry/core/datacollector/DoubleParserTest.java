//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import org.junit.Test;

/**
 * Provides tests for the <code>DoubleParser</code> class.
 */
public class DoubleParserTest
{
    /** Parser. */
    private YahooFinanceParser _parser = new YahooFinanceParser();

    /**
     * Test the <code>parse()</code> method.
     */
    @Test
    public void parse()
    {
        // final HtmlUnitDriver parent = null;
        // final HtmlElement element = new HtmlTableDataCell();
        // final String valueString = "108.95";
        // element.setTextContent(valueString);
        // final WebElement webElement = new HtmlUnitWebElement(parent,
        // element);
        // ((HtmlUnitWebElement)webElement).
        // final Double result = _parser.parse(webElement);
        // assertNotNull(result);
        // assertThat(result, is(108.95));
    }

    /**
     * Test the <code>parse()</code> method.
     */
    @Test
    public void parseBillion()
    {
        // final String valueString = "23.61B";
        // final Double result = _parser.parse(valueString);
        // assertNotNull(result);
        // assertThat(result, is(23610000000.0));
    }

    /**
     * Test the <code>parse()</code> method.
     */
    @Test
    public void parseNull()
    {
        // final String valueString = null;
        // final Double result = _parser.parse(valueString);
        // assertNull(result);
    }

    /**
     * Test the <code>parse()</code> method.
     */
    @Test
    public void parsePercent()
    {
        // final String valueString = "82.00%";
        // final Double result = _parser.parse(valueString);
        // assertNotNull(result);
        // assertThat(result, is(0.82));
    }
}
