package trimatrix.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import trimatrix.db.Users;

public final class Dictionary {
	// global logger
	public static Logger logger = Logger.getLogger("trimatrix");
	// actual user logged on
	private Users myUser;
	// actual role
	private String myRole;

	public String getLanguage() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		return locale.getLanguage();
	}

	public static void setLanguage(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public static String getValueFromExpression(String expression) {
		return (String) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding(expression).getValue(
						FacesContext.getCurrentInstance());
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

	public Users getMyUser() {
		return myUser;
	}

	public void setMyUser(Users myUser) {
		this.myUser = myUser;
	}

	public String getMyRole() {
		return myRole;
	}

	public void setMyRole(String myRole) {
		this.myRole = myRole;
	}

	public static Dictionary getFromApplicationContext(ApplicationContext ctx) {
		return (Dictionary) ctx.getBean("dictionaryService");
	}
}
