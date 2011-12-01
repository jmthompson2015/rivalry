package org.rivalry.core.model;

import org.rivalry.core.util.AbstractProvider;

/**
 * Provides a default category provider.
 */
public class DefaultCategoryProvider extends AbstractProvider<Category>
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Construct this object.
     */
    public DefaultCategoryProvider()
    {
        super();
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param exemplar Exemplar.
     */
    public DefaultCategoryProvider(final DefaultCategory exemplar)
    {
        super(exemplar);
    }

    @Override
    public Category newInstance()
    {
        final Category answer = super.newInstance();

        answer.setName(getExemplar().getName());
        answer.setDescription(getExemplar().getDescription());

        return answer;
    }

    @Override
    protected Category createObject()
    {
        return new DefaultCategory();
    }
}
