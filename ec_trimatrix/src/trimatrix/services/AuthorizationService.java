package trimatrix.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import trimatrix.db.DAOLayer;
import trimatrix.structures.SAuthorization;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Role;

public class AuthorizationService {
	public static final Log logger = LogFactory.getLog(AuthorizationService.class);
	private Dictionary dictionaryService;
	private DAOLayer daoLayer;
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}	
	
	public SAuthorization getAuthorization(Entity entity, String id) {
		// admin gets all
		if(dictionaryService.getMyRoles().contains(Role.ADMIN)) {
			return SAuthorization.ALL;
		}
		SAuthorization authorization = null;
		// no admin, check more detailed :-((
		switch (entity.getBase()) {
		case PERSON:
			authorization = handlePerson(id);			
			break;
		}
		if(authorization!=null) return authorization;
		return SAuthorization.NONE;
	}
	
	public SAuthorization handlePerson(String id) {
		// if person, editing oneself always allowed
		if(dictionaryService.getMyPerson().getId().equals(id)) return SAuthorization.ALL;
		return null;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public static AuthorizationService getFromApplicationContext(ApplicationContext ctx) {
		return (AuthorizationService) ctx.getBean("authorizationService");
	}
}
