import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TVShow implements Watchable, Bingeable<Episode> {
	
	//a LinkedList of episodes in the TV show
	private List<Episode> aList = new LinkedList<>();
	
	//a String to represent the name of the TV show
	private String aName;
	
	//used to set custom information
	private Map<String, String> aTags = new HashMap<>();
	
	//used to store the production studio
	private String aStudio;
	
	//initializes the parameters of a TV show using a LinkedList input and a String for the name
	public TVShow (List<Episode> pList, String pName, String pStudio) {
		aList = pList;
		aName = pName;
		aStudio = pStudio;
	}
	
	public List<Episode> getEpisodes() {
		return Collections.unmodifiableList(aList);
	}
	
	//returns the name of the TV show
	public String getName() {
		return aName;
	}
	
	
	//returns the validity of the TV show as a boolean value
	public boolean isValid() {
		//iterate through all the episodes in the show
		for (Episode e : aList) {
			//if one of the episodes is NOT valid, then we return false
			if (!e.isValid()) {
				return false;
			}
		}
		//if all episodes are valid, we return true
		return true;
	}
	
	//returns the name of the TV show as a String
	public String getTitle() {
		return aName;
	}
	
	//returns the language of the TV show as a String
	public String getLanguage() {
		return aList.get(0).getLanguage();
	}
	
	//returns the publishing studio of the TV show as a String
	public String getStudio() {
		return aStudio;
	}
	
	//returns the value associated with the key of the TV show
	public String getTag(String pKey) {
		return aTags.get(pKey);
	}
	
	//set the custom information for a TV show given a key and value
	public void setTag(String pKey, String pValue) {
		if (pValue == null) {
			aTags.remove(pKey);
		}
		else {
			aTags.put(pKey, pValue);
		}
	}
	
	//adding an episode to the TV show
	//adds to the end of the linked list
	public void addEpisode(Episode e) {
		aList.add(e);
	}
	
	//gets an episode of a TV show given the episode number
	public Episode getEpisode(int num) {
		for (Episode e: aList) {
			if (e.getSeqNum()==num) {
				return e;
			}
		}
		throw new IllegalArgumentException("There is no episode of that number in this TV show.");		
	}
	
	@Override
	public Sequential<Episode> getIterator() {
		return new ShowIterator();
	}

	//returns an iterator
	private class ShowIterator implements Sequential<Episode> {
		
		//keeps track of which episode we are on
		//the episodes are numbered starting from 1
		private int currEpisode = 1;
		
		//returns true if there is an episode following
		//returns false otherwise
		@Override
		public boolean hasNext() {
			if (currEpisode > aList.size()) {
				return false;
			}
			return true;
		}
	
		@Override
		public boolean hasPrev() {
			if (currEpisode <= 1) {
				return false;
			}
			return true;
		}
		
		@Override
		public Episode next() {
			if (hasNext()) {
				return getEpisode(currEpisode++);
			}
			throw new IllegalArgumentException("This is the last episode.");
		}
	
		@Override
		public Episode prev() {
			// TODO Auto-generated method stub
			if (hasPrev()) {
				return getEpisode(--currEpisode);
			}
			throw new IllegalArgumentException("this is first ep");
		}
	
	
		
	
	}
	
}