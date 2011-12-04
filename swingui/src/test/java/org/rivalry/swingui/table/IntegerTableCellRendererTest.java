//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Provides tests for the <code>IntegerTableCellRenderer</code> class.
 */
public class IntegerTableCellRendererTest
{
    /**
     * Test the <code>setValue()</code> method.
     */
    @Test
    public void setValue()
    {
        final IntegerTableCellRenderer renderer = new IntegerTableCellRenderer();
        assertThat(renderer.getText(), is(""));
        renderer.setValue(12);
        assertThat(renderer.getText(), is("12"));
    }
}
