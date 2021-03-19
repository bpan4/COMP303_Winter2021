
public interface Sequential<Watchable> {
	//indicates whether or not the object has a sequel by returning a boolean value
		public boolean hasNext();
		
		//indicates whether or not the object is a sequel of another object by returning a boolean value
		public boolean hasPrev();
		
		//returns the sequel to the object
		public Watchable next();
		
		//returns the prequel to the object
		public Watchable prev();
		
		/*
		 * //allows you to set up the relations between Sequential objects public void
		 * setPrequel(Watchable pPrequel); public void setSequel(Watchable pSequel);
		 */
}
