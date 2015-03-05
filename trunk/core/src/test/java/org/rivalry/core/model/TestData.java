//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.util.ArrayList;
import java.util.List;

import org.rivalry.core.fitness.WeightedSumFitnessFunction;

/**
 * Provides test data.
 */
public class TestData
{
    /**
     * @param name Name.
     *
     * @return a new candidate.
     */
    public Candidate createCandidate(final String name)
    {
        final Candidate answer = new DefaultCandidate();

        answer.setName(name);

        return answer;
    }

    /**
     * @param criteria Criteria.
     *
     * @return a new list of candidates.
     */
    public List<Candidate> createCandidates(final List<Criterion> criteria)
    {
        final List<Candidate> answer = new ArrayList<Candidate>();

        {
            final Candidate candidate = createCandidate("1");
            candidate.putValue(criteria.get(0), 1.1);
            candidate.putValue(criteria.get(1), 1.2);
            candidate.putValue(criteria.get(2), 1.3);
            answer.add(candidate);
        }

        {
            final Candidate candidate = createCandidate("2");
            candidate.putValue(criteria.get(0), 2.1);
            candidate.putValue(criteria.get(1), 2.2);
            candidate.putValue(criteria.get(2), 2.3);
            answer.add(candidate);
        }

        {
            final Candidate candidate = createCandidate("3");
            candidate.putValue(criteria.get(0), 3.1);
            candidate.putValue(criteria.get(1), 3.2);
            candidate.putValue(criteria.get(2), 3.3);
            answer.add(candidate);
        }

        return answer;
    }

    /**
     * @return a new list of categories.
     */
    public List<Category> createCategories()
    {
        final List<Category> answer = new ArrayList<Category>();

        answer.add(createCategory("A"));
        answer.add(createCategory("B"));
        answer.add(createCategory("C"));

        return answer;
    }

    /**
     * @param name Name.
     *
     * @return a new category.
     */
    public Category createCategory(final String name)
    {
        final Category answer = new DefaultCategory();

        answer.setName(name);

        return answer;
    }

    /**
     * @param categories Categories.
     *
     * @return a new list of criteria.
     */
    public List<Criterion> createCriteria(final List<Category> categories)
    {
        final List<Criterion> answer = new ArrayList<Criterion>();

        answer.add(createCriterion("a", categories.get(0)));
        answer.add(createCriterion("b", categories.get(1)));
        answer.add(createCriterion("c", categories.get(2)));

        return answer;
    }

    /**
     * @param name Name.
     * @param category Category.
     *
     * @return a new criterion.
     */
    public Criterion createCriterion(final String name, final Category category)
    {
        final Criterion answer = new DefaultCriterion();

        answer.setName(name);
        answer.setCategory(category);

        return answer;
    }

    /**
     * @return a new rivalry data.
     */
    public RivalryData createRivalryData()
    {
        return createRivalryData("test");
    }

    /**
     * @param preferencePrefix Preference prefix.
     *
     * @return a new rivalry data.
     */
    public RivalryData createRivalryData(final String preferencePrefix)
    {
        final RivalryData answer = new DefaultRivalryData();

        answer.getCategories().addAll(createCategories());
        answer.getCriteria().addAll(createCriteria(answer.getCategories()));
        answer.getCandidates().addAll(createCandidates(answer.getCriteria()));

        answer.setPreferencePrefix(preferencePrefix);

        return answer;
    }

    /**
     * @param preferencePrefix Preference prefix.
     * @param criteria Criteria.
     *
     * @return a new fitness function.
     */
    public WeightedSumFitnessFunction createWeightedSumFitnessFunction(final String preferencePrefix,
            final List<Criterion> criteria)
    {
        final WeightedSumFitnessFunction answer = new WeightedSumFitnessFunction(preferencePrefix);

        answer.putWeight(criteria.get(0), 1);
        answer.putWeight(criteria.get(1), 2);
        answer.putWeight(criteria.get(2), 3);

        return answer;
    }
}
