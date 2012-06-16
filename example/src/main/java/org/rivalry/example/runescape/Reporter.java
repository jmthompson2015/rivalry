//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reporter
{
    private static class ItemPremium implements Comparable<ItemPremium>
    {
        private final Item _item;
        private final Integer _premium;
        private final Integer _basePremium;

        public ItemPremium(final Item item, final Integer premium, final Integer basePremium)
        {
            _item = item;
            _premium = premium;
            _basePremium = basePremium;
        }

        @Override
        public int compareTo(final ItemPremium other)
        {
            int answer = -_premium.compareTo(other._premium);

            if (answer == 0)
            {
                answer = _item.getName().compareTo(other._item.getName());
            }

            return answer;
        }

        /**
         * @return the basePremium
         */
        public Integer getBasePremium()
        {
            return _basePremium;
        }

        /**
         * @return the item
         */
        public Item getItem()
        {
            return _item;
        }

        /**
         * @return the premium
         */
        public Integer getPremium()
        {
            return _premium;
        }
    }

    public void reportPremiums(final Writer writer, final GrandExchange grandExchange)
    {
        final List<ItemPremium> itemPremiums = new ArrayList<ItemPremium>();

        for (final Item item : Item.values())
        {
            final Integer premium = grandExchange.computePremium(item);
            final Integer basePremium = grandExchange.computeBasePremium(item);
            itemPremiums.add(new ItemPremium(item, premium, basePremium));
        }

        Collections.sort(itemPremiums);

        try
        {
            writer.write(String.format("%8s %13s %-30s %8s %8s %9s%n", "Premium", "Base Premium", "Item", "Value",
                    "Cost", "Base Cost"));
            writer.write(String.format("%8s %13s %-30s %8s %8s %9s%n", "-------", "------------", "----", "-----",
                    "----", "---------"));

            for (final ItemPremium itemPremium : itemPremiums)
            {
                final Integer value = grandExchange.getValue(itemPremium.getItem());
                if (value != null)
                {
                    final Integer premium = itemPremium.getPremium();
                    final Integer basePremium = itemPremium.getBasePremium();
                    final Integer cost = itemPremium.getItem().computeCost(grandExchange);
                    final Integer baseCost = itemPremium.getItem().computeBaseCost(grandExchange);

                    writer.write(String.format("%8d %13d %-30s %8d %8d %9d%n", premium, basePremium, itemPremium
                            .getItem().getName(), value, cost, baseCost));
                }
            }
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
