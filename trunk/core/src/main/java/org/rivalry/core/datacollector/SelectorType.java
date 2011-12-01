//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.core.datacollector;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Provides an enumeration of selector types.
 */
public enum SelectorType
{
    /** Class name. */
    CLASS_NAME
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.className(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.className(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.className(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.className(value));
        }
    },

    /** CSS selector. */
    CSS_SELECTOR
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.cssSelector(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.cssSelector(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.cssSelector(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.cssSelector(value));
        }
    },

    /** ID. */
    ID
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.id(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.id(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.id(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.id(value));
        }
    },

    /** Link text. */
    LINK_TEXT
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.linkText(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.linkText(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.linkText(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.linkText(value));
        }
    },

    /** Name. */
    NAME
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.name(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.name(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.name(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.name(value));
        }
    },

    /** Partial link text. */
    PARTIAL_LINK_TEXT
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.partialLinkText(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.partialLinkText(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.partialLinkText(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.partialLinkText(value));
        }
    },

    /** Tag name. */
    TAG_NAME
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.tagName(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.tagName(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.tagName(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.tagName(value));
        }
    },

    /** XPath. */
    XPATH
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElement(By.xpath(value));
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return parent.findElement(By.xpath(value));
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return webDriver.findElements(By.xpath(value));
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return parent.findElements(By.xpath(value));
        }
    },

    /** Literal. */
    LITERAL
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            // TODO Auto-generated method stub
            return null;
        }
    },

    /** Click action. */
    CLICK
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return null;
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return null;
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return null;
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return null;
        }
    },

    /** Submit action. */
    SUBMIT
    {
        @Override
        public WebElement findElement(final WebDriver webDriver,
                final String value)
        {
            return null;
        }

        @Override
        public WebElement findElement(final WebElement parent,
                final String value)
        {
            return null;
        }

        @Override
        public List<WebElement> findElements(final WebDriver webDriver,
                final String value)
        {
            return null;
        }

        @Override
        public List<WebElement> findElements(final WebElement parent,
                final String value)
        {
            return null;
        }
    };

    /**
     * @param webDriver Web driver.
     * @param value Value.
     * 
     * @return the child web element which corresponds to the given selector.
     */
    public abstract WebElement findElement(final WebDriver webDriver,
            String value);

    /**
     * @param parent Parent web element.
     * @param value Value.
     * 
     * @return the child web element which corresponds to the given selector.
     */
    public abstract WebElement findElement(WebElement parent, String value);

    /**
     * @param webDriver Web driver.
     * @param value Value.
     * 
     * @return the child web element which corresponds to the given selector.
     */
    public abstract List<WebElement> findElements(final WebDriver webDriver,
            String value);

    /**
     * @param parent Parent web element.
     * @param value Value.
     * 
     * @return the child web element which corresponds to the given selector.
     */
    public abstract List<WebElement> findElements(WebElement parent,
            String value);
}
