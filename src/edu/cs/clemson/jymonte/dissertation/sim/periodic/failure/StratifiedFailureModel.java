package edu.cs.clemson.jymonte.dissertation.sim.periodic.failure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StratifiedFailureModel implements FailureModelInterface {
	private List<FailureStrata> failureList = new ArrayList<FailureStrata>();
	public StratifiedFailureModel() {
		super();
	}
	
	public void addStrata(FailureStrata strata) {
		this.failureList.add(strata);
		Collections.sort(failureList, new FailureStrataComparator());
	}

	@Override
	public double computeFailure(double probability, double rngValue) {
		double failureCoeffRetResult = 0.0;
		double prDifference = rngValue - probability;
		if(prDifference < 0)
			return 1;
		double runningDiscount = 0.0;
		for(FailureStrata fs : failureList) {
			runningDiscount += fs.getFailureRange();
			//System.out.println("Checking for " + (Math.floor((prDifference * 100))) + " <= " + (Math.floor(((1 - probability) * runningDiscount* 100))));
			//if(Math.floor(prDifference * 100) <= Math.floor(((1 - probability) * runningDiscount) * 100)) {
			if(prDifference * 100 <= ((1 - probability) * runningDiscount) * 100) {
			//	System.out.println("TRUE!");
				failureCoeffRetResult = fs.getFailureCoeff();
				break;
			}
//			else {
//				System.out.println("FALSE");
//			}
		}		
		return failureCoeffRetResult;
	}

	public void printStrata() {
		for(FailureStrata fs : failureList)
			System.out.println(fs);
	}
}
