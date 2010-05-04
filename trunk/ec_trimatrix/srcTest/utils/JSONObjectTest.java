package utils;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.logic.helper.DayInfo;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;

public class JSONObjectTest {
	@Test
	public void testJSONObject() {
		Marshaller<DayInfo> dayInfoMarshaller = TwoLattes.createMarshaller(DayInfo.class);
		DayInfo dayInfo = new DayInfo();
		dayInfo.setDays(false);
		String dayInfoString = dayInfoMarshaller.marshall(dayInfo).toString();
		Json.Object dayInfoObject = (Json.Object)Json.fromString(dayInfoString);
		DayInfo dayInfo2 = dayInfoMarshaller.unmarshall(dayInfoObject);
		Assert.assertEquals(dayInfo.getDays(), dayInfo2.getDays());
	}
}
