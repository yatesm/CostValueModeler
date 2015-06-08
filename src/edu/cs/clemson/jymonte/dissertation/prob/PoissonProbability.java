package edu.cs.clemson.jymonte.dissertation.prob;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class PoissonProbability implements ProbabilityModelInterface {
	double lambda;
	double k;
	double scalingFactor;
	
	public PoissonProbability(double lambda, double k, double scalingFactor) {
		this.lambda = lambda;
		this.k = k;
		this.scalingFactor = scalingFactor;
	}
	
	public PoissonProbability() {
	}

	@Override
	public double evaluate() {
		return scalingFactor * (( Math.pow(lambda, k) / CombinatoricsUtils.factorial((int)k )) * Math.exp(-lambda));
	}
	
	public String toXML() {
		String result = "<PoissonProbability>\n";
		result += "<lambda>" + lambda + "</lambda>\n";
		result += "<k>" + k + "</k>\n";
		result += "<scalingFactor>" + scalingFactor + "</scalingFactor>\n";
		result += "</PoissonProbability>\n";
		return result;
	}

	/**
	 * @return the lambda
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * @param lambda the lambda to set
	 */
	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	/**
	 * @return the k
	 */
	public double getK() {
		return k;
	}

	/**
	 * @param k the k to set
	 */
	public void setK(double k) {
		this.k = k;
	}

	/**
	 * @return the scalingFactor
	 */
	public double getScalingFactor() {
		return scalingFactor;
	}

	/**
	 * @param scalingFactor the scalingFactor to set
	 */
	public void setScalingFactor(double scalingFactor) {
		this.scalingFactor = scalingFactor;
	}
}
