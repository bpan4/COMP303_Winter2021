package comp303.assignment6.robot.tests;

import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Compact;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class CompactTest {
	
	WallE robot;
	Compact compact;
	
	@BeforeEach
	void setUp() {
		robot = new WallE();
		compact = new Compact();
	}

	@Test
	void testGripperStateOfRobot() {
		try {
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			Robot.GripperState gripperState = (Robot.GripperState) privateGripperField.get(robot);
			Method protectedDoSpecificActionMethod = Compact.class.getDeclaredMethod("doSpecificAction", Robot.class);
			if (gripperState != Robot.GripperState.HOLDING_OBJECT) {
				try {
					protectedDoSpecificActionMethod.invoke(compact, robot);
					Assert.fail("Does not fail upon violation of preconditions.");
				} catch (Throwable e) {
					privateGripperField.set(robot, Robot.GripperState.HOLDING_OBJECT);
					protectedDoSpecificActionMethod.invoke(compact, robot);
					Assert.assertEquals ((Robot.GripperState) privateGripperField.get(robot), Robot.GripperState.OPEN);
				}
			}
			protectedDoSpecificActionMethod.invoke(compact, robot);
			Assert.assertEquals ((Robot.GripperState) privateGripperField.get(robot), Robot.GripperState.OPEN);
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
			Method protectedDoSpecificActionMethod = Compact.class.getDeclaredMethod("doSpecificAction", Robot.class);
			try {
				protectedDoSpecificActionMethod.invoke(compact, robot2);
				Assert.fail("Does not fail upon violation of preconditions.");
			} catch (Throwable e) {
				privateGripperField.set(robot, Robot.GripperState.HOLDING_OBJECT);
				protectedDoSpecificActionMethod.invoke(compact, robot);
				Assert.assertEquals ((Robot.GripperState) privateGripperField.get(robot), Robot.GripperState.OPEN);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	void testIfValueUpdated() {
		try {
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			Robot.GripperState gripperState = (Robot.GripperState) privateGripperField.get(robot);
			
			Field privateCompactIntField = WallE.class.getDeclaredField("compactedItems");
			privateCompactIntField.setAccessible(true);
			int compactedItems = privateCompactIntField.getInt(robot);
			
			Method protectedDoSpecificActionMethod = Compact.class.getDeclaredMethod("doSpecificAction", Robot.class);
			
			if (gripperState != Robot.GripperState.HOLDING_OBJECT) {
				try {
					protectedDoSpecificActionMethod.invoke(compact, robot);
					Assert.fail("Does not fail upon violation of preconditions.");
				} catch (Throwable e) {
					privateGripperField.set(robot, Robot.GripperState.HOLDING_OBJECT);
					protectedDoSpecificActionMethod.invoke(compact, robot);
					Assert.assertEquals (privateCompactIntField.getInt(robot), compactedItems+1);
				}
			}
			
			protectedDoSpecificActionMethod.invoke(compact, robot);
			Assert.assertEquals (privateCompactIntField.getInt(robot), compactedItems+1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

}
