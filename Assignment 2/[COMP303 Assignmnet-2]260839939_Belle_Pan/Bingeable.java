
public interface Bingeable<Watchable> {
	//bingeable objects include TVShows and WatchLists
	
	//returns an iterator of the specified type
	public Sequential<Watchable> getIterator();
	
	//returns the name of the Bingeable object
	public String getName();
	
}
