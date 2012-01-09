package org.rivalry.core.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>UserPreferences</code> class.
 */
public class UserPreferencesTest
{
    /** Preference prefix. */
    private static final String PREF_PREFIX = "test";

    /** User preferences. */
    private final UserPreferences _userPreferences = new UserPreferences(PREF_PREFIX);

    /**
     * Test the <code>putCriterionWeight()</code> method.
     */
    @Test
    public void putCriterionWeight()
    {
        final TestData testData = new TestData();
        final List<Category> categories = testData.createCategories();
        final List<Criterion> criteria = testData.createCriteria(categories);

        final Criterion criterion = criteria.get(0);
        Integer result = _userPreferences.getCriterionWeight(
                criterion);
        assertNotNull(result);
        assertThat(result, is(0));

        _userPreferences.putCriterionWeight( criterion, 1);

        result = _userPreferences.getCriterionWeight( criterion);
        assertNotNull(result);
        assertThat(result, is(1));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        _userPreferences.clearCriterionWeights();
    }

    /**
     * Tear down the test.
     */
    @After
    public void tearDown()
    {
        _userPreferences.clearCriterionWeights();
    }
}
