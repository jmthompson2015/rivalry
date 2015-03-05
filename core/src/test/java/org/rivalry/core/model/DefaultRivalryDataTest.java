package org.rivalry.core.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>DefaultRivalryData</code> class.
 */
public class DefaultRivalryDataTest
{
    /**
     * Test the <code>getRating()</code> method.
     */
    @Test
    public void getRating()
    {
        // Setup.
        final TestData testData = new TestData();

        // Run.
        final RivalryData result = testData.createRivalryData();

        // Verify.
        assertNotNull(result);
        assertThat(result.findCategoryByName("A"), is(result.getCategories().get(0)));
        assertThat(result.findCriterionByName("a"), is(result.getCriteria().get(0)));
        assertThat(result.findCandidateByName("1"), is(result.getCandidates().get(0)));
    }
}
