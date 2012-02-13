//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import org.rivalry.core.util.AbstractProvider;

/**
 * Provides a default candidate provider.
 */
public class DefaultCandidateProvider extends AbstractProvider<Candidate>
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object.
     */
    public DefaultCandidateProvider()
    {
        super();
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param exemplar Exemplar.
     */
    public DefaultCandidateProvider(final DefaultCandidate exemplar)
    {
        super(exemplar);
    }

    @Override
    public Candidate newInstance()
    {
        final Candidate answer = super.newInstance();

        answer.setName(getExemplar().getName());
        answer.setDescription(getExemplar().getDescription());

        return answer;
    }

    @Override
    protected Candidate createObject()
    {
        return new DefaultCandidate();
    }
}
