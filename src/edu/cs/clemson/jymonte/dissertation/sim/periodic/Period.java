package edu.cs.clemson.jymonte.dissertation.sim.periodic;
import java.util.ArrayList;
import java.util.List;

import edu.cs.clemson.jymonte.dissertation.params.CompositeValue;
import edu.cs.clemson.jymonte.dissertation.params.ValueInterface;
import edu.cs.clemson.jymonte.dissertation.prob.ProbabilityModelInterface;
import edu.cs.clemson.jymonte.dissertation.sim.cost.CompositeCost;
import edu.cs.clemson.jymonte.dissertation.sim.cost.CostInterface;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.results.IterationResult;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.results.PeriodResult;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.results.PeriodResultAggregator;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.results.SimulationResultAggregator;
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
	private double failureCoefficient = 0.9;
	
	/* Linked List Implementation */
	
	private Period nextPeriod = null;
	
	public void addPeriod(Period p) {
		if(nextPeriod == null)
			nextPeriod = p;
		else
			nextPeriod.addPeriod(p);
	}

	//These lists contain the average netValue and average carryOut for this period 
	//of development, given a time period
	private PeriodResultAggregator periodAggregateResults = new PeriodResultAggregator();
	//These arraylists contain all the netValues and carryOuts for each iteration of a simulation
	//of a given development period given a time period.
	List<IterationResult> resultList = new ArrayList<IterationResult>();
	
	
	public void simulate(int numIterations, int maxPeriod, PeriodResultAggregator carryInResults) {
		System.out.print("Starting Period");
		PeriodResult aggregateResults = null;
		//Iterate over each period
		for(int currentPeriod = 0; currentPeriod < maxPeriod; currentPeriod++) {
			//Run simulation for number of periods
			if(currentPeriod <= this.periodOfCompletion) {
				for(int iteration = 0; iteration < numIterations; iteration++) {
					if(carryInResults != null)
						this.computeNetValue(currentPeriod, carryInResults.getPeriodResultList().get(currentPeriod).getAverageCarryOut());
					else
						this.computeNetValue(currentPeriod, 0.0);
				}
				//Compute average results
				aggregateResults = new PeriodResult(resultList);
				aggregateResults.calculateResults();
				this.periodAggregateResults.addPeriodResult(aggregateResults);
				resultList = new ArrayList<IterationResult>();
			}
			else {
				this.periodAggregateResults.addPeriodResult(aggregateResults);
			}
				
//				if(i >= currentPeriod) {
//					for(int iteration = 0; iteration < numIterations; iteration++) {
//						periodResultList.add(iteration, periodsOfDevelopment.get(i).evaluate(currentPeriod));
//					}
//					singlePeriodResults.add(periodResultList);
//				}
//				else {
//					singlePeriodResults.add(allPeriodAggregateResults.get(currentPeriod - 1).get(i));
//				}
				
				
		}
		System.out.println("\tPeriod Finished");
		//See if next period exists.  if so, run simulation, passing in carryOut for each time period.
		if(nextPeriod != null)
			nextPeriod.simulate(numIterations, maxPeriod, periodAggregateResults);
	}
	
	public SimulationResultAggregator coalesceResults(SimulationResultAggregator allPeriodResults) {
		if(allPeriodResults == null)
			allPeriodResults = new SimulationResultAggregator();
		allPeriodResults.addList(this.periodAggregateResults);
		if(this.nextPeriod != null)
			return nextPeriod.coalesceResults(allPeriodResults);
		else
			return allPeriodResults;
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

	public void computeNetValue(int currentPeriod, double averageCarryIn) {
		double finalCost, finalValue, expectedValue, netValue, carryOut;
		finalCost = this.getDiscountedCost(currentPeriod);
		finalValue = this.getDiscountedValue(currentPeriod) + averageCarryIn;
		double probability = this.successfulImplementationModel.evaluate(this.chanceOfSuccessfulImpl.evaluate(), 
				currentPeriod, this.getPeriodOfCompletion());
		if(RNGSingleton.getInstance().getRandomDouble() > probability) {
			expectedValue = finalValue * this.getFailureCoefficient();
			carryOut = finalValue - expectedValue; 
		}
		else {
			expectedValue = finalValue;
			carryOut = 0;		
		}
		netValue = expectedValue - finalCost;
		IterationResult r = new IterationResult(finalCost, finalValue, expectedValue, netValue, carryOut, averageCarryIn);
		resultList.add(r);
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
