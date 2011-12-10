package org.rivalry.core.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>DefaultCandidate</code> class.
 */
public class DefaultCandidateTest
{
    /**
     * Test the <code>getRating()</code> method.
     */
    @Test
    public void getRating()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Candidate candidate0 = rivalryData.getCandidatesList().get(0);

        final Criterion criterionA0 = rivalryData.findCriterionByName("a");
        assertNotNull(criterionA0);

        {
            final Double rating0 = candidate0.getRating(criterionA0);
            assertNotNull(rating0);
            assertThat(rating0, is(1.1));
        }

        // Change the criterion.
        criterionA0.setMinimumRating(1.0);
        criterionA0.setMaximumRating(5.0);

        {
            final Double rating0 = candidate0.getRating(criterionA0);
            assertNotNull(rating0);
            assertThat(rating0, is(1.1));
        }
    }
}
