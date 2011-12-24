//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.boardgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataReader;
import org.rivalry.core.model.RivalryDataWriter;

/**
 * Provides a data collector for dog breeds.
 */
public class BoardgameDataCollectorMain
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
            final BoardgameInjector injector = new BoardgameInjector();
            final DataCollector dataCollector = injector.injectDataCollector();

            final BoardgameDataCollectorMain main = new BoardgameDataCollectorMain();

            final DCSpec dcSpec = main.createDCSpec();
            final String username = null;
            final String password = null;
            final RivalryData rivalryData = main.readCandidates();

            dataCollector.fetchData(dcSpec, username, password, rivalryData);

            System.out.println("\n\ncandidates.size() = "
                    + rivalryData.getCandidates().size());

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
        String answer = "BoardgameRivalryData.xml";

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
                        "java [-cp <classpath>] org.rivalry.example.boardgame.BoardgameDataCollectorMain",
                        options);
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpec()
    {
        final DCSpecReader dcReader = new DCSpecReader();
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorBoardGameGeek.xml");
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpec answer = dcReader.read(reader);

        return answer;
    }

    /**
     * @return rivalry data.
     */
    private RivalryData readCandidates()
    {
        RivalryData answer = null;

        final RivalryDataReader rdReader = new RivalryDataReader();
        try
        {
            final Reader reader = new FileReader("BoardgameCandidates.xml");
            answer = rdReader.read(reader);
        }
        catch (final FileNotFoundException e)
        {
            e.printStackTrace();
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
