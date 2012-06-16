package org.rivalry.example.runescape;

import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCriterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides test data.
 */
public class TestData
{
    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     * @param value Value.
     * 
     * @return a new candidate.
     */
    public Candidate createCandidate(final RivalryData rivalryData, final Item item, final Integer value)
    {
        final RuneScapeCandidate answer = new RuneScapeCandidate();

        answer.setItem(item);
        answer.setName(item.getName());

        final Criterion valueCriterion = getCriterion(rivalryData, Constants.VALUE_CRITERION);
        answer.putValue(valueCriterion, value);

        return answer;
    }

    /**
     * @return a new rivalry data object.
     */
    public RivalryData createRivalryData()
    {
        final DataCollectorInjector injector = new RuneScapeInjector();
        final RivalryData answer = injector.injectRivalryData();

        addCandidate(answer, Item.COPPER_ORE, 100);
        addCandidate(answer, Item.TIN_ORE, 200);
        addCandidate(answer, Item.IRON_ORE, 300);
        addCandidate(answer, Item.SILVER_ORE, 400);
        addCandidate(answer, Item.COAL, 500);
        addCandidate(answer, Item.GOLD_ORE, 600);
        addCandidate(answer, Item.MITHRIL_ORE, 700);
        addCandidate(answer, Item.ADAMANTITE_ORE, 800);
        addCandidate(answer, Item.RUNITE_ORE, 900);

        addCandidate(answer, Item.BRONZE_BAR, 150);
        addCandidate(answer, Item.IRON_BAR, 250);
        addCandidate(answer, Item.SILVER_BAR, 350);
        addCandidate(answer, Item.STEEL_BAR, 450);
        addCandidate(answer, Item.GOLD_BAR, 550);
        addCandidate(answer, Item.MITHRIL_BAR, 650);
        addCandidate(answer, Item.ADAMANT_BAR, 750);
        addCandidate(answer, Item.RUNE_BAR, 850);

        addCandidate(answer, Item.UNCUT_SAPPHIRE, 110);
        addCandidate(answer, Item.UNCUT_EMERALD, 120);
        addCandidate(answer, Item.UNCUT_RUBY, 130);
        addCandidate(answer, Item.UNCUT_DIAMOND, 140);
        addCandidate(answer, Item.UNCUT_DRAGONSTONE, 150);
        addCandidate(answer, Item.UNCUT_ONYX, 160);

        addCandidate(answer, Item.SAPPHIRE, 115);
        addCandidate(answer, Item.EMERALD, 125);
        addCandidate(answer, Item.RUBY, 135);
        addCandidate(answer, Item.DIAMOND, 145);
        addCandidate(answer, Item.DRAGONSTONE, 155);
        addCandidate(answer, Item.ONYX, 165);

        addCandidate(answer, Item.SAPPHIRE_RING, 876);
        addCandidate(answer, Item.EMERALD_RING, 987);
        addCandidate(answer, Item.RUBY_RING, 1098);
        addCandidate(answer, Item.DIAMOND_RING, 11109);
        addCandidate(answer, Item.DRAGONSTONE_RING, 121110);
        addCandidate(answer, Item.ONYX_RING, 131211);

        addCandidate(answer, Item.RUNE_ESSENCE, 51);
        addCandidate(answer, Item.PURE_ESSENCE, 76);
        addCandidate(answer, Item.AIR_RUNE, 5);
        addCandidate(answer, Item.MIND_RUNE, 10);
        addCandidate(answer, Item.WATER_RUNE, 15);
        addCandidate(answer, Item.EARTH_RUNE, 20);
        addCandidate(answer, Item.FIRE_RUNE, 25);
        addCandidate(answer, Item.BODY_RUNE, 30);
        addCandidate(answer, Item.COSMIC_RUNE, 35);

        addCandidate(answer, Item.EARTH_RUNE_10, getValue(answer, Item.EARTH_RUNE) * 10);
        addCandidate(answer, Item.EARTH_RUNE_15, getValue(answer, Item.EARTH_RUNE) * 15);
        addCandidate(answer, Item.WATER_RUNE_15, getValue(answer, Item.WATER_RUNE) * 15);
        addCandidate(answer, Item.EARTH_RUNE_20, getValue(answer, Item.EARTH_RUNE) * 20);
        addCandidate(answer, Item.FIRE_RUNE_20, getValue(answer, Item.FIRE_RUNE) * 20);

        final GrandExchange grandExchange = new RivalryDataGrandExchange(answer);
        addCandidate(answer, Item.ENCHANT_1_JEWELRY, Item.ENCHANT_1_JEWELRY.computeCost(grandExchange));
        addCandidate(answer, Item.ENCHANT_2_JEWELRY, Item.ENCHANT_2_JEWELRY.computeCost(grandExchange));
        addCandidate(answer, Item.ENCHANT_3_JEWELRY, Item.ENCHANT_3_JEWELRY.computeCost(grandExchange));
        addCandidate(answer, Item.ENCHANT_4_JEWELRY, Item.ENCHANT_4_JEWELRY.computeCost(grandExchange));
        addCandidate(answer, Item.ENCHANT_5_JEWELRY, Item.ENCHANT_5_JEWELRY.computeCost(grandExchange));
        addCandidate(answer, Item.ENCHANT_6_JEWELRY, Item.ENCHANT_6_JEWELRY.computeCost(grandExchange));

        addCandidate(answer, Item.RING_OF_RECOIL, 1111);
        addCandidate(answer, Item.RING_OF_DUELLING, 2222);
        addCandidate(answer, Item.RING_OF_FORGING, 3333);
        addCandidate(answer, Item.RING_OF_LIFE, 4444);
        addCandidate(answer, Item.RING_OF_WEALTH, 5555);
        addCandidate(answer, Item.RING_OF_STONE, 6666);

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param criterionName Criterion name.
     * 
     * @return the value criterion, creating it if necessary.
     */
    public Criterion getCriterion(final RivalryData rivalryData, final String criterionName)
    {
        Criterion answer = rivalryData.findCriterionByName(criterionName);

        if (answer == null)
        {
            answer = new DefaultCriterion();
            answer.setName(criterionName);
            rivalryData.getCriteria().add(answer);
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     * 
     * @return the value.
     */
    public Integer getValue(final RivalryData rivalryData, final Item item)
    {
        Integer answer = 0;

        final Candidate candidate = rivalryData.findCandidateByName(item.getName());

        if (candidate != null)
        {
            final Criterion valueCriterion = rivalryData.findCriterionByName(Constants.VALUE_CRITERION);
            final Double rating = candidate.getRating(valueCriterion);

            if (rating != null)
            {
                answer = rating.intValue();
            }
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     * @param value Value.
     */
    private void addCandidate(final RivalryData rivalryData, final Item item, final Integer value)
    {
        if (rivalryData.findCandidateByName(item.getName()) != null)
        {
            System.out.println("already have a candidate for " + item.getName());
        }

        final Candidate candidate = createCandidate(rivalryData, item, value);
        rivalryData.getCandidates().add(candidate);
    }
}
