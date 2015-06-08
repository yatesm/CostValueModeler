package edu.cs.clemson.jymonte.dissertation.stat.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cs.clemson.jymonte.dissertation.stat.LinearProbabilityStrategy;

public class LinearProbabilityStrategyTest {
	
	LinearProbabilityStrategy strat1 = new LinearProbabilityStrategy(0.05);
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		evaluateTest();
	}
	
	@Test
	public void evaluateTest() {
//		for(int i = 0; i < 10; i++) {
//			for(int j = 0; j < i; j++) {
//				assertEquals(this.strat1.evaluate(0.5, i, j), 0.5 - ((j - i) * 0.05), 0.01);
//			}
//		}
		assertEquals(this.strat1.evaluate(0.5, 0, 0), 0.5, 0.01);
		assertEquals(this.strat1.evaluate(0.5, 0, 1), 0.45, 0.01);
	
		assertEquals(this.strat1.evaluate(0.5, 0, 2), 0.40, 0.01);
		assertEquals(this.strat1.evaluate(0.5, 1, 3), 0.40, 0.01);
		for(int i = 0; i < 10; i++)
			assertEquals(this.strat1.evaluate(0.5, i, 0), 0.5, 0.01);
		
	}

}
