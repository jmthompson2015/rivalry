package org.rivalry.example.nutrition;

import java.util.ArrayList;
import java.util.List;

import org.rivalry.core.model.Category;
import org.rivalry.core.model.DefaultCategory;

/**
 * Provides an enumeration of food categories.
 */
public class FoodCategory
{
    /**
     * @return values.
     */
    public static List<Category> values()
    {
        if (_values == null)
        {
            _values = new ArrayList<Category>();

            _values.add(CARBOHYDRATE);
            _values.add(FAT);
            _values.add(PROTEIN);

            System.out.println(FoodCategory.class.getName() + " _values.size() = " + _values.size());
        }

        return _values;
    }

    /**
     * @param name Name.
     *
     * @return a new category.
     */
    private static Category createCategory(final String name)
    {
        final Category answer = new DefaultCategory();

        answer.setName(name);

        return answer;
    }

    /** Category. */
    public static final Category CARBOHYDRATE = createCategory("Carbohydrate");

    /** Category. */
    public static final Category FAT = createCategory("Fat");

    /** Category. */
    public static final Category PROTEIN = createCategory("Protein");

    /** Values. */
    private static List<Category> _values;
}
