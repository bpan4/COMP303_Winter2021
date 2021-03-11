
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	
	// Optional
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 */
	public void addMovie(Movie pMovie) {
		aMovies.add(pMovie);
	}
	
	// Optional
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 */
	public void addWatchList(WatchList pList) {
		aWatchLists.add(pList);
		for (Movie movie : pList.getMovies()) {
			addMovie(movie);
		}
	}
	 //adds a TV show, denoted "pTVShow",  to the library
	//adds the episodes in the show into the set of episodes in the library also!
	public void addTVShow(TVShow pTVShow) {
		aTVShows.add(pTVShow);
		for (Episode e : pTVShow.getEpisodes()) {
			aEpisodes.add(e);
		}
	}
	//adds an episode to the library
	public void addEpisode(Episode pEpisode) {
		aEpisodes.add(pEpisode);
	}

	//filters movies by input specified
	public WatchList filterMovies(String name, String language, String pubStudio, int number) {
		WatchList newWatchlist = new WatchList(name);
		if (number==0) {
			for (Movie m : aMovies) {
				if (m.getLanguage().equals(language) || m.getStudio().equals(pubStudio)) {
					newWatchlist.addMovie(m);
				}
			}
			return newWatchlist;
		}
		
		for (Movie m : aMovies) {
			while (number>0) {
				if (m.getLanguage().equals(language) || m.getStudio().equals(pubStudio)) {
					newWatchlist.addMovie(m);
					number--;
				}
			}
		}
		return newWatchlist;
	}
	
	//filters movies by input specified
	public WatchList filterEpisodes(String nameOfWatchList, String nameOfShow, String language, String pubStudio, int number) {
		WatchList newWatchlist = new WatchList(nameOfWatchList);
		
		//keeps track of how many episodes there should be in the watchlist
		int size = 0;
		
		//if the number of episodes wanted is not specified, we simply return a WatchList with the TV show that fits the other specified requirements
		if (number==0) {
			for (TVShow show : aTVShows) {
				if (show.getName().equals(nameOfShow) || show.getLanguage().equals(language) || show.getStudio().equals(pubStudio)) {
					newWatchlist.addTVShow(show);
				}
			}
			return newWatchlist;
		}
		//if the number of episodes wanted is specified, then we have to randomly select that number of episodes to add into the WatchList
		for (TVShow show : aTVShows) {
			if (show.getName().equals(nameOfShow) || show.getLanguage().equals(language) || show.getStudio().equals(pubStudio)) {
				while (number>0) {
					Episode ep = show.getEpisodes().get( (int) getRandom(0, show.getEpisodes().size()));
					newWatchlist.addEpisode(ep);
					if (newWatchlist.getNumberEpisodes()==(size+1)) {
						number--;
						size++;
					}
				}
			}
		}
		return newWatchlist;
	}
	
	//generates a random number in a given range
	public double getRandom(int min, int max) {
		double rand = (Math.random()*((max-min)+1)) +min;
		return rand;
	}

}
