package org.rivalry.swingui;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;
import org.rivalry.swingui.filter.CandidateFilter;
import org.rivalry.swingui.filter.FilterClause;
import org.rivalry.swingui.filter.Operator;

/**
 * Provides tests for the <code>DefaultUIUserPreferences</code> class.
 */
public class DefaultUIUserPreferencesTest
{
    /** User preferences. */
    private final UIUserPreferences _userPreferences = new DefaultUIUserPreferences();

    /**
     * Test the <code>putCandidateFilter()</code> method.
     */
    @Test
    public void putCandidateFilter()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion0 = rivalryData.getCriteria().get(0);
        final Operator operator0 = Operator.LT;
        final Double value0 = 1.0;

        final FilterClause filterClause0 = new FilterClause(criterion0, operator0, value0);
        final CandidateFilter candidateFilter = new CandidateFilter();
        candidateFilter.add(filterClause0);

        _userPreferences.putCandidateFilter(rivalryData, candidateFilter);
        final CandidateFilter result = _userPreferences.getCandidateFilter(rivalryData);
        assertNotNull(result);
        assertThat(result.getFilterClauses().size(), is(1));
        {
            final FilterClause filterClause = result.getFilterClauses().get(0);
            assertNotNull(filterClause);
            assertThat(filterClause.getCriterion(), is(criterion0));
            assertThat(filterClause.getOperator(), is(operator0));
            assertThat((Double)filterClause.getValue(), is(value0));
        }
    }

    /**
     * Test the <code>putLookAndFeel()</code> method.
     */
    @Test
    public void putLookAndFeel()
    {
        final LookAndFeel laf = LookAndFeel.getDefaultLookAndFeel();
        _userPreferences.putLookAndFeel(laf);
        final LookAndFeel result = _userPreferences.getLookAndFeel();
        assertNotNull(result);
        assertThat(result.getName(), is("Metal"));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        _userPreferences.clearLookAndFeel();
    }

    /**
     * Tear down the test.
     */
    @After
    public void tearDown()
    {
        _userPreferences.clearLookAndFeel();
    }
}
