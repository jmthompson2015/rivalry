package org.rivalry.swingui.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>FilterClause</code> class.
 */
public class CandidateFilterTest
{
    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesGT()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion0 = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.GT;

        final FilterClause filterClause0 = new FilterClause(criterion0, operator, 1.0);
        final CandidateFilter filter = new CandidateFilter();
        filter.add(filterClause0);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filter.passes(candidate);
        assertTrue(result);

        final Criterion criterion1 = rivalryData.getCriteria().get(1);
        final FilterClause filterClause1 = new FilterClause(criterion1, operator, 1.1);
        filter.add(filterClause1);
        result = filter.passes(candidate);
        assertTrue(result);

        final Criterion criterion2 = rivalryData.getCriteria().get(2);
        FilterClause filterClause2 = new FilterClause(criterion2, operator, 1.2);
        filter.add(filterClause2);
        result = filter.passes(candidate);
        assertTrue(result);

        filter.remove(filterClause2);
        filterClause2 = new FilterClause(criterion2, operator, 1.3);
        filter.add(filterClause2);
        result = filter.passes(candidate);
        assertFalse(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesLT()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion0 = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.LT;

        final FilterClause filterClause0 = new FilterClause(criterion0, operator, 1.2);
        final CandidateFilter filter = new CandidateFilter();
        filter.add(filterClause0);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filter.passes(candidate);
        assertTrue(result);

        final Criterion criterion1 = rivalryData.getCriteria().get(1);
        final FilterClause filterClause1 = new FilterClause(criterion1, operator, 1.3);
        filter.add(filterClause1);
        result = filter.passes(candidate);
        assertTrue(result);

        final Criterion criterion2 = rivalryData.getCriteria().get(2);
        FilterClause filterClause2 = new FilterClause(criterion2, operator, 1.4);
        filter.add(filterClause2);
        result = filter.passes(candidate);
        assertTrue(result);

        filter.remove(filterClause2);
        filterClause2 = new FilterClause(criterion2, operator, 1.3);
        filter.add(filterClause2);
        result = filter.passes(candidate);
        assertFalse(result);
    }
}
