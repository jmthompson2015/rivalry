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
 * Defines methods required by a data collector.
 */
public interface DataCollector
{
    /**
     * Fetch data, process it, and load into the given rivalry data object.
     * 
     * @param dcSpec Data collector specification.
     * @param username Username.
     * @param password Password.
     * @param rivalryData Rivalry data.
     */
    void fetchData(DCSpec dcSpec, String username, String password,
            RivalryData rivalryData);
}
