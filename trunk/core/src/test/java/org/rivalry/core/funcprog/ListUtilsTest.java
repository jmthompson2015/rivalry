package org.rivalry.core.funcprog;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Provides tests for the <code>ListUtils</code> class.
 */
public class ListUtilsTest
{
    /**
     * Test the <code>filter()</code> method.
     */
    @Test
    public void filterEvensDouble()
    {
        final List<Integer> list0 = createIntegerList();
        final MapFunction<Integer, Double> function0 = new IntegerToDoubleFunction();
        final List<Double> list = ListUtils.map(list0, function0);
        final FilterFunction<Double> function = new EvensFunction<Double>();
        final List<Double> result = ListUtils.filter(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(8.0));
        assertThat(result.get(1), is(2.0));
        assertThat(result.get(2), is(6.0));
    }

    /**
     * Test the <code>filter()</code> method.
     */
    @Test
    public void filterEvensInteger()
    {
        final List<Integer> list = createIntegerList();
        final FilterFunction<Integer> function = new EvensFunction<Integer>();
        final List<Integer> result = ListUtils.filter(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(8));
        assertThat(result.get(1), is(2));
        assertThat(result.get(2), is(6));
    }

    /**
     * Test the <code>filter()</code> method.
     */
    @Test
    public void filterNulls()
    {
        List<Integer> list = null;
        final FilterFunction<Integer> function = null;
        List<Integer> result = ListUtils.filter(list, function);
        assertNull(result);

        list = createEmptyIntegerList();
        result = ListUtils.filter(list, function);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test the <code>filter()</code> method.
     */
    @Test
    public void filterOddsDouble()
    {
        final List<Integer> list0 = createIntegerList();
        final MapFunction<Integer, Double> function0 = new IntegerToDoubleFunction();
        final List<Double> list = ListUtils.map(list0, function0);
        final FilterFunction<Double> function = new OddsFunction<Double>();
        final List<Double> result = ListUtils.filter(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(5.0));
        assertThat(result.get(1), is(7.0));
        assertThat(result.get(2), is(3.0));
    }

    /**
     * Test the <code>filter()</code> method.
     */
    @Test
    public void filterOddsInteger()
    {
        final List<Integer> list = createIntegerList();
        final FilterFunction<Integer> function = new OddsFunction<Integer>();
        final List<Integer> result = ListUtils.filter(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(5));
        assertThat(result.get(1), is(7));
        assertThat(result.get(2), is(3));
    }

    /**
     * Test the <code>first()</code> method.
     */
    @Test
    public void first()
    {
        final List<Integer> list = createIntegerList();
        final Integer result = ListUtils.first(list);
        assertNotNull(result);
        assertThat(result, is(8));
    }

    /**
     * Test the <code>first()</code> method.
     */
    @Test
    public void firstNull()
    {
        final List<Integer> list = null;
        final Integer result = ListUtils.first(list);
        assertNull(result);
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapDoubleInteger()
    {
        final List<Integer> list = createIntegerList();
        final MapFunction<Integer, Integer> function = new DoubleIntegerFunction();
        final List<Integer> result = ListUtils.map(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(list.size()));
        assertThat(result.get(0), is(16));
        assertThat(result.get(1), is(4));
        assertThat(result.get(2), is(10));
        assertThat(result.get(3), is(12));
        assertThat(result.get(4), is(14));
        assertThat(result.get(5), is(6));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapDoubleThenIncrementInteger()
    {
        final List<Integer> list = createIntegerList();
        final MapFunction<Integer, Integer> baseFunction = new DoubleIntegerFunction();
        final MapFunction<Integer, Integer> composedFunction = new IncrementIntegerFunction();
        final ComposedMapFunction<Integer, Integer> function = baseFunction
                .compose(composedFunction);
        final List<Integer> result = ListUtils.map(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(list.size()));
        assertThat(result.get(0), is(8 * 2 + 1));
        assertThat(result.get(1), is(2 * 2 + 1));
        assertThat(result.get(2), is(5 * 2 + 1));
        assertThat(result.get(3), is(6 * 2 + 1));
        assertThat(result.get(4), is(7 * 2 + 1));
        assertThat(result.get(5), is(3 * 2 + 1));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapDoubleThenToString()
    {
        final List<Integer> list = createIntegerList();
        final MapFunction<Integer, Integer> baseFunction = new DoubleIntegerFunction();
        final MapFunction<Integer, String> composedFunction = new ObjectToStringFunction<Integer>();
        final List<Integer> result0 = ListUtils.map(list, baseFunction);
        assertNotNull(result0);
        final List<String> result = ListUtils.map(result0, composedFunction);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(list.size()));
        assertThat(result.get(0), is("16"));
        assertThat(result.get(1), is("4"));
        assertThat(result.get(2), is("10"));
        assertThat(result.get(3), is("12"));
        assertThat(result.get(4), is("14"));
        assertThat(result.get(5), is("6"));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapIncrementInteger()
    {
        final List<Integer> list = createIntegerList();
        final MapFunction<Integer, Integer> function = new IncrementIntegerFunction();
        final List<Integer> result = ListUtils.map(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(list.size()));
        assertThat(result.get(0), is(9));
        assertThat(result.get(1), is(3));
        assertThat(result.get(2), is(6));
        assertThat(result.get(3), is(7));
        assertThat(result.get(4), is(8));
        assertThat(result.get(5), is(4));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapIncrementThenDoubleInteger()
    {
        final List<Integer> list = createIntegerList();
        final MapFunction<Integer, Integer> baseFunction = new IncrementIntegerFunction();
        final MapFunction<Integer, Integer> composedFunction = new DoubleIntegerFunction();
        final ComposedMapFunction<Integer, Integer> function = baseFunction
                .compose(composedFunction);
        final List<Integer> result = ListUtils.map(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(list.size()));
        assertThat(result.get(0), is((8 + 1) * 2));
        assertThat(result.get(1), is((2 + 1) * 2));
        assertThat(result.get(2), is((5 + 1) * 2));
        assertThat(result.get(3), is((6 + 1) * 2));
        assertThat(result.get(4), is((7 + 1) * 2));
        assertThat(result.get(5), is((3 + 1) * 2));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapIntegerToString()
    {
        final List<Integer> list = createIntegerList();
        final MapFunction<Integer, String> function = new ObjectToStringFunction<Integer>();
        final List<String> result = ListUtils.map(list, function);
        assertNotNull(result);
        assertThat(result, instanceOf(list.getClass()));
        assertThat(result.size(), is(list.size()));
        assertThat(result.get(0), is("8"));
        assertThat(result.get(1), is("2"));
        assertThat(result.get(2), is("5"));
        assertThat(result.get(3), is("6"));
        assertThat(result.get(4), is("7"));
        assertThat(result.get(5), is("3"));
    }

    /**
     * Test the <code>map()</code> method.
     */
    @Test
    public void mapNulls()
    {
        List<Integer> list = null;
        final MapFunction<Integer, Integer> function = null;
        List<Integer> result = ListUtils.map(list, function);
        assertNull(result);

        list = createEmptyIntegerList();
        result = ListUtils.map(list, function);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceAddDouble()
    {
        final List<Double> list = createDoubleList();
        final ReduceFunction<Double> function = new AddDoubleFunction();
        final Double result = ListUtils.reduce(list, function);
        assertNotNull(result);
        assertThat(result, is(34.1));
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceAddInteger()
    {
        final List<Integer> list = createIntegerList();
        final ReduceFunction<Integer> function = new AddIntegerFunction();
        final Integer result = ListUtils.reduce(list, function);
        assertNotNull(result);
        assertThat(result, is(31));
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceMaxDouble()
    {
        final List<Double> list = createDoubleList();
        final ReduceFunction<Double> function = new MaxFunction<Double>();
        final Double result = ListUtils.reduce(list, function);
        assertNotNull(result);
        assertThat(result, is(8.8));
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceMaxInteger()
    {
        final List<Integer> list = createIntegerList();
        final ReduceFunction<Integer> function = new MaxFunction<Integer>();
        final Integer result = ListUtils.reduce(list, function);
        assertNotNull(result);
        assertThat(result, is(8));
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceMinDouble()
    {
        final List<Double> list = createDoubleList();
        final ReduceFunction<Double> function = new MinFunction<Double>();
        final Double result = ListUtils.reduce(list, function);
        assertNotNull(result);
        assertThat(result, is(2.2));
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceMinInteger()
    {
        final List<Integer> list = createIntegerList();
        final ReduceFunction<Integer> function = new MinFunction<Integer>();
        final Integer result = ListUtils.reduce(list, function);
        assertNotNull(result);
        assertThat(result, is(2));
    }

    /**
     * Test the <code>reduce()</code> method.
     */
    @Test
    public void reduceNulls()
    {
        final List<Integer> list = null;
        final ReduceFunction<Integer> function = null;
        final Integer result = ListUtils.reduce(list, function);
        assertNull(result);
    }

    /**
     * Test the <code>rest()</code> method.
     */
    @Test
    public void rest()
    {
        final List<Integer> list = createIntegerList();
        final List<Integer> result = ListUtils.rest(list);
        assertNotNull(result);
        assertThat(result.size(), is(list.size() - 1));
    }

    /**
     * Test the <code>rest()</code> method.
     */
    @Test
    public void restNull()
    {
        final List<Integer> list = null;
        final List<Integer> result = ListUtils.rest(list);
        assertNull(result);
    }

    /**
     * @return a new list.
     */
    private List<Double> createDoubleList()
    {
        final List<Double> answer = createEmptyDoubleList();

        answer.add(8.8);
        answer.add(2.2);
        answer.add(5.5);
        answer.add(6.6);
        answer.add(7.7);
        answer.add(3.3);

        return answer;
    }

    /**
     * @return a new list.
     */
    private List<Double> createEmptyDoubleList()
    {
        final List<Double> answer = new ArrayList<Double>();

        return answer;
    }

    /**
     * @return a new list.
     */
    private List<Integer> createEmptyIntegerList()
    {
        final List<Integer> answer = new ArrayList<Integer>();

        return answer;
    }

    /**
     * @return a new list.
     */
    private List<Integer> createIntegerList()
    {
        final List<Integer> answer = createEmptyIntegerList();

        answer.add(8);
        answer.add(2);
        answer.add(5);
        answer.add(6);
        answer.add(7);
        answer.add(3);

        return answer;
    }
}
