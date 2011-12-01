package org.rivalry.core.util;

/**
 * Provides base functionality for an abstract factory.
 * 
 * @param <T> Type of object manufactured.
 */
public abstract class AbstractProvider<T> implements Provider<T>
{
    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Exemplar. */
    private final T _exemplar;

    /**
     * Construct this object.
     */
    public AbstractProvider()
    {
        _exemplar = createObject();
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param exemplar Exemplar.
     */
    public AbstractProvider(final T exemplar)
    {
        _exemplar = exemplar;
    }

    @Override
    public T newInstance()
    {
        final T answer = createObject();

        return answer;
    }

    /**
     * @return a new object of type T.
     */
    protected abstract T createObject();

    /**
     * @return the exemplar
     */
    protected T getExemplar()
    {
        return _exemplar;
    }
}
