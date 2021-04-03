package comp303.assignment5;

import comp303.assignment5.TVShow.Episode;

public class FirstEpisodeFilterStrategy implements WatchListFilter {

    /**
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(Movie pMovie) {
        return false;
    }

    /**
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @pre pTVShow != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(TVShow pTVShow) {
        return true;
    }

    /**
     *
     * @param pEpisode
     *            a Watchable to potentially include in the Watchlist
     * @pre pEpisode != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(Episode pEpisode) {
        return pEpisode.getEpisodeNumber() == 1;
    }
}
