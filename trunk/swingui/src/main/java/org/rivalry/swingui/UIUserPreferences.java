package org.rivalry.swingui;

/**
 * Defines methods required by a user interface user preferences.
 */
public interface UIUserPreferences
{
    /**
     * Clear all look and feel user preferences.
     */
    void clearLookAndFeel();

    /**
     * @return the look and feel.
     */
    LookAndFeel getLookAndFeel();

    /**
     * @param laf laf to set.
     */
    void putLookAndFeel(LookAndFeel laf);
}
