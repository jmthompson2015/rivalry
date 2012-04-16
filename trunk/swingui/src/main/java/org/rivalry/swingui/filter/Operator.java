package org.rivalry.swingui.filter;

import org.apache.commons.lang.StringUtils;

/**
 * Provides an enumeration of operator types.
 */
public enum Operator
{
    /** Contains. */
    CONTAINS("contains", "contains"),

    /** Does not contain. */
    DOES_NOT_CONTAIN("does not contain", "!contains"),

    /** Equals. */
    EQ("is", "="),

    /** Not equals. */
    NE("is not", "\u2260"),

    /** Greater than. */
    GT("is greater than", ">"),

    /** Less than. */
    LT("is less than", "<"),

    /** Greater than or equal. */
    GE("is greater than or equal", "\u2265"),

    /** Less than or equal. */
    LE("is less than or equal", "\u2264");

    /**
     * @param displayName Display name.
     * 
     * @return the operator which matches the given parameter.
     */
    public static Operator valueOfDisplayName(final String displayName)
    {
        Operator answer = null;

        if (StringUtils.isNotEmpty(displayName))
        {
            final int size = values().length;

            for (int i = 0; (answer == null) && (i < size); i++)
            {
                final Operator operator = values()[i];

                if (displayName.equals(operator.getDisplayName()))
                {
                    answer = operator;
                }
            }
        }

        return answer;
    }

    /**
     * @param displaySymbol Display symbol.
     * 
     * @return the operator which matches the given parameter.
     */
    public static Operator valueOfDisplaySymbol(final String displaySymbol)
    {
        Operator answer = null;

        if (StringUtils.isNotEmpty(displaySymbol))
        {
            final int size = values().length;

            for (int i = 0; (answer == null) && (i < size); i++)
            {
                final Operator operator = values()[i];

                if (displaySymbol.equals(operator.getDisplaySymbol()))
                {
                    answer = operator;
                }
            }
        }

        return answer;
    }

    /** Display name. */
    private final String _displayName;

    /** Display symbol. */
    private final String _displaySymbol;

    /**
     * Construct this object with the given parameters.
     * 
     * @param displayName Display name.
     * @param displaySymbol Display symbol.
     */
    private Operator(final String displayName, final String displaySymbol)
    {
        _displayName = displayName;
        _displaySymbol = displaySymbol;
    }

    /**
     * @return Return displayName.
     */
    public String getDisplayName()
    {
        return _displayName;
    }

    /**
     * @return the displaySymbol
     */
    public String getDisplaySymbol()
    {
        return _displaySymbol;
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString()
    {
        String answer = null;

        if (StringUtils.isNotEmpty(_displayName))
        {
            answer = _displayName;
        }
        else if (StringUtils.isNotEmpty(_displaySymbol))
        {
            answer = _displaySymbol;
        }
        else
        {
            answer = super.toString();
        }

        return answer;
    }
}
