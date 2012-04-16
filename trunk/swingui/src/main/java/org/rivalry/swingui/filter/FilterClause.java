package org.rivalry.swingui.filter;

import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;

/**
 * Provides a clause for a filter.
 */
public class FilterClause
{
    /** Criterion. */
    private final Criterion _criterion;

    /** Operator. */
    private final Operator _operator;

    /** Value. */
    private final Object _value;

    /**
     * @param criterion Criterion.
     * @param operator Operator.
     * @param value Value.
     */
    public FilterClause(final Criterion criterion, final Operator operator, final Object value)
    {
        _criterion = criterion;
        _operator = operator;
        _value = value;
    }

    /**
     * @return the criterion
     */
    public Criterion getCriterion()
    {
        return _criterion;
    }

    /**
     * @return the operator
     */
    public Operator getOperator()
    {
        return _operator;
    }

    /**
     * @return the value
     */
    public Object getValue()
    {
        return _value;
    }

    /**
     * @return the value
     */
    public String getValueAsString()
    {
        String answer = null;

        if (_value instanceof String)
        {
            answer = (String)_value;
        }
        else if (_value != null)
        {
            answer = _value.toString();
        }

        return answer;
    }

    /**
     * @param candidate Candidate.
     * 
     * @return true if the given object passes this filter.
     */
    public boolean passes(final Candidate candidate)
    {
        boolean answer = false;

        final Double rating = candidate.getRating(_criterion);

        if (rating != null)
        {
            final Double doubleValue = (Double)_value;

            switch (_operator)
            {
            case EQ:
                answer = rating.equals(doubleValue);
                break;
            case NE:
                answer = !rating.equals(doubleValue);
                break;
            case GT:
                answer = rating > doubleValue;
                break;
            case LT:
                answer = rating < doubleValue;
                break;
            case GE:
                answer = rating >= doubleValue;
                break;
            case LE:
                answer = rating <= doubleValue;
                break;
            default:
                System.out.println("Unhandled operator: " + _operator + " (double case)");
                break;
            }
        }
        else
        {
            final Object value = candidate.getValue(_criterion);

            if (value instanceof String)
            {
                final String valueString = (String)value;
                final String stringValue = (String)_value;

                switch (_operator)
                {
                case CONTAINS:
                    answer = valueString.contains(stringValue);
                    break;
                case DOES_NOT_CONTAIN:
                    answer = !valueString.contains(stringValue);
                    break;
                default:
                    System.out.println("Unhandled operator: " + _operator + " (string case)");
                    break;
                }
            }
        }

        return answer;
    }

    /**
     * @return a string representation of this object.
     */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();

        sb.append(_criterion.getName());
        sb.append(" ");
        sb.append(_operator.getDisplaySymbol());
        sb.append(" ");
        sb.append(_value);

        return sb.toString();
    }
}
