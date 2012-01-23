package org.rivalry.example.bestplace;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCategory;
import org.rivalry.core.model.DefaultCriterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataWriter;

/**
 * Provides a data collector for best place names.
 */
public class BestPlaceNameDataCollectorMain
{

    /** Minimum population. */
    private static final Integer MIN_POPULATION = 50000;

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
        System.out.println("start");
        final BestPlaceNameDataCollectorMain dataCollector = new BestPlaceNameDataCollectorMain();
        final BestPlaceInjector injector = new BestPlaceInjector();
        final RivalryData rivalryData = injector.injectRivalryData();
        dataCollector.fetchCandidates(rivalryData);

        final String categoryName = "Basic";
        final Criterion stateCriterion = dataCollector.getCriterion(
                rivalryData, "State", categoryName);
        final Criterion populationCriterion = dataCollector.getCriterion(
                rivalryData, "Population", categoryName);

        for (final Candidate candidate : rivalryData.getCandidates())
        {
            System.out.println(String.format("%30s %-55s %10d %3s",
                    candidate.getName(), candidate.getPage(),
                    candidate.getValue(populationCriterion),
                    candidate.getValue(stateCriterion)));
        }

        System.out.println("Candidate count = "
                + rivalryData.getCandidates().size());

        final RivalryDataWriter rdWriter = new RivalryDataWriter();
        final Writer writer = new FileWriter("BestPlaceCandidates.xml");
        rdWriter.write(rivalryData, writer);
    }

    /** Candidate creator. */
    private final CandidateCreator _candidateCreator = new CandidateCreator();

    /**
     * @param rivalryData Rivalry data.
     */
    public void fetchCandidates(final RivalryData rivalryData)
    {
        final InputStream inputStream = getClass().getResourceAsStream(
                "CBSA-EST2009-alldata.csv");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));

        final String categoryName = "Basic";
        final Criterion stateCriterion = getCriterion(rivalryData, "State",
                categoryName);
        final Criterion populationCriterion = getCriterion(rivalryData,
                "Population", categoryName);

        try
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                // Only include certain areas.
                if (line.contains("Statistical Area"))
                {
                    final String[] data0 = line.split("\"");

                    if (data0.length > 2)
                    {
                        final String name = data0[1];
                        final String[] data1 = data0[2].split(",");
                        final Integer population = Integer.valueOf(data1[13]);

                        if (population >= MIN_POPULATION)
                        {
                            final Candidate candidate = _candidateCreator
                                    .create(name);
                            if (candidate != null)
                            {
                                candidate.putValue(populationCriterion,
                                        population);
                                final String state = determineState(candidate
                                        .getName());
                                candidate.putValue(stateCriterion, state);
                                rivalryData.getCandidates().add(candidate);
                            }
                        }
                    }
                }
            }
        }
        catch (final IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try
            {
                inputStream.close();
            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @param candidateName Candidate name.
     * 
     * @return state.
     */
    private String determineState(final String candidateName)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(candidateName))
        {
            answer = candidateName.trim();
            final int index = answer.lastIndexOf(' ');
            if (index >= 0)
            {
                answer = answer.substring(index);
            }
        }
        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param categoryName Category name.
     * 
     * @return the criterion of the given name, creating it if necessary.
     */
    private Category getCategory(final RivalryData rivalryData,
            final String categoryName)
    {
        Category answer = rivalryData.findCategoryByName(categoryName);

        if (answer == null)
        {
            answer = new DefaultCategory();
            answer.setName(categoryName);

            rivalryData.getCategories().add(answer);
        }

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param criterionName Criterion name.
     * @param categoryName Category name.
     * 
     * @return the criterion of the given name, creating it if necessary.
     */
    private Criterion getCriterion(final RivalryData rivalryData,
            final String criterionName, final String categoryName)
    {
        Criterion answer = rivalryData.findCriterionByName(criterionName);

        if (answer == null)
        {
            answer = new DefaultCriterion();
            answer.setName(criterionName);
            answer.setCategory(getCategory(rivalryData, categoryName));

            rivalryData.getCriteria().add(answer);
        }

        return answer;
    }
}
