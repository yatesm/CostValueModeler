package edu.cs.clemson.jymonte.dissertation.sim;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private List<Period> periodsOfDevelopment;
	List<List<List<Double>>> allPeriodAggregateResults; 

	
	public Project(Project p) {
		this.periodsOfDevelopment = new ArrayList<Period>(p.periodsOfDevelopment);
		this.allPeriodAggregateResults = new ArrayList<List<List<Double>>>();
	}
	
	public Project() {
		this.periodsOfDevelopment = new ArrayList<Period>();
		this.allPeriodAggregateResults = new ArrayList<List<List<Double>>>();
	}

	public List<List<List<Double>>> evaluate(int numIterations) {
		List<Double> periodResultList = null;
		List<List<Double>> singlePeriodResults = null; 
		int maxPeriod = this.getTotalPeriods();
		for(int currentPeriod = 0; currentPeriod < maxPeriod; currentPeriod++) {
			singlePeriodResults = new ArrayList<List<Double>>(numIterations);
			for(int i = 0; i < maxPeriod; i++) {
				periodResultList = new ArrayList<Double>();
				if(i >= currentPeriod) {
					for(int iteration = 0; iteration < numIterations; iteration++) {
						periodResultList.add(iteration, periodsOfDevelopment.get(i).evaluate(currentPeriod));
					}
					singlePeriodResults.add(periodResultList);
				}
				else {
					singlePeriodResults.add(allPeriodAggregateResults.get(currentPeriod - 1).get(i));
				}
			}
			this.allPeriodAggregateResults.add(singlePeriodResults);
		}
		return this.allPeriodAggregateResults;
	}
	
	public int getTotalPeriods() {
		return periodsOfDevelopment.size();
	}
	
	public void addPeriodToProject(Period p) {
		periodsOfDevelopment.add(p);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < this.periodsOfDevelopment.size(); i++)
			s += "Period " + i + ": " + periodsOfDevelopment.get(i).toString() + "\n";
		return s;
	}

	/**
	 * @return the allPeriodAggregateResults
	 */
	public List<List<List<Double>>> getAllPeriodAggregateResults() {
		return allPeriodAggregateResults;
	}

	/**
	 * @param allPeriodAggregateResults the allPeriodAggregateResults to set
	 */
	public void setAllPeriodAggregateResults(
			List<List<List<Double>>> allPeriodAggregateResults) {
		this.allPeriodAggregateResults = allPeriodAggregateResults;
	}
	
	public String toXML() {
		String result = "<project>\n";
		for(Period p : periodsOfDevelopment)
			result += p.toXML();
		result += "</project>";
		return result;
	}
	
}
