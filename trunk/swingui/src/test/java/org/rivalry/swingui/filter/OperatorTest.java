package org.rivalry.swingui.filter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>Operator</code> class.
 */
public class OperatorTest
{
    /**
     * Test the <code>toString()</code> method.
     */
    @Test
    public void testToString()
    {
        for (final Operator operator : Operator.values())
        {
            System.out.println(operator.getDisplayName() + " " + operator.getDisplaySymbol() + " ["
                    + operator.toString() + "]");
        }
    }

    /**
     * Test the <code>valueOfDisplayName()</code> method.
     */
    @Test
    public void valueOfDisplayName()
    {
        Operator result = Operator.valueOfDisplayName("contains");
        assertNotNull(result);
        assertThat(result, is(Operator.CONTAINS));

        result = Operator.valueOfDisplayName("does not contain");
        assertNotNull(result);
        assertThat(result, is(Operator.DOES_NOT_CONTAIN));

        result = Operator.valueOfDisplayName("is");
        assertNotNull(result);
        assertThat(result, is(Operator.EQ));

        result = Operator.valueOfDisplayName("is not");
        assertNotNull(result);
        assertThat(result, is(Operator.NE));

        result = Operator.valueOfDisplayName("is greater than");
        assertNotNull(result);
        assertThat(result, is(Operator.GT));

        result = Operator.valueOfDisplayName("is less than");
        assertNotNull(result);
        assertThat(result, is(Operator.LT));

        result = Operator.valueOfDisplayName("is greater than or equal");
        assertNotNull(result);
        assertThat(result, is(Operator.GE));

        result = Operator.valueOfDisplayName("is less than or equal");
        assertNotNull(result);
        assertThat(result, is(Operator.LE));
    }

    /**
     * Test the <code>valueOfDisplayName()</code> method.
     */
    @Test
    public void valueOfDisplayNameNull()
    {
        Operator result = Operator.valueOfDisplayName(null);
        assertNull(result);

        result = Operator.valueOfDisplayName("");
        assertNull(result);

        result = Operator.valueOfDisplayName("bogus");
        assertNull(result);
    }

    /**
     * Test the <code>valueOfDisplaySymbol()</code> method.
     */
    @Test
    public void valueOfDisplaySymbol()
    {
        Operator result = Operator.valueOfDisplaySymbol("contains");
        assertNotNull(result);
        assertThat(result, is(Operator.CONTAINS));

        result = Operator.valueOfDisplaySymbol("!contains");
        assertNotNull(result);
        assertThat(result, is(Operator.DOES_NOT_CONTAIN));

        result = Operator.valueOfDisplaySymbol("=");
        assertNotNull(result);
        assertThat(result, is(Operator.EQ));

        result = Operator.valueOfDisplaySymbol("\u2260");
        assertNotNull(result);
        assertThat(result, is(Operator.NE));

        result = Operator.valueOfDisplaySymbol(">");
        assertNotNull(result);
        assertThat(result, is(Operator.GT));

        result = Operator.valueOfDisplaySymbol("<");
        assertNotNull(result);
        assertThat(result, is(Operator.LT));

        result = Operator.valueOfDisplaySymbol("\u2265");
        assertNotNull(result);
        assertThat(result, is(Operator.GE));

        result = Operator.valueOfDisplaySymbol("\u2264");
        assertNotNull(result);
        assertThat(result, is(Operator.LE));
    }

    /**
     * Test the <code>valueOfDisplaySymbol()</code> method.
     */
    @Test
    public void valueOfDisplaySymbolNull()
    {
        Operator result = Operator.valueOfDisplaySymbol(null);
        assertNull(result);

        result = Operator.valueOfDisplaySymbol("");
        assertNull(result);

        result = Operator.valueOfDisplaySymbol("bogus");
        assertNull(result);
    }
}
