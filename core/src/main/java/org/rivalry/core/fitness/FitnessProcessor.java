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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

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
     * Compute fitness scores of all the candidates.
     * 
     * @param candidates Candidates.
     * 
     * @return a map of candidate to fitness.
     */
    public Map<Candidate, Double> computeCandidateFitness(
            final List<Candidate> candidates)
    {
        final Map<Candidate, Double> answer = new LinkedHashMap<Candidate, Double>();

        final List<Criterion> criteria = new ArrayList<Criterion>();

        for (final Candidate candidate : candidates)
        {
            criteria.clear();
            criteria.addAll(candidate.getRatings().keySet());
            final Double fitness = _fitnessFunction.computeFitness(candidate,
                    criteria);
            answer.put(candidate, fitness);
        }

        return answer;
    }
}
