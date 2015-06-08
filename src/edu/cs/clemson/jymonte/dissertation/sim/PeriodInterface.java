package edu.cs.clemson.jymonte.dissertation.sim;

import java.io.BufferedWriter;
import java.io.IOException;

public interface PeriodInterface {
	public double computeNetValue(int currentPeriod);
	public void writeOutputXML(BufferedWriter out) throws IOException;
}
