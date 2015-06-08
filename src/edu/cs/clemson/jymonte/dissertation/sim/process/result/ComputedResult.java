package edu.cs.clemson.jymonte.dissertation.sim.process.result;

import java.util.ArrayList;
import java.util.List;

public class ComputedResult {
	List<List<Double>> averageValueCarryOut = new ArrayList<List<Double>>();
	List<List<Double>> valueCarryOutStdDev = new ArrayList<List<Double>>();
	List<List<Double>> sumValueCarryOut = new ArrayList<List<Double>>();
	List<List<Double>> averageNetValue = new ArrayList<List<Double>>();
	List<List<Double>> netValueStdDev = new ArrayList<List<Double>>();
	List<List<Double>> sumNetValue = new ArrayList<List<Double>>();
	
	List<List<Double>> averageCostCarryOut = new ArrayList<List<Double>>();
	List<List<Double>> costCarryOutStdDev = new ArrayList<List<Double>>();
	List<List<Double>> sumCostCarryOut = new ArrayList<List<Double>>();
	
	SimulationResult simResult;
	private int numPeriods;
	private int numIterations;
	
	public ComputedResult(SimulationResult simResult, int numIterations, int numPeriods) {
		this.simResult = simResult;
		this.numIterations = numIterations;
		this.numPeriods = numPeriods;
	}
	
	
	public void computeResults() {
		computeAverages();
		//computeStandardDeviations();
	}

	private void computeAverages() {
		//Compute CarryOut Averages
		//Encodes the IterationsxNumPeriodx1 results of the simulation 
		TimePeriodResult timePeriodResult;
		//Encodes the 
		ProcessResult processResult;
		PeriodResult periodResult;
		
		double runningAverageValueCarryOut = 0.0;
		double runningValueCarryOutStdDev = 0.0;
		double runningAverageNetValue = 0.0;
		double runningNetValueStdDev = 0.0;
		double runningAverageCostCarryOut = 0.0;
		double runningCostCarryOutStdDev = 0.0;
		
//		for(int timePeriodIndex = 0; timePeriodIndex < maxPeriod; timePeriodIndex++) {
//			tp = sr.get(timePeriodIndex);
//			System.out.println("\tTimePeriodResult.size() == " + tp.size());
//			for(int j = 0; j < maxPeriod; j++) {
//				System.out.println("\t\tProcessResult.size() == " + pr.size());
//				for(int k = 0; k < maxIterations; k++) {
//					pr = tp.get(k);
//					System.out.println("\t\t\t" + pr.get(j));
//				}				
//			}
//		}
		
		for(int timePeriod = 0; timePeriod < numPeriods; timePeriod++) {
			timePeriodResult = simResult.get(timePeriod);
			averageValueCarryOut.add(new ArrayList<Double>());
			valueCarryOutStdDev.add(new ArrayList<Double>());
			averageNetValue.add(new ArrayList<Double>());
			netValueStdDev.add(new ArrayList<Double>());
			
			costCarryOutStdDev.add(new ArrayList<Double>());
			averageCostCarryOut.add(new ArrayList<Double>());
			for(int developmentPeriod = 0; developmentPeriod < numPeriods; developmentPeriod++) {
				for(int iteration = 0; iteration < numIterations; iteration++) {
					processResult = timePeriodResult.get(iteration);
					periodResult = processResult.get(developmentPeriod);
					runningAverageValueCarryOut += periodResult.getValueCarryOut();
					runningValueCarryOutStdDev += Math.pow(periodResult.getValueCarryOut(), 2);
					runningAverageNetValue += periodResult.getNetValue();
					runningNetValueStdDev += Math.pow(periodResult.getNetValue(), 2);
					
					runningAverageCostCarryOut += periodResult.getCostCarryOut();
					runningCostCarryOutStdDev += Math.pow(periodResult.getCostCarryOut(), 2);
					
				}
				
				//Calculate StdDevs
				runningValueCarryOutStdDev = Math.sqrt(runningValueCarryOutStdDev + Math.pow(runningAverageValueCarryOut, 2));
				runningNetValueStdDev = Math.sqrt(runningNetValueStdDev + Math.pow(runningAverageNetValue, 2));
				runningCostCarryOutStdDev = Math.sqrt(runningCostCarryOutStdDev + Math.pow(runningAverageCostCarryOut, 2));
				
				
				//Calculate Averages
				runningAverageNetValue /= numIterations;
				runningAverageValueCarryOut /= numIterations;
				runningAverageCostCarryOut /= numIterations;
				
				costCarryOutStdDev.get(costCarryOutStdDev.size() - 1).add(runningCostCarryOutStdDev);
				valueCarryOutStdDev.get(valueCarryOutStdDev.size() - 1).add(runningValueCarryOutStdDev);
				netValueStdDev.get(netValueStdDev.size() - 1).add(runningNetValueStdDev);
				averageValueCarryOut.get(averageValueCarryOut.size()-1).add(runningAverageValueCarryOut);
				averageNetValue.get(averageNetValue.size()-1).add(runningAverageNetValue);
				averageCostCarryOut.get(averageValueCarryOut.size()-1).add(runningAverageCostCarryOut);
				
				runningAverageValueCarryOut = 0.0;
				runningValueCarryOutStdDev = 0.0;
				runningAverageNetValue = 0.0;
				runningNetValueStdDev = 0.0;
				runningAverageCostCarryOut = 0.0;
				runningCostCarryOutStdDev = 0.0;
			}
		}
	}
	
	public void outputPeriodNetValue() {
		System.out.println();
		System.out.println("Period Net Value");
		int j = 0;
		for(List<Double> list : this.averageNetValue) {
			System.out.print(list.get(0));
			System.out.print(j);
			for(int i = 1; i < list.size(); i++) {
			//for(Double d : list) {
				System.out.print("\t" + list.get(i));
			}
			System.out.print(j);
			//List<Double> stdDevList = 
			//for(int i = 0; )
			System.out.println();
		}
		
	}
	
	public void outputSumNetValue() {
		double runningSum = 0.0;
		System.out.println();
		System.out.println("Sum Net Value");
		for(List<Double> list : this.averageNetValue) {
			runningSum = list.get(0);
			System.out.print(runningSum);
			for(int i = 1; i < list.size(); i++) {
				runningSum += list.get(i);
				System.out.print("\t" + runningSum);
			}
			System.out.println();
		}
	}
	
	public void outputCarryOutValue() {
		System.out.println();
		System.out.println("Average Carry Out Value");
		for(List<Double> list : this.averageValueCarryOut) {
			System.out.print(list.get(0));
			for(int i = 1; i < list.size(); i++) {
			//for(Double d : list) {
				System.out.print("\t" + list.get(i));
			}
			System.out.println();
		}
	}
	
	public void outputCarryInValue() {
		System.out.println();
		System.out.println("Average Carry Out Cost");
		for(List<Double> list : this.averageCostCarryOut) {
			System.out.print(list.get(0));
			for(int i = 1; i < list.size(); i++) {
			//for(Double d : list) {
				System.out.print("\t" + list.get(i));
			}
			System.out.println();
		}
	}
}
