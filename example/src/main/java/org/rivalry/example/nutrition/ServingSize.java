package org.rivalry.example.nutrition;

/**
 * Provides a serving size.
 */
public enum ServingSize
{
    /** Serving size. */
    ONE_CUP("1 cup", 1.0, Unit.CUP),

    /** Serving size. */
    ONE_HALF_CUP("1/2 cup", 0.5, Unit.CUP),

    /** Serving size. */
    ONE_CONTAINER("1 container", 1.0, Unit.CONTAINER),

    /** Serving size. */
    ONE_HALF_CONTAINER("1/2 container", 1.0 / 2.0, Unit.CONTAINER),

    /** Serving size. */
    ONE_THIRD_CONTAINER("1/3 container", 1.0 / 3.0, Unit.CONTAINER),

    /** Serving size. */
    ONE_OUNCE("1 oz", 1.0, Unit.OUNCE),

    /** Serving size. */
    TWO_OUNCES("2 oz", 2.0, Unit.OUNCE),

    /** Serving size. */
    ONE_TABLESPOON("1 tablespoon", 1.0, Unit.TABLESPOON),

    /** Serving size. */
    TWO_TABLESPOONS("2 tablespoons", 2.0, Unit.TABLESPOON),

    ;

    /** Amount. */
    private final Double _amount;

    /** Display name. */
    private final String _displayName;

    /** Unit. */
    private final Unit _unit;

    /**
     * Construct this object with the given parameter.
     *
     * @param displayName Display name.
     * @param amount Amount.
     * @param unit Unit.
     */
    private ServingSize(final String displayName, final Double amount, final Unit unit)
    {
        _displayName = displayName;
        _amount = amount;
        _unit = unit;
    }

    /**
     * @return the amount
     */
    public Double getAmount()
    {
        return _amount;
    }

    /**
     * @return the unit
     */
    public Unit getUnit()
    {
        return _unit;
    }

    /**
     * @param servingSize Serving size.
     * @param servingsPerContainer Servings per container.
     *
     * @return value multiplier.
     */
    public Double multiplier(final ServingSize servingSize, final Double servingsPerContainer)
    {
        Double answer = null;

        if ((getUnit() == Unit.CONTAINER) && (servingSize.getUnit() == Unit.CONTAINER))
        {
            answer = getAmount() / servingSize.getAmount();
        }
        else
        {
            final Double multiplier = getUnit().multiplier(servingSize.getUnit(), servingsPerContainer);

            if (multiplier != null)
            {
                final Double amount = getAmount();

                switch (servingSize)
                {
                case ONE_CUP:
                    answer = amount * multiplier;
                    break;
                case ONE_HALF_CUP:
                    answer = (amount * multiplier) / 2.0;
                    break;
                case ONE_CONTAINER:
                    answer = servingsPerContainer;
                    break;
                case ONE_HALF_CONTAINER:
                    answer = servingsPerContainer / 2.0;
                    break;
                case ONE_THIRD_CONTAINER:
                    answer = servingsPerContainer / 3.0;
                    break;
                case ONE_OUNCE:
                    answer = amount * multiplier;
                    break;
                case TWO_OUNCES:
                    answer = (amount * multiplier) / 2.0;
                    break;
                case ONE_TABLESPOON:
                    answer = amount * multiplier;
                    break;
                case TWO_TABLESPOONS:
                    answer = (amount * multiplier) / 2.0;
                    break;
                }
                // System.out.println(name() + " multiplier = " + multiplier + " getAmount() = " + getAmount() +
                // " answer = "
                // + answer);
            }
        }

        return answer;
    }

    @Override
    public String toString()
    {
        return _displayName;
    }
}
