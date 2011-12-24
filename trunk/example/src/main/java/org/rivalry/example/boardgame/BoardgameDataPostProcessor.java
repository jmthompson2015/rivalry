package org.rivalry.example.boardgame;

import java.util.List;

import org.rivalry.core.datacollector.DefaultDataPostProcessor;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.RivalryData;

/**
 * Provides a data post processor for boardgames.
 */
public class BoardgameDataPostProcessor extends DefaultDataPostProcessor
{
    @Override
    public void postProcess(final RivalryData rivalryData)
    {
        // Convert playing time into a number.
        final String criterionName = "Playing Time";
        final Criterion criterion = rivalryData
                .findCriterionByName(criterionName);
        final List<Candidate> candidates = rivalryData.getCandidates();

        for (final Candidate candidate : candidates)
        {
            final Object value = candidate.getValue(criterion);

            if (value instanceof String)
            {
                String playingTime = (String)value;
                final int index = playingTime.indexOf(" minutes");

                if (index >= 0)
                {
                    playingTime = playingTime.substring(0, index);
                    try
                    {
                        final Integer time = Integer.parseInt(playingTime);
                        candidate.putValue(criterion, time);
                    }
                    catch (final NumberFormatException ignore)
                    {
                        // Nothing to do.
                    }
                }
            }
        }

        super.postProcess(rivalryData);
    }
}
