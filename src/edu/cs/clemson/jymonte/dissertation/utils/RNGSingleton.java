package edu.cs.clemson.jymonte.dissertation.utils;

import java.io.BufferedReader;

import org.apache.commons.math3.random.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RNGSingleton {
	private static RNGSingleton instance = null;
	
	private List<MersenneTwister> rand;
	private int numSeeds = 16;
	private int seedSeed;
	private int[] seeds = new int[numSeeds];

	private int currentSeed = 0;
	
	public void generateRandomSeeds() {
		seedSeed = (int) new Date().getTime(); 
		Random rand = new Random(numSeeds);
		for(int i = 0; i < numSeeds; i++) {
			seeds[i] = rand.nextInt();
			this.rand.add(i, new MersenneTwister(seeds[i]));
		}
	}
	
	private RNGSingleton() {
		rand = new ArrayList<MersenneTwister>(numSeeds);
		this.generateRandomSeeds();
	}
	
	public void incrementCurrentSeed() {
		currentSeed = (currentSeed + 1) % numSeeds;
	}
	public static RNGSingleton getInstance() {
		if(instance == null)
			instance = new RNGSingleton();
		return instance;
	}
	
	public int getRandomInt() {
		return rand.get(currentSeed).nextInt();
	}
	
	public int getRandomIntBounded(int bound) {
		return rand.get(currentSeed).nextInt(bound);
	}
	
	public double getRandomDouble() {
		return rand.get(currentSeed).nextDouble();
	}
	
	public void outputInfo() {
		BufferedWriter out = null;
		String filename = "output/rng" + new Date().getTime() + ".txt";
		try {
			out = new BufferedWriter(new FileWriter(filename));
			out.write(String.valueOf(seedSeed) + "\n");
			out.write(String.valueOf(numSeeds) + "\n");
			out.write(String.valueOf(currentSeed) + "\n");
			for(int i = 0; i < numSeeds; i++) {
				out.write(String.valueOf(seeds[i]) + "\n");
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Could not write to file: " + filename);
			e.printStackTrace();
		} finally {
			if(out != null) try { out.close(); } catch(Exception e) { }
		}
	}
	
	public void readSeedFile(String filename) {
		BufferedReader in = null;		
		try {
			in = new BufferedReader(new FileReader(filename));
			String s = in.readLine().replace("\n", "");
			if(s != null && s.length() > 0)
				numSeeds = Integer.valueOf(s);
			this.seeds = new int[numSeeds];
			for(int i = 0; i < numSeeds; i++) {
				String line = in.readLine();
				if(line != null)
					line = line.replace("\n", "");
					try {
						seeds[i] = Integer.valueOf(line);
					} catch (NumberFormatException e) {
						System.err.println("Could not parse number: " + line + " as integer");
						e.printStackTrace();
					}
			}
		}
		catch(IOException e) {
			System.err.println("Error reading from file: " + filename);
			
		} finally {
			if(in != null) try { in.close(); } catch(Exception e) { }
		}
		
	}
	
	public void writeSeeds() {
		for(int i = 0; i < numSeeds; i++)
			System.out.println(seeds[i]);
	}

	/**
	 * @return the numSeeds
	 */
	public int getNumSeeds() {
		return numSeeds;
	}

	/**
	 * @param numSeeds the numSeeds to set
	 */
	public void setNumSeeds(int numSeeds) {
		this.numSeeds = numSeeds;
	}

	/**
	 * @return the seedSeed
	 */
	public long getSeedSeed() {
		return seedSeed;
	}

	/**
	 * @param seedSeed the seedSeed to set
	 */
	public void setSeedSeed(int seedSeed) {
		this.seedSeed = seedSeed;
	}

	/**
	 * @return the seeds
	 */
	public int [] getSeeds() {
		return seeds;
	}

	/**
	 * @param seeds the seeds to set
	 */
	public void setSeeds(int [] seeds) {
		this.seeds = seeds;
	}

	/**
	 * @return the currentSeed
	 */
	public int getCurrentSeed() {
		return currentSeed;
	}

	/**
	 * @param currentSeed the currentSeed to set
	 */
	public void setCurrentSeed(int currentSeed) {
		this.currentSeed = currentSeed;
	}

}
