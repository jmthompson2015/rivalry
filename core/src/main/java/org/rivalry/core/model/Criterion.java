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
 * Defines methods required by a criterion for a decision making application.
 */
public interface Criterion extends Entity
{
    /**
     * @return category
     */
    Category getCategory();

    /**
     * @return maximumRating
     */
    Double getMaximumRating();

    /**
     * @return minimumRating
     */
    Double getMinimumRating();

    /**
     * @return autoMinMax
     */
    Boolean isAutoMinMax();

    /**
     * @param autoMinMaxIn autoMinMax
     */
    void setAutoMinMax(Boolean autoMinMaxIn);

    /**
     * @param categoryIn category
     */
    void setCategory(Category categoryIn);

    /**
     * @param maximumRatingIn maximumRating
     */
    void setMaximumRating(Double maximumRatingIn);

    /**
     * @param minimumRatingIn minimumRating
     */
    void setMinimumRating(Double minimumRatingIn);
}
