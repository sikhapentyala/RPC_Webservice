package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import model.MCDAResponse;
import model.ScoredItem;

public class PearsonCorrelationComputation {

	private MCDAResponse wsmResponse;
	private MCDAResponse topsisResponse;
	private Map<String, Double> WSMScoreMap;
	private Map<String, Double> TOPSISScoreMap;

	/**
	 * @param wsmResponse
	 * @param topsisResponse
	 */
	public PearsonCorrelationComputation(MCDAResponse wsmResponse, MCDAResponse topsisResponse) {
		this.wsmResponse = wsmResponse;
		this.topsisResponse = topsisResponse;
	}

	public double computePearsonCorrelationCoefficient() throws Exception {
		double correlationValue;
		try {
			if (wsmResponse.getItems().size() != topsisResponse.getItems().size())
				throw new Exception("JSON responses do not match");

			createMaps();

			double[] x = new double[wsmResponse.getItems().size()];
			double[] y = new double[topsisResponse.getItems().size()];
			// double x_average = wsmResponse.getAverageScore();
			// double y_average = wsmResponse.getAverageScore();

			int i = 0;
			for (String itemName : WSMScoreMap.keySet()) {
				x[i++] = WSMScoreMap.get(itemName);
				y[i++] = TOPSISScoreMap.get(itemName);

				i++;
			}

			PearsonsCorrelation pearsonsCorrelationObject = new PearsonsCorrelation();

			correlationValue = pearsonsCorrelationObject.correlation(x, y);

		} catch (Exception ex) {

			System.out.println(ex);
			throw new IOException(ex.getMessage());

		}
		return correlationValue;
	}

	public void createMaps() throws Exception {
		try {
			WSMScoreMap = new HashMap<>();
			TOPSISScoreMap = new HashMap<>();
			ScoredItem item;

			for (int i = 0; i < wsmResponse.getItems().size(); i++) {
				item = wsmResponse.getItems().get(i);
				WSMScoreMap.put(item.getItem().getName(), item.getScore());

				item = topsisResponse.getItems().get(i);
				TOPSISScoreMap.put(item.getItem().getName(), item.getScore());
			}
			if ((WSMScoreMap.size() != TOPSISScoreMap.size()) || WSMScoreMap.isEmpty() || TOPSISScoreMap.isEmpty())
				throw new Exception("Invalid Maps from JSON responses");
		} catch (Exception ex) {

			System.out.println(ex);
			throw new IOException(ex.getMessage());

		}
	}

}
