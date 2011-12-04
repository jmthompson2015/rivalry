//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.fitness;

import org.rivalry.core.model.Candidate;

/**
 * Defines methods required by a fitness function.
 */
public interface FitnessFunction
{
    /**
     * @param candidate Candidate.
     * 
     * @return fitness.
     */
    Double computeFitness(Candidate candidate);
}
