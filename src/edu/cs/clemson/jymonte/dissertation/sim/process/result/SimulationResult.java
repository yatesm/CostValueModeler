package edu.cs.clemson.jymonte.dissertation.sim.process.result;

import java.util.ArrayList;
import java.util.List;

public class SimulationResult {
	List<TimePeriodResult> timePeriodResultList = new ArrayList<TimePeriodResult>();
	
	public void addTimePeriodResult(TimePeriodResult tp) {
		timePeriodResultList.add(tp);
	}
	
	public TimePeriodResult get(int i) {
		TimePeriodResult tpResultRetValue = null;
		if(i < timePeriodResultList.size())
			tpResultRetValue = this.timePeriodResultList.get(i);
		return tpResultRetValue;
	}

	public int size() {
		return timePeriodResultList.size();
	}
	
	
}
