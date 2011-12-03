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
 * Defines methods required by a name string parser.
 */
public interface NameStringParser
{
    /**
     * @param webElement Web element.
     * 
     * @return a string which represents the given parameter.
     */
    String parse(WebElement webElement);
}
