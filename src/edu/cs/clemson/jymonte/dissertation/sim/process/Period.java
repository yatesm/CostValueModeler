package edu.cs.clemson.jymonte.dissertation.sim.process;
import edu.cs.clemson.jymonte.dissertation.params.CompositeValue;
import edu.cs.clemson.jymonte.dissertation.params.ValueInterface;
import edu.cs.clemson.jymonte.dissertation.prob.ProbabilityModelInterface;
import edu.cs.clemson.jymonte.dissertation.sim.cost.CompositeCost;
import edu.cs.clemson.jymonte.dissertation.sim.cost.CostInterface;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.FailureModelInterface;
import edu.cs.clemson.jymonte.dissertation.sim.process.result.PeriodResult;
import edu.cs.clemson.jymonte.dissertation.sim.process.result.ProcessResult;
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
	private FailureModelInterface failureModel;

	/* Linked List Implementation */
	
	private PeriodResult result;
	
	private Period nextPeriod = null;
	
	public void addPeriod(Period p) {
		if(nextPeriod == null)
			nextPeriod = p;
		else
			nextPeriod.addPeriod(p);
	}
	
	public ProcessResult simulate(int currentPeriod, ProcessResult pr) {
		//Check to see if this is first period
		//If so, initialize ProcessResult holder
		if(pr == null) {
			pr = new ProcessResult(currentPeriod);
			//Iterate over each period
			this.computeNetValue(currentPeriod, null);
		} else
			this.computeNetValue(currentPeriod, pr.getLastPeriodResult());
		pr.addResult(this.result);
		//See if next period exists.  if so, run simulation, passing in carryOut for each time period.
		if(nextPeriod != null)
			pr = nextPeriod.simulate(currentPeriod, pr);
		return pr;
	}

	private PeriodResult computeNetValue2(int currentPeriod, PeriodResult previousPeriodResult) {
		//Delcare local variables to hold results from this period simulation
		double finalCost = 0.0, finalValue = 0.0, expectedValue, netValue;
		double valueCarryOut = 0.0, valueCarryIn = 0.0;
		double costCarryOut = 0.0, costCarryIn = 0.0;
		boolean failure = false;
		
		//Check to see if we have cost and value carried in from previous period
		if(previousPeriodResult != null){
			valueCarryIn = previousPeriodResult.getValueCarryOut();
			costCarryIn = previousPeriodResult.getCostCarryOut();
		}
		
		//Add in the carried in cost and values
		finalCost += this.cost.evaluate() + costCarryIn;
		finalValue += this.value.evaluate() + valueCarryIn;
		
		double adustedProbability = this.chanceOfSuccessfulImpl.evaluate() * (finalCost / (finalCost - costCarryIn));
		double probability;
		
		return null;
		
	}
	
	public PeriodResult computeNetValue(int currentPeriod, PeriodResult previousPeriodResult) {
		double finalCost, finalValue, expectedValue, netValue;
		double valueCarryOut = 0.0, valueCarryIn = 0.0;
		double costCarryOut = 0.0, costCarryIn = 0.0;
		boolean failure = false;
		
		if(previousPeriodResult != null){
			valueCarryIn = previousPeriodResult.getValueCarryOut();
			costCarryIn = previousPeriodResult.getCostCarryOut();
		}
			
		finalCost = this.getDiscountedCost(currentPeriod) + (costCarryIn * (1 - this.costReduction)) * Math.exp((-this.risklessDiscountRate * (this.periodOfCompletion - currentPeriod)));
		finalValue = this.getDiscountedValue(currentPeriod) + (valueCarryIn * (1 + this.addedValue)) * Math.exp((-this.risklessDiscountRate * (this.periodOfCompletion - currentPeriod)));  
		double adjustedProbability = this.chanceOfSuccessfulImpl.evaluate() * (finalCost / (finalCost - costCarryIn));
		//TODO: Refactor this shit.  Add in the previous result

		double probability = this.successfulImplementationModel.evaluate(adjustedProbability, 
				currentPeriod, this.getPeriodOfCompletion());
		double rngValue = RNGSingleton.getInstance().getRandomDouble();
		if(rngValue > probability) {
			failure = true;
			expectedValue = finalValue * this.failureModel.computeFailure(probability, rngValue);
			//TODO: Figure out what the failure result is.
			valueCarryOut = ((finalValue - expectedValue) / (1 + this.addedValue)) / Math.exp((-this.risklessDiscountRate * (this.periodOfCompletion - currentPeriod)));
			costCarryOut = (this.cost.evaluate() / this.value.evaluate()) * this.cost.evaluate(); 
		}
		else {
			expectedValue = finalValue;	
		}
		netValue = expectedValue - finalCost;
		//note that the carryOut from last period is the carryIn for this period.
		this.result = new PeriodResult(finalCost, finalValue, expectedValue, netValue, valueCarryOut, valueCarryIn, failure, 
				costCarryOut, costCarryIn, currentPeriod, this.periodOfCompletion);
		return this.result;
	}
	
	public Period getNextPeriod() {
		return nextPeriod;
	}
		
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
			ProbabilityModelVariationStrategy probabilityVariationModel,
			FailureModelInterface failureModel) {
		super();
		this.cost = cost;
		this.value = value;
		this.risklessDiscountRate = risklessDiscountRate;
		this.costReduction = costReduction;
		this.addedValue = addedValue;
		this.periodOfCompletion = periodOfCompletion;
		this.chanceOfSuccessfulImpl = chanceOfSuccessfulImpl;
		this.successfulImplementationModel = probabilityVariationModel;
		this.failureModel = failureModel;
	}

	

	protected int computePeriodDifference(int currentPeriod) {
		return  (this.periodOfCompletion - currentPeriod) > 0 ? this.periodOfCompletion - currentPeriod : 0;
	}

	protected double computeRisklessDiscountFactor(int periodDifference) {
		return Math.pow(Math.E, (-1.0 * this.risklessDiscountRate * periodDifference));
	}

	public Double evaluate(int currentPeriod) {
		return null;//new Double(computeNetValue(currentPeriod));
		
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
	
	public void outputScenarioSettings() {
		Period periodPointer = this;
		System.out.print("Period");
		while(periodPointer.nextPeriod != null) {
			System.out.print("\t" + periodPointer.periodOfCompletion);
			periodPointer = periodPointer.nextPeriod;
		}
		System.out.println();
		periodPointer = this;
		System.out.print("Cost");
		while(periodPointer.nextPeriod != null) {
			System.out.print("\t" + periodPointer.cost.evaluate());
			periodPointer = periodPointer.nextPeriod;
		}
		System.out.println();
		periodPointer = this;
		System.out.print("Value");
		while(periodPointer.nextPeriod != null) {
			System.out.print("\t" + periodPointer.value.evaluate());
			periodPointer = periodPointer.nextPeriod;
		}
		System.out.println();
		periodPointer = this;
		System.out.print("Cost Reduction");
		while(periodPointer.nextPeriod != null) {
			System.out.print("\t" + periodPointer.costReduction);
			periodPointer = periodPointer.nextPeriod;
		}
		System.out.println();
		periodPointer = this;
		System.out.print("Added Value");
		while(periodPointer.nextPeriod != null) {
			System.out.print("\t" + periodPointer.addedValue);
			periodPointer = periodPointer.nextPeriod;
		}
		System.out.println();
		periodPointer = this;
		System.out.print("Riskless Discount Rate");
		while(periodPointer.nextPeriod != null) {
			System.out.print("\t" + periodPointer.risklessDiscountRate);
			periodPointer = periodPointer.nextPeriod;
		}
		System.out.println();
		
	}
	
	public void alternativeScenarioOutput() {
		String delim = "\t";
		System.out.println(this.periodOfCompletion + delim + this.cost + delim + this.value + delim + this.costReduction + delim + this.addedValue + delim + this.risklessDiscountRate);
		if(this.nextPeriod != null)
			this.nextPeriod.alternativeScenarioOutput();
		
	}
	

}
