package edu.cs.clemson.jymonte.dissertation.sim.cost;

import java.util.ArrayList;
import java.util.List;

public class CompositeCost implements CostInterface {
	
	List<CostInterface> costs;
	
	public CompositeCost() {
		this.costs = new ArrayList<CostInterface>();
	}
	
	public void addCost(CostInterface cost) {
		costs.add(cost);
	}
	
	@Override
	public double evaluate() {
		double result = 0.0;
		for(CostInterface cost : costs) {
			result += cost.evaluate();
		}
		return result;
	}

}
