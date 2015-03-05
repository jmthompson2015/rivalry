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
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.UserPreferences;

/**
 * Provides a fitness function implemented as a weighted sum of ratings.
 */
public class WeightedSumFitnessFunction implements FitnessFunction
{
    /** Rivalry data. */
    private final RivalryData rivalryData;

    /** User preferences. */
    private final UserPreferences userPreferences;

    /**
     * Construct this object with the given parameter.
     *
     * @param preferencePrefix Preference prefix.
     * @param rivalryData Rivalry data.
     */
    @SuppressWarnings("hiding")
    public WeightedSumFitnessFunction(final String preferencePrefix, final RivalryData rivalryData)
    {
        this.userPreferences = new UserPreferences(preferencePrefix);
        this.rivalryData = rivalryData;
    }

    @Override
    public Double computeFitness(final Candidate candidate)
    {
        Double answer = null;

        if (candidate != null)
        {
            answer = 0.0;

            final List<String> criteriaNames = new ArrayList<String>();

            if (candidate.getValues() != null)
            {
                criteriaNames.addAll(candidate.getValues().keySet());
            }

            for (final String criterionName : criteriaNames)
            {
                final Criterion criterion = rivalryData.findCriterionByName(criterionName);
                final Double min = criterion.getMinimumRating();
                final Double max = criterion.getMaximumRating();
                final Double rating = candidate.getRating(criterionName);
                final Integer weight = getWeight(criterion);

                if ((rating != null) && (weight != null))
                {
                    if ((min != null) && (max != null) && (min < max))
                    {
                        answer += (weight * (rating - min)) / (max - min);
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
        return userPreferences.getCriterionWeight(criterion);
    }

    /**
     * @param criterion Criterion.
     * @param weight Weight.
     */
    public void putWeight(final Criterion criterion, final Integer weight)
    {
        userPreferences.putCriterionWeight(criterion, weight);
    }
}
