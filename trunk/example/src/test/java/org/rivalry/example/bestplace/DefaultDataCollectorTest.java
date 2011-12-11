//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.bestplace;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.rivalry.core.datacollector.DCSpec;
import org.rivalry.core.datacollector.DataCollector;
import org.rivalry.core.datacollector.DataCollectorInjector;
import org.rivalry.core.datacollector.DefaultDataCollectorInjector;
import org.rivalry.core.datacollector.io.DCSpecReader;
import org.rivalry.core.model.Candidate;
import org.rivalry.core.model.Category;
import org.rivalry.core.model.Criterion;
import org.rivalry.core.model.DefaultCandidate;
import org.rivalry.core.model.RivalryData;

/**
 * Provides tests for the <code>DefaultDataCollector</code> class.
 */
public class DefaultDataCollectorTest
{
    /** Data collector. */
    private DataCollector _dataCollector;

    /** Data collector specification. */
    private DCSpec _dcSpec;

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataDenverColorado()
    {
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("colorado/denver",
                _dcSpec.getUrl());

        _dataCollector.fetchData(_dcSpec, rivalryData, candidate);

        assertThat(rivalryData.getCandidates().size(), is(1));
        assertThat(rivalryData.getCategories().size(), is(11));
        assertThat(rivalryData.getCriteria().size(), is(9 // Climate
                + 4 // Cost of Living
                + 2 // Crime
                + 24 // Economy
                + 8 // Education
                + 4 // Health
                + 29 // Housing
                + 10 // People
                + 13 // Religion
                + 10 // Transportation
                + 3 // Voting
        ));

        // Climate
        final Double[] climateValues = { 12.6, 53.8, 78.0, 245.0, 86.0, 21.6,
                57.0, 4.8, 5228.0, };
        verifyClimate(candidate, rivalryData, climateValues);

        // Cost of Living
        final Double[] costValues = { 110.0, 103.0, 91.0, 108.0, };
        verifyCostOfLiving(candidate, rivalryData, costValues);

        // Crime
        final Double[] crimeValues = { 6.0, 5.0, };
        verifyCrime(candidate, rivalryData, crimeValues);

        // Economy
        final Double[] economyValues = { 0.1020, -0.0076, 0.2828, 0.0772,
                0.0463, 27936.0,
                47137.0, // first group
                0.1352, 0.1073, 0.1210, 0.1676, 0.1886, 0.1081, 0.1047, 0.0295,
                0.0308, 0.0072, // second group
                0.1476, 0.2307, 0.1695, 0.2236, 0.0005, 0.0989, 0.1184, };
        verifyEconomy(candidate, rivalryData, economyValues);

        // Education
        final Double[] educationValues = { 4375.0, 18.8, 551.0, 713.0, 0.0492,
                0.2270, 0.1542, 0.8256, };
        verifyEducation(candidate, rivalryData, educationValues);

        // Health
        final Double[] healthValues = { 45.0, 87.0, 11.0, 467.6, };
        verifyHealth(candidate, rivalryData, healthValues);

        // Housing
        final Double[] housingValues = { 47.9, 225700.0, -0.0163, 0.5100,
                0.0771,
                0.4129,
                5.71, // first group
                0.0026, 0.0026, 0.0046, 0.0114, 0.0252, 0.1632, 0.2212, 0.3052,
                0.1327, 0.0489, 0.0547, 0.0176,
                0.0101, // second group
                0.1237, 0.0386, 0.0212, 0.0766, 0.1450, 0.1255, 0.1662, 0.0843,
                0.2191, };
        verifyHousing(candidate, rivalryData, housingValues);

        // People
        final Double[] peopleValues = { 612193.0, 3894.0, 0.1031, 37.1,
                258139.0, 2.32, 0.5064, 0.4936, 0.3700, 0.6300, };
        verifyPeople(candidate, rivalryData, peopleValues);

        // Religion
        final Double[] religionValues = { 0.5083, 0.2739, 0.0061, 0.0362,
                0.0154, 0.0111, 0.0152, 0.0154, 0.0127, 0.0459, 0.0641, 0.0007,
                0.0115, };
        verifyReligion(candidate, rivalryData, religionValues);

        // Transportation
        final Double[] transportationValues = { 28.1, 0.6767, 0.1107, 0.0872,
                0.0517, 0.2181, 0.4191, 0.2287, 0.0791, 0.0549, };
        verifyTransportation(candidate, rivalryData, transportationValues);

        // Voting
        final Double[] votingValues = { 0.7545, 0.2304, 0.01504, };
        verifyVoting(candidate, rivalryData, votingValues);
    }

    /**
     * Test the <code>fetchData()</code> method.
     */
    @Ignore
    @Test
    public void fetchDataPortlandOregon()
    {
        final RivalryData rivalryData = new RivalryData();
        final Candidate candidate = createCandidate("oregon/portland",
                _dcSpec.getUrl());

        _dataCollector.fetchData(_dcSpec, rivalryData, candidate);

        assertThat(rivalryData.getCandidates().size(), is(1));
        assertThat(rivalryData.getCategories().size(), is(11));
        assertThat(rivalryData.getCriteria().size(), is(9 // Climate
                + 4 // Cost of Living
                + 2 // Crime
                + 24 // Economy
                + 8 // Education
                + 4 // Health
                + 29 // Housing
                + 10 // People
                + 13 // Religion
                + 10 // Transportation
                + 3 // Voting
        ));

        // Climate
        final Double[] climateValues = { 42.4, 3.3, 154.0, 144.0, 79.0, 36.6,
                63.0, 3.0, 79.0, };
        verifyClimate(candidate, rivalryData, climateValues);

        // Cost of Living
        final Double[] costValues = { 119.0, 108.0, 92.0, 104.0, };
        verifyCostOfLiving(candidate, rivalryData, costValues);

        // Crime
        final Double[] crimeValues = { 6.0, 7.0, };
        verifyCrime(candidate, rivalryData, crimeValues);

        // Economy
        final Double[] economyValues = { 0.0950, 0.0140, 0.3330, 0.0, 0.0900,
                28651.0,
                49295.0, // first group
                0.1273, 0.1032, 0.1121, 0.1628, 0.2023, 0.1207, 0.1079, 0.0313,
                0.0262, 0.0062, // second group
                0.1491, 0.2803, 0.1507, 0.2293, 0.0034, 0.0606, 0.1419, };
        verifyEconomy(candidate, rivalryData, economyValues);

        // Education
        final Double[] educationValues = { 5386.0, 17.5, 543.0, 500.0, 0.0625,
                0.2412, 0.1541, 0.8908, };
        verifyEducation(candidate, rivalryData, educationValues);

        // Health
        final Double[] healthValues = { 68.2, 52.0, 10.0, 389.9, };
        verifyHealth(candidate, rivalryData, healthValues);

        // Housing
        final Double[] housingValues = { 53.4, 266200.0, -0.0643, 0.5444,
                0.0580, 0.3976, 12.55, 0.0075, 0.0061, 0.0040, 0.0047, 0.0077,
                0.0668, 0.1873, 0.4022, 0.1527, 0.0674, 0.0638, 0.0168, 0.0130,
                0.1014, 0.0456, 0.0417, 0.0525, 0.1111, 0.1024, 0.1359, 0.0977,
                0.3117, };
        verifyHousing(candidate, rivalryData, housingValues);

        // People
        final Double[] peopleValues = { 569553.0, 4185.0, 0.0771, 39.0,
                243266.0, 2.28, 0.4962, 0.5038, 0.3913, 0.6087, };
        verifyPeople(candidate, rivalryData, peopleValues);

        // Religion
        final Double[] religionValues = { 0.4469, 0.2287, 0.0159, 0.0271,
                0.0090, 0.0415, 0.0169, 0.0097, 0.0160, 0.0489, 0.0274, 0.0011,
                0.0047, };
        verifyReligion(candidate, rivalryData, religionValues);

        // Transportation
        final Double[] transportationValues = { 26.4, 0.6117, 0.0937, 0.1221,
                0.0607, 0.2350, 0.4474, 0.2089, 0.0541, 0.0546, };
        verifyTransportation(candidate, rivalryData, transportationValues);

        // Voting
        final Double[] votingValues = { 0.7669, 0.2061, 0.02699, };
        verifyVoting(candidate, rivalryData, votingValues);
    }

    /**
     * Set up the tests.
     */
    @Before
    public void setUp()
    {
        final DataCollectorInjector injector = new DefaultDataCollectorInjector();
        _dataCollector = injector.injectDataCollector();

        _dcSpec = readDcSpec();
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
     * @return a new data collector specification read from a file of the given
     *         name.
     */
    private DCSpec readDcSpec()
    {
        final InputStream inputStream = getClass().getResourceAsStream(
                "DataCollectorBestPlaces.xml");
        assertNotNull(inputStream);
        final Reader reader = new InputStreamReader(inputStream);
        final DCSpecReader dcReader = new DCSpecReader();
        final DCSpec dcSpec = dcReader.read(reader);
        assertNotNull(dcSpec);

        return dcSpec;
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyClimate(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Climate";

        verifyRating(candidate, rivalryData, categoryName, "Rainfall (in.)",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Snowfall (in.)",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName,
                "Precipitation Days", values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Sunny Days",
                values[3]);
        verifyRating(candidate, rivalryData, categoryName, "Avg. July High",
                values[4]);
        verifyRating(candidate, rivalryData, categoryName, "Avg. Jan. Low",
                values[5]);
        verifyRating(candidate, rivalryData, categoryName,
                "Comfort Index (higher=better)", values[6]);
        verifyRating(candidate, rivalryData, categoryName, "UV Index",
                values[7]);
        verifyRating(candidate, rivalryData, categoryName, "Elevation ft.",
                values[8]);

    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyCostOfLiving(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Cost of Living";

        verifyRating(candidate, rivalryData, categoryName, "Overall", values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Food", values[1]);
        verifyRating(candidate, rivalryData, categoryName, "Utilities",
                values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Miscellaneous",
                values[3]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyCrime(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Crime";

        verifyRating(candidate, rivalryData, categoryName, "Violent Crime",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Property Crime",
                values[1]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyEconomy(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Economy";

        verifyRating(candidate, rivalryData, categoryName, "Unemployment Rate",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Recent Job Growth",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName, "Future Job Growth",
                values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Sales Taxes",
                values[3]);
        verifyRating(candidate, rivalryData, categoryName, "Income Taxes",
                values[4]);
        verifyRating(candidate, rivalryData, categoryName, "Income per Cap.",
                values[5]);
        verifyRating(candidate, rivalryData, categoryName, "Household Income",
                values[6]);

        verifyRating(candidate, rivalryData, categoryName,
                "Income Less Than 15K", values[7]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 15K and 25K", values[8]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 25K and 35K", values[9]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 35K and 50K", values[10]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 50K and 75K", values[11]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 75K and 100K", values[12]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 100K and 150K", values[13]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 150K and 250K", values[14]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income between 250K and 500K", values[15]);
        verifyRating(candidate, rivalryData, categoryName,
                "Income greater than 500K", values[16]);

        verifyRating(candidate, rivalryData, categoryName,
                "Management, Business, and Financial Operations", values[17]);
        verifyRating(candidate, rivalryData, categoryName,
                "Professional and Related Occupations", values[18]);
        verifyRating(candidate, rivalryData, categoryName, "Service",
                values[19]);
        verifyRating(candidate, rivalryData, categoryName, "Sales and Office",
                values[20]);
        verifyRating(candidate, rivalryData, categoryName,
                "Farming, Fishing, and Forestry", values[21]);
        verifyRating(candidate, rivalryData, categoryName,
                "Construction, Extraction, and Maintenance", values[22]);
        verifyRating(candidate, rivalryData, categoryName,
                "Production, Transportation, and Material Moving", values[23]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyEducation(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Education";

        verifyRating(candidate, rivalryData, categoryName, "School Expend.",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName,
                "Pupil/Teacher Ratio", values[1]);
        verifyRating(candidate, rivalryData, categoryName,
                "Students per Librarian", values[2]);
        verifyRating(candidate, rivalryData, categoryName,
                "Students per Counselor", values[3]);
        verifyRating(candidate, rivalryData, categoryName,
                "2 yr College Grad.", values[4]);
        verifyRating(candidate, rivalryData, categoryName,
                "4 yr College Grad.", values[5]);
        verifyRating(candidate, rivalryData, categoryName, "Graduate Degrees",
                values[6]);
        verifyRating(candidate, rivalryData, categoryName,
                "High School Grads.", values[7]);

    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyHealth(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Health";

        verifyRating(candidate, rivalryData, categoryName,
                "Air Quality (100=best)", values[0]);
        verifyRating(candidate, rivalryData, categoryName,
                "Water Quality (100=best)", values[1]);
        verifyRating(candidate, rivalryData, categoryName,
                "Superfund Sites (100=best)", values[2]);
        verifyRating(candidate, rivalryData, categoryName,
                "Physicians per 100k", values[3]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyHousing(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Housing";

        verifyRating(candidate, rivalryData, categoryName, "Median Home Age",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Median Home Cost",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName, "Home Appreciation",
                values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Homes Owned",
                values[3]);
        verifyRating(candidate, rivalryData, categoryName, "Housing Vacant",
                values[4]);
        verifyRating(candidate, rivalryData, categoryName, "Homes Rented",
                values[5]);
        verifyRating(candidate, rivalryData, categoryName, "Property Tax Rate",
                values[6]);

        verifyRating(candidate, rivalryData, categoryName, "Less Than $20,000",
                values[7]);
        verifyRating(candidate, rivalryData, categoryName,
                "$20,000 to $39,999", values[8]);
        verifyRating(candidate, rivalryData, categoryName,
                "$40,000 to $59,999", values[9]);
        verifyRating(candidate, rivalryData, categoryName,
                "$60,000 to $79,999", values[10]);
        verifyRating(candidate, rivalryData, categoryName,
                "$80,000 to $99,999", values[11]);
        verifyRating(candidate, rivalryData, categoryName,
                "$100,000 to $149,999", values[12]);
        verifyRating(candidate, rivalryData, categoryName,
                "$150,000 to $199,999", values[13]);
        verifyRating(candidate, rivalryData, categoryName,
                "$200,000 to $299,999", values[14]);
        verifyRating(candidate, rivalryData, categoryName,
                "$300,000 to $399,999", values[15]);
        verifyRating(candidate, rivalryData, categoryName,
                "$400,000 to $499,999", values[16]);
        verifyRating(candidate, rivalryData, categoryName,
                "$500,000 to $749,999", values[17]);
        verifyRating(candidate, rivalryData, categoryName,
                "$750,000 to $999,999", values[18]);
        verifyRating(candidate, rivalryData, categoryName,
                "$1,000,000 or more", values[19]);

        verifyRating(candidate, rivalryData, categoryName, "1999 to 2005",
                values[20]);
        verifyRating(candidate, rivalryData, categoryName, "1995 to 1998",
                values[21]);
        verifyRating(candidate, rivalryData, categoryName, "1990 to 1994",
                values[22]);
        verifyRating(candidate, rivalryData, categoryName, "1980 to 1989",
                values[23]);
        verifyRating(candidate, rivalryData, categoryName, "1970 to 1979",
                values[24]);
        verifyRating(candidate, rivalryData, categoryName, "1960 to 1969",
                values[25]);
        verifyRating(candidate, rivalryData, categoryName, "1950 to 1959",
                values[26]);
        verifyRating(candidate, rivalryData, categoryName, "1940 to 1949",
                values[27]);
        verifyRating(candidate, rivalryData, categoryName, "1939 or Earlier",
                values[28]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyPeople(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "People";

        verifyRating(candidate, rivalryData, categoryName, "Population",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Pop. Density",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName, "Pop. Change",
                values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Median Age",
                values[3]);
        verifyRating(candidate, rivalryData, categoryName, "Households",
                values[4]);
        verifyRating(candidate, rivalryData, categoryName, "Household Size",
                values[5]);
        verifyRating(candidate, rivalryData, categoryName, "Male Population",
                values[6]);
        verifyRating(candidate, rivalryData, categoryName, "Female Population",
                values[7]);
        verifyRating(candidate, rivalryData, categoryName,
                "Married Population", values[8]);
        verifyRating(candidate, rivalryData, categoryName, "Single Population",
                values[9]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param categoryName Category name.
     * @param criterionName Criterion name.
     * @param expectedRating Expected rating.
     */
    private void verifyRating(final Candidate candidate,
            final RivalryData rivalryData, final String categoryName,
            final String criterionName, final Double expectedRating)
    {
        final Criterion criterion = rivalryData
                .findCriterionByName(criterionName);
        assertNotNull(criterion);
        final Double rating = candidate.getRating(criterion);
        assertNotNull(rating);
        assertEquals(rating, expectedRating, 0.0001);
        final Category category = rivalryData.findCategoryByName(categoryName);
        assertNotNull(category);
        assertThat(criterion.getCategory(), is(category));
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyReligion(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Religion";

        verifyRating(candidate, rivalryData, categoryName, "Percent Religious",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Catholic",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName, "LDS", values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Baptist", values[3]);
        verifyRating(candidate, rivalryData, categoryName, "Episcopalian",
                values[4]);
        verifyRating(candidate, rivalryData, categoryName, "Pentecostal",
                values[5]);
        verifyRating(candidate, rivalryData, categoryName, "Lutheran",
                values[6]);
        verifyRating(candidate, rivalryData, categoryName, "Methodist",
                values[7]);
        verifyRating(candidate, rivalryData, categoryName, "Presbyterian",
                values[8]);
        verifyRating(candidate, rivalryData, categoryName, "Other Christian",
                values[9]);
        verifyRating(candidate, rivalryData, categoryName, "Jewish", values[10]);
        verifyRating(candidate, rivalryData, categoryName, "Eastern",
                values[11]);
        verifyRating(candidate, rivalryData, categoryName, "Islam", values[12]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyTransportation(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Transportation";

        verifyRating(candidate, rivalryData, categoryName, "Commute Time",
                values[0]);

        verifyRating(candidate, rivalryData, categoryName, "Auto (alone)",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName, "Carpool", values[2]);
        verifyRating(candidate, rivalryData, categoryName, "Mass Transit",
                values[3]);
        verifyRating(candidate, rivalryData, categoryName, "Work at Home",
                values[4]);

        verifyRating(candidate, rivalryData, categoryName,
                "Commute Less Than 15 min.", values[5]);
        verifyRating(candidate, rivalryData, categoryName,
                "Commute 15 to 29 min.", values[6]);
        verifyRating(candidate, rivalryData, categoryName,
                "Commute 30 to 44 min.", values[7]);
        verifyRating(candidate, rivalryData, categoryName,
                "Commute 45 to 59 min.", values[8]);
        verifyRating(candidate, rivalryData, categoryName,
                "Commute greater than 60 min.", values[9]);
    }

    /**
     * @param candidate Candidate.
     * @param rivalryData Rivalry data.
     * @param values Values.
     */
    private void verifyVoting(final Candidate candidate,
            final RivalryData rivalryData, final Double[] values)
    {
        final String categoryName = "Voting";

        verifyRating(candidate, rivalryData, categoryName, "Democrat",
                values[0]);
        verifyRating(candidate, rivalryData, categoryName, "Republican",
                values[1]);
        verifyRating(candidate, rivalryData, categoryName, "Independent Other",
                values[2]);
    }
}
