package trimatrix.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
	public static final String DOCTYPE =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">";

	  public static String headWithTitle(String title) {
	    return(DOCTYPE + "\n" +
	           "<HTML>\n" +
	           "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
	  }

	  /** Read a parameter with the specified name, convert it to an int,
	      and return it. Return the designated default value if the parameter
	      doesn't exist or if it is an illegal integer format.
	  */	  
	  public static int getIntParameter(HttpServletRequest request,
	                                    String paramName,
	                                    int defaultValue) {
	    String paramString = request.getParameter(paramName);
	    int paramValue;
	    try {
	      paramValue = Integer.parseInt(paramString);
	    } catch(NumberFormatException nfe) { // Handles null and bad format
	      paramValue = defaultValue;
	    }
	    return(paramValue);
	  }

	  public static String getCookieValue(Cookie[] cookies,
	                                      String cookieName,
	                                      String defaultValue) {
	    for(int i=0; i<cookies.length; i++) {
	      Cookie cookie = cookies[i];
	      if (cookieName.equals(cookie.getName()))
	        return(cookie.getValue());
	    }
	    return(defaultValue);
	  }
}
