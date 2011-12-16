package org.rivalry.core.util;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;

import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.Criterion;

/**
 * Provides convenience methods for managing user preferences.
 */
public class UserPreferences
{
    /**
     * Clear all column visibility user preferences.
     * 
     * @param preferencePrefix Preference prefix.
     */
    public void clearColumnVisibility(final String preferencePrefix)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getColumnVisibleSuffix());

        try
        {
            prefs.clear();
        }
        catch (final BackingStoreException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clear all criterion column visibility user preferences.
     * 
     * @param preferencePrefix Preference prefix.
     */
    public void clearCriterionWeights(final String preferencePrefix)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getCriterionWeightSuffix());

        try
        {
            prefs.clear();
        }
        catch (final BackingStoreException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clear all sort column user preferences.
     * 
     * @param preferencePrefix Preference prefix.
     */
    public void clearSortKey(final String preferencePrefix)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getSortKeySuffix());

        try
        {
            prefs.clear();
        }
        catch (final BackingStoreException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param criterion Criterion.
     * 
     * @return the weight for the given parameters.
     */
    public Integer getCriterionWeight(final String preferencePrefix,
            final Criterion criterion)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getCriterionWeightSuffix());

        return prefs.getInt(criterion.getName(), 0);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * 
     * @return the sort key.
     */
    public SortKey getSortKey(final String preferencePrefix)
    {
        SortKey answer = null;

        final Integer columnIndex = getSortColumn(preferencePrefix);

        if (columnIndex != null && columnIndex >= 0)
        {
            final SortOrder sortOrder = getSortOrder(preferencePrefix);

            if (sortOrder != null)
            {
                answer = new SortKey(columnIndex, sortOrder);
            }
        }

        return answer;
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param columnName Column name.
     * 
     * @return true if the column is visible.
     */
    public Boolean isColumnVisible(final String preferencePrefix,
            final String columnName)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getColumnVisibleSuffix());

        return prefs.getBoolean(columnName, Boolean.TRUE);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param columnName Column name.
     * @param isVisible Flag indicating column visibility.
     */
    public void putColumnVisible(final String preferencePrefix,
            final String columnName, final Boolean isVisible)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getColumnVisibleSuffix());

        prefs.putBoolean(columnName, isVisible);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param criterion Criterion.
     * @param weight Weight.
     */
    public void putCriterionWeight(final String preferencePrefix,
            final Criterion criterion, final Integer weight)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getCriterionWeightSuffix());

        prefs.putInt(criterion.getName(), weight);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param sortKey Sort key.
     */
    public void putSortKey(final String preferencePrefix, final SortKey sortKey)
    {
        putSortColumn(preferencePrefix, sortKey.getColumn());
        putSortOrder(preferencePrefix, sortKey.getSortOrder());
    }

    /**
     * @return the criterion column visible suffix.
     */
    private String getColumnVisibleSuffix()
    {
        return "/columnVisible";
    }

    /**
     * @return the criterion to weight suffix.
     */
    private String getCriterionWeightSuffix()
    {
        return "/criterionWeight";
    }

    /**
     * @param preferencePrefix Preferences prefix.
     * @param preferenceSuffix Preferences suffix.
     * 
     * @return preferences object.
     */
    private Preferences getPreferences(final String preferencePrefix,
            final String preferenceSuffix)
    {
        final String pathName = getClass().getName() + "/" + preferencePrefix
                + preferenceSuffix;

        return Preferences.userRoot().node(pathName);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * 
     * @return the sort column name.
     */
    private Integer getSortColumn(final String preferencePrefix)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getSortColumnSuffix());

        return prefs.getInt(getSortColumnKey(), -1);
    }

    /**
     * @return sort column key.
     */
    private String getSortColumnKey()
    {
        return "sortColumn";
    }

    /**
     * @return sort column suffix.
     */
    private String getSortColumnSuffix()
    {
        return "";
    }

    /**
     * @return sort column suffix.
     */
    private String getSortKeySuffix()
    {
        return "";
    }

    /**
     * @param preferencePrefix Preference prefix.
     * 
     * @return the sort column name.
     */
    private SortOrder getSortOrder(final String preferencePrefix)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getSortOrderSuffix());
        final String sortOrderString = prefs.get(getSortOrderKey(), null);

        SortOrder answer = null;

        if (StringUtils.isNotEmpty(sortOrderString))
        {
            answer = SortOrder.valueOf(sortOrderString);
        }

        return answer;
    }

    /**
     * @return sort order key.
     */
    private String getSortOrderKey()
    {
        return "sortOrder";
    }

    /**
     * @return sort order suffix.
     */
    private String getSortOrderSuffix()
    {
        return "";
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param columnIndex Column index.
     */
    private void putSortColumn(final String preferencePrefix,
            final Integer columnIndex)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getSortColumnSuffix());

        prefs.putInt(getSortColumnKey(), columnIndex);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param sortOrder Sort order.
     */
    private void putSortOrder(final String preferencePrefix,
            final SortOrder sortOrder)
    {
        final Preferences prefs = getPreferences(preferencePrefix,
                getSortOrderSuffix());

        prefs.put(getSortOrderKey(), sortOrder.name());
    }
}
