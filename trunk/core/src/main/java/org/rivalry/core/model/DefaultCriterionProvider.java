//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import org.rivalry.core.util.AbstractProvider;

/**
 * Provides a default criterion provider.
 */
public class DefaultCriterionProvider extends AbstractProvider<Criterion>
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object.
     */
    public DefaultCriterionProvider()
    {
        super();
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param exemplar Exemplar.
     */
    public DefaultCriterionProvider(final DefaultCriterion exemplar)
    {
        super(exemplar);
    }

    @Override
    public Criterion newInstance()
    {
        final Criterion answer = super.newInstance();

        answer.setName(getExemplar().getName());
        answer.setDescription(getExemplar().getDescription());

        return answer;
    }

    @Override
    protected Criterion createObject()
    {
        return new DefaultCriterion();
    }
}
