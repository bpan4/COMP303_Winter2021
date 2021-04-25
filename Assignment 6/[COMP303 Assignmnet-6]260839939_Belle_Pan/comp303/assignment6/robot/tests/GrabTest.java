package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Grab;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class GrabTest {
	
	WallE robot;
	Grab grab;
	
	@BeforeEach
	void setUp() {
		robot = new WallE();
		grab = new Grab();
	}

	@Test
	void testStateOfRobot() {
		try {
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			Robot.GripperState gripperState = (Robot.GripperState) privateGripperField.get(robot);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			Robot.ArmState armState = (Robot.ArmState) privateArmField.get(robot);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Method protectedDoSpecificActionMethod = Grab.class.getDeclaredMethod("doSpecificAction", Grab.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			if (armState != Robot.ArmState.RETRACTED || gripperState != Robot.GripperState.OPEN) {
				try {
					protectedDoSpecificActionMethod.invoke(grab, robot);
					if ((int) privateChargeField.get(robot) != initCharge) {
						Assert.fail("Does not fail upon violation of preconditions.");
					}
				} catch (Throwable e) {
					privateArmField.set(robot, Robot.ArmState.RETRACTED);
					privateGripperField.set(robot, Robot.GripperState.OPEN);
					protectedDoSpecificActionMethod.invoke(grab, robot);
					Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
				}
			}
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			privateGripperField.set(robot, Robot.GripperState.OPEN);
			protectedDoSpecificActionMethod.invoke(grab, robot);
			Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNull() {
		WallE robot2 = null;
		
		try {
			Method protectedDoSpecificActionMethod = Grab.class.getDeclaredMethod("doSpecificAction", Grab.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot2);
			
			try {
				protectedDoSpecificActionMethod.invoke(grab, robot2);
				if ((int) privateChargeField.get(robot2) != initCharge) {
					Assert.fail("Does not fail upon violation of preconditions.");
				}
				privateGripperField.set(robot, Robot.GripperState.OPEN);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				protectedDoSpecificActionMethod.invoke(grab, robot);
				Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
			} catch (Throwable e) {
				privateGripperField.set(robot, Robot.GripperState.OPEN);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				protectedDoSpecificActionMethod.invoke(grab, robot);
				Assert.assertEquals ((Robot.ArmState) privateArmField.get(robot), Robot.ArmState.RETRACTED);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
	}
}
