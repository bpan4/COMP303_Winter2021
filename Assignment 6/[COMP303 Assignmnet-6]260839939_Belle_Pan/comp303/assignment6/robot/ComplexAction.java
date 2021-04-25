package comp303.assignment6.robot;

import java.util.ArrayList;
import java.util.List;

public class ComplexAction extends Action implements Sequenceable {

	//field to store the actions that the robot will execute in order
	private List<Action> aActions = new ArrayList<Action>();
	
	//Constructor
	public ComplexAction() {}
	
	/**
	 * Executes each action in the list one by one
	 * 
	 * @pre aActions > 0
	 * 			have to ensure that there are executable actions to begin with!
	 * 		pRobot != null
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		if (aActions.size() > 0 || pRobot == null) {
			return;
		}
		for (Action action : aActions) {
			action.execute(pRobot);
		}
	}
	
	/**
	 * returns a copy of the actions within the complex action
	 * 
	 * @pre aActions != null
	 */
	@Override
	public List<Action> getActions() {
		assert aActions != null;
		List<Action> copyOfActions = new ArrayList<Action>();
		for (Action action : aActions) {
			copyOfActions.add(action);
		}
		return copyOfActions;
	}
	
	/**
	 * returns the number of actions in the action
	 * 
	 * @pre aActions != null
	 */
	@Override
	public int getNumberActions() {
		assert aActions != null;
		return aActions.size();
	}
	
	/**
	 * allows the client to add one action at a time to the series of actions
	 * adds to the end of the list
	 * 
	 * @pre pAction != null
	 */
	@Override
	public void addAction(Action pAction) {
		assert pAction != null;
		aActions.add(pAction);
	}
	
	
	/**
	 * Allows the client to remove a specific action from the list
	 * 
	 * @pre pActions != null
	 * 		aActions.contains(pAction)
	 */
	@Override
	public void removeAction(Action pAction) {
		assert pAction != null && aActions.contains(pAction);
		aActions.remove(pAction);
	}
	
//	/**
//	 * returns the action at the provided index within the complex action
//	 * 
//	 * @pre aActions != null
//	 * 		i >= 0
//	 * 		i < aActions.size()
//	 */
//	@Override
//	public Action getAction(int i) {
//		assert i >= 0 && i < aActions.size() && aActions != null;
//		return aActions.get(i);
//	}
	
//	/**
//	 * Allows the client to add a whole list of actions at a time to the series of actions
//	 * 
//	 * @pre pActions != null
//	 */
//	@Override
//	public void addActions(List<Action> pActions) {
//		assert pActions != null;
//		aActions.addAll(pActions);
//	}
//	
//	/**
//	 * allows the client to add one action at a time to a specific index in the series of actions
//	 * all that comes after that index in the list is shifted to the right by 1
//	 * 
//	 * @pre pAction != null
//	 * 		index < aActions.size()
//	 */
//	@Override
//	public void insertAction(Action pAction, int index) {
//		assert pAction != null && index < aActions.size();
//		aActions.add(index, pAction);
//	}
//	
//	/**
//	 * allows the client to add many actions at a time to the series of actions
//	 * inserts them to the specific index provided
//	 * all that comes after that index in the list is shifted to the right
//	 * 
//	 * @pre pAction != null
//	 * 		index < aActions.size()
//	 */
//	@Override
//	public void insertActions(List<Action> pAction, int index) {
//		assert pAction != null && index < aActions.size();
//		for (int i = 0; i < pAction.size(); i++) {
//			aActions.add(index+i, pAction.get(i));
//		}
//	}

	
//	/**
//	 * Overloads the previous removeAction function
//	 * Allows the client to remove an action at a specific index from the list
//	 * 
//	 * @pre index < aActions.size()
//	 */
//	@Override
//	public void removeAction(int index) {
//		assert index < aActions.size();
//		Action actionToBeRemoved = aActions.get(index);
//		aActions.remove(actionToBeRemoved);
//	}


}
