package utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.aspectj.apache.bcel.generic.Type;
import org.junit.Test;

import trimatrix.utils.Constants;

public class JSONArrayToListTest {

	@Test
	public void testJSON() {
		String str = "[1.33,null,2,4.58]";  
		JsonConfig config = new JsonConfig();
		//config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		config.setRootClass(Double.TYPE);	
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(str);			
		List<Double> output = (List<Double>) JSONSerializer.toJava(jsonArray, config);  
 		Assert.assertEquals(1.33d, output.get(0));
 		Assert.assertEquals(2d, jsonArray.getDouble(2));
		
		List<Double> input = new ArrayList<Double>();
		input.add(new Double(1.34));
		input.add(new Double(1.51));
		input.add(new Double(2));
		input.add(new Double(4.59));	
		JSONArray jsonArray2 = (JSONArray)JSONSerializer.toJSON(input);
		Assert.assertEquals("[1.34,1.51,2,4.59]", jsonArray2.toString());	
		Assert.assertEquals(2d, jsonArray2.get(2));			
	}
}
