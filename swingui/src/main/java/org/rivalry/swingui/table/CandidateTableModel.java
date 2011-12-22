//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.fitness.FitnessProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a table model to present candidate data.
 */
public class CandidateTableModel extends AbstractTableModel
{
    /** Candidate name column index. */
    public static final int CANDIDATE_COLUMN = 1;

    /** Score column index. */
    public static final int SCORE_COLUMN = 0;

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Candidate scores. */
    private Map<Candidate, Double> _candidateScores;

    /** Map of column index to column class. */
    private Map<Integer, Class<?>> _columnToClass = new HashMap<Integer, Class<?>>();

    /** Fitness processor. */
    private final FitnessProcessor _fitnessProcessor;

    /** Rivalry data. */
    private final RivalryData _rivalryData;

    /**
     * Construct this object with the given parameter.
     * 
     * @param rivalryData Rivalry data.
     * @param fitnessFunction Fitness function.
     */
    public CandidateTableModel(final RivalryData rivalryData,
            final FitnessFunction fitnessFunction)
    {
        _rivalryData = rivalryData;
        _fitnessProcessor = new FitnessProcessor(fitnessFunction);

        recomputeCandidateScores();
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex)
    {
        Class<?> answer = _columnToClass.get(columnIndex);

        if (answer == null)
        {
            if (getRowCount() > 0)
            {
                answer = determineColumnClass(columnIndex);
            }
            else
            {
                answer = Object.class;
            }
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
        answer += _rivalryData.getCriteria().size();

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
        return _rivalryData.getCandidates().size();
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
                answer = _candidateScores.get(getCandidate(rowIndex));
                break;

            case CANDIDATE_COLUMN:
                answer = getCandidate(rowIndex).getName();
                break;

            default:
                final Candidate candidate = getCandidate(rowIndex);
                final Criterion criterion = getCriterion(columnIndex);
                answer = candidate.getValue(criterion);
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
     * Recompute the candidate scores.
     */
    public void recomputeCandidateScores()
    {
        final List<Candidate> candidates = _rivalryData.getCandidates();
        _candidateScores = _fitnessProcessor
                .computeCandidateFitness(candidates);
        fireTableDataChanged();
    }

    /**
     * @param columnIndex Column index.
     * 
     * @return column class.
     */
    private Class<?> determineColumnClass(final int columnIndex)
    {
        Class<?> answer = null;

        if (columnIndex == SCORE_COLUMN)
        {
            answer = Double.class;
        }
        else if (columnIndex == CANDIDATE_COLUMN)
        {
            answer = String.class;
        }
        else
        {
            final int size = getRowCount();
            boolean isString = false;
            boolean isDouble = false;
            boolean isInteger = false;

            for (int i = 0; i < size; i++)
            {
                final Object value = getValueAt(i, columnIndex);

                if (value != null)
                {
                    if (value instanceof String)
                    {
                        isString = true;
                    }
                    else if (value instanceof Double)
                    {
                        isDouble = true;
                    }
                    else if (value instanceof Integer)
                    {
                        isInteger = true;
                    }
                }
            }

            if (isString && (isDouble || isInteger))
            {
                // If there is a mix of string and number, object is the lowest
                // common denominator.
                answer = Object.class;
            }
            else if (isString)
            {
                answer = String.class;
            }
            else if (isDouble)
            {
                answer = Double.class;
            }
            else if (isInteger)
            {
                answer = Integer.class;
            }
            else
            {
                answer = Object.class;
            }
        }

        // Cache it.
        _columnToClass.put(columnIndex, answer);

        return answer;
    }

    /**
     * @param rowIndex Row index.
     * 
     * @return the candidate.
     */
    private Candidate getCandidate(final int rowIndex)
    {
        return _rivalryData.getCandidates().get(rowIndex);
    }

    /**
     * @param columnIndex Column index.
     * 
     * @return the criterion.
     */
    private Criterion getCriterion(final int columnIndex)
    {
        final int index = columnIndex - 2;

        return _rivalryData.getCriteria().get(index);
    }
}
