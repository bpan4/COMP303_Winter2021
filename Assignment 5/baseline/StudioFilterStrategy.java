package comp303.assignment5;

import comp303.assignment5.TVShow.Episode;

public class StudioFilterStrategy implements WatchListFilter {

    private final String aStudio;

    public StudioFilterStrategy(String pStudio) {
        aStudio = pStudio;
    }

    /**
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * @return true if the Watchable must be included, false otherwise
     */
    @Override
    public boolean filter(Movie pMovie) {
        assert pMovie != null;
        return pMovie.getStudio().equals(aStudio);
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
        assert pTVShow != null;
        return pTVShow.getStudio().equals(aStudio);
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
        assert pEpisode != null;
        return pEpisode.getStudio().equals(aStudio);
    }
}
