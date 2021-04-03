package assignment5;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable>, Callback{
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;
	private Optional<Watchable> lastWatchedItem = Optional.empty();
	
	
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
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @pre lastWatchedItem!=null;
	 * 
	 * @throws NoSuchElementException
	 * 				throws this exception when there is no last watched element from this watchlist
	 */
	@Override
	public Watchable lastWatched() throws NoSuchElementException{
		if (lastWatchedItem.isPresent() ) {
			return lastWatchedItem.get();
		}
		throw new NoSuchElementException("You have not watched anything from this WatchList yet.");
	}
	
	/**
	 * Sets the last element that was watched in this watchlist.
	 * 
	 * @param w
	 * 			the element from the watchlist that we will set as the element that was last watched
	 * @pre w!=null;
	 */
	public void setLastWatched(Watchable w) {
		assert w != null;
		lastWatchedItem = Optional.of(w);
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
	private void setName(String pName) {
		assert pName != null;
		aName = pName;
	}
	
	/**
	 * Adds a watchable at the end of this watchlist. 
	 * The watchable will be updated so that it is associated with this watchlist
	 * 
	 * @param pWatchable
	 *            the watchable to add
	 * @pre pWatchable!=null;
	 */
	private void addWatchable(Media pWatchable) {
		assert pWatchable != null;
		aList.add(pWatchable);
		pWatchable.setWatchList(this);
		
	}
	
	/**
	 * Retrieves and removes the Watchable at the specified index.
	 * 
	 * @param pIndex
	 *            the position of the Watchable to remove
	 * @pre pIndex < getTotalCount() && pIndex >= 0
	 */
	private Watchable removeWatchable(int pIndex) {
		assert pIndex < aList.size() && pIndex >= 0;
		if (aNext > pIndex) {
			aNext--;
		}
		return aList.remove(pIndex);
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
	
//	@Override
	private Watchable next() {
		assert getRemainingCount() > 0;
		Watchable next = aList.get(aNext);
		aNext++;
		if (aNext >= aList.size()) {
			aNext = 0;
		}
		return next;
	}
	
//	@Override
	private void reset() {
		aNext = 0;
	}
	
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}

	public Command changeNameCommand(String pName) {
		return new Command() {
			String prevName = getName();
			
			@Override
			public void execute() {
				setName(pName);
			}

			@Override
			public void undo() {
				setName(prevName);
			}
			
			@Override
			public void redo() {
				setName(pName);
			}
		};
	}
	
	public Command addWatchableCommand(Media pWatchable) {
		return new Command() {
			
			@Override
			public void execute() {
				addWatchable(pWatchable);
			}

			@Override
			public void undo() {
				if (aList.contains(pWatchable)) {
					aList.remove(pWatchable);
				}
			}
			
			@Override
			public void redo() {
				addWatchable(pWatchable);
			}
		};
	}
	
	public Command removeWatchableCommand(int pIndex) {
		return new Command() {
			Media watchable;
			
			@Override
			public void execute() {
				watchable = (Media) aList.get(pIndex);
				removeWatchable(pIndex);
			}

			@Override
			public void undo() {
				addWatchable(watchable);
			}
			
			@Override
			public void redo() {
				watchable = (Media) aList.get(pIndex);
				removeWatchable(pIndex);
			}
		};
	}
	
	public Command nextCommand() {
		return new Command() {
			int originalNumber = aNext;
			
			@Override
			public void execute() {
				next();
			}

			@Override
			public void undo() {
				aNext = originalNumber;
			}
			
			@Override
			public void redo() {
				next();
			}
		};
	}
	
	public Command resetCommand() {
		return new Command() {
			int originalNumber = aNext;
			
			@Override
			public void execute() {
				reset();
			}

			@Override
			public void undo() {
				aNext = originalNumber;
			}
			
			@Override
			public void redo() {
				reset();
			}
		};
	}

}
