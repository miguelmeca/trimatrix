package utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Test;

public class JSONArrayToListTest {

	@Test
	public void testJSON() {
		String str = "[1.33, 1.50, 2.0, 4.58]";  
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON( str );  
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_LIST);
		jsonConfig.setRootClass(Double.TYPE);
		List<Double> output = (List<Double>) JSONSerializer.toJava( jsonArray, jsonConfig );  
		Assert.assertEquals(1.33d, output.get(0));
		
		List<Double> input = new ArrayList<Double>();
		input.add(new Double(1.34));
		input.add(new Double(1.51));
		input.add(new Double(2.01));
		input.add(new Double(4.59));
		JSONArray jsonArray2 = (JSONArray)JSONSerializer.toJSON(input);
		Assert.assertEquals("[1.34,1.51,2.01,4.59]", jsonArray2.toString());	
	}
}
