package comp303.assignment6.robot;

import java.util.List;

public interface Sequenceable {
	
	//returns a copy of the actions in the object
	public List<Action> getActions();
	
	//returns the number of actions
	public int getNumberActions();
	
	//allows the client to add one action at a time to the series of actions
	public void addAction(Action pAction);
	
	//Allows the client to remove a specific action from the list
	public void removeAction(Action pAction);
	
	
//	//returns the action at the provided index
//	public Action getAction(int i);
	
//	//allows the client to add a whole list of actions at a time to the series of actions
//	public void addActions(List<Action> pAction);
//	
//	//allows the client to add one action at a time to a specific index in the series of actions
//	//all that comes after that index in the list is shifted to the right by 1
//	public void insertAction(Action pAction, int index);
//	
//	//allows the client to add many actions at a time to the series of actions
//	//inserts them to the specific index provided
//	//all that comes after that index in the list is shifted to the right
//	public void insertActions(List<Action> pAction, int index);
	

//	
//	//Allows the client to remove an action at a specific index
//	public void removeAction(int index);
}
