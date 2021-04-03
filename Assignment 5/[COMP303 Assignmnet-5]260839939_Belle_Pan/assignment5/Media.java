package assignment5;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Media implements Watchable{
	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aTags = new HashMap<>();
	protected Optional<WatchList> aWatchList = Optional.empty();
	
	Media(String pTitle, Language pLanguage, String pStudio) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
	}
	
	/**
	 * Updates the WatchList that the watchable is associated with
	 * 
	 * @param pWatchList != null
	 */
	
	public void setWatchList(WatchList pWatchList) {
		assert pWatchList != null;
		aWatchList = Optional.of(pWatchList);
	}
	
	/**
	 * Returns the watchlist the TVShow is associated with, i.e. the watchlist that the TVShow has been added to
	 * 
	 * @pre aWatchList.isPresent()
	 * 
	 * @return the watchlist associated with the watchable
	 */
	public WatchList getWatchList() {
		assert aWatchList.isPresent();
		return aWatchList.get();
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	public Language getLanguage() {
		return aLanguage;
	}
	
	public String getStudio() {
		return aStudio;
	}
	
	public String setInfo(String pKey, String pValue) {
		assert pKey != null && !pKey.isBlank();
		if (pValue == null) {
			return getaTags().remove(pKey);
		}
		else {
			return getaTags().put(pKey, pValue);
		}
	}
	
	public String getInfo(String pKey) {
		assert hasInfo(pKey);
		return getaTags().get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		assert pKey != null && !pKey.isBlank();
		return getaTags().containsKey(pKey);
	}

	public Map<String, String> getaTags() {
		return aTags;
	}

	public void setaTags(Map<String, String> aTags) {
		this.aTags = aTags;
	}
}
