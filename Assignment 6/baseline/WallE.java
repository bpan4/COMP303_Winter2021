package comp303.assignment6.robot;

import java.util.Random;

public class WallE implements Robot {
	
	private final Random random = new Random();
	private int charge = 100;
	private GripperState gripperState = GripperState.EMPTY;
	private ArmState armState = ArmState.RETRACTED;
	private int compactedItems = 0;
	
	@Override
	public void turnRobot(double pDegrees) {
		assert armState == ArmState.RETRACTED;
		System.out.println("Turn");
	}
	
	@Override
	public void moveRobot(double pDistance) {
		assert armState == ArmState.RETRACTED;
		System.out.println("Forward");
	}
	
	@Override
	public void openGripper() {
		assert gripperState != GripperState.OPEN && armState == ArmState.RETRACTED;
		System.out.println("Open gripper");
		gripperState = GripperState.OPEN;
	}
	
	@Override
	public void closeGripper() {
		assert gripperState == GripperState.OPEN;
		System.out.println("Close gripper");
		switch (armState) {
			case EXTENDED:
				gripperState = GripperState.HOLDING_OBJECT;
				break;
			case RETRACTED:
				gripperState = GripperState.EMPTY;
				break;
			default:
				assert false;
				break;
		}
	}
	
	@Override
	public GripperState getGripperState() {
		return gripperState;
	}
	
	@Override
	public void extendArm() {
		assert armState == ArmState.RETRACTED;
		System.out.println("Extend arm");
		armState = ArmState.EXTENDED;
	}
	
	@Override
	public void retractArm() {
		assert armState == ArmState.EXTENDED;
		System.out.println("Retract arm");
		armState = ArmState.RETRACTED;
	}
	
	@Override
	public ArmState getArmState() {
		return armState;
	}
	
	@Override
	public void compact() {
		assert compactedItems < 10 && gripperState == GripperState.HOLDING_OBJECT;
		System.out.println("Compact");
		compactedItems++;
		gripperState = GripperState.OPEN;
	}
	
	@Override
	public void emptyCompactor() {
		assert compactedItems > 0;
		System.out.println("Empty compactor");
		compactedItems = 0;
	}
	
	@Override
	public int getCompactorLevel() {
		return compactedItems;
	}
	
	@Override
	public void rechargeBattery() {
		System.out.println("Recharge");
		charge = 100;
	}
	
	@Override
	public void updateBatteryLevel() {
		charge -= random.nextInt(5) + 1;
	}
	
	@Override
	public int getBatteryCharge() {
		return charge;
	}
}
