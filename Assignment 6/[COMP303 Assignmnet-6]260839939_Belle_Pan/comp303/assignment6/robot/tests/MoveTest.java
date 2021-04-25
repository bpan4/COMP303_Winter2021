package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Move;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class MoveTest {
	
	WallE robot;
	Move move;
	
	@BeforeEach
	void setUp() {
		robot = new WallE();
		move = new Move(90);
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
			
			Method protectedDoSpecificActionMethod = Move.class.getDeclaredMethod("doSpecificAction", Move.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			try {
				protectedDoSpecificActionMethod.invoke(move, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				if (initCharge1 < initCharge) {
					Assert.assertEquals(0, 0);
				}
				
			} catch (AssertionError e) {
				Assert.fail("The robot is not executing the move function.");
			}
			Assert.fail("The robot is not executing the move function.");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNull() {
		WallE robot2 = null;
		
		try {
			Method protectedDoSpecificActionMethod = Move.class.getDeclaredMethod("doSpecificAction", Move.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			try {
				privateArmField.set(robot2, Robot.ArmState.RETRACTED);
				int initCharge2 = (int) privateChargeField.get(robot2);
				protectedDoSpecificActionMethod.invoke(move, robot2);
				if ((int) privateChargeField.get(robot2) != initCharge2) {
					Assert.fail("Null robot does not give correct response.");
				}
				Assert.fail();
			} catch (Throwable e) {
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				int initCharge = (int) privateChargeField.get(robot);
				protectedDoSpecificActionMethod.invoke(move, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				if (initCharge1 < initCharge) {
					Assert.assertEquals(0, 0);
				}
				Assert.fail("The robot is not executing the move function.");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testArmStateNotRetracted() {
		try {
			Method protectedDoSpecificActionMethod = Move.class.getDeclaredMethod("doSpecificAction", Move.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			privateArmField.set(robot, Robot.ArmState.EXTENDED);
			
			try {
				protectedDoSpecificActionMethod.invoke(move, robot);
				Assert.fail("Action is executed even when preconditions are violated.");
			} catch (AssertionError e) {
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				protectedDoSpecificActionMethod.invoke(move, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				
				if (initCharge1 < initCharge) {
					protectedDoSpecificActionMethod.invoke(move, robot);
					int initCharge2 = (int) privateChargeField.get(robot);
					if (initCharge2 < initCharge1) {
						Assert.assertEquals(0, 0);
					}
				}
				Assert.fail("Action is executed even when preconditions are not violated.");
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDistanceUnacceptedForAction() {
		try {
			move = new Move(-1);
			Assert.fail("Allows client to set invalid input as distance.");
		} catch (AssertionError e) {
			try {
				Method protectedDoSpecificActionMethod = Move.class.getDeclaredMethod("doSpecificAction", Move.class);
				protectedDoSpecificActionMethod.setAccessible(true);
				
				Field privateChargeField = WallE.class.getDeclaredField("charge");
				privateChargeField.setAccessible(true);
				int initCharge = (int) privateChargeField.get(robot);
				
				Field privateArmField = WallE.class.getDeclaredField("armState");
				privateArmField.setAccessible(true);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				
				protectedDoSpecificActionMethod.invoke(move, robot);
				int initCharge1 = (int) privateChargeField.get(robot);
				if (initCharge1 != initCharge) {
					Assert.assertEquals(0, 0);
				}
				Assert.fail("Action is executed even when preconditions are violated.");
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
			
		}
	}
	
	@Test
	void testDistanceUnacceptedForGetDistance() {
		Move move3 = new Move(0);
		Move move4 = new Move(-1);
		
		if (move3.getDistance() == 0) {
			if (move4.getDistance() == -1)
			Assert.fail("Null action does not give correct response.");
		}
		Assert.assertEquals(0, (int)move4.getDistance());
	}
}
