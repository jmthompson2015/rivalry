//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Provides a table cell renderer for a hyperlink.
 */
public class HyperlinkCellRenderer extends DefaultTableCellRenderer
{
    /** Default foreground. */
    public static final Color DEFAULT_FOREGROUND = Color.BLUE;

    /** HTML prefix. */
    private static final String PREFIX = "<html><u>";

    /** HTML suffix. */
    private static final String SUFFIX = "</u></html>";

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column)
    {
        final JLabel answer = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);

        if (value instanceof String)
        {
            // Wrap the value in an HTML anchor tag.
            final StringBuilder sb = new StringBuilder();
            sb.append(PREFIX);
            sb.append(value);
            sb.append(SUFFIX);

            final String link = sb.toString();

            answer.setText(link);

            if (isSelected)
            {
                answer.setForeground(Color.BLACK);
            }
            else
            {
                answer.setForeground(DEFAULT_FOREGROUND);
            }
        }

        return answer;
    }
}
