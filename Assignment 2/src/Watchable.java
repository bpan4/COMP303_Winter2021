
public interface Watchable {
	//Watchable types include Movies and Episodes
	
	//returns the validity of the Watchable object as a boolean value
	public boolean isValid();
	
	//returns the name of the Watchable object as a String
	public String getTitle();
	
	//returns the language of the Watchable object as a String
	public String getLanguage();
	
	//returns the publishing studio of the Watchable object as a String
	public String getStudio();
	
	//returns the value associated with the key of the Watchable object
	public String getTag(String pKey);
	
	//set the aTag value using a key-value as input
	public void setTag(String pKey, String pValue);
	

}
