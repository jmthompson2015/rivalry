//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.Comparator;

import org.rivalry.core.comparator.DefaultCandidateComparator;
import org.rivalry.core.comparator.DefaultCategoryComparator;
import org.rivalry.core.comparator.DefaultCriterionComparator;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidateProvider;
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.DefaultRivalryData;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Provides a default implementation of a data collector injector.
 */
public class DefaultDataCollectorInjector implements DataCollectorInjector
{
    @Override
    public Comparator<Candidate> injectCandidateComparator()
    {
        return new DefaultCandidateComparator();
    }

    @Override
    public Provider<Candidate> injectCandidateProvider()
    {
        return new DefaultCandidateProvider();
    }

    @Override
    public Comparator<Category> injectCategoryComparator()
    {
        return new DefaultCategoryComparator();
    }

    @Override
    public Provider<Category> injectCategoryProvider()
    {
        return new DefaultCategoryProvider();
    }

    @Override
    public Comparator<Criterion> injectCriterionComparator()
    {
        return new DefaultCriterionComparator();
    }

    @Override
    public Provider<Criterion> injectCriterionProvider()
    {
        return new DefaultCriterionProvider();
    }

    @Override
    public DataCollector injectDataCollector()
    {
        final boolean isJavascriptEnabled = injectIsJavascriptEnabled();
        final Integer maxThreads = injectMaxThreads();
        final NameStringParser nameStringParser = injectNameStringParser();
        final ValueStringParser<?> valueStringParser = injectValueStringParser();
        final Provider<Candidate> candidateProvider = injectCandidateProvider();
        final Provider<Category> categoryProvider = injectCategoryProvider();
        final Provider<Criterion> criterionProvider = injectCriterionProvider();
        final DataPostProcessor dataPostProcessor = injectDataPostProcessor();
        final boolean isAverageCandidateCreated = injectIsAverageCandidateCreated();
        final boolean isMedianCandidateCreated = injectIsMedianCandidateCreated();

        final DataCollector answer = new DefaultDataCollector(isJavascriptEnabled, maxThreads, nameStringParser,
                valueStringParser, candidateProvider, categoryProvider, criterionProvider, dataPostProcessor,
                isAverageCandidateCreated, isMedianCandidateCreated);

        return answer;
    }

    @Override
    public DataPostProcessor injectDataPostProcessor()
    {
        return new DefaultDataPostProcessor(injectCandidateComparator(), injectCategoryComparator(),
                injectCriterionComparator());
    }

    @Override
    public boolean injectIsAverageCandidateCreated()
    {
        return false;
    }

    @Override
    public boolean injectIsJavascriptEnabled()
    {
        return false;
    }

    @Override
    public boolean injectIsMedianCandidateCreated()
    {
        return false;
    }

    @Override
    public Integer injectMaxThreads()
    {
        return 1;
    }

    @Override
    public NameStringParser injectNameStringParser()
    {
        return new DefaultNameStringParser();
    }

    @Override
    public RivalryData injectRivalryData()
    {
        return new DefaultRivalryData();
    }

    @Override
    public ValueStringParser<?> injectValueStringParser()
    {
        return new DefaultValueStringParser();
    }
}
