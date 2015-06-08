package edu.cs.clemson.jymonte.dissertation.sim;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultsAggregator {
	private List<List<List<List<Double>>>> results;
	private List<List<Double>> periodValues;
	private List<List<Double>> periodSumValues;
	
	public ResultsAggregator(List<List<List<List<Double>>>> simulationResults) {
		this.results = simulationResults;
		this.periodValues = new ArrayList<List<Double>>();
		this.periodSumValues = new ArrayList<List<Double>>();
	}
	
	public void aggregate() {
		//System.out.println("results.size() (Simulation Runs) == " + results.size());
		for(List<List<List<Double>>> seedSimulation : results) {
			for(List<List<Double>> periodsByPeriod : seedSimulation) {
				periodValues.add(new ArrayList<Double>());
				for(List<Double> periodIterations : periodsByPeriod) {					
					Double periodAverage = aggregatePeriodIterations(periodIterations);
					periodValues.get(periodValues.size()-1).add(periodAverage);
				}
			}
		}
	}
	
	public void aggregateRunningSum() {
		double sum = 0.0;
		for(List<List<List<Double>>> seedSimulation : results) {
			for(List<List<Double>> periodsByPeriod : seedSimulation) {
				periodSumValues.add(new ArrayList<Double>());
				for(List<Double> periodIterations : periodsByPeriod) {
					Double periodAverage = aggregatePeriodIterations(periodIterations);
					sum += periodAverage;
					periodSumValues.get(periodSumValues.size()-1).add(sum);
				}
				sum = 0.0;
			}
		}
		
	}
	
	/**
	 * Returns the average of all iterations of a particular period.
	 * @param list List of doubles that represent the Black-Scholes computation 
	 * 	for a particular period. 
	 * @return periodAverage Average of elements in list
	 */
	public Double aggregatePeriodIterations(List<Double> list) {
		double periodAverage = 0.0;
		for(Double result : list)
			periodAverage += result.doubleValue();
		periodAverage /= list.size();
		return new Double(periodAverage);
	}
	
	public void outputPeriodValues() {
		int i = 0;
		System.out.println("Values for each Period");
		for(List<Double> periodResult : periodValues) {
			//System.out.println("Seed " + j++ % 6);
			System.out.print("Period = " + i++ % 11 + "\t");
			for(Double dResult : periodResult)
				System.out.print(new DecimalFormat("0000.0000").format(dResult) + "\t");
			System.out.println();
		}
		System.out.println();
	}
	
	public void outputPeriodSumValues() {
		int i = 0;
		System.out.println("Summation of Period Values");
		for(List<Double> periodResult : periodSumValues) {
			//System.out.println("Seed " + j++ % 6);
			System.out.print("Period = " + i++ % 11 + "\t");
			for(Double dResult : periodResult)
				System.out.print(new DecimalFormat("0000.0000").format(dResult) + "\t");
			System.out.println();
		}
		System.out.println();
	}
	
	public void outputPeriodValuesToDisk(String filename) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(new File(filename)));
			int i = 0;
			for(List<Double> periodResult : periodValues) {
				out.write("Period " + Integer.toString(i) + "\t");
				for(Double dResult : periodResult)
					out.write(new DecimalFormat("0000.0000").format(dResult) + "\t"); 
				out.write("\n");
				i++;
			}
			out.flush();
		} catch (IOException e) {
			System.err.println("IOException thrown: " + e.getMessage());
			e.printStackTrace();
		} 
		
		try { if(out != null) out.close(); } catch(Exception e) {System.err.println("Error closing file"); } finally { }		
	}
	
	public void outputPeriodSumValuesToDisk(String filename) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(new File(filename)));
			int i = 0;
			for(List<Double> periodResult : periodSumValues) {
				out.write("Period " + Integer.toString(i) + "\t");
				for(Double dResult : periodResult)
					out.write(new DecimalFormat("0000.0000").format(dResult) + "\t"); 
				out.write("\n");
				i++;
			}
			out.flush();
		} catch (IOException e) {
			System.err.println("IOException thrown: " + e.getMessage());
			e.printStackTrace();
		} 
		
		try { if(out != null) out.close(); } catch(Exception e) { System.err.println("Error closing file");} finally { }		
	}
}
