package org.rivalry.example.runescape;

import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for RuneScape.
 */
public class RuneScapeInjector extends DefaultDataCollectorInjector
{
    @Override
    public DataCollector injectDataCollector()
    {
        return new RuneScapeDataCollector(injectDataPostProcessor());
    }

    @Override
    public DataPostProcessor injectDataPostProcessor()
    {
        return new RuneScapeDataPostProcessor();
    }

    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setDescription("RuneScape item data \u00A9 2002 - 2011 Sal's Realm of RuneScape."
                + "\nAll Rights Reserved. Used without permission.");
        answer.setPreferencePrefix("runeScape");

        return answer;
    }
}
