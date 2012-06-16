package org.rivalry.example.runescape;

/**
 * Provides a mock grand exchange for testing.
 */
public class MockGrandExchange extends RivalryDataGrandExchange
{
    /** Test data. */
    private static final TestData _testData = new TestData();

    /**
     * Construct this object.
     */
    public MockGrandExchange()
    {
        super(_testData.createRivalryData());
    }
}
