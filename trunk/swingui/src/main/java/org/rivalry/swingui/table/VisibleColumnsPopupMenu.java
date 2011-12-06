//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a popup menu for visible table columns.
 */
public class VisibleColumnsPopupMenu extends JPopupMenu
{
    /**
     * Provides an action to set selection of all items in a menu.
     */
    private static class SelectAllAction extends AbstractAction
    {
        /** Serial version UID. */
        private static final long serialVersionUID = 1L;

        /** Menu. */
        private final JComponent _menu;

        /** Flag indicating whether the items should be selected. */
        private final boolean _isSelected;

        /**
         * Construct this object with the given parameters.
         * 
         * @param text Action text.
         * @param menu Menu.
         * @param isSelected Flag indicating whether the items should be
         *            selected.
         */
        public SelectAllAction(final String text, final JComponent menu,
                final boolean isSelected)
        {
            super(text);

            _menu = menu;
            _isSelected = isSelected;
        }

        @Override
        public void actionPerformed(final ActionEvent event)
        {
            if (_menu instanceof JMenu)
            {
                clickAllCheckBoxes((JMenu)_menu);
            }
            else
            {
                clickAllCheckBoxes(_menu);
            }
        }

        /**
         * @param menu Menu.
         */
        private void clickAllCheckBoxes(final JComponent menu)
        {
            final int size = menu.getComponentCount();

            for (int i = 0; i < size; i++)
            {
                final Component component = menu.getComponent(i);

                if (component instanceof JMenu)
                {
                    clickAllCheckBoxes((JMenu)component);
                }
                else
                {
                    maybeDoClick(component);
                }
            }
        }

        /**
         * @param menu Menu.
         */
        private void clickAllCheckBoxes(final JMenu menu)
        {
            final int size = menu.getItemCount();

            for (int i = 0; i < size; i++)
            {
                final JMenuItem menuItem = menu.getItem(i);
                maybeDoClick(menuItem);
            }
        }

        /**
         * @param component Component.
         */
        private void maybeDoClick(final Component component)
        {
            if (component instanceof JCheckBoxMenuItem)
            {
                final JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem)component;
                if (checkBox.isSelected() != _isSelected)
                {
                    checkBox.doClick();
                }
            }
        }
    }

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Visible columns table model. */
    final VisibleColumnsTableModel _tableModel;

    /**
     * Construct this object with the given parameters.
     * 
     * @param rivalryData Rivalry data.
     * @param tableModel Table model.
     */
    public VisibleColumnsPopupMenu(final RivalryData rivalryData,
            final VisibleColumnsTableModel tableModel)
    {
        _tableModel = tableModel;

        if (rivalryData.getCategoriesList().isEmpty())
        {
            // Just add all the criteria.
            addMenuItems(this, rivalryData.getCriteriaList());
        }
        else
        {
            add(createSelectAllMenuItem(this));
            add(createSelectNoneMenuItem(this));
            addSeparator();

            // Add each category's criteria as a submenu.
            for (final Category category : rivalryData.getCategoriesList())
            {
                final JMenu menu = createMenu(category);
                add(menu);

                final List<Criterion> criteria = rivalryData
                        .findCriteriaByCategory(category);
                addMenuItems(menu, criteria);
            }
        }
    }

    /**
     * @param columnName Column name.
     * 
     * @return the absolute column index.
     */
    int determineColumnIndex(final String columnName)
    {
        int answer = -1;

        if (StringUtils.isNotEmpty(columnName))
        {
            final TableModel dataModel = _tableModel.getDataModel();
            final int size = dataModel.getColumnCount();

            for (int i = 0; answer < 0 && i < size; i++)
            {
                if (columnName.equals(dataModel.getColumnName(i)))
                {
                    answer = i;
                }
            }
        }

        return answer;
    }

    /**
     * @param menu Menu.
     * 
     * @param criteria Criteria.
     */
    private void addMenuItems(final JComponent menu,
            final List<Criterion> criteria)
    {
        menu.add(createSelectAllMenuItem(menu));
        menu.add(createSelectNoneMenuItem(menu));
        if (menu == this)
        {
            ((JPopupMenu)menu).addSeparator();
        }
        else if (menu instanceof JMenu)
        {
            ((JMenu)menu).addSeparator();
        }

        for (final Criterion criterion : criteria)
        {
            menu.add(createMenuItem(criterion));
        }
    }

    /**
     * @param criterion Criterion.
     * 
     * @return a new action listener.
     */
    private ActionListener createActionListener(final Criterion criterion)
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent event)
            {
                final JCheckBoxMenuItem source = (JCheckBoxMenuItem)event
                        .getSource();

                // Determine which column to change.
                final String columnName = source.getText();
                final int absoluteColumnIndex = determineColumnIndex(columnName);

                // Change the column visibility in the table model.
                final boolean isVisible = source.isSelected();
                _tableModel.setColumnVisible(absoluteColumnIndex, isVisible);
            }
        };
    }

    /**
     * @param category Category.
     * 
     * @return a new menu.
     */
    private JMenu createMenu(final Category category)
    {
        final JMenu answer = new JMenu(category.getName());

        return answer;
    }

    /**
     * @param criterion Criterion.
     * 
     * @return a new menu item.
     */
    private JMenuItem createMenuItem(final Criterion criterion)
    {
        final JCheckBoxMenuItem answer = new JCheckBoxMenuItem(
                criterion.getName());

        answer.setSelected(true);
        answer.addActionListener(createActionListener(criterion));

        return answer;
    }

    /**
     * @param menu Menu.
     * 
     * @return a new menu item.
     */
    private JMenuItem createSelectAllMenuItem(final JComponent menu)
    {
        final Action actionSelected = new SelectAllAction("Select All", menu,
                true);

        final JMenuItem answer = new JMenuItem(actionSelected);

        return answer;
    }

    /**
     * @param menu Menu.
     * 
     * @return a new menu item.
     */
    private JMenuItem createSelectNoneMenuItem(final JComponent menu)
    {
        final Action actionUnselected = new SelectAllAction("Select None",
                menu, false);

        final JMenuItem answer = new JMenuItem(actionUnselected);

        return answer;
    }
}
