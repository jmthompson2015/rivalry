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
 * Defines methods required by a fitness function.
 */
public interface FitnessFunction
{
    /**
     * @param candidate Candidate.
     * @param criteria Criteria.
     * @return fitness.
     */
    Double computeFitness(Candidate candidate, List<Criterion> criteria);
}
