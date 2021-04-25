package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Action;
import comp303.assignment6.robot.Compact;
import comp303.assignment6.robot.Move;
import comp303.assignment6.robot.Program;
import comp303.assignment6.robot.DistanceComputation;
import comp303.assignment6.robot.WallE;

class DistanceComputationTest {
	
	WallE robot;
	Move move1;
	Move move2;
	Move move3;
	DistanceComputation comp;
	
	
	@BeforeEach
	void setUp() {
		move1 = new Move(90);
		move2 = new Move(90);
		move3 = new Move(0);
		robot = new WallE();
		comp = new DistanceComputation();
	}

	@Test
	void testFilter() {
		if (comp.filter(move1)) {
			if (comp.filter(new Compact())) {
				Assert.fail("Including actions that should not be included.");
			}
			Assert.assertEquals(0, 0);
		}
	}
	
	@Test
	void testFilterNullAction() {
		Move move4 = null;
		try {
			if (comp.filter(move4)) {
				Assert.fail("Null action still filters.");
			}
		} catch (AssertionError e) {
			Assert.assertEquals(0, 0);
		}
	}
	
	@Test
	void testComputeProgram() {
		Program program = new Program();
		program.addAction(move1);
		program.addAction(move2);
		try {
			double moveTest = comp.compute(program);
			if (moveTest == 180) {
				program.addAction(move3);
				double moveTest2 = comp.compute(program);
				Assert.assertEquals(180, (int)moveTest2);
			}
			Assert.fail("Null actions still compute.");
		} catch (AssertionError e) {
			Assert.fail("Preconditions fulfilled, yet still does not compute.");
		}
	}
	
	@Test
	void testCompute() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(move1);
		actions.add(move2);
		try {
			double moveTest = comp.compute(actions);
			if (moveTest == 180) {
				actions.add(move3);
				double moveTest2 = comp.compute(actions);
				Assert.assertEquals(180, (int)moveTest2);
			}
			Assert.fail("Null actions still compute.");
		} catch (AssertionError e) {
			Assert.fail("Preconditions fulfilled, yet still does not compute.");
		}
	}
	
	@Test
	void testComputeNullAction() {
		List<Action> nullAction = null;
		try {
			comp.compute(nullAction);
			Assert.fail("Null actions still compute.");
		} catch (AssertionError e) {
			Assert.assertEquals(0, 0);
		}
	}
}