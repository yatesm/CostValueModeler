package edu.cs.clemson.jymonte.dissertation.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.cs.clemson.jymonte.dissertation.params.BaseValue;
import edu.cs.clemson.jymonte.dissertation.prob.PoissonProbability;
import edu.cs.clemson.jymonte.dissertation.prob.ProbabilityModelInterface;
import edu.cs.clemson.jymonte.dissertation.sim.Period;
import edu.cs.clemson.jymonte.dissertation.sim.Project;
import edu.cs.clemson.jymonte.dissertation.sim.cost.BaseCost;
import edu.cs.clemson.jymonte.dissertation.stat.ProbabilityModelVariationStrategy;

/**
 * @author YatesDisgrace
 *
 */
/**
 * @author YatesDisgrace
 *
 */
public class InputXML {
	
	SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	SAXParser parser;
	DefaultHandler handler = new SAXHandler();
	
	
	/**
	 * @param fileName
	 * @return
	 */
	public Project inputSimulationXML(String fileName) {
		try {
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException e1) {
			System.err.println("Could not instantiate SAX Parser!");
			e1.printStackTrace();
			return null;
		} catch (SAXException e1) {
			System.err.println("SAXException encountered, bailing");
			e1.printStackTrace();
			return null;
		}
		try {
			parser.parse(fileName, handler);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Project)((SAXHandler) handler).project;
	}

	
	class SAXHandler extends DefaultHandler {
		private Project project = null;
		private Period period = null;
		private ProbabilityModelVariationStrategy probStrat = null;
		private ProbabilityModelInterface probMod = null;
		
		String content = null;
		
		
		public Project getProject() {
			return project;
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			switch(qName) {
				case "Project":
					project = new Project();
					break;
				case "Period":
					break;
				case "PoissonProbability":
					probMod = new PoissonProbability();
					break;
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch(qName) {
				case "period":
					project.addPeriodToProject(period);
					break;
				case "value":
					double value = Double.valueOf(content);
					period.setValue(new BaseValue(value));
					break;
				case "cost":
					double cost = Double.valueOf(content);
					period.setCost(new BaseCost(cost));
					break;
				case "valueAddition":
					double valueAdded = Double.valueOf(content);
					period.setAddedValue(valueAdded);
					break;
				case "costReduction":
					double costReduction = Double.valueOf(content);
					period.setCostReduction(costReduction);
					break;
				case "discountRate":
					double discountRate = Double.valueOf(content);
					period.setRisklessDiscountRate(discountRate);
					break;
				case "periodOfCompletion":
					int periodOfCompletion = Integer.valueOf(content);
					period.setPeriodOfCompletion(periodOfCompletion);
					break;
				case "k":
					double k = Double.valueOf(content);
					//(PoissonProbability)(probMod).setK(k);
					break;
				case "scalingFactor":
					double scalingFactor = Double.valueOf(content);
					//(PoissonProbability) (probMod).setScalingFactor(scalingFactor);
					break;
				case "probabilityModel":
					period.setProbabilityModel(probMod);
					break;
			}
		}
		
		public void characters(char [] ch, int start, int length) {
			content = String.copyValueOf(ch, start, length).trim();
		}
	}
}
