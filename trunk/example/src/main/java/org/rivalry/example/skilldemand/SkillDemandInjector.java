package org.rivalry.example.skilldemand;

import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides a data collector injector for skill demand.
 */
public class SkillDemandInjector extends DefaultDataCollectorInjector
{
    @Override
    public ValueStringParser injectValueStringParser()
    {
        return new SkillDemandValueStringParser();
    }
}
