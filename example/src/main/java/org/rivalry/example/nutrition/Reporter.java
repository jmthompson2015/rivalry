package org.rivalry.example.nutrition;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.rivalry.core.model.Candidate;

/**
 * Provides a meal reporter.
 */
public class Reporter
{
    /** Meals. */
    private final List<Meal> _meals = new ArrayList<Meal>();

    /** Food database. */
    private static final FoodDatabase FOOD_DATABASE = new FoodDatabase();

    /**
     * Application method.
     * 
     * @param args Application arguments.
     */
    public static final void main(final String[] args)
    {
        final Reporter reporter = new Reporter();
        final Meal lunch = createLunch();
        reporter.add(lunch);
        final StringWriter writer = new StringWriter();
        reporter.writeReport(writer);
        System.out.println();
        System.out.println(writer.toString());
    }

    /**
     * @return a new meal.
     */
    private static Meal createLunch()
    {
        final Meal answer = new Meal("Lunch");

        {
            final Candidate candidate = FOOD_DATABASE.getFood("Albacore tuna");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_HALF_CONTAINER);
            answer.add(foodItem);
        }

        {
            final Candidate candidate = FOOD_DATABASE.getFood("Salsa");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.TWO_TABLESPOONS);
            answer.add(foodItem);
        }

        {
            final Candidate candidate = FOOD_DATABASE.getFood("Green beans, sliced French style");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_THIRD_CONTAINER);
            answer.add(foodItem);
        }

        {
            final Candidate candidate = FOOD_DATABASE.getFood("Black beans");
            final FoodItem foodItem = new FoodItem(candidate, ServingSize.ONE_HALF_CONTAINER);
            answer.add(foodItem);
        }

        return answer;
    }

    /**
     * @param meal Meal.
     */
    public void add(final Meal meal)
    {
        _meals.add(meal);
    }

    /**
     * @param writer Writer.
     */
    public void writeReport(final Writer writer)
    {
        for (final Meal meal : _meals)
        {
            try
            {
                writer.write(String.format(getHeaderFormat(), "Food", "Serving Size", "Calories", "Fat Calories",
                        "Total Carbs", "Protein"));

                for (final FoodItem foodItem : meal.getFoodItems())
                {
                    final Candidate food = foodItem.getFood();
                    writer.write(String.format(getValueFormat(), food.getName(), foodItem.getServingSize(),
                            food.getRating(FoodCriterion.CALORIES), food.getRating(FoodCriterion.CALORIES_FROM_FAT),
                            food.getRating(FoodCriterion.TOTAL_CARBOHYDRATE), food.getRating(FoodCriterion.PROTEIN)));
                }

                writer.write(String.format(getHeaderFormat(), "--------------------------------", "------------",
                        "--------", "------------", "------------", "------------"));
                writer.write(String.format(getValueFormat(), meal.getName(), "",
                        meal.getRating(FoodCriterion.CALORIES), meal.getRating(FoodCriterion.CALORIES_FROM_FAT),
                        meal.getRating(FoodCriterion.TOTAL_CARBOHYDRATE), meal.getRating(FoodCriterion.PROTEIN)));
            }
            catch (final IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @return header format.
     */
    private String getHeaderFormat()
    {
        return "%-35s %14s %9s %13s %13s %13s%n";
    }

    /**
     * @return value format.
     */
    private String getValueFormat()
    {
        return "%-35s %14s %9.1f %13.1f %13.1f %13.1f%n";
    }
}
