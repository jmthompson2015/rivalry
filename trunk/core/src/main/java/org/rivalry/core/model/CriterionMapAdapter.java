//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Provides an XML adapter for a criterion map.
 */
public class CriterionMapAdapter extends XmlAdapter<CriterionMapModeller, CriterionMap>
{
    @Override
    public CriterionMapModeller marshal(final CriterionMap map) throws Exception
    {
        if (map == null)
        {
            throw new RuntimeException("map is null");
        }

        final CriterionMapModeller modeller = new CriterionMapModeller();

        if (map.entrySet() == null)
        {
            throw new RuntimeException("map.entrySet() is null");
        }

        for (final Map.Entry<Criterion, Object> entry : map.entrySet())
        {
            final CriterionMapModeller.Entry e = new CriterionMapModeller.Entry();
            e.setKey(entry.getKey());
            e.setValue(entry.getValue());
            modeller.getEntry().add(e);
        }

        return modeller;
    }

    @Override
    public CriterionMap unmarshal(final CriterionMapModeller modeller) throws Exception
    {
        final CriterionMap map = new CriterionMap();

        for (final CriterionMapModeller.Entry e : modeller.getEntry())
        {
            map.put(e.getKey(), e.getValue());
        }

        return map;
    }
}
