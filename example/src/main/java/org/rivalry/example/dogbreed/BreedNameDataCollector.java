package org.rivalry.example.dogbreed;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.DefaultCandidate;

/**
 * Provides a data collector for dog breed candidates.
 */
public class BreedNameDataCollector
{
    /**
     * @return Candidates.
     */
    public List<Candidate> fetchCandidates()
    {
        final List<Candidate> answer = new ArrayList<Candidate>();

        final WebDriver webDriver = createWebDriver();
        webDriver.get("http://www.dogtime.com/dog-breeds");

        final List<WebElement> parents = webDriver.findElements(By
                .className("breed-list"));

        if (CollectionUtils.isNotEmpty(parents))
        {
            final WebElement parent = parents.get(0);
            final List<WebElement> anchors = parent.findElements(By
                    .tagName("a"));

            for (final WebElement webElement : anchors)
            {
                final String name = webElement.getText();
                final String page = webElement.getAttribute("href");
                answer.add(createCandidate(name, page));
            }
        }

        return answer;
    }

    /**
     * @param name Name.
     * @param page Page.
     * 
     * @return a new candidate.
     */
    private Candidate createCandidate(final String name, final String page)
    {
        final Candidate answer = new DefaultCandidate();

        answer.setName(name);
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
            ((HtmlUnitDriver)answer).setJavascriptEnabled(false);
        }

        return answer;
    }
}
