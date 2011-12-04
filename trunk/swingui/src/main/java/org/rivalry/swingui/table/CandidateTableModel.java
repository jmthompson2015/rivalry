//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import javax.swing.table.AbstractTableModel;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a table model to present candidate data.
 */
public class CandidateTableModel extends AbstractTableModel
{
    /** Candidate name column index. */
    private static final int CANDIDATE_COLUMN = 1;

    /** Score column index. */
    private static final int SCORE_COLUMN = 0;

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Rivalry data. */
    private final RivalryData _rivalryData;

    /**
     * Construct this object with the given parameter.
     * 
     * @param rivalryData Rivalry data.
     */
    public CandidateTableModel(final RivalryData rivalryData)
    {
        _rivalryData = rivalryData;
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex)
    {
        Class<?> answer = null;

        switch (columnIndex)
        {
        case SCORE_COLUMN:
            answer = Double.class;
            break;

        case CANDIDATE_COLUMN:
            answer = String.class;
            break;

        default:
            answer = Double.class;
        }

        return answer;
    }

    @Override
    public int getColumnCount()
    {
        int answer = 0;

        // Add one for score.
        answer++;

        // Add one for candidate name.
        answer++;

        // Add criteria count.
        answer += _rivalryData.getCriteriaList().size();

        return answer;
    }

    @Override
    public String getColumnName(final int columnIndex)
    {
        String answer = null;

        switch (columnIndex)
        {
        case SCORE_COLUMN:
            answer = "Score";
            break;

        case CANDIDATE_COLUMN:
            answer = "Candidate";
            break;

        default:
            answer = getCriterion(columnIndex).getName();
        }

        return answer;
    }

    @Override
    public int getRowCount()
    {
        return _rivalryData.getCandidatesList().size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex)
    {
        Object answer = null;

        try
        {
            switch (columnIndex)
            {
            case SCORE_COLUMN:
                answer = getCandidate(rowIndex).getScore();
                break;

            case CANDIDATE_COLUMN:
                answer = getCandidate(rowIndex).getName();
                break;

            default:
                final Candidate candidate = getCandidate(rowIndex);
                final Criterion criterion = getCriterion(columnIndex);
                answer = candidate.getRating(criterion);
                // if (answer == null)
                // {
                // System.out.println("answer null for candidate "
                // + candidate.getName() + " and criterion "
                // + criterion.getName());
                // }
                // System.out.println("getValueAt(" + rowIndex + ", "
                // + columnIndex + ") = " + answer);
                break;
            }
        }
        catch (final Throwable t)
        {
            t.printStackTrace();
        }

        return answer;
    }

    /**
     * @param rowIndex Row index.
     * 
     * @return the candidate.
     */
    private Candidate getCandidate(final int rowIndex)
    {
        return _rivalryData.getCandidatesList().get(rowIndex);
    }

    /**
     * @param columnIndex Column index.
     * 
     * @return the criterion.
     */
    private Criterion getCriterion(final int columnIndex)
    {
        final int index = columnIndex - 2;
        // System.out.println("getting criterion for index = " + index);
        return _rivalryData.getCriteriaList().get(index);
    }
}
