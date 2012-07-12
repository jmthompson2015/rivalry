//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.skilldemand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataWriter;

/**
 * Provides a data collector for skill demand.
 */
public class SkillDemandDataCollectorMain
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
            final DataCollectorInjector injector = new SkillDemandInjector();
            final DataCollector dataCollector = injector.injectDataCollector();

            final SkillDemandDataCollectorMain main = new SkillDemandDataCollectorMain();

            final DCSpec dcSpec = main.createDCSpec();
            final String username = null;
            final String password = null;
            final RivalryData rivalryData = injector.injectRivalryData();

            final boolean isSimple = false;

            if (isSimple)
            {
                final List<String> keywords = main.getKeywords();
                main.createCandidates(keywords, rivalryData);
            }
            else
            {
                final List<String> languages = main.getLanguages();
                main.createCandidates(languages, rivalryData);
                final List<String> tools = main.getTools();
                main.createCandidates(tools, rivalryData);
            }

            System.out.println("candidates.size() = " + rivalryData.getCandidates().size());
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
    private static final String determineOutputFile(final CommandLine commandLine)
    {
        String answer = "SkillDemandRivalryData.xml";

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
        formatter.printHelp("java [-cp <classpath>] org.rivalry.example.skilldemand.SkillDemandDataCollectorMain",
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
     * @param keywords Keywords.
     * @param rivalryData Rivalry data.
     */
    private void createCandidates(final List<String> keywords, final RivalryData rivalryData)
    {
        final List<Candidate> candidates = rivalryData.getCandidates();

        for (final String keyword : keywords)
        {
            final String url = createUrl(keyword);
            candidates.add(createCandidate(keyword, url));
        }
    }

    /**
     * @return a new data collector specification.
     */
    private DCSpec createDCSpec()
    {
        final DCSpecReader dcReader = new DCSpecReader();
        final InputStream inputStream = getClass().getResourceAsStream("DataCollectorDice.xml");
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpec answer = dcReader.read(reader);

        return answer;
    }

    /**
     * @param keyword Search keyword.
     * 
     * @return URL.
     */
    private String createUrl(final String keyword)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(keyword))
        {
            String myKeyword = null;
            try
            {
                myKeyword = URLEncoder.encode(keyword, "UTF-8");
            }
            catch (final UnsupportedEncodingException ignore)
            {
                // Nothing to do.
            }

            answer = "http://seeker.dice.com/jobsearch/servlet/JobSearch" + "?op=300" + "&QUICK=1" + "&FREE_TEXT="
                    + myKeyword;

            if (keyword.toLowerCase().contains(" and ") || keyword.toLowerCase().contains(" or "))
            {
                // Construct a boolean search URL.
                answer += "&Ntx=mode+matchboolean";
            }
            else
            {
                answer += "&Ntx=mode+matchall";
            }
        }

        return answer;
    }

    /**
     * @return Search keywords.
     */
    private List<String> getKeywords()
    {
        final List<String> answer = new ArrayList<String>();

        answer.add("Java");
        answer.add("JPA");
        answer.add("Vaadin");
        answer.add("Visual Basic");
        answer.add("ROS or \"robot operating system\"");

        return answer;
    }

    /**
     * @return language keywords.
     */
    private List<String> getLanguages()
    {
        return readKeywords("languages.txt");
    }

    /**
     * @return tool keywords.
     */
    private List<String> getTools()
    {
        return readKeywords("tools.txt");
    }

    /**
     * @param filename Filename.
     * 
     * @return keywords.
     */
    private List<String> readKeywords(final String filename)
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
            rivalryDataWriter.write(rivalryData, writer);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
