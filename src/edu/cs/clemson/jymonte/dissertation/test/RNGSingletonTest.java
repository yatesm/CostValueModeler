package edu.cs.clemson.jymonte.dissertation.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cs.clemson.jymonte.dissertation.utils.RNGSingleton;

public class RNGSingletonTest {
	RNGSingleton singleton = RNGSingleton.getInstance();
	
	
	@Test
	public void testGenerateRandomSeeds() {
		singleton.generateRandomSeeds();
		singleton.writeSeeds();
	}

	@Test
	public void testIncrementCurrentSeed() {
		int j = 0;
		for(int i = 0; i < singleton.getNumSeeds() * 2; i++) {			
			assertEquals(singleton.getCurrentSeed(), j);
			j = (j + 1) % 16;
			singleton.incrementCurrentSeed();
		}
			
	}

	@Test
	public void testGetRandomInt() {
		singleton.generateRandomSeeds();
		System.out.println(singleton.getRandomInt());
		System.out.println(singleton.getRandomInt());
		System.out.println(singleton.getRandomInt());
		System.out.println(singleton.getRandomInt());
	}

	@Test
	public void testGetRandomIntBounded() {
		singleton.generateRandomSeeds();
		System.out.println(singleton.getRandomIntBounded(100));
		System.out.println(singleton.getRandomIntBounded(1000));
		System.out.println(singleton.getRandomIntBounded(10000));
		System.out.println(singleton.getRandomIntBounded(100000));
	}

	@Test
	public void testGetRandomDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testOutputInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadSeedFile() {
		fail("Not yet implemented");
	}

}
