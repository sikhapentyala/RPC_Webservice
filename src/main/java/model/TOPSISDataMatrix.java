package model;

public class TOPSISDataMatrix {

	private DataMatrix dataMatrix;
	private double[][] normalizedValues;
	private double[] computedScore;
	private int[] rank;
	private double[] idealBestValue;
	private double[] idealWorstValue;
	private double[] euclideianDistanceFromIdealWorst;
	private double[] euclideianDistanceFromIdealBest;
	private double averageScore;
	private double standardDeviationOfScores;
	/**
	 * @param dataMatrix
	 */
	public TOPSISDataMatrix(DataMatrix dataMatrix) {
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
	 * @return the idealBestValue
	 */
	public double[] getIdealBestValue() {
		return idealBestValue;
	}
	/**
	 * @param idealBestValue the idealBestValue to set
	 */
	public void setIdealBestValue(double[] idealBestValue) {
		this.idealBestValue = idealBestValue;
	}
	/**
	 * @return the idealWorstValue
	 */
	public double[] getIdealWorstValue() {
		return idealWorstValue;
	}
	/**
	 * @param idealWorstValue the idealWorstValue to set
	 */
	public void setIdealWorstValue(double[] idealWorstValue) {
		this.idealWorstValue = idealWorstValue;
	}
	/**
	 * @return the euclideianDistanceFromIdealWorst
	 */
	public double[] getEuclideianDistanceFromIdealWorst() {
		return euclideianDistanceFromIdealWorst;
	}
	/**
	 * @param euclideianDistanceFromIdealWorst the euclideianDistanceFromIdealWorst to set
	 */
	public void setEuclideianDistanceFromIdealWorst(double[] euclideianDistanceFromIdealWorst) {
		this.euclideianDistanceFromIdealWorst = euclideianDistanceFromIdealWorst;
	}
	/**
	 * @return the euclideianDistanceFromIdealBest
	 */
	public double[] getEuclideianDistanceFromIdealBest() {
		return euclideianDistanceFromIdealBest;
	}
	/**
	 * @param euclideianDistanceFromIdealBest the euclideianDistanceFromIdealBest to set
	 */
	public void setEuclideianDistanceFromIdealBest(double[] euclideianDistanceFromIdealBest) {
		this.euclideianDistanceFromIdealBest = euclideianDistanceFromIdealBest;
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
