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
import java.util.List;

import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
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
        verifyCategories(result.getCategories());
        verifyCriteria(result.getCriteria());
        verifyCandidates(result);
    }

    /**
     * @param rivalryData Rivalry data.
     */
    private void verifyCandidates(final RivalryData rivalryData)
    {
        final List<Candidate> candidates = rivalryData.getCandidates();
        assertNotNull(candidates);
        assertThat(candidates.size(), is(1));

        {
            final Candidate candidate = candidates.get(0);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Mac Mini Late 2014 1.4 GHz"));
            assertNull(candidate.getDescription());
            assertThat(candidate.getPage(), is("http://store.apple.com/us/buy-mac/mac-mini"));

            /*
             * final CriterionMap map = candidate.getValues(); System.out.println("map.size() = " + map.size()); for
             * (final Entry<String, Object> entry : map.entrySet()) { System.out.println(entry.getKey() + ": " +
             * entry.getValue()); }
             */

            assertThat((String)candidate.getValue(rivalryData.findCriterionByName("release")), is("2014/10"));
            assertThat((Double)candidate.getValue(rivalryData.findCriterionByName("processorSpeed")), is(1.4));
            assertThat((String)candidate.getValue(rivalryData.findCriterionByName("processor")),
                    is("dual-core Intel Core i5"));
            assertThat((Integer)candidate.getValue(rivalryData.findCriterionByName("ram")), is(4));
            assertThat((Integer)candidate.getValue(rivalryData.findCriterionByName("harddrive")), is(500));
            assertThat((Integer)candidate.getValue(rivalryData.findCriterionByName("harddriveSpeed")), is(5400));
            assertNull(candidate.getValue(rivalryData.findCriterionByName("flashStorage")));
            assertThat((String)candidate.getValue(rivalryData.findCriterionByName("graphicsCard")),
                    is("Intel HD Graphics 5000"));
            assertThat((Integer)candidate.getValue(rivalryData.findCriterionByName("graphicsRam")), is(1024));
            assertThat((Integer)candidate.getValue(rivalryData.findCriterionByName("price")), is(499));
            assertThat((Integer)candidate.getValue(rivalryData.findCriterionByName("geekbench3Score")), is(5382));
        }
    }

    /**
     * @param categories Categories.
     */
    private void verifyCategories(final List<Category> categories)
    {
        assertNotNull(categories);
        assertThat(categories.size(), is(0));
    }

    /**
     * @param criteria Criteria.
     */
    private void verifyCriteria(final List<Criterion> criteria)
    {
        assertNotNull(criteria);
        assertThat(criteria.size(), is(11));

        int i = 0;

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("release"));
            assertThat(criterion.getDescription(), is("Release"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("processorSpeed"));
            assertThat(criterion.getDescription(), is("Processor Speed (GHz)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("processor"));
            assertThat(criterion.getDescription(), is("Processor"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("ram"));
            assertThat(criterion.getDescription(), is("RAM (GB)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("harddrive"));
            assertThat(criterion.getDescription(), is("Harddrive (GB)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("harddriveSpeed"));
            assertThat(criterion.getDescription(), is("Harddrive Speed (rpm)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("flashStorage"));
            assertThat(criterion.getDescription(), is("Flash Storage (GB)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("graphicsCard"));
            assertThat(criterion.getDescription(), is("Graphics Card"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("graphicsRam"));
            assertThat(criterion.getDescription(), is("Graphics RAM (MB)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("price"));
            assertThat(criterion.getDescription(), is("Price ($)"));
            assertTrue(criterion.isAutoMinMax());
        }

        {
            final Criterion criterion = criteria.get(i++);
            assertNotNull(criterion);
            assertThat(criterion.getName(), is("geekbench3Score"));
            assertThat(criterion.getDescription(), is("Geekbench 3 Score"));
            assertTrue(criterion.isAutoMinMax());
        }

        assertThat(i, is(11));
    }
}
