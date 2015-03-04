package org.rivalry.core.model;

import java.util.LinkedHashMap;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Provides a map of <code>Criterion</code> to <code>Object</code>.
 */
@XmlJavaTypeAdapter(CriterionMapAdapter.class)
public class CriterionMap extends LinkedHashMap<Criterion, Object>
{
    // Nothing to do.
}
