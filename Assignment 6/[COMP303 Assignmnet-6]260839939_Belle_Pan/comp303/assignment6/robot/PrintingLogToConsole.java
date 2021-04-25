package comp303.assignment6.robot;

public class PrintingLogToConsole implements LoggingSystem {
	
	//constructor
	public PrintingLogToConsole() {}
	
	/**
	 * allows the logger to simply print the required information to the console
	 * 
	 * @pre pRobot != null
	 * 		pAction != null
	 */
	@Override
	public void log(Robot pRobot, Action pAction) {
		assert pAction != null && pRobot != null;
		if (pAction instanceof ComplexAction) {
			for (Action action : (((ComplexAction) pAction).getActions())) {
				System.out.println(action.getClass().getName() + " action performed");
			}
		}
		System.out.println(pAction.getClass().getName() + " action performed, battery level is " + pRobot.getBatteryCharge());
		
	}

}
