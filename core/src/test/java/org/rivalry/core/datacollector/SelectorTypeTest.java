//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Provides tests for the <code>SelectorType</code> class.
 */
public class SelectorTypeTest
{
    /** Web driver. */
    private static WebDriver _webDriver;

    /**
     * Set up class for the tests.
     */
    @BeforeClass
    public static void setUpClass()
    {
        _webDriver = new HtmlUnitDriver();
        _webDriver.get("http://finance.yahoo.com/q/ks?s=AAPL+Key+Statistics");
    }

    /**
     * Test the <code>findElement()</code> method.
     */
    @Test
    public void findElementWebDriver()
    {
        final WebElement result = SelectorType.CLASS_NAME.findElement(
                _webDriver, "yfnc_datamodoutline1");
        assertNotNull(result);

        try
        {
            SelectorType.CLASS_NAME.findElement(_webDriver, "bogus");
            fail("Should have thrown an exception");
        }
        catch (final NoSuchElementException e)
        {
            assertTrue(e.getMessage().startsWith(
                    "Unable to locate a node using"));
        }
    }
}
