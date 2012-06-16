package org.rivalry.example.dogbreed;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.rivalry.core.model.Candidate;

/**
 * Provides tests for the <code>BreedNameDataCollector</code> class.
 */
public class BreedNameDataCollectorTest
{
    /**
     * Test the <code>fetchCandidates()</code> method.
     */
    @Test
    public void fetchCandidates()
    {
        final BreedNameDataCollector dataCollector = new BreedNameDataCollector();
        final List<Candidate> candidates = dataCollector.fetchCandidates();

        assertNotNull(candidates);
        assertThat(candidates.size(), is(196));

        {
            final Candidate candidate = candidates.get(0);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Affenpinscher"));
            assertThat(candidate.getPage(), is("http://dogtime.com/dog-breeds/affenpinscher"));
        }

        {
            final Candidate candidate = candidates.get(195);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Yorkshire Terrier"));
            assertThat(candidate.getPage(), is("http://dogtime.com/dog-breeds/yorkshire-terrier"));
        }
    }
}
