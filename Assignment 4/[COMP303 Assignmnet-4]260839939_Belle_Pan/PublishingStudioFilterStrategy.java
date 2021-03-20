package comp303assignment4;

/*
 * Strategy to filter Movies, TVShows or Episodes whose language is equal to aStudio
 */
public class PublishingStudioFilterStrategy implements WatchListFilter {

    private final String aStudio;

    public PublishingStudioFilterStrategy(String pStudio) {
    	assert pStudio != null;
    	aStudio = pStudio;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
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
     * Indicates whether a Watchable elements should be included in the watchlist.
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
     * Indicates whether a Watchable elements should be included in the watchlist.
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
