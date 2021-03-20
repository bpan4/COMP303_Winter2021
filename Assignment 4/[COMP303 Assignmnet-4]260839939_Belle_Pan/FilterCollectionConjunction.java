package comp303assignment4;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of ways to filter Watchable elements
 */

public class FilterCollectionConjunction implements WatchListFilter{
	
	private List<WatchListFilter> aFilters;
	
	public FilterCollectionConjunction() {
		aFilters = new ArrayList<>();
	}
	
	
	
	/**
     * Indicates whether a Watchable elements should be included in the watchlist
     * Does this by passing the Watchable element to each filter's individual filter(Movie pMovie) method
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * 	  		  the input is not null
     * 		aFilters !=null
     * 			  there are filters that will be applied
     * @return true if the Watchable is said to be included by both filters, false otherwise
     */
	
	@Override
	public boolean filter(Movie pMovie) {
		assert pMovie != null && aFilters != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pMovie) == false) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
     * Indicates whether a Watchable elements should be included in the watchlist
     * Does this by passing the Watchable element to each filter's individual filter(TVShow pTVShow) method
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @pre pTVShow != null
     * 	  		  the input is not null
     * 		aFilters !=null
     * 			  there are filters that will be applied
     * @return true if the Watchable is said to be included by both filters, false otherwise
     */
	@Override
	public boolean filter(TVShow pTVShow) {
		assert pTVShow != null && aFilters != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pTVShow) == false) {
				return false;
			}
		}
		return true;
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
     * @return true if the Watchable is said to be included by both filters, false otherwise
     */
	@Override
	public boolean filter(Episode pEpisode) {
		assert pEpisode != null && aFilters != null;
		for (WatchListFilter filter : aFilters) {
			if (filter.filter(pEpisode) == false) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
     * Adds a WatchListFilter to the collection (a List of all WatchListFilter s denoted as aFilters)
     *
     * @param pFilter
     *            a WatchListFilter to potentially add to the collection
     * @pre !aFilters.contains(pFilter)
     * 			  the WatchListFilter is only added if it does not already exist in the collection
     * 
     */
	public void addFilter(WatchListFilter pFilter) {
		if (!aFilters.contains(pFilter)) {
			aFilters.add(pFilter);
		}
	}
	
	
	/**
     * Removes a WatchListFilter from the collection (a List of all WatchListFilter s denoted as aFilters)
     *
     * @param pFilter
     *            a WatchListFilter to potentially remove from the collection
     * @pre aFilters.contains(pFilter)
     * 			  the WatchListFilter is only removed if it already exists in the collection
     * 
     */
	public void removeFilter(WatchListFilter pFilter) {
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
