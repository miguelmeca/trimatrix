package trimatrix.logic;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.db.IUsersDAO;
import trimatrix.db.KLanguages;
import trimatrix.db.KLanguagesDAO;
import trimatrix.db.KRoles;
import trimatrix.db.Users;
import trimatrix.services.TranslationService;
import trimatrix.utils.Dictionary;

public class LogonLogic {
	private IUsersDAO usersDAO;
	private KLanguagesDAO languagesDAO;
	private TranslationService translationService;
	private Dictionary dictionaryService;

	public boolean logon(String username, String password) {
		// TODO use Entities
		List<Users> users = usersDAO.findByProperty("userName", username);
		if(users.size()==0) {
			return false;
		}		
		Users user = users.get(0);
		// check user status
		if (!user.getActive() && !user.getInitial()) {
			return false;
		}
		// check password
		if(!password.equals(user.getUserHash())) {
			return false;
		}
		dictionaryService.setMyUser(user);
		// get relevant roles
		Set<KRoles> roles = user.getRoles();
		if(roles == null || roles.isEmpty()) {
			return false;
		}
		// set role
		for (KRoles role : roles) {
			dictionaryService.setMyRole(role.getKey());
			break;
		}
		// TODO remove
		dictionaryService.setMyRole("admin");
		return true;
	}
	
	public void changeLanguage(String language) {
		if(language != null && language.length() > 0) {
    		Locale locale;
    		if (language.equalsIgnoreCase("en")) {
    			locale = Locale.ENGLISH;
    		} else if (language.equalsIgnoreCase("de")){ 
    			locale = Locale.GERMAN;
    		} else {
    			return;
    		}
    		Dictionary.setLanguage(locale);
    	}
	}
	// TODO use ValueListBindingService
	public ValidValuesBinding getLogonLanguages(String language) {
		ValidValuesBinding vvb = new ValidValuesBinding();
		List<KLanguages> klanguages = languagesDAO.findByProperty("logon", true);
		for (KLanguages klanguage : klanguages) {
			vvb.addValidValue(klanguage.getKey(), translationService.getDescriptionFromDB(klanguage.getKey(), TranslationService.LANGUAGES, language));
		}
		return vvb;
	}
	
	public boolean isUserInitial() {
		return dictionaryService.getMyUser().getInitial();
	}
	
	public boolean isUserLocked() {
		return dictionaryService.getMyUser().getLocked();
	}
	
	public void changePassword(String password) {
		dictionaryService.getMyUser().setUserHash(password);
		dictionaryService.getMyUser().setInitial(false);
		usersDAO.merge(dictionaryService.getMyUser());
	}
	
	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	public void setLanguagesDAO(KLanguagesDAO languagesDAO) {
		this.languagesDAO = languagesDAO;
	}
	
	public void setTranslationService(TranslationService translationService) {
		this.translationService = translationService;
	}	

	public void setDictionaryService(Dictionary dictionary) {
		this.dictionaryService = dictionary;
	}

}
