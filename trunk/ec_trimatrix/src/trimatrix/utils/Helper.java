package trimatrix.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.resources.ResourceManager;
import org.eclnt.jsfserver.util.HttpSessionAccess;

public class Helper {
	public static String getLiteral(String property) {
		return ResourceManager.getRuntimeInstance().readProperty(Constants.LITERALS,property);
	}

	public static boolean isFileInWebRoot(String filename) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext().getContext();
		String path = sc.getRealPath(filename);			
		File file = new File(path);
		return file.exists();	
	}

	public static boolean isEmailValid(String email) {
		// When not set ok
		if(email==null || email.length()==0) { return true; }
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
	    byte[] bytes = new byte[(int)length];
	
	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
	
	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
	
	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}

	public static String getBlackOrWhite(Color color) {
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

	public static String getValueFromExpression(String expression) {
		return (String) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding(expression).getValue(
						FacesContext.getCurrentInstance());
	}
	
	/**
	 * Get actual locale from the http request
	 * @return Locale
	 */
	public static Locale getLocale() {
		return new Locale(HttpSessionAccess.getCurrentRequest().getHeader(Constants.ECLNT_LANGUAGE));
	}
	
	/**
	 * Get actual width of app screen from http request
	 * @return width
	 */
	public static int getWidth() {
		return Integer.valueOf(HttpSessionAccess.getCurrentRequest().getHeader(Constants.ECLNT_WIDTH));
	}
	
	/**
	 * Get NumberFormat object for formatting locale specific data e.g. date, double
	 * @return NumberFormat
	 */
	public static NumberFormat getNumberFormat() {
		return NumberFormat.getInstance(getLocale());
	}
	
	/**
	 * Calculate duration in Format mm:ss
	 * @param start	startpoint in mm:ss format
	 * @param duration duration in mm:ss format
	 * @return endpoint in mm:ss format
	 */
	public static String calculateDuration(String start, String duration) {		
		if(start==null && duration!=null) return duration;
		if(start!=null && duration==null) return start;
		String[] arrStart = start.split(":");
		String[] arrDuration = duration.split(":");
		// check format mm:ss
		if(arrStart.length<2 || arrDuration.length<2) return null;
		int secStart    = Integer.valueOf(arrStart[0]) * 60 + Integer.valueOf(arrStart[1]);
		int secDuration = Integer.valueOf(arrDuration[0]) * 60 + Integer.valueOf(arrDuration[1]);
		int secTotal = secStart + secDuration;
		int minutes = secTotal / 60;
		int seconds = secTotal % 60;
		// max. 99 minutes
		if(minutes>99) minutes = 99;
		return String.format("%02d:%02d", minutes, seconds);
	}
	
	/**
	 * Calculate percentage of a given time
	 * @param time
	 * @param percentage
	 * @return calculated time
	 */
	public static String percentageOfTime(String time, Integer percentage) {
		if(percentage==0) return "00:00"; 
		if(percentage==100) return time;
		String[] arrTime = time.split(":");
		// check format mm:ss
		if(arrTime.length<2) return null;
		int secTime = Integer.valueOf(arrTime[0]) * 60 + Integer.valueOf(arrTime[1]);
		int secPercent = secTime / 100 * percentage;
		int minutes = secPercent / 60;
		int seconds = secPercent % 60;
		return String.format("%02d:%02d", minutes, seconds);		
	}
	
	/**
	 * This method enables adding items to wildcard captured lists 
	 * @param <E>	
	 * @param list
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	public static <E> void addHelper(List<E> list, Object item) {
	    list.add((E)item);
	}	

	// TODO put logger in every relevant class
	public static final Log logger = LogFactory.getLog("trimatrix");
	//CLog.L logger
	//public static Logger logger = Logger.getLogger("trimatrix");
}
