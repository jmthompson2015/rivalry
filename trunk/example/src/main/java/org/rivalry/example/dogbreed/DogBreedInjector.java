//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.dogbreed;

import org.rivalry.core.datacollector.DataPostProcessor;
import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.datacollector.ValueStringParser;

/**
 * Provides a data collector injector for dog breeds.
 */
public class DogBreedInjector extends DefaultDataCollectorInjector
{
    @Override
    public DataPostProcessor injectDataPostProcessor()
    {
        return new DogBreedDataPostProcessor();
    }

    @Override
    public ValueStringParser injectValueStringParser()
    {
        return new DogBreedsParser();
    }
}
