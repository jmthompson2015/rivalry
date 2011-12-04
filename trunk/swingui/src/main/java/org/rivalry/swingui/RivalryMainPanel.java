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

import org.rivalry.core.fitness.FitnessProcessor;
import org.rivalry.core.fitness.WeightedSumFitnessFunction;
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

    /** Criterion sort table panel. */
    private SortTablePanel _criterionSortTablePanel;

    /** Candidate sort table panel. */
    private SortTablePanel _candidateSortTablePanel;

    /** Fitness processor. */
    FitnessProcessor _fitnessProcessor = new FitnessProcessor(
            new WeightedSumFitnessFunction());

    /**
     * Construct this object with the given parameter.
     * 
     * @param criterionTableModel Criterion table model.
     * @param candidateTableModel Candidate table model.
     */
    public RivalryMainPanel(final CriterionTableModel criterionTableModel,
            final CandidateTableModel candidateTableModel)
    {
        _fitnessProcessor.updateFitness(criterionTableModel.getRivalryData());

        setLeftComponent(createCriterionPanel(criterionTableModel));
        setRightComponent(createCandidatePanel(candidateTableModel));

        criterionTableModel.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(final TableModelEvent event)
            {
                System.out.println("criterionTableModel changed");

                final RivalryData rivalryData = criterionTableModel
                        .getRivalryData();
                _fitnessProcessor.updateFitness(rivalryData);
                candidateTableModel.fireTableDataChanged();
            }
        });
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new criterion panel.
     */
    private JPanel createCandidatePanel(final CandidateTableModel tableModel)
    {
        _candidateSortTablePanel = new SortTablePanel(tableModel);

        return createTitledSortTablePanel("Candidates",
                _candidateSortTablePanel);
    }

    /**
     * @param tableModel Table model.
     * 
     * @return a new criterion panel.
     */
    private JPanel createCriterionPanel(final CriterionTableModel tableModel)
    {
        _criterionSortTablePanel = new SortTablePanel(tableModel);
        _criterionSortTablePanel.getTable().setDefaultRenderer(Integer.class,
                new IntegerTableCellRenderer());

        return createTitledSortTablePanel("Criteria", _criterionSortTablePanel);
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
