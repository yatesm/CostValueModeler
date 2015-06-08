package edu.cs.clemson.jymonte.dissertation;

import java.text.DecimalFormat;

import edu.cs.clemson.jymonte.dissertation.params.BaseValue;
import edu.cs.clemson.jymonte.dissertation.prob.PoissonProbability;
import edu.cs.clemson.jymonte.dissertation.prob.SimpleProbability;
import edu.cs.clemson.jymonte.dissertation.sim.Period;
import edu.cs.clemson.jymonte.dissertation.sim.Project;
import edu.cs.clemson.jymonte.dissertation.sim.ResultsAggregator;
import edu.cs.clemson.jymonte.dissertation.sim.Simulation;
import edu.cs.clemson.jymonte.dissertation.sim.cost.BaseCost;
import edu.cs.clemson.jymonte.dissertation.stat.ConstantProbabilityStrategy;
import edu.cs.clemson.jymonte.dissertation.stat.LinearProbabilityStrategy;

public class OSPlatform1 {
	private static double costRedux = 0.1;
	private static double addedValue = 0.15;
	
	public static void main(String [] args) {
		constantLinearSimulation();
		constantConstantSimulation();
		poissonConstantSimulation();
		
	}
	
	public static Project makePoissonConstantProject(double lambda, double scaling) {
		Project p = new Project();
		for(int i = 0; i < 7; i++) {
			Period pe = new Period(new BaseCost(232631.1429), new BaseValue(225894.2571), 0.05, costRedux, addedValue, i,
					new PoissonProbability(lambda, i, scaling), new ConstantProbabilityStrategy());
			p.addPeriodToProject(pe);
		}
		return p;
		//CostInterface cost, ValueInterface value, double risklessDiscountRate, double costReduction,
		//double addedValue, int periodOfCompletion,ProbabilityModelInterface chanceOfSuccessfulImpl,
		//ProbabilityModelVariationStrategy probabilityVariationModel
	}
	
	public static void poissonConstantSimulation() {
		Project [] projects = new Project[10];
		ResultsAggregator [] aggregators = new ResultsAggregator[10];
		Simulation [] simulations = new Simulation[10];
		double scaleFactor = 4.0;
		for(int i = 0; i < 10; i++) {
			projects[i] = makePoissonConstantProject(i, scaleFactor);
			simulations[i] = new Simulation();
			simulations[i].setProject(projects[i]);
			simulations[i].run();
			aggregators[i] = new ResultsAggregator(simulations[i].getSimulationResults());
			aggregators[i].aggregate();
			aggregators[i].aggregateRunningSum();
			System.out.println(projects[i]);
			aggregators[i].outputPeriodValues();
			aggregators[i].outputPeriodSumValues();
			aggregators[i].outputPeriodSumValuesToDisk("results/osplatform1/poisson_constant/sum_results_lambda-" + i + "-scaling-" + scaleFactor + ".csv");
			aggregators[i].outputPeriodValuesToDisk("results/osplatform1/poisson_constant/period_results_lambda-" + i + "-scaling-" + scaleFactor + ".csv");
			
		}			
	}
	
	public static void constantLinearSimulation() {
		Project [] projects = new Project[10];
		ResultsAggregator [] aggregators = new ResultsAggregator[10];
		Simulation [] simulations = new Simulation[10];
		for(int j = 1; j < 11; j++) {
			double delta = j / 100.0;
			for(int i = 0; i < 10; i++) {
				double probability = 1.0 - (i * 0.1);
				
				projects[i] = makeConstantLinearProject(1.0 - (i * 0.1), i / 100.0);
				simulations[i] = new Simulation();
				simulations[i].setProject(projects[i]);
				simulations[i].run();
				aggregators[i] = new ResultsAggregator(simulations[i].getSimulationResults());
				aggregators[i].aggregate();
				aggregators[i].aggregateRunningSum();
				System.out.println(projects[i]);
				aggregators[i].outputPeriodValues();
				aggregators[i].outputPeriodSumValues();
				aggregators[i].outputPeriodSumValuesToDisk("results/osplatform1/constant_linear/sum_results_p-" 
						+ new DecimalFormat("0.00").format(probability) 
						+ "-d-" 
						+ new DecimalFormat("0.00").format(delta) + ".csv");
				aggregators[i].outputPeriodValuesToDisk("results/osplatform1/constant_linear/period_results_p-" + new DecimalFormat(".00").format(probability)+ 
						"-d-" +	new DecimalFormat("0.00").format(delta) + ".csv");
			}
		}
	}
	
	public static Project makeConstantLinearProject(double probability, double delta) {
		Project p = new Project();
		for(int i = 0; i < 7; i++) {
			Period pe = new Period(new BaseCost(232631.1429), new BaseValue(225894.2571), 0.05, costRedux, addedValue,
			i, new SimpleProbability(probability), new LinearProbabilityStrategy(delta));
			p.addPeriodToProject(pe);
		}
		System.out.println("Total Periods for probability " + probability + ": " + p.getTotalPeriods());
		return p;
	}
	
	public static Project makeConstantConstantProject(double probability) {
		Project p = new Project();
		for(int i = 0; i < 7; i++) {
			Period pe = new Period(new BaseCost(232631.1429), new BaseValue(225894.2571), 0.05, costRedux, addedValue,
			i, new SimpleProbability(probability), new ConstantProbabilityStrategy());
			p.addPeriodToProject(pe);
		}
		System.out.println("Total Periods for probability " + probability + ": " + p.getTotalPeriods());
		return p;
		
		//Period(CostInterface cost, ValueInterface value,double risklessDiscountRate, double costReduction,
			//	double addedValue, int periodOfCompletion,
				//ProbabilityModelInterface chanceOfSuccessfulImpl,
				//ProbabilityModelVariationStrategy probabilityVariationModel)
	}
	
	public static void constantConstantSimulation() {
		Project [] projects = new Project[10];
		ResultsAggregator [] aggregators = new ResultsAggregator[10];
		Simulation [] simulations = new Simulation[10];
		for(int i = 0; i < 10; i++) {
			double probability = 1.0 - (i * 0.1);
			projects[i] = makeConstantConstantProject(1.0 - (i * 0.1));
			simulations[i] = new Simulation();
			simulations[i].setProject(projects[i]);
			simulations[i].run();
			aggregators[i] = new ResultsAggregator(simulations[i].getSimulationResults());
			aggregators[i].aggregate();
			aggregators[i].aggregateRunningSum();
			System.out.println(projects[i]);
			aggregators[i].outputPeriodValues();
			aggregators[i].outputPeriodSumValues();
			aggregators[i].outputPeriodSumValuesToDisk("results/osplatform1/constant_constant/sum_results_p-" + new DecimalFormat(".0000").format(probability) + ".csv");
			aggregators[i].outputPeriodValuesToDisk("results/osplatform1/constant_constant/period_results_p-" + new DecimalFormat(".0000").format(probability)+ ".csv");
		}			
	}
}
