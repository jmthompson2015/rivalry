package org.rivalry.example.dogbreed;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.cli.ParseException;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataWriter;

/**
 * Provides a data collector for dog breed names.
 */
public class BreedNameDataCollectorMain
{
    /**
     * Application method.
     * 
     * @param args Application arguments.
     * 
     * @throws IOException if there is an I/O problem.
     * @throws ParseException if there is a parsing problem.
     */
    public static final void main(final String[] args) throws IOException,
            ParseException
    {
        final BreedNameDataCollector dataCollector = new BreedNameDataCollector();
        final List<Candidate> candidates = dataCollector.fetchCandidates();

        final DogBreedInjector injector = new DogBreedInjector();
        final RivalryData rivalryData = injector.injectRivalryData();
        rivalryData.getCandidates().addAll(candidates);
        final RivalryDataWriter rdWriter = new RivalryDataWriter();
        final Writer writer = new FileWriter("DogBreedCandidates.xml");
        rdWriter.write(rivalryData, writer);
    }
}
