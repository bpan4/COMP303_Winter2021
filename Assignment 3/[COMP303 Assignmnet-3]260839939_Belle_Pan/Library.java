package comp303.assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {
	
	private static Library aSingleInstance = null;
	private String aName;
	private Optional<String> aEmailID;
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();
	
	/**
	 * Private constructor of Library class restricted to the class itself
	 * 
	 * @param pName
	 * 			The name of the library to be created
	 * @pre pName!=null
	 */
	private Library(String pName) {
		this.setName(pName);
		aEmailID = Optional.empty();
		aSingleInstance = this;
	}
	
	/**
	 * Creates an instance of Library class. Ensures that a Library is created only if there are no other instances of the Library class.
	 * 
	 * @param pName
	 * 			The name of the library to be created
	 * @pre pName != null
	 */
	public static synchronized Library getInstanceOfLibrary(String pName) {
		assert pName != null;
		if (aSingleInstance == null) {
			aSingleInstance = new Library(pName);
		}
		return aSingleInstance;
	}
	
	/**
	 * Sets the name of the Library object.
	 * 
	 * @param pName
	 * 			The name of the library to be created
	 * @pre pName != null
	 */
	public void setName(String pName) {
		assert pName != null;
		this.aName = pName;
	}

	/**
	 * Gets the name of the Library object.
	 * 
	 * @pre pName != null
	 */
	public String getName() {
		assert aName != null;
		return aName;
	}

	/**
	 * Sets the EmailID of the Library object.
	 * 
	 * @param pEmailID
	 * 			The EmailID of the library to be created
	 * @pre pEmailID != null
	 */
	public void setEmailID(String pEmailID) {
		assert pEmailID != null;
		this.aEmailID = Optional.of(pEmailID);
	}

	/**
	 * Gets the EmailID of the Library object.
	 * 
	 * @pre pEmailID != null
	 */
	public String getEmailID() {
		assert aEmailID.isPresent();
		return aEmailID.get();
	}
	
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable movie : pList) {
			addMovie((Movie) movie);
		}
	}
	
	/**
	 * Adds a TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow
	 *            the TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism
	 * 
	 * @param pName
	 *            the name of the watchlist to create
	 * @param pGenerationParameters
	 *            the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListGenerationInfo pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		List<Watchable> items = new ArrayList<>();
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filter(show)) {
				for (Episode episode : show) {
					if (pGenerationParameters.filter(episode)) {
						items.add(episode);
					}
				}
			}
		}
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filter(movie)) {
				items.add(movie);
			}
		}
		Collections.sort(items, pGenerationParameters);
		WatchList watchlist = new WatchList(pName);
		for (Watchable item : items) {
			watchlist.addWatchable(item);
		}
		return watchlist;
	}
}
