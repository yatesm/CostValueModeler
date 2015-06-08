package edu.cs.clemson.jymonte.dissertation.params;

import java.util.ArrayList;
import java.util.List;

public class CompositeValue implements ValueInterface {
	List<ValueInterface> values;
	
	public CompositeValue() {
		values = new ArrayList<ValueInterface>();
	}
	
	public void addValue(ValueInterface value) {
		this.values.add(value);
	}
	
	@Override
	public double evaluate() {
		double result = 0.0;
		for(ValueInterface value : values)
			result += value.evaluate();
		return result;
	}

}
