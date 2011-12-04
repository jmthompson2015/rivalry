//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.reporter;

import java.io.File;

/**
 * Defines methods required by a reporter.
 */
public interface Reporter
{
    /**
     * Write a report.
     */
    void writeReport();

    /**
     * Write the report to a file.
     * 
     * @param file File.
     */
    void writeToFile(final File file);
}
