//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
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
    /** Report style. */
    public enum ReportStyle
    {
        /** Candidate then criterion order. */
        CANDIDATE_CRITERION,
        /** Criterion then candidate order. */
        CRITERION_CANDIDATE;
    }

    /** Column width. */
    private static final int COLUMN_WIDTH = 8;

    /** Horizontal rule. */
    private static final String HORIZONTAL_RULE = "-------------------";

    /** Label width. */
    private static final int LABEL_WIDTH = 10;

    /** Line separator. */
    private static final String LINE_SEPARATOR = System
            .getProperty("line.separator");

    /** Report style. */
    private final ReportStyle _reportStyle;

    /** Rivalry data. */
    private final RivalryData _rivalryData;

    /** Writer. */
    private final Writer _writer;

    /**
     * Construct this object with the given parameters.
     * 
     * @param reportStyle Report style.
     * @param rivalryData Rivalry data.
     * @param writer Writer.
     */
    public DefaultReporter(final ReportStyle reportStyle,
            final RivalryData rivalryData, final Writer writer)
    {
        _reportStyle = reportStyle;
        _rivalryData = rivalryData;
        _writer = writer;
    }

    @Override
    public void writeReport()
    {
        switch (_reportStyle)
        {
        case CRITERION_CANDIDATE:
            writeCriterionCandidateReport();
            break;
        case CANDIDATE_CRITERION:
            writeCandidateCriterionReport();
            break;
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
     * Write a report in candidate then criterion order.
     */
    private void writeCandidateCriterionReport()
    {
        // FIXME: determine maximum criterion name length
        final int labelWidth = 30;
        // FIXME: determine maximum candidate name length
        final int columnWidth = 15;

        final String labelFormat = "%-" + labelWidth + "s";
        final String columnFormat0 = " %" + columnWidth + "s";
        final String columnFormat1 = " %," + columnWidth + ".0f";

        final List<Candidate> candidates = _rivalryData.getCandidatesList();
        final List<Criterion> criteria = _rivalryData.getCriteriaList();

        writeLine(String.format(labelFormat + columnFormat0 + columnFormat0
                + columnFormat0, "Candidates", candidates.get(0).getName(),
                candidates.get(1).getName(), candidates.get(2).getName()));

        for (final Criterion criterion : criteria)
        {
            final int size = candidates.size();
            final Double[] ratings = new Double[size];
            String format = "";
            for (int i = 0; i < size; i++)
            {
                final Candidate candidate = candidates.get(i);
                ratings[i] = candidate.getRating(criterion);
                format += columnFormat1;
            }

            writeLine(String.format(labelFormat + format, criterion.getName(),
                    ratings[0], ratings[1], ratings[2]));
        }
    }

    /**
     * Write a report in criterion then candidate order.
     */
    private void writeCriterionCandidateReport()
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
