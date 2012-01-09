package org.rivalry.core.util;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.rivalry.core.model.Criterion;

/**
 * Provides convenience methods for managing user preferences.
 */
public class UserPreferences
{
    /** Preference prefix. */
    private final String _preferencePrefix;

    /**
     * Construct this object with the given parameter.
     * 
     * @param preferencePrefix Preference prefix.
     */
    public UserPreferences(final String preferencePrefix)
    {
        _preferencePrefix = preferencePrefix;
    }

    /**
     * Clear all criterion column visibility user preferences.
     */
    public void clearCriterionWeights()
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
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
     * @param criterion Criterion.
     * 
     * @return the weight for the given parameters.
     */
    public Integer getCriterionWeight(final Criterion criterion)
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
                getCriterionWeightSuffix());

        return prefs.getInt(criterion.getName(), 0);
    }

    /**
     * @param criterion Criterion.
     * @param weight Weight.
     */
    public void putCriterionWeight(final Criterion criterion,
            final Integer weight)
    {
        final Preferences prefs = getPreferences(_preferencePrefix,
                getCriterionWeightSuffix());

        prefs.putInt(criterion.getName(), weight);
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
}
