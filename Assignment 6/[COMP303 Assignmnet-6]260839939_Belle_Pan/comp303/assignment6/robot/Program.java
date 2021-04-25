package comp303.assignment6.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Program implements Sequenceable {
	
	//list that keeps track of all the actions in the program
	private List<Action> aActions = new ArrayList<Action>();
	
	//the logging system that's being used
	private Optional<LoggingSystem> aLoggingSystem = Optional.empty();
	
	//constructor
	public Program() {}
	
	
	/**
	 * Executes each action in the list one by one
	 * 
	 * @param Robot pRobot
	 * 				the robot on which the program will be executed on
	 * 
	 * @pre aActions != null
	 * 			have to ensure that there are executable actions to begin with!
	 * 		pRobot != null
	 */
	public void execute(Robot pRobot) {
		assert aActions != null && pRobot != null;
		for (int i = 0; i < aActions.size(); i++) {
			Action action = aActions.get(i);
			try {
				action.execute(pRobot);
				logAction(pRobot, action);
			} catch (Throwable e) {
				return;
			}
		}
	}
	
	/**
	 * passes the action and the robot to the logging system for logging
	 * \if the logging system has not been specified, then there is no need to log anything
	 */
	protected void logAction(Robot pRobot, Action pAction) {
		if (pRobot == null || pAction == null || aLoggingSystem.equals(Optional.empty())) {
			return;
		}
		aLoggingSystem.get().log(pRobot, pAction);
	}
	
	/**
	 * sets the logging system
	 * 
	 * @pre pLoggingSystem != null
	 */
	public void setLoggingSystem(LoggingSystem pLoggingSystem) {
		assert pLoggingSystem != null;
		aLoggingSystem = Optional.of(pLoggingSystem);
	}

	
	/**
	 * returns a copy of the actions within the program
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
	 * returns the number of actions in the program
	 * 
	 * @pre aActions != null
	 * 
	 * @return the number of elements in the list
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
	 * @param Action pAction
	 * 				the action to be added
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
	 * @param Action pAction
	 * 				the action that the client wants to remove from the program
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
//	 * Returns a value given a computational type 
//	 * for example, the computation we want to execute 
//	 * is one that finds the total distance the robot would move if the program were executed
//	 * 
//	 * @param ComputationTypes computation
//	 * 				specifies the type of computation we want to make
//	 * @pre computation != null
//	 * 		aActions != null
//	 * 
//	 * @return the result of the computation
//	 */
//	public double compute(ComputationTypes computation) {
//		assert computation != null && aActions != null;
//		List<Action> actionsToCompute = new ArrayList<Action>();
//		for (Action action : aActions) {
//			if (action instanceof ComplexAction) {
//				for (Action complexAction : ((ComplexAction) action).getActions()) {
//					if (computation.filter(complexAction)) {
//						actionsToCompute.add(complexAction);
//					}
//				}
//			}
//			else if (action instanceof Charge) {
//				if (computation.filter(((Charge) action).getAction())) {
//					actionsToCompute.add(action);
//				}
//			}
//			else if (computation.filter(action)) {
//				actionsToCompute.add(action);
//			}
//		}
//		return computation.compute(actionsToCompute);
//	}
	
//	/**
//	 * Allows the client to add a whole list of actions at a time to the series of actions
//	 * 
//	 * @param List<Action> pActions
//	 * 				the actions to add to the list
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
//	 * @param Action pAction
//	 * 				the action to insert into the list
//	 * 		int index
//	 * 				the index at which we want to insert the action
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
//	 * @param List<Action> pAction
//	 * 				the list of actions we want to insert into the list
//	 * 		int index
//	 * 				the index at which we want to insert the actions
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
	

//	
//	/**
//	 * returns the action at the provided index within the program
//	 * 
//	 * @param int i
//	 * 				the index at which we want to retrieve an action
//	 * 
//	 * @pre aActions != null
//	 * 		i >= 0
//	 * 		i < aActions.size()
//	 * 
//	 * @return the action at index i
//	 */
//	@Override
//	public Action getAction(int i) {
//		assert i >= 0 && i < aActions.size() && aActions != null;
//		return aActions.get(i);
//	}
	
//	/**
//	 * Overloads the previous removeAction function
//	 * Allows the client to remove an action at a specific index from the list
//	 * 
//	 * @param int index
//	 * 				the index at which we want to remove an action
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