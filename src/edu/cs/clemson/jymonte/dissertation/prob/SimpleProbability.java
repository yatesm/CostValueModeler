package edu.cs.clemson.jymonte.dissertation.prob;

public class SimpleProbability implements ProbabilityModelInterface {

	private double probability;
	
	public SimpleProbability(double probability) {
		this.probability = probability;
	}
	
	@Override
	public double evaluate() {
		return this.probability;
	}
	
	public String toXML() {
		String result = "<SimpleProbability>\n";
		result += "<probability>" + this.probability + "</probability>";
		result += "</SimpleProbability>\n";
		return result;
	}

}
