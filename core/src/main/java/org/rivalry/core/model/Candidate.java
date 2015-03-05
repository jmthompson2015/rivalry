//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

/**
 * Defines methods required by a candidate for a decision making application.
 */
public interface Candidate extends Entity
{
    /**
     * @return page
     */
    String getPage();

    /**
     * @param criterion Criterion.
     *
     * @return the rating for the given parameter.
     */
    Double getRating(Criterion criterion);

    /**
     * @param criterionName Criterion name.
     *
     * @return the rating for the given parameter.
     */
    Double getRating(String criterionName);

    /**
     * @param criterion Criterion.
     *
     * @return the value for the given parameter.
     */
    Object getValue(Criterion criterion);

    /**
     * @param criterionName Criterion name.
     *
     * @return the value for the given parameter.
     */
    Object getValue(String criterionName);

    /**
     * @return values
     */
    CriterionMap getValues();

    /**
     * Store the given value for the given criterion.
     *
     * @param criterion Criterion.
     * @param value Value.
     */
    void putValue(Criterion criterion, Object value);

    /**
     * Store the given value for the given criterion.
     *
     * @param criterionName Criterion name.
     * @param value Value.
     */
    void putValue(String criterionName, Object value);

    /**
     * @param pageIn page
     */
    void setPage(String pageIn);

    /**
     * @param valuesIn values
     */
    void setValues(CriterionMap valuesIn);
}
