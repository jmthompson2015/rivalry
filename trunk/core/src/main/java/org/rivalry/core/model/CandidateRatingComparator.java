package org.rivalry.core.model;

import java.util.Comparator;

/**
 * Provides a comparator for candidate town ID.
 */
public class CandidateRatingComparator implements Comparator<Candidate>
{
    /** Criterion. */
    private Criterion _criterion;

    /**
     * Construct this object with the give parameter.
     * 
     * @param criterion Criterion.
     */
    public CandidateRatingComparator(final Criterion criterion)
    {
        _criterion = criterion;
    }

    @Override
    public int compare(final Candidate candidate0, final Candidate candidate1)
    {
        final Double rating0 = candidate0.getRating(_criterion);
        final Double rating1 = candidate1.getRating(_criterion);

        return rating1.compareTo(rating0);
    }
}
