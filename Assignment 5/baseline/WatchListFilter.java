package comp303.assignment5;

import comp303.assignment5.TVShow.Episode;

/**
 * Interface for Strategy design pattern
 */
interface WatchListFilter {
	
	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 * 
	 * @param pMovie
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filter(Movie pMovie);
	
	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 * 
	 * @param pTVShow
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filter(TVShow pTVShow);
	
	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 * 
	 * @param pEpisode
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filter(Episode pEpisode);
}