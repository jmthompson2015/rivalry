package org.rivalry.swingui;

import org.rivalry.core.model.RivalryData;
import org.rivalry.swingui.filter.CandidateFilter;

/**
 * Defines methods required by a user interface user preferences.
 */
public interface UIUserPreferences
{
    /**
     * Clear all candidate filter user preferences.
     * 
     * @param rivalryData Rivalry data.
     */
    void clearCandidateFilter(RivalryData rivalryData);

    /**
     * Clear all look and feel user preferences.
     */
    void clearLookAndFeel();

    /**
     * @param rivalryData Rivalry data.
     * 
     * @return the candidate filter.
     */
    CandidateFilter getCandidateFilter(RivalryData rivalryData);

    /**
     * @return the look and feel.
     */
    LookAndFeel getLookAndFeel();

    /**
     * @param rivalryData Rivalry data.
     * @param candidateFilter Candidate filter to set.
     */
    void putCandidateFilter(RivalryData rivalryData, CandidateFilter candidateFilter);

    /**
     * @param laf Look and feel to set.
     */
    void putLookAndFeel(LookAndFeel laf);
}
