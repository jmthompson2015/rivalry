package org.rivalry.core.funcprog;

/**
 * Provides an integer to double function.
 */
public class IntegerToDoubleFunction extends MapFunction<Integer, Double>
{
    @Override
    public Double apply(final Integer x)
    {
        Double answer = null;

        if (x != null)
        {
            answer = x.doubleValue();
        }

        return answer;
    }
}
