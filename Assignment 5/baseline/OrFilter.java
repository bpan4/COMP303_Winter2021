package comp303.assignment5;

import java.util.Set;

import comp303.assignment5.TVShow.Episode;

public class OrFilter implements WatchListFilter {
	
	private final Set<WatchListFilter> aFilters;
	
	/**
	 * Creates a new filter that is the disjunction of the filters passed as argument. There is no guarantee about the
	 * order of execution of the filters.
	 * 
	 * @param pFilters
	 *            list of filters to combine
	 */
	public OrFilter(WatchListFilter... pFilters) {
		assert pFilters != null;
		aFilters = Set.of(pFilters);
	}
	
	/**
	 * @param pMovie
	 *            a Watchable to potentially include in the Watchlist
	 * @pre pMovie != null
	 * @return true if the Watchable must be included, false otherwise
	 */
	@Override
	public boolean filter(Movie pMovie) {
		assert pMovie != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pMovie)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param pTVShow
	 *            a Watchable to potentially include in the Watchlist
	 * @pre pTVShow != null
	 * @return true if the Watchable must be included, false otherwise
	 */
	@Override
	public boolean filter(TVShow pTVShow) {
		assert pTVShow != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pTVShow)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param pEpisode
	 *            a Watchable to potentially include in the Watchlist
	 * @pre pTVShow != null
	 * @return true if the Watchable must be included, false otherwise
	 */
	@Override
	public boolean filter(Episode pEpisode) {
		assert pEpisode != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pEpisode)) {
				return true;
			}
		}
		return false;
	}
}
