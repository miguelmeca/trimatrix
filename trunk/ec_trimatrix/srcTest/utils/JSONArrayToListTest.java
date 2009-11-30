package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Test;

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
			System.out.println((Integer)split.strokes + " - " + split.time);
		}
		
		// Limits
		Limit[] limits = new Limit[2];		
		limits[0] = new Limit("W20", new Double[] {new Double(2), 2.2d});
		limits[1] = new Limit("W30", new Double[] {2.1d, 3.2d});
		JSONArray jsonArrayLimit = (JSONArray)JSONSerializer.toJSON(limits);
		String strLimits = jsonArrayLimit.toString();
		System.out.println(strLimits);
		JsonConfig configLimits = new JsonConfig();
		configLimits.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		Object[] limits2 = (Object[]) JSONSerializer.toJava(jsonArrayLimit, configLimits);
		Assert.assertEquals(limits.length, limits2.length);
		for(Object obj : limits) {
			Limit limit = (Limit)obj;
			System.out.println(limit.getCategory() + " - " + limit.getLimits().toString());
		}
		
	}
	
	public class Split implements Serializable {
	  	String time;
	   	int strokes;
		
	   	public Split() { };
	   	
		public Split(String time, int strokes) {
			this.time = time;
			this.strokes = strokes;
		}
			
		public String getTime() {return time;}
		public void setTime(String time) {this.time = time;}
			
		public int getStrokes() {return strokes;}
		public void setStrokes(int strokes) {this.strokes = strokes;}	
	}	
	
	public class Limit {
		String category;
		Double[] limits = new Double[2];	// initialize!!
		
		public Limit() { }
		
		public Limit(String category, Double[] limits) {
			this.category = category;
			if(limits!=null) this.limits = limits;
		}
		
		public Limit(String limit) {
			String[] parts = limit.split(":");
			if(parts.length!=2) return;
			this.category = parts[0];
			String[] strLimits = parts[1].split(",");
			this.limits = new Double[strLimits.length];
			for(int i=0;i<strLimits.length;i++) {   
				try {
					limits[i] = Double.valueOf(strLimits[i]);
			    } catch (NumberFormatException nfe) {
			    	limits[i] = 0d;
			    }       
			}
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Double[] getLimits() {
			return limits;
		}

		public void setLimits(Double[] limits) {
			this.limits = limits;
		}

		@Override
		public String toString() {
			return category + " : " + Arrays.toString(limits);
		}			
	}
}
