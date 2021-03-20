package comp303assignment4;
import java.io.File;
import java.util.*;

/**
 * Represents a single TV show, with at least a title, language, and publishing studio. Each TVShow aggregates episodes.
 */
public class TVShow implements Watchable, Bingeable<Episode> {
	
	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo;
	private Episode aPrototype;
	
	private List<Episode> aEpisodes = new ArrayList<>();
	private int aNextToWatch;
	
	/**
	 * Creates a TVShow with required metadata about the show.
	 *
	 * @param pTitle
	 *            official title of the TVShow
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	public TVShow(String pTitle, Language pLanguage, String pStudio) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aNextToWatch = 0;
		aInfo = new HashMap<>();
	}
	
	@Override
	public void watch() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				episode.watch();
			}
		}
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
	
	public String getInfo(String pKey) {
		return aInfo.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aInfo.containsKey(pKey);
	}
	
	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aInfo.remove(pKey);
		}
		else {
			return aInfo.put(pKey, pValue);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return true if the TV show contains at least one valid episode
	 */
	@Override
	public boolean isValid() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Creates and updates a Prototype Episode for this TV show
	 * This is used to create a new Episode for the TV Show
	 * 
	 * @param pPrototype
	 *            the path of the file that contains the video of the episode
	 * @pre pPrototype != null;
	 */
	private void setPrototype(Episode pPrototype) {
		assert pPrototype != null;
		aPrototype = (Episode) pPrototype.clone();
	}
	
	/**
	 * Provides the Prototype
	 * Useful for testing whether or not the prototype exists
	 * 
	 * @pre aPrototype != null
	 * 
	 * @return aPrototype of type Episode
	 */
	public Episode getPrototype() {
		assert aPrototype != null;
		return aPrototype;
	}
	
	/**
	 * Creates a new Episode for this TV show, and adds it the end of the episode list.
	 * 
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @pre pPath != null && pTitle != null;
	 */
	public Episode createAndAddEpisode(File pPath, String pTitle) {
		assert pPath != null && pTitle != null;
		int nb = aEpisodes.size();
		if (nb == 0) {
			Episode episode = new Episode(pPath, this, pTitle, 1);
			setPrototype(episode);
			aEpisodes.add(episode);
			return episode;
		}
		Episode episode = aPrototype.clone();
		episode.setName(pTitle);
		episode.setFilePath(pPath);
		episode.setEpisodeNumber(nb+1);
		aEpisodes.add(episode);
		return episode;
		
	/**Below is template code provided in the baseline code	
	 * assert pPath != null && pTitle != null;
		int nb = aEpisodes.size()+1;
		Episode episode = new Episode(pPath, this, pTitle, nb);
		aEpisodes.add(episode);
		
	*/
	}
	
	/**
	 * Returns the Episode of a given number. Episode numbers are 1-based: the first episode is 1, not 0.
	 *
	 * @param pNumber
	 *            the number whose Episode is to be returned
	 * @return the Episode of the given number
	 * @pre there is an episode for the given number
	 */
	public Episode getEpisode(int pNumber) {
		assert aEpisodes.size() >= pNumber;
		return aEpisodes.get(pNumber - 1);
	}


	@Override
	public int getTotalCount() {
		return aEpisodes.size();
	}
	
	
}