package trimatrix.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.eclnt.jsfserver.resources.ResourceManager;
import org.eclnt.jsfserver.util.HttpSessionAccess;

public class Helper {
	public static String getLiteral(String property) {
		return ResourceManager.getRuntimeInstance().readProperty(
				Constants.LITERALS, property);
	}

	public static boolean isFileInWebRoot(String filename) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext()
				.getContext();
		String path = sc.getRealPath(filename);
		File file = new File(path);
		return file.exists();
	}

	public static String readFileAsString(String filePath) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext()
				.getContext();
		filePath = sc.getRealPath(filePath);
		byte[] buffer = new byte[(int) new File(filePath).length()];
		FileInputStream f = new FileInputStream(filePath);
		f.read(buffer);
		return new String(buffer);
	}

	public static boolean isEmailValid(String email) {
		// When not set ok
		if (email == null || email.length() == 0) {
			return true;
		}
		// Set the email pattern string
		Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher matcher = pattern.matcher(email);

		// check whether match is found
		return matcher.matches();
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static String getBlackOrWhite(Color color) {
		if (color == null)
			return Constants.BLACK;
		// get RGB
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		// transform to gray scale and get alpha
		double alpha = red * 0.299 + green * 0.587 + blue * 0.114;

		if (alpha < 186) {
			return Constants.WHITE;
		} else {
			return Constants.BLACK;
		}
	}

	public static String getBlackOrWhite(String color) {
		if (isEmpty(color))
			return Constants.BLACK;
		return getBlackOrWhite(Color.decode(color));
	}

	public static String getValueFromExpression(String expression) {
		return (String) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding(expression).getValue(
						FacesContext.getCurrentInstance());
	}

	/**
	 * Get actual locale from the http request
	 * 
	 * @return Locale
	 */
	public static Locale getLocale() {
		return new Locale(HttpSessionAccess.getCurrentRequest().getHeader(
				Constants.ECLNT_LANGUAGE));
	}

	/**
	 * Get actual width of app screen from http request
	 * 
	 * @return width
	 */
	public static int getWidth() {
		return Integer.valueOf(HttpSessionAccess.getCurrentRequest().getHeader(
				Constants.ECLNT_WIDTH));
	}

	public static String getClientIP() {
		return HttpSessionAccess.getCurrentRequest().getHeader(
				Constants.ECLNT_IP);
	}

	public static HttpSession getSession() {
		return HttpSessionAccess.getCurrentRequest().getSession();
	}

	/**
	 * Get NumberFormat object for formatting locale specific data e.g. date,
	 * double
	 * 
	 * @return NumberFormat
	 */
	public static NumberFormat getNumberFormat() {
		return NumberFormat.getInstance(getLocale());
	}

	/**
	 * Correcting short time input to valid signature If string does'nt match
	 * return null, so value is not changed
	 * 
	 * @param input
	 *            shortened input
	 * @return valid time string
	 */
	public static String correctTimeInput(String input) {
		if (isEmpty(input))
			return null;
		String output = null;
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
				output = "00:00:00";
				break;
			}
		} else {
			output = input;
		}
		// check regex
		if (!Pattern.compile("\\d\\d:[0-5]\\d:[0-5]\\d").matcher(output)
				.matches())
			output = null;
		return output;
	}

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
		// convert to seconds
		Integer secStart = calculateSeconds(start);
		if (secStart == null)
			return null;
		Integer secDuration = calculateSeconds(duration);
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
		return calculateTime(secTotal, hhmmss);
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
		switch (time.length()) {
		case 5: // mm:ss
			if (percentage == 0)
				return "00:00";
			hhmmss = false;
			break;
		case 8: // hh:mm:ss
			if (percentage == 0)
				return "00:00:00";
			hhmmss = true;
			break;
		default:
			return null;
		}
		if (percentage == 100)
			return time;
		int secTime = calculateSeconds(time);
		double seconds = (secTime / 100d) * percentage;
		return calculateTime((int) seconds, hhmmss);
	}

	public static Double getPercentageByTime(String time, String part) {
		int secTime = calculateSeconds(time);
		if (secTime == 0)
			return 0d;
		int secPart = calculateSeconds(part);
		return 100d / secTime * secPart;
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
		Double secTime = (double) calculateSeconds(time);
		if (secTime == null || secTime == 0)
			return null;
		return distance / secTime;
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

	/**
	 * Calculate time of sconds
	 * 
	 * @param seconds
	 *            sconds
	 * @param hhmmss
	 *            use long format hh:mm:ss
	 * @return time
	 */
	public static String calculateTime(Integer seconds, boolean hhmmss) {
		if (seconds == null || seconds == 0) {
			if (hhmmss) {
				return "00:00:00";
			} else {
				return "00:00";
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

	/**
	 * This method enables adding items to wildcard captured lists
	 * 
	 * @param <E>
	 * @param list
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	public static <E> void addHelper(List<E> list, Object item) {
		list.add((E) item);
	}

	/**
	 * Dynamic invocation of objects
	 * 
	 * @param methodName
	 * @param obj
	 * @param args
	 * @return
	 */
	public static Object callMethod(String methodName, Object obj,
			Object... args) throws Exception {
		Class classes[] = new Class[args.length];
		for (int j = 0; j != classes.length; j++) {
			classes[j] = args[j].getClass();
		}
		Class oClass = obj.getClass();
		Method method = oClass.getMethod(methodName, classes);
		return method.invoke(obj, args);
	}

	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	public static Double round(Double d, int decimalPlace) {
		// see the Javadoc about why we use a String in the constructor
		// http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		return sd.format(date);
	}

	public static Object clone(Object copyObject) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(copyObject);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos
					.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object deepCopy = ois.readObject();
			return deepCopy;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	// public static final Log logger = LogFactory.getLog("trimatrix");
	// CLog.L logger
	// public static Logger logger = Logger.getLogger("trimatrix");
}
