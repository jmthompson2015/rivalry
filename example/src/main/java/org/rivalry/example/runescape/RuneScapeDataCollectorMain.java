package org.rivalry.example.runescape;

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
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.io.RivalryDataWriter;

/**
 * Provides a data collector application for RuneScape.
 */
public class RuneScapeDataCollectorMain
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
            final DataCollectorInjector injector = new RuneScapeInjector();
            final DataCollector dataCollector = injector.injectDataCollector();
            final RivalryData rivalryData = injector.injectRivalryData();

            final DCSpec dcSpec = null;
            final String username = "SalsRealm";
            final String password = null;
            dataCollector.fetchData(dcSpec, username, password, rivalryData);

            final String outputFile = determineOutputFile(commandLine);
            System.out.println("outputFile = [" + outputFile + "]");

            final RuneScapeDataCollectorMain main = new RuneScapeDataCollectorMain();
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
    private static final String determineOutputFile(final CommandLine commandLine)
    {
        String answer = "RuneScapeRivalryData.xml";

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
        formatter.printHelp("java [-cp <classpath>] org.rivalry.example.runescape.RuneScapeDataCollectorMain", options);
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
