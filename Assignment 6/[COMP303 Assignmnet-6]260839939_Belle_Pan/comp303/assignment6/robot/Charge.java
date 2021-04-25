package comp303.assignment6.robot;

public class Charge extends Action {
	
	//field that holds the action that we want to charge the battery before executing
	private Action aAction;
	
	/**
	* Constructor
	* 
	 *  @pre pAction != null;
	 */
	public Charge(Action pAction) {
		assert pAction != null;
		aAction = pAction;
	}

	/**
	 * Charges the battery of the robot and then executes the action
	 * 
	 * @pre pAction != null;
	 * 		pRobot != null;
	 */
	@Override
	protected void doSpecificAction(Robot pRobot) {
		assert aAction != null && pRobot != null;
		charge(pRobot);
		aAction.execute(pRobot);
	}
	
	/**
	 * helper function used to recharge the robot's battery
	 * 
	 * @pre pRobot != null
	 */
	private void charge(Robot pRobot) {
		assert pRobot != null;
		pRobot.rechargeBattery();
	}
	
	/**
	 * returns the action associated with the charging action
	 * 
	 * @pre aAction != null
	 * @return aAction
	 */
	public Action getAction() {
		assert aAction != null;
		return aAction;
	}
	
}