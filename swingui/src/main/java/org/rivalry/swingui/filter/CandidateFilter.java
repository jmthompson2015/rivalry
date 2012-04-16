package org.rivalry.swingui.filter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;

import org.rivalry.core.model.Candidate;
import org.rivalry.swingui.table.CandidateTableModel;
import org.rivalry.swingui.table.VisibleColumnsTableModel;

/**
 * Provides a row filter for the candidate table.
 */
public class CandidateFilter extends RowFilter<VisibleColumnsTableModel, Object>
{
    /** Filter clauses. */
    private List<FilterClause> _filterClauses = new ArrayList<FilterClause>();

    /**
     * @param filterClause Filter clause.
     */
    public void add(final FilterClause filterClause)
    {
        _filterClauses.add(filterClause);
    }

    /**
     * @return the filterClauses
     */
    public List<FilterClause> getFilterClauses()
    {
        return _filterClauses;
    }

    @Override
    public boolean include(final javax.swing.RowFilter.Entry<? extends VisibleColumnsTableModel, ? extends Object> entry)
    {
        final VisibleColumnsTableModel tableModel = entry.getModel();
        final CandidateTableModel dataModel = (CandidateTableModel)tableModel.getDataModel();
        final Candidate candidate = dataModel.getCandidate((Integer)entry.getIdentifier());

        return passes(candidate);
    }

    /**
     * @param candidate Candidate.
     * 
     * @return true if the given object passes this filter.
     */
    public boolean passes(final Candidate candidate)
    {
        boolean answer = true;

        final int size = _filterClauses.size();

        // Implicit AND filter clauses.
        for (int i = 0; answer && (i < size); i++)
        {
            final FilterClause filterClause = _filterClauses.get(i);

            if (!filterClause.passes(candidate))
            {
                answer = false;
            }
        }

        return answer;
    }

    /**
     * @param filterClause Filter clause.
     */
    public void remove(final FilterClause filterClause)
    {
        _filterClauses.remove(filterClause);
    }

    /**
     * @return a string representation of this object.
     */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();

        final int size = _filterClauses.size();

        for (int i = 0; i < size; i++)
        {
            final FilterClause filterClause = _filterClauses.get(i);
            sb.append(filterClause);

            if (i < (size - 1))
            {
                sb.append(" AND ");
            }
        }

        return sb.toString();
    }
}
