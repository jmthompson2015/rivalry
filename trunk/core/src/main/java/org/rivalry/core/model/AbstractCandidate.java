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
        Double answer = null;
        final Object value = getValue(criterion);

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
        return getValues().get(criterion.getName());
    }

    @Override
    public void putValue(final Criterion criterion, final Object value)
    {
        getValues().put(criterion.getName(), value);
    }
}
