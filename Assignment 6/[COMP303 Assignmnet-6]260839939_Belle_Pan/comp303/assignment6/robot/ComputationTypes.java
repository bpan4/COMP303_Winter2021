package comp303.assignment6.robot;

import java.util.ArrayList;
import java.util.List;

public abstract class ComputationTypes {
	
//	Action aAction;
	
	/**
	 * Allows the client to filter through the actions within a program and decide if they should be included in a computation
	 * 
	 * @return true  - if the Action should be included in the computation
	 * 			false  - if the Action should not be included in the computation
	 */
	public abstract boolean filter(Action pAction);
//		assert pAction != null;
//		if (pAction.getClass().equals(aAction.getClass())) {
//			return true;
//		}
//		return false;
//	}
	
	
	/**
	 * Allows the client to compute all the actions in the list
	 * 
	 * @return the computation results
	 */
	public abstract double compute(List<Action> pActions);
	
	
	/**
	 * Allows the client to compute all the actions in a program
	 * This sums up all the number of items compacted by the robot and returns the result
	 * 
	 * @pre pProgram != null
	 * 
	 * @return total number of items compressed by a robot if the program executes
	 */
	public double compute(Program pProgram) {
		assert pProgram != null;
		List<Action> actions = pProgram.getActions();
		List<Action> actionsToCompute = new ArrayList<Action>();
		for (Action action : actions) {
			if (action instanceof ComplexAction) {
				for (Action complexAction : ((ComplexAction) action).getActions()) {
					if (filter(complexAction)) {
						actionsToCompute.add(complexAction);
					}
				}
			}
			else if (action instanceof Charge) {
				if (filter(((Charge) action).getAction())) {
					actionsToCompute.add(action);
				}
			}
			else if (filter(action)) {
				actionsToCompute.add(action);
			}
		}
		return compute(actionsToCompute);
	}
}
