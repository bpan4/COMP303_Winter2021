import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Episode implements Watchable{
	
	//location of the episode on the file system
	private final File aPath;
	
	//official title of the episode
	private String aTitle;
	
	//language of the episode, in full text
	private String aLanguage;
	
	//studio which originally published the episode
	private String aStudio;
	
	//used to set custom information!
	private Map<String, String> aTags = new HashMap<>();
	
	//sequential number that is used to identify the episode
	private int aSeqNum;
	
	/*
	 * //Saves the preceeding episode private Optional<Episode> aPrequel =
	 * Optional.empty();
	 * 
	 * //saves the following episode private Optional<Episode> aSequel =
	 * Optional.empty();
	 */
	/**
	 * Creates an episode given the the file path, title, language, and publishing studio
	 * @throws IllegalArgumentException if the path provided does not point to a file
	 */
	public Episode(File pPath, String pTitle, String pLanguage, String pStudio, int pSeqNum) {
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aSeqNum = pSeqNum;
	}

	/**
	 * Indicates whether this Movie object represents a valid movie that can be played.
	 * 
	 * @return true if the underlying video file exists and is a file (not a directory)
	 */
	@Override
	public boolean isValid() {
		return aPath.isFile();
	}

	@Override
	public String getTitle() {
		return aTitle;
	}

	@Override
	public String getLanguage() {
		return aLanguage;
	}

	@Override
	public String getStudio() {
		return aStudio;
	}

	/**
	 * Sets the value of a custom tag.
	 *
	 * @param pKey
	 *            the key used to retrieve the tag.
	 * @param pValue
	 *            the value of the tag to insert. Use null to remove the key.
	 */
	@Override
	public void setTag(String pKey, String pValue) {
		if (pValue == null) {
			aTags.remove(pKey);
		}
		else {
			aTags.put(pKey, pValue);
		}
	}
	//change the sequential number of the episode
	public int getSeqNum () {
		return aSeqNum;
	}
		
	//change the sequential number of the episode
	public void changeSeqNum (int num) {
		aSeqNum = num;
	}
	/**
	 * Retrieves the value of a custom tag.
	 *
	 * @param pKey
	 *            the tag key, as it was inserted
	 * @return the associated value
	 */
	@Override
	public String getTag(String pKey) {
		return aTags.get(pKey);
	}
	
	/*
	 * //sets the sequel of the episode //also updates the aPrequal of the episode
	 * provided! public void setSequel(Episode pSequel) { pSequel.setPrequel(this);
	 * this.setSequel(pSequel); }
	 * 
	 * //sets the prequel of the episode //also updates the aSequal of the episode
	 * provided! public void setPrequel(Episode pPrequel) {
	 * pPrequel.setSequel(this); this.setSequel(pPrequel); }
	 * 
	 * 
	 * 
	 * //indicates whether or not the episode is followed by another episode
	 * //returns a boolean value public boolean hasNext() { if (aSequel.isPresent())
	 * { return true; } return false; }
	 * 
	 * //indicates whether or not the episode precedes another episode public
	 * boolean hasPrev() { if (aPrequel.isPresent()) { return true; } return false;
	 * }
	 * 
	 * //returns the sequel to the episode
	 * 
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public Episode next() { if (this.hasNext()) {
	 * return aSequel.get(); }
	 * System.out.println("There is no episode following this one."); return this; }
	 * 
	 * //returns the prequel to the episode
	 * 
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public Episode prev() { if (this.hasPrev()) {
	 * return aPrequel.get(); }
	 * System.out.println("There is no episode preceeding this one."); return this;
	 * }
	 */
	
}
