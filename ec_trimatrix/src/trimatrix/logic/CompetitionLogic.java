package trimatrix.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.Categories;
import trimatrix.db.CategoriesDAO;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.DAOLayer;
import trimatrix.logic.helper.Limit;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

import com.twolattes.json.Entity;
import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;
import com.twolattes.json.Value;

public class CompetitionLogic {
	public static final Log logger = LogFactory.getLog(CompetitionLogic.class);
	private static final Marshaller<Limit> limitMarshaller = TwoLattes.createMarshaller(Limit.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	
	public void saveCompetitionScouts(CompetitionsScouts cs) {
		daoLayer.getCompetitionsScoutsDAO().merge(cs);
	}
	
	public List<Categories> getCategories() {
		 return daoLayer.getCategoriesDAO().findByProperty(CategoriesDAO.SCOUTID, serviceLayer.getDictionaryService().getMyPerson().getId());
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
	
	public Limit createLimit(String category, Double[] limits, String[] swim, String[] run) {
		return new Limit(category, limits, swim, run);
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
