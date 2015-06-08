package edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.FailureStrata;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.StratifiedFailureModel;

public class StratifiedFailureModelTest {
	StratifiedFailureModel model1 = new StratifiedFailureModel();
	
	@Before
	public void setUp() throws Exception {
		model1.addStrata(new FailureStrata("minor", 0.25, 0.25));
		model1.addStrata(new FailureStrata("major", 0.50, 0.25));
		model1.addStrata(new FailureStrata("critical", 0.75, 0.25));
		model1.addStrata(new FailureStrata("catastrophic", 0.9, 0.25));
		
		model1.printStrata();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		double result = 0.0;
		result = model1.computeFailure(0.8, 0.75);
		assertEquals(result, 1, 0.01);
		for(int i = 0; i <= 20; i++) {
			System.out.println("Test for 0.80 and " + (0.80 + (0.01 * i)));
			result = model1.computeFailure(0.80, 0.80 + 0.01 * i);
			if(i <= 5)
				assertEquals(0.25, result, 0.01);
			else if(i > 5 && i <= 10)
				assertEquals(0.5, result, 0.01);
			else if(i > 10 && i <= 15)
				assertEquals(0.75, result, 0.01);
			else
				assertEquals(0.90, result, 0.01);
		}		
	}

}
