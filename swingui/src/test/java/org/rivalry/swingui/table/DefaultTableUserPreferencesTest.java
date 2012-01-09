package org.rivalry.swingui.table;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.TestData;

/**
 * Provides tests for the <code>DefaultTableUserPreferences</code> class.
 */
public class DefaultTableUserPreferencesTest
{
    /** Preference prefix. */
    private static final String PREF_PREFIX = "test";

    /** User preferences. */
    private final DefaultTableUserPreferences _userPreferences = new DefaultTableUserPreferences(
            PREF_PREFIX);

    /**
     * Test the <code>putColumnVisible()</code> method.
     */
    @Test
    public void putColumnVisible()
    {
        final TestData testData = new TestData();
        final List<Category> categories = testData.createCategories();
        final List<Criterion> criteria = testData.createCriteria(categories);

        final Criterion criterion = criteria.get(0);
        final String columnName = criterion.getName();
        Boolean result = _userPreferences.isColumnVisible(columnName);
        assertNotNull(result);
        assertThat(result, is(Boolean.TRUE));

        _userPreferences.putColumnVisible(columnName, Boolean.FALSE);

        result = _userPreferences.isColumnVisible(columnName);
        assertNotNull(result);
        assertThat(result, is(Boolean.FALSE));
    }

    /**
     * Test the <code>putSortKey()</code> method.
     */
    @Test
    public void putSortKey()
    {
        SortKey result = _userPreferences.getSortKey();
        assertNull(result);

        int columnIndex = 0;
        SortKey sortKey = new SortKey(columnIndex, SortOrder.ASCENDING);
        _userPreferences.putSortKey(sortKey);

        result = _userPreferences.getSortKey();
        assertNotNull(result);
        assertThat(result.getColumn(), is(columnIndex));
        assertThat(result.getSortOrder(), is(SortOrder.ASCENDING));

        columnIndex = 1;
        sortKey = new SortKey(columnIndex, SortOrder.DESCENDING);
        _userPreferences.putSortKey(sortKey);

        result = _userPreferences.getSortKey();
        assertNotNull(result);
        assertThat(result.getColumn(), is(columnIndex));
        assertThat(result.getSortOrder(), is(SortOrder.DESCENDING));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        _userPreferences.clearColumnVisibility();
        _userPreferences.clearSortKey();
    }

    /**
     * Tear down the test.
     */
    @After
    public void tearDown()
    {
        _userPreferences.clearColumnVisibility();
        _userPreferences.clearSortKey();
    }
}
