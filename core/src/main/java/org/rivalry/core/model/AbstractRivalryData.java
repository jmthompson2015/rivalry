//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011-2015 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Provides a base class for a <code>RivalryData</code>.
 */
public abstract class AbstractRivalryData implements RivalryData
{
    @Override
    public Candidate findCandidateByName(final String name)
    {
        Candidate answer = null;

        if (StringUtils.isNotEmpty(name))
        {
            final List<Candidate> candidates = getCandidates();
            final int size = candidates.size();

            for (int i = 0; (answer == null) && (i < size); i++)
            {
                final Candidate candidate = candidates.get(i);
                if (name.equals(candidate.getName()))
                {
                    answer = candidate;
                }
            }
        }

        return answer;
    }

    @Override
    public Category findCategoryByName(final String name)
    {
        Category answer = null;

        if (StringUtils.isNotEmpty(name))
        {
            final List<Category> categories = getCategories();
            final int size = categories.size();

            for (int i = 0; (answer == null) && (i < size); i++)
            {
                final Category category = categories.get(i);
                if (name.equals(category.getName()))
                {
                    answer = category;
                }
            }
        }

        return answer;
    }

    @Override
    public List<org.rivalry.core.model.Criterion> findCriteriaByCategory(final Category category)
    {
        final List<Criterion> answer = new ArrayList<Criterion>();

        if (category != null)
        {
            final List<Criterion> criteria = getCriteria();
            final int size = criteria.size();

            for (int i = 0; i < size; i++)
            {
                final Criterion criterion = criteria.get(i);
                if (category.equals(criterion.getCategory()))
                {
                    answer.add(criterion);
                }
            }
        }

        return answer;
    }

    @Override
    public Criterion findCriterionByName(final String name)
    {
        Criterion answer = null;

        if (StringUtils.isNotEmpty(name))
        {
            final List<Criterion> criteria = getCriteria();
            final int size = criteria.size();

            for (int i = 0; (answer == null) && (i < size); i++)
            {
                final Criterion criterion = criteria.get(i);
                if (name.equals(criterion.getName()))
                {
                    answer = criterion;
                }
            }
        }

        return answer;
    }
}
