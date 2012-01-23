package org.rivalry.example.bestplace;

import org.apache.commons.lang.StringUtils;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.DefaultCandidate;

/**
 * Provides a candidate creator for best places.
 */
public class CandidateCreator
{
    /** Base page URL. */
    private static final String BASE_URL = "http://www.bestplaces.net/city/";

    /**
     * @param rawName Raw candidate name.
     * 
     * @return a new candidate.
     */
    public Candidate create(final String rawName)
    {
        final Candidate answer = new DefaultCandidate();

        final String name = createName(rawName);
        final String page = createPage(rawName);

        answer.setName(name);
        answer.setPage(page);

        return answer;
    }

    /**
     * @param rawString Raw string.
     * 
     * @return substring before the first hyphen.
     */
    private String beforeHyphen(final String rawString)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawString))
        {
            answer = rawString.trim();

            final int index = answer.indexOf('-');

            if (index >= 0)
            {
                answer = answer.substring(0, index);
            }
        }

        return answer;
    }

    /**
     * @param rawString Raw string.
     * 
     * @return substring before the first hyphen.
     */
    private String beforeSlash(final String rawString)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawString))
        {
            answer = rawString.trim();

            final int index = answer.indexOf('/');

            if (index >= 0)
            {
                answer = answer.substring(0, index).trim();
            }
        }

        return answer;
    }

    /**
     * @param rawName Raw candidate name.
     * 
     * @return candidate name.
     */
    private String createName(final String rawName)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawName))
        {
            answer = rawName;

            // Fix a hyphenated city name.
            answer = removeBetween(answer, '-', ',');

            // Fix a slashed city name.
            answer = removeBetween(answer, '/', ',');

            // Fix a notation.
            answer = beforeSlash(answer);

            // Remove comma between city and state.
            answer = answer.replaceAll(",", "");

            // Fix a hyphenated state name.
            answer = beforeHyphen(answer);

            if (!answer.contains(" "))
            {
                System.out.println("Data problem: [" + rawName + "] no space ["
                        + answer + "]");
            }
        }

        return answer;
    }

    /**
     * @param rawName Raw candidate name.
     * 
     * @return page.
     */
    private String createPage(final String rawName)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawName))
        {
            final String[] data = rawName.split(",");

            if (data.length < 1)
            {
                System.out.println("Data problem: [" + rawName + "]");
            }
            else
            {
                final String city = createPageCity(data[0]);
                final String state = createPageState(data[1]);

                if (state.length() > 2)
                {
                    System.out.println("Data problem: [" + rawName
                            + "] with state: [" + data[1] + "]");
                }
                else
                {
                    answer = BASE_URL + state + "/" + city;
                }
            }
        }

        return answer;
    }

    /**
     * @param rawCity Raw city name.
     * 
     * @return city.
     */
    private String createPageCity(final String rawCity)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawCity))
        {
            answer = rawCity.trim().toLowerCase();

            // Fix a hyphenated city name.
            answer = beforeHyphen(answer);

            // Fix a slashed city name.
            answer = beforeSlash(answer);

            // Replace spaces.
            answer = answer.replaceAll(" ", "_");
        }

        return answer;
    }

    /**
     * @param rawState Raw state string.
     * 
     * @return state.
     */
    private String createPageState(final String rawState)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawState))
        {
            answer = rawState.trim().toLowerCase();

            // Fix a hyphenated state name.
            answer = beforeHyphen(answer);

            // Fix a notation.
            answer = beforeSlash(answer);
        }

        return answer;
    }

    /**
     * 
     * @param rawString Raw string.
     * @param key0 Start key.
     * @param key1 End key.
     * 
     * @return string with the substring between the keys removed.
     */
    private String removeBetween(final String rawString, final char key0,
            final char key1)
    {
        String answer = null;

        if (StringUtils.isNotEmpty(rawString))
        {
            answer = rawString.trim();

            final int index0 = answer.indexOf(key0);
            final int index1 = answer.lastIndexOf(key1);

            if (index0 >= 0 && index0 < index1)
            {
                answer = answer.substring(0, index0) + answer.substring(index1);
            }
        }

        return answer;
    }
}
