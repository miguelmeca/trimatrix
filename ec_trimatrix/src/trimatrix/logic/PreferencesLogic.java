package trimatrix.logic;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.DAOLayer;
import trimatrix.db.Persons;
import trimatrix.db.UserDefaults;
import trimatrix.db.UserPreferences;
import trimatrix.db.Users;
import trimatrix.entities.EntityLayer;
import trimatrix.logic.helper.DayInfo;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Helper;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;

public class PreferencesLogic {
	public static final Log logger = LogFactory.getLog(PreferencesLogic.class);
	public static final Marshaller<DayInfo> dayInfoMarshaller = TwoLattes.createMarshaller(DayInfo.class);
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

	public UserPreferences getPreferences(String personId) {
		List<Users> users = daoLayer.getUsersDAO().findByProperty("personId", personId);
		if(Helper.isEmpty(users)) return null;
		UserPreferences userPreferences = users.get(0).getPreferences();
		if(userPreferences==null) logger.error("PreferencesLogic->getPreferences: there's no UserPreference object for user " + users.get(0).toString());
		return userPreferences;
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

	public DayInfo getDayInfo(String dayInfoString) {
		if(Helper.isEmpty(dayInfoString)) return new DayInfo();
		Json.Object dayInfoObject = (Json.Object)Json.fromString(dayInfoString);
		return dayInfoMarshaller.unmarshall(dayInfoObject);
	}

	public String getDayInfoString(DayInfo dayInfo) {
		return dayInfoMarshaller.marshall(dayInfo).toString();
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
