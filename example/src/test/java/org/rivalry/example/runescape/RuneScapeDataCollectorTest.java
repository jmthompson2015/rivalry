package org.rivalry.example.runescape;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;

import org.junit.Ignore;
import org.junit.Test;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultRivalryData;
import org.rivalry.core.model.RivalryData;

/**
 * Provides tests for the <code>RuneScapeDataCollector</code> class.
 */
public class RuneScapeDataCollectorTest
{
    /** Data post processor. */
    private final DataPostProcessor _dataPostProcessor = new RuneScapeDataPostProcessor();

    /** Data collector. */
    private final RuneScapeDataCollector _dataCollector = new RuneScapeDataCollector(_dataPostProcessor);

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchData()
    {
        final DCSpec dcSpec = null;
        final String username = "SalsRealm";
        final String password = null;
        final RivalryData rivalryData = new DefaultRivalryData();

        _dataCollector.fetchData(dcSpec, username, password, rivalryData);

        assertFalse(rivalryData.getCandidates().isEmpty());
        verifyData(rivalryData, Item.GOLD_ORE);
        verifyData(rivalryData, Item.GOLD_BAR);
        verifyData(rivalryData, Item.UNCUT_EMERALD);
        verifyData(rivalryData, Item.EMERALD);
        verifyData(rivalryData, Item.EMERALD_RING);
        verifyData(rivalryData, Item.RING_OF_DUELLING);

        if (!rivalryData.getCandidates().isEmpty())
        {
            final GrandExchange grandExchange = new RivalryDataGrandExchange(rivalryData);
            final Reporter reporter = new Reporter();
            final StringWriter writer = new StringWriter();
            reporter.reportPremiums(writer, grandExchange);
            System.out.println(writer.toString());
        }
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     */
    private void verifyData(final RivalryData rivalryData, final Item item)
    {
        final Candidate candidate = rivalryData.findCandidateByName(item.getName());
        assertNotNull(candidate);
        final Criterion criterion = rivalryData.findCriterionByName("value");
        assertNotNull(candidate.getRating(criterion));
    }
}
