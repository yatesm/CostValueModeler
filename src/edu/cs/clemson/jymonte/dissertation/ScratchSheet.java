package edu.cs.clemson.jymonte.dissertation;

import java.util.Date;

import org.apache.commons.math3.util.CombinatoricsUtils;

import edu.cs.clemson.jymonte.dissertation.prob.PoissonProbability;
import edu.cs.clemson.jymonte.dissertation.stat.SlidingPoissonProbabilityStrategy;
import edu.cs.clemson.jymonte.dissertation.utils.RNGSingleton;

public class ScratchSheet {
	public static void binomial() {
		int n = 40;
		double p = 0.5;

		for (int k = 0; k < 10; k++) {
			double result = (CombinatoricsUtils.factorial(n)
					/ CombinatoricsUtils.factorial(k) * CombinatoricsUtils
						.factorial(n - k))
					* Math.pow(p, k)
					* Math.pow(1 - p, n - k);
			System.out.println(result);
		}
	}

	public static void generateRNGSeeds() {
		System.out.println("output/rng.txt_" + new Date().getTime());

		RNGSingleton rng = RNGSingleton.getInstance();
		for (int i = 0; i < 16; i++) {
			rng.generateRandomSeeds();
			rng.writeSeeds();
			rng.outputInfo();
			rng.incrementCurrentSeed();
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		SlidingPoissonProbabilityStrategy s = new SlidingPoissonProbabilityStrategy(new PoissonProbability(4, 4, 5));
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println("i=" + i + "\tj=" + j + "\tp=" + s.evaluate(0, i, j));
			}
		}
		//PeriodInterface p = new PeriodFTE(16000, 24000, 0.89, 0.05, 11, 24000, 0.45);
		//p.computeNetValue(3);

		/*
		 * List<List<List<Double>>> results = project.evaluate(0); for(int i =
		 * 0; i < results.size(); i++) { System.out.println("-----------Period "
		 * + i + "----------"); for(int j = 0; j < results.get(i).size(); j++) {
		 * System.out.println("-----------Phase " + j + "----------"); for(int k
		 * = 0; k < results.get(i).get(j).size(); k++) {
		 * System.out.print(results.get(i).get(j).get(k) + "\t"); }
		 * System.out.println(); } }
		 */
		System.out.println("Done");
	}

	public static void poisson() {
		double lambda = 5;
		double result;
		for (int i = 1; i < 10; i++) {
			lambda = i;
			for (int j = 1; j < 20; j++) {
				// result = (Math.exp(-lambda) * Math.pow(lambda, j)) /
				// CombinatoricsUtils.factorial(j);
				result = (Math.pow(lambda, j) / CombinatoricsUtils.factorial(j))
						* Math.exp(-lambda);
				System.out.println(result * 4);
			}
			System.out.println();
		}
	}
}
