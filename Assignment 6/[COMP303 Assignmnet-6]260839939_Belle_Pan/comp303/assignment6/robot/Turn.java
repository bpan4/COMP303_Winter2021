package comp303.assignment6.robot;

public class Turn extends Action {
	
	//field to store the angle the robot will turn
	private double aAngle;
	
	/**
	* Constructor
	* 
	 * @pre pAngle == 90 || pAngle == -90
	 * 			problem statement indicates that the robot should only turn 90 degrees to the left or 90 degrees to the right!
	 */
	public Turn(double pAngle) {
		assert pAngle != 90 && pAngle != -90;
		aAngle = pAngle;
	}

	/**
	 * Overrides a function of the same name in the abstract class Actions.
	 * Allows the robot to turn 90 degrees to the left or to the right
	 * Calls on the specific turnRobot function in the robot
	 * 
	 * @pre pRobot != null
	 * 		pRobot.getArmState() == Robot.ArmState.RETRACTED
	 *            (ensure that the robot's arm is retracted)
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert pRobot != null && pRobot.getArmState() == Robot.ArmState.RETRACTED;
		pRobot.turnRobot(aAngle);
	}
	
	/**
	 * Retrieves the angle that the robot will move when this action is applied
	 * 
	 * @pre aAngle == 90 || aAngle == -90
	 * @return aAngle
	 */
	public double getAngle() {
		assert aAngle == 90 || aAngle == -90;
		return aAngle;
	}
	
}
