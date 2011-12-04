package org.rivalry.core.funcprog;

/**
 * Provides a double function.
 */
public class DoubleIntegerFunction extends MapFunction<Integer, Integer>
{
    @Override
    public Integer apply(final Integer x)
    {
        return x * 2;
    }
}
