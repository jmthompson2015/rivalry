//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.dogbreed;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultRivalryData;
import org.rivalry.core.model.RivalryData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Provides tests for the <code>DefaultDataCollector</code> class.
 */
public class DefaultDataCollectorTest
{
    /** Data collector. */
    private DataCollector _dataCollector;

    /** Data collector specification. */
    private DCSpec _dcSpec;

    /** Flag indicating whether to provide verbose output. */
    private final boolean _isVerbose = false;

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void directTest()
    {
        System.out.println("start directTest()");

        final WebDriver webDriver = createWebDriver();
        webDriver.get("http://dogtime.com/dog-breeds/boston-terrier");

        final List<WebElement> groups = webDriver.findElements(By.className("breed-characteristic-group"));
        final WebElement seeAllCharacteristics = groups.get(groups.size() - 1);
        System.out.println("seeAllCharacteristics = " + seeAllCharacteristics);
        System.out.println("seeAllCharacteristics.getText() = [" + seeAllCharacteristics.getText() + "]");
        groups.get(groups.size() - 1).click();

        final WebElement parent = webDriver.findElement(By.className("characteristics"));
        assertNotNull(parent);
        assertTrue(parent.isDisplayed());
        assertTrue(parent.isEnabled());

        final List<WebElement> ratings = parent.findElements(By.className("five-star-ratings"));
        assertNotNull(ratings);
        assertThat(ratings.size(), is(25));

        {
            final WebElement element = ratings.get(0);
            System.out.println("element = " + element);
            assertNotNull(element);
            assertThat(element.getAttribute("class"), is("five-star-ratings five-star-ratings-5"));
        }

        {
            final WebElement element = ratings.get(1);
            assertNotNull(element);
            assertThat(element.getAttribute("class"), is("five-star-ratings five-star-ratings-4"));
        }

        final List<WebElement> h3s = parent.findElements(By.tagName("h3"));
        assertNotNull(h3s);
        assertThat(h3s.size(), is(25));

        {
            final WebElement element = h3s.get(0);
            System.out.println("element.getText() = [" + element.getText() + "]");
            assertNotNull(element);
            assertThat(element.getText(), is("Adapt well to apartment living"));
        }

        {
            final WebElement element = h3s.get(1);
            System.out.println("element.getText() = [" + element.getText() + "]");
            assertNotNull(element);
            assertThat(element.getText(), is("Affectionate with family"));
        }

        System.out.println("end directTest()");
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Test
    public void fetchDataDogBreeds()
    {
        final String username = null;
        final String password = null;
        final RivalryData rivalryData = new DefaultRivalryData();
        final Candidate candidate = createCandidate("boston-terrier", _dcSpec.getUrl());
        rivalryData.getCandidates().add(candidate);

        _dataCollector.fetchData(_dcSpec, username, password, rivalryData);

        assertNotNull(rivalryData.getCandidates());
        assertNotNull(rivalryData.getCategories());
        assertNotNull(rivalryData.getCriteria());
        assertThat(rivalryData.getCandidates().size(), is(1));
        assertThat(rivalryData.getCategories().size(), is(0));
        assertThat(rivalryData.getCriteria().size(), is(26));

        if (_isVerbose)
        {
            for (final Criterion criterion : rivalryData.getCriteria())
            {
                System.out.println("criterion = [" + criterion.getName() + "]");
            }
        }

        {
            final Criterion criterion = rivalryData.findCriterionByName("Adapt well to apartment living");
            assertNotNull(criterion);
            final Double rating = candidate.getRating(criterion);
            assertNotNull(rating);
            assertThat(rating, is(5.0));
        }

        {
            final Criterion criterion = rivalryData.findCriterionByName("Wanderlust potential");
            assertNotNull(criterion);
            final Double rating = candidate.getRating(criterion);
            assertNotNull(rating);
            assertThat(rating, is(5.0));
        }
    }

    /**
     * Set up the tests.
     */
    @Before
    public void setUp()
    {
        final DataCollectorInjector injector = new DogBreedInjector();
        _dataCollector = injector.injectDataCollector();

        _dcSpec = readDcSpec();
    }

    /**
     * Test the <code>fetchData()</code> method.
     *
     * @throws IOException if there is a problem.
     * @throws ParserConfigurationException if there is a problem.
     * @throws SAXException if there is a problem.
     * @throws XPathExpressionException if there is a problem.
     */
    @Ignore
    @Test
    public void testXPath() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException
    {
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("SampleDogTime.xml");
        assertNotNull(inputStream);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setValidating(false);

        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(inputStream);
        assertNotNull(document);

        final XPath xpath = XPathFactory.newInstance().newXPath();
        final String expression = "//*[contains(text(), 'See All Characteristic Ratings')]";
        final Node widgetNode = (Node)xpath.evaluate(expression, document, XPathConstants.NODE);
        assertNotNull(widgetNode);
        System.out.println("widgetNode = " + widgetNode.getTextContent());
    }

    /**
     *
     * @param candidateName Candidate name.
     * @param url URL.
     *
     * @return a new candidate.
     */
    private Candidate createCandidate(final String candidateName, final String url)
    {
        String page = url;

        if (page.contains("$1"))
        {
            page = page.replaceAll("\\$1", candidateName);
        }

        final Candidate answer = new DefaultCandidate();

        answer.setName(candidateName);
        answer.setPage(page);

        return answer;
    }

    /**
     * @return a new web driver.
     */
    private WebDriver createWebDriver()
    {
        final WebDriver answer = new HtmlUnitDriver();

        final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
        logger.setLevel(Level.OFF);

        if (answer instanceof HtmlUnitDriver)
        {
            ((HtmlUnitDriver)answer).setJavascriptEnabled(true);
        }

        return answer;
    }

    /**
     * @return a new data collector specification read from a file of the given name.
     */
    private DCSpec readDcSpec()
    {
        final InputStream inputStream = getClass().getResourceAsStream("DataCollectorDogTime.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec dcSpec = dcReader.read(reader);
        assertNotNull(dcSpec);

        return dcSpec;
    }
}
