package org.rivalry.example.nutrition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rivalry.core.model.Candidate;

/**
 * Provides tests for the <code>FoodItem</code> class.
 */
public class FoodItemTest
{
    /**
     * Test the <code>getFood()</code> method.
     */
    @Test
    public void getFoodOneHalfContainer()
    {
        final FoodDatabase foodDatabase = new FoodDatabase();
        final Candidate candidate = foodDatabase.getFood("Black beans");
        final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_HALF_CONTAINER);
        final Candidate food = foodItem.getFood();

        assertThat((Double)food.getValue(FoodCriterion.CALORIES), is((3.5 * 110) / 2.0));
        assertThat((Double)food.getValue(FoodCriterion.CALORIES_FROM_FAT), is((3.5 * 10) / 2.0));
        assertThat((Double)food.getValue(FoodCriterion.TOTAL_FAT), is((3.5 * 1) / 2.0));
        assertThat((Double)food.getValue(FoodCriterion.TOTAL_CARBOHYDRATE), is((3.5 * 19) / 2.0));
        assertThat((Double)food.getValue(FoodCriterion.PROTEIN), is((3.5 * 7) / 2.0));
    }
}
