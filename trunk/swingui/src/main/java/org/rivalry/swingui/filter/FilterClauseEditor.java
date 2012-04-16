package org.rivalry.swingui.filter;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.Criterion;
import org.rivalry.swingui.util.GridLayout2;

/**
 * Provides an editor for a <code>FilterClause</code> object.
 */
public class FilterClauseEditor extends JPanel
{
    /**
     * Provides a decorator for a <code>Criterion</code> object.
     */
    private static class CriterionDecorator
    {
        /** Decorated component. */
        private final Criterion _component;

        /**
         * Construct this object with the given parameter.
         * 
         * @param component Decorated component.
         */
        public CriterionDecorator(final Criterion component)
        {
            _component = component;
        }

        /**
         * @return the component
         */
        public Criterion getComponent()
        {
            return _component;
        }

        @Override
        public String toString()
        {
            return _component.getName();
        }
    }

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Criteria widget. */
    private final JComboBox _criteriaUI;

    /** Operator widget. */
    private final JComboBox _operatorUI;

    /** Value widget. */
    private final JTextField _valueUI;

    /**
     * Construct this object with the given parameters.
     * 
     * @param criteria Criteria.
     * @param filterClause Filter clause.
     */
    public FilterClauseEditor(final List<Criterion> criteria, final FilterClause filterClause)
    {
        _criteriaUI = createCriteriaUI(criteria);
        _operatorUI = createOperatorUI();
        _valueUI = createValueUI();

        setLayout(new GridLayout2(0, 3));

        add(_criteriaUI);
        add(_operatorUI);
        add(_valueUI);

        if (filterClause != null)
        {
            _criteriaUI.setSelectedItem(findDecoratorFor(filterClause.getCriterion()));
            _operatorUI.setSelectedItem(filterClause.getOperator());
            _valueUI.setText(filterClause.getValueAsString());
        }
    }

    /**
     * @return the filterClause
     */
    public FilterClause getFilterClause()
    {
        final Criterion criterion = ((CriterionDecorator)_criteriaUI.getSelectedItem()).getComponent();
        final Operator operator = (Operator)_operatorUI.getSelectedItem();
        final Object value = asDoubleOrString(_valueUI.getText());

        return new FilterClause(criterion, operator, value);
    }

    /**
     * @param valueString Value string.
     * 
     * @return the given parameter as a Double object if possible, else as a String.
     */
    private Object asDoubleOrString(final String valueString)
    {
        Object answer = null;

        if (StringUtils.isNotEmpty(valueString))
        {
            try
            {
                final Double valueDouble = Double.parseDouble(valueString);
                answer = valueDouble;
            }
            catch (final NumberFormatException ignore)
            {
                // It's not a number, so just use the string.
                answer = valueString;
            }
        }

        return answer;
    }

    /**
     * @param criteria Criteria.
     * 
     * @return a new criteria widget.
     */
    private JComboBox createCriteriaUI(final List<Criterion> criteria)
    {
        final JComboBox answer = new JComboBox();

        for (final Criterion criterion : criteria)
        {
            answer.addItem(new CriterionDecorator(criterion));
        }

        return answer;
    }

    /**
     * @return a new operator widget.
     */
    private JComboBox createOperatorUI()
    {
        final JComboBox answer = new JComboBox();

        for (final Operator operator : Operator.values())
        {
            answer.addItem(operator);
        }

        return answer;
    }

    /**
     * @return a new value widget.
     */
    private JTextField createValueUI()
    {
        final JTextField answer = new JTextField(5);

        return answer;
    }

    /**
     * @param criterion Criterion.
     * 
     * @return the decorator for the given parameter.
     */
    private CriterionDecorator findDecoratorFor(final Criterion criterion)
    {
        CriterionDecorator answer = null;

        final int size = _criteriaUI.getItemCount();

        for (int i = 0; (answer == null) && (i < size); i++)
        {
            final CriterionDecorator decorator = (CriterionDecorator)_criteriaUI.getItemAt(i);
            if (criterion.equals(decorator.getComponent()))
            {
                answer = decorator;
            }
        }

        return answer;
    }
}
