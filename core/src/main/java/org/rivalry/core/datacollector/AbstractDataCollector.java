//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCategory;
import org.rivalry.core.model.DefaultCriterion;

/**
 * Provides methods to support a data collector.
 */
public abstract class AbstractDataCollector
{
    /**
     * @param name Name.
     * 
     * @return a new category.
     */
    protected Category createCategory(final String name)
    {
        final Category answer = new DefaultCategory();

        answer.setName(name);

        return answer;
    }

    /**
     * @param name Name.
     * @param category Category.
     * 
     * @return a new criterion.
     */
    protected Criterion createCriterion(final String name,
            final Category category)
    {
        final Criterion answer = new DefaultCriterion();

        answer.setName(name);
        answer.setCategory(category);

        return answer;
    }

    /**
     * Disable Selenium WebDriver logger.
     */
    protected void disableWebDriverLogging()
    {
        final Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
    }
}
