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
import org.rivalry.core.util.DefaultStringParser;
import org.rivalry.core.util.StringParser;

/**
 * Provides a parser for double values.
 */
public class DefaultValueStringParser implements ValueStringParser
{
    /** String parser. */
    private final StringParser _stringParser = new DefaultStringParser();

    @Override
    public Double parse(final WebElement webElement)
    {
        Double answer = null;

        if (webElement != null)
        {
            final String valueString = webElement.getText();

            if (StringUtils.isNotEmpty(valueString))
            {
                answer = _stringParser.parseDouble(valueString);
            }
        }

        return answer;
    }
}
