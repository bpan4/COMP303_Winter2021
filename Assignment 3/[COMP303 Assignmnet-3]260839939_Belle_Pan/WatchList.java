package comp303.assignment3;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;
	
	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 *            the name of the list
	 * @pre pName!=null;
	 */
	public WatchList(String pName) {
		assert pName != null;
		aName = pName;
		aNext = 0;
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 * @pre pName!=null;
	 */
	public void setName(String pName) {
		assert pName != null;
		aName = pName;
	}
	
	/**
	 * Adds a watchable at the end of this watchlist.
	 * 
	 * @param pWatchable
	 *            the watchable to add
	 * @pre pWatchable!=null;
	 */
	public void addWatchable(Watchable pWatchable) {
		assert pWatchable != null;
		aList.add(pWatchable);
	}
	
	/**
	 * Retrieves and removes the next watchable to watch from this watchlist. Watchables are retrieved in FIFO order.
	 */
	public Watchable removeNext() {
		return aList.remove(0);
	}
	
	/**
	 * @return the total number of valid watchable elements
	 */
	public int getValidCount() {
		int count = 0;
		for (Watchable item : aList) {
			if (item.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getTotalCount() {
		return aList.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aList.size() - aNext;
	}
	
	@Override
	public Watchable next() {
		assert getRemainingCount() > 0;
		Watchable next = aList.get(aNext);
		aNext++;
		if (aNext >= aList.size()) {
			aNext = 0;
		}
		return next;
	}
	
	@Override
	public void reset() {
		aNext = 0;
	}
	
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}
	
	/**
	 * Compares the WatchList object with another WatchList object. Returns true if they have the same content, and false otherwise.
	 * 
	 * @param o
	 *            the Object (WatchList) to compare
	 * @pre   o != null
	 */
	@Override
	public boolean equals(Object o) {
		assert o!=null;
		if (this == o) {
			return true;
		}
		WatchList watchlist = (WatchList) o;
		if (aList.size() != watchlist.aList.size() && watchlist.hashCode() != this.hashCode()) {
			return false;
		}
		for (int index = 0; index<aList.size(); index++) {
			if(watchlist.aList.get(index).hashCode() == this.aList.get(index).hashCode()) {
				if (!watchlist.aList.get(index).equals(aList.get(index))) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	@Override
    public int hashCode() {
		int hashCode = 0;
		for (Watchable w : aList) {
			hashCode += w.hashCode();
		}
        return hashCode;
    }
	
}
