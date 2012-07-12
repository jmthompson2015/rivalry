package org.rivalry.example.skilldemand;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides tests for the <code>SkillDemandValueStringParser</code> class.
 */
public class SkillDemandValueStringParserTest
{
    /** Parser. */
    private final ValueStringParser<Integer> _parser = new SkillDemandValueStringParser();

    /**
     * Test the <code>parse()</code> method.
     */
    @Test
    public void parse()
    {
        final String valueString = "Search results: 1 - 30 of 17536";
        final Integer expected = 17536;
        final Integer result = _parser.parse(valueString);
        assertNotNull(result);
        assertThat(result, is(expected));
    }
}
