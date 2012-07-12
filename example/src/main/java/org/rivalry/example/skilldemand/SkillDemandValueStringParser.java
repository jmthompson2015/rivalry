//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.skilldemand;

import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.WebElement;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides a value string parser for skill demand.
 */
public class SkillDemandValueStringParser implements ValueStringParser<Integer>
{
    @Override
    public Integer parse(final String valueString)
    {
        Integer answer = null;

        if (StringUtils.isNotEmpty(valueString))
        {
            String totalString = valueString;
            final String key = " of ";
            final int index = totalString.indexOf(key);
            System.out.println("valueStringParser index = " + index);
            if (index >= 0)
            {
                totalString = totalString.substring(index + key.length());
            }

            answer = Integer.parseInt(totalString);
        }

        return answer;
    }

    @Override
    public Integer parse(final WebElement webElement)
    {
        Integer answer = null;

        if (webElement != null)
        {
            final String totalString = webElement.getText();
            answer = parse(totalString);
        }

        return answer;
    }
}
