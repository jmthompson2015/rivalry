package org.rivalry.example.runescape;

import org.rivalry.core.model.DefaultCandidate;

/**
 * Provides a candidate for RuneScape.
 */
public class RuneScapeCandidate extends DefaultCandidate
{
    /** Item. */
    private Item _item;

    /**
     * @return the item
     */
    public Item getItem()
    {
        return _item;
    }

    /**
     * @return name
     */
    @Override
    public String getName()
    {
        return _item.getName();
    }

    /**
     * @param item the item to set
     */
    public void setItem(final Item item)
    {
        _item = item;
    }
}
