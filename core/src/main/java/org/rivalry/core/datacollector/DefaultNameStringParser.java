//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import org.openqa.selenium.WebElement;

/**
 * Provides a parser for names.
 */
public class DefaultNameStringParser implements NameStringParser
{
    @Override
    public String parse(final WebElement webElement)
    {
        String answer = null;

        if (webElement != null)
        {
            answer = webElement.getText();
        }

        return answer;
    }
}
