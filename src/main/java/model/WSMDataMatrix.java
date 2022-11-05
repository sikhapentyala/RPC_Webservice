package model;

public class WSMDataMatrix{
	
	private DataMatrix dataMatrix;
	private double[][] normalizedValues;
	private double[] computedScore;
	private int[] rank;
	private double[] minRawValue;
	private double[] maxRawValue;
	private double averageScore;
	private double standardDeviationOfScores;
	/**
	 * @param dataMatrix
	 */
	public WSMDataMatrix(DataMatrix dataMatrix) {
		this.dataMatrix = dataMatrix;
	}
	/**
	 * @return the dataMatrix
	 */
	public DataMatrix getDataMatrix() {
		return dataMatrix;
	}
	/**
	 * @param dataMatrix the dataMatrix to set
	 */
	public void setDataMatrix(DataMatrix dataMatrix) {
		this.dataMatrix = dataMatrix;
	}
	/**
	 * @return the normalizedValues
	 */
	public double[][] getNormalizedValues() {
		return normalizedValues;
	}
	/**
	 * @param normalizedValues the normalizedValues to set
	 */
	public void setNormalizedValues(double[][] normalizedValues) {
		this.normalizedValues = normalizedValues;
	}
	/**
	 * @return the computedScore
	 */
	public double[] getComputedScore() {
		return computedScore;
	}
	/**
	 * @param computedScore the computedScore to set
	 */
	public void setComputedScore(double[] computedScore) {
		this.computedScore = computedScore;
	}
	/**
	 * @return the averageScore
	 */
	public double getAverageScore() {
		return averageScore;
	}
	/**
	 * @param averageScore the averageScore to set
	 */
	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	/**
	 * @return the standardDeviationOfScores
	 */
	public double getStandardDeviationOfScores() {
		return standardDeviationOfScores;
	}
	/**
	 * @param standardDeviationOfScores the standardDeviationOfScores to set
	 */
	public void setStandardDeviationOfScores(double standardDeviationOfScores) {
		this.standardDeviationOfScores = standardDeviationOfScores;
	}
	/**
	 * @return the minRawValue
	 */
	public double[] getMinRawValue() {
		return minRawValue;
	}
	/**
	 * @param minRawValue the minRawValue to set
	 */
	public void setMinRawValue(double[] minRawValue) {
		this.minRawValue = minRawValue;
	}
	/**
	 * @return the maxRawValue
	 */
	public double[] getMaxRawValue() {
		return maxRawValue;
	}
	/**
	 * @param maxRawValue the maxRawValue to set
	 */
	public void setMaxRawValue(double[] maxRawValue) {
		this.maxRawValue = maxRawValue;
	}
	/**
	 * @return the rank
	 */
	public int[] getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int[] rank) {
		this.rank = rank;
	}


}
