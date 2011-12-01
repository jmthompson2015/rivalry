//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;

/**
 * Provides a default implementation of a data collector.
 */
public class DefaultDataCollector implements DataCollector
{
    /**
     * Provides an expected condition implementation which looks for a visible
     * element.
     */
    class VisibilityOfElementLocated implements ExpectedCondition<Boolean>
    {
        /** Find condition. */
        private By _findCondition;

        /** Start time. */
        private Long _start;

        /** Text. */
        private String _text;

        /**
         * @param by Find condition.
         * @param text Text.
         */
        VisibilityOfElementLocated(By by, String text)
        {
            Validate.notNull(by);
            Validate.notEmpty(text);

            _findCondition = by;
            _text = text;
            System.out.println("text = [" + text + "]");
        }

        @Override
        public Boolean apply(WebDriver driver)
        {
            if (_start == null)
                _start = System.currentTimeMillis();

            Boolean answer = Boolean.FALSE;

            WebElement element = driver.findElement(_findCondition);

            if (element != null)
            {
                // String myText = element.getText();
                // long elapsed = System.currentTimeMillis() - _start;
                // System.out.println(elapsed + "\tmyText = [" + myText + "]");

                if (_text.equals(element.getText()))
                {
                    answer = Boolean.TRUE;
                    // System.out.println(driver.getPageSource());
                }
            }

            return answer;
        }
    }

    /** Category provider. */
    private final Provider<Category> _categoryProvider;

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider;

    /** Value string parser. */
    private final ValueStringParser _valueStringParser;

    /**
     * Construct this object with the given parameter.
     * 
     * @param valueStringParser Value string parser.
     * @param categoryProvider Category provider.
     * @param criterionProvider Criterion provider.
     */
    public DefaultDataCollector(final ValueStringParser valueStringParser,
            final Provider<Category> categoryProvider,
            final Provider<Criterion> criterionProvider)
    {
        _valueStringParser = valueStringParser;
        _categoryProvider = categoryProvider;
        _criterionProvider = criterionProvider;
    }

    @Override
    public void fetchData(DCSpec dcSpec, String username, String password,
            RivalryData rivalryData)
    {
        final long start = System.currentTimeMillis();

        WebDriver webDriver = createWebDriver();

        try
        {
            for (final Candidate candidate : rivalryData.getCandidatesList())
            {
                fetchData(webDriver, dcSpec, rivalryData, candidate);
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }

        final long end = System.currentTimeMillis();
        logTiming("0 fetchData()", start, end);
    }

    @Override
    public void fetchData(final DCSpec dcSpec, final RivalryData rivalryData,
            final Candidate candidate)
    {
        final long start = System.currentTimeMillis();

        final WebDriver webDriver = createWebDriver();

        fetchData(webDriver, dcSpec, rivalryData, candidate);

        webDriver.quit();

        final long end = System.currentTimeMillis();
        logTiming("1 fetchData()", start, end);
    }

    /**
     * @param webDriver Web driver.
     * @param dcSpec Data collector specification.
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     */
    @Override
    public void fetchData(final WebDriver webDriver, final DCSpec dcSpec,
            final RivalryData rivalryData, final Candidate candidate)
    {
        final long start = System.currentTimeMillis();

        // disableWebDriverLogging();

        // And now use this to visit a web page.
        final String url = candidate.getPage();
        if (StringUtils.isNotEmpty(url))
        {
            System.out.println("Accessing URL " + url);
            webDriver.get(url);

            // Check the title of the page.
            // System.out.println("Page title is: " + webDriver.getTitle());
            // WebElement element = webDriver.findElement(By.tagName("title"));
            // System.out.println("title element = [" + element.getText() +
            // "]");
        }

        for (final DCSelector selector : dcSpec.getSelectors())
        {
            final SelectorType type = selector.getType();
            switch (type)
            {
            case CLICK:
                DCSelector childSelector = selector.getSelectors().get(0);
                SelectorType childType = childSelector.getType();
                String childValue = childSelector.getValue();
                System.out.println("childType = " + childType
                        + " childValue = " + childValue);
                childType.findElement(webDriver, childValue).click();
                System.out.println("2 Page title is: " + webDriver.getTitle());
                break;

            case SUBMIT:
                type.findElement(webDriver, selector.getValue()).submit();
                break;

            default:
                final List<WebElement> parents = type.findElements(webDriver,
                        selector.getValue());
                for (final WebElement parent : parents)
                {
                    process(rivalryData, candidate, parent,
                            selector.getSelectors());
                }
            }
        }

        final long end = System.currentTimeMillis();
        logTiming("1 fetchData()", start, end);
    }

    /**
     * @param name Name.
     * 
     * @return a new category.
     */
    private Category createCategory(final String name)
    {
        final Category answer = getCategoryProvider().newInstance();

        answer.setName(name);

        return answer;
    }

    /**
     * @param name Name.
     * @param category Category.
     * 
     * @return a new criterion.
     */
    private Criterion createCriterion(final String name, final Category category)
    {
        final Criterion answer = getCriterionProvider().newInstance();

        answer.setName(name);
        answer.setCategory(category);

        return answer;
    }

    /**
     * Disable Selenium WebDriver logger.
     */
    // private void disableWebDriverLogging()
    // {
    // final Logger logger = Logger.getLogger("");
    // logger.setLevel(Level.OFF);
    // }

    /**
     * @param parent Parent web element.
     * @param selector0 Data collection selector.
     * @param size Size.
     * 
     * @return a list of names.
     */
    private List<String> createNames(WebElement parent, DCSelector selector0,
            int size)
    {
        List<String> answer = new ArrayList<String>();

        if (selector0.getType() == SelectorType.LITERAL)
        {
            String value = selector0.getValue();

            for (int i = 0; i < size; i++)
            {
                answer.add(value);
            }
        }
        else
        {
            final List<WebElement> elements0 = selector0.getType()
                    .findElements(parent, selector0.getValue());

            for (int i = 0; i < size; i++)
            {
                final String name = elements0.get(i).getText();
                answer.add(name);
            }
        }

        return answer;
    }

    /**
     * @return a new web driver.
     */
    private WebDriver createWebDriver()
    {
        final WebDriver answer = new HtmlUnitDriver();

        final Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        if (answer instanceof HtmlUnitDriver)
        {
            ((HtmlUnitDriver)answer).setJavascriptEnabled(true);
        }

        return answer;
    }

    /**
     * @return the categoryProvider
     */
    private Provider<Category> getCategoryProvider()
    {
        return _categoryProvider;
    }

    /**
     * @return the criterionProvider
     */
    private Provider<Criterion> getCriterionProvider()
    {
        return _criterionProvider;
    }

    /**
     * @return the valueStringParser
     */
    private ValueStringParser getValueStringParser()
    {
        return _valueStringParser;
    }

    /**
     * Log the given timing parameters.
     * 
     * @param title Title.
     * @param start Start time.
     * @param end End time.
     */
    private void logTiming(final String title, final long start, final long end)
    {
        System.out.println(title + " \telapsed: " + (end - start) + " ms");
    }

    /**
     * Process the given parameters to create new entries for rivalry data.
     * 
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     * @param parent Parent web element.
     * @param selector Selector.
     */
    private void process(final RivalryData rivalryData,
            final Candidate candidate, final WebElement parent,
            final DCSelector selector)
    {
        System.out.println("selector = " + selector.getType() + " "
                + selector.getValue());

        final WebElement child = selector.getType().findElement(parent,
                selector.getValue());

        if (selector.getSelectors().isEmpty())
        {
            System.out.println("parent.getText() = [" + parent.getText() + "]");
            System.out.println("child.getText()  = [" + child.getText() + "]");
        }
        else
        {
            process(rivalryData, candidate, child, selector.getSelectors());
        }
    }

    /**
     * Process the given parameters to create new entries for rivalry data.
     * 
     * @param rivalryData Rivalry data.
     * @param candidate Candidate.
     * @param parent Parent web element.
     * @param selectors Selectors.
     */
    private void process(final RivalryData rivalryData,
            final Candidate candidate, final WebElement parent,
            final List<DCSelector> selectors)
    {
        if (selectors.size() == 2 && selectors.get(0).getSelectors().isEmpty()
                && selectors.get(1).getSelectors().isEmpty())
        {
            // Parent selector has two leaf children.
            final DCSelector selector0 = selectors.get(0);
            final DCSelector selector1 = selectors.get(1);
            // final List<WebElement> elements0;
            // elements0= selector0.getType()
            // .findElements(parent, selector0.getValue());
            final List<WebElement> elements1 = selector1.getType()
                    .findElements(parent, selector1.getValue());

            final int size = elements1.size();
            List<String> names = createNames(parent, selector0, size);

            if (size > 0
                    && !rivalryData.getCandidatesList().contains(candidate))
            {
                rivalryData.getCandidatesList().add(candidate);
            }

            for (int i = 0; i < size; i++)
            {
                // final String name = elements0.get(i).getText();
                String name = names.get(i);
                final WebElement valueElement = elements1.get(i);

                final String categoryName = name;
                Category category = rivalryData
                        .findCategoryByName(categoryName);

                if (category == null)
                {
                    category = createCategory(categoryName);
                    rivalryData.getCategoriesList().add(category);
                }

                final String criterionName = name;
                Criterion criterion = rivalryData
                        .findCriterionByName(criterionName);
                if (criterion == null)
                {
                    criterion = createCriterion(criterionName, category);
                    rivalryData.getCriteriaList().add(criterion);
                }

                final Double value = getValueStringParser().parse(valueElement);
                candidate.putRating(criterion, value);
            }
        }
        else
        {
            for (final DCSelector selector : selectors)
            {
                process(rivalryData, candidate, parent, selector);
            }
        }
    }
}
