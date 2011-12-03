//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.dogbreed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataReader;
import org.rivalry.core.reporter.DefaultReporter;
import org.rivalry.core.reporter.Reporter;

/**
 * Provides a reporter for skill demand.
 */
public class DogBreedReporterMain
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
            final String inputFile = determineInputFile(commandLine);
            System.out.println("inputFile = [" + inputFile + "]");

            final DogBreedReporterMain main = new DogBreedReporterMain();
            final RivalryData rivalryData = main.readRivalryData(inputFile);

            final Writer writer = new StringWriter();
            final Reporter reporter = new DefaultReporter(
                    DefaultReporter.ReportStyle.CANDIDATE_CRITERION,
                    rivalryData, writer);
            reporter.writeReport();

            System.out.println(writer.toString());
            final File file = new File("Reports.txt");
            reporter.writeToFile(file);
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
        OptionBuilder.withDescription("Dog breed data file");

        final Option inputFile = OptionBuilder.create("f");

        answer.addOption(help);
        answer.addOption(inputFile);

        return answer;
    }

    /**
     * @param commandLine Command line.
     * 
     * @return input file.
     */
    private static final String determineInputFile(final CommandLine commandLine)
    {
        String answer = "DogBreedDataCollector.xml";

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
                        "java [-cp <classpath>] org.rivalry.example.dogbreed.DogBreedReporterMain",
                        options);
    }

    /**
     * @param inputFile Input file.
     * 
     * @return rivalry data.
     */
    private RivalryData readRivalryData(final String inputFile)
    {
        RivalryData answer = null;

        try
        {
            final RivalryDataReader rivalryDataReader = new RivalryDataReader();
            final FileReader reader = new FileReader(inputFile);
            answer = rivalryDataReader.read(reader);
        }
        catch (final FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return answer;
    }
}
