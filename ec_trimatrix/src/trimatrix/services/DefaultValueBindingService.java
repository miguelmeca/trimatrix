package trimatrix.services;

import java.util.List;

import trimatrix.db.DAOLayer;
import trimatrix.db.UserDefaults;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Helper;

public class DefaultValueBindingService {
	private Dictionary dictionaryService;
	private DAOLayer daoLayer;

	public String getDVBinding(String key) {
		String[] values = ((String)key).split(Constants.COLON);
		UserDefaults userDefault = new UserDefaults();
		userDefault.setUserId(dictionaryService.getMyUser().getId());
		userDefault.setField(values[0]);
		if(values.length==2) {
			userDefault.setDependency(values[1]);
		}
		List<UserDefaults> userDefaults = daoLayer.getUserDefaultsDAO().findByExample(userDefault);
		if(Helper.isEmpty(userDefaults)) return null;
		return userDefaults.get(0).getValue();
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
