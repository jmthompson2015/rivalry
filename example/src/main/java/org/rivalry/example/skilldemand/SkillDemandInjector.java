package org.rivalry.example.skilldemand;

import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data collector injector for skill demand.
 */
public class SkillDemandInjector extends DefaultDataCollectorInjector
{
    @Override
    public RivalryData injectRivalryData()
    {
        final RivalryData answer = super.injectRivalryData();

        answer.setDescription("Jobs data \u00A9 1990-2011 Dice. All rights reserved. Used without permission.");
        answer.setPreferencePrefix("skillDemand");

        return answer;
    }

    @Override
    public ValueStringParser<Integer> injectValueStringParser()
    {
        return new SkillDemandValueStringParser();
    }
}
