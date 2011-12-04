package org.rivalry.core.funcprog;

/**
 * Provides an object to string function.
 * 
 * @param <A> From type.
 */
public class ObjectToStringFunction<A extends Object> extends
        MapFunction<A, String>
{
    @Override
    public String apply(final A x)
    {
        String answer = null;

        if (x != null)
        {
            answer = x.toString();
        }

        return answer;
    }
}
