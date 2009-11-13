package trimatrix.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.db.DAOLayer;
import trimatrix.db.KRoles;
import trimatrix.db.Users;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.UserTracker;

public class LogonLogic {
	public static final Log logger = LogFactory.getLog(LogonLogic.class);
	
	private ServiceLayer serviceLayer;
	private DAOLayer daoLayer;

	public boolean logon(String username, String password) {		
		List<Users> users = daoLayer.getUsersDAO().findByProperty("userName", username);
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
		serviceLayer.getDictionaryService().setMyUser(user);
		// get relevant roles
		Set<KRoles> roles = user.getRoles();
		if(roles == null || roles.isEmpty()) {
			return false;
		}
		// set role		
		List<String> myRoles = new ArrayList<String>();
		for (KRoles role : roles) {
			myRoles.add(role.getKey());
			// set admin explicitly
			
		}
		serviceLayer.getDictionaryService().setMyRoles(myRoles);
		
		UserTracker.addUser(username);
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
    		serviceLayer.getDictionaryService().setLanguage(locale);
    	}
	}

	public ValidValuesBinding getLogonLanguages() {
		return serviceLayer.getValueListBindingService().getVVBinding(Constants.ValueList.LOGONLANGUAGE);
	}
	
	public boolean isUserInitial() {
		return serviceLayer.getDictionaryService().getMyUser().getInitial();
	}
	
	public boolean isUserLocked() {
		return serviceLayer.getDictionaryService().getMyUser().getLocked();
	}
	
	public void changePassword(String password) throws Exception {
		serviceLayer.getDictionaryService().getMyUser().setUserHash(password);
		serviceLayer.getDictionaryService().getMyUser().setInitial(false);
		// set active for at first logon this is set to false by standard
		serviceLayer.getDictionaryService().getMyUser().setActive(true);
		daoLayer.getUsersDAO().merge(serviceLayer.getDictionaryService().getMyUser());
	}
		
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
