package edu.cs.clemson.jymonte.dissertation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.cs.clemson.jymonte.dissertation.sim.Project;

/**
 * @author YatesDisgrace
 *
 */
public class OutputXML {
	private BufferedWriter out;
	public OutputXML() { }
	
	/**
	 * @param fileName
	 * @param project
	 */
	public void outputSimulationXML(String fileName, Project project) {
		File outFile = new File(fileName);
		try {
			out = new BufferedWriter(new FileWriter(outFile));
			out.write(project.toXML());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not create outputfile: " + outFile);
			return;
		} finally {
			if(out != null) try { out.close(); } catch(Exception e) {System.err.println("Error closing file"); } finally { }	
		}
	}
}
