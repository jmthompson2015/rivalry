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

/**
 * Provides a default implementation of a candidate comparator which compares by
 * name.
 */
public class DefaultCandidateComparator implements Comparator<Candidate>
{
    @Override
    public int compare(final Candidate element0, final Candidate element1)
    {
        int answer;

        if (element0 == element1)
        {
            answer = 0;
        }
        else if (element0 == null)
        {
            answer = 1;
        }
        else if (element1 == null)
        {
            answer = -1;
        }
        else
        {
            final String name0 = element0.getName();
            final String name1 = element1.getName();

            answer = name0.compareTo(name1);
        }

        return answer;
    }
}
