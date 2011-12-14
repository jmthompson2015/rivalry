package org.rivalry.example.bestplace;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.rivalry.core.model.Candidate;
import org.rivalry.example.dogbreed.BreedNameDataCollector;

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
        assertThat(candidates.size(), is(195));

        {
            final Candidate candidate = candidates.get(0);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Affenpinscher"));
            assertThat(candidate.getPage(),
                    is("http://www.dogtime.com/dog-breeds/affenpinscher"));
        }

        {
            final Candidate candidate = candidates.get(194);
            assertNotNull(candidate);
            assertThat(candidate.getName(), is("Yorkshire Terrier"));
            assertThat(candidate.getPage(),
                    is("http://www.dogtime.com/dog-breeds/yorkshire-terrier"));
        }
    }
}
