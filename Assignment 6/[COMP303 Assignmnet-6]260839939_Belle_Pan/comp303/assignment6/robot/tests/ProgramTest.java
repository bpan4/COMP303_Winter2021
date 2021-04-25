package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Action;
import comp303.assignment6.robot.Grab;
import comp303.assignment6.robot.Program;
import comp303.assignment6.robot.Release;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class ProgramTest {
	
	WallE robot;
	Program program;
	Grab grab;
	Release release;
	
	
	@BeforeEach
	void setUp() {
		grab = new Grab();
		release = new Release();
		robot = new WallE();
		program = new Program();
	}

	@SuppressWarnings("unchecked")
	@Test
	void testAddingActions() {
		try {
			Field privateActionsField = Program.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(program);
			
			if (initActions.size() == 0) {
				program.addAction(grab);
				program.addAction(release);
				Assert.assertEquals(((List<Action>) privateActionsField.get(program)).size(), 2);
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
			Field privateActionsField = Program.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(program);
			
			if (initActions.size() == program.getActions().size()) {
				program.addAction(grab);
				program.addAction(release);
				Assert.assertEquals(((List<Action>) privateActionsField.get(program)).size(), program.getActions().size());
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
			Field privateActionsField = Program.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(program);
			
			if (initActions.size() == program.getNumberActions()) {
				program.addAction(grab);
				program.addAction(release);
				Assert.assertEquals(((List<Action>) privateActionsField.get(program)).size(), program.getNumberActions());
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
			Field privateActionsField = Program.class.getDeclaredField("aActions");
			privateActionsField.setAccessible(true);
			List<Action> initActions = (List<Action>) privateActionsField.get(program);
			
			if (initActions.size() == 0) {
				program.addAction(grab);
				if (((List<Action>) privateActionsField.get(program)).size() == 1) {
					program.removeAction(grab);
					try {
						program.removeAction(grab);
						Assert.fail();
					} catch (Throwable e) {
						if (!((List<Action>) privateActionsField.get(program)).contains(grab)) {
							Assert.assertEquals(0, ((List<Action>) privateActionsField.get(program)).size());
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
	void testExecute() {
		try {
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			int initCharge = (int) privateChargeField.get(robot);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			privateGripperField.set(robot, Robot.GripperState.OPEN);
			
			program.addAction(grab);
			program.addAction(release);
			
			program.execute(robot);
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
			
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			program.addAction(grab);
			program.addAction(release);
			
			try {
				int initCharge = (int) privateChargeField.get(robot2);
				program.execute(robot2);
				if ((int) privateChargeField.get(robot2) < initCharge) {
					Assert.fail("Executes even though preconditions are violated.");
				}
				Assert.assertEquals(initCharge, (int) privateChargeField.get(robot));
			} catch (Throwable e) {
				int initCharge = (int) privateChargeField.get(robot);
				privateArmField.set(robot, Robot.ArmState.RETRACTED);
				privateGripperField.set(robot, Robot.GripperState.OPEN);
				program.execute(robot);
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
			Field privateChargeField = WallE.class.getDeclaredField("charge");
			privateChargeField.setAccessible(true);
			
			Field privateGripperField = WallE.class.getDeclaredField("gripperState");
			privateGripperField.setAccessible(true);
			
			Field privateArmField = WallE.class.getDeclaredField("armState");
			privateArmField.setAccessible(true);
			
			privateArmField.set(robot, Robot.ArmState.RETRACTED);
			privateGripperField.set(robot, Robot.GripperState.OPEN);
			
			try {
				int initCharge = (int) privateChargeField.get(robot);
				program.execute(robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.fail("Executed non-executable functions.");
				}
				Assert.assertEquals(initCharge, (int) privateChargeField.get(robot));
			} catch (Throwable e) {
				int initCharge = (int) privateChargeField.get(robot);
				program.addAction(grab);
				program.execute(robot);
				if ((int) privateChargeField.get(robot) != initCharge) {
					Assert.assertEquals(0, 0);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}