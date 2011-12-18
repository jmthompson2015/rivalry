//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.stock;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.rivalry.core.datacollector.NameStringParser;

/**
 * Provides an implementation of a name string parser for Yahoo! Finance.
 */
public class YahooFinanceNameStringParser implements NameStringParser
{
    @Override
    public String parse(final WebElement webElement)
    {
        String answer = null;

        if (webElement != null)
        {
            answer = webElement.getText();

            if (StringUtils.isNotEmpty(answer))
            {
                answer = trimEndKey(answer, "1:");
                answer = trimEndKey(answer, "2:");
                answer = trimEndKey(answer, "3:");
                answer = trimEndKey(answer, "4:");
                answer = trimEndKey(answer, "5:");
                answer = trimEndKey(answer, "6:");
                answer = trimEndKey(answer, ":");
            }
        }

        return answer;
    }

    /**
     * @param value Value.
     * @param key Key.
     * 
     * @return the value without the key on the end.
     */
    private String trimEndKey(final String value, final String key)
    {
        String answer = value;
        if (answer.endsWith(key))
        {
            final int newLength = answer.length() - key.length();
            answer = answer.substring(0, newLength);
        }
        return answer;
    }
}
