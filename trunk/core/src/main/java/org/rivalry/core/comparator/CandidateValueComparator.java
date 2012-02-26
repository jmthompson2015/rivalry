//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.comparator;

import java.util.Comparator;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

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

        final Object value0 = candidate0.getValue(_criterion);
        final Object value1 = candidate1.getValue(_criterion);

        if (value0 == value1)
        {
            // Same object or both null.
            answer = 0;
        }
        else if (value0 == null)
        {
            // value0 == null && value1 != null
            answer = 1;
        }
        else
        {
            if (value1 == null)
            {
                // value0 !=null && value1 == null
                answer = -1;
            }
            else
            {
                if (value0 instanceof Number && value1 instanceof Number)
                {
                    final Double myValue0 = Double.parseDouble(value0
                            .toString());
                    final Double myValue1 = Double.parseDouble(value1
                            .toString());
                    answer = myValue0.compareTo(myValue1);
                }
                else if (value0 instanceof Comparable)
                {
                    @SuppressWarnings("rawtypes")
                    final Comparable myValue0 = (Comparable)value0;
                    @SuppressWarnings("unchecked")
                    final int comp = myValue0.compareTo(value1);
                    answer = comp;
                }
            }
        }

        return answer;
    }
}
