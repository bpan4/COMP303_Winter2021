import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Driver {
	public void main(String[] args) {
		
		//initialize two new files
		File file1 = new File("C:\\\\Users\\\\user\\\\Downloads\\\\HarryPotter1.MOV");
		File file2 = new File("C:\\Users\\user\\Downloads\\LifeofPi.MP4");
		
		//initialize two Movies
		Movie movie1 = new Movie(file1, "Harry Potter and the Philosopher's Stone", "French", "Warner Brothers");
		Movie movie2 = new Movie(file2, "Life of Pi", "English", "20th Century Fox");
		
		//initialize a WatchList
		WatchList watchlist = new WatchList("My first watchlist!");
		//adding the two movies into the watchlist
		watchlist.addMovie(movie1);
		watchlist.addMovie(movie2);
		
		//now, creating a library using the above watchlist and movies
		Library library = new Library();
		library.addWatchList(watchlist);
		
		//there should only be one french movie in this watchlist, as we have not specified how many movies we want
		WatchList frenchWatchList = library.filterMovies("All French Movies", "French", "", 0);
		System.out.println(frenchWatchList.getMovies().size()); //should be 1
		
		//initialize another French movie and add it to the library
		File file3 = new File("C:\\Users\\user\\Downloads\\Intouchables.MP4");
		Movie movie3 = new Movie(file3,"Intouchables", "French", "Gaumont");
		library.addMovie(movie3);
		
		//now we update frenchWatchList
		// there should now be two French movies in this watchlist!
		frenchWatchList = library.filterMovies("All French Movies", "French", "", 2);
		System.out.println(frenchWatchList.getMovies().size()); //should be 2
		
		
		//now we test out EpisodesAtRandom!
		//we first need to create some episodes
		File efile1 = new File("C:\\\\Users\\\\user\\\\Downloads\\\\Ladders.MOV");
		File efile2 = new File("C:\\Users\\user\\Downloads\\History101.MP4");
		
		Episode episode1 = new Episode(efile1, "Ladders", "English", "Sony Pictures Television", 0401);
		Episode episode2 = new Episode(efile2, "History 101", "English", "Sony Pictures Television", 0601);
		
		//now, we create a linked list of the episodes we have and use it to initialize a TVShow object
		List<Episode> show = new LinkedList<Episode>(Arrays.asList(episode1, episode2));
		TVShow tvshow = new TVShow(show, "Community", episode1.getStudio());
		library.addTVShow(tvshow);
		
		//this should give us a watchlist consisting of one single episode of the TV show
		WatchList randomEpisodes = library.filterEpisodes("A Random Community Episode", "Community", "English", "Sony Pictures Television", 1);
		System.out.println(randomEpisodes.getNumberEpisodes()); //should be 1
	}
}
