package model;

public class AttributeValue {
	
	private Attribute attribute;
	private double value;
	
	/**
	 * @param attribute
	 * @param value
	 */
	public AttributeValue(Attribute attribute, double value) {
		this.attribute = attribute;
		this.value = value;
	}
	/**
	 * @return the attribute
	 */
	public Attribute getAttribute() {
		return attribute;
	}
	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	

}
