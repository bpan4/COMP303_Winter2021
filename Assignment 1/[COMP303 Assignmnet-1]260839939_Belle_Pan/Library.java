import java.util.*; 

public class Library {
	private ArrayList<Watchlist> aWatchlists;
	private ArrayList<Movie> aMovies;
	
	public Library (ArrayList<Watchlist> pWatchlists, ArrayList<Movie> pMovies) {
		aWatchlists = pWatchlists;
		aMovies = pMovies;
	}
	
	public void addWatchlist (Watchlist watchlist) { //adds a new watchlist to the ArrayList of watchlists
		aWatchlists.add(watchlist);
	}
	
	public void addMovie (Movie movie) { //adds a new movie to the ArrayList of movies
		aMovies.add(movie);
	}
	
}
