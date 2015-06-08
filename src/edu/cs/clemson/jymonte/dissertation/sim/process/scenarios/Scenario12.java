package edu.cs.clemson.jymonte.dissertation.sim.process.scenarios;

import edu.cs.clemson.jymonte.dissertation.params.BaseValue;
import edu.cs.clemson.jymonte.dissertation.prob.ProbabilityModelInterface;
import edu.cs.clemson.jymonte.dissertation.prob.SimpleProbability;
import edu.cs.clemson.jymonte.dissertation.sim.cost.BaseCost;
import edu.cs.clemson.jymonte.dissertation.stat.*;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.FailureModelInterface;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.FailureStrata;
import edu.cs.clemson.jymonte.dissertation.sim.periodic.failure.StratifiedFailureModel;
import edu.cs.clemson.jymonte.dissertation.sim.process.Period;
import edu.cs.clemson.jymonte.dissertation.sim.process.result.ComputedResult;
import edu.cs.clemson.jymonte.dissertation.sim.process.result.ProcessResult;
import edu.cs.clemson.jymonte.dissertation.sim.process.result.SimulationResult;
import edu.cs.clemson.jymonte.dissertation.sim.process.result.TimePeriodResult;

public class Scenario12 {
	public static void main(String [] args) {
		System.out.println(Runtime.getRuntime().maxMemory());
		
		int maxPeriod = 26;
		int maxIterations = 50000;
		Period pe = makeRupScenario(0.1, 0.25, new SimpleProbability(0.8), new LinearProbabilityStrategy(0.02));
		ProcessResult pr = null;
		TimePeriodResult tp = null;
		SimulationResult sr = new SimulationResult();
		for(int currentPeriod = 0; currentPeriod < maxPeriod; currentPeriod++) {
			System.out.println("CurrentPeriod = " + currentPeriod);
			tp = new TimePeriodResult();
			for(int iteration = 0; iteration < maxIterations; iteration++) {
				pr = pe.simulate(currentPeriod, null);
				tp.addProcessResult(pr);
			}
			sr.addTimePeriodResult(tp);
		}
		System.out.println("Simulation Result.size() == " + sr.size());

		//Output stuff
		ComputedResult cr = new ComputedResult(sr, maxIterations, maxPeriod);
		System.out.println("Period\tCost\tValue\tCostReduction\tValueAddition\tRiskless Discount Rate");
		pe.alternativeScenarioOutput();
		cr.computeResults();
		cr.outputPeriodNetValue();
		cr.outputSumNetValue();
		cr.outputCarryInValue();
		cr.outputCarryOutValue();			
	}

	public static FailureModelInterface makeBasicFailureModel() {
		StratifiedFailureModel failureModel = new StratifiedFailureModel();
		failureModel.addStrata(new FailureStrata("minor", 0.5, 0.25));
		failureModel.addStrata(new FailureStrata("major", 0.25, 0.5));
		failureModel.addStrata(new FailureStrata("critical", 0.15, 0.75));
		failureModel.addStrata(new FailureStrata("catastrophic", 0.1, 0.9));
		
		return failureModel;
	}
	
	public static Period makeRupScenario(double costRedux, double addedValue, ProbabilityModelInterface p, ProbabilityModelVariationStrategy pv) {
		FailureModelInterface failureModel = makeBasicFailureModel();
		Period pe = new Period(new BaseCost(27080), new BaseValue(61596), 0.05, costRedux, addedValue, 0, p,
				pv, failureModel);
		pe.addPeriod(new Period(new BaseCost(27080), new BaseValue(61596), 0.05, costRedux, addedValue, 1,p,
				pv, failureModel));
		pe.addPeriod(new Period(new BaseCost(27080), new BaseValue(61596), 0.05, costRedux, addedValue, 2, p,
				pv, failureModel ));
		
		for(int i = 3; i < 11; i++) {
			pe.addPeriod(new Period(new BaseCost(43328), new BaseValue(61596), 0.05, costRedux, addedValue, i, p,
					pv, failureModel ));
		}
		for(int i = 11; i < 24; i++) {
			pe.addPeriod(new Period(new BaseCost(64992), new BaseValue(61596), 0.05, costRedux, addedValue, i, p,
					pv, failureModel ));
		}
		for(int i = 24; i < 27; i++) {
			pe.addPeriod(new Period(new BaseCost(54160), new BaseValue(61596), 0.05, costRedux, addedValue, i, p,
					pv, failureModel ));
		}
		return pe;
	}

}