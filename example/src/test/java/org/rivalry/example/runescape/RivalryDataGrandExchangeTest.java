package org.rivalry.example.runescape;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>RivalryDataGrandExchange</code> class.
 */
public class RivalryDataGrandExchangeTest
{
    /** Test data. */
    private final TestData _testData = new TestData();

    /** Grand exchange. */
    private final GrandExchange _grandExchange = new RivalryDataGrandExchange(_testData.createRivalryData());

    /**
     * Test the <code>computePremium()</code> method.
     */
    @Test
    public void computePremium()
    {
        assertThat(_grandExchange.computePremium(Item.BRONZE_BAR), is(-50));
        assertThat(_grandExchange.computePremium(Item.IRON_BAR), is(-17));
        assertThat(_grandExchange.computePremium(Item.SILVER_BAR), is(-12));
        assertThat(_grandExchange.computePremium(Item.STEEL_BAR), is(-65));
        assertThat(_grandExchange.computePremium(Item.GOLD_BAR), is(-8));
        assertThat(_grandExchange.computePremium(Item.MITHRIL_BAR), is(-76));

        assertThat(_grandExchange.computePremium(Item.SAPPHIRE), is(5));
        assertThat(_grandExchange.computePremium(Item.SAPPHIRE_RING), is(32));
        assertThat(_grandExchange.computePremium(Item.RING_OF_RECOIL), is(20));

        assertThat(_grandExchange.computePremium(Item.EMERALD), is(4));
        assertThat(_grandExchange.computePremium(Item.EMERALD_RING), is(46));
        assertThat(_grandExchange.computePremium(Item.RING_OF_DUELLING), is(114));

        assertThat(_grandExchange.computePremium(Item.RUBY), is(4));
        assertThat(_grandExchange.computePremium(Item.RUBY_RING), is(60));
        assertThat(_grandExchange.computePremium(Item.RING_OF_FORGING), is(165));

        assertThat(_grandExchange.computePremium(Item.DIAMOND), is(4));
        assertThat(_grandExchange.computePremium(Item.DIAMOND_RING), is(1498));
        assertThat(_grandExchange.computePremium(Item.RING_OF_LIFE), is(-61));
    }

    /**
     * Test the <code>getValue()</code> method.
     */
    @Test
    public void getValue()
    {
        assertThat(_grandExchange.getValue(Item.GOLD_ORE), is(600));
    }

}
