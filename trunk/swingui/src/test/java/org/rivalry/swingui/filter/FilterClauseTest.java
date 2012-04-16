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
public class FilterClauseTest
{
    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesCONTAINS()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.CONTAINS;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, "abc");
        final FilterClause filterClause11 = new FilterClause(criterion, operator, "def");
        final FilterClause filterClause12 = new FilterClause(criterion, operator, "ghi");

        final Candidate candidate = rivalryData.getCandidates().get(0);
        candidate.putValue(criterion, "bcdefgh");

        boolean result = filterClause10.passes(candidate);
        assertFalse(result);

        result = filterClause11.passes(candidate);
        assertTrue(result);

        result = filterClause12.passes(candidate);
        assertFalse(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesDOES_NOT_CONTAIN()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.DOES_NOT_CONTAIN;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, "abc");
        final FilterClause filterClause11 = new FilterClause(criterion, operator, "def");
        final FilterClause filterClause12 = new FilterClause(criterion, operator, "ghi");

        final Candidate candidate = rivalryData.getCandidates().get(0);
        candidate.putValue(criterion, "bcdefgh");

        boolean result = filterClause10.passes(candidate);
        assertTrue(result);

        result = filterClause11.passes(candidate);
        assertFalse(result);

        result = filterClause12.passes(candidate);
        assertTrue(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesEQ()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.EQ;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, 1.0);
        final FilterClause filterClause11 = new FilterClause(criterion, operator, 1.1);
        final FilterClause filterClause12 = new FilterClause(criterion, operator, 1.2);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filterClause10.passes(candidate);
        assertFalse(result);

        result = filterClause11.passes(candidate);
        assertTrue(result);

        result = filterClause12.passes(candidate);
        assertFalse(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesGE()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.GE;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, 1.0);
        final FilterClause filterClause11 = new FilterClause(criterion, operator, 1.1);
        final FilterClause filterClause12 = new FilterClause(criterion, operator, 1.2);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filterClause10.passes(candidate);
        assertTrue(result);

        result = filterClause11.passes(candidate);
        assertTrue(result);

        result = filterClause12.passes(candidate);
        assertFalse(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesGT()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.GT;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, 1.0);
        final FilterClause filterClause11 = new FilterClause(criterion, operator, 1.1);
        final FilterClause filterClause12 = new FilterClause(criterion, operator, 1.2);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filterClause10.passes(candidate);
        assertTrue(result);

        result = filterClause11.passes(candidate);
        assertFalse(result);

        result = filterClause12.passes(candidate);
        assertFalse(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesLE()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.LE;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, 1.0);
        final FilterClause filterClause11 = new FilterClause(criterion, operator, 1.1);
        final FilterClause filterClause12 = new FilterClause(criterion, operator, 1.2);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filterClause10.passes(candidate);
        assertFalse(result);

        result = filterClause11.passes(candidate);
        assertTrue(result);

        result = filterClause12.passes(candidate);
        assertTrue(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesLT()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.LT;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, 1.0);
        final FilterClause filterClause11 = new FilterClause(criterion, operator, 1.1);
        final FilterClause filterClause12 = new FilterClause(criterion, operator, 1.2);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filterClause10.passes(candidate);
        assertFalse(result);

        result = filterClause11.passes(candidate);
        assertFalse(result);

        result = filterClause12.passes(candidate);
        assertTrue(result);
    }

    /**
     * Test the <code>passes()</code> method.
     */
    @Test
    public void passesNE()
    {
        final TestData testData = new TestData();
        final RivalryData rivalryData = testData.createRivalryData();
        final Criterion criterion = rivalryData.getCriteria().get(0);
        final Operator operator = Operator.NE;

        final FilterClause filterClause10 = new FilterClause(criterion, operator, 1.0);
        final FilterClause filterClause11 = new FilterClause(criterion, operator, 1.1);
        final FilterClause filterClause12 = new FilterClause(criterion, operator, 1.2);

        final Candidate candidate = rivalryData.getCandidates().get(0);

        boolean result = filterClause10.passes(candidate);
        assertTrue(result);

        result = filterClause11.passes(candidate);
        assertFalse(result);

        result = filterClause12.passes(candidate);
        assertTrue(result);
    }
}
