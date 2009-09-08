package utils;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.utils.Helper;

public class HelperTest {
	@Test
	public void testCalculateDuration() {
		String start = "05:20";
		String duration = "04:55";
		String end = Helper.calculateDuration(start, duration);
		Assert.assertEquals("10:15", end);
		
		start = "5:20";
		duration = "95:55";
		end = Helper.calculateDuration(start, duration);
		Assert.assertEquals("99:15", end);
	}
}
