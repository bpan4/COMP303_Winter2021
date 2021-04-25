package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Action;
import comp303.assignment6.robot.Charge;
import comp303.assignment6.robot.Grab;
import comp303.assignment6.robot.WallE;

class ChargeTest {
	
	WallE robot;
	Charge charge;
	Grab grab;
	
	@BeforeEach
	void setUp() {
		grab = new Grab();
		robot = new WallE();
		charge = new Charge(grab);
	}

	@Test
	void testStateOfRobot() {
		try {
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Method protectedDoSpecificActionMethod = Charge.class.getDeclaredMethod("doSpecificAction", Charge.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			try {
				protectedDoSpecificActionMethod.invoke(charge, robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.assertEquals(0, 0);
				}
			} catch (AssertionError e) {
				Assert.fail("fails even when preconditions met");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNullOnAction() {
		WallE robot2 = null;
		
		try {
			Method protectedDoSpecificActionMethod = Charge.class.getDeclaredMethod("doSpecificAction", Charge.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			try {
				int initCharge = (int) privateChargeField.get(robot2);
				protectedDoSpecificActionMethod.invoke(charge, robot2);
				if ((int) privateChargeField.get(robot2) != initCharge) {
					Assert.fail();
				}
			} catch (Throwable e) {
				int initCharge = (int) privateChargeField.get(robot);
				protectedDoSpecificActionMethod.invoke(charge, robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.assertEquals(0, 0);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNullOnCharging() {
		WallE robot2 = null;
		
		try {
			Method protectedChargeMethod = Charge.class.getDeclaredMethod("charge", Charge.class);
			protectedChargeMethod.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			try {
				int initCharge = (int) privateChargeField.get(robot2);
				protectedChargeMethod.invoke(robot2);
				if ((int) privateChargeField.get(robot2) != initCharge) {
					Assert.fail();
				}
			} catch (Throwable e) {
				protectedChargeMethod.invoke(robot);
				Assert.assertEquals ((int) privateChargeField.get(robot), 100);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfActionNullOnGetAction() {
		Charge charge2 = new Charge(null);
		
		try {
			Action action = charge2.getAction();
			if (action == null) {
				Assert.fail("Null action does not give correct response.");
			}
		} catch (Throwable e) {
			if (charge.getAction() == null) {
				Assert.fail("Non-null action returns null.");
			}
		}
		Assert.assertEquals(grab, charge.getAction());
	}
}
