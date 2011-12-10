//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import org.rivalry.core.model.RivalryData;

/**
 * Defines methods required by a rivalry data post processor.
 */
public interface DataPostProcessor
{
    /**
     * @param rivalryData Rivalry data.
     */
    void postProcess(RivalryData rivalryData);
}
