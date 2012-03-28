package org.rivalry.swingui;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides tests for the <code>DefaultUIUserPreferences</code> class.
 */
public class DefaultUIUserPreferencesTest
{
    /** User preferences. */
    private final UIUserPreferences _userPreferences = new DefaultUIUserPreferences();

    /**
     * Test the <code>putLookAndFeel()</code> method.
     */
    @Test
    public void putLookAndFeel()
    {
        final LookAndFeel laf = LookAndFeel.getDefaultLookAndFeel();
        _userPreferences.putLookAndFeel(laf);
        final LookAndFeel result = _userPreferences.getLookAndFeel();
        assertNotNull(result);
        assertThat(result.getName(), is("Metal"));
    }

    /**
     * Set up the test.
     */
    @Before
    public void setUp()
    {
        _userPreferences.clearLookAndFeel();
    }

    /**
     * Tear down the test.
     */
    @After
    public void tearDown()
    {
        _userPreferences.clearLookAndFeel();
    }
}
