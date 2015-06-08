package edu.cs.clemson.jymonte.dissertation.params;

public class BaseValue implements ValueInterface {
	private double value;
	
	public BaseValue(double value) {
		this.value = value;
	}

	@Override
	public double evaluate() {
		return value;
	}
	
	@Override
	public String toString() {
		return "" + value;
	}
}
