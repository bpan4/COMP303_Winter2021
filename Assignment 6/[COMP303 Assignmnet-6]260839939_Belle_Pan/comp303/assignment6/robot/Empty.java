package comp303.assignment6.robot;

public class Empty extends Action {
	
	/**
	* Constructor
	 */
	public Empty() {}

	/**
	 * Overrides a function of the same name in the abstract class Actions.
	 * Allows the robot to empty its contents
	 * 
	 * @pre pRobot != null
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert pRobot != null;
		pRobot.emptyCompactor();
	}
	
}