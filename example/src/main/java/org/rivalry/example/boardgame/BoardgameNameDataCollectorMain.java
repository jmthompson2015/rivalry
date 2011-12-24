package org.rivalry.example.boardgame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.cli.ParseException;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.DefaultCriterion;
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
        final BoardgameInjector injector = new BoardgameInjector();
        final RivalryData rivalryData = injector.injectRivalryData();
        dataCollector.fetchCandidates(rivalryData);

        final RivalryDataWriter rdWriter = new RivalryDataWriter();
        final Writer writer = new FileWriter("BoardgameCandidates.xml");
        rdWriter.write(rivalryData, writer);
    }

    /**
     * @param rivalryData Rivalry data.
     */
    public void fetchCandidates(final RivalryData rivalryData)
    {
        final WebDriver webDriver = createWebDriver();
        webDriver.get("http://www.boardgamegeek.com/browse/boardgame");

        final WebElement table = webDriver
                .findElement(By.id("collectionitems"));

        if (table != null)
        {
            final List<WebElement> parents = table.findElements(By
                    .tagName("tr"));

            if (CollectionUtils.isNotEmpty(parents))
            {
                // The first one is the header row; skip it.
                parents.remove(0);

                for (final WebElement parent : parents)
                {
                    final WebElement td = parent.findElement(By
                            .className("collection_objectname"));
                    final WebElement anchor = td.findElement(By.tagName("a"));

                    final String name = anchor.getText();
                    final String page = anchor.getAttribute("href");
                    final Candidate candidate = createCandidate(name, page);
                    rivalryData.getCandidates().add(candidate);

                    final WebElement rankElement = parent.findElement(By
                            .className("collection_rank"));
                    final Integer rank = Integer
                            .parseInt(rankElement.getText());
                    Criterion criterion = getCriterion(rivalryData,
                            "Board Game Rank");
                    candidate.putValue(criterion, rank);

                    final List<WebElement> stats = parent.findElements(By
                            .className("collection_bggrating"));
                    if (CollectionUtils.isNotEmpty(stats))
                    {
                        final Double geekRating = Double.parseDouble(stats.get(
                                0).getText());
                        criterion = getCriterion(rivalryData, "Geek Rating");
                        candidate.putValue(criterion, geekRating);

                        final Double avgRating = Double.parseDouble(stats
                                .get(1).getText());
                        criterion = getCriterion(rivalryData, "Avg Rating");
                        candidate.putValue(criterion, avgRating);

                        final Integer numVoters = Integer.parseInt(stats.get(2)
                                .getText());
                        criterion = getCriterion(rivalryData, "Num Voters");
                        candidate.putValue(criterion, numVoters);
                    }
                }
            }
        }

        System.out.println("Candidates found: "
                + rivalryData.getCandidates().size());
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

    /**
     * @param rivalryData Rivalry data.
     * @param criterionName Criterion name.
     * 
     * @return the criterion of the given name, creating it if necessary.
     */
    private Criterion getCriterion(final RivalryData rivalryData,
            final String criterionName)
    {
        Criterion answer = rivalryData.findCriterionByName(criterionName);

        if (answer == null)
        {
            answer = new DefaultCriterion();
            answer.setName(criterionName);

            rivalryData.getCriteria().add(answer);
        }

        return answer;
    }
}
