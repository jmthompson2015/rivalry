//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************

package org.rivalry.swingui.util;

import java.security.AccessControlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides some commonly used system utility methods.
 */
public final class SystemUtilities
{
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SystemUtilities.class);

    /**
     * @return true if this is running in an applet environment.
     */
    public boolean isApplet()
    {
        return !isApplication();
    }

    /**
     * @return true if this is running in an application environment.
     * 
     *         <p>
     *         This is kind of a hack. The java runtime name property cannot be
     *         accessed from an applet environment due to security restrictions.
     *         A call for this property causes:
     *         </p>
     * 
     *         <p>
     *         <code>java.security.AccessControlException: access denied
     *          (java.util.PropertyPermission java.runtime.name read)</code>
     *         </p>
     * 
     *         <p>
     *         to be thrown. This approach may fail if the user has monkeyed
     *         with their <tt>java.policy</tt> file and the security permissions
     *         therein.
     *         </p>
     */
    public boolean isApplication()
    {
        boolean answer = true;

        try
        {
            System.getProperty("java.runtime.name");
        }
        catch (final AccessControlException e)
        {
            LOGGER.debug(e.getMessage(), e);
            answer = false;
        }

        return answer;
    }

    /**
     * @return true if this application is running on a Macintosh OS.
     */
    public boolean isMacPlatform()
    {
        boolean answer = false;

        try
        {
            final String osName = System.getProperty("os.name").toLowerCase();
            LOGGER.debug("osName = " + osName);
            answer = (osName.startsWith("mac os"));
        }
        catch (final Exception e)
        {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.debug("answer = " + answer);

        return answer;
    }

    /**
     * @return true if this application is running on a Windows OS.
     */
    public boolean isWindowsPlatform0()
    {
        boolean answer = false;

        try
        {
            final String osName = System.getProperty("os.name").toLowerCase();
            LOGGER.debug("osName = " + osName);
            answer = (osName.startsWith("windows"));
        }
        catch (final Exception e)
        {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.debug("answer = " + answer);

        return answer;
    }
}
