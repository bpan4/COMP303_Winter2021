package comp303.assignment6.robot;

/**
 * Interface for manipulating the motors of a trash cleaning robot.
 */
public interface Robot {
	
	/**
	 * Turns the robot clockwise by some angle.
	 * 
	 * @param pDegrees
	 *            the angle in degrees of the rotation. Negative angles turn the robot anticlockwise.
	 * @pre arm is retracted
	 */
	public void turnRobot(double pDegrees);
	
	/**
	 * Moves the robot forward.
	 * 
	 * @param pDistance
	 *            the distance to travel, in meters.
	 * @pre pDistance >= 0
	 * @pre arm is retracted
	 */
	public void moveRobot(double pDistance);
	
	/**
	 * Opens the gripper. It should not be on the ground (i.e., the arm should not be extended) to avoid damage.
	 * 
	 * @pre gripper isn't already open
	 * @pre arm is retracted
	 */
	public void openGripper();
	
	/**
	 * Closes the gripper.
	 * 
	 * @pre gripper isn't already closed
	 */
	public void closeGripper();
	
	/**
	 * @return the state of the gripper
	 */
	public GripperState getGripperState();
	
	/**
	 * Extends the arm to reach the ground.
	 * 
	 * @pre arm isn't already extended
	 */
	public void extendArm();
	
	/**
	 * Retracts the arm close to the body, allowing the robot to move without damaging the arm.
	 * 
	 * @pre arm isn't already retracted
	 */
	public void retractArm();
	
	/**
	 * @return the position of the arm
	 */
	public ArmState getArmState();
	
	/**
	 * Puts the object held by the gripper in the compactor and compact it. The gripper will remain open after this
	 * operation. The compactor can only contain up to 10 objects before it needs to be emptied.
	 * 
	 * @pre gripper holds an object
	 * @pre compactor contains less than 10 objects
	 * @post gripper is open
	 */
	public void compact();
	
	/**
	 * Empties the compactor.
	 * 
	 * @pre compactor isn't already empty
	 */
	public void emptyCompactor();
	
	/**
	 * @return number of objects in the compactor
	 */
	public int getCompactorLevel();
	
	/**
	 * Recharges the battery to full capacity (100 units). It will also automatically update the charge level once
	 * finished.
	 */
	public void rechargeBattery();
	
	/**
	 * Refreshes the information about the battery charge. This is not automatically done for very important reasons.
	 */
	public void updateBatteryLevel();
	
	/**
	 * Returns the last charge level of the battery computed by {@link #updateBatteryLevel()} or by
	 * {@link #rechargeBattery}.
	 * 
	 * @return the last value of the charge of the battery
	 */
	public int getBatteryCharge();
	
	public static enum GripperState {
		OPEN,
		EMPTY,
		HOLDING_OBJECT
	}
	
	public static enum ArmState {
		EXTENDED,
		RETRACTED
	}
}
