//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.funcprog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * Provides functional programming methods for <code>java.util.List</code>.
 */
public class ListUtils
{
    /**
     * @param list List.
     * @param function Function.
     * 
     * @return a new filtered list.
     */
    public static <E> List<E> filter(final List<E> list,
            final FilterFunction<E> function)
    {
        List<E> answer = null;

        if (list != null)
        {
            answer = newInstance(list);

            if (!list.isEmpty())
            {
                for (final E element : list)
                {
                    if (function.apply(element))
                    {
                        answer.add(element);
                    }
                }
            }
        }

        return answer;
    }

    /**
     * @param list List.
     * 
     * @return first value.
     */
    public static <E> E first(final List<E> list)
    {
        E answer = null;

        if (CollectionUtils.isNotEmpty(list))
        {
            answer = list.get(0);
        }

        return answer;
    }

    /**
     * @param list List.
     * @param function Function.
     * 
     * @return a new mapped list.
     */
    public static <A, B> List<B> map(final List<A> list,
            final MapFunction<A, B> function)
    {
        List<B> answer = null;

        if (list != null)
        {
            // answer = newInstance(list);
            answer = new ArrayList<B>();

            if (!list.isEmpty())
            {
                for (final A element : list)
                {
                    answer.add(function.apply(element));
                }
            }
        }

        return answer;
    }

    /**
     * @param list List.
     * @param function Function.
     * 
     * @return list reduced value.
     */
    public static <E> E reduce(final List<E> list,
            final ReduceFunction<E> function)
    {
        E answer = null;

        if (function != null)
        {
            final E first = first(list);
            final List<E> rest = rest(list);

            if (rest == null)
            {
                answer = first;
            }
            else
            {
                answer = reduce(rest, function, first);
            }
        }

        return answer;
    }

    /**
     * @param list List.
     * @param function Function.
     * @param initialValue Initial value.
     * 
     * @return list reduced value.
     */
    public static <E> E reduce(final List<E> list,
            final ReduceFunction<E> function, final E initialValue)
    {
        List<E> remainingList = rest(list);
        E result = function.apply(initialValue, first(list));

        while (remainingList != null)
        {
            result = function.apply(result, first(remainingList));

            remainingList = rest(remainingList);
        }

        return result;
    }

    /**
     * @param list List.
     * 
     * @return the rest of the list.
     */
    public static <E> List<E> rest(final List<E> list)
    {
        List<E> answer = null;
        if (CollectionUtils.isNotEmpty(list))
        {
            final int fromIndex = 1;
            final int toIndex = list.size();
            answer = list.subList(fromIndex, toIndex);
        }

        return answer;
    }

    /**
     * @param list List.
     * 
     * @return a new instance of the given type of list.
     */
    private static <E> List<E> newInstance(final List<E> list)
    {
        List<E> answer = null;

        try
        {
            @SuppressWarnings("unchecked")
            final List<E> newList = list.getClass().newInstance();
            answer = newList;
        }
        catch (final InstantiationException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        catch (final IllegalAccessException e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return answer;
    }
}
