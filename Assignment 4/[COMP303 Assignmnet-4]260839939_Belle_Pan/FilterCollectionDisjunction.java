package comp303assignment4;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of ways to filter Watchable elements
 */

public class FilterCollectionDisjunction implements WatchListFilter{
	
	private List<WatchListFilter> aFilters;
	
	public FilterCollectionDisjunction() {
		aFilters = new ArrayList<>();
	}
	
	
	/**
     * Indicates whether a Watchable elements should be included in the watchlist
     * Does this by passing the Watchable element to each filter's individual filter(Movie pMovie) method
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * 			  the input is not null
     * 		aFilters !=null
     * 			  there are filters that will be applied
     * @return true if the Watchable is said to be included by either one of the filters, false otherwise
     */
	
	@Override
	public boolean filter(Movie pMovie) {
		assert pMovie != null && aFilters != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pMovie) == true) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
     * Indicates whether a Watchable elements should be included in the watchlist
     * Does this by passing the Watchable element to each filter's individual filter(TVShow pTVShow) method
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @pre pTVShow != null
     * 			  the input is not null
     * 		aFilters !=null
     * 			  there are filters that will be applied
     * @return true if the Watchable is said to be included by either one of the filters, false otherwise
     */
	@Override
	public boolean filter(TVShow pTVShow) {
		assert pTVShow != null && aFilters != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pTVShow) == true) {
				return true;
			}
		}
		return false;
	}

	
	/**
     * Indicates whether a Watchable elements should be included in the watchlist
     * Does this by passing the Watchable element to each filter's individual filter(Episode pEpisode) method
     *
     * @param pEpisode
     *            a Watchable to potentially include in the Watchlist
     * @pre pEpisode != null
     * 			  the input is not null
     * 		aFilters !=null
     * 			  there are filters that will be applied
     * @return true if the Watchable is said to be included by either one of the filters, false otherwise
     */
	@Override
	public boolean filter(Episode pEpisode) {
		assert pEpisode != null && aFilters != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pEpisode) == true) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
     * Adds a WatchListFilter to the collection (a List of all WatchListFilter s denoted as aFilters)
     *
     * @param pFilter
     *            a WatchListFilter to potentially add to the collection
     * @pre pFilter != null
     * 
     */
	public void addFilter(WatchListFilter pFilter) {
		assert pFilter != null;
		if (!aFilters.contains(pFilter)) {
			aFilters.add(pFilter);
		}
	}
	
	
	/**
     * Removes a WatchListFilter from the collection (a List of all WatchListFilter s denoted as aFilters)
     *
     * @param pFilter
     *            a WatchListFilter to potentially remove from the collection
     * @pre pFilter != null
     * 
     */
	public void removeFilter(WatchListFilter pFilter) {
		assert pFilter != null;
		if (aFilters.contains(pFilter)) {
			aFilters.remove(pFilter);
		}
	}
	
	/**
     * Returns a list with all the WatchListFilters in the collection
     * 
     * @pre aFilters != null
     * 
     */
	public List<WatchListFilter> getFilters() {
		assert aFilters != null;
		List<WatchListFilter> copyOfaFilters = new ArrayList<>();
		for (WatchListFilter filter : aFilters) {
			copyOfaFilters.add(filter);
		}
		return copyOfaFilters;
	}
}

