package edu.cs.clemson.jymonte.dissertation.prob;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class InversePoissonProbability implements ProbabilityModelInterface {
	double lambda;
	double k;
	double scalingFactor;
	
	public InversePoissonProbability(double lambda, double k, double scalingFactor) {
		this.lambda = lambda;
		this.k = k;
		this.scalingFactor = scalingFactor;
	}
	
	@Override
	public double evaluate() {
		return scalingFactor * (1 - ( Math.pow(lambda, k) / CombinatoricsUtils.factorial((int)k )) * Math.exp(-lambda));
	}
	
	public String toXML() {
		String result = "<InversePoissonProbability>\n";
		result += "<lambda>" + lambda + "</lambda>\n";
		result += "<k>" + k + "</k>\n";
		result += "<scalingFactor>" + scalingFactor + "</scalingFactor>\n";
		result += "</InversePoissonProbability>\n";
		return result;
	}
}
