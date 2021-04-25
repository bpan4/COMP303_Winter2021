package comp303.assignment6.robot;

public class Move extends Action {
	
	//field to store the distance the robot will move forward
	private double aDistance;
	
	/**
	 * Constructor
	 * 
	 * @pre pDistance >= 0
	 */
	public Move(double pDistance) {
		assert pDistance >= 0; 
		aDistance = pDistance;
	}
	
	/**
	 * Overrides a function of the same name in the abstract class Actions.
	 * Allows the robot to move forward by the distance provided
	 * Calls on the specific moveRobot function in the robot
	 * 
	 * @pre pRobot != null
	 * 		pRobot.getArmState() == Robot.ArmState.RETRACTED
	 *            (ensure that the robot's arm is retracted)
	 *  	aDistance >= 0
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert pRobot != null && pRobot.getArmState() == Robot.ArmState.RETRACTED && aDistance >= 0;
		pRobot.moveRobot(aDistance);
	}
	
	/**
	 * Retrieves the distance that the robot will move when this action is applied
	 * 
	 * @pre pDistance >= 0
	 * @return aDistance
	 */
	public double getDistance() {
		assert aDistance >= 0;
		return aDistance;
	}
	
}
