//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.dogbreed;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides a value string parser implementation to handle dog breed information.
 */
public class DogBreedsParser implements ValueStringParser<Double>
{
    @Override
    public Double parse(final String valueString)
    {
        Double answer = null;

        if (StringUtils.isNotEmpty(valueString))
        {
            final int index = valueString.lastIndexOf('-');

            if (index >= 0)
            {
                final String value = valueString.substring(index + 1);
                answer = Double.parseDouble(value);
            }
        }

        return answer;
    }

    @Override
    public Double parse(final WebElement webElement)
    {
        Double answer = null;

        if (webElement != null)
        {
            final String valueString = webElement.getAttribute("class");
            answer = parse(valueString);
        }

        return answer;
    }
}
