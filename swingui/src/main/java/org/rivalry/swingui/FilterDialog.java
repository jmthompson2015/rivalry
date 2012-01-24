package org.rivalry.swingui;

import java.awt.BorderLayout;
import java.util.regex.PatternSyntaxException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import org.rivalry.swingui.table.CandidateTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a filter dialog.
 */
public class FilterDialog extends JOptionPane
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FilterDialog.class);

    /** Criteria widget. */
    private final JComboBox _criteriaUI;

    /** Filter text widget. */
    private final JTextField _filterTextUI;

    /** Candidate table model. */
    private final CandidateTableModel _candidateTableModel;

    /**
     * Construct this object with the given parameter.
     * 
     * @param candidateTableModel Candidate table model.
     */
    public FilterDialog(final CandidateTableModel candidateTableModel)
    {
        _candidateTableModel = candidateTableModel;

        _criteriaUI = new JComboBox();

        final int columnCount = _candidateTableModel.getColumnCount();

        for (int i = 0; i < columnCount; i++)
        {
            _criteriaUI.addItem(_candidateTableModel.getColumnName(i));
        }

        _filterTextUI = new JTextField();
        _filterTextUI.setColumns(10);

        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(_criteriaUI, BorderLayout.WEST);
        mainPanel.add(_filterTextUI, BorderLayout.CENTER);

        setMessage(new Object[] { mainPanel, });
        setOptionType(OK_CANCEL_OPTION);
    }

    /**
     * @return a new candidate table row filter.
     */
    public RowFilter<CandidateTableModel, Integer> getCandidateTableRowFilter()
    {
        final String filterText = _filterTextUI.getText();
        final String columnName = (String)_criteriaUI.getSelectedItem();
        final int columnIndex = indexOf(columnName);

        LOGGER.debug("filterText = [" + filterText + "] columnIndex = "
                + columnIndex);

        RowFilter<CandidateTableModel, Integer> answer = null;

        try
        {
            answer = RowFilter.regexFilter(filterText, columnIndex);
        }
        catch (final PatternSyntaxException ignore)
        {
            // Nothing to do.
        }

        return answer;
    }

    /**
     * @param columnName Column name.
     * 
     * @return the column index of the given parameter.
     */
    private int indexOf(final String columnName)
    {
        int answer = -1;

        final int columnCount = _candidateTableModel.getColumnCount();

        for (int i = 0; answer < 0 && i < columnCount; i++)
        {
            if (columnName.equals(_candidateTableModel.getColumnName(i)))
            {
                answer = i;
            }
        }

        return answer;
    }
}
