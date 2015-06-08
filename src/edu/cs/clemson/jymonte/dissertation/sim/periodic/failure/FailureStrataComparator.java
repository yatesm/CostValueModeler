package edu.cs.clemson.jymonte.dissertation.sim.periodic.failure;

import java.util.Comparator;

public class FailureStrataComparator implements Comparator<FailureStrata> {

	@Override
	public int compare(FailureStrata arg0, FailureStrata arg1) {
		return arg0.compareTo(arg1);
	}

}
