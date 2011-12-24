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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.UserPreferences;
import org.rivalry.swingui.table.CandidateTableModel;
import org.rivalry.swingui.table.CriterionTableModel;
import org.rivalry.swingui.table.VisibleColumnsPopupMenu;
import org.rivalry.swingui.table.VisibleColumnsTableModel;

/**
 * Provides a main panel for the Rivalry GUI.
 */
public class RivalryMainPanel extends JSplitPane
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Criterion table model. */
    private CriterionTableModel _criterionTableModel;

    /** Candidate table model. */
    CandidateTableModel _candidateTableModel;

    /** User preferences. */
    private final UserPreferences _userPreferences = new UserPreferences();

    /**
     * Construct this object with the given parameter.
     * 
     * @param rivalryData Rivalry data.
     */
    public RivalryMainPanel(final RivalryData rivalryData)
    {
        setLeftComponent(createCriterionPanel(rivalryData));
        setRightComponent(createCandidatePanel(rivalryData,
                _criterionTableModel.getFitnessFunction()));

        setOneTouchExpandable(true);
    }

    /**
     * @return rivalryData
     */
    public RivalryData getRivalryData()
    {
        return _criterionTableModel.getRivalryData();
    }

    /**
     * @param rivalryData Rivalry data.
     * @param fitnessFunction Fitness function.
     * 
     * @return a new criterion panel.
     */
    private JPanel createCandidatePanel(final RivalryData rivalryData,
            final FitnessFunction fitnessFunction)
    {
        _candidateTableModel = new CandidateTableModel(rivalryData,
                fitnessFunction);
        final String preferencePrefix = rivalryData.getPreferencePrefix()
                + "/candidateTable";
        final VisibleColumnsTableModel vcTableModel = new VisibleColumnsTableModel(
                _candidateTableModel, preferencePrefix);

        final SortTablePanel candidateSortTablePanel = new SortTablePanel(
                vcTableModel, createTableSortKeys(preferencePrefix),
                preferencePrefix, rivalryData.getCreateDate());

        final VisibleColumnsPopupMenu popupMenu = new VisibleColumnsPopupMenu(
                rivalryData, vcTableModel);
        candidateSortTablePanel.getTable().getTableHeader()
                .addMouseListener(new PopupListener(popupMenu));

        return createTitledSortTablePanel("Candidates", candidateSortTablePanel);
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
        final String preferencePrefix = rivalryData.getPreferencePrefix()
                + "/criterionTable";

        if (rivalryData.getCategories().isEmpty())
        {
            // There are no categories, so hide the category column.
            final VisibleColumnsTableModel vcTableModel = new VisibleColumnsTableModel(
                    _criterionTableModel, preferencePrefix);
            criterionSortTablePanel = new SortTablePanel(vcTableModel,
                    createTableSortKeys(preferencePrefix), preferencePrefix,
                    null);
            vcTableModel.setColumnVisible(CriterionTableModel.CATEGORY_COLUMN,
                    false);
        }
        else
        {
            criterionSortTablePanel = new SortTablePanel(_criterionTableModel,
                    createTableSortKeys(preferencePrefix), preferencePrefix,
                    null);
        }

        return createTitledSortTablePanel("Criteria", criterionSortTablePanel);
    }

    /**
     * @param preferencePrefix Preference prefix.
     * 
     * @return table sort keys.
     */
    private List<SortKey> createTableSortKeys(final String preferencePrefix)
    {
        List<SortKey> answer = null;

        final SortKey sortKey = _userPreferences.getSortKey(preferencePrefix);

        if (sortKey != null)
        {
            answer = new ArrayList<SortKey>();
            answer.add(sortKey);
        }

        return answer;
    }

    /**
     * @param title Title.
     * @param panel Sort table panel.
     * 
     * @return a new criterion panel.
     */
    private JPanel createTitledSortTablePanel(final String title,
            final SortTablePanel panel)
    {

        final JPanel answer = new JPanel(new BorderLayout());

        answer.add(new JLabel(title), BorderLayout.NORTH);
        answer.add(panel, BorderLayout.CENTER);

        return answer;
    }
}
