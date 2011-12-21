//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides a value string parser implementation for Illyriad.
 */
public class IllyriadValueStringParser implements ValueStringParser<Integer>
{
    @Override
    public Integer parse(final WebElement webElement)
    {
        Integer answer = null;

        if (webElement != null)
        {
            final String valueString = webElement.getText();

            if (StringUtils.isNotEmpty(valueString))
            {
                String myValueString = valueString.replaceAll(",", "");
                myValueString = myValueString.replaceAll("[+]", "");

                if (StringUtils.isNotEmpty(myValueString))
                {
                    if (myValueString.endsWith("M"))
                    {
                        final int length = myValueString.length();
                        myValueString = myValueString.substring(0, length - 1);
                        answer = Double.valueOf(
                                parseDoubleOnly(myValueString) * 1000000)
                                .intValue();
                    }
                    else
                    {
                        answer = parseIntegerOnly(myValueString);
                    }
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

    /**
     * @param valueString Value string.
     * 
     * @return an integer parsed from the given parameter, if possible.
     */
    private Integer parseIntegerOnly(final String valueString)
    {
        Integer answer = null;

        try
        {
            answer = Integer.parseInt(valueString);
        }
        catch (final NumberFormatException ignore)
        {
            // Nothing to do.
        }

        return answer;
    }
}
