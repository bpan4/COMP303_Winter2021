package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.Turn;
import comp303.assignment6.robot.WallE;

class TurnTest {
	
	WallE robot;
	Turn turn1;
	Turn turn2;
	
	@BeforeEach
	void setUp() {
		robot = new WallE();
		turn1 = new Turn(-90);
		turn2 = new Turn(90);
	}

	@Test
	void testStateOfRobot() {
		try {
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			
			Method protectedDoSpecificActionMethod = Turn.class.getDeclaredMethod("doSpecificAction", Turn.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			try {
				protectedDoSpecificActionMethod.invoke(turn1, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				if (initCharge1 < initCharge) {
					protectedDoSpecificActionMethod.invoke(turn2, robot);
					int initCharge2 = (int) privateChargeField.get(robot);
					if (initCharge2 <initCharge1) {
						Assert.assertEquals(0, 0);
					}
				}
			} catch (AssertionError e) {
				Assert.fail("The robot is not executing the turn function.");
			}
			Assert.fail("The robot is not executing the turn function.");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNull() {
		WallE robot2 = null;
		
		try {
			Method protectedDoSpecificActionMethod = Turn.class.getDeclaredMethod("doSpecificAction", Turn.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			try {
				int initCharge2 = (int) privateChargeField.get(robot2);
				privateArmField.set(robot2, Robot.ArmState.RETRACTED);
				protectedDoSpecificActionMethod.invoke(turn1, robot2);
				protectedDoSpecificActionMethod.invoke(turn2, robot2);
				if ((int) privateChargeField.get(robot2) != initCharge2) {
					Assert.fail("Violates preconditions, yet still executes.");
				}
			} catch (Throwable e) {
				protectedDoSpecificActionMethod.invoke(turn1, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				if (initCharge1 < initCharge) {
					protectedDoSpecificActionMethod.invoke(turn2, robot);
					int initCharge2 = (int) privateChargeField.get(robot);
					if (initCharge2 <initCharge1) {
						Assert.assertEquals(0, 0);
					}
				}
				Assert.fail("The robot is not executing the turn function.");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testArmStateNotRetracted() {
		
		try {
			Method protectedDoSpecificActionMethod = Turn.class.getDeclaredMethod("doSpecificAction", Turn.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			privateArmField.set(robot, Robot.ArmState.EXTENDED);
			
			try {
				protectedDoSpecificActionMethod.invoke(turn1, robot);
				protectedDoSpecificActionMethod.invoke(turn2, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				
				if (initCharge1 != initCharge) {
					Assert.fail();
				}
					
			} catch (AssertionError e) {
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				protectedDoSpecificActionMethod.invoke(turn1, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				protectedDoSpecificActionMethod.invoke(turn2, robot);
				int initCharge2 = (int) privateChargeField.get(robot);
				if (initCharge1 != initCharge2) {
					Assert.assertEquals(0, 0);
				}
				
			}
			Assert.fail("Action is executed even when preconditions are violated.");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	void testIfAngleUnaccepted() {
		try {
			Turn turn3 = new Turn(0);
			Turn turn4 = new Turn(-1);
			Assert.fail("Null action does not give correct response.");
		} catch (AssertionError e) {
			Assert.assertEquals(-90, (int)turn1.getAngle());
			Assert.assertEquals(90, (int)turn2.getAngle());
		}
	}
}
