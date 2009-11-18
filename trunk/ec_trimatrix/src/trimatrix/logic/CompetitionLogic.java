package trimatrix.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.Categories;
import trimatrix.db.CategoriesDAO;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.DAOLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;

public class CompetitionLogic {
	public static final Log logger = LogFactory.getLog(CompetitionLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	
	public void saveCompetitionScouts(CompetitionsScouts cs) {
		daoLayer.getCompetitionsScoutsDAO().merge(cs);
	}
	
	public List<Categories> getCategories() {
		 return daoLayer.getCategoriesDAO().findByProperty(CategoriesDAO.SCOUTID, serviceLayer.getDictionaryService().getMyPerson().getId());
	}
	
	public String buildString(List<Limit> limits) {
        if(limits==null || limits.size()==0) return Constants.EMPTY;
        StringBuilder result = new StringBuilder();       
        for(int i=0;i<limits.size();i++) {           	
        	if(i>0) result.append(';');
        	result.append('{');
        	result.append(limits.get(i).getCategory());
        	result.append(':');
        	for(int j=0;j<limits.get(i).getLimits().length;j++) {  
        		if(j>0) result.append(',');
        		result.append(limits.get(i).getLimits()[j].toString());
        	}            
            result.append('}');
        }
        result.insert(0,'[');       
        result.append(']');
        return result.toString();
    }
	
	public Limit[] getLimits(String limits) {
		if(limits==null || limits.length()==0) return new Limit[0];
		// remove brackets
		limits = limits.substring(1, limits.length()-1);
		String[] arrLimits = limits.split(";");
		Limit[] result = new Limit[arrLimits.length];
		for(int i=0;i<arrLimits.length;i++) { 
			// remove brackets
			String limit = arrLimits[i].substring(1, arrLimits[i].length()-1);
			result[i] = new Limit(limit);
		}		
		return result;
	}
	
	public Map<String, Double[]> getLimitsMap(String limits) {
		Map<String, Double[]> result = new HashMap<String, Double[]>();
		if(limits==null || limits.length()==0) return result;		
		// remove brackets
		limits = limits.substring(1, limits.length()-1);
		String[] arrLimits = limits.split(";");
		for(int i=0;i<arrLimits.length;i++) { 
			// remove brackets
			String strLimit = arrLimits[i].substring(1, arrLimits[i].length()-1);
			Limit limit = new Limit(strLimit);
			result.put(limit.getCategory(), limit.getLimits());
		}		
		return result;
	}
	
	public Limit createLimit() {
		return new Limit();
	}
	
	public Limit createLimit(String category, Double[] limits) {
		return new Limit(category, limits);
	}
	
	public class Limit {
		String category;
		Double[] limits = new Double[2];	// initialize!!
		
		public Limit() { }
		
		public Limit(String category, Double[] limits) {
			this.category = category;
			if(limits!=null) this.limits = limits;
		}
		
		public Limit(String limit) {
			String[] parts = limit.split(":");
			if(parts.length!=2) return;
			this.category = parts[0];
			String[] strLimits = parts[1].split(",");
			this.limits = new Double[strLimits.length];
			for(int i=0;i<strLimits.length;i++) {   
				try {
					limits[i] = Double.valueOf(strLimits[i]);
			    } catch (NumberFormatException nfe) {
			    	limits[i] = 0d;
			    }       
			}
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Double[] getLimits() {
			return limits;
		}

		public void setLimits(Double[] limits) {
			this.limits = limits;
		}

		@Override
		public String toString() {
			return category + " : " + Arrays.toString(limits);
		}			
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
