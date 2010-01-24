package trimatrix.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.DAOLayer;
import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.db.PersonsHaveRelationsDAO;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SAuthorization;
import trimatrix.utils.Constants.AuthorityObject;
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
	
	public Persons getStandardCoach(String athleteId) {
		// get coach of athlete
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PersonsHaveRelationsDAO.PARTNER1, athleteId);
		properties.put(PersonsHaveRelationsDAO.RELTYP_KEY, Relation.COACH.type());
		List<PersonsHaveRelations> phrs = daoLayer.getPersonsHaveRelationsDAO().findByProperties(properties);
		if(phrs==null || phrs.size()==0) return null;
		String coachId = phrs.get(0).getPartner2();
		return daoLayer.getPersonsDAO().findById(coachId);
	}
	
	public List<ZoneInfo> getAthletesZone(String athleteId, Persons coach) {
		List<ZoneInfo> result = new ArrayList<ZoneInfo>();		
		// get zones definition
		List<ZonesDefinition> zonesDefinitions = coach.getZonesDefinition();   	
		for(ZonesDefinition zonesDefinition : zonesDefinitions) {
			// read athletes detail			
			Zones example = new Zones();
			example.setAthleteId(athleteId);
			example.setZonesDefinitionId(zonesDefinition.getId());
			List<Zones> zones = daoLayer.getZonesDAO().findByExample(example);
			if(zones==null || zones.size()==0) {
				result.add(new ZoneInfo(zonesDefinition,null));
			} else {
				result.add(new ZoneInfo(zonesDefinition,zones.get(0)));
			}
		}
		return result;	
	}
	
	public List<ZoneInfo> getAthletesZone(String athleteId) {
		Persons coach = getStandardCoach(athleteId);
		return coach!=null ? getAthletesZone(athleteId, coach) : new ArrayList<ZoneInfo>();	
	}
	
	public Zones createZone(String athleteId, String zonesDefinitionId) {
		String id = UUID.randomUUID().toString();
		return new Zones(id, athleteId, zonesDefinitionId);
	}
	
	public boolean saveZone(Zones zone) {
		try {
			daoLayer.getZonesDAO().merge(zone);
			return true;
		} catch (Exception ex) {
			Statusbar.outputError("Zone couldn't be saved!", ex.toString());
			return false;
		}
	}
	
	public SAuthorization getAuthorization(String coachId) {
		return serviceLayer.getAuthorizationService().getAuthorization(AuthorityObject.ZONES, coachId);
	}
	
	public PersonsAthlete getPersonsAthlete(String personId) {
		return daoLayer.getPersonAthleteDAO().findById(personId);
	}
	
	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}
	
	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}	
	
	public class ZoneInfo {
		private ZonesDefinition definition;
		private Zones zone;
		
		private ZoneInfo() { }

		public ZoneInfo(ZonesDefinition definition, Zones zone) {
			this.definition = definition;
			this.zone = zone;
		}

		public ZonesDefinition getDefinition() {
			return definition;
		}

		public Zones getZone() {
			return zone;
		}		
	}
}
