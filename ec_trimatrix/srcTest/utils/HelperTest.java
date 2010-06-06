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

	@Test
	public void testCorrectTimeInput() {
		Assert.assertEquals("00:05:00", Helper.correctTimeInput("5"));
		Assert.assertEquals("00:15:00", Helper.correctTimeInput("15"));
		Assert.assertEquals("01:30:00", Helper.correctTimeInput("130"));
		Assert.assertEquals(null, Helper.correctTimeInput("2390"));
		Assert.assertEquals("01:23:20", Helper.correctTimeInput("12320"));
		Assert.assertEquals("10:23:20", Helper.correctTimeInput("102320"));
		Assert.assertEquals("10:23:20,95", Helper.correctTimeInput("102320,95"));
		Assert.assertEquals("00:20,915", Helper.correctTimeInput2("20,915"));
		Assert.assertEquals("23:20,95", Helper.correctTimeInput2("2320,95"));
	}

	@Test
	public void testRest() {
		Assert.assertEquals("5", Helper.removeTrailingZeros("500"));
		Assert.assertEquals("0", Helper.removeTrailingZeros("00"));
		Assert.assertEquals("0", Helper.removeTrailingZeros("0"));
	}
}
