package org.rivalry.swingui;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Provides user interface user preferences.
 */
public class DefaultUIUserPreferences implements UIUserPreferences
{
    @Override
    public void clearLookAndFeel()
    {
        final Preferences prefs = getPreferences(getPreferencePrefix(),
                getLookAndFeelSuffix());

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
    public LookAndFeel getLookAndFeel()
    {
        final Preferences prefs = getPreferences(getPreferencePrefix(),
                getLookAndFeelSuffix());
        final String name = prefs.get(getLookAndFeelKey(), null);
        LookAndFeel answer = LookAndFeel.findByName(name);

        if (answer == null)
        {
            answer = LookAndFeel.getDefaultLookAndFeel();
        }

        return answer;
    }

    @Override
    public void putLookAndFeel(final LookAndFeel laf)
    {
        if (laf != null)
        {
            final Preferences prefs = getPreferences(getPreferencePrefix(),
                    getLookAndFeelSuffix());

            prefs.put(getLookAndFeelKey(), laf.getName());
        }
    }

    /**
     * @return the look and feel key.
     */
    private String getLookAndFeelKey()
    {
        return "lookAndFeel";
    }

    /**
     * @return the look and feel suffix.
     */
    private String getLookAndFeelSuffix()
    {
        return "";
    }

    /**
     * @return a preferences prefix.
     */
    private String getPreferencePrefix()
    {
        return "rivalryUI";
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
}
