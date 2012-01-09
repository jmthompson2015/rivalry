package org.rivalry.swingui.table;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;

import org.apache.commons.lang.StringUtils;

/**
 * Provides a default implementation of a table user preferences.
 */
public class DefaultTableUserPreferences implements TableUserPreferences
{
    /** Preference prefix. */
    private final String _preferencePrefix;

    /**
     * Construct this object with the given parameter.
     * 
     * @param preferencePrefix Preference prefix.
     */
    public DefaultTableUserPreferences(final String preferencePrefix)
    {
        _preferencePrefix = preferencePrefix;
    }

    @Override
    public void clearColumnVisibility()
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
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

    @Override
    public void clearSortKey()
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
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

    @Override
    public String getPreferencePrefix()
    {
        return _preferencePrefix;
    }

    @Override
    public SortKey getSortKey()
    {
        SortKey answer = null;

        final Integer columnIndex = getSortColumn(_preferencePrefix);

        if (columnIndex != null && columnIndex >= 0)
        {
            final SortOrder sortOrder = getSortOrder(_preferencePrefix);

            if (sortOrder != null)
            {
                answer = new SortKey(columnIndex, sortOrder);
            }
        }

        return answer;
    }

    @Override
    public Boolean isColumnVisible(final String columnName)
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
                getColumnVisibleSuffix());

        return prefs.getBoolean(columnName, Boolean.TRUE);
    }

    @Override
    public void putColumnVisible(final String columnName,
            final Boolean isVisible)
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
                getColumnVisibleSuffix());

        prefs.putBoolean(columnName, isVisible);
    }

    @Override
    public void putSortKey(final SortKey sortKey)
    {
        putSortColumn(_preferencePrefix, sortKey.getColumn());
        putSortOrder(_preferencePrefix, sortKey.getSortOrder());
    }

    /**
     * @return the criterion column visible suffix.
     */
    private String getColumnVisibleSuffix()
    {
        return "/columnVisible";
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
