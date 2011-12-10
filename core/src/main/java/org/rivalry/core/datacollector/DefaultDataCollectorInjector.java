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
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Provides a default implementation of a data collector injector.
 */
public class DefaultDataCollectorInjector implements DataCollectorInjector
{
    @Override
    public Provider<Category> injectCategoryProvider()
    {
        return new DefaultCategoryProvider();
    }

    @Override
    public Provider<Criterion> injectCriterionProvider()
    {
        return new DefaultCriterionProvider();
    }

    @Override
    public DataCollector injectDataCollector()
    {
        final NameStringParser nameStringParser = injectNameStringParser();
        final ValueStringParser valueStringParser = injectValueStringParser();
        final Provider<Category> categoryProvider = injectCategoryProvider();
        final Provider<Criterion> criterionProvider = injectCriterionProvider();
        final DataPostProcessor dataPostProcessor = injectDataPostProcessor();

        return new DefaultDataCollector(nameStringParser, valueStringParser,
                categoryProvider, criterionProvider, dataPostProcessor);
    }

    @Override
    public DataPostProcessor injectDataPostProcessor()
    {
        return new DefaultDataPostProcessor();
    }

    @Override
    public NameStringParser injectNameStringParser()
    {
        return new DefaultNameStringParser();
    }

    @Override
    public RivalryData injectRivalryData()
    {
        return new RivalryData();
    }

    @Override
    public ValueStringParser injectValueStringParser()
    {
        return new DefaultValueStringParser();
    }
}
