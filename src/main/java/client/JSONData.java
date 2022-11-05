package client;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Attribute;
import model.AttributeValue;
import java.util.ArrayList;
import model.DataMatrix;
import model.Item;

public class JSONData {

	private DataMatrix dataMatrix;
	public static String serviceURL = "http://localhost:8080/quiz2";

	private  void generateRandomMatrix() {

		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("A1", 0.8, false));
		attributes.add(new Attribute("A2", 0.1, true));
		attributes.add(new Attribute("A3", 0.1, true));

		List<Item> items = new ArrayList<>();

		List<AttributeValue> attributeValues1 = new ArrayList<>();
		for (int j = 0, i = 1; j < 3; j++) {
			attributeValues1.add(new AttributeValue(attributes.get(j), i + j));
		}
		items.add(new Item("Item1", attributeValues1));

		List<AttributeValue> attributeValues2 = new ArrayList<>();
		for (int j = 0, i = 2; j < 3; j++) {
			attributeValues2.add(new AttributeValue(attributes.get(j), i + j));
		}
		items.add(new Item("Item2", attributeValues2));

		List<AttributeValue> attributeValues3 = new ArrayList<>();
		for (int j = 0, i = 2; j < 3; j++) {
			attributeValues3.add(new AttributeValue(attributes.get(j), i + j));
		}
		items.add(new Item("Item3", attributeValues3));

		double[][] rawValues = new double[items.size()][attributes.size()];
		for (int i = 0; i < items.size(); i++) {
			for (int j = 0; j < attributes.size(); j++) {
				
				rawValues[i][j] = items.get(i).getAttributeValues().get(j).getValue();
			}
		}
		dataMatrix = new DataMatrix(items, attributes, rawValues);
	}

	public  void main() throws JsonProcessingException {
		generateRandomMatrix();
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(dataMatrix);
		System.out.println(jsonInString);
		
	}

}
