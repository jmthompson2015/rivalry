//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.util.Comparator;

/**
 * Provides a comparator for candidate values.
 */
public class CandidateValueComparator implements Comparator<Candidate>
{
    /** Criterion. */
    private Criterion _criterion;

    /**
     * Construct this object with the give parameter.
     * 
     * @param criterion Criterion.
     */
    public CandidateValueComparator(final Criterion criterion)
    {
        _criterion = criterion;
    }

    @Override
    public int compare(final Candidate candidate0, final Candidate candidate1)
    {
        int answer = 0;

        final Object rating0 = candidate0.getValue(_criterion);
        final Object rating1 = candidate1.getValue(_criterion);

        if (rating0 == rating1)
        {
            answer = 0;
        }
        else if (rating0 != null)
        {
            if (rating1 == null)
            {
                answer = -1;
            }
            else
            {
                if (rating0 instanceof Comparable)
                {
                    @SuppressWarnings("rawtypes")
                    final Comparable myRating0 = (Comparable)rating0;
                    @SuppressWarnings("unchecked")
                    final int comp = myRating0.compareTo(rating1);
                    answer = comp;
                }
            }
        }
        else
        {
            answer = 1;
        }

        return answer;
    }
}
