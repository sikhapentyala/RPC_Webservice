package controllers;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import model.TOPSISDataMatrix;
import model.Item;
import model.ScoredItem;
import model.MCDAResponse;

public class TopsisComputation {

	private TOPSISDataMatrix topsisDataMatrix;
	private int numberOfAttributes;
	private int numberOfItems;

	/**
	 * @param topsisDataMatrix
	 */
	public TopsisComputation(TOPSISDataMatrix topsisDataMatrix) {
		this.topsisDataMatrix = topsisDataMatrix;
		this.numberOfItems = topsisDataMatrix.getDataMatrix().getItems().size();
		this.numberOfAttributes = topsisDataMatrix.getDataMatrix().getAttributes().size();
	}

	/**
	 * @return the topsisDataMatrix
	 */
	public TOPSISDataMatrix getTopsisDataMatrix() {
		return topsisDataMatrix;
	}

	/**
	 * @param topsisDataMatrix the topsisDataMatrix to set
	 */
	public void setTopsisDataMatrix(TOPSISDataMatrix topsisDataMatrix) {
		this.topsisDataMatrix = topsisDataMatrix;
	}

	public void computeScores() throws Exception {
		try {
			computeNormalizedMatrix();
			// computedWeightedNormalizedMatrix();
			computeIdealValue();
			computeDistanceFromIdealValues();
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
			double[] computedScores = topsisDataMatrix.getComputedScore();
			double[] sortedComputedScores = computedScores.clone();
			Arrays.sort(sortedComputedScores);
			
			double[] reversed = new double[numberOfItems];
			Arrays.setAll(reversed, i -> sortedComputedScores[numberOfItems - i - 1]);

			HashMap<Double, Integer> rankings = new HashMap<>();
			for (int i = 0; i < reversed.length; i++)
				if (!rankings.containsKey(reversed[i]))
					rankings.put(reversed[i], i);

			for (int i = 0; i < computedScores.length; i++)
				ranks[i] = rankings.get(computedScores[i]);

			topsisDataMatrix.setRank(ranks);

		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computePerformanceScore() throws Exception {
		double[] computedScore = new double[numberOfItems];
		try {
			double[] distancesFromIdealWorst = topsisDataMatrix.getEuclideianDistanceFromIdealWorst();
			double[] distancesFromIdealBest = topsisDataMatrix.getEuclideianDistanceFromIdealBest();
			double average = 0;
			double stdDeviation = 0;

			for (int i = 0; i < numberOfItems; i++) {
				computedScore[i] = distancesFromIdealWorst[i]
						/ (distancesFromIdealBest[i] + distancesFromIdealWorst[i]);
				average += computedScore[i];
			}

			average = average / computedScore.length;
			for (double score : computedScore) {
				stdDeviation += Math.pow(score - average, 2);
			}

			stdDeviation = Math.sqrt(stdDeviation / computedScore.length);

			topsisDataMatrix.setComputedScore(computedScore);
			topsisDataMatrix.setAverageScore(average);
			topsisDataMatrix.setStandardDeviationOfScores(stdDeviation);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computeDistanceFromIdealValues() throws Exception {

		double[] distancesFromIdealBest = new double[numberOfItems];
		double[] distancesFromIdealWorst = new double[numberOfItems];
		try {
			double[] idealBest = topsisDataMatrix.getIdealBestValue();
			double[] idealWorst = topsisDataMatrix.getIdealWorstValue();
			double[][] normalizedMatrix = topsisDataMatrix.getNormalizedValues();

			for (int i = 0; i < numberOfItems; i++) {
				double distanceFromBest = 0;
				double distanceFromWorst = 0;

				for (int j = 0; j < numberOfAttributes; j++) {

					double squareOfDifferenceFromBest = normalizedMatrix[i][j] - idealBest[j]; // subtract from ideal
																								// best
					squareOfDifferenceFromBest = squareOfDifferenceFromBest * squareOfDifferenceFromBest; // calculate
																											// square
					distanceFromBest += squareOfDifferenceFromBest; // sum squares for all criteria

					double squareOfDifferenceFromWorst = normalizedMatrix[i][j] - idealWorst[j]; // subtract from ideal
																									// worst
					squareOfDifferenceFromWorst = squareOfDifferenceFromWorst * squareOfDifferenceFromWorst; // calculate
																												// square
					distanceFromWorst += squareOfDifferenceFromWorst; // sum squares for all criteria
				}

				distancesFromIdealBest[i] = Math.pow(distanceFromBest, 0.5); // square root of sum
				distancesFromIdealWorst[i] = Math.pow(distanceFromWorst, 0.5); // square root of sum
			}

			topsisDataMatrix.setEuclideianDistanceFromIdealBest(distancesFromIdealBest);
			topsisDataMatrix.setEuclideianDistanceFromIdealWorst(distancesFromIdealWorst);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computeIdealValue() throws Exception {

		double[] idealBest = new double[numberOfAttributes];
		double[] idealWorst = new double[numberOfAttributes];
		try {
			double[][] normalizedMatrix = topsisDataMatrix.getNormalizedValues();

			for (int j = 0; j < numberOfAttributes; j++) {
				double minValue = Double.MAX_VALUE;
				double maxValue = 0;

				for (int i = 0; i < numberOfItems; i++) {

					if (normalizedMatrix[i][j] > maxValue) {
						maxValue = normalizedMatrix[i][j];
					}
					if (normalizedMatrix[i][j] < minValue) {
						minValue = normalizedMatrix[i][j];
					}
				}

				// If negative, the ideal best is the min value
				// Otherwise, ideal best is the max value
				if (!topsisDataMatrix.getDataMatrix().getAttributes().get(j).isBeneficiary()) {
					idealBest[j] = minValue;
					idealWorst[j] = maxValue;
				} else {
					idealBest[j] = maxValue;
					idealWorst[j] = minValue;
				}
			}

			topsisDataMatrix.setIdealBestValue(idealBest);
			topsisDataMatrix.setIdealWorstValue(idealWorst);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

	}

	private void computeNormalizedMatrix() throws Exception {

		try {
			double[][] rawValues = topsisDataMatrix.getDataMatrix().getRawValues();
			double[][] normalizedValues = new double[numberOfItems][numberOfAttributes];
			;
			double[] weights = topsisDataMatrix.getDataMatrix().fetchWeightsOfAttributes();

			for (int j = 0; j < numberOfAttributes; j++) {

				double denominatorForNormalization = 0;

				for (int i = 0; i < numberOfItems; i++) {
					denominatorForNormalization += rawValues[i][j] * rawValues[i][j];// sum the squares
				}

				denominatorForNormalization = Math.pow(denominatorForNormalization, 0.5); // square root of the sum

				for (int i = 0; i < numberOfItems; i++) {
					double normalizedValue = rawValues[i][j] / denominatorForNormalization; // divide original value by
																							// the sqrt of the sum

					normalizedValue = normalizedValue * weights[j]; // apply criteria weight
					normalizedValues[i][j] = normalizedValue;
				}
			}

			topsisDataMatrix.setNormalizedValues(normalizedValues);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}
	}

	public MCDAResponse generateResponse() throws Exception {
		MCDAResponse mcdaresponse;
		try {
			List<Item> items = topsisDataMatrix.getDataMatrix().getItems();
			double[] scores = topsisDataMatrix.getComputedScore();
			int[] ranks = topsisDataMatrix.getRank();

			List<ScoredItem> scoredItems = new ArrayList<>();
			for (int i = 0; i < scores.length; i++) {
				scoredItems.add(new ScoredItem(items.get(i), scores[i], ranks[i]));
			}
			
			scoredItems.sort((item1, item2) -> Integer.compare(item1.getRank(), item2.getRank()));

			mcdaresponse = new MCDAResponse(scoredItems, topsisDataMatrix.getAverageScore(),
					topsisDataMatrix.getStandardDeviationOfScores());

		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}
		return mcdaresponse;

	}
}
