package trimatrix.utils;

import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;

import trimatrix.db.Persons;
import trimatrix.db.Users;

public class Dictionary {
	
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
	
	public static Dictionary getFromApplicationContext(ApplicationContext ctx) {
		return (Dictionary) ctx.getBean("dictionaryService");
	}
}
