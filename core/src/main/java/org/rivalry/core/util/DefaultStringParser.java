package org.rivalry.core.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a default implementation of a string parser.
 */
public class DefaultStringParser implements StringParser
{
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultStringParser.class);

    @Override
    public final Double parseDouble(final String valueString)
    {
        Double answer = null;

        if (StringUtils.isNotEmpty(valueString))
        {
            final String myString = prepareValueString(valueString);

            answer = convertString(myString);
        }

        return answer;
    }

    /**
     * @param valueString Value string.
     * 
     * @return a double parsed from the given parameter, if possible.
     */
    protected Double convertString(final String valueString)
    {
        Double answer = null;

        String myString = valueString;

        final boolean isPercent = isPercent(myString);

        if (isPercent)
        {
            // Remove percent signs.
            myString = myString.replaceAll("%", "");
        }

        answer = parseDoubleOnly(myString);

        if (isPercent && answer != null)
        {
            answer /= 100.0;
        }

        return answer;
    }

    /**
     * @param valueString Value string.
     * 
     * @return true if the string represents a percentage.
     */
    protected boolean isPercent(final String valueString)
    {
        return valueString.contains("%");
    }

    /**
     * @param valueString Value string.
     * 
     * @return a double parsed from the given parameter, if possible.
     */
    protected Double parseDoubleOnly(final String valueString)
    {
        Double answer = null;

        try
        {
            answer = Double.parseDouble(valueString);
        }
        catch (final NumberFormatException e)
        {
            LOGGER.warn("Unable to parse [" + valueString + "] as a Double.");
        }

        return answer;
    }

    /**
     * Prepare the value string for parsing. Subclass should extend this method
     * to handle special cases.
     * 
     * @param valueString Value string.
     * 
     * @return value string.
     */
    protected String prepareValueString(final String valueString)
    {
        String answer = valueString.trim();

        if (StringUtils.isNotEmpty(answer))
        {
            // Remove dollar signs and commas.
            answer = answer.replaceAll("[$,]", "");
        }

        return answer;
    }

}
