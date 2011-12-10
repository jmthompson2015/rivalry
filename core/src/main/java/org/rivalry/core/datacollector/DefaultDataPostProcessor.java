//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.List;

import org.rivalry.core.funcprog.ListUtils;
import org.rivalry.core.funcprog.MaxFunction;
import org.rivalry.core.funcprog.MinFunction;
import org.rivalry.core.funcprog.RatingsFunction;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a default implementation of a rivalry data post processor. This
 * implementation computes the minimum and maximum of each candidate's ratings
 * for each criterion.
 */
public class DefaultDataPostProcessor implements DataPostProcessor
{
    @Override
    public void postProcess(final RivalryData rivalryData)
    {
        final List<Candidate> candidates = rivalryData.getCandidatesList();
        final List<Criterion> criteria = rivalryData.getCriteriaList();

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
    }
}
