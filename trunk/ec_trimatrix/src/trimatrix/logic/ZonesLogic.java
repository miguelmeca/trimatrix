package trimatrix.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.db.DAOLayer;
import trimatrix.db.Persons;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.db.PersonsHaveRelationsDAO;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants.Relation;

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
	
	public void getAthletesZone(String athleteId) {
		// get coach of athlete
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PersonsHaveRelationsDAO.PARTNER1, athleteId);
		properties.put(PersonsHaveRelationsDAO.RELTYP_KEY, Relation.COACH.type());
		List<PersonsHaveRelations> phrs = daoLayer.getPersonsHaveRelationsDAO().findByProperties(properties);
		if(phrs==null || phrs.size()==0) return;
		String coachId = phrs.get(0).getPartner2();
		Persons coach = daoLayer.getPersonsDAO().findById(coachId);
		// get zones definition
		List<ZonesDefinition> zonesDefinitions = coach.getZonesDefinition();   	
		for(ZonesDefinition zonesDefinition : zonesDefinitions) {
			// read athletes detail
			Zones example = new Zones();
			example.setAthleteId(athleteId);
			example.setZonesDefinitionId(zonesDefinition.getId());
			List<Zones> zones = daoLayer.getZonesDAO().findByExample(example);
			if(zones==null || zones.size()==0) return;
			Zones zone = zones.get(0);
		}
	
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
}
