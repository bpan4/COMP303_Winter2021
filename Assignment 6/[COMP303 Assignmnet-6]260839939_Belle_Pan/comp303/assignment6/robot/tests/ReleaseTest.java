package comp303.assignment6.robot.tests;

import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Release;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class ReleaseTest {
	
	WallE robot;
	Release release;
	
	@BeforeEach
	void setUp() {
		robot = new WallE();
		release = new Release();
	}

	@Test
	void testStateOfRobot() {
		try {
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			Robot.GripperState gripperState = (Robot.GripperState) privateGripperField.get(robot);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			Robot.ArmState armState = (Robot.ArmState) privateArmField.get(robot);
			
			Method protectedDoSpecificActionMethod = Release.class.getDeclaredMethod("doSpecificAction", Release.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			if (gripperState == Robot.GripperState.OPEN || armState != Robot.ArmState.RETRACTED) {
				try {
					protectedDoSpecificActionMethod.invoke(release, robot);
					if ((int) privateChargeField.get(robot) != initCharge) {
						Assert.fail("Does not fail upon violation of preconditions.");
					}
				} catch (Throwable e) {
					privateGripperField.set(robot, Robot.GripperState.HOLDING_OBJECT);
					privateArmField.set(robot, Robot.ArmState.RETRACTED);
					protectedDoSpecificActionMethod.invoke(release, robot);
					Assert.assertEquals ((Robot.GripperState) privateGripperField.get(robot), Robot.GripperState.OPEN);
					Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
				}
			}
			privateGripperField.set(robot, Robot.GripperState.HOLDING_OBJECT);
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			protectedDoSpecificActionMethod.invoke(release, robot);
			Assert.assertEquals ((Robot.GripperState) privateGripperField.get(robot), Robot.GripperState.OPEN);
			Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNull() {
		WallE robot2 = null;
		
		try {
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);

			Method protectedDoSpecificActionMethod = Release.class.getDeclaredMethod("doSpecificAction", Release.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			try {
				int initCharge = (int) privateChargeField.get(robot2);
				protectedDoSpecificActionMethod.invoke(release, robot2);
				if ((int) privateChargeField.get(robot2) != initCharge) {
					Assert.fail("Does not fail upon violation of preconditions.");
				}
			} catch (Throwable e) {
				privateGripperField.set(robot, Robot.GripperState.HOLDING_OBJECT);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				protectedDoSpecificActionMethod.invoke(release, robot);
				Assert.assertEquals ((Robot.GripperState) privateGripperField.get(robot), Robot.GripperState.OPEN);
				Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
