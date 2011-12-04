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
import org.rivalry.core.model.RivalryData;

/**
 * Provides a fitness processor.
 */
public class FitnessProcessor
{
    /** Fitness function. */
    private final FitnessFunction _fitnessFunction;

    /**
     * Construct this object with the given parameter.
     * 
     * @param fitnessFunction Fitness function.
     */
    public FitnessProcessor(final FitnessFunction fitnessFunction)
    {
        _fitnessFunction = fitnessFunction;
    }

    /**
     * Update fitness scores of all the candidates.
     * 
     * @param rivalryData Rivalry data.
     */
    public void updateFitness(final RivalryData rivalryData)
    {
        final List<Criterion> criteria = rivalryData.getCriteriaList();

        for (final Candidate candidate : rivalryData.getCandidatesList())
        {
            final Double fitness = _fitnessFunction.computeFitness(candidate,
                    criteria);
            candidate.setScore(fitness);
        }
    }
}
