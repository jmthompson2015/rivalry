//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.fitness.WeightedSumFitnessFunction;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataWriter;
import org.rivalry.core.util.UserPreferences;

/**
 * Provides a data collector for stock.
 */
public class StockDataCollectorMain
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
            final StockInjector injector = new StockInjector();
            final DataCollector dataCollector = injector.injectDataCollector();

            final StockDataCollectorMain main = new StockDataCollectorMain();

            final DCSpec dcSpec = main.createDCSpec();
            final String username = null;
            final String password = null;
            final RivalryData rivalryData = injector.injectRivalryData();

            final UserPreferences userPreferences = new UserPreferences();
            userPreferences.clearCriterionWeights(rivalryData
                    .getPreferencePrefix());

            // main.readCandidates(rivalryData);
            final List<String> symbols = main.getSymbols();
            main.createCandidates(symbols, rivalryData);

            System.out.println("candidates.size() = "
                    + rivalryData.getCandidates().size());
            dataCollector.fetchData(dcSpec, username, password, rivalryData);

            final WeightedSumFitnessFunction fitnessFunction = new WeightedSumFitnessFunction(
                    rivalryData.getPreferencePrefix());
            for (final Candidate candidate : rivalryData.getCandidates())
            {
                fitnessFunction.computeFitness(candidate);
            }

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
        String answer = "StockRivalryData.xml";

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
                        "java [-cp <classpath>] org.rivalry.example.dogbreed.StockDataCollectorMain",
                        options);
    }

    /**
     * @param symbol Stock symbol.
     * 
     * @return a new candidate.
     */
    private Candidate createCandidate(final String symbol)
    {
        final String url = "http://finance.yahoo.com/q/ks?s=" + symbol
                + "+Key+Statistics";

        final Candidate answer = new DefaultCandidate();

        answer.setName(symbol);
        answer.setPage(url);

        return answer;
    }

    /**
     * @param symbols Symbols.
     * @param rivalryData Rivalry data.
     */
    private void createCandidates(final List<String> symbols,
            final RivalryData rivalryData)
    {
        final List<Candidate> candidates = rivalryData.getCandidates();

        for (final String symbol : symbols)
        {
            candidates.add(createCandidate(symbol));
        }
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpec()
    {
        final DCSpecReader dcReader = new DCSpecReader();
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorYahooFinance.xml");
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpec answer = dcReader.read(reader);

        return answer;
    }

    /**
     * @return stock symbols.
     */
    private List<String> getSymbols()
    {
        return readKeywords("symbols.txt");
    }

    /**
     * @param filename Filename.
     * 
     * @return keywords.
     */
    private List<String> readKeywords(final String filename)
    {
        final List<String> answer = new ArrayList<String>();

        final InputStream inputStream = getClass()
                .getResourceAsStream(filename);
        if (inputStream != null)
        {
            try
            {
                final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line = null;

                while ((line = reader.readLine()) != null)
                {
                    if (StringUtils.isNotEmpty(line) && !line.startsWith("#"))
                    {
                        answer.add(line);
                    }
                }
            }
            catch (final FileNotFoundException ignore)
            {
                // Nothing to do.
            }
            catch (final IOException ignore)
            {
                // Nothing to do.
            }
        }

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
