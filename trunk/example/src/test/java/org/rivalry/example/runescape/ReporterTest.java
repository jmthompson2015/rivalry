package org.rivalry.example.runescape;

import java.io.StringWriter;

import org.junit.Test;

/**
 * Provides tests for the <code>Reporter</code> class.
 */
public class ReporterTest
{
    /** Grand exchange. */
    private final GrandExchange _grandExchange = new MockGrandExchange();

    /** Reporter. */
    private final Reporter _reporter = new Reporter();

    /**
     * Test the <code>computePremium()</code> method.
     */
    @Test
    public void computePremium()
    {
        final StringWriter writer = new StringWriter();
        _reporter.reportPremiums(writer, _grandExchange);

        System.out.println(writer.toString());
    }
}
