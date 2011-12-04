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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.rivalry.core.fitness.FitnessFunction;
import org.rivalry.core.model.RivalryData;
import org.rivalry.swingui.table.CandidateTableModel;
import org.rivalry.swingui.table.CriterionTableModel;
import org.rivalry.swingui.table.IntegerTableCellRenderer;

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
        final SortTablePanel candidateSortTablePanel = new SortTablePanel(
                _candidateTableModel);

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
                System.out.println("criterionTableModel changed");
                _candidateTableModel.recomputeCandidateScores();
            }
        });

        final SortTablePanel criterionSortTablePanel = new SortTablePanel(
                _criterionTableModel);
        criterionSortTablePanel.getTable().setDefaultRenderer(Integer.class,
                new IntegerTableCellRenderer());

        return createTitledSortTablePanel("Criteria", criterionSortTablePanel);
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
