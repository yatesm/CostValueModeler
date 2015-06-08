/**
 * 
 */
package edu.cs.clemson.jymonte.dissertation.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.Before;
import org.junit.Test;

import edu.cs.clemson.jymonte.dissertation.sim.Period;

/**
 * @author YatesDisgrace
 *
 */
public class PeriodTest {

	Period p1;
	Period p2;
	Period p3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p1 = new Period();
		//p2 = new Period(new BaseCost(52.000), new BaseValue(47.000), 0.98, 0.05, 3);
		p2 = new Period();
		p3 = new Period();
		
	}

	/**
	 * Test method for {@link edu.cs.clemson.jymonte.dissertation.sim.Period#Period()}.
	 */
	@Test
	public void testPeriod() {
		assertEquals(p1.getCost().evaluate(), 0.0, 0.0);
		assertEquals(p1.getValue().evaluate(), 0.0, 0.0);
		//assertEquals(p1.getChanceOfSuccessfulImplementation(), 0.0, 0.0);
	}

	/**
	 * Test method for {@link edu.cs.clemson.jymonte.dissertation.sim.Period#Period(double, double, double, int)}.
	 */
	@Test
	public void testPeriodDoubleDoubleDoubleInt() {
		assertEquals(p2.getCost().evaluate(), 52.000, 0.00);
		assertEquals(p2.getValue().evaluate(), 47.000, 0.00);
		//assertEquals(p2.getChanceOfSuccessfulImplementation(), 0.98, 0.00);
	}

	/**
	 * Test method for {@link edu.cs.clemson.jymonte.dissertation.sim.Period#writeOutputXML(java.io.BufferedWriter)}.
	 */
	@Test
	public void testWriteOutputXML() {
		BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			//p1.writeOutputXML(log);
			log.flush();
			//p2.writeOutputXML(log);
			log.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Test method for {@link edu.cs.clemson.jymonte.dissertation.sim.Period#computeRisklessDiscountFactor(int)}.
	 */
	@Test
	public void testComputeRisklessDiscountFactor() {
//		assertEquals(p1.computeRisklessDiscountFactor(0), 1.0, 0.0);
//		assertEquals(p2.computeRisklessDiscountFactor(1), 0.95123, 0.01);
//		assertEquals(p2.computeRisklessDiscountFactor(2), 0.90484, 0.01);
//		assertEquals(p2.computeRisklessDiscountFactor(3), 0.86072, 0.01);
		
	}
	
	@Test
	public void testGetDiscountedCost() {
//		assertEquals(p1.getDiscountedCost(0), 0.0, 0.0);
//		assertEquals(p1.getDiscountedCost(1), 0.0, 0.0);
//		assertEquals(44.75744, p2.getDiscountedCost(0), 0.01);
//		assertEquals(47.05168, p2.getDiscountedCost(1), 0.01);
//		assertEquals(49.46396, p2.getDiscountedCost(2), 0.01);
//		assertEquals(52, p2.getDiscountedCost(3), 0.01);
//		assertEquals(52, p2.getDiscountedCost(4), 0.01);
	}
	
	@Test
	public void testGetDiscountedValue() {
//		assertEquals(p1.getDiscountedValue(0), 0.0, 0.0);
//		assertEquals(p1.getDiscountedValue(1), 0.0, 0.0);
//		assertEquals(40.45384, p2.getDiscountedValue(0), 0.01);
//		assertEquals(42.52748, p2.getDiscountedValue(1), 0.01);
//		assertEquals(44.70781, p2.getDiscountedValue(2), 0.01);
//		assertEquals(47, p2.getDiscountedValue(3), 0.01);
//		assertEquals(47, p2.getDiscountedValue(4), 0.01);
	}
	
	public void testComputePeriodDifference() {
//		assertEquals(p1.computePeriodDifference(1), 0);
//		assertEquals(p1.computePeriodDifference(2), 0);
//		assertEquals(p2.computePeriodDifference(0), 3);
//		assertEquals(p2.computePeriodDifference(1), 2);
//		assertEquals(p2.computePeriodDifference(2), 1);
	}
	
	@Test
	public void testComputeNetValue() {
		System.out.println(p1.computeNetValue(0));
		System.out.println(p1.computeNetValue(1));
		System.out.println(p1.computeNetValue(2));
		System.out.println(p1.computeNetValue(3));
		
		System.out.println(p2.computeNetValue(0));
		System.out.println(p2.computeNetValue(1));
		for(int i = 0; i < 100; i++)
			System.out.println(p2.computeNetValue(2));
		System.out.println(p2.computeNetValue(3));
		
	}

}
