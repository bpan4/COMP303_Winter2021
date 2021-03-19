package comp303.assignment3;

import java.io.File;

public class Driver {
	public void main(String[] args) {
		
		
		
		//test to see if only one Library object is created in the application
		
		
		//create a Library object
		Library library1 = Library.getInstanceOfLibrary("This should be the only library in this application!");
		//try to create another Library object!
		Library library2 = Library.getInstanceOfLibrary("This should be identical to library1");
		//check if they are the same using their hashCode!
		boolean hashCodeEquality = library1.hashCode() == library2.hashCode();
		if (hashCodeEquality) {
			System.out.println("There was only one instance of a library created!");
		}
		else {
			System.out.println("There is something wrong with the implementation of the Singleton design pattern in class Library!");
		}
		
		
		
		//test to see if EmailID is truly optional in Library object
		
		
		//we will try to see if there is an EmailID associated with the Library object
		try {
			System.out.println(library1.getEmailID());
			System.out.println("There is something wrong with the implementation of the Optional<String> EmailID field in class Library!");
		}
		catch (Exception AssertionError){
			System.out.println("There is no EmailID associated with the Library object.");
		}
		//now, we will update the EmailID of the Library object and try to access it again
		library1.setEmailID("This is my EmailID that I set successfully!");
		try {
			System.out.println(library1.getEmailID());
			System.out.println("There is now an EmailID that is associated with the Library object! Yay!");
		}
		catch (Exception AssertionError){
			System.out.println("There is an error with the setting and getting of the EmailID field of class Library!");
		}
		
		
		
		//For any given title, there can only ever be one Movie object with that title in the application
		
		
		//initialize two new files
		File movieFile1 = new File("C:\\\\Users\\\\user\\\\Downloads\\\\HarryPotter1.MOV");
		File movieFile2 = new File("C:\\Users\\user\\Downloads\\ARandomMovie.MP4");
		//create two movie objects
		Movie movie1 = (Movie) MovieFactory.getMovie(movieFile1, "Harry Potter 1", Language.ENGLISH, "Warner Brother Studios");
		Movie movie2 = (Movie) MovieFactory.getMovie(movieFile2, "Harry Potter 1", Language.ANCIENT_GREEK, "This Studio Doesn't Matter");
		//this should result in "true"! movie1 should be returned in movie2
		System.out.println("movie1 and movie 2 are the same:"+ Boolean.toString(movie2.equals(movie1)));
		
		
		
		//For any given title, there can only ever be one Movie object with that title in the application
		
		
		//create two TVShow objects
		TVShow TVShow1 = (TVShow) TVShowFactory.getTVShow("Community", Language.ENGLISH, "Warner Brother Studios");
		TVShow TVShow2 = (TVShow) TVShowFactory.getTVShow("Community", Language.ANCIENT_GREEK, "This Studio Doesn't Matter");
		//this should result in "true"! TVShow1 should have the same object stored as TVShow2
		System.out.println("movie1 and movie 2 are the same:"+ Boolean.toString(TVShow1.equals(TVShow2)));
		
		
		
		//Two WatchLists are compared and seen as equal if they have the same content, regardless of the title of the WatchList
		
		
		//create two Watchlists containing the same objects but different names
		WatchList watchList1 = new WatchList("This is the first watchlist!");
		watchList1.addWatchable(TVShow1);
		watchList1.addWatchable(movie1);
		WatchList watchList2 = new WatchList("This is the second watchlist!");
		watchList2.addWatchable(TVShow1);
		watchList2.addWatchable(movie1);
		//now we test if these are considered duplicates
		//this should yield "true"!
		System.out.println("These two watchLists are considered duplicates:" + Boolean.toString(watchList1.equals(watchList2)));
		
		//now we create two Watchlists containing the same objects but in a different order
		WatchList watchList3 = new WatchList("This is the third watchlist!");
		watchList3.addWatchable(movie1);
		watchList3.addWatchable(TVShow1);
		WatchList watchList4 = new WatchList("This is the fourth watchlist!");
		watchList4.addWatchable(TVShow1);
		watchList4.addWatchable(movie1);
		//now we test if these are considered duplicates
		//this should yield "false"!
		System.out.println("These two watchLists are considered duplicates:" + Boolean.toString(watchList1.equals(watchList2)));
	}
}
