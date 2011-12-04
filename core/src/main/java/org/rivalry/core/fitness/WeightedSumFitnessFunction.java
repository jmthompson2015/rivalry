//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.fitness;

import java.util.List;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

/**
 * Provides a fitness function implemented as a weighted sum of ratings.
 */
public class WeightedSumFitnessFunction implements FitnessFunction
{
    @Override
    public Double computeFitness(final Candidate candidate,
            final List<Criterion> criteria)
    {
        Double answer = null;

        if (candidate != null && criteria != null)
        {
            answer = 0.0;

            for (final Criterion criterion : criteria)
            {
                final Double rating = candidate.getRating(criterion);
                final Integer weight = criterion.getWeight();

                if (rating != null && weight != null)
                {
                    answer += rating * weight;
                }
            }

            // System.out.println(candidate.getName() + " fitness = " + answer);
        }

        return answer;
    }
}
