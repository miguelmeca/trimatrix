package utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.logic.helper.Limit;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;

public class JSONArrayToListTest {

	@Test
	public void testJSON() {
		// Limits
		Marshaller<Limit> limitMarshaller = TwoLattes.createMarshaller(Limit.class);
		List<Limit> limits = new ArrayList<Limit>(2);
		limits.add(new Limit("W20", new String[] {"00:30", "00:40"},new String[] {"Markus", "00:35"}, new String[] {"Dany", "01:35"}));
		limits.add(new Limit("W30", new String[] {"01:30", "01:40"},new String[] {"Seppo", "01:00"}, new String[] {"Hannes", "01:52"}));
		Json.Array array = limitMarshaller.marshallList(limits);
		String strLimits = array.toString();
		System.out.println(strLimits);
		Json.Array array2 = (Json.Array)Json.fromString(strLimits);
		List<Limit> limits2 = limitMarshaller.unmarshallList(array2);
		Assert.assertEquals(limits.size(), limits2.size());

//		Marshaller<Double> doubleMarshaller = TwoLattes.withType(DoubleType.class).createMarshaller(double.class);
//		List<Double> doubles = new ArrayList<Double>(2);
//		doubles.add(2.1d);
//		doubles.add(1d);
//		Json.Array jsonArrDouble = doubleMarshaller.marshallList(doubles);
//		String strDoubles = jsonArrDouble.toString();
//		System.out.println(strDoubles);
//		Json.Array jsonArrDouble2 = (Json.Array)Json.fromString(strDoubles);
//		List<Double> doubles2 = doubleMarshaller.unmarshallList(jsonArrDouble2);
//		Assert.assertEquals(doubles.size(), doubles2.size());


	}
}
