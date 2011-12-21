package org.rivalry.example.boardgame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.cli.ParseException;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.RivalryData;
import org.rivalry.core.model.RivalryDataWriter;

/**
 * Provides a data collector for boardgame names.
 */
public class BoardgameNameDataCollectorMain
{
    /**
     * Application method.
     * 
     * @param args Application arguments.
     * 
     * @throws IOException if there is an I/O problem.
     * @throws ParseException if there is a parsing problem.
     */
    public static final void main(final String[] args) throws IOException,
            ParseException
    {
        System.out.println("start");
        final BoardgameNameDataCollectorMain dataCollector = new BoardgameNameDataCollectorMain();
        final List<Candidate> candidates = dataCollector.fetchCandidates();

        final BoardgameInjector injector = new BoardgameInjector();
        final RivalryData rivalryData = injector.injectRivalryData();
        rivalryData.getCandidates().addAll(candidates);

        final RivalryDataWriter rdWriter = new RivalryDataWriter();
        final Writer writer = new FileWriter("BoardgameCandidates.xml");
        rdWriter.write(rivalryData, writer);
    }

    /**
     * @return Candidates.
     */
    public List<Candidate> fetchCandidates()
    {
        final List<Candidate> answer = new ArrayList<Candidate>();

        final WebDriver webDriver = createWebDriver();
        webDriver.get("http://www.boardgamegeek.com/browse/boardgame");

        final List<WebElement> tables = webDriver.findElements(By
                .id("collectionitems"));

        if (CollectionUtils.isNotEmpty(tables))
        {
            final WebElement table = tables.get(0);

            final List<WebElement> parents = table.findElements(By
                    .className("collection_objectname"));

            if (CollectionUtils.isNotEmpty(parents))
            {
                for (final WebElement parent : parents)
                {
                    final List<WebElement> anchors = parent.findElements(By
                            .tagName("a"));

                    if (CollectionUtils.isNotEmpty(tables))
                    {
                        final WebElement anchor = anchors.get(0);

                        final String name = anchor.getText();
                        final String page = anchor.getAttribute("href");
                        answer.add(createCandidate(name, page));
                    }
                }
            }
        }

        System.out.println("Candidates found: " + answer.size());

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
