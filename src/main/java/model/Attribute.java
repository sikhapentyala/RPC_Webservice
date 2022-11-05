package model;

public class Attribute {
	
	private String name;
	private double weight;
	private boolean isBeneficiary;
	/**
	 * @param name
	 * @param weight
	 * @param isBeneficiary
	 */
	public Attribute(String name, double weight, boolean isBeneficiary) {
		this.name = name;
		this.weight = weight;
		this.isBeneficiary = isBeneficiary;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the isBeneficiary
	 */
	public boolean isBeneficiary() {
		return isBeneficiary;
	}
	/**
	 * @param isBeneficiary the isBeneficiary to set
	 */
	public void setBeneficiary(boolean isBeneficiary) {
		this.isBeneficiary = isBeneficiary;
	}
	
	

}
