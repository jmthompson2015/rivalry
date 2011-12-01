//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

/**
 * Provides a parser for double values.
 */
public class YahooFinanceParser implements ValueStringParser
{
    @Override
    public Double parse(final WebElement webElement)
    {
        Double answer = null;

        if (webElement != null)
        {
            final String valueString = webElement.getText();

            if (StringUtils.isNotEmpty(valueString))
            {
                final String myString = valueString.trim();

                if (myString.endsWith("%"))
                {
                    answer = parseDoubleOnly(myString.substring(0,
                            myString.length() - 1)) / 100.0;
                }
                else if (myString.endsWith("B"))
                {
                    answer = parseDoubleOnly(myString.substring(0,
                            myString.length() - 1)) * 1000000000;
                }
                else
                {
                    answer = parseDoubleOnly(myString);
                }
            }
        }

        return answer;
    }

    /**
     * @param valueString Value string.
     * 
     * @return a double parsed from the given parameter, if possible.
     */
    private Double parseDoubleOnly(final String valueString)
    {
        Double answer = null;

        try
        {
            answer = Double.parseDouble(valueString);
        }
        catch (final NumberFormatException ignore)
        {
            // Nothing to do.
        }

        return answer;
    }
}
