package trimatrix.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import trimatrix.db.Persons;
import trimatrix.db.Users;

public class Dictionary {
	
	// TODO put logger in every relevant class
	public static final Log logger = LogFactory.getLog("trimatrix");
	//CLog.L logger
	//public static Logger logger = Logger.getLogger("trimatrix");
	
	// actual user logged on
	private Users myUser;
	// actual role
	private List<String> myRoles;

	public String getLanguage() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		return locale.getLanguage();
	}

	public void setLanguage(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public static String getValueFromExpression(String expression) {
		return (String) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding(expression).getValue(
						FacesContext.getCurrentInstance());
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

	public Users getMyUser() {
		return myUser;
	}

	public void setMyUser(Users myUser) {
		this.myUser = myUser;
	}	

	public Persons getMyPerson() {
		if(myUser==null) {
			return null;
		} else {
			return myUser.getPerson();
		}
	}

	public List<String> getMyRoles() {
		return myRoles;
	}	

	public void setMyRoles(List<String> myRoles) {
		this.myRoles = myRoles;
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

	public static Dictionary getFromApplicationContext(ApplicationContext ctx) {
		return (Dictionary) ctx.getBean("dictionaryService");
	}
}
