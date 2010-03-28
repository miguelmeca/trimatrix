package trimatrix.logic;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import trimatrix.db.DAOLayer;
import trimatrix.db.UserDefaults;
import trimatrix.db.UserPreferences;
import trimatrix.db.Users;
import trimatrix.db.ZonesDefinition;
import trimatrix.entities.EntityLayer;
import trimatrix.services.ServiceLayer;

public class PreferencesLogic {
	public static final Log logger = LogFactory.getLog(PreferencesLogic.class);
	private ServiceLayer serviceLayer;
	private DAOLayer daoLayer;
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

	public UserDefaults createUserDefault() {
		String id = UUID.randomUUID().toString();
    	return new UserDefaults(id, serviceLayer.getDictionaryService().getMyUser().getId(),null);
	}

	public List<UserDefaults> getUserDefaults() {
		UserDefaults example = new UserDefaults();
		example.setUserId(serviceLayer.getDictionaryService().getMyUser().getId());
		return daoLayer.getUserDefaultsDAO().findByExample(example);
	}

	public boolean deleteDefaults(Set<String> deletedIds) {
		try {
			for(String id : deletedIds) {
				daoLayer.getUserDefaultsDAO().delete(daoLayer.getUserDefaultsDAO().findById(id));
			}
		} catch (Exception ex) {
			logger.error("Error deleting user defaults : " + ex.toString());
			return false;
		}
		return true;
	}

	public boolean updateDefault(UserDefaults userDefault) {
		try {
			daoLayer.getUserDefaultsDAO().merge(userDefault);
		} catch (Exception ex) {
			logger.error("Error changing user default : " + ex.toString());
			return false;
		}
		return true;
	}


	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}
}
