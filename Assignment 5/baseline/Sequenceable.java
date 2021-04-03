package comp303.assignment5;

/**
 * Represents an element that can have a prequel or sequel.
 */
interface Sequenceable<T extends Sequenceable<T>> extends Watchable {
	
	/**
	 * Indicates if the element is preceded by another element (e.g., a previous episode or a prequel).
	 * 
	 * @return true if there is a previous element
	 */
	public boolean hasPrevious();
	
	/**
	 * Indicates if the element is followed by another element (e.g., another episode or a sequel).
	 * 
	 * @return true if there is a following element
	 */
	public boolean hasNext();
	
	/**
	 * Retrieves the previous element.
	 * 
	 * @return a reference to the previous element.
	 * @pre hasPrevious()
	 */
	public T getPrevious();
	
	/**
	 * Retrieves the next element.
	 * 
	 * @return a reference to the next element.
	 * @pre hasNext();
	 */
	public T getNext();
	
}
