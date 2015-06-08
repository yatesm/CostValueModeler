/**
 * 
 */
package edu.cs.clemson.jymonte.dissertation.sim;

import java.util.ArrayList;
import java.util.List;

import edu.cs.clemson.jymonte.dissertation.utils.RNGSingleton;

/**
 * @author YatesDisgrace
 *
 */
public class Simulation {
	private List<List<List<List<Double>>>> simulationResults;
	/**
	 * @return the simulationResults
	 */
	public List<List<List<List<Double>>>> getSimulationResults() {
		return simulationResults;
	}

	private int numIterations = 1000;
	private int numSeeds = 1;
	//List<ArrayList<>>
	Project project;

	public Simulation() {
		simulationResults =  new ArrayList<List<List<List<Double>>>>(numSeeds);
	}
	
	public Simulation(int numSeeds, int numIterations) {
		this.numIterations = numIterations;
		this.numSeeds = numSeeds;
		simulationResults =  new ArrayList<List<List<List<Double>>>>(numSeeds);
	}
	
	public Simulation(int numSeeds, int numIterations, Project project) {
		this.numIterations = numIterations;
		this.numSeeds = numSeeds;
		simulationResults =  new ArrayList<List<List<List<Double>>>>(numSeeds);
		this.project = project;
	}
	
	public void run() {
		for(int seed = 0; seed < numSeeds; seed++) {
			project.evaluate(numIterations);
			simulationResults.add(project.getAllPeriodAggregateResults());
			RNGSingleton.getInstance().incrementCurrentSeed();
		}
			
	}
	
	/**
	 * @return the numIterations
	 */
	public int getNumIterations() {
		return numIterations;
	}

	/**
	 * @param numIterations the numIterations to set
	 */
	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
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
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
}
