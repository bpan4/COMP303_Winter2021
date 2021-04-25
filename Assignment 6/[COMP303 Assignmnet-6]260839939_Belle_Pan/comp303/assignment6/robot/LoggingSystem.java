package comp303.assignment6.robot;

public interface LoggingSystem {
	
	//allows different logging systems to implement their own needs for logging
	public void log(Robot pRobot,  Action pAction);
}
