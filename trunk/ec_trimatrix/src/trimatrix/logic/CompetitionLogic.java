package trimatrix.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.CompetitionsScouts;
import trimatrix.db.DAOLayer;
import trimatrix.db.UserPreferences;
import trimatrix.db.Users;
import trimatrix.logic.helper.Limit;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Helper;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;

public class CompetitionLogic {
	public static final Log logger = LogFactory.getLog(CompetitionLogic.class);
	private static final Marshaller<Limit> limitMarshaller = TwoLattes.createMarshaller(Limit.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	private LogicLayer logicLayer;

	public void saveCompetitionScouts(CompetitionsScouts cs) {
		daoLayer.getCompetitionsScoutsDAO().merge(cs);
	}

	public List<String> getCategories() {
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		if(preferences==null || Helper.isEmpty(preferences.getCompetitionCategories())) return Collections.EMPTY_LIST;
		String[] categories = preferences.getCompetitionCategories().split(";");
		return Arrays.asList(categories);
	}
	
	public void addCategoriesToPreferences(String newCategory) {
		if(Helper.isEmpty(newCategory)) return;
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		if(preferences==null) return;
		String strCategories = preferences.getCompetitionCategories();
		if(strCategories==null) {
			preferences.setCompetitionCategories(newCategory);
		} else {
			String[] categories = strCategories.split(";");
			for(String category : categories) {
				if(newCategory.equals(category.trim())) return;
			}
			// category not found append
			preferences.setCompetitionCategories(strCategories + ";" + newCategory);
		}				
		try {
			logicLayer.getPreferencesLogic().savePreferences(preferences);		
		} catch (Exception ex) {
			logger.warn("Error saving preferences (competition adding category)! " + ex.toString());
		}		
	}

	public String buildString(List<Limit> limits) {
		return limitMarshaller.marshallList(limits).toString();
    }

	public List<Limit> getLimits(String limits) {
		if(Helper.isEmpty(limits)) return Collections.EMPTY_LIST;
		Json.Array array = (Json.Array)Json.fromString(limits);
		return limitMarshaller.unmarshallList(array);
	}

	public Map<String, Limit> getLimitsMap(String limits) {
		if(Helper.isEmpty(limits)) return Collections.EMPTY_MAP;
		Map<String, Limit> result = new HashMap<String, Limit>();
		List<Limit> lstLimits = getLimits(limits);
		for(Limit limit:lstLimits) {
			result.put(limit.getCategory(), limit);
		}
		return result;
	}

	public Limit createLimit() {
		return new Limit();
	}

	public Limit createLimit(String category, String[] limits, String[] swim, String[] run, Boolean swimsuit) {
		return new Limit(category, limits, swim, run, swimsuit);
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setLogicLayer(LogicLayer logicLayer) {
		this.logicLayer = logicLayer;
	}	
}
