package org.rivalry.example.runescape;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

/**
 * Provides tests for the <code>Item</code> class.
 */
public class ItemTest
{
    /**
     * Provides a simple visitor which just prints the item name.
     */
    static class PrintNameVisitor implements Visitor
    {
        /** Writer. */
        private final Writer _writer;

        /**
         * Construct this object with the given parameter.
         * 
         * @param writer Writer.
         */
        public PrintNameVisitor(final Writer writer)
        {
            _writer = writer;
        }

        @Override
        public void visit(final Item item)
        {
            try
            {
                _writer.write(item.getName());
                _writer.write("\n");
            }
            catch (final IOException ignore)
            {
                // Nothing to do.
            }
        }
    }

    /** Grand exchange. */
    private final GrandExchange _grandExchange = new MockGrandExchange();

    /**
     * Test the <code>computeBaseCost()</code> method.
     */
    @Test
    public void acceptBreadthFirst()
    {
        final Writer writer = new StringWriter();
        final Visitor visitor = new PrintNameVisitor(writer);
        Item.EMERALD_RING.acceptBreadthFirst(visitor);

        System.out.println(writer.toString());

        final String expected = "Emerald ring\nGold bar\nEmerald\nGold ore\nUncut emerald\n";
        assertThat(writer.toString(), is(expected));
    }

    /**
     * Test the <code>acceptDepthFirst()</code> method.
     */
    @Test
    public void acceptDepthFirst()
    {
        final Writer writer = new StringWriter();
        final Visitor visitor = new PrintNameVisitor(writer);
        Item.EMERALD_RING.acceptDepthFirst(visitor);

        System.out.println(writer.toString());

        final String expected = "Emerald ring\nGold bar\nGold ore\nEmerald\nUncut emerald\n";
        assertThat(writer.toString(), is(expected));
    }

    /**
     * Test the <code>computeBaseCost()</code> method.
     */
    @Test
    public void computeBaseCost()
    {
        assertThat(Item.COPPER_ORE.computeBaseCost(_grandExchange), is(100));
        assertThat(Item.TIN_ORE.computeBaseCost(_grandExchange), is(200));
        assertThat(Item.GOLD_ORE.computeBaseCost(_grandExchange), is(600));

        assertThat(Item.BRONZE_BAR.computeBaseCost(_grandExchange), is(300));
        assertThat(Item.GOLD_BAR.computeBaseCost(_grandExchange), is(600));

        assertThat(Item.SAPPHIRE_RING.computeBaseCost(_grandExchange), is(710));
        assertThat(Item.EMERALD_RING.computeBaseCost(_grandExchange), is(720));

        assertThat(Item.ENCHANT_6_JEWELRY.computeBaseCost(_grandExchange), is(976));

        assertThat(Item.RING_OF_RECOIL.computeBaseCost(_grandExchange), is(837));
        assertThat(Item.RING_OF_DUELLING.computeBaseCost(_grandExchange), is(949));
        assertThat(Item.RING_OF_FORGING.computeBaseCost(_grandExchange), is(1061));
        assertThat(Item.RING_OF_LIFE.computeBaseCost(_grandExchange), is(1016));
        assertThat(Item.RING_OF_WEALTH.computeBaseCost(_grandExchange), is(1351));
        assertThat(Item.RING_OF_STONE.computeBaseCost(_grandExchange), is(1736));
    }

    /**
     * Test the <code>computeCost()</code> method.
     */
    @Test
    public void computeCost()
    {
        assertThat(Item.COPPER_ORE.computeCost(_grandExchange), is(100));
        assertThat(Item.TIN_ORE.computeCost(_grandExchange), is(200));
        assertThat(Item.GOLD_ORE.computeCost(_grandExchange), is(600));

        assertThat(Item.BRONZE_BAR.computeCost(_grandExchange), is(300));
        assertThat(Item.GOLD_BAR.computeCost(_grandExchange), is(600));

        assertThat(Item.SAPPHIRE_RING.computeCost(_grandExchange), is(665));
        assertThat(Item.EMERALD_RING.computeCost(_grandExchange), is(675));
        assertThat(Item.RUBY_RING.computeCost(_grandExchange), is(685));
        assertThat(Item.DIAMOND_RING.computeCost(_grandExchange), is(695));
        assertThat(Item.DRAGONSTONE_RING.computeCost(_grandExchange), is(705));
        assertThat(Item.ONYX_RING.computeCost(_grandExchange), is(715));

        assertThat(Item.ENCHANT_4_JEWELRY.computeCost(_grandExchange), is(235));
        assertThat(Item.ENCHANT_5_JEWELRY.computeCost(_grandExchange), is(560));
        assertThat(Item.ENCHANT_6_JEWELRY.computeCost(_grandExchange), is(935));

        assertThat(Item.RING_OF_RECOIL.computeCost(_grandExchange), is(926));
        assertThat(Item.RING_OF_DUELLING.computeCost(_grandExchange), is(1037));
        assertThat(Item.RING_OF_FORGING.computeCost(_grandExchange), is(1258));
        // Diamond ring 11109 + Enchant 4 235 = 11344
        // assertThat(_grandExchange.getValue(Item.DIAMOND_RING), is(11109));
        // assertThat(_grandExchange.getValue(Item.ENCHANT_4_JEWELRY), is(235));
        assertThat(Item.RING_OF_LIFE.computeCost(_grandExchange), is(11344));
        // Dragonstone ring 121110 + Enchant 5 560 = 121670
        assertThat(Item.RING_OF_WEALTH.computeCost(_grandExchange), is(121670));
        // Onyx ring 131211 + Enchant 6 935 = 132146
        assertThat(Item.RING_OF_STONE.computeCost(_grandExchange), is(132146));
    }

    /**
     * Test the <code>isLeaf()</code> method.
     */
    @Test
    public void isLeaf()
    {
        assertTrue(Item.GOLD_ORE.isLeaf());
        assertFalse(Item.GOLD_BAR.isLeaf());
        assertTrue(Item.UNCUT_EMERALD.isLeaf());
        assertFalse(Item.EMERALD.isLeaf());
        assertFalse(Item.EMERALD_RING.isLeaf());
        assertFalse(Item.RING_OF_DUELLING.isLeaf());
    }

    /**
     * Test the <code>valueOfName()</code> method.
     */
    @Test
    public void valueOfName()
    {
        assertThat(Item.valueOfName("Gold ore"), is(Item.GOLD_ORE));
    }
}
