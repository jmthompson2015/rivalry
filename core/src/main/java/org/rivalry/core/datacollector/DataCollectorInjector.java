//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Defines methods required by a data collector injector.
 */
public interface DataCollectorInjector
{
    /**
     * @return a new category provider.
     */
    Provider<Category> injectCategoryProvider();

    /**
     * @return a new criterion provider.
     */
    Provider<Criterion> injectCriterionProvider();

    /**
     * @return a new data collector.
     */
    DataCollector injectDataCollector();

    /**
     * @return a new data post processor.
     */
    DataPostProcessor injectDataPostProcessor();

    /**
     * @return a flag indicating if Javascript is enabled.
     */
    boolean injectJavascriptEnabled();

    /**
     * @return the maximum number of threads to use.
     */
    Integer injectMaxThreads();

    /**
     * @return a new name string parser.
     */
    NameStringParser injectNameStringParser();

    /**
     * @return a new rivalry data.
     */
    RivalryData injectRivalryData();

    /**
     * @return a new value string parser.
     */
    ValueStringParser<?> injectValueStringParser();
}
