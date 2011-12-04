//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.fitness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

/**
 * Provides a fitness function implemented as a weighted sum of ratings.
 */
public class WeightedSumFitnessFunction implements FitnessFunction
{
    /** Criterion weights. */
    private final Map<Criterion, Integer> _criterionWeight = new HashMap<Criterion, Integer>();

    @Override
    public Double computeFitness(final Candidate candidate)
    {
        Double answer = null;

        if (candidate != null)
        {
            answer = 0.0;

            final List<Criterion> criteria = new ArrayList<Criterion>();
            criteria.clear();
            criteria.addAll(candidate.getRatings().keySet());

            for (final Criterion criterion : criteria)
            {
                final Double rating = candidate.getRating(criterion);
                final Integer weight = _criterionWeight.get(criterion);

                if (rating != null && weight != null)
                {
                    answer += rating * weight;
                }
            }
        }

        return answer;
    }

    /**
     * @param criterion Criterion.
     * 
     * @return weight.
     */
    public Integer getWeight(final Criterion criterion)
    {
        return _criterionWeight.get(criterion);
    }

    /**
     * @param criterion Criterion.
     * @param weight Weight.
     */
    public void putWeight(final Criterion criterion, final Integer weight)
    {
        _criterionWeight.put(criterion, weight);
    }
}
