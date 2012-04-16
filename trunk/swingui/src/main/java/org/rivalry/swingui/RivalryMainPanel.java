//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.model.RivalryData;
import org.rivalry.swingui.filter.CandidateFilter;
import org.rivalry.swingui.filter.CandidateFilterEditor;
import org.rivalry.swingui.table.CandidateTableModel;
import org.rivalry.swingui.table.CriterionTableModel;
import org.rivalry.swingui.table.DefaultTableUserPreferences;
import org.rivalry.swingui.table.TableUserPreferences;
import org.rivalry.swingui.table.VisibleColumnsPopupMenu;
import org.rivalry.swingui.table.VisibleColumnsTableModel;

/**
 * Provides a main panel for the Rivalry GUI.
 */
public class RivalryMainPanel extends JSplitPane
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Candidate table model. */
    CandidateTableModel _candidateTableModel;

    /** Candidate filter widget. */
    final JComboBox _filterUI = new JComboBox();

    /** Candidate sort table panel. */
    private SortTablePanel _candidateSortTablePanel;

    /** Criterion table model. */
    private CriterionTableModel _criterionTableModel;

    /** User interface user preferences. */
    final UIUserPreferences _uiUserPreferences;

    /**
     * Construct this object with the given parameter.
     * 
     * @param rivalryData Rivalry data.
     * @param uiUserPreferences User interface user preferences.
     */
    public RivalryMainPanel(final RivalryData rivalryData, final UIUserPreferences uiUserPreferences)
    {
        _uiUserPreferences = uiUserPreferences;

        setLeftComponent(createCriterionPanel(rivalryData));
        setRightComponent(createCandidatePanel(rivalryData, _criterionTableModel.getFitnessFunction()));

        setOneTouchExpandable(true);
    }

    /**
     * @return the candidateSortTablePanel
     */
    public SortTablePanel getCandidateSortTablePanel()
    {
        return _candidateSortTablePanel;
    }

    /**
     * @return the candidateTableModel
     */
    public CandidateTableModel getCandidateTableModel()
    {
        return _candidateTableModel;
    }

    /**
     * @return rivalryData
     */
    public RivalryData getRivalryData()
    {
        return _criterionTableModel.getRivalryData();
    }

    /**
     * @param rowFilter to set
     */
    void setRowFilter(final RowFilter<VisibleColumnsTableModel, Object> rowFilter)
    {
        getCandidateSortTablePanel().setRowFilter(rowFilter);
    }

    /**
     * @return a new candidate filter panel.
     */
    private JPanel createCandidateFilterPanel()
    {
        _filterUI.addItem("Unfiltered");

        final CandidateFilter candidateFilter0 = _uiUserPreferences.getCandidateFilter(getRivalryData());

        if (candidateFilter0 != null)
        {
            _filterUI.addItem(candidateFilter0);
        }

        final URL imageURL = getClass().getClassLoader().getResource("images/Filter24.gif");
        final Icon icon = new ImageIcon(imageURL, "Filter...");
        final JButton filterButton = new JButton(icon);
        filterButton.setMargin(new Insets(0, 1, 1, 1));
        filterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                CandidateFilter candidateFilter = null;

                if (_filterUI.getItemCount() > 1)
                {
                    candidateFilter = (CandidateFilter)_filterUI.getItemAt(1);
                }

                final CandidateFilterEditor editor = new CandidateFilterEditor(getRivalryData().getCriteria(),
                        candidateFilter);
                final JPanel panel = new JPanel();
                panel.add(editor);

                final int response = JOptionPane.showConfirmDialog(null, panel, "Edit Candidate Filter",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.OK_OPTION)
                {
                    if (_filterUI.getItemCount() > 1)
                    {
                        _filterUI.removeItemAt(1);
                    }

                    final CandidateFilter rowFilter = editor.getCandidateFilter();
                    _filterUI.addItem(rowFilter);
                    _filterUI.setSelectedItem(rowFilter);
                    _uiUserPreferences.putCandidateFilter(getRivalryData(), rowFilter);
                }
            }
        });

        _filterUI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                CandidateFilter rowFilter = null;
                final Object selectedItem = _filterUI.getSelectedItem();

                if (selectedItem instanceof CandidateFilter)
                {
                    rowFilter = (CandidateFilter)selectedItem;
                }

                setRowFilter(rowFilter);
            }
        });

        final JPanel answer = new JPanel();

        answer.add(_filterUI);
        answer.add(filterButton);

        return answer;
    }

    /**
     * @param rivalryData Rivalry data.
     * @param fitnessFunction Fitness function.
     * 
     * @return a new criterion panel.
     */
    private JPanel createCandidatePanel(final RivalryData rivalryData, final FitnessFunction fitnessFunction)
    {
        _candidateTableModel = new CandidateTableModel(rivalryData, fitnessFunction);
        final String preferencePrefix = rivalryData.getPreferencePrefix() + "/candidateTable";
        final TableUserPreferences tableUserPreferences = new DefaultTableUserPreferences(preferencePrefix);
        final VisibleColumnsTableModel vcTableModel = new VisibleColumnsTableModel(_candidateTableModel,
                tableUserPreferences);
        final JPanel centerComponent = createCandidateFilterPanel();
        _candidateSortTablePanel = new SortTablePanel(vcTableModel, rivalryData.getCreateDate(), tableUserPreferences,
                centerComponent);

        final VisibleColumnsPopupMenu popupMenu = new VisibleColumnsPopupMenu(rivalryData, vcTableModel);
        _candidateSortTablePanel.getTable().getTableHeader().addMouseListener(new PopupListener(popupMenu));

        return createTitledSortTablePanel("Candidates", _candidateSortTablePanel);
    }

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return a new criterion panel.
     */
    private JPanel createCriterionPanel(final RivalryData rivalryData)
    {
        _criterionTableModel = new CriterionTableModel(rivalryData);

        _criterionTableModel.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(final TableModelEvent event)
            {
                _candidateTableModel.recomputeCandidateScores();
            }
        });

        final SortTablePanel criterionSortTablePanel;
        final String preferencePrefix = rivalryData.getPreferencePrefix() + "/criterionTable";
        final TableUserPreferences tableUserPreferences = new DefaultTableUserPreferences(preferencePrefix);

        if (rivalryData.getCategories().isEmpty())
        {
            // There are no categories, so hide the category column.
            final VisibleColumnsTableModel vcTableModel = new VisibleColumnsTableModel(_criterionTableModel,
                    tableUserPreferences);
            criterionSortTablePanel = new SortTablePanel(vcTableModel, null, tableUserPreferences, null);
            vcTableModel.setColumnVisible(CriterionTableModel.CATEGORY_COLUMN, false);
        }
        else
        {
            criterionSortTablePanel = new SortTablePanel(_criterionTableModel, null, tableUserPreferences, null);
        }

        return createTitledSortTablePanel("Criteria", criterionSortTablePanel);
    }

    /**
     * @param title Title.
     * @param panel Sort table panel.
     * 
     * @return a new criterion panel.
     */
    private JPanel createTitledSortTablePanel(final String title, final SortTablePanel panel)
    {
        final JPanel answer = new JPanel(new BorderLayout());

        answer.add(new JLabel(title), BorderLayout.NORTH);
        answer.add(panel, BorderLayout.CENTER);

        return answer;
    }
}
