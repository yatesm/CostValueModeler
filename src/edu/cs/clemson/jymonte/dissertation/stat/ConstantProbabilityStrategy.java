package edu.cs.clemson.jymonte.dissertation.stat;

public class ConstantProbabilityStrategy implements
		ProbabilityModelVariationStrategy {
	
	public ConstantProbabilityStrategy() {
	}
	
	@Override
	public double evaluate(double baseProbability, int currentPeriod, int completionPeriod) {
		return baseProbability;
	}
	
	public String toXML() {
		String result = "<ConstantProbabilityModel>\n";
		result += "</ConstantProbabilityModel>\n";
		return result;
	}
}
