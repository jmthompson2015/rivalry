//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.rivalry.core.funcprog.ListUtils;
import org.rivalry.core.funcprog.MaxFunction;
import org.rivalry.core.funcprog.MinFunction;
import org.rivalry.core.funcprog.RatingsFunction;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a default implementation of a rivalry data post processor. This
 * implementation computes the minimum and maximum of each candidate's ratings
 * for each criterion, and sorts the rivalry data components.
 */
public class DefaultDataPostProcessor implements DataPostProcessor
{
    /** Candidate comparator. */
    private final Comparator<Candidate> _candidateComparator;

    /** Category comparator. */
    private final Comparator<Category> _categoryComparator;

    /** Criterion comparator. */
    private final Comparator<Criterion> _criterionComparator;

    /**
     * Construct this object.
     */
    public DefaultDataPostProcessor()
    {
        this(null, null, null);
    }

    /**
     * Construct this object with the given parameters.
     * 
     * @param candidateComparator Candidate comparator.
     * @param categoryComparator Category comparator.
     * @param criterionComparator Criterion comparator.
     */
    public DefaultDataPostProcessor(
            final Comparator<Candidate> candidateComparator,
            final Comparator<Category> categoryComparator,
            final Comparator<Criterion> criterionComparator)
    {
        _candidateComparator = candidateComparator;
        _categoryComparator = categoryComparator;
        _criterionComparator = criterionComparator;
    }

    @Override
    public void postProcess(final RivalryData rivalryData)
    {
        final List<Candidate> candidates = rivalryData.getCandidates();
        final List<Criterion> criteria = rivalryData.getCriteria();

        final MinFunction<Double> minFunction = new MinFunction<Double>();
        final MaxFunction<Double> maxFunction = new MaxFunction<Double>();

        for (final Criterion criterion : criteria)
        {
            final RatingsFunction ratingsFunction = new RatingsFunction(
                    criterion);
            final List<Double> ratings = ListUtils.map(candidates,
                    ratingsFunction);
            criterion.setMinimumRating(ListUtils.reduce(ratings, minFunction));
            criterion.setMaximumRating(ListUtils.reduce(ratings, maxFunction));
            criterion.setAutoMinMax(true);
        }

        sortCategories(rivalryData);
        sortCriteria(rivalryData);
        sortCandidates(rivalryData);
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void sortCandidates(final RivalryData rivalryData)
    {
        if (_candidateComparator != null)
        {
            Collections.sort(rivalryData.getCandidates(), _candidateComparator);
        }
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void sortCategories(final RivalryData rivalryData)
    {
        if (_categoryComparator != null)
        {
            Collections.sort(rivalryData.getCategories(), _categoryComparator);
        }
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void sortCriteria(final RivalryData rivalryData)
    {
        if (_criterionComparator != null)
        {
            Collections.sort(rivalryData.getCriteria(), _criterionComparator);
        }
    }
}
