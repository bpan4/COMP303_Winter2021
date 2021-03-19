import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a single movie, with at least a title, language, and publishing studio. Each movie is identified by its
 * path on the file system.
 */
public class Movie implements Watchable{

	private final File aPath;

	private String aTitle;
	private String aLanguage;
	private String aStudio;
	
	/*
	 * //Saves the preceeding movie private Optional<Movie> aPrequel =
	 * Optional.empty();
	 * 
	 * //saves the following movie private Optional<Movie> aSequel =
	 * Optional.empty();
	 */

	private Map<String, String> aTags = new HashMap<>();

	/**
	 * Creates a movie from the file path. Callers must also provide required metadata about the movie.
	 *
	 * @param pPath
	 *            location of the movie on the file system.
	 * @param pTitle
	 *            official title of the movie in its original language
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	public Movie(File pPath, String pTitle, String pLanguage, String pStudio) {
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
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
	 * //sets the sequel of the movie //also updates the aPrequal of the movie
	 * provided! public void setSequel(Movie pSequel) { pSequel.setPrequel(this);
	 * this.setSequel(pSequel); }
	 * 
	 * //sets the prequel of the movie //also updates the aSequal of the movie
	 * provided! public void setPrequel(Movie pPrequel) { pPrequel.setSequel(this);
	 * this.setSequel(pPrequel); }
	 * 
	 * //indicates whether or not the movie has a sequel //returns a boolean value
	 * public boolean hasNext() { if (aSequel.isPresent()) { return true; } return
	 * false; }
	 * 
	 * //indicates whether or not the movie precedes another movie public boolean
	 * hasPrev() { if (aPrequel.isPresent()) { return true; } return false; }
	 * 
	 * //returns the sequel to the movie
	 * 
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public Movie next() { if (this.hasNext()) {
	 * return aSequel.get(); }
	 * System.out.println("There is no sequel to this movie."); return null; }
	 * 
	 * //returns the prequel to the movie
	 * 
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public Movie prev() { if (this.hasPrev()) {
	 * return aPrequel.get(); }
	 * System.out.println("There is no prequel to this movie."); return null; }
	 */
	
}
