package comp303.assignment6.robot.tests;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import comp303.assignment6.robot.Action;
import comp303.assignment6.robot.Compact;
import comp303.assignment6.robot.CompactComputation;
import comp303.assignment6.robot.Move;
import comp303.assignment6.robot.Program;
import comp303.assignment6.robot.WallE;

class CompactComputationTest {
	
	WallE robot;
	Compact compact1;
	Compact compact2;
	CompactComputation comp;
	
	
	@BeforeEach
	void setUp() {
		compact1 = new Compact();
		compact2 = new Compact();
		robot = new WallE();
		comp = new CompactComputation();
	}

	@Test
	void testFilter() {
		if (comp.filter(compact1)) {
			if (comp.filter(new Move(90))) {
				Assert.fail("Including actions that should not be included.");
			}
			Assert.assertEquals(0, 0);
		}
	}
	
	@Test
	void testFilterNullAction() {
		Compact compact3 = null;
		try {
			if (comp.filter(compact3)) {
				Assert.fail("Null action still filters.");
			}
		} catch (AssertionError e) {
			Assert.assertEquals(0, 0);
		}
	}
	
	@Test
	void testComputeProgram() {
		Program program = new Program();
		program.addAction(compact1);
		program.addAction(compact2);
		try {
			double moveTest = comp.compute(program);
			if (moveTest == 180) {
				program.addAction(new Move(90));
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
		actions.add(compact1);
		actions.add(compact2);
		try {
			double compactTest = comp.compute(actions);
			if (compactTest == 2) {
				actions.add(new Move(90));
				double compactTest2 = comp.compute(actions);
				Assert.assertEquals(2, (int)compactTest2);
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