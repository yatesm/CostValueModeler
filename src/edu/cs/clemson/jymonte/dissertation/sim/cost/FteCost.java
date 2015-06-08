package edu.cs.clemson.jymonte.dissertation.sim.cost;

public class FteCost implements CostInterface {
	
	private double cost;
	private double costValueRealization;
	
	public FteCost(double cost, double costValueRealization) {
		this.cost = cost;
		this.costValueRealization = costValueRealization;
	}
	
	@Override
	public double evaluate() {
		return this.cost * this.costValueRealization;
	}

}
