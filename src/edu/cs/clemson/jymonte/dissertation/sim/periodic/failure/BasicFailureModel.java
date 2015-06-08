package edu.cs.clemson.jymonte.dissertation.sim.periodic.failure;

public class BasicFailureModel implements FailureModelInterface {
	private double failureDiscountFactor = 0.5;

	/**
	 * @param failureDiscountFactor
	 */
	public BasicFailureModel(double failureDiscountFactor) {
		super();
		this.failureDiscountFactor = failureDiscountFactor;
	}
	
	@Override
	public double computeFailure(double probability, double rngValue) {
		return this.failureDiscountFactor;
	}
	
	/**
	 * @return the failureDiscountFactor
	 */
	public double getFailureDiscountFactor() {
		return failureDiscountFactor;
	}

	/**
	 * @param failureDiscountFactor the failureDiscountFactor to set
	 */
	public void setFailureDiscountFactor(double failureDiscountFactor) {
		this.failureDiscountFactor = failureDiscountFactor;
	}	
}
