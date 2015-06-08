package edu.cs.clemson.jymonte.dissertation.sim.periodic.failure;

public class FailureStrata implements Comparable<FailureStrata> {
	private String label;
	private double failureCoeff;
	private double failureRange;
	
	/**
	 * @param label
	 * @param failureCoeff
	 * @param failureRange
	 */
	public FailureStrata(String label, double failureCoeff, double failureRange) {
		this.label = label;
		this.failureCoeff = failureCoeff;
		this.failureRange = failureRange;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the failureCoeff
	 */
	public double getFailureCoeff() {
		return failureCoeff;
	}
	/**
	 * @param failureCoeff the failureCoeff to set
	 */
	public void setFailureCoeff(double failureCoeff) {
		this.failureCoeff = failureCoeff;
	}
	/**
	 * @return the failureRange
	 */
	public double getFailureRange() {
		return failureRange;
	}
	/**
	 * @param failureRange the failureRange to set
	 */
	public void setFailureRange(double failureRange) {
		this.failureRange = failureRange;
	}
	
	public int compareTo(FailureStrata arg0) {
		int ret = 1;
		if(this.getFailureRange() > arg0.getFailureRange())
			ret = -1;
		else if(this.getFailureRange() == arg0.getFailureRange())
			ret = 0;
		return ret;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FailureStrata [label=" + label + ", failureCoeff="
				+ failureCoeff + ", failureRange=" + failureRange + "]";
	}
}
