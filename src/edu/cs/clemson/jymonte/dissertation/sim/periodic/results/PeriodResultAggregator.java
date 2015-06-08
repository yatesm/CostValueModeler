package edu.cs.clemson.jymonte.dissertation.sim.periodic.results;

import java.util.ArrayList;
import java.util.List;

public class PeriodResultAggregator {
	List<PeriodResult> periodResultList;

	/**
	 * @param periodResultList
	 */
	public PeriodResultAggregator(List<PeriodResult> periodResultList) {
		super();
		this.periodResultList = periodResultList;
	}

	public PeriodResultAggregator() {
		this.periodResultList = new ArrayList<PeriodResult>();
	}

	/**
	 * @return the periodResultList
	 */
	public List<PeriodResult> getPeriodResultList() {
		return periodResultList;
	}

	/**
	 * @param periodResultList the periodResultList to set
	 */
	public void setPeriodResultList(List<PeriodResult> periodResultList) {
		this.periodResultList = periodResultList;
	}
	
	public void addPeriodResult(PeriodResult p) {
		this.periodResultList.add(p);
	}
	
}
