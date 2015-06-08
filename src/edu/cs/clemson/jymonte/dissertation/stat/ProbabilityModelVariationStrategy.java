package edu.cs.clemson.jymonte.dissertation.stat;

public interface ProbabilityModelVariationStrategy {
	public double evaluate(double baseProbability, int currentPeriod, int completionPeriod);
	
	public String toXML();
}
