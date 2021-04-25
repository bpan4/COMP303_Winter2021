package comp303.assignment6.robot.tests;

import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Empty;
import comp303.assignment6.robot.WallE;

class EmptyTest {
	
	WallE robot;
	Empty empty;
	
	@BeforeEach
	void setUp() {
		robot = new WallE();
		empty = new Empty();
	}
	
	@Test
	void testIfRobotNull() {
		WallE robot2 = null;
		
		try {
			Field privateCompactorField = WallE.class.getDeclaredField("compactedItems");
			privateCompactorField.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			
			Method protectedDoSpecificActionMethod = Empty.class.getDeclaredMethod("doSpecificAction", Empty.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			int initChargeRobot2 = (int) privateChargeField.get(robot2);
			
			try {
				protectedDoSpecificActionMethod.invoke(empty, robot2);
				if ((int) privateChargeField.get(robot2) != initChargeRobot2) {
					Assert.fail("Does not fail upon violation of preconditions.");
				}
			} catch (Throwable e) {
				privateCompactorField.set(robot, 1);
				protectedDoSpecificActionMethod.invoke(empty, robot);
				Assert.assertEquals ((int) privateCompactorField.get(robot), 0);
			}
			privateCompactorField.set(robot, 1);
			protectedDoSpecificActionMethod.invoke(empty, robot);
			Assert.assertEquals ((int) privateCompactorField.get(robot), 0);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfValueUpdated() {
		try {
			Field privateCompactorField = WallE.class.getDeclaredField("compactedItems");
			privateCompactorField.setAccessible(true);
			
			Method protectedDoSpecificActionMethod = Empty.class.getDeclaredMethod("doSpecificAction", Empty.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			privateCompactorField.set(robot, 1);
			protectedDoSpecificActionMethod.invoke(empty, robot);
			Assert.assertEquals (0, (int) privateCompactorField.get(robot));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

}
