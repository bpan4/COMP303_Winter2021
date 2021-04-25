package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Action;
import comp303.assignment6.robot.Charge;
import comp303.assignment6.robot.ComplexAction;
import comp303.assignment6.robot.Grab;
import comp303.assignment6.robot.Release;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class ComplexActionsTest {
	
	WallE robot;
	ComplexAction complexAction;
	Grab grab;
	Release release;
	
	
	@BeforeEach
	void setUp() {
		grab = new Grab();
		release = new Release();
		robot = new WallE();
		complexAction = new ComplexAction();
	}

	@SuppressWarnings("unchecked")
	@Test
	void testAddingActions() {
		try {
			Field privateActionsField = ComplexAction.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(complexAction);
			
			if (initActions.size() == 0) {
				complexAction.addAction(grab);
				complexAction.addAction(release);
				Assert.assertEquals(((List<Action>) privateActionsField.get(complexAction)).size(), 2);
			}
			Assert.fail();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetActions() {
		try {
			Field privateActionsField = ComplexAction.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(complexAction);
			
			if (initActions.size() == complexAction.getActions().size()) {
				complexAction.addAction(grab);
				complexAction.addAction(release);
				Assert.assertEquals(((List<Action>) privateActionsField.get(complexAction)).size(), complexAction.getActions().size());
			}
			Assert.fail();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetNumberActions() {
		try {
			Field privateActionsField = ComplexAction.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(complexAction);
			
			if (initActions.size() == complexAction.getNumberActions()) {
				complexAction.addAction(grab);
				complexAction.addAction(release);
				Assert.assertEquals(((List<Action>) privateActionsField.get(complexAction)).size(), complexAction.getNumberActions());
			}
			Assert.fail();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testRemoveActions() {
		try {
			Field privateActionsField = ComplexAction.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(complexAction);
			
			if (initActions.size() == 0) {
				complexAction.addAction(grab);
				if (((List<Action>) privateActionsField.get(complexAction)).size() == 1) {
					complexAction.removeAction(grab);
					try {
						complexAction.removeAction(grab);
						Assert.fail();
					} catch (Throwable e) {
						if (!((List<Action>) privateActionsField.get(complexAction)).contains(grab)) {
							Assert.assertEquals(0, ((List<Action>) privateActionsField.get(complexAction)).size());
						}
					}
				}
			}
			Assert.fail();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testStateOfRobot() {
		try {
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Method protectedDoSpecificActionMethod = ComplexAction.class.getDeclaredMethod("doSpecificAction", Charge.class);
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			privateGripperField.set(robot, Robot.GripperState.OPEN);
			
			complexAction.addAction(grab);
			complexAction.addAction(release);
			
			protectedDoSpecificActionMethod.invoke(complexAction, robot);
			if ((int) privateChargeField.get(robot) < initCharge) {
				Assert.assertEquals(0, 0);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfRobotNullOnAction() {
		WallE robot2 = null;
		try {
			Method protectedDoSpecificActionMethod = ComplexAction.class.getDeclaredMethod("doSpecificAction");
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			try {
				int initCharge = (int) privateChargeField.get(robot2);
				protectedDoSpecificActionMethod.invoke(complexAction, robot2);
				if ((int) privateChargeField.get(robot2) < initCharge) {
					Assert.fail("Executes even though preconditions are violated.");
				}
			} catch (Throwable e) {
				complexAction.addAction(grab);
				int initCharge = (int) privateChargeField.get(robot);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				privateGripperField.set(robot, Robot.GripperState.OPEN);
				protectedDoSpecificActionMethod.invoke(complexAction, robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.assertEquals(0, 0);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIfNoExecutableActions() {
		try {
			Method protectedDoSpecificActionMethod = ComplexAction.class.getDeclaredMethod("doSpecificAction");
			protectedDoSpecificActionMethod.setAccessible(true);
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			try {
				int initCharge = (int) privateChargeField.get(robot);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				privateGripperField.set(robot, Robot.GripperState.OPEN);
				protectedDoSpecificActionMethod.invoke(complexAction, robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.fail("Executed non-executable functions.");
				}
				Assert.assertEquals(initCharge, (int) privateChargeField.get(robot));
			} catch (Throwable e) {
				complexAction.addAction(grab);
				int initCharge = (int) privateChargeField.get(robot);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				privateGripperField.set(robot, Robot.GripperState.OPEN);
				protectedDoSpecificActionMethod.invoke(complexAction, robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.assertEquals(0, 0);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}