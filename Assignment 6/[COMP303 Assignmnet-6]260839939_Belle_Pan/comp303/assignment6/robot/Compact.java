package comp303.assignment6.robot;

public class Compact extends Action {
	
	/**
	* Constructor
	 */
	public Compact() {}

	/**
	 * Overrides a function of the same name in the abstract class Actions.
	 * Allows the robot to compact what it is currently holding
	 * 
	 * @pre pRobot != null
	 * 		pRobot.getGripperState() == Robot.GripperState.HOLDING_OBJECT
	 *      	(ensure that the gripper is indeed holding an object that it can compact)
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert pRobot != null && pRobot.getGripperState() == Robot.GripperState.HOLDING_OBJECT;
		pRobot.compact();
	}

}