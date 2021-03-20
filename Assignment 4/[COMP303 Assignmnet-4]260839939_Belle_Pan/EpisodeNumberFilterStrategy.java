package comp303assignment4;

/*
 * Strategy to filter Movies, TVShows or Episodes whose language is equal to aStudio
 */
public class EpisodeNumberFilterStrategy implements WatchListFilter {

    private final int aEpisodeNumber;

    public EpisodeNumberFilterStrategy(int pEpisodeNumber) {
    	assert pEpisodeNumber > 0;
    	aEpisodeNumber = pEpisodeNumber;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @return @return false, this filter strategy does not apply to Movie objects
     */
    @Override
    public boolean filter(Movie pMovie) {
        return false;
    }

    /**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @return false, this filter strategy does not apply to TVShow objects
     */
    @Override
    public boolean filter(TVShow pTVShow) {
        return false;
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
        return pEpisode.getEpisodeNumber() == aEpisodeNumber;
    }

}