package utils;

import junit.framework.Assert;

import org.junit.Test;

import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;

public class HelperTest {
	@Test
	public void testCalculateDuration() {
		String start = "05:20";
		String duration = "04:55";
		String end = HelperTime.calculateDuration(start, duration, false, false);
		Assert.assertEquals("10:15", end);

		start = "05:20";
		duration = "95:55";
		end = HelperTime.calculateDuration(start, duration, false, false);
		Assert.assertEquals("99:15", end);

		start = "01:05:20";
		duration = "00:21";
		end = HelperTime.calculateDuration(start, duration, true, true);
		Assert.assertEquals("01:04:59", end);
		start = "01:05:20";
		duration = "01:00:21";
		end = HelperTime.calculateDuration(start, duration, true, false);
		Assert.assertEquals("04:59", end);

		start = "01:05:20";
		duration = "1:55:21";
		end = HelperTime.calculateDuration(start, duration, false, true);
		Assert.assertEquals("03:00:41", end);
	}

	@Test
	public void testCalucaltePercentage() {
		Assert.assertEquals("02:00", HelperTime.getTimeByPercentage("01:00", 200));
		Assert.assertEquals("01:00", HelperTime.getTimeByPercentage("01:00", 100));
		Assert.assertEquals("00:45", HelperTime.getTimeByPercentage("01:00", 75));
		Assert.assertEquals("00:30", HelperTime.getTimeByPercentage("01:00", 50));
		Assert.assertEquals("00:00", HelperTime.getTimeByPercentage("01:00", 0));

		Assert.assertEquals("01:02:04", HelperTime.getTimeByPercentage("00:31:02", 200));
		Assert.assertEquals("00:45:30", HelperTime.getTimeByPercentage("01:31:00", 50));
		Assert.assertEquals("01:11:11", HelperTime.getTimeByPercentage("01:11:11", 100));
		Assert.assertEquals("00:00:00", HelperTime.getTimeByPercentage("00:31:00", 0));
	}

	@Test
	public void testCorrectTimeInput() {
		Assert.assertEquals("00:05:00", HelperTime.correctTimeInput("5"));
		Assert.assertEquals("00:15:00", HelperTime.correctTimeInput("15"));
		Assert.assertEquals("01:30:00", HelperTime.correctTimeInput("130"));
		Assert.assertEquals(null, HelperTime.correctTimeInput("2390"));
		Assert.assertEquals("01:23:20", HelperTime.correctTimeInput("12320"));
		Assert.assertEquals("10:23:20", HelperTime.correctTimeInput("102320"));
		Assert.assertEquals("10:23:20,95", HelperTime.correctTimeInput("102320,95", true));
		Assert.assertEquals("00:20,915", HelperTime.correctTimeInputShort("20,915", true));
		Assert.assertEquals("23:20,95", HelperTime.correctTimeInputShort("2320,95", true));
	}

	@Test
	public void testRest() {
		Assert.assertEquals("5", Helper.removeTrailingZeros("500"));
		Assert.assertEquals("5", Helper.removeTrailingZeros("50"));
		Assert.assertEquals("0", Helper.removeTrailingZeros("00"));
		Assert.assertEquals("0", Helper.removeTrailingZeros("0"));
	}

	@Test
	public void testcalculateSeconds() {
		Assert.assertEquals(119, (int)HelperTime.calculateSeconds("01:59"));
		Assert.assertEquals(1, (int)HelperTime.calculateSeconds("00:01"));
		Assert.assertEquals(1, (int)HelperTime.calculateSeconds("00:01,4"));
		Assert.assertEquals(2, (int)HelperTime.calculateSeconds("00:01,5"));
		Assert.assertEquals(1, (int)HelperTime.calculateSeconds("00:01,499"));
		Assert.assertEquals(3719, (int)HelperTime.calculateSeconds("01:01:59"));
		Assert.assertEquals(3719, (int)HelperTime.calculateSeconds("01:01:59,111"));
		Assert.assertEquals(3720, (int)HelperTime.calculateSeconds("01:01:59,5"));
	}
}
