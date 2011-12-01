package org.rivalry.core.reporter;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.CandidateRatingComparator;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a default implementation of a reporter.
 */
public class DefaultReporter implements Reporter
{
    /** Label width. */
    private static final int LABEL_WIDTH = 10;

    /** Column width. */
    private static final int COLUMN_WIDTH = 8;

    /** Horizontal rule. */
    private static final String HORIZONTAL_RULE = "-------------------";

    /** Line separator. */
    private static final String LINE_SEPARATOR = System
            .getProperty("line.separator");

    /** Rivalry data. */
    private final RivalryData _rivalryData;

    /** Writer. */
    private final Writer _writer;

    /**
     * Construct this object with the given parameters.
     * 
     * @param rivalryData Rivalry data.
     * @param writer Writer.
     */
    public DefaultReporter(final RivalryData rivalryData, final Writer writer)
    {
        _rivalryData = rivalryData;
        _writer = writer;
    }

    @Override
    public void writeReport()
    {
        final List<Criterion> criteria = _rivalryData.getCriteriaList();

        for (final Criterion criterion : criteria)
        {
            writeLine("Criterion: " + criterion.getName());

            writeTitleRow();

            // Sort the candidates by their rating for this criterion.
            final List<Candidate> candidates = createSortedList(criterion);

            for (final Candidate candidate : candidates)
            {
                final Double rating = candidate.getRating(criterion);
                writeLine(String.format("%-" + LABEL_WIDTH + "s %,"
                        + COLUMN_WIDTH + ".0f", candidate.getName(), rating));
            }
        }
    }

    @Override
    public void writeToFile(final File file)
    {
        try
        {
            final String content = getWriter().toString();
            FileUtils.writeStringToFile(file, content);
        }
        catch (final IOException unexpected)
        {
            unexpected.printStackTrace();
        }
    }

    /**
     * @param criterion Criterion.
     * 
     * @return a new sorted list of candidates.
     */
    private List<Candidate> createSortedList(final Criterion criterion)
    {
        final List<Candidate> answer = new ArrayList<Candidate>();

        answer.addAll(_rivalryData.getCandidatesList());
        final Comparator<Candidate> comparator = new CandidateRatingComparator(
                criterion);
        Collections.sort(answer, comparator);

        return answer;
    }

    /**
     * @return the writer
     */
    private Writer getWriter()
    {
        return _writer;
    }

    /**
     * @param string String.
     */
    private void write(final String string)
    {
        try
        {
            getWriter().write(string);
        }
        catch (final IOException ignore)
        {
            // Nothing to do.
        }
    }

    /**
     * @param line Line.
     */
    private void writeLine(final String line)
    {
        write(line);
        writeLineSeparator();
    }

    /**
     * Write a line separator.
     */
    private void writeLineSeparator()
    {
        write(LINE_SEPARATOR);
    }

    /**
     * Write a title row.
     */
    private void writeTitleRow()
    {
        writeLine(String.format("%n%-" + LABEL_WIDTH + "s %" + COLUMN_WIDTH
                + "s", "Candidate", "Rating"));
        writeLine(String.format("%s", HORIZONTAL_RULE));
    }
}
