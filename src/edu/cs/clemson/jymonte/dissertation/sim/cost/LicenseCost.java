package edu.cs.clemson.jymonte.dissertation.sim.cost;

public class LicenseCost implements CostInterface {
	
	private double cost;
	
	public LicenseCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public double evaluate() {
		return cost;
	}

}
