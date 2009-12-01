package utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Test;

import trimatrix.logic.helper.Limit;
import trimatrix.logic.helper.Split;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;

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
		
		// Splits
		Split[] splits = new Split[3];
		splits[0] = new Split("00:00", 5);
		splits[1] = new Split("00:30", 6);
		splits[2] = new Split("00:50", 7);
		JSONArray jsonArraySplit = (JSONArray)JSONSerializer.toJSON(splits);
		String strSplits = jsonArraySplit.toString();
		System.out.println(strSplits);
		JsonConfig configSplits = new JsonConfig();
		configSplits.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		//configSplits.setRootClass(Split.class);
		Object[] splits2 = (Object[]) JSONSerializer.toJava(jsonArraySplit, configSplits);
		Assert.assertEquals(splits.length, splits2.length);
		for(Object obj : splits) {
			Split split = (Split)obj;
			System.out.println((Integer)split.getStrokes() + " - " + split.getTime());
		}
		
		// Limits		
		Marshaller<Limit> limitMarshaller = TwoLattes.createMarshaller(Limit.class);
		List<Limit> limits = new ArrayList<Limit>(2);
		limits.add(new Limit("W20", new Double[] {new Double(2), 2.2d}));
		limits.add(new Limit("W30", new Double[] {2.1d, 3.2d}));
		Json.Array array = limitMarshaller.marshallList(limits);
		String strLimits = array.toString();
		System.out.println(strLimits);
		Json.Array array2 = (Json.Array)Json.fromString(strLimits);
		List<Limit> limits2 = limitMarshaller.unmarshallList(array2);
		Assert.assertEquals(limits.size(), limits2.size());				
	}	
}
