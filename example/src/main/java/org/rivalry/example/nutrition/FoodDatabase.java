package org.rivalry.example.nutrition;

import java.util.ArrayList;
import java.util.List;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultRivalryData;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a food database.
 */
public class FoodDatabase
{
    /** Rivalry data. */
    private RivalryData _rivalryData;

    /**
     * Construct this object.
     */
    public FoodDatabase()
    {
        _rivalryData = new DefaultRivalryData();

        _rivalryData.getCategories().addAll(createCategories());
        _rivalryData.getCriteria().addAll(createCriteria(_rivalryData.getCategories()));
        _rivalryData.getCandidates().addAll(createCandidates(_rivalryData.getCriteria()));

        final String preferencePrefix = "nutrition";
        _rivalryData.setPreferencePrefix(preferencePrefix);
    }

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
     * @param name Name.
     * @param maker Maker.
     * @param servingSize Serving size.
     * @param servingsPerContainer Servings per container.
     * @param calories Calories.
     * @param caloriesFromFat Calories from fat.
     * @param totalFat Total fat.
     * @param saturatedFat Saturated fat.
     * @param transFat Trans fat.
     * @param cholesterol Cholesterol.
     * @param sodium Sodium.
     * @param totalCarbohydrate Total carbohydrate.
     * @param dietaryFiber Dietary fiber.
     * @param sugars Sugars.
     * @param protein Protein.
     *
     * @return a new candidate.
     */
    public Candidate createCandidate(final String name, final String maker, final ServingSize servingSize,
            final double servingsPerContainer, final int calories, final int caloriesFromFat, final int totalFat,
            final int saturatedFat, final int transFat, final int cholesterol, final int sodium,
            final int totalCarbohydrate, final int dietaryFiber, final int sugars, final int protein)
    {
        final Candidate answer = createCandidate(name);

        answer.putValue(FoodCriterion.MAKER, maker);
        answer.putValue(FoodCriterion.SERVING_SIZE, servingSize);
        answer.putValue(FoodCriterion.SERVINGS_PER_CONTAINER, servingsPerContainer);
        answer.putValue(FoodCriterion.CALORIES, calories);
        answer.putValue(FoodCriterion.CALORIES_FROM_FAT, caloriesFromFat);
        answer.putValue(FoodCriterion.TOTAL_FAT, totalFat);
        answer.putValue(FoodCriterion.SATURATED_FAT, saturatedFat);
        answer.putValue(FoodCriterion.TRANS_FAT, transFat);
        answer.putValue(FoodCriterion.CHOLESTEROL, cholesterol);
        answer.putValue(FoodCriterion.SODIUM, sodium);
        answer.putValue(FoodCriterion.TOTAL_CARBOHYDRATE, totalCarbohydrate);
        answer.putValue(FoodCriterion.DIETARY_FIBER, dietaryFiber);
        answer.putValue(FoodCriterion.SUGARS, sugars);
        answer.putValue(FoodCriterion.PROTEIN, protein);

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

        answer.add(createCandidate("Albacore tuna", "StarKist", ServingSize.TWO_OUNCES, 5.0, 60, 10, 1, 0, 0, 25, 200,
                0, 0, 0, 13));
        answer.add(createCandidate("Black beans", "Kroger", ServingSize.ONE_HALF_CUP, 3.5, 110, 10, 1, 0, 0, 0, 370,
                19, 7, 0, 7));
        answer.add(createCandidate("Salsa", "Private Selection", ServingSize.TWO_TABLESPOONS, 22.0, 10, 0, 0, 0, 0, 0,
                210, 2, 1, 1, 0));
        answer.add(createCandidate("Green beans, sliced French style", "Kroger", ServingSize.ONE_HALF_CUP, 3.5, 20, 0,
                0, 0, 0, 0, 15, 4, 2, 2, 1));

        return answer;
    }

    /**
     * @return a new list of categories.
     */
    public List<Category> createCategories()
    {
        return FoodCategory.values();
    }

    /**
     * @param categories Categories.
     *
     * @return a new list of criteria.
     */
    public List<Criterion> createCriteria(final List<Category> categories)
    {
        return FoodCriterion.values();
    }

    /**
     * @param candidateName Candidate name.
     *
     * @return food.
     */
    public Candidate getFood(final String candidateName)
    {
        return _rivalryData.findCandidateByName(candidateName);
    }
}
