package org.rivalry.swingui.table;

import javax.swing.RowSorter.SortKey;

/**
 * Defines methods required for user preferences of a table.
 */
public interface TableUserPreferences
{
    /**
     * Clear all column visibility user preferences.
     */
    void clearColumnVisibility();

    /**
     * Clear all sort column user preferences.
     */
    void clearSortKey();

    /**
     * @return the preferencePrefix
     */
    String getPreferencePrefix();

    /**
     * @return the sort key.
     */
    SortKey getSortKey();

    /**
     * @param columnName Column name.
     * 
     * @return true if the column is visible.
     */
    Boolean isColumnVisible(final String columnName);

    /**
     * @param columnName Column name.
     * @param isVisible Flag indicating column visibility.
     */
    void putColumnVisible(final String columnName, final Boolean isVisible);

    /**
     * @param sortKey Sort key.
     */
    void putSortKey(final SortKey sortKey);
}
