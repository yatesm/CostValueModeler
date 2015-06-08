package edu.cs.clemson.jymonte.dissertation.sim.periodic.results;

public class IterationResult {
	private double finalCost;
	private double finalValue;
	private double expectedCost;
	private double netValue;
	private double carryOut;
	private double carryIn;
	/**
	 * @param finalCost
	 * @param finalValue
	 * @param expectedCost
	 * @param netValue
	 * @param carryOut
	 * @param carryIn
	 */
	public IterationResult(double finalCost, double finalValue, double expectedCost,
			double netValue, double carryOut, double carryIn) {
		this.finalCost = finalCost;
		this.finalValue = finalValue;
		this.expectedCost = expectedCost;
		this.netValue = netValue;
		this.carryOut = carryOut;
		this.carryIn = carryIn;
	}
	/**
	 * @return the finalCost
	 */
	public double getFinalCost() {
		return finalCost;
	}
	/**
	 * @param finalCost the finalCost to set
	 */
	public void setFinalCost(double finalCost) {
		this.finalCost = finalCost;
	}
	/**
	 * @return the finalValue
	 */
	public double getFinalValue() {
		return finalValue;
	}
	/**
	 * @param finalValue the finalValue to set
	 */
	public void setFinalValue(double finalValue) {
		this.finalValue = finalValue;
	}
	/**
	 * @return the expectedCost
	 */
	public double getExpectedCost() {
		return expectedCost;
	}
	/**
	 * @param expectedCost the expectedCost to set
	 */
	public void setExpectedCost(double expectedCost) {
		this.expectedCost = expectedCost;
	}
	/**
	 * @return the netValue
	 */
	public double getNetValue() {
		return netValue;
	}
	/**
	 * @param netValue the netValue to set
	 */
	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}
	/**
	 * @return the carryOut
	 */
	public double getCarryOut() {
		return carryOut;
	}
	/**
	 * @param carryOut the carryOut to set
	 */
	public void setCarryOut(double carryOut) {
		this.carryOut = carryOut;
	}
	/**
	 * @return the carryIn
	 */
	public double getCarryIn() {
		return carryIn;
	}
	/**
	 * @param carryIn the carryIn to set
	 */
	public void setCarryIn(double carryIn) {
		this.carryIn = carryIn;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IterationResult [finalCost=" + finalCost + ", finalValue=" + finalValue
				+ ", expectedValue=" + expectedCost + ", netValue=" + netValue
				+ ", carryOut=" + carryOut + ", carryIn=" + carryIn + "]";
	}
	
	
}
