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
import trimatrix.db.ZonesSwim;
import trimatrix.db.ZonesSwimDAO;
import trimatrix.db.ZonesSwimId;
import trimatrix.services.ServiceLayer;
import trimatrix.structures.SAuthorization;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.AuthorityObject;
import trimatrix.utils.Constants.Relation;

public class ZonesLogic {
	public static final Log logger = LogFactory.getLog(ZonesLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;

	public boolean deleteZones(Set<String> deletedIds) {
		try {
			for(String id : deletedIds) {
				ZonesDefinition zone = daoLayer.getZonesDefinitionDAO().findById(id);
				daoLayer.getZonesDefinitionDAO().delete(zone);
			}
		} catch (Exception ex) {
			logger.error("Error deleting zones : " + ex.toString());
			return false;
		}
		return true;
	}

	public boolean deleteZonesSwim(ZonesSwimId id) {
		try {
			ZonesSwim zonesSwim = daoLayer.getZonesSwimDAO().findById(id);
			if(zonesSwim!=null) daoLayer.getZonesSwimDAO().delete(zonesSwim);
		} catch (Exception ex) {
			logger.error("Error deleting individual swim zone " + id.getDistance() + " " + id.getZonesDefinitionId() + " : " + ex.toString());
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

	public Map<Integer, List<IndividualSwimZoneInfo>> getIndividualSwimZones(String athleteId, Persons coach) {
		Map<Integer, List<IndividualSwimZoneInfo>> result = new HashMap<Integer, List<IndividualSwimZoneInfo>>();
		Map<String, ZonesDefinition> definitionMap = new HashMap<String, ZonesDefinition>();
		List<ZonesDefinition> zonesDefinitions = coach.getZonesDefinition();
		for(ZonesDefinition definition : zonesDefinitions) {
			definitionMap.put(definition.getId(), definition);
		}
		ZonesSwimId exampleId = new ZonesSwimId();
		exampleId.setAthleteId(athleteId);
		ZonesSwim example = new ZonesSwim(exampleId);
		List<ZonesSwim> zonesSwims = daoLayer.getZonesSwimDAO().findByExample(example);
		for(ZonesSwim zonesSwim : zonesSwims) {
			Integer distance = zonesSwim.getId().getDistance();
			ZonesDefinition definition = definitionMap.get(zonesSwim.getId().getZonesDefinitionId());
			IndividualSwimZoneInfo info = new IndividualSwimZoneInfo(definition, zonesSwim, distance);
			// algorithm for sorting!
			if(!result.containsKey(distance)) {
				result.put(distance, new ArrayList<IndividualSwimZoneInfo>());
				result.get(distance).add(info);
			} else {
				int size = result.get(distance).size();
				for(int i=0;i<size;i++) {
					Integer sequence = result.get(distance).get(i).getDefinition().getSequence();
					Integer sequenceNew = definition.getSequence();
					if(sequenceNew<sequence) {
						result.get(distance).add(i, info);
						break;
					}
					if(i==size-1) {
						result.get(distance).add(size, info);
						break;
					}
				}
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

	public ZonesSwim createZonesSwim(Integer distance, String athleteId, String zonesDefinitionId) {
		ZonesSwimId id = new ZonesSwimId(distance, athleteId, zonesDefinitionId);
		return new ZonesSwim(id);
	}

	public boolean saveZone(Zones zone) {
		try {
			daoLayer.getZonesDAO().merge(zone);
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("save_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
			return false;
		}
	}

	public boolean saveIndividualSwimZone(ZonesSwim zonesSwim) {
		try {
			daoLayer.getZonesSwimDAO().merge(zonesSwim);
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("save_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
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

	public IndividualSwimZoneInfo createIndividualSwimZoneInfo(ZonesDefinition definition, ZonesSwim zonesSwim, Integer distance) {
		return new IndividualSwimZoneInfo(definition, zonesSwim, distance);
	}

	public class IndividualSwimZoneInfo {
		private ZonesDefinition definition;
		private ZonesSwim zonesSwim;
		private Integer distance;

		private IndividualSwimZoneInfo() { }

		public IndividualSwimZoneInfo(ZonesDefinition definition, ZonesSwim zonesSwim, Integer distance) {
			this.definition = definition;
			this.zonesSwim = zonesSwim;
			this.distance = distance;
		}

		public ZonesDefinition getDefinition() {
			return definition;
		}

		public ZonesSwim getZonesSwim() {
			return zonesSwim;
		}

		public Integer getDistance() {
			return distance;
		}
	}
}
