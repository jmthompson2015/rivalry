package org.rivalry.example.runescape;

import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCriterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data post processor for RuneScape.
 */
public class RuneScapeDataPostProcessor implements DataPostProcessor
{
    @Override
    public void postProcess(final RivalryData rivalryData)
    {
        final GrandExchange grandExchange = new RivalryDataGrandExchange(rivalryData);

        createSpecialCandidates(rivalryData, grandExchange);

        for (final Candidate candidate : rivalryData.getCandidates())
        {
            final Item item = ((RuneScapeCandidate)candidate).getItem();

            final Integer cost = item.computeCost(grandExchange);
            final Integer baseCost = item.computeBaseCost(grandExchange);
            final Integer premium = grandExchange.computePremium(item);
            final Integer basePremium = grandExchange.computeBasePremium(item);

            candidate.putValue(getPremiumCriterion(rivalryData), premium);
            candidate.putValue(getCostCriterion(rivalryData), cost);
            candidate.putValue(getBasePremiumCriterion(rivalryData), basePremium);
            candidate.putValue(getBaseCostCriterion(rivalryData), baseCost);
        }
    }

    /**
     * @param rivalryData Rivalry data.
     * @param item Item.
     * @param value Value.
     * 
     * @return a new candidate.
     */
    private Candidate createCandidate(final RivalryData rivalryData, final Item item, final Double value)
    {
        final RuneScapeCandidate answer = new RuneScapeCandidate();

        answer.setItem(item);

        final Criterion valueCriterion = getValueCriterion(rivalryData);
        answer.putValue(valueCriterion, value);

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param grandExchange Grand exchange.
     */
    private void createSpecialCandidates(final RivalryData rivalryData, final GrandExchange grandExchange)
    {
        // Constant values.
        rivalryData.getCandidates().add(createCandidate(rivalryData, Item.GP_100, 100.0));
        rivalryData.getCandidates().add(createCandidate(rivalryData, Item.GP_250, 250.0));
        rivalryData.getCandidates().add(createCandidate(rivalryData, Item.GP_500, 500.0));
        rivalryData.getCandidates().add(createCandidate(rivalryData, Item.GP_1500, 1500.0));
        rivalryData.getCandidates().add(createCandidate(rivalryData, Item.WATER, 0.0));

        // Missing values.
        if (rivalryData.findCandidateByName(Item.MAHOGANY_LOGS.getName()) == null)
        {
            rivalryData.getCandidates().add(createCandidate(rivalryData, Item.MAHOGANY_LOGS, 404.0));
        }

        if (rivalryData.findCandidateByName(Item.PASTRY_DOUGH.getName()) == null)
        {
            rivalryData.getCandidates().add(createCandidate(rivalryData, Item.PASTRY_DOUGH, 370.0));
        }

        // Computed values.

        {
            final Integer value = grandExchange.getValue(Item.EARTH_RUNE);

            if (value != null)
            {
                rivalryData.getCandidates().add(createCandidate(rivalryData, Item.EARTH_RUNE_10, 10.0 * value));
                rivalryData.getCandidates().add(createCandidate(rivalryData, Item.EARTH_RUNE_15, 15.0 * value));
                rivalryData.getCandidates().add(createCandidate(rivalryData, Item.EARTH_RUNE_20, 20.0 * value));
            }
        }

        {
            final Integer value = grandExchange.getValue(Item.WATER_RUNE);
            if (value != null)
            {
                final Candidate candidate = createCandidate(rivalryData, Item.WATER_RUNE_15, 15.0 * value);
                rivalryData.getCandidates().add(candidate);
            }
        }

        {
            final Integer value = grandExchange.getValue(Item.FIRE_RUNE);
            if (value != null)
            {
                final Candidate candidate = createCandidate(rivalryData, Item.FIRE_RUNE_20, 20.0 * value);
                rivalryData.getCandidates().add(candidate);
            }
        }

        final Item[] enchantments = { Item.ENCHANT_1_JEWELRY, Item.ENCHANT_2_JEWELRY, Item.ENCHANT_3_JEWELRY,
                Item.ENCHANT_4_JEWELRY, Item.ENCHANT_5_JEWELRY, Item.ENCHANT_6_JEWELRY, };

        for (final Item item : enchantments)
        {
            final Integer value = item.computeCost(grandExchange);
            if (value != null)
            {
                final Candidate candidate = createCandidate(rivalryData, item, value.doubleValue());
                rivalryData.getCandidates().add(candidate);
            }
        }
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the criterion, creating it if necessary.
     */
    private Criterion getBaseCostCriterion(final RivalryData rivalryData)
    {
        return getCriterion(rivalryData, Constants.BASE_COST_CRITERION);
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the criterion, creating it if necessary.
     */
    private Criterion getBasePremiumCriterion(final RivalryData rivalryData)
    {
        return getCriterion(rivalryData, Constants.BASE_PREMIUM_CRITERION);
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the criterion, creating it if necessary.
     */
    private Criterion getCostCriterion(final RivalryData rivalryData)
    {
        return getCriterion(rivalryData, Constants.COST_CRITERION);
    }

    /**
     * @param rivalryData Rivalry data.
     * @param criterionName Criterion name.
     * 
     * @return the value criterion, creating it if necessary.
     */
    private Criterion getCriterion(final RivalryData rivalryData, final String criterionName)
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
     * 
     * @return the criterion, creating it if necessary.
     */
    private Criterion getPremiumCriterion(final RivalryData rivalryData)
    {
        return getCriterion(rivalryData, Constants.PREMIUM_CRITERION);
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the value criterion, creating it if necessary.
     */
    private Criterion getValueCriterion(final RivalryData rivalryData)
    {
        return getCriterion(rivalryData, Constants.VALUE_CRITERION);
    }
}
