import java.util.*; 

public class Watchlist {
	private String aName; //represents the name of the watchlist, should be mutable
	private ArrayList<Movie> aMovies; //stores all the movies in the watchlist
	
	public Watchlist (String pName, ArrayList<Movie> pMovies){
		aName = pName;
		aMovies = pMovies;
	}
	
	public void changeName(String pName) { //used to change the name of the watchlist
		aName = pName;
		System.out.println("The name of the watchlist has been updated to " + pName + "."); //allows the client to know that it was successful
	}
	
	public ArrayList<Movie> getMovies() { //returns all the movies in the watchlist
		ArrayList<Movie> accessAll = new ArrayList<Movie>();
		for (Movie movie: aMovies) {
			accessAll.add(movie);
		}
		return accessAll; //return a deep copy so that the client may not accidentally tamper with 
						//the watchlist outside of the defined methods
	}
	
	public void removeFirstMovie() { //removes only the first movie in the watchlist
		if (!aMovies.isEmpty()) {
			aMovies.remove(0);
			System.out.println("The movie has been removed from " + aName + ".");
		}
		System.out.println(aName + " is empty."); //does not remove any movies if the watchlist is empty
	}
	
	public int numValid() { //returns the number of valid movies in the watchlist
		int valid = 0;
		for (Movie movie: this.aMovies) {
			if (movie.isValid()) {
				valid+=1;
			}
		}
		System.out.println("There are " + valid + " valid movies in " + aName +"."); //print a string here so that we know what the int represents!
		return valid;
	}
	
	public ArrayList<String> allPubStudios() { //returns all the publishing studios for the movies in the watchlist
		ArrayList<String> allPubStudios = new ArrayList<String>();
		for (Movie movie: aMovies) {
			if (!allPubStudios.contains(movie.getPubStudio())) { //ensures that there are no duplicates in the ArrayList!
				allPubStudios.add(movie.getPubStudio());
			}
		}
		return allPubStudios;
	}
	
	public ArrayList<String> allLanguages () { //returns all the languages of the movies in the watchlist
		ArrayList<String> allLanguages = new ArrayList<String>();
		for (Movie movie: aMovies) {
			if (!allLanguages.contains(movie.getLanguage())) { //ensures that there are no duplicates in the ArrayList!
				allLanguages.add(movie.getLanguage());
			}
		}
		return allLanguages;
	}
	
	//extra methods
	
	public String removeMovie(Movie pMovie) {
		if (aMovies.contains(pMovie)) {
			aMovies.remove(pMovie);
			return "The movie has been removed from " + aName + ".";
		}
		return "The movie does not exist in " + aName + ".";
	}
	
	public String addMovie (Movie newMovie) {
		if (aMovies.contains(newMovie)) {
			return "The movie is already in " + aName + ".";
		}
		aMovies.add(newMovie);
		return "The movie was successfully added into " + aName + ".";
	}
	
	public ArrayList<String> allTitles() { //
		ArrayList<String> names = new ArrayList<String>();
		for (Movie movie: aMovies) {
			names.add(movie.getTitle());
		}
		return names;
	}
	
	public String getName() {
		return aName;
	}
}

