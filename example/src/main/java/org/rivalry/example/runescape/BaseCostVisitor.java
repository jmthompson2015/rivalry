package org.rivalry.example.runescape;

/**
 * Provides a visitor which computes base cost.
 */
public class BaseCostVisitor implements Visitor
{
    /** Cost. */
    private Integer _cost = 0;

    /** Grand exchange. */
    private final GrandExchange _grandExchange;

    /**
     * Construct this object with the given parameters.
     * 
     * @param grandExchange Grand exchange.
     */
    public BaseCostVisitor(final GrandExchange grandExchange)
    {
        _grandExchange = grandExchange;
    }

    /**
     * @return the cost
     */
    public Integer getCost()
    {
        return _cost;
    }

    @Override
    public void visit(final Item item)
    {
        if (item.isLeaf())
        {
            _cost += computeValue(item);
        }
    }

    /**
     * @param item Item.
     * 
     * @return non-null value.
     */
    private Integer computeValue(final Item item)
    {
        final Integer value = _grandExchange.getValue(item);

        return (value == null ? 0 : value);
    }
}
