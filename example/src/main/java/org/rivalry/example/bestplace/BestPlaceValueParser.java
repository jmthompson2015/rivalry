//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.bestplace;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides a parser for double values.
 */
public class BestPlaceValueParser implements ValueStringParser
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
                String myString = valueString.trim();
                myString = myString.replaceAll(",", "");
                answer = parseDoubleOnly(myString);
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
