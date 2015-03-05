//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides tests for the <code>RivalryDataReader</code> class.
 */
public class RivalryDataReaderTest
{
    /**
     * Test the <code>read</code> method.
     *
     * @throws FileNotFoundException if the file is not found.
     */
    @Test
    public void readMacintoshComputers() throws FileNotFoundException
    {
        // Setup.
        final RivalryDataReader rivalryDataReader = new RivalryDataReader();
        final InputStream inputStream = getClass().getResourceAsStream("/MacintoshComputersRivalryData.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);

        // Run.
        final RivalryData result = rivalryDataReader.read(reader);
        // System.out.println("result =\n" + result);

        // Verify.
        assertNotNull(result);
        assertThat(result.getCategories().size(), is(0));
        assertThat(result.getCriteria().size(), is(4));
        assertThat(result.getCandidates().size(), is(1));

        {
            final Criterion criterion = result.getCriteria().get(0);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("processorSpeed"));
            assertThat(criterion.getDescription(), is("Processor Speed (GHz)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Candidate candidate = result.getCandidates().get(0);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Mac Mini Late 2014 1.4 GHz"));
            assertNull(candidate.getDescription());
            assertThat(candidate.getPage(), is("http://store.apple.com/us/buy-mac/mac-mini"));
        }
    }
}
