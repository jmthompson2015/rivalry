package org.rivalry.core.model;

import java.util.ArrayList;
import java.util.List;

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
            candidate.putRating(criteria.get(0), 1.1);
            candidate.putRating(criteria.get(1), 1.2);
            candidate.putRating(criteria.get(2), 1.3);
            answer.add(candidate);
        }

        {
            final Candidate candidate = createCandidate("2");
            candidate.putRating(criteria.get(0), 2.1);
            candidate.putRating(criteria.get(1), 2.2);
            candidate.putRating(criteria.get(2), 2.3);
            answer.add(candidate);
        }

        {
            final Candidate candidate = createCandidate("3");
            candidate.putRating(criteria.get(0), 3.1);
            candidate.putRating(criteria.get(1), 3.2);
            candidate.putRating(criteria.get(2), 3.3);
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

        answer.add(createCriterion("a", categories.get(0), 1));
        answer.add(createCriterion("b", categories.get(1), 2));
        answer.add(createCriterion("c", categories.get(2), 3));

        return answer;
    }

    /**
     * @param name Name.
     * @param category Category.
     * @param weight Weight.
     * 
     * @return a new criterion.
     */
    public Criterion createCriterion(final String name,
            final Category category, final Integer weight)
    {
        final Criterion answer = new DefaultCriterion();

        answer.setName(name);
        answer.setCategory(category);
        answer.setWeight(weight);

        return answer;
    }

    /**
     * @return a new rivalry data.
     */
    public RivalryData createRivalryData()
    {
        final RivalryData answer = new RivalryData();

        answer.getCategoriesList().addAll(createCategories());
        answer.getCriteriaList().addAll(
                createCriteria(answer.getCategoriesList()));
        answer.getCandidatesList().addAll(
                createCandidates(answer.getCriteriaList()));

        return answer;
    }
}
