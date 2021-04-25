package comp303.assignment6.robot;

public class Grab extends Action {
	
	/**
	* Constructor
	 */
	public Grab() {}

	/**
	 * Overrides a function of the same name in the abstract class Actions.
	 * Allows the robot to grab an object
	 * 
	 * @pre pRobot != null
	 * 		pRobot.getArmState() == Robot.ArmState.RETRACTED
	 *            (ensure that the robot's arm is retracted)
	 *      pRobot.getGripperState() == Robot.GripperState.OPEN
	 *      	(ensure that the gripper is already open)
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert pRobot != null && pRobot.getArmState() == Robot.ArmState.RETRACTED && pRobot.getGripperState() == Robot.GripperState.OPEN;
		pRobot.extendArm();
		pRobot.closeGripper();
		pRobot.retractArm();
	}
}