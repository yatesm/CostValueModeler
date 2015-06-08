/**
 * 
 */
package edu.cs.clemson.jymonte.dissertation.stat.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cs.clemson.jymonte.dissertation.prob.PoissonProbability;
import edu.cs.clemson.jymonte.dissertation.stat.SlidingPoissonProbabilityStrategy;

/**
 * @author YatesDisgrace
 *
 */
public class SlidingPoissonProbabilityStrategyTest {
	SlidingPoissonProbabilityStrategy p1 = new SlidingPoissonProbabilityStrategy(new PoissonProbability(4, 4, 0));
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link edu.cs.clemson.jymonte.dissertation.stat.SlidingPoissonProbabilityStrategy#evaluate(double, int, int)}.
	 */
	@Test
	public void testEvaluate() {
		System.out.println(p1.evaluate(0, 1, 2));
	}

}
