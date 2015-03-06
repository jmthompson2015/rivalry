package org.rivalry.example.nutrition;

import java.util.ArrayList;
import java.util.List;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

/**
 * Provides a meal.
 */
public class Meal
{
    /** Name. */
    private final String _name;

    /** Food items. */
    private final List<FoodItem> _foodItems = new ArrayList<FoodItem>();

    /**
     * Construct this object.
     *
     * @param name Name.
     */
    public Meal(final String name)
    {
        _name = name;
    }

    /**
     * @param foodItem Food item.
     */
    public void add(final FoodItem foodItem)
    {
        _foodItems.add(foodItem);
    }

    /**
     * @return the foodItems
     */
    public List<FoodItem> getFoodItems()
    {
        return _foodItems;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @param criterion Criterion.
     *
     * @return rating.
     */
    public Double getRating(final Criterion criterion)
    {
        Double answer = null;

        for (final FoodItem foodItem : _foodItems)
        {
            final Candidate food = foodItem.getFood();
            final Double rating = food.getRating(criterion);
            if (rating != null)
            {
                if (answer == null)
                {
                    answer = rating;
                }
                else
                {
                    answer += rating;
                }
            }
        }

        return answer;
    }

    /**
     * @return size.
     */
    public int size()
    {
        return _foodItems.size();
    }

    // public void writeReport()
    // {
    // for (final FoodItem foodItem : _foodItems)
    // {
    // final Candidate food = foodItem.getFood();
    // System.out.println(String.format("%s %d", food.getName(), food.getRating(FoodCriterion.CALORIES)));
    // }
    // }
}
