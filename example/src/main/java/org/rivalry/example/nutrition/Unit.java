package org.rivalry.example.nutrition;

/**
 * Provides measurement units.
 */
public enum Unit
{
    /** Unit. */
    CONTAINER("container")
    {
        @Override
        public Double multiplier(final Unit unit, final Double servingsPerContainer)
        {
            Double answer = null;

            switch (unit)
            {
            case CONTAINER:
                answer = 1.0;
                break;
            case CUP:
                break;
            case OUNCE:
                break;
            case TABLESPOON:
                break;
            }

            return answer;
        }
    },

    /** Unit. */
    CUP("cup")
    {
        @Override
        public Double multiplier(final Unit unit, final Double servingsPerContainer)
        {
            Double answer = null;

            switch (unit)
            {
            case CONTAINER:
                answer = servingsPerContainer;
                break;
            case CUP:
                answer = 1.0;
                break;
            case OUNCE:
                answer = 8.0;
                break;
            case TABLESPOON:
                answer = 16.0;
                break;
            }

            return answer;
        }
    },

    /** Unit. */
    OUNCE("ounce")
    {
        @Override
        public Double multiplier(final Unit unit, final Double servingsPerContainer)
        {
            Double answer = null;

            switch (unit)
            {
            case CONTAINER:
                answer = servingsPerContainer;
                break;
            case CUP:
                answer = 1.0 / 8.0;
                break;
            case OUNCE:
                answer = 1.0;
                break;
            case TABLESPOON:
                answer = 2.0;
                break;
            }

            return answer;
        }
    },

    /** Unit. */
    TABLESPOON("tablespoon")
    {
        @Override
        public Double multiplier(final Unit unit, final Double servingsPerContainer)
        {
            Double answer = null;

            switch (unit)
            {
            case CONTAINER:
                answer = servingsPerContainer;
                break;
            case CUP:
                answer = 1.0 / 16.0;
                break;
            case OUNCE:
                answer = 1.0 / 2.0;
                break;
            case TABLESPOON:
                answer = 1.0;
                break;
            }

            return answer;
        }
    };

    /** Display name. */
    private final String _displayName;

    /**
     * Construct this object.
     *
     * @param displayName Display name.
     */
    private Unit(final String displayName)
    {
        _displayName = displayName;
    }

    /**
     * @param unit Unit.
     * @param servingsPerContainer Servings per container.
     *
     * @return value multiplier.
     */
    public abstract Double multiplier(Unit unit, Double servingsPerContainer);

    @Override
    public String toString()
    {
        return _displayName;
    }
}
