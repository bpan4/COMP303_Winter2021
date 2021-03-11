package comp303.assignment3;

import java.util.Comparator;

/**
 * Interface for Strategy design pattern
 */
interface WatchListGenerationInfo extends Comparator<Watchable> {
	
	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 * 
	 * @param pWatchable
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filter(Movie pMovie);
	
	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 * 
	 * @param pWatchable
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filter(TVShow pShow);
	
	/**
	 * Indicates whether a Watchable elements should be included in the watchlist.
	 * 
	 * @param pWatchable
	 *            a Watchable to potentially include in the Watchlist
	 * @return true if the Watchable must be included, false otherwise
	 */
	public boolean filter(Episode pEpisode);
}