//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.bestplace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.lang.StringUtils;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DefaultDataCollector;
import org.rivalry.core.datacollector.DefaultNameStringParser;
import org.rivalry.core.datacollector.NameStringParser;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataWriter;
import org.rivalry.core.util.Provider;

/**
 * Provides a data collector for dog breeds.
 */
public class BestPlaceDataCollectorMain
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
        final Options options = createOptions();
        final CommandLineParser parser = new PosixParser();
        final CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption('h'))
        {
            printHelp(options);
        }
        else
        {
            final NameStringParser nameStringParser = new DefaultNameStringParser();
            final ValueStringParser valueStringParser = new BestPlaceValueParser();
            final Provider<Category> categoryProvider = new DefaultCategoryProvider();
            final Provider<Criterion> criterionProvider = new DefaultCriterionProvider();
            final DataCollector dataCollector = new DefaultDataCollector(
                    nameStringParser, valueStringParser, categoryProvider,
                    criterionProvider);

            final BestPlaceDataCollectorMain main = new BestPlaceDataCollectorMain();

            final DCSpec dcSpec = main.createDCSpec();
            final String username = null;
            final String password = null;
            final RivalryData rivalryData = new RivalryData();

            final List<String> keywords = main.getKeywords();
            main.createCandidates(dcSpec, keywords, rivalryData);

            System.out.println("candidates.size() = "
                    + rivalryData.getCandidatesList().size());
            dataCollector.fetchData(dcSpec, username, password, rivalryData);

            final String outputFile = determineOutputFile(commandLine);
            System.out.println("outputFile = [" + outputFile + "]");

            main.writeToFile(outputFile, rivalryData);
        }
    }

    /**
     * @return application command line options.
     */
    private static final Options createOptions()
    {
        final Options answer = new Options();

        final Option help = new Option("h", "print this message");

        OptionBuilder.withArgName("file");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("output file");

        final Option outputFile = OptionBuilder.create("f");

        answer.addOption(help);
        answer.addOption(outputFile);

        return answer;
    }

    /**
     * @param commandLine Command line.
     * 
     * @return output file.
     */
    private static final String determineOutputFile(
            final CommandLine commandLine)
    {
        String answer = "BestPlaceRivalryData.xml";

        final char option = 'f';

        if (commandLine.hasOption(option))
        {
            answer = commandLine.getOptionValue(option);
        }

        return answer;
    }

    /**
     * Print the help text.
     * 
     * @param options Command line options.
     */
    private static final void printHelp(final Options options)
    {
        // Automatically generate the help statement.
        final HelpFormatter formatter = new HelpFormatter();
        formatter
                .printHelp(
                        "java [-cp <classpath>] org.rivalry.example.bestplace.BestPlaceDataCollectorMain",
                        options);
    }

    /**
     * @param name Name.
     * @param url URL.
     * 
     * @return a new candidate.
     */
    private Candidate createCandidate(final String name, final String url)
    {
        final Candidate answer = new DefaultCandidate();

        answer.setName(name);
        answer.setPage(url);

        return answer;
    }

    /**
     * @param dcSpec Data collector specification.
     * @param keywords Keywords.
     * @param rivalryData Rivalry data.
     */
    private void createCandidates(final DCSpec dcSpec,
            final List<String> keywords, final RivalryData rivalryData)
    {
        final List<Candidate> candidates = rivalryData.getCandidatesList();

        for (final String keyword : keywords)
        {
            final String url = createUrl(dcSpec, keyword);
            candidates.add(createCandidate(keyword, url));
        }
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpec()
    {
        final DCSpecReader dcReader = new DCSpecReader();
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorBestPlaces.xml");
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpec answer = dcReader.read(reader);

        return answer;
    }

    /**
     * @param dcSpec Data collector specification.
     * @param keyword Search keyword.
     * 
     * @return URL.
     */
    private String createUrl(final DCSpec dcSpec, final String keyword)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(keyword))
        {
            String myKeyword = null;
            // try
            // {
            // myKeyword = URLEncoder.encode(keyword, "UTF-8");
            // }
            // catch (final UnsupportedEncodingException ignore)
            // {
            // // Nothing to do.
            // }
            myKeyword = keyword;

            final String url = dcSpec.getUrl();
            answer = url.replaceAll("\\$1", myKeyword);
        }

        return answer;
    }

    /**
     * @return Search keywords.
     */
    private List<String> getKeywords()
    {
        final List<String> answer = new ArrayList<String>();

        answer.add("oregon/portland");
        answer.add("colorado/denver");
        // answer.add("california/san_diego");
        answer.add("arizona/phoenix");
        answer.add("california/los%20angeles");

        return answer;
    }

    /**
     * @param outputFile Output file.
     * @param rivalryData Rivalry data.
     */
    private void writeToFile(final String outputFile,
            final RivalryData rivalryData)
    {
        try
        {
            final RivalryDataWriter rivalryDataWriter = new RivalryDataWriter();
            final File myOutputFile = new File(outputFile);
            final FileWriter writer = new FileWriter(myOutputFile);
            rivalryDataWriter.write(rivalryData, writer);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
