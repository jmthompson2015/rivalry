//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import org.rivalry.core.model.DefaultCandidate;

/**
 * Provides a candidate for Illyriad.
 */
public class IllyriadCandidate extends DefaultCandidate
{
    /** Town ID. */
    private Long _townId;

    /**
     * @return the townId
     */
    public Long getTownId()
    {
        return _townId;
    }

    /**
     * @param townId the townId to set
     */
    public void setTownId(final Long townId)
    {
        _townId = townId;
    }
}
