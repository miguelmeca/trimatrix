package trimatrix.logic;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.DAOLayer;
import trimatrix.db.ZonesDefinition;
import trimatrix.services.ServiceLayer;

public class ZonesLogic {
	public static final Log logger = LogFactory.getLog(ZonesLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;

	public boolean deleteZones(Set<String> deletedIds) {
		try {
			for(String id : deletedIds) {
				daoLayer.getZonesDefinitionDAO().delete(daoLayer.getZonesDefinitionDAO().findById(id));					
			}
		} catch (Exception ex) {
			logger.error("Error deleting zones : " + ex.toString());
			return false;
		}
		return true;
	}
	
	public boolean updateZones(List<ZonesDefinition> zonesDefinitions) {
		try {
			for (ZonesDefinition zone : zonesDefinitions) {
				daoLayer.getZonesDefinitionDAO().merge(zone);
			}
			// update zones add global MyPerson instance to be consistent
			serviceLayer.getDictionaryService().getMyPerson().setZonesDefinition(zonesDefinitions);			
		} catch (Exception ex) {
			logger.error("Error changing zones : " + ex.toString());
			return false;
		}		
		return true;
	}
	
	public ZonesDefinition getMyZone() {
		return null;
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
