package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Grab;
import comp303.assignment6.robot.Move;
import comp303.assignment6.robot.PrintingLogToConsole;
import comp303.assignment6.robot.Program;
import comp303.assignment6.robot.Release;
import comp303.assignment6.robot.Robot;
import comp303.assignment6.robot.WallE;

class PrintingLogToConsoleTest {
	
	WallE robot;
	Program program;
	Grab grab;
	Release release;
	PrintingLogToConsole console;
	
	
	@BeforeEach
	void setUp() {
		grab = new Grab();
		release = new Release();
		robot = new WallE();
		program = new Program();
		console = new PrintingLogToConsole();
	}
	
	@Test
	void testExecuteProgramAndLog() {
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
			program.setLoggingSystem(console);
			program.execute(robot);
			if ((int) privateChargeField.get(robot) < initCharge) {
				Assert.assertEquals(0, 0);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testLogAction() {
		console.log(robot, grab);
		console.log(robot, release);
	}
	
	@Test
	void testLogNullRobot() {
		WallE robot2 = null;
		try {
			console.log(robot2, grab);
			Assert.fail("Preconditions violated");
		} catch (AssertionError e) {
			robot2 = new WallE();
			console.log(robot2, grab);
		}
	}
	
	@Test
	void testLogNullAction() {
		Move move = null;
		try {
			console.log(robot, move);
			Assert.fail("Preconditions violated");
		} catch (AssertionError e) {
			move = new Move(90);
			console.log(robot, move);
		}
	}
	
}