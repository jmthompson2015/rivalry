//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

/**
 * Provides a base class for a <code>Candidate</code>.
 */
public abstract class AbstractCandidate extends DefaultEntity implements Candidate
{
    /**
     * Construct this object.
     */
    public AbstractCandidate()
    {
        setValues(new CriterionMap());
    }

    @Override
    public Double getRating(final Criterion criterion)
    {
        return getRating(criterion.getName());
    }

    @Override
    public Double getRating(final String criterionName)
    {
        Double answer = null;
        final Object value = getValue(criterionName);

        if (value instanceof Double)
        {
            answer = (Double)value;
        }
        else if (value instanceof Integer)
        {
            answer = ((Integer)value).doubleValue();
        }
        else if (value instanceof Float)
        {
            answer = ((Float)value).doubleValue();
        }
        else if (value instanceof Long)
        {
            answer = ((Long)value).doubleValue();
        }
        else if (value instanceof Short)
        {
            answer = ((Short)value).doubleValue();
        }

        return answer;
    }

    @Override
    public Object getValue(final Criterion criterion)
    {
        return getValue(criterion.getName());
    }

    @Override
    public Object getValue(final String criterionName)
    {
        return getValues().get(criterionName);
    }

    @Override
    public void putValue(final Criterion criterion, final Object value)
    {
        putValue(criterion.getName(), value);
    }

    @Override
    public void putValue(final String criterionName, final Object value)
    {
        getValues().put(criterionName, value);
    }
}
