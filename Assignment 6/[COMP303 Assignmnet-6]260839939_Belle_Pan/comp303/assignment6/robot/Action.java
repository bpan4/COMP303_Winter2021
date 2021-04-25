package comp303.assignment6.robot;

public abstract class Action {
/**
 * abstraction for the actions that are used to manipulate the robot.
 */
	
	/**
	 * execute() provides the preliminary actions that must be taken before the execution of an action
	 * The initialization of all actions require the follow steps:
	 * 1. First check the state of the battery
	 * 2. If the charge of the battery is <= 5 units, then recharge the battery
	 * 3. Perform the action and update the battery level
	 * 
	 * @pre pRobot != null
	 */
	protected void execute(Robot pRobot) {
		assert pRobot != null;
		if (pRobot.getBatteryCharge() <= 5) {
			pRobot.rechargeBattery();
		}
		this.doSpecificAction(pRobot);
		pRobot.updateBatteryLevel();
	}
	
	/**
	 * actually executes the action
	 * this is abstract because it varies between different actions!
	 * The actions will have to implement this separately as there is no code overlap
	 */
	protected abstract void doSpecificAction(Robot pRobot);

}
