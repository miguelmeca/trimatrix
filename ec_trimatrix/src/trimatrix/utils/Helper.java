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

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.eclnt.jsfserver.resources.ResourceManager;
import org.eclnt.jsfserver.util.HttpSessionAccess;


public class Helper {
	public static String getLiteral(String property) {
		String value = ResourceManager.getRuntimeInstance().readProperty(
				Constants.LITERALS, property);
		return isEmpty(value)?property:value;
	}

	public static String getMessages(String property) {
		String value = ResourceManager.getRuntimeInstance().readProperty(
				Constants.MESSAGES, property);
		return isEmpty(value)?property:value;
	}

	public static boolean isFileInWebRoot(String filename) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext()
				.getContext();
		String path = sc.getRealPath(filename);
		File file = new File(path);
		return file.exists();
	}

	public static String getTemplate(String templateName) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext()
				.getContext();
		String filename = "/templates/" + templateName + "_" + getLanguageServer() + ".template";
		File file = new File(sc.getRealPath(filename));
		if(!file.exists()) {
			filename = "/templates/" + templateName + ".template";
			file = new File(sc.getRealPath(filename));
		}
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream f = new FileInputStream(file);
		f.read(buffer);
		return new String(buffer);
	}

	public static String getTrimatrixUrl() {
		//return "http://cardassia.secitec.net/trimatrix";
		ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		return ectx.getInitParameter("trimatrix.url");
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
			throw new IOException(String.format(Helper.getMessages("read_file_failure"), file.getName()));
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
		return new Locale(HttpSessionAccess.getCurrentRequest().getHeader(Constants.ECLNT_LANGUAGE));
	}

	/**
	 * This method only works in managed beans, in not bound instances it throws a NPE
	 * @return Language which is set on server side
	 */
	public static String getLanguageServer() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		return locale.getLanguage();
	}

	public static void setLanguageServer(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
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

	public static boolean isEmpty(Integer value) {
		return value == null;
	}

	public static boolean isEmpty(Double value) {
		return value == null;
	}

	public static boolean isEmpty(Long value) {
		return value == null || value == 0L;
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
		SimpleDateFormat sd = new SimpleDateFormat(format, FacesContext.getCurrentInstance().getViewRoot().getLocale());
		return sd.format(date);
	}

	public static String formatDate(Date date, String format, Locale locale) {
		SimpleDateFormat sd = new SimpleDateFormat(format, locale);
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

	public static String removeTrailingZeros(String value) {
		while(value.endsWith("0") && value.length()>1) {
			value = value.substring(0, value.length()-1);
		}
		return value;
	}

	// public static final Log logger = LogFactory.getLog("trimatrix");
	// CLog.L logger
	// public static Logger logger = Logger.getLogger("trimatrix");
}
