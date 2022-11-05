/**
 * 
 */
package model;

import java.util.List;

/**
 * @author sikha
 *
 */
public class Item {

	private String name;
	private List<AttributeValue> attributeValues;
	//private double calculatedPerformanceScore;
	
	
	/**
	 * @param name
	 * @param attributeValues
	 * @param calculatedPerformanceScore
	 */
	public Item(String name, List<AttributeValue> attributeValues) {
		this.name = name;
		this.attributeValues = attributeValues;

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
	 * @return the attributeValues
	 */
	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}


	/**
	 * @param attributeValues the attributeValues to set
	 */
	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}



	
	
	
}
