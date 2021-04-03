package comp303.assignment5;

/**
 * Represents a list of elements to watch in sequence ("binge")
 */
interface Bingeable<T extends Watchable> extends Iterable<T> {
	
	/**
	 * @return the total number of Watchable elements in this object
	 */
	public int getTotalCount();
	
	/**
	 * @return the number of Watchable elements that haven't been watched yet
	 */
	public int getRemainingCount();
	
	/**
	 * Resets the status of the elements to watch
	 */
	public void reset();
	
	/**
	 * Returns an element in the watchlist and mark that element as accessed
	 *
	 * @return the next element to access
	 * @pre getRemainingCount() > 0
	 */
	public T next();
}