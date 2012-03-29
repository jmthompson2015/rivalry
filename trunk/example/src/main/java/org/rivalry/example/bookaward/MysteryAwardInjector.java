package org.rivalry.example.bookaward;

import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for mystery book award.
 */
public class MysteryAwardInjector extends DefaultDataCollectorInjector
{
    @Override
    public DataCollector injectDataCollector()
    {
        return new MysteryAwardDataCollector();
    }

    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setDescription("Mystery Book Awards data \u00A9 2005-2012 Omnimystery, a Family of Mystery Websites."
                + "\nAll rights reserved. Used without permission.");
        answer.setPreferencePrefix("mysteryBookAward");

        return answer;
    }
}
