//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.illyriad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.io.RivalryDataWriter;

/**
 * Provides a data collector for Illyriad.
 */
public class IllyriadDataCollectorMain
{
    /**
     * Application method.
     *
     * @param args Application arguments.
     *
     * @throws IOException if there is an I/O problem.
     * @throws ParseException if there is a parsing problem.
     */
    public static final void main(final String[] args) throws IOException, ParseException
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
            final IllyriadInjector injector = new IllyriadInjector();
            final DataCollector dataCollector = injector.injectDataCollector();

            final DCSpec dcSpec = new DCSpec();
            final String username0 = determineUsername0(commandLine);
            final String password0 = determinePassword0(commandLine);

            final RivalryData rivalryData = injector.injectRivalryData();
            dataCollector.fetchData(dcSpec, username0, password0, rivalryData);

            final String username1 = determineUsername1(commandLine);
            final String password1 = determinePassword1(commandLine);

            if (StringUtils.isNotEmpty(username1))
            {
                final RivalryData rivalryData1 = injector.injectRivalryData();
                rivalryData1.getCategories().addAll(rivalryData.getCategories());
                rivalryData1.getCriteria().addAll(rivalryData.getCriteria());

                dataCollector.fetchData(dcSpec, username1, password1, rivalryData1);

                if (!rivalryData1.getCandidates().isEmpty())
                {
                    rivalryData.getCandidates().addAll(rivalryData1.getCandidates());
                }
            }

            final DataPostProcessor dataPostProcessor = injector.injectDataPostProcessor();
            dataPostProcessor.postProcess(rivalryData);

            final String outputFile = determineOutputFile(commandLine);
            System.out.println("outputFile = [" + outputFile + "]");

            final IllyriadDataCollectorMain main = new IllyriadDataCollectorMain();
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

        OptionBuilder.withArgName("username0");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("first Illyriad username");

        final Option username0 = OptionBuilder.create("u");

        OptionBuilder.withArgName("password0");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("first Illyriad password");

        final Option password0 = OptionBuilder.create("p");

        OptionBuilder.withArgName("username1");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("second Illyriad username");

        final Option username1 = OptionBuilder.create("U");

        OptionBuilder.withArgName("password1");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("second Illyriad password");

        final Option password1 = OptionBuilder.create("P");

        OptionBuilder.withArgName("file");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("output file");

        final Option outputFile = OptionBuilder.create("f");

        answer.addOption(help);
        answer.addOption(username0);
        answer.addOption(password0);
        answer.addOption(username1);
        answer.addOption(password1);
        answer.addOption(outputFile);

        return answer;
    }

    /**
     * @param commandLine Command line.
     *
     * @return output file.
     */
    private static final String determineOutputFile(final CommandLine commandLine)
    {
        String answer = "IllyriadRivalryData.xml";

        final char option = 'f';

        if (commandLine.hasOption(option))
        {
            answer = commandLine.getOptionValue(option);
        }

        return answer;
    }

    /**
     * @param commandLine Command line.
     *
     * @return password.
     */
    private static final String determinePassword0(final CommandLine commandLine)
    {
        String answer = "";

        final char option = 'p';

        if (commandLine.hasOption(option))
        {
            answer = commandLine.getOptionValue(option);
        }

        return answer;
    }

    /**
     * @param commandLine Command line.
     *
     * @return password.
     */
    private static final String determinePassword1(final CommandLine commandLine)
    {
        String answer = "";

        final char option = 'P';

        if (commandLine.hasOption(option))
        {
            answer = commandLine.getOptionValue(option);
        }

        return answer;
    }

    /**
     * @param commandLine Command line.
     *
     * @return username.
     */
    private static final String determineUsername0(final CommandLine commandLine)
    {
        String answer = "";

        final char option = 'u';

        if (commandLine.hasOption(option))
        {
            answer = commandLine.getOptionValue(option);
        }

        return answer;
    }

    /**
     * @param commandLine Command line.
     *
     * @return username.
     */
    private static final String determineUsername1(final CommandLine commandLine)
    {
        String answer = "";

        final char option = 'U';

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
        formatter.printHelp("java [-cp <classpath>] org.rivalry.example.illyriad.IllyriadDataCollectorMain", options);
    }

    /**
     * @param outputFile Output file.
     * @param rivalryData Rivalry data.
     */
    private void writeToFile(final String outputFile, final RivalryData rivalryData)
    {
        try
        {
            final RivalryDataWriter rivalryDataWriter = new RivalryDataWriter();
            final File myOutputFile = new File(outputFile);
            final FileWriter writer = new FileWriter(myOutputFile);
            rivalryDataWriter.write(writer, rivalryData);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
