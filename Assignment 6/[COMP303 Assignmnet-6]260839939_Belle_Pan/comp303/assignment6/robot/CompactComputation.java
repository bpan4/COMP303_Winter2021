package comp303.assignment6.robot;

import java.util.List;

public class CompactComputation extends ComputationTypes {
	
	public CompactComputation () {};
	/**
	 * Allows the client to filter through the actions within a program and decide if they should be included in a computation
	 * As we are looking to compute the total number of items compacted,
	 * we only want to compute the Compact actions
	 * 
	 * @return true  - if the Action should be included in the computation
	 * 			false  - if the Action should not be included in the computation
	 */
	@Override
	public boolean filter(Action pAction) {
		assert pAction != null;
		if (pAction instanceof Compact) {
			return true;
		}
		return false;
	}
	
	/**
	 * Allows the client to compute all the actions in the list
	 * This sums up all the number of items compacted by the robot and returns the result
	 * 
	 * @return total number of items compressed by a robot if the program executes
	 */
	@Override
	public double compute(List<Action> pActions) {
		if (pActions.size() == 0) {
			return 0;
		}
		double total = 0;
		for (Action action : pActions) {
			if (filter(action)) {
				total += 1;
			}
		}
		return total;
	}
}