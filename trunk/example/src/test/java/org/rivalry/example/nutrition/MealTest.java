package org.rivalry.example.nutrition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rivalry.core.model.Candidate;

/**
 * Provides tests for the <code>Meal</code> class.
 */
public class MealTest
{
    /**
     * Test the <code>getRating()</code> method.
     */
    @Test
    public void getRating()
    {
        final Meal meal = createMeal();
        assertThat(meal.getRating(FoodCriterion.CALORIES), is(377.5));
    }

    /**
     * Test the <code>size()</code> method.
     */
    @Test
    public void size()
    {
        final Meal meal = createMeal();
        assertThat(meal.size(), is(3));
    }

    /**
     * @return a new meal.
     */
    private Meal createMeal()
    {
        final FoodDatabase foodDatabase = new FoodDatabase();
        final Meal meal = new Meal("Lunch");

        {
            final Candidate candidate = foodDatabase.getFood("Albacore tuna");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_HALF_CONTAINER);
            meal.add(foodItem);
        }

        {
            final Candidate candidate = foodDatabase.getFood("Green beans, sliced French style");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_HALF_CONTAINER);
            meal.add(foodItem);
        }

        {
            final Candidate candidate = foodDatabase.getFood("Black beans");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_HALF_CONTAINER);
            meal.add(foodItem);
        }

        return meal;
    }
}
