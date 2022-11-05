package model;

import java.util.List;

/*"include  the  alternative  names  and  the  relative  WSM  performance  scores,  ranking  of  each \n"
+ "alternative,  average  performance  score,  and  standard  deviation  of  performance  scores."
*/

public class MCDAResponse {
	List<ScoredItem> items;
	double averageScore;
	double standardDeviation;
	/**
	 * @param items
	 * @param averageScore
	 * @param standardDeviation
	 */
	public MCDAResponse(List<ScoredItem> items, double averageScore, double standardDeviation) {
		this.items = items;
		this.averageScore = averageScore;
		this.standardDeviation = standardDeviation;
	}
	/**
	 * @return the items
	 */
	public List<ScoredItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<ScoredItem> items) {
		this.items = items;
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
	 * @return the standardDeviation
	 */
	public double getStandardDeviation() {
		return standardDeviation;
	}
	/**
	 * @param standardDeviation the standardDeviation to set
	 */
	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

}
