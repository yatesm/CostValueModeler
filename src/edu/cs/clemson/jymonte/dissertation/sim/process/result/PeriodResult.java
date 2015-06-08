package edu.cs.clemson.jymonte.dissertation.sim.process.result;

public class PeriodResult {
	private double finalCost;
	private double finalValue;
	private double expectedValue;
	private double netValue;
	private double valueCarryOut;
	private double valueCarryIn;
	private boolean failure;
	
	private double costCarryOut;
	private double costCarryIn;
	
	private int currentPeriod;
	private int periodOfCompletion;
	

	/**
	 * @param finalCost
	 * @param finalValue
	 * @param expectedValue
	 * @param netValue
	 * @param valueCarryOut
	 * @param valueCarryIn
	 * @param failure
	 * @param costCarryOut
	 * @param costCarryIn
	 * @param currentPeriod
	 * @param periodOfCompletion
	 */
	public PeriodResult(double finalCost, double finalValue,
			double expectedValue, double netValue, double valueCarryOut,
			double valueCarryIn, boolean failure, double costCarryOut,
			double costCarryIn, int currentPeriod, int periodOfCompletion) {
		super();
		this.finalCost = finalCost;
		this.finalValue = finalValue;
		this.expectedValue = expectedValue;
		this.netValue = netValue;
		this.valueCarryOut = valueCarryOut;
		this.valueCarryIn = valueCarryIn;
		this.failure = failure;
		this.costCarryOut = costCarryOut;
		this.costCarryIn = costCarryIn;
		this.currentPeriod = currentPeriod;
		this.periodOfCompletion = periodOfCompletion;
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
		return expectedValue;
	}
	/**
	 * @param expectedCost the expectedCost to set
	 */
	public void setExpectedCost(double expectedCost) {
		this.expectedValue = expectedCost;
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
	 * @return the expectedValue
	 */
	public double getExpectedValue() {
		return expectedValue;
	}
	/**
	 * @param expectedValue the expectedValue to set
	 */
	public void setExpectedValue(double expectedValue) {
		this.expectedValue = expectedValue;
	}
	/**
	 * @return the failure
	 */
	public boolean getFailure() {
		return failure;
	}
	/**
	 * @param failure the failure to set
	 */
	public void setFailure(boolean failure) {
		this.failure = failure;
	}
	/**
	 * @return the currentPeriod
	 */
	public int getCurrentPeriod() {
		return currentPeriod;
	}
	/**
	 * @param currentPeriod the currentPeriod to set
	 */
	public void setCurrentPeriod(int currentPeriod) {
		this.currentPeriod = currentPeriod;
	}
	/**
	 * @return the periodOfCompletion
	 */
	public int getPeriodOfCompletion() {
		return periodOfCompletion;
	}
	/**
	 * @param periodOfCompletion the periodOfCompletion to set
	 */
	public void setPeriodOfCompletion(int periodOfCompletion) {
		this.periodOfCompletion = periodOfCompletion;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PeriodResult [finalCost=" + finalCost + ", finalValue="
				+ finalValue + ", expectedValue=" + expectedValue
				+ ", netValue=" + netValue + ", carryOut=" + valueCarryOut
				+ ", carryIn=" + valueCarryIn + ", failure=" + failure
				+ ", currentPeriod=" + currentPeriod + ", periodOfCompletion="
				+ periodOfCompletion + "]";
	}
	/**
	 * @return the valueCarryOut
	 */
	public double getValueCarryOut() {
		return valueCarryOut;
	}
	/**
	 * @param valueCarryOut the valueCarryOut to set
	 */
	public void setValueCarryOut(double valueCarryOut) {
		this.valueCarryOut = valueCarryOut;
	}
	/**
	 * @return the valueCarryIn
	 */
	public double getValueCarryIn() {
		return valueCarryIn;
	}
	/**
	 * @param valueCarryIn the valueCarryIn to set
	 */
	public void setValueCarryIn(double valueCarryIn) {
		this.valueCarryIn = valueCarryIn;
	}
	/**
	 * @return the costCarryOut
	 */
	public double getCostCarryOut() {
		return costCarryOut;
	}
	/**
	 * @param costCarryOut the costCarryOut to set
	 */
	public void setCostCarryOut(double costCarryOut) {
		this.costCarryOut = costCarryOut;
	}
	/**
	 * @return the costCarryIn
	 */
	public double getCostCarryIn() {
		return costCarryIn;
	}
	/**
	 * @param costCarryIn the costCarryIn to set
	 */
	public void setCostCarryIn(double costCarryIn) {
		this.costCarryIn = costCarryIn;
	}
}
