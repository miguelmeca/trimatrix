package trimatrix.logic;

import trimatrix.db.UserPreferences;
import trimatrix.db.Users;
import trimatrix.entities.EntityLayer;
import trimatrix.services.ServiceLayer;

public class PreferencesLogic {
	private ServiceLayer serviceLayer;
	private EntityLayer entityLayer;

	public void savePreferences(UserPreferences preferences) throws Exception {
		serviceLayer.getDictionaryService().getMyUser().setPreferences(preferences);
		Users user = (Users)entityLayer.getUserEntity().save(serviceLayer.getDictionaryService().getMyUser());
		serviceLayer.getDictionaryService().setMyUser(user);		
	}

	public UserPreferences getPreferences() {
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		if(preferences==null) preferences = new UserPreferences(serviceLayer.getDictionaryService().getMyUser().getId());
		return preferences;
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}
}
