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

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a default implementation of a data collector.
 */
public class DefaultDataCollector implements DataCollector
{
    /** Logger. */
    static final Logger LOGGER = LoggerFactory
            .getLogger(DefaultDataCollector.class);

    /** Category provider. */
    private final Provider<Category> _categoryProvider;

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider;

    /** Data post processor. */
    private final DataPostProcessor _dataPostProcessor;

    /** Flag indicating whether to enable Javascript. */
    private boolean _isJavascriptEnabled;

    /** Name string parser. */
    private final NameStringParser _nameStringParser;

    /** Value string parser. */
    private final ValueStringParser _valueStringParser;

    /**
     * Construct this object with the given parameter.
     * 
     * @param nameStringParser Name string parser.
     * @param valueStringParser Value string parser.
     * @param categoryProvider Category provider.
     * @param criterionProvider Criterion provider.
     * @param dataPostProcessor Data post processor.
     */
    public DefaultDataCollector(final NameStringParser nameStringParser,
            final ValueStringParser valueStringParser,
            final Provider<Category> categoryProvider,
            final Provider<Criterion> criterionProvider,
            final DataPostProcessor dataPostProcessor)
    {
        _nameStringParser = nameStringParser;
        _valueStringParser = valueStringParser;
        _categoryProvider = categoryProvider;
        _criterionProvider = criterionProvider;
        _dataPostProcessor = dataPostProcessor;
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

    @Override
    public void fetchData(final DCSpec dcSpec, final String username,
            final String password, final RivalryData rivalryData)
    {
        final long start = System.currentTimeMillis();

        final WebDriver webDriver = createWebDriver();

        try
        {
            for (final Candidate candidate : rivalryData.getCandidates())
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

        // And now use this to visit a web page.
        final String url = candidate.getPage();
        if (StringUtils.isNotEmpty(url))
        {
            System.out.println("Accessing URL " + url);
            webDriver.get(url);
        }

        for (final DCSelector selector : dcSpec.getSelectors())
        {
            final SelectorType type = selector.getType();
            switch (type)
            {
            case CLICK:
                final DCSelector childSelector = selector.getSelectors().get(0);
                final SelectorType childType = childSelector.getType();
                final String childValue = childSelector.getValue();
                LOGGER.debug("childType = " + childType + " childValue = "
                        + childValue);
                childType.findElement(webDriver, childValue).click();
                LOGGER.debug("2 Page title is: " + webDriver.getTitle());
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

        _dataPostProcessor.postProcess(rivalryData);

        final long end = System.currentTimeMillis();
        logTiming("2 fetchData()", start, end);
    }

    /**
     * @return the isJavascriptEnabled
     */
    public boolean isJavascriptEnabled()
    {
        return _isJavascriptEnabled;
    }

    /**
     * @param isJavascriptEnabled the isJavascriptEnabled to set
     */
    public void setJavascriptEnabled(final boolean isJavascriptEnabled)
    {
        _isJavascriptEnabled = isJavascriptEnabled;
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
     * @param parent Parent web element.
     * @param selector0 Data collection selector.
     * @param size Size.
     * 
     * @return a list of names.
     */
    private List<String> createNames(final WebElement parent,
            final DCSelector selector0, final int size)
    {
        final List<String> answer = new ArrayList<String>();

        if (selector0.getType() == SelectorType.LITERAL)
        {
            for (int i = 0; i < size; i++)
            {
                answer.add(selector0.getValue());
            }
        }
        else
        {
            LOGGER.debug("parent = " + parent);
            final List<WebElement> elements0 = selector0.getType()
                    .findElements(parent, selector0.getValue());
            final int size0 = elements0.size();
            LOGGER.debug("size0 = " + size0);

            for (int i = 0; i < size0; i++)
            {
                final String name = _nameStringParser.parse(elements0.get(i));
                LOGGER.debug(i + " name = [" + name + "]");
                if (StringUtils.isNotEmpty(name))
                {
                    answer.add(name);
                }
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

        final java.util.logging.Logger logger = java.util.logging.Logger
                .getLogger("");
        logger.setLevel(Level.OFF);

        if (answer instanceof HtmlUnitDriver)
        {
            ((HtmlUnitDriver)answer).setJavascriptEnabled(_isJavascriptEnabled);
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
        LOGGER.info(title + " \telapsed: " + (end - start) + " ms");
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
        LOGGER.debug("selector = " + selector.getType() + " "
                + selector.getValue());

        final WebElement child = selector.getType().findElement(parent,
                selector.getValue());

        if (selector.getSelectors().isEmpty())
        {
            LOGGER.debug("parent.getText() = [" + parent.getText() + "]");
            LOGGER.debug("child.getText()  = [" + child.getText() + "]");
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
        if ((selectors.size() == 2 || selectors.size() == 3)
                && selectors.get(0).getSelectors().isEmpty()
                && selectors.get(1).getSelectors().isEmpty())
        {
            // Parent selector has two or three leaf children.
            final DCSelector selector0 = selectors.get(0);
            final DCSelector selector1 = selectors.get(1);
            final List<WebElement> elements1 = selector1.getType()
                    .findElements(parent, selector1.getValue());

            final int size = elements1.size();
            final List<String> names = createNames(parent, selector0, size);
            LOGGER.debug("names = " + names);

            if (size > 0 && !rivalryData.getCandidates().contains(candidate))
            {
                rivalryData.getCandidates().add(candidate);
            }

            Category category = null;

            if (selectors.size() == 3)
            {
                // Create a category.
                final DCSelector selector2 = selectors.get(2);
                String categoryName;
                if (selector2.getType() == SelectorType.LITERAL)
                {
                    categoryName = selector2.getValue();
                }
                else
                {
                    final List<WebElement> elements2 = selector2.getType()
                            .findElements(parent, selector2.getValue());
                    final WebElement valueElement = elements2.get(0);
                    categoryName = valueElement.getText();
                }
                LOGGER.debug("categoryName = [" + categoryName + "]");
                category = rivalryData.findCategoryByName(categoryName);

                if (category == null)
                {
                    category = createCategory(categoryName);
                    rivalryData.getCategories().add(category);
                }
            }

            for (int i = 0; i < size; i++)
            {
                final String name = names.get(i);

                if (StringUtils.isNotEmpty(name))
                {
                    final WebElement valueElement = elements1.get(i);

                    final String criterionName = name;
                    Criterion criterion = rivalryData
                            .findCriterionByName(criterionName);
                    if (criterion == null)
                    {
                        criterion = createCriterion(criterionName, category);
                        rivalryData.getCriteria().add(criterion);
                    }

                    final Double value = getValueStringParser().parse(
                            valueElement);
                    LOGGER.debug("name = [" + name + "] value = [" + value
                            + "]");
                    candidate.putRating(criterion, value);
                }
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
