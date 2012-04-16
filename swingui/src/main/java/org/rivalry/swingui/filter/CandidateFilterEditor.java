package org.rivalry.swingui.filter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.rivalry.core.model.Criterion;

/**
 * Provides an editor for a <code>CandidateFilter</code> object.
 */
public class CandidateFilterEditor extends JPanel
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Filter clause widget. */
    JList _filterClauseUI;

    /** Candidate filter clause list model. */
    DefaultListModel _listModel = new DefaultListModel();

    /** Edit button. */
    private JButton _editButton;

    /** Delete button. */
    private JButton _deleteButton;

    /** Criteria. */
    final List<Criterion> _criteria;

    /**
     * Construct this object with the given parameters.
     * 
     * @param criteria Criteria.
     * @param candidateFilter Candidate filter.
     */
    public CandidateFilterEditor(final List<Criterion> criteria, final CandidateFilter candidateFilter)
    {
        _criteria = criteria;
        _filterClauseUI = createFilterClauseUI();
        final JPanel buttonPanel = createButtonPanel();

        setLayout(new BorderLayout());

        add(new JScrollPane(_filterClauseUI), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        if (candidateFilter != null)
        {
            for (final FilterClause filterClause : candidateFilter.getFilterClauses())
            {
                _listModel.addElement(filterClause);
            }
        }

        checkActions();
    }

    /**
     * @return candidateFilter
     */
    public CandidateFilter getCandidateFilter()
    {
        final CandidateFilter answer = new CandidateFilter();

        final int size = _listModel.getSize();

        for (int i = 0; i < size; i++)
        {
            final FilterClause filterClause = (FilterClause)_listModel.get(i);
            answer.add(filterClause);
        }

        return answer;
    }

    /**
     * Check the enabled state of actions.
     */
    void checkActions()
    {
        final boolean isSelected = _filterClauseUI.getSelectedValue() != null;
        _editButton.setEnabled(isSelected);
        _deleteButton.setEnabled(isSelected);
    }

    /**
     * @return a new button panel.
     */
    private JPanel createButtonPanel()
    {
        final JButton newButton = new JButton("New...");
        newButton.addActionListener(createNewActionListener());

        _editButton = new JButton("Edit...");
        _editButton.addActionListener(createEditActionListener());
        _deleteButton = new JButton("Delete");
        _deleteButton.addActionListener(createDeleteActionListener());

        final JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(newButton);
        panel.add(_editButton);
        panel.add(_deleteButton);

        final JPanel answer = new JPanel();

        answer.add(panel);

        return answer;
    }

    /**
     * @return a new delete action listener.
     */
    private ActionListener createDeleteActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                final FilterClause filterClause = (FilterClause)_filterClauseUI.getSelectedValue();
                _listModel.removeElement(filterClause);
            }
        };
    }

    /**
     * @return a new edit action listener.
     */
    private ActionListener createEditActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                final int index = _filterClauseUI.getSelectedIndex();
                final FilterClause filterClause0 = (FilterClause)_filterClauseUI.getSelectedValue();
                final FilterClauseEditor editor = new FilterClauseEditor(_criteria, filterClause0);
                final JPanel panel = new JPanel();
                panel.add(editor);

                final int response = JOptionPane.showConfirmDialog(null, panel, "Edit Filter Clause",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.OK_OPTION)
                {
                    _listModel.removeElement(filterClause0);
                    final FilterClause filterClause = editor.getFilterClause();
                    _listModel.add(index, filterClause);
                }
            }
        };
    }

    /**
     * @return a new filter clause widget.
     */
    private JList createFilterClauseUI()
    {
        final JList answer = new JList(_listModel);

        answer.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(final ListSelectionEvent e)
            {
                checkActions();
            }
        });

        return answer;
    }

    /**
     * @return a new new action listener.
     */
    private ActionListener createNewActionListener()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                final FilterClauseEditor editor = new FilterClauseEditor(_criteria, null);
                final JPanel panel = new JPanel();
                panel.add(editor);

                final int response = JOptionPane.showConfirmDialog(null, panel, "New Filter Clause",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.OK_OPTION)
                {
                    final FilterClause filterClause = editor.getFilterClause();
                    _listModel.addElement(filterClause);
                }
            }
        };
    }
}
