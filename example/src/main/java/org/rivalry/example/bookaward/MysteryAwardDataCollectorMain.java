package org.rivalry.example.bookaward;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.io.RivalryDataWriter;

/**
 * Provides a data collector for mystery book award.
 */
public class MysteryAwardDataCollectorMain
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
            final MysteryAwardDataCollectorMain main = new MysteryAwardDataCollectorMain();
            final List<String> authors = main.readAuthors("mysteryAuthors.txt");

            final DataCollectorInjector injector = new MysteryAwardInjector();
            final MysteryAwardDataCollector dataCollector = (MysteryAwardDataCollector)injector.injectDataCollector();
            final RivalryData rivalryData = injector.injectRivalryData();

            System.out.println("criteria.size() = " + rivalryData.getCriteria().size());
            dataCollector.fetchData(authors, rivalryData);

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
    private static final String determineOutputFile(final CommandLine commandLine)
    {
        String answer = "MysteryAwardRivalryData.xml";

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
        formatter.printHelp("java [-cp <classpath>] org.rivalry.example.bookaward.MysteryAwardDataCollectorMain",
                options);
    }

    /**
     * @param filename Filename.
     *
     * @return authors.
     */
    private List<String> readAuthors(final String filename)
    {
        final List<String> answer = new ArrayList<String>();

        final InputStream inputStream = getClass().getResourceAsStream(filename);
        if (inputStream != null)
        {
            try
            {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
