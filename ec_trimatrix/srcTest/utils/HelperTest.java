package utils;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.utils.Helper;

public class HelperTest {
	@Test
	public void testCalculateDuration() {
		String start = "05:20";
		String duration = "04:55";
		String end = Helper.calculateDuration(start, duration, false, false);
		Assert.assertEquals("10:15", end);
		
		start = "05:20";
		duration = "95:55";
		end = Helper.calculateDuration(start, duration, false, false);
		Assert.assertEquals("99:15", end);
		
		start = "01:05:20";
		duration = "00:21";
		end = Helper.calculateDuration(start, duration, true, true);
		Assert.assertEquals("01:04:59", end);
		start = "01:05:20";
		duration = "01:00:21";
		end = Helper.calculateDuration(start, duration, true, false);
		Assert.assertEquals("04:59", end);
		
		start = "01:05:20";
		duration = "1:55:21";
		end = Helper.calculateDuration(start, duration, false, true);
		Assert.assertEquals("03:00:41", end);
	}
	
	@Test
	public void testCalucaltePercentage() {
		Assert.assertEquals("02:00", Helper.getTimeByPercentage("01:00", 200));
		Assert.assertEquals("01:00", Helper.getTimeByPercentage("01:00", 100));
		Assert.assertEquals("00:45", Helper.getTimeByPercentage("01:00", 75));
		Assert.assertEquals("00:30", Helper.getTimeByPercentage("01:00", 50));
		Assert.assertEquals("00:00", Helper.getTimeByPercentage("01:00", 0));
		
		Assert.assertEquals("01:02:04", Helper.getTimeByPercentage("00:31:02", 200));
		Assert.assertEquals("00:45:30", Helper.getTimeByPercentage("01:31:00", 50));
		Assert.assertEquals("01:11:11", Helper.getTimeByPercentage("01:11:11", 100));
		Assert.assertEquals("00:00:00", Helper.getTimeByPercentage("00:31:00", 0));
	}
}