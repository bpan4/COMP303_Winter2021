package comp303.assignment6.robot;

import java.util.List;

public class DistanceComputation extends ComputationTypes {
	
	
	/**
	 * Allows the client to filter through the actions within a program and decide if they should be included in a computation
	 * As we are looking to compute the total distance the robot moves,
	 * we only want to compute the Move actions
	 * 
	 * @pre pAction != null
	 * 
	 * @return true  - if the Action should be included in the computation
	 * 			false  - if the Action should not be included in the computation
	 */
	@Override
	public boolean filter(Action pAction) {
		assert pAction != null;
		if (pAction instanceof Move) {
			return true;
		}
		return false;
	}

	
	/**
	 * Allows the client to compute all the actions in the list
	 * This sums up all the distance covered by each action and returns the result
	 * 
	 * @pre pActions != null
	 * 
	 * @return total distance moved by a robot if the program executes
	 */
	@Override
	public double compute(List<Action> pActions) {
		if (pActions == null) {
			return 0;
		}
		double distance = 0;
		for (Action action : pActions) {
			if (filter(action)) {
				distance += ((Move) action).getDistance();
			}
		}
		return distance;
	}
	
}
