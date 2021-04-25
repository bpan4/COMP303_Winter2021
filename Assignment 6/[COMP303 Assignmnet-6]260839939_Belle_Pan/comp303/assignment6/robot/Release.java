package comp303.assignment6.robot;

public class Release extends Action {
	
	/**
	* Constructor
	 */
	public Release() {}

	/**
	 * Overrides a function of the same name in the abstract class Actions.
	 * Allows the robot to release what it is currently holding
	 * 
	 * @pre pRobot!= null
	 * 		pRobot.getArmState() == Robot.ArmState.RETRACTED
	 *            (ensure that the robot's arm is retracted)
	 *      pRobot.getGripperState() != Robot.GripperState.OPEN
	 *      	(ensure that the gripper is not already open and released its object)
	 * @post pRobot.getGripperState() = OPEN
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert pRobot != null && pRobot.getArmState() == Robot.ArmState.RETRACTED && pRobot.getGripperState() != Robot.GripperState.OPEN;
		pRobot.openGripper();
	}
	
}