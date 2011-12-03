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

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DefaultDataCollector;
import org.rivalry.core.datacollector.DefaultNameStringParser;
import org.rivalry.core.datacollector.NameStringParser;
import org.rivalry.core.datacollector.ValueStringParser;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultCategoryProvider;
import org.rivalry.core.model.DefaultCriterionProvider;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.util.Provider;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Provides tests for the <code>DefaultDataCollector</code> class.
 */
public class DefaultDataCollectorTest
{
    /** Dog breeds value string processor. */
    private final NameStringParser _nameStringParser = new DefaultNameStringParser();

    /** Dog breeds value string processor. */
    private final ValueStringParser _valueStringParser = new DogBreedsParser();

    /** Category provider. */
    private final Provider<Category> _categoryProvider = new DefaultCategoryProvider();

    /** Criterion provider. */
    private final Provider<Criterion> _criterionProvider = new DefaultCriterionProvider();

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

        final List<WebElement> groups = webDriver.findElements(By
                .className("breed-characteristic-group"));
        final WebElement seeAllCharacteristics = groups.get(groups.size() - 1);
        System.out.println("seeAllCharacteristics = " + seeAllCharacteristics);
        System.out.println("seeAllCharacteristics.getText() = ["
                + seeAllCharacteristics.getText() + "]");
        groups.get(groups.size() - 1).click();

        final WebElement parent = webDriver.findElement(By
                .className("characteristics"));
        assertNotNull(parent);
        assertTrue(parent.isDisplayed());
        assertTrue(parent.isEnabled());

        final List<WebElement> ratings = parent.findElements(By
                .className("five-star-ratings"));
        assertNotNull(ratings);
        assertThat(ratings.size(), is(25));

        {
            final WebElement element = ratings.get(0);
            System.out.println("element = " + element);
            assertNotNull(element);
            assertThat(element.getAttribute("class"),
                    is("five-star-ratings five-star-ratings-5"));
        }

        {
            final WebElement element = ratings.get(1);
            assertNotNull(element);
            assertThat(element.getAttribute("class"),
                    is("five-star-ratings five-star-ratings-4"));
        }

        final List<WebElement> h3s = parent.findElements(By.tagName("h3"));
        assertNotNull(h3s);
        assertThat(h3s.size(), is(25));

        {
            final WebElement element = h3s.get(0);
            System.out.println("element.getText() = [" + element.getText()
                    + "]");
            assertNotNull(element);
            assertThat(element.getText(), is("Adapt well to apartment living"));
        }

        {
            final WebElement element = h3s.get(1);
            System.out.println("element.getText() = [" + element.getText()
                    + "]");
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
        final DataCollector dataCollector = new DefaultDataCollector(
                _nameStringParser, _valueStringParser, _categoryProvider,
                _criterionProvider);

        final DCSpec dcSpec = readDcSpec("");
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("boston-terrier",
                dcSpec.getUrl());

        dataCollector.fetchData(dcSpec, rivalryData, candidate);

        assertNotNull(rivalryData.getCandidatesList());
        assertNotNull(rivalryData.getCategoriesList());
        assertNotNull(rivalryData.getCriteriaList());
        assertThat(rivalryData.getCandidatesList().size(), is(1));
        assertThat(rivalryData.getCategoriesList().size(), is(25));
        assertThat(rivalryData.getCriteriaList().size(), is(25));

        if (_isVerbose)
        {
            for (final Criterion criterion : rivalryData.getCriteriaList())
            {
                System.out.println("criterion = [" + criterion.getName() + "]");
            }
        }

        {
            final Criterion criterion = rivalryData
                    .findCriterionByName("Adapt well to apartment living");
            assertNotNull(criterion);
            final Double rating = candidate.getRating(criterion);
            assertNotNull(rating);
            assertThat(rating, is(5.0));
        }

        {
            final Criterion criterion = rivalryData
                    .findCriterionByName("Wanderlust potential");
            assertNotNull(criterion);
            final Double rating = candidate.getRating(criterion);
            assertNotNull(rating);
            assertThat(rating, is(1.0));
        }
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
    public void testXPath() throws IOException, ParserConfigurationException,
            SAXException, XPathExpressionException
    {
        final InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("SampleDogTime.xml");
        assertNotNull(inputStream);

        final DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();
        factory.setNamespaceAware(false);
        factory.setValidating(false);

        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(inputStream);
        assertNotNull(document);

        final XPath xpath = XPathFactory.newInstance().newXPath();
        final String expression = "//*[contains(text(), 'See All Characteristic Ratings')]";
        final Node widgetNode = (Node)xpath.evaluate(expression, document,
                XPathConstants.NODE);
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
    private Candidate createCandidate(final String candidateName,
            final String url)
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

        final java.util.logging.Logger logger = java.util.logging.Logger
                .getLogger("");
        logger.setLevel(Level.OFF);

        if (answer instanceof HtmlUnitDriver)
        {
            ((HtmlUnitDriver)answer).setJavascriptEnabled(true);
        }

        return answer;
    }

    /**
     * @param filename Filename.
     * 
     * @return a new data collector specification read from a file of the given
     *         name.
     */
    private DCSpec readDcSpec(final String filename)
    {
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorDogTime.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec dcSpec = dcReader.read(reader);
        assertNotNull(dcSpec);

        return dcSpec;
    }

}
