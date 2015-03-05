//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.util.Calendar;
import java.util.List;

/**
 * Defines methods for a data bean for a decision making application.
 */
public interface RivalryData
{
    /**
     * @param name Name.
     *
     * @return the candidate of the given name, if any.
     */
    Candidate findCandidateByName(String name);

    /**
     * @param name Name.
     *
     * @return the category of the given name, if any.
     */
    Category findCategoryByName(String name);

    /**
     * @param category Category.
     *
     * @return the criteria of the given category, if any.
     */
    List<Criterion> findCriteriaByCategory(Category category);

    /**
     * @param name Name.
     *
     * @return the criterion of the given name, if any.
     */
    Criterion findCriterionByName(String name);

    /**
     * @return candidates
     */
    List<Candidate> getCandidates();

    /**
     * @return categories
     */
    List<Category> getCategories();

    /**
     * @return createDate
     */
    Calendar getCreateDate();

    /**
     * @return criteria
     */
    List<Criterion> getCriteria();

    /**
     * @return description
     */
    String getDescription();

    /**
     * @return preferencePrefix
     */
    String getPreferencePrefix();

    /**
     * @param createDateIn Create date.
     */
    void setCreateDate(Calendar createDateIn);

    /**
     * @param descriptionIn Description.
     */
    void setDescription(String descriptionIn);

    /**
     * @param preferencePrefixIn Preference prefix.
     */
    void setPreferencePrefix(String preferencePrefixIn);
}
