package model;

public class ScoredItem {
	Item item;
	//String name;
	double score;
	int rank;
	/**
	 * @param item
	 * @param score
	 * @param rank
	 */
	public ScoredItem(Item item, double score, int rank) {
		this.item = item;
		this.score = score;
		this.rank = rank;
	}
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	

}
