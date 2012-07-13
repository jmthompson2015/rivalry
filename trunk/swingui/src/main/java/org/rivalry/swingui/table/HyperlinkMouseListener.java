//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2012 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.swingui.table;

import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JTable;
import javax.swing.RowSorter;

import org.rivalry.core.model.Candidate;

/**
 * Provides a mouse listener for hyperlink events on a <code>JTable</code>. The action is to launch the default browser
 * to a URL.
 * <p>
 * This class is specific to the candidate table in RivalryUI.
 * </p>
 */
public class HyperlinkMouseListener extends MouseAdapter
{
    /** Column index. */
    private final int _columnIndex;

    /**
     * Construct this object with the given parameters.
     * 
     * @param columnIndex Hyperlink column index. (required)
     */
    public HyperlinkMouseListener(final int columnIndex)
    {
        _columnIndex = columnIndex;
    }

    /**
     * @return the columnIndex
     */
    public int getColumnIndex()
    {
        return _columnIndex;
    }

    @Override
    public void mouseClicked(final MouseEvent event)
    {
        final Object source = event.getSource();

        if (source instanceof JTable)
        {
            final JTable table = (JTable)source;
            final Point point = event.getPoint();
            final int col = table.columnAtPoint(point);

            if (col == getColumnIndex())
            {
                int row = table.rowAtPoint(point);
                final RowSorter<?> rowSorter = table.getRowSorter();

                if (rowSorter != null)
                {
                    row = rowSorter.convertRowIndexToModel(row);
                }

                // This assumes a particular table model hierarchy.
                final VisibleColumnsTableModel vcTableModel = (VisibleColumnsTableModel)table.getModel();
                final CandidateTableModel cTableModel = (CandidateTableModel)vcTableModel.getDataModel();
                final Candidate candidate = cTableModel.getCandidate(row);

                if (candidate != null)
                {
                    final URL url = createUrl(candidate.getPage());
                    openUrl(url);
                }
            }
        }
    }

    /**
     * Create a URL using the given value. The URL is constructed from a prefix, the cell value, and a suffix.
     * Optionally, the cell value can be ignored.
     * 
     * @param value Cell data value.
     * 
     * @return the URL.
     */
    private URL createUrl(final String value)
    {
        URL answer = null;

        try
        {
            answer = new URL(value);
        }
        catch (final MalformedURLException e)
        {
            System.err.println("Malformed URL: " + value);
        }

        return answer;
    }

    /**
     * @param url URL.
     */
    private void openUrl(final URL url)
    {
        if ((url != null) && Desktop.isDesktopSupported())
        {
            // JDK 1.6.0
            try
            {
                Desktop.getDesktop().browse(url.toURI());
            }
            catch (final IOException e)
            {
                System.err.println("Unable to open URL: " + url);
            }
            catch (final URISyntaxException e)
            {
                System.err.println("Unable to open URL: " + url);
            }
        }
    }
}
