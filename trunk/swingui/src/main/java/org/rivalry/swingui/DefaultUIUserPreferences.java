package org.rivalry.swingui;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.swingui.filter.CandidateFilter;
import org.rivalry.swingui.filter.FilterClause;
import org.rivalry.swingui.filter.Operator;

/**
 * Provides user interface user preferences.
 */
public class DefaultUIUserPreferences implements UIUserPreferences
{
    @Override
    public void clearCandidateFilter(final RivalryData rivalryData)
    {
        final Preferences prefs = getPreferences(getPreferencePrefix(rivalryData), getCandidateFilterSuffix());

        try
        {
            prefs.clear();
            prefs.removeNode();
        }
        catch (final BackingStoreException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearLookAndFeel()
    {
        final Preferences prefs = getPreferences(getPreferencePrefix(), getLookAndFeelSuffix());

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
    public CandidateFilter getCandidateFilter(final RivalryData rivalryData)
    {
        CandidateFilter answer = null;

        int index = 0;
        FilterClause filterClause = getFilterClause(rivalryData, index);

        while (filterClause != null)
        {
            if (answer == null)
            {
                answer = new CandidateFilter();
            }

            answer.add(filterClause);

            index++;
            filterClause = getFilterClause(rivalryData, index);
        }

        return answer;
    }

    @Override
    public LookAndFeel getLookAndFeel()
    {
        final Preferences prefs = getPreferences(getPreferencePrefix(), getLookAndFeelSuffix());
        final String name = prefs.get(getLookAndFeelKey(), null);
        LookAndFeel answer = LookAndFeel.findByName(name);

        if (answer == null)
        {
            answer = LookAndFeel.getDefaultLookAndFeel();
        }

        return answer;
    }

    @Override
    public void putCandidateFilter(final RivalryData rivalryData, final CandidateFilter candidateFilter)
    {
        if (candidateFilter != null)
        {
            clearCandidateFilter(rivalryData);

            final int size = candidateFilter.getFilterClauses().size();

            for (int i = 0; i < size; i++)
            {
                final FilterClause filterClause = candidateFilter.getFilterClauses().get(i);
                putFilterClause(rivalryData, i, filterClause);
            }
        }
    }

    @Override
    public void putLookAndFeel(final LookAndFeel laf)
    {
        if (laf != null)
        {
            final Preferences prefs = getPreferences(getPreferencePrefix(), getLookAndFeelSuffix());

            prefs.put(getLookAndFeelKey(), laf.getName());
        }
    }

    /**
     * @param valueString Value string.
     * 
     * @return the given parameter as a Double object if possible, else as a String.
     */
    private Object asDoubleOrString(final String valueString)
    {
        Object answer = null;

        if (StringUtils.isNotEmpty(valueString))
        {
            try
            {
                final Double valueDouble = Double.parseDouble(valueString);
                answer = valueDouble;
            }
            catch (final NumberFormatException ignore)
            {
                // It's not a number, so just use the string.
                answer = valueString;
            }
        }

        return answer;
    }

    /**
     * @return the candidate filter suffix.
     */
    private String getCandidateFilterSuffix()
    {
        return "candidateFilter";
    }

    /**
     * @return the criterion key.
     */
    private String getCriterionKey()
    {
        return "criterion";
    }

    /**
     * @param rivalryData Rivalry data.
     * @param index Filter clause index.
     * 
     * @return a new filter clause.
     */
    private FilterClause getFilterClause(final RivalryData rivalryData, final int index)
    {
        FilterClause answer = null;

        final Preferences prefs = getPreferences(getPreferencePrefix(rivalryData), getFilterClauseSuffix(index));

        final String criterionName = prefs.get(getCriterionKey(), null);
        final Criterion criterion = rivalryData.findCriterionByName(criterionName);

        if (criterion != null)
        {
            final String operatorString = prefs.get(getOperatorKey(), null);
            final String valueString = prefs.get(getValueKey(), null);

            final Operator operator = Operator.valueOfDisplaySymbol(operatorString);
            final Object value = asDoubleOrString(valueString);

            answer = new FilterClause(criterion, operator, value);
        }

        return answer;
    }

    /**
     * @param index Filter clause index.
     * 
     * @return the candidate filter suffix.
     */
    private String getFilterClauseSuffix(final int index)
    {
        return getCandidateFilterSuffix() + "/" + "filterClause" + index;
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
     * @return the operator key.
     */
    private String getOperatorKey()
    {
        return "operator";
    }

    /**
     * @return a preferences prefix.
     */
    private String getPreferencePrefix()
    {
        return "rivalryUI";
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return a preferences prefix.
     */
    private String getPreferencePrefix(final RivalryData rivalryData)
    {
        return getPreferencePrefix() + "/" + rivalryData.getPreferencePrefix();
    }

    /**
     * @param preferencePrefix Preferences prefix.
     * @param preferenceSuffix Preferences suffix.
     * 
     * @return preferences object.
     */
    private Preferences getPreferences(final String preferencePrefix, final String preferenceSuffix)
    {
        final String pathName = getClass().getName() + "/" + preferencePrefix + preferenceSuffix;

        return Preferences.userRoot().node(pathName);
    }

    /**
     * @return the value key.
     */
    private String getValueKey()
    {
        return "value";
    }

    /**
     * @param rivalryData Rivalry data.
     * @param index Filter clause index.
     * @param filterClause Filter clause.
     */
    private void putFilterClause(final RivalryData rivalryData, final int index, final FilterClause filterClause)
    {
        final Preferences prefs = getPreferences(getPreferencePrefix(rivalryData), getFilterClauseSuffix(index));

        prefs.put(getCriterionKey(), filterClause.getCriterion().getName());
        prefs.put(getOperatorKey(), filterClause.getOperator().getDisplaySymbol());
        prefs.put(getValueKey(), filterClause.getValueAsString());
    }
}
