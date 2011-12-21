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

                if (myValueString.endsWith("M"))
                {
                    answer = parseIntegerOnly(myValueString.substring(0,
                            myValueString.length() - 1)) * 1000000;
                }
                else
                {
                    answer = parseIntegerOnly(myValueString);
                }
            }
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
