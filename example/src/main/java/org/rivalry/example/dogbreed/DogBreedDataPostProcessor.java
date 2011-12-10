//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.dogbreed;

import java.util.List;

import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides an implementation of a data post processor for dog breeds. This
 * implementation simply sets the minimum and maximum for each criterion to the
 * known values of 1 and 5 respectively.
 */
public class DogBreedDataPostProcessor implements DataPostProcessor
{
    @Override
    public void postProcess(final RivalryData rivalryData)
    {
        final Double minRating = 1.0;
        final Double maxRating = 5.0;

        final List<Criterion> criteria = rivalryData.getCriteriaList();

        for (final Criterion criterion : criteria)
        {
            criterion.setMinimumRating(minRating);
            criterion.setMaximumRating(maxRating);
        }
    }
}
