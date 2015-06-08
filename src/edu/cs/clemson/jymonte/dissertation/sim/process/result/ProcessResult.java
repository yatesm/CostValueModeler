package edu.cs.clemson.jymonte.dissertation.sim.process.result;

import java.util.ArrayList;
import java.util.List;

public class ProcessResult {
	List<PeriodResult> processResultList = new ArrayList<PeriodResult>();
	int period;
	
	public ProcessResult(int period) {
		this.period = period;
	}
	
	public void addResult(PeriodResult result) {
		this.processResultList.add(result);
		
	}
	
	public PeriodResult getLastPeriodResult() {
		return this.processResultList.get(processResultList.size() - 1);
	}
	
	public PeriodResult get(int i) {
		PeriodResult periodResultReturnValue = null; 
		if(i < processResultList.size())
			periodResultReturnValue = processResultList.get(i);
		return periodResultReturnValue;
	}
	
	public int size() {
		return processResultList.size();
	}

}
