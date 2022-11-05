package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import model.Item;
import model.MCDAResponse;
import model.ScoredItem;
import model.WSMDataMatrix;

public class WSMComputation {

	private WSMDataMatrix wsmDataMatrix;
	private int numberOfAttributes;
	private int numberOfItems;

	/**
	 * @param topsisDataMatrix
	 */
	public WSMComputation(WSMDataMatrix wsmDataMatrix) {
		this.wsmDataMatrix = wsmDataMatrix;
		this.numberOfItems = wsmDataMatrix.getDataMatrix().getItems().size();
		this.numberOfAttributes = wsmDataMatrix.getDataMatrix().getAttributes().size();
	}

	/**
	 * @return the topsisDataMatrix
	 */
	public WSMDataMatrix getTopsisDataMatrix() {
		return wsmDataMatrix;
	}

	/**
	 * @param topsisDataMatrix the topsisDataMatrix to set
	 */
	public void setWSMDataMatrix(WSMDataMatrix wsmDataMatrix) {
		this.wsmDataMatrix = wsmDataMatrix;
	}

	public void computeScores() throws Exception {
		try {
			computeMinMax();
			computeNormalizedMatrix();
			computePerformanceScore();
			computeRankings();
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}
		return;
	}

	private void computeRankings() throws Exception {
		int[] ranks = new int[numberOfItems];
		try {
			double[] computedScores = wsmDataMatrix.getComputedScore();
			double[] sortedComputedScores = computedScores.clone();
			Arrays.sort(sortedComputedScores);

			HashMap<Double, Integer> rankings = new HashMap<>();
			for (int i = 0; i < sortedComputedScores.length; i++)
				if (!rankings.containsKey(sortedComputedScores[i]))
					rankings.put(sortedComputedScores[i], i);

			for (int i = 0; i < computedScores.length; i++)
				ranks[i] = rankings.get(computedScores[i]);

			wsmDataMatrix.setRank(ranks);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computeMinMax() throws Exception {

		try {
			double[][] rawValues = wsmDataMatrix.getDataMatrix().getRawValues();
			double[] minRawValues = new double[numberOfAttributes];
			double[] maRawValues = new double[numberOfAttributes];

			for (int j = 0; j < numberOfAttributes; j++) {

				double min = Double.MAX_VALUE, max = Double.MIN_VALUE;

				for (int i = 0; i < numberOfItems; i++) {
					if (rawValues[i][j] > max)
						max = rawValues[i][j];
					if (rawValues[i][j] < min)
						min = rawValues[i][j];

				}
				minRawValues[j] = min;
				maRawValues[j] = max;

			}
			wsmDataMatrix.setMinRawValue(minRawValues);
			wsmDataMatrix.setMaxRawValue(maRawValues);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computePerformanceScore() throws Exception {
		double[] computedScore = new double[numberOfItems];
		try {

			double average = 0;
			double stdDeviation = 0;
			double[][] normalizedMatrix = wsmDataMatrix.getNormalizedValues();

			for (int i = 0; i < numberOfItems; i++) {
				for (int j = 0; j < numberOfAttributes; j++) {
					computedScore[i] += normalizedMatrix[i][j];
				}

				average += computedScore[i];
			}

			average = average / computedScore.length;
			for (double score : computedScore) {
				stdDeviation += Math.pow(score - average, 2);
			}

			stdDeviation = Math.sqrt(stdDeviation / computedScore.length);

			wsmDataMatrix.setComputedScore(computedScore);
			wsmDataMatrix.setAverageScore(average);
			wsmDataMatrix.setStandardDeviationOfScores(stdDeviation);

		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computeNormalizedMatrix() throws Exception {

		try {
			double[][] rawValues = wsmDataMatrix.getDataMatrix().getRawValues();
			double[][] normalizedValues = new double[numberOfItems][numberOfAttributes];
			double[] weights = wsmDataMatrix.getDataMatrix().fetchWeightsOfAttributes();

			for (int j = 0; j < numberOfAttributes; j++) {

				boolean isBeneficiary = wsmDataMatrix.getDataMatrix().getAttributes().get(j).isBeneficiary();
				double normalizedValue;

				for (int i = 0; i < numberOfItems; i++) {

					if (isBeneficiary)
						normalizedValue = rawValues[i][j] / wsmDataMatrix.getMaxRawValue()[j];
					else
						normalizedValue = wsmDataMatrix.getMinRawValue()[j] / rawValues[i][j];

					normalizedValue = normalizedValue * weights[j]; // apply criteria weight
					normalizedValues[i][j] = normalizedValue;
				}
			}

			wsmDataMatrix.setNormalizedValues(normalizedValues);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}
	}

	public MCDAResponse generateResponse() throws Exception {
		MCDAResponse mcdaresponse;
		try {
			List<Item> items = wsmDataMatrix.getDataMatrix().getItems();
			double[] scores = wsmDataMatrix.getComputedScore();
			int[] ranks = wsmDataMatrix.getRank();

			List<ScoredItem> scoredItems = new ArrayList<>();
			for (int i = 0; i < scores.length; i++) {
				scoredItems.add(new ScoredItem(items.get(i), scores[i], ranks[i]));
			}

			mcdaresponse = new MCDAResponse(scoredItems, wsmDataMatrix.getAverageScore(),
					wsmDataMatrix.getStandardDeviationOfScores());

		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}
		return mcdaresponse;

	}

}
