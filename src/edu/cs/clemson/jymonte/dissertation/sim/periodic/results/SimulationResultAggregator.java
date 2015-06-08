package edu.cs.clemson.jymonte.dissertation.sim.periodic.results;

import java.util.ArrayList;
import java.util.List;

public class SimulationResultAggregator {
	List<PeriodResultAggregator> resultList;
	List<List<Double>> sumNetValues;
	List<List<Double>> sumCarryOutValues;
	
	/**
	 * @param resultList
	 */
	public SimulationResultAggregator(List<PeriodResultAggregator> resultList) {
		super();
		this.resultList = resultList;
	}

	public SimulationResultAggregator() {
		resultList = new ArrayList<PeriodResultAggregator>();
	}

	/**
	 * @return the resultList
	 */
	public List<PeriodResultAggregator> getResultList() {
		return resultList;
	}
	
	public void addList(PeriodResultAggregator periodResults) {
		this.resultList.add(periodResults);
	}
	
	public void outputNetValue(int numPeriods) {
		System.out.println("Net Value Per Period");
		for(int i = 0; i < numPeriods; i++) {
			for(int j = 0; j < resultList.size(); j++) {
				System.out.print(resultList.get(j).getPeriodResultList().get(i).getAverageNetValue() + "\t");
			}
			System.out.println();				
		}
		System.out.println();
	}
	
	public void outputCarryOut(int numPeriods) {
		System.out.println("Carry Out Per Period");
		for(int i = 0; i < numPeriods; i++) {
			for(int j = 0; j < resultList.size(); j++) {
				System.out.print(resultList.get(j).getPeriodResultList().get(i).getAverageCarryOut() + "\t");
			}
			System.out.println();				
		}
		System.out.println();
	}
	
	public void outputSumCarryOut() {
		System.out.println("Sum Carry Out Per Period");
		for(List<Double> list : this.sumCarryOutValues) {
			for(Double result : list) {
				System.out.print(result + "\t"); 
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	public void outputSumNetValue() {
		System.out.println("Sum Net Value Per Period");
		for(List<Double> list : this.sumNetValues) {
			for(Double result : list) {
				System.out.print(result + "\t"); 
			}
			System.out.print("\n");
		}
		System.out.println("\n");
		
	}
	
	public void computeSums(int numPeriods) {
		this.sumNetValues = new ArrayList<List<Double>>();
		this.sumCarryOutValues = new ArrayList<List<Double>>();
		
		double sumNetVal = 0.0;
		double sumCarryOutVal = 0.0;
		//For each column (time period)
		for(int i = 0; i < numPeriods; i++) {
			List<Double> tempNetValList = new ArrayList<Double>();
			List<Double> tempCarryOutValList = new ArrayList<Double>();
			for(int k = 0; k < numPeriods; k++) {
				sumNetVal += this.resultList.get(k).getPeriodResultList().get(i).getAverageNetValue();
				tempNetValList.	add(new Double(sumNetVal));
				sumCarryOutVal += this.resultList.get(k).getPeriodResultList().get(i).getAverageCarryOut();
				tempCarryOutValList.add(new Double(sumCarryOutVal));					
			}
			this.sumNetValues.add(tempNetValList);
			this.sumCarryOutValues.add(tempCarryOutValList);
			sumNetVal = 0.0;
			sumCarryOutVal = 0.0;
		}			
	}
}
