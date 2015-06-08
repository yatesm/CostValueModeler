package edu.cs.clemson.jymonte.dissertation.sim.periodic.results;

import java.util.ArrayList;
import java.util.List;

public class PeriodResult {
	
	List<IterationResult> resultList = new ArrayList<IterationResult>();
	
	private double averageFinalCost = 0.0;
	private double averageFinalValue = 0.0;
	private double averageExpectedValue = 0.0;
	private double averageNetValue = 0.0;
	private double averageCarryOut = 0.0;
	private double averageCarryIn = 0.0;
	
	private double deviationFinalCost;
	private double deviationFinalValue;
	private double deviationExpectedValue;
	private double deviationNetValue;
	private double deviationCarryOut;
	private double deviationCarryIn;
	
	private double finalCostSumSquared;
	private double finalValueSumSquared;
	private double finalExpectedValueSumSquared;
	private double netValueSumSquared;
	private double carryOutSumSquared;
	private double carryInSumSquared;
	
	private double finalCostSquareSum;
	private double finalValueSquareSum;
	private double finalExpectedValueSquareSum;
	private double netValueSquareSum;
	private double carryOutSquareSum;
	private double carryInSquareSum;
	/**
	 * @param resultList
	 */
	public PeriodResult(List<IterationResult> resultList) {
		super();
		this.resultList = resultList;
	}

	public PeriodResult() {
		resultList = new ArrayList<IterationResult>();
	}
	
	public void calculateResults() {
		calculateAverages();
		calculateStandardDeviations();
	}
	
	private void calculateAverages() {
		for(IterationResult iterationResult : resultList) {
			averageFinalCost += iterationResult.getFinalCost();
			averageFinalValue += iterationResult.getFinalValue();
			averageExpectedValue += iterationResult.getExpectedCost();
			averageNetValue += iterationResult.getNetValue();
			averageCarryOut += iterationResult.getCarryOut();
			averageCarryIn += iterationResult.getCarryIn();
		}
		
		averageFinalCost /= resultList.size();
		averageFinalValue /= resultList.size();
		averageExpectedValue /= resultList.size();
		averageNetValue /= resultList.size();
		averageCarryOut /= resultList.size();
		averageCarryIn /= resultList.size(); 
	}
	
	private void calculateStandardDeviations() {
		for(IterationResult iterationResult : resultList) {
			finalCostSumSquared += iterationResult.getFinalCost();
			finalValueSumSquared += iterationResult.getFinalValue();
			finalExpectedValueSumSquared += iterationResult.getExpectedCost();
			netValueSumSquared += iterationResult.getNetValue();
			carryOutSumSquared += iterationResult.getCarryOut();
			carryInSumSquared += iterationResult.getCarryIn();
			
			finalCostSquareSum += Math.pow(iterationResult.getFinalCost(), 2);
			finalValueSquareSum += Math.pow(iterationResult.getFinalValue(), 2);
			finalExpectedValueSquareSum += Math.pow(iterationResult.getExpectedCost(), 2);
			netValueSquareSum += Math.pow(iterationResult.getNetValue(), 2);
			carryOutSquareSum += Math.pow(iterationResult.getCarryOut(), 2);
			carryInSquareSum += Math.pow(iterationResult.getCarryIn(), 2);
		}
		
		finalCostSumSquared = Math.pow(finalCostSumSquared, 2);
		finalValueSumSquared = Math.pow(finalValueSumSquared, 2);
		finalExpectedValueSumSquared = Math.pow(finalExpectedValueSumSquared, 2);
		netValueSumSquared = Math.pow(netValueSumSquared, 2);
		carryOutSumSquared = Math.pow(carryOutSumSquared, 2);
		carryInSumSquared = Math.pow(carryInSumSquared, 2);
		
		deviationFinalCost = Math.sqrt(finalCostSumSquared + finalCostSquareSum);
		deviationFinalValue = Math.sqrt(finalValueSumSquared + finalValueSquareSum);
		deviationExpectedValue = Math.sqrt(finalExpectedValueSumSquared + finalExpectedValueSquareSum);
		deviationNetValue = Math.sqrt(netValueSumSquared+ netValueSquareSum);
		deviationCarryOut = Math.sqrt(carryOutSumSquared + carryOutSquareSum);
		deviationCarryIn = Math.sqrt(carryInSumSquared + carryInSquareSum);
	}

	/**
	 * @return the averageFinalCost
	 */
	public double getAverageFinalCost() {
		return averageFinalCost;
	}

	/**
	 * @return the averageFinalValue
	 */
	public double getAverageFinalValue() {
		return averageFinalValue;
	}

	/**
	 * @return the averageExpectedValue
	 */
	public double getAverageExpectedValue() {
		return averageExpectedValue;
	}

	/**
	 * @return the averageNetValue
	 */
	public double getAverageNetValue() {
		return averageNetValue;
	}

	/**
	 * @return the averageCarryOut
	 */
	public double getAverageCarryOut() {
		return averageCarryOut;
	}

	/**
	 * @return the averageCarryIn
	 */
	public double getAverageCarryIn() {
		return averageCarryIn;
	}

	/**
	 * @return the deviationFinalCost
	 */
	public double getDeviationFinalCost() {
		return deviationFinalCost;
	}

	/**
	 * @return the deviationFinalValue
	 */
	public double getDeviationFinalValue() {
		return deviationFinalValue;
	}

	/**
	 * @return the deviationExpectedValue
	 */
	public double getDeviationExpectedValue() {
		return deviationExpectedValue;
	}

	/**
	 * @return the deviationNetValue
	 */
	public double getDeviationNetValue() {
		return deviationNetValue;
	}

	/**
	 * @return the deviationCarryOut
	 */
	public double getDeviationCarryOut() {
		return deviationCarryOut;
	}

	/**
	 * @return the deviationCarryIn
	 */
	public double getDeviationCarryIn() {
		return deviationCarryIn;
	}

	public void addResult(IterationResult r) {
		this.resultList.add(r);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PeriodResult [resultList=" + resultList + ", averageFinalCost="
				+ averageFinalCost + ", averageFinalValue=" + averageFinalValue
				+ ", averageExpectedValue=" + averageExpectedValue
				+ ", averageNetValue=" + averageNetValue + ", averageCarryOut="
				+ averageCarryOut + ", averageCarryIn=" + averageCarryIn
				+ ", deviationFinalCost=" + deviationFinalCost
				+ ", deviationFinalValue=" + deviationFinalValue
				+ ", deviationExpectedValue=" + deviationExpectedValue
				+ ", deviationNetValue=" + deviationNetValue
				+ ", deviationCarryOut=" + deviationCarryOut
				+ ", deviationCarryIn=" + deviationCarryIn
				+ "]";
	}
}