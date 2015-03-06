package org.rivalry.example.nutrition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>ServingSize</code> class.
 */
public class ServingSizeTest
{
    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierOneContainer()
    {
        final Double servingsPerContainer = 1.0;

        assertThat(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_CONTAINER, servingsPerContainer), is(1.0));
        assertThat(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_HALF_CONTAINER, servingsPerContainer), is(2.0));
        assertThat(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_THIRD_CONTAINER, servingsPerContainer), is(3.0));

        assertNull(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_CUP, servingsPerContainer));
        assertNull(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_HALF_CUP, servingsPerContainer));
        assertNull(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer));
        assertNull(ServingSize.ONE_CONTAINER.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer));
        assertNull(ServingSize.ONE_CONTAINER.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer));
        assertNull(ServingSize.ONE_CONTAINER.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierOneCup()
    {
        final Double servingsPerContainer = 3.5;

        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_CONTAINER, servingsPerContainer), is(3.5));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_HALF_CONTAINER, servingsPerContainer), is(1.75));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_THIRD_CONTAINER, servingsPerContainer), is(3.5 / 3.0));

        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_CUP, servingsPerContainer), is(1.0));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_HALF_CUP, servingsPerContainer), is(0.5));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer), is(8.0));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer), is(4.0));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer), is(16.0));
        assertThat(ServingSize.ONE_CUP.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer), is(8.0));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierOneHalfContainer()
    {
        final Double servingsPerContainer = 1.0;

        assertThat(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_CONTAINER, 1.0), is(0.5));
        assertThat(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_HALF_CONTAINER, 1.0), is(1.0));
        assertThat(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_THIRD_CONTAINER, 1.0), is(3.0 / 2.0));

        assertNull(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_CUP, 1.0));
        assertNull(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_HALF_CUP, 1.0));
        assertNull(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer));
        assertNull(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer));
        assertNull(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer));
        assertNull(ServingSize.ONE_HALF_CONTAINER.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierOneOunce()
    {
        final Double servingsPerContainer = 3.5;

        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_CONTAINER, servingsPerContainer), is(3.5));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_HALF_CONTAINER, servingsPerContainer), is(1.75));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_THIRD_CONTAINER, servingsPerContainer),
                is(3.5 / 3.0));

        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_CUP, servingsPerContainer), is(1.0 / 8.0));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_HALF_CUP, servingsPerContainer), is(1.0 / 16.0));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer), is(1.0));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer), is(1.0 / 2.0));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer), is(2.0));
        assertThat(ServingSize.ONE_OUNCE.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer), is(1.0));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierOneTablespoon()
    {
        final Double servingsPerContainer = 3.5;

        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_CONTAINER, servingsPerContainer), is(3.5));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_HALF_CONTAINER, servingsPerContainer),
                is(1.75));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_THIRD_CONTAINER, servingsPerContainer),
                is(3.5 / 3.0));

        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_CUP, servingsPerContainer), is(1.0 / 16.0));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_HALF_CUP, servingsPerContainer),
                is(1.0 / 32.0));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer), is(1.0 / 2.0));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer), is(1.0 / 4.0));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer), is(1.0));
        assertThat(ServingSize.ONE_TABLESPOON.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer),
                is(1.0 / 2.0));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierOneThirdContainer()
    {
        final Double servingsPerContainer = 1.0;

        assertThat(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_CONTAINER, 1.0), is(1.0 / 3.0));
        assertThat(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_HALF_CONTAINER, 1.0), is(2.0 / 3.0));
        assertThat(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_THIRD_CONTAINER, 1.0), is(1.0));

        assertNull(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_CUP, 1.0));
        assertNull(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_HALF_CUP, 1.0));
        assertNull(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer));
        assertNull(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer));
        assertNull(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer));
        assertNull(ServingSize.ONE_THIRD_CONTAINER.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierTwoOunces()
    {
        final Double servingsPerContainer = 3.5;

        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_CONTAINER, servingsPerContainer), is(3.5));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_HALF_CONTAINER, servingsPerContainer), is(1.75));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_THIRD_CONTAINER, servingsPerContainer),
                is(3.5 / 3.0));

        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_CUP, servingsPerContainer), is(1.0 / 4.0));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_HALF_CUP, servingsPerContainer), is(1.0 / 8.0));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer), is(2.0));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer), is(1.0));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer), is(4.0));
        assertThat(ServingSize.TWO_OUNCES.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer), is(2.0));
    }

    /**
     * Test the <code>multiplier()</code> method.
     */
    @Test
    public void multiplierTwoTablespoons()
    {
        final Double servingsPerContainer = 3.5;

        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_CONTAINER, servingsPerContainer), is(3.5));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_HALF_CONTAINER, servingsPerContainer),
                is(1.75));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_THIRD_CONTAINER, servingsPerContainer),
                is(3.5 / 3.0));

        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_CUP, servingsPerContainer), is(1.0 / 8.0));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_HALF_CUP, servingsPerContainer),
                is(1.0 / 16.0));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_OUNCE, servingsPerContainer), is(1.0));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.TWO_OUNCES, servingsPerContainer), is(1.0 / 2.0));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.ONE_TABLESPOON, servingsPerContainer), is(2.0));
        assertThat(ServingSize.TWO_TABLESPOONS.multiplier(ServingSize.TWO_TABLESPOONS, servingsPerContainer), is(1.0));
    }
}
