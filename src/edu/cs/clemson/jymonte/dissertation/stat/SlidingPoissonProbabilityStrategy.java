package edu.cs.clemson.jymonte.dissertation.stat;

import edu.cs.clemson.jymonte.dissertation.prob.PoissonProbability;

public class SlidingPoissonProbabilityStrategy implements
		ProbabilityModelVariationStrategy {

	private PoissonProbability p;
		
	/**
	 * @param p
	 */
	public SlidingPoissonProbabilityStrategy(PoissonProbability p) {
		super();
		this.p = p;
	}
	
	public SlidingPoissonProbabilityStrategy(double lambda, double scalingFactor, int period) {
		p = new PoissonProbability(lambda, scalingFactor, period);
	}

	@Override
	public double evaluate(double baseProbability, int currentPeriod, int completionPeriod) {
		int periodShift = completionPeriod > currentPeriod ? completionPeriod - currentPeriod : 1;
		p.setK(periodShift);
		double result = p.evaluate();
		return result;
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
