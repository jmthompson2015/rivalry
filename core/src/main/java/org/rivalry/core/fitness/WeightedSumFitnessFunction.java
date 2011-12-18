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
import java.util.List;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.util.UserPreferences;

/**
 * Provides a fitness function implemented as a weighted sum of ratings.
 */
public class WeightedSumFitnessFunction implements FitnessFunction
{
    /** User preferences. */
    private final UserPreferences _userPreferences = new UserPreferences();

    /** Preference prefix. */
    private final String _prefPrefix;

    /**
     * Construct this object with the given parameter.
     * 
     * @param preferencePrefix Preference prefix.
     */
    public WeightedSumFitnessFunction(final String preferencePrefix)
    {
        _prefPrefix = preferencePrefix;
    }

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
                final Double min = criterion.getMinimumRating();
                final Double max = criterion.getMaximumRating();
                final Double rating = candidate.getRating(criterion);
                final Integer weight = getWeight(criterion);

                if (rating != null && weight != null)
                {
                    if (min != null && max != null && min < max)
                    {
                        answer += weight * (rating - min) / (max - min);
                    }
                    else
                    {
                        answer += rating * weight;
                    }
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
        return _userPreferences.getCriterionWeight(_prefPrefix, criterion);
    }

    /**
     * @param criterion Criterion.
     * @param weight Weight.
     */
    public void putWeight(final Criterion criterion, final Integer weight)
    {
        _userPreferences.putCriterionWeight(_prefPrefix, criterion, weight);
    }
}
