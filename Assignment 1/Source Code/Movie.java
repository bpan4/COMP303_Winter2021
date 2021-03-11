import java.io.*;
import java.util.HashMap;

public class Movie {
	final private Format aFormat; //defines the format of the movie using Format, which is an enum type
	                              //ensures that the file is of a valid file type
	                              //this should not be changed once it has been declared hence the "final"
	final private File aPath; //stores information regarding where the file is located
							//this should not be changed once it has been declared hence the "final"
	private boolean Valid; //this represents the validity of the path provided (ie if the file exists)
							//this can change during the movie's lifetime
	final private String aTitle; //stores the name of the film
								//this should not be changed once it has been declared hence the "final"
	final private String aLanguage;//stores the language
									//this should not be changed once it has been declared hence the "final"
	final private String aPubStudio;//represents which publishing studio created the movie
									//this should not be changed once it has been declared hence the "final"
	private HashMap<String,String> aCustomInfo;//represents customizable information the client may modify for their need

	public Movie (Format pFormat, File pPath, String pTitle, String pLanguage, String pPubStudio, HashMap<String,String> pCustomInfo) {
		aFormat = pFormat;
		aPath = pPath;
		Valid = pPath.isFile(); //checks if the path given does indeed lead to an existing file
		aTitle = pTitle;
		aLanguage = pLanguage;
		aPubStudio = pPubStudio;
		aCustomInfo = pCustomInfo;
	}

	public boolean isValid() { //returns true if the path provided is valid and is indeed a file, false otherwise
		Valid = aPath.isFile();
		return Valid;
	}

	public File getPath () { //returns the path of the movie (ie where it's located and its file name)
		return aPath;
	}
	
	public String getTitle () { //returns the title of the movie
		return aTitle;
	}
	
	public String getLanguage () { //returns the language of the title
		return aLanguage;
	}
	
	public String getPubStudio () { //returns the publishing studio
		return aPubStudio;
	}
	
	public void customizeInfo (String key, String value) { //update the custom information
		aCustomInfo.put(key, value);
		System.out.println("The custom information has been updated."); //lets the client know if it was successful
	}
	
	public HashMap<String,String> getCustomInfo () { //returns a deep copy of the custom information
		HashMap<String, String> copy = new HashMap<String, String>();
	    for (HashMap.Entry<String,String> entry : aCustomInfo.entrySet()) {
	        copy.put(entry.getKey(), entry.getValue());
	    }
	    return copy; //ensures that the client may not change the info stored in the custom info outside 
	    			//of the functions we have defined for them
	}
}
