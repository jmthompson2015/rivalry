package org.rivalry.example.nutrition;

import java.util.ArrayList;
import java.util.List;

import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCriterion;

/**
 * Provides an enumeration of food criteria.
 */
public class FoodCriterion
{
    /**
     * @return values.
     */
    public static List<Criterion> values()
    {
        if (_values == null)
        {
            _values = new ArrayList<Criterion>();

            _values.add(MAKER);
            _values.add(SERVING_SIZE);
            _values.add(SERVINGS_PER_CONTAINER);
            _values.add(CALORIES);
            _values.add(CALORIES_FROM_FAT);
            _values.add(TOTAL_FAT);
            _values.add(SATURATED_FAT);
            _values.add(TRANS_FAT);
            _values.add(CHOLESTEROL);
            _values.add(SODIUM);
            _values.add(TOTAL_CARBOHYDRATE);
            _values.add(DIETARY_FIBER);
            _values.add(SUGARS);
            _values.add(PROTEIN);

            System.out.println(FoodCriterion.class.getName() + " _values.size() = " + _values.size());
        }

        return _values;
    }

    /**
     * @param name Name.
     * @param category Category.
     *
     * @return a new criterion.
     */
    private static Criterion createCriterion(final String name, final Category category)
    {
        final Criterion answer = new DefaultCriterion();

        answer.setName(name);
        answer.setCategory(category);

        return answer;
    }

    /** Criterion. */
    public static final Criterion MAKER = createCriterion("Maker", null);

    /** Criterion. */
    public static final Criterion SERVING_SIZE = createCriterion("Serving Size", null);

    /** Criterion. */
    public static final Criterion SERVINGS_PER_CONTAINER = createCriterion("Servings Per Container", null);

    /** Criterion. */
    public static final Criterion CALORIES = createCriterion("Calories", null);

    /** Criterion. */
    public static final Criterion CALORIES_FROM_FAT = createCriterion("Calories from Fat", null);

    /** Criterion. */
    public static final Criterion TOTAL_FAT = createCriterion("Total Fat (g)", FoodCategory.FAT);

    /** Criterion. */
    public static final Criterion SATURATED_FAT = createCriterion("Saturated Fat (g)", FoodCategory.FAT);

    /** Criterion. */
    public static final Criterion TRANS_FAT = createCriterion("Trans Fat (g)", FoodCategory.FAT);

    /** Criterion. */
    public static final Criterion CHOLESTEROL = createCriterion("Cholesterol (mg)", null);

    /** Criterion. */
    public static final Criterion SODIUM = createCriterion("Sodium (mg)", null);

    /** Criterion. */
    public static final Criterion TOTAL_CARBOHYDRATE = createCriterion("Total Carbohydrate (g)",
            FoodCategory.CARBOHYDRATE);

    /** Criterion. */
    public static final Criterion DIETARY_FIBER = createCriterion("Dietary Fiber (g)", FoodCategory.CARBOHYDRATE);

    /** Criterion. */
    public static final Criterion SUGARS = createCriterion("Sugars (g)", FoodCategory.CARBOHYDRATE);

    /** Criterion. */
    public static final Criterion PROTEIN = createCriterion("Protein (g)", FoodCategory.PROTEIN);

    /** Values. */
    private static List<Criterion> _values;
}
