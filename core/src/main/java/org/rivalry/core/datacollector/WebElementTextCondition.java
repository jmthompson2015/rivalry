//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import org.apache.commons.lang.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Provides a condition which looks for particular text in a web element.
 */
public class WebElementTextCondition implements ExpectedCondition<Boolean>
{
    /** Find condition. */
    private By _findCondition;

    /** Text. */
    private String _text;

    /** Start. */
    private Long _start;

    /**
     * Construct this object with the given parameters.
     * 
     * @param by Find condition.
     * @param text Element text to match.
     */
    public WebElementTextCondition(final By by, final String text)
    {
        Validate.notNull(by);
        Validate.notEmpty(text);

        _findCondition = by;
        _text = text;
    }

    @Override
    public Boolean apply(final WebDriver driver)
    {
        if (_start == null)
        {
            _start = System.currentTimeMillis();
        }

        Boolean answer = Boolean.FALSE;

        final WebElement element = driver.findElement(_findCondition);

        if (element != null)
        {
            final String myText = element.getText();

            if (_text.equals(myText))
            {
                answer = Boolean.TRUE;
            }
        }

        return answer;
    }
}
