package edu.cs.clemson.jymonte.dissertation.sim;

import edu.cs.clemson.jymonte.dissertation.params.CompositeValue;
import edu.cs.clemson.jymonte.dissertation.params.ValueInterface;
import edu.cs.clemson.jymonte.dissertation.prob.ProbabilityModelInterface;
import edu.cs.clemson.jymonte.dissertation.sim.cost.CompositeCost;
import edu.cs.clemson.jymonte.dissertation.sim.cost.CostInterface;
import edu.cs.clemson.jymonte.dissertation.stat.ProbabilityModelVariationStrategy;
import edu.cs.clemson.jymonte.dissertation.utils.RNGSingleton;

public class Period {
	private CostInterface cost;
	private ValueInterface value;
	private double risklessDiscountRate = 0.0;
	private double costReduction = 0.0;
	private double addedValue = 0.0;
	private int periodOfCompletion = 0;
	
	private ProbabilityModelInterface chanceOfSuccessfulImpl;
	private ProbabilityModelVariationStrategy successfulImplementationModel;
	private double failureCoefficient = 0.6;
	
	public Period() {
		this.cost = new CompositeCost();
		this.value = new CompositeValue();
	}

	/**
	 * @param cost
	 * @param value
	 * @param chanceOfSuccessfulImplementation
	 * @param risklessDiscountRate
	 * @param costReduction
	 * @param addedValue
	 * @param periodOfCompletion
	 */
	public Period(CostInterface cost, ValueInterface value,			
			double risklessDiscountRate, double costReduction,
			double addedValue, int periodOfCompletion,
			ProbabilityModelInterface chanceOfSuccessfulImpl,
			ProbabilityModelVariationStrategy probabilityVariationModel) {
		super();
		this.cost = cost;
		this.value = value;
		this.risklessDiscountRate = risklessDiscountRate;
		this.costReduction = costReduction;
		this.addedValue = addedValue;
		this.periodOfCompletion = periodOfCompletion;
		this.chanceOfSuccessfulImpl = chanceOfSuccessfulImpl;
		this.successfulImplementationModel = probabilityVariationModel;
	}

	public double computeNetValue(int currentPeriod) {
		double finalCost, finalValue, expectedValue, netValue;
		finalCost = this.getDiscountedCost(currentPeriod);
		finalValue = this.getDiscountedValue(currentPeriod);
		double probability = this.successfulImplementationModel.evaluate(this.chanceOfSuccessfulImpl.evaluate(), 
				currentPeriod, this.getPeriodOfCompletion());
		if(RNGSingleton.getInstance().getRandomDouble() > probability)
			expectedValue = finalValue * this.getFailureCoefficient();
		else
			expectedValue = finalValue;
		netValue = expectedValue - finalCost;
		return netValue;
	}

	protected int computePeriodDifference(int currentPeriod) {
		return  (this.periodOfCompletion - currentPeriod) > 0 ? this.periodOfCompletion - currentPeriod : 0;
	}

	protected double computeRisklessDiscountFactor(int periodDifference) {
		return Math.pow(Math.E, (-1.0 * this.risklessDiscountRate * periodDifference));
	}

	public Double evaluate(int currentPeriod) {
		return new Double(computeNetValue(currentPeriod));
		
	}

	/**
	 * @return the addedValue
	 */
	public double getAddedValue() {
		return addedValue;
	}

	/**
	 * @return the costReduction
	 */
	public double getCostReduction() {
		return costReduction;
	}

	private double getDiscountedCost(int currentPeriod) {
		int periodDifference = computePeriodDifference(currentPeriod);
		return this.cost.evaluate() * this.computeRisklessDiscountFactor(periodDifference) * (1.0 - this.costReduction);
	}

	protected double getDiscountedValue(int currentPeriod) {
		int periodDifference = computePeriodDifference(currentPeriod);
		return this.value.evaluate() * this.computeRisklessDiscountFactor(periodDifference) * (1.0 + this.addedValue);
	}

	/**
	 * @return the periodOfCompletion
	 */
	public int getPeriodOfCompletion() {
		return periodOfCompletion;
	}
	
	/**
	 * @return the risklessDiscountRate
	 */
	public double getRisklessDiscountRate() {
		return risklessDiscountRate;
	}
	
	/**
	 * @return the value
	 */
	public ValueInterface getValue() {
		return value;
	}
	
	/**
	 * @param addedValue the addedValue to set
	 */
	public void setAddedValue(double addedValue) {
		this.addedValue = addedValue;
	}
		
	/**
	 * @param costReduction the costReduction to set
	 */
	public void setCostReduction(double costReduction) {
		this.costReduction = costReduction;
	}

	/**
	 * @param periodOfCompletion the periodOfCompletion to set
	 */
	public void setPeriodOfCompletion(int periodOfCompletion) {
		this.periodOfCompletion = periodOfCompletion;
	}
	

	/**
	 * @param risklessDiscountRate the risklessDiscountRate to set
	 */
	public void setRisklessDiscountRate(double risklessDiscountRate) {
		this.risklessDiscountRate = risklessDiscountRate;
	}


	@Override
	public String toString() {
		String s = "\tC: " + this.cost.evaluate() + "\tCR: " + this.costReduction + 
				"\tV: "  + this.value.evaluate() + "\tAV: " + this.addedValue + 
				"\tR: " + this.risklessDiscountRate + "\tP: " + 
				this.chanceOfSuccessfulImpl.evaluate();
		return s;
	}
	
	/**
	 * @return the successfulImplementationModel
	 */
	public ProbabilityModelVariationStrategy getSuccessfulImplementationModel() {
		return successfulImplementationModel;
	}

	/**
	 * @param successfulImplementationModel the successfulImplementationModel to set
	 */
	public void setSuccessfulImplementationModel(
			ProbabilityModelVariationStrategy successfulImplementationModel) {
		this.successfulImplementationModel = successfulImplementationModel;
	}

	/**
	 * @return the failureCoefficient
	 */
	public double getFailureCoefficient() {
		return failureCoefficient;
	}

	/**
	 * @param failureCoefficient the failureCoefficient to set
	 */
	public void setFailureCoefficient(double failureCoefficient) {
		this.failureCoefficient = failureCoefficient;
	}

	public CostInterface getCost() {
		return this.cost;
	}

	public String toXML() {
		String result = "<period>\n";
		result += "<cost>" + this.cost.evaluate() + "</cost>\n";
		result += "<value>" + this.value.evaluate() + "</value>\n";
		result += "<costReduction>" + this.costReduction + "</costReduction>\n";
		result += "<valueAddition>" + this.addedValue + "</valueAddition>\n";
		result += "<discountRate>" + this.risklessDiscountRate + "</discountRate>\n";
		result += "<periodOfCompletion>" + this.periodOfCompletion + "</periodOfCompletion>\n";
		result += "<probabilityModel>" + chanceOfSuccessfulImpl.toXML() + "</probabilityModel>\n";
		result += "<probabilityVarianceModel>" + successfulImplementationModel.toXML() + "</probabilityVarianceModel>\n";
		
		result += "</period>\n";
		return result;
	}

	public void setValue(ValueInterface baseValue) {
		this.value = baseValue;
		
	}

	public void setCost(CostInterface baseCost) {
		this.cost = baseCost;
		
	}

	public void setProbabilityModel(ProbabilityModelInterface probMod) {
		this.chanceOfSuccessfulImpl = probMod;
		
	}

}
