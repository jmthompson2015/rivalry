//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Provides a table cell renderer for Integer values.
 */
public class IntegerTableCellRenderer extends DefaultTableCellRenderer
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object.
     */
    public IntegerTableCellRenderer()
    {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public void setValue(final Object value)
    {
        String valueString = "";

        if (value instanceof Number)
        {
            final Integer myValue = ((Number)value).intValue();
            if (myValue != null)
            {
                valueString = myValue.toString();
            }
        }

        setText(valueString);
    }
}
