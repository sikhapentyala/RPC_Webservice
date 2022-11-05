package model;

import java.util.List;

public class DataMatrix {
	
	private List<Item> items;
	private List<Attribute> attributes;
	private double[][] rawValues;
	/**
	 * @param items
	 * @param attributes
	 * @param numberOfAttributes
	 * @param numberOfItems
	 * @param rawValues
	 */
	public DataMatrix(List<Item> items, List<Attribute> attributes,
			double[][] rawValues) {
		this.items = items;
		this.attributes = attributes;
		this.rawValues = rawValues;
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	/**
	 * @return the attributes
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the rawValues
	 */
	public double[][] getRawValues() {
		return rawValues;
	}
	/**
	 * @param rawValues the rawValues to set
	 */
	public void setRawValues(double[][] rawValues) {
		this.rawValues = rawValues;
	}
	
	public double[] fetchWeightsOfAttributes()
	{
		double[] weights = new double[this.attributes.size()];
		int i = 0;
		for(Attribute a : this.attributes)
			weights[i++] = a.getWeight();
		return weights;
	}
	

}
