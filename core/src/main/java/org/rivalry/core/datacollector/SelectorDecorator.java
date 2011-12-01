//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * Provides a decorator for a selector that adds to string capability.
 */
public class SelectorDecorator implements Selector
{
    /** Component. */
    private Selector _component;

    /**
     * Construct this object with the given parameter.
     * 
     * @param component Component.
     */
    public SelectorDecorator(final Selector component)
    {
        _component = component;
    }

    @Override
    public List<Selector> getSelectors()
    {
        return _component.getSelectors();
    }

    @Override
    public SelectorType getType()
    {
        return _component.getType();
    }

    @Override
    public String getValue()
    {
        return _component.getValue();
    }

    @Override
    public void setType(final SelectorType valueIn)
    {
        _component.setType(valueIn);
    }

    @Override
    public void setValue(final String valueIn)
    {
        _component.setValue(valueIn);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(getClass().getName());

        sb.append(" [");
        sb.append("type=").append(getType());
        sb.append(", value=").append(getValue());
        if (CollectionUtils.isNotEmpty(getSelectors()))
        {
            sb.append(", selectors=\n");
            for (final Selector selector : getSelectors())
            {
                sb.append("    ").append(selector.toString()).append("\n");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
