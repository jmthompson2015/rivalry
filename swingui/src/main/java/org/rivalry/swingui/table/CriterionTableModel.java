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

import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.fitness.WeightedSumFitnessFunction;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a table model to present criterion data.
 * <ul>
 * <li>Make weight column editable</li>
 * </ul>
 */
public class CriterionTableModel extends AbstractTableModel
{
    /** Category column index. */
    public static final int CATEGORY_COLUMN = 0;

    /** Criterion column index. */
    public static final int CRITERION_COLUMN = 1;

    /** Weight column index. */
    public static final int WEIGHT_COLUMN = 2;

    /** Column names. */
    private static final String[] COLUMN_NAMES = { "Category", "Criterion", "Weight", };

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Weighted sum fitness function. */
    private final WeightedSumFitnessFunction _fitnessFunction;

    /** Rivalry data. */
    private final RivalryData _rivalryData;

    /**
     * Construct this object with the given parameter.
     *
     * @param rivalryData Rivalry data.
     */
    public CriterionTableModel(final RivalryData rivalryData)
    {
        _rivalryData = rivalryData;
        _fitnessFunction = new WeightedSumFitnessFunction(_rivalryData.getPreferencePrefix(), _rivalryData);
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex)
    {
        Class<?> answer = null;

        switch (columnIndex)
        {
        case CATEGORY_COLUMN:
            answer = String.class;
            break;

        case CRITERION_COLUMN:
            answer = String.class;
            break;

        case WEIGHT_COLUMN:
            answer = Integer.class;
            break;
        }

        return answer;
    }

    @Override
    public int getColumnCount()
    {
        int answer = 0;

        // Add one for category name.
        answer++;

        // Add one for criterion name.
        answer++;

        // Add one for weight.
        answer++;

        return answer;
    }

    @Override
    public String getColumnName(final int columnIndex)
    {
        return COLUMN_NAMES[columnIndex];
    }

    /**
     * @return the fitnessFunction
     */
    public FitnessFunction getFitnessFunction()
    {
        return _fitnessFunction;
    }

    /**
     * @return the rivalryData
     */
    public RivalryData getRivalryData()
    {
        return _rivalryData;
    }

    @Override
    public int getRowCount()
    {
        return _rivalryData.getCriteria().size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex)
    {
        Object answer = null;

        switch (columnIndex)
        {
        case CATEGORY_COLUMN:
            final Category category = getCriterion(rowIndex).getCategory();
            if (category != null)
            {
                answer = category.getName();
            }
            break;

        case CRITERION_COLUMN:
            answer = getCriterion(rowIndex).getName();
            break;

        case WEIGHT_COLUMN:
            answer = _fitnessFunction.getWeight(getCriterion(rowIndex));
            if (answer == null)
            {
                answer = 0;
            }
            break;

        default:
            throw new IllegalArgumentException("Unknown columnIndex: " + columnIndex);
        }

        return answer;
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex)
    {
        return columnIndex == WEIGHT_COLUMN;
    }

    @Override
    public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex)
    {
        if (aValue instanceof Number)
        {
            final Integer newWeight = ((Number)aValue).intValue();
            final Criterion criterion = getCriterion(rowIndex);
            _fitnessFunction.putWeight(criterion, newWeight);
            fireTableDataChanged();
        }
    }

    /**
     * @param rowIndex Row index.
     *
     * @return the criterion.
     */
    private Criterion getCriterion(final int rowIndex)
    {
        return _rivalryData.getCriteria().get(rowIndex);
    }
}
