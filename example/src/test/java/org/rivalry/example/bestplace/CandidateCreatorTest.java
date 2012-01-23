package org.rivalry.example.bestplace;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rivalry.core.model.Candidate;

/**
 * Provides tests for the <code>CandidateCreator</code> class.
 */
public class CandidateCreatorTest
{
    /** Candidate creator. */
    private final CandidateCreator _creator = new CandidateCreator();

    /**
     * Test the <code>create()</code> method.
     */
    @Test
    public void createAbileneTx()
    {
        final Candidate result = _creator.create("Abilene, TX");
        assertNotNull(result);
        assertThat(result.getName(), is("Abilene TX"));
        assertThat(result.getPage(),
                is("http://www.bestplaces.net/city/tx/abilene"));
    }

    /**
     * Test the <code>create()</code> method.
     */
    @Test
    public void createAlbanyNy()
    {
        final Candidate result = _creator.create("Albany-Schenectady-Troy, NY");
        assertNotNull(result);
        assertThat(result.getName(), is("Albany NY"));
        assertThat(result.getPage(),
                is("http://www.bestplaces.net/city/ny/albany"));
    }

    /**
     * Test the <code>create()</code> method.
     */
    @Test
    public void createAllentownPa()
    {
        final Candidate result = _creator
                .create("Allentown-Bethlehem-Easton, PA-NJ");
        assertNotNull(result);
        assertThat(result.getName(), is("Allentown PA"));
        assertThat(result.getPage(),
                is("http://www.bestplaces.net/city/pa/allentown"));
    }

    /**
     * Test the <code>create()</code> method.
     */
    @Test
    public void createBoulderCo()
    {
        final Candidate result = _creator.create("Boulder, CO /1");
        assertNotNull(result);
        assertThat(result.getName(), is("Boulder CO"));
        assertThat(result.getPage(),
                is("http://www.bestplaces.net/city/co/boulder"));
    }

    /**
     * Test the <code>create()</code> method.
     */
    @Test
    public void createLouisvilleKy()
    {
        final Candidate result = _creator
                .create("Louisville/Jefferson County, KY-IN");
        assertNotNull(result);
        assertThat(result.getName(), is("Louisville KY"));
        assertThat(result.getPage(),
                is("http://www.bestplaces.net/city/ky/louisville"));
    }
}
