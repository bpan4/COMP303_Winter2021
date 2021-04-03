package assignment5;

public interface Observer extends Watchable{
	
	/**
	 * Indicates the last Watchable element that was watched
	 * 
	 * @return the last Watchable element that was watched
	 */
	Watchable lastWatched();
}
