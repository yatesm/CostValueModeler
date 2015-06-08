package edu.cs.clemson.jymonte.dissertation.sim.periodic;

import edu.cs.clemson.jymonte.dissertation.params.BaseValue;
import edu.cs.clemson.jymonte.dissertation.prob.PoissonProbability;
import edu.cs.clemson.jymonte.dissertation.prob.SimpleProbability;
import edu.cs.clemson.jymonte.dissertation.sim.cost.BaseCost;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.results.SimulationResultAggregator;
import edu.cs.clemson.jymonte.dissertation.stat.LinearProbabilityStrategy;
import edu.cs.clemson.jymonte.dissertation.stat.SlidingPoissonProbabilityStrategy;

public class ExperimentalRunnerOld {
	public static void main(String [] args) {
		System.out.println(Runtime.getRuntime().maxMemory());
		Period pe = new Period(new BaseCost(232631.1429), new BaseValue(225894.2571), 0.05, 0.1, 0.15,
		0, new SimpleProbability(0.8), new SlidingPoissonProbabilityStrategy(new PoissonProbability(4, 4, 0)));
		
		for(int i = 1; i < 7; i++) {
			Period p = new Period(new BaseCost(232631.1429), new BaseValue(225894.2571), 0.05, 0.1, 0.15,
					i, new SimpleProbability(0.8), new SlidingPoissonProbabilityStrategy(new PoissonProbability(4, 4, 0)));
			pe.addPeriod(p);
		}
		
		pe.simulate(750000, 7, null);
		
		SimulationResultAggregator resultList = pe.coalesceResults(null);
		resultList.computeSums(7);
		resultList.outputCarryOut(7);
		resultList.outputNetValue(7);
		resultList.outputSumCarryOut();
		resultList.outputSumNetValue();
		
		
			
	}
	

}

