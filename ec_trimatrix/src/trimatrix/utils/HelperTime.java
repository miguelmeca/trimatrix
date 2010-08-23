package trimatrix.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;


public class HelperTime {

	/**
	 * Calculate duration in Format mm:ss or hh:mm:ss
	 *
	 * @param start
	 *            startpoint in mm:ss or hh:mm:ss format
	 * @param duration
	 *            duration in mm:ss or hh:mm:ss format
	 * @return endpoint in mm:ss or hh:mm:ss format
	 */
	public static String calculateDuration(String start, String duration,
			boolean subtract, boolean hhmmss) {
		if (start == null && duration != null)
			return duration;
		if (start != null && duration == null)
			return start;
		// convert to seconds with msec
		Integer secStart = HelperTime.calculateSeconds(start);
		if (secStart == null)
			return null;
		Integer secDuration = HelperTime.calculateSeconds(duration);
		if (secDuration == null)
			return null;
		Integer secTotal = null;
		if (subtract) {
			if (secDuration > secStart)
				return null;
			secTotal = secStart - secDuration;
		} else {
			secTotal = secStart + secDuration;
		}
		return HelperTime.calculateTime(secTotal, hhmmss);
	}

	/**
	 * Calculate the speed in m/s
	 *
	 * @param distance
	 *            Distance in m
	 * @param time
	 *            Time in format mm:ss
	 * @return Speed m/s
	 */
	public static Double calculateMeterPerSecond(Integer distance, String time) {
		if(time==null) return 0d;
		Double msecs = 0d;
		// check if there are msecs
		if(time.contains(Constants.COMMA)) {
			String[] splitStr = time.split(Constants.COMMA);
			time = splitStr[0];
			if(NumberUtils.isDigits(splitStr[1])) {
				msecs = Double.valueOf(splitStr[1]);
				if(msecs<10) {
					msecs = msecs / 10;
				} else if(msecs<100) {
					msecs = msecs / 100;
				} else {
					msecs = msecs / 1000;
				}
			}
		}
		Double secTime = (double) HelperTime.calculateSeconds(time) + msecs;
		if (secTime == null || secTime == 0d) return null;
		return distance / secTime;
	}

	public static String addSeconds(String time, Integer seconds) {
		if(seconds==null || seconds == 0) return time;
		Integer secTime = HelperTime.calculateSeconds(time) + seconds;
		return calculateTime(secTime, true);
	}

	public static Double calculateSecondsMsecs(String time) {
		if (time == null) return 0d;
		Double msecs = 0d;
		// check if there are msecs
		if(time.contains(Constants.COMMA)) {
			String[] splitStr = time.split(Constants.COMMA);
			time = splitStr[0];
			if(NumberUtils.isDigits(splitStr[1])) {
				msecs = Double.valueOf(splitStr[1]);
				if(msecs<10) {
					msecs = msecs / 10;
				} else if(msecs<100) {
					msecs = msecs / 100;
				} else {
					msecs = msecs / 1000;
				}
			}
		}
		String[] arrTime = time.split(":");
		// mm:ss
		if (arrTime.length == 2)
			return Double.valueOf(arrTime[0]) * 60
					+ Double.valueOf(arrTime[1]) + msecs;
		// mm:hh:ss
		if (arrTime.length == 3)
			return Double.valueOf(arrTime[0]) * 3600
					+ Double.valueOf(arrTime[1]) * 60
					+ Double.valueOf(arrTime[2]) + msecs;
		// wrong
		return 0d;
	}

	/**
	 * Calculate time of sconds
	 *
	 * @param seconds
	 *            seconds
	 * @param hhmmss
	 *            use long format hh:mm:ss
	 * @return time
	 */
	public static String calculateTime(Integer seconds, boolean hhmmss) {
		if (seconds == null || seconds == 0) {
			if (hhmmss) {
				return Constants.EMPTYTIME;
			} else {
				return Constants.EMPTYTIMESHORT;
			}
		}
		Integer hour = seconds / 3600;
		seconds = seconds % 3600;
		Integer minute = seconds / 60;
		Integer second = seconds % 60;
		if (hhmmss) {
			if (hour > 99)
				hour = 99; // hour max. 99
			return String.format("%02d:%02d:%02d", hour, minute, second);
		} else {
			minute += hour * 60;
			if (minute > 99)
				minute = 99; // minute max. 99
			return String.format("%02d:%02d", minute, second);
		}

	}

	public static String calculateTime(Double seconds, boolean hhmmss) {
		if (seconds == null || seconds == 0d) {
			if (hhmmss) {
				return Constants.EMPTYTIME;
			} else {
				return Constants.EMPTYTIMESHORT;
			}
		}
		String output = null;
		Integer secondsOnly = seconds.intValue();
		Double msecs = seconds - secondsOnly;
		Integer msecsInt = (int)(msecs * 1000);
		String msecsStr = Helper.removeTrailingZeros(msecsInt.toString());
		Integer hour = secondsOnly / 3600;
		secondsOnly = secondsOnly % 3600;
		Integer minute = secondsOnly / 60;
		Integer second = secondsOnly % 60;
		if (hhmmss) {
			if (hour > 99)
				hour = 99; // hour max. 99
			output = String.format("%02d:%02d:%02d", hour, minute, second);
		} else {
			minute += hour * 60;
			if (minute > 99)
				minute = 99; // minute max. 99
			output = String.format("%02d:%02d", minute, second);
		}
		if(!Helper.isEmpty(msecsStr)) output += Constants.COMMA + msecsStr;
		return output;

	}

	/**
	 * Calculate time of sconds
	 *
	 * @param seconds
	 *            seconds
	 * @param msecs
	 *            msecs
	 * @param hhmmss
	 *            use long format hh:mm:ss
	 * @return time
	 */
	public static String calculateTime(Integer seconds, Integer msecs, boolean hhmmss) {
		String time = calculateTime(seconds, hhmmss);
		return time + Constants.COMMA + msecs.toString();
	}

	/**
	 * Correcting short time input to valid signature If string doesn't match
	 * return null, so value is not changed
	 *
	 * @param input shortened input
	 * @return valid time string
	 */
	public static String correctTimeInput(String input, boolean printMsec) {
		if (Helper.isEmpty(input))
			return null;
		String output = null;
		Integer msecs = null;
		// check if there are msecs
		if(input.contains(Constants.COMMA)) {
			String[] splitStr = input.split(Constants.COMMA);
			input = splitStr[0];
			if(NumberUtils.isDigits(splitStr[1])) msecs = Integer.valueOf(splitStr[1]);
		}
		// short input
		if (NumberUtils.isDigits(input)) {
			switch (input.length()) {
			case 1:
				output = "00:0" + input + ":00";
				break;
			case 2:
				output = "00:" + input + ":00";
				break;
			case 3:
				output = "0" + input.substring(0, 1) + ":"
						+ input.substring(1, 3) + ":00";
				break;
			case 4:
				output = "00:" + input.substring(0, 2) + ":"
						+ input.substring(2);
				break;
			case 5:
				output = "0" + input.substring(0, 1) + ":"
						+ input.substring(1, 3) + ":" + input.substring(3);
				break;
			case 6:
				output = input.substring(0, 2) + ":" + input.substring(2, 4)
						+ ":" + input.substring(4);
				break;
			default:
				output = Constants.EMPTYTIME;
				break;
			}
		} else {
			output = input;
		}
		// check regex
		if (!Pattern.compile("\\d\\d:[0-5]\\d:[0-5]\\d").matcher(output).matches())	output = null;
		if(msecs!=null && printMsec) output = output + Constants.COMMA + msecs.toString();
		return output;
	}

	public static String correctTimeInput(String input) {
		return correctTimeInput(input, false);
	}

	public static String correctTimeInputShort(String input, boolean printMsec) {
		if (Helper.isEmpty(input))
			return null;
		String output = null;
		Integer msecs = null;
		// check if there are msecs
		if(input.contains(Constants.COMMA)) {
			String[] splitStr = input.split(Constants.COMMA);
			input = splitStr[0];
			if(NumberUtils.isDigits(splitStr[1])) msecs = Integer.valueOf(splitStr[1]);
		}
		// short input
		if (NumberUtils.isDigits(input)) {
			switch (input.length()) {
			case 1:
				output = "0" + input + ":00";
				break;
			case 2:
				output = "00:" + input;
				break;
			case 3:
				output = "0" + input.substring(0, 1) + ":"
						+ input.substring(1, 3);
				break;
			case 4:
				output = input.substring(0, 2) + ":"
						+ input.substring(2);
				break;
			default:
				output = Constants.EMPTYTIMESHORT;
				break;
			}
		} else {
			output = input;
		}
		// check regex
		if (!Pattern.compile("\\d\\d:[0-5]\\d").matcher(output).matches()) output = null;
		if(msecs!=null && printMsec) output = output + Constants.COMMA + msecs.toString();
		return output;
	}

	public static String correctTimeInputShort(String input) {
		return correctTimeInputShort(input, false);
	}

	/**
	 * Calculate percentage of a given time
	 *
	 * @param time
	 * @param percentage
	 * @return calculated time
	 */
	public static String getTimeByPercentage(String time, Integer percentage) {
		boolean hhmmss;
		Double msecs = 0d;
		// check if there are msecs
		if(time.contains(Constants.COMMA)) {
			String[] splitStr = time.split(Constants.COMMA);
			time = splitStr[0];
			if(NumberUtils.isDigits(splitStr[1])) {
				msecs = Double.valueOf(splitStr[1]);
				if(msecs<10) {
					msecs = msecs / 10;
				} else if(msecs<100) {
					msecs = msecs / 100;
				} else {
					msecs = msecs / 1000;
				}
			}
		}
		switch (time.length()) {
		case 5: // mm:ss
			if (percentage == 0)
				return Constants.EMPTYTIMESHORT;
			hhmmss = false;
			break;
		case 8: // hh:mm:ss
			if (percentage == 0)
				return Constants.EMPTYTIME;
			hhmmss = true;
			break;
		default:
			return null;
		}
		if (percentage == 100) return time;
		double secTime = HelperTime.calculateSeconds(time) + msecs;
		double seconds = (secTime / 100d) * percentage;
		return calculateTime((int) seconds, hhmmss);
	}

	public static Double getPercentageByTime(String time, String part) {
		int secTime = HelperTime.calculateSeconds(time);
		if (secTime == 0)
			return 0d;
		int secPart = HelperTime.calculateSeconds(part);
		return 100d / secTime * secPart;
	}

	/**
	 * Calculate seconds of time
	 *
	 * @param time
	 *            Time in format mm:ss or hh:mm:ss
	 * @return seconds
	 */
	public static Integer calculateSeconds(String time) {
		if (time == null)
			return 0;
		String[] arrTime = time.split(":");
		// mm:ss
		if (arrTime.length == 2)
			return Integer.valueOf(arrTime[0]) * 60
					+ Integer.valueOf(arrTime[1]);
		// mm:hh:ss
		if (arrTime.length == 3)
			return Integer.valueOf(arrTime[0]) * 3600
					+ Integer.valueOf(arrTime[1]) * 60
					+ Integer.valueOf(arrTime[2]);
		// wrong
		return 0;
	}

}
