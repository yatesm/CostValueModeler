package edu.cs.clemson.jymonte.dissertation.stat;

public class LinearProbabilityStrategy implements
		ProbabilityModelVariationStrategy {
	
	private double delta = 0.05;
	
	public LinearProbabilityStrategy(double delta) {
		this.delta = delta;
	}

	@Override
	public double evaluate(double baseProbability, int currentPeriod, int completionPeriod) {
		double returnValue = baseProbability;
		if(completionPeriod > currentPeriod)
			returnValue += (currentPeriod - completionPeriod) * this.delta;
		return returnValue;
	}
	
	public String toXML() {
		String result = "<LinearProbabilityStrategy>\n";
		result += "<delta>" + this.delta + "</delta>\n";
		result += "</LinearProbabilityStrategy>\n";
		return result;		
	}

}
