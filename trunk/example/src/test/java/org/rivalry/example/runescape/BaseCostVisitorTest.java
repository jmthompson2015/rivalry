package org.rivalry.example.runescape;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>BaseCostVisitor</code> class.
 */
public class BaseCostVisitorTest
{
    /** Grand exchange. */
    private final GrandExchange _grandExchange = new MockGrandExchange();

    /**
     * Test the <code>computeBaseCost()</code> method.
     */
    @Test
    public void computeBaseCost()
    {
        verityBaseCost(Item.COPPER_ORE, 100);
        verityBaseCost(Item.TIN_ORE, 200);
        verityBaseCost(Item.GOLD_ORE, 600);

        verityBaseCost(Item.BRONZE_BAR, 300);
        verityBaseCost(Item.GOLD_BAR, 600);

        verityBaseCost(Item.SAPPHIRE_RING, 710);
        verityBaseCost(Item.EMERALD_RING, 720);

        verityBaseCost(Item.RING_OF_RECOIL, 837);
        verityBaseCost(Item.RING_OF_DUELLING, 949);
    }

    /**
     * @param item Item.
     * @param cost Expected cost.
     */
    private void verityBaseCost(final Item item, final Integer cost)
    {
        final BaseCostVisitor visitor = new BaseCostVisitor(_grandExchange);
        item.acceptDepthFirst(visitor);
        assertThat(visitor.getCost(), is(cost));
    }
}
