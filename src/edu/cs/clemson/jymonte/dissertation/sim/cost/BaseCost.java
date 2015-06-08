package edu.cs.clemson.jymonte.dissertation.sim.cost;

public class BaseCost implements CostInterface {

	double cost;
	
	public BaseCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public double evaluate() {
		return cost;
	}
	
	@Override
	public String toString() {
		return "" + cost;
	}
}
