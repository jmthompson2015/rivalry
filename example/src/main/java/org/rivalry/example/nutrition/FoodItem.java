package org.rivalry.example.nutrition;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;

/**
 * Provides a food item.
 */
public class FoodItem
{
    /** Candidate. */
    private final Candidate _candidate;

    /** Service size. */
    private final ServingSize _servingSize;

    /**
     * Construct this object.
     *
     * @param candidate Candidate.
     * @param servingSize Service size.
     */
    public FoodItem(final Candidate candidate, final ServingSize servingSize)
    {
        _candidate = candidate;
        _servingSize = servingSize;
    }

    /**
     * @return food.
     */
    public Candidate getFood()
    {
        final ServingSize servingSize = (ServingSize)_candidate.getValue(FoodCriterion.SERVING_SIZE);
        final Double servingsPerContainer = (Double)_candidate.getValue(FoodCriterion.SERVINGS_PER_CONTAINER);
        final Double multiplier = servingSize.multiplier(_servingSize, servingsPerContainer);
        // System.out.println("_candidate = " + ToStringBuilder.reflectionToString(_candidate));
        // System.out.println("multiplier = " + multiplier);

        final Candidate answer = new DefaultCandidate();

        answer.setName(_candidate.getName());

        for (final Criterion criterion : FoodCriterion.values())
        {
            // System.out.println("criterion = " + criterion.getName());
            // System.out.println("_candidate.getRating(" + criterion + ") = " + _candidate.getRating(criterion));

            final Double rating = _candidate.getRating(criterion);
            // System.out.println("rating = " + rating);

            if (rating != null)
            {
                final Double value = multiplier * rating;
                answer.putValue(criterion, value);
            }
        }

        return answer;
    }

    /**
     * @return the servingSize
     */
    public ServingSize getServingSize()
    {
        return _servingSize;
    }
}
