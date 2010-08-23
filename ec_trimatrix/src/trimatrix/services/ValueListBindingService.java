package trimatrix.services;

import java.util.List;

import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.db.DAOLayer;
import trimatrix.db.Persons;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.entities.EntityLayer;
import trimatrix.entities.IEntityData;
import trimatrix.logic.LogicLayer;
import trimatrix.structures.SValueList;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.Helper;

public class ValueListBindingService {
	// constant vvbs
	public static ValidValuesBinding EMPTYLIST = new ValidValuesBinding();
	public static ValidValuesBinding FUNCTIONS = new ValidValuesBinding() {
		{
			addValidValue(Constants.EXP, Constants.EXPONENTIAL);
			addValidValue(Constants.POLY, Constants.POLYNOMIAL);
			addValidValue(Constants.SPLINE, Constants.SPLINEINTERPOLATION);
			addValidValue(Constants.NEVILLE, Constants.NEVILLEINTERPOLATION);
			addValidValue(Constants.DIVDIFF, Constants.DIVDIFFINTERPOLATION);
		}
	};

	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private DAOLayer daoLayer;
	private EntityLayer entityLayer;
	private LogicLayer logicLayer;

	private ValidValuesBinding getVVBindingMYRESULTS() {
		ValidValuesBinding vvb = new ValidValuesBinding();
		List<IEntityData> data = entityLayer.getPersonEntity().getData(Constants.Entity.MYATHLETES, Constants.NO_FILTER);
		for(IEntityData date : data) {
			vvb.addValidValue(date.getId(), date.toString());
		}
		return vvb;
	}

	private ValidValuesBinding getVVBindingMYRESULTSANDME() {
		ValidValuesBinding vvb = getVVBindingMYRESULTS();
		vvb.addValidValue(dictionaryService.getMyPerson().getId(), dictionaryService.getMyPerson().toString());
		return vvb;
	}

	private ValidValuesBinding getVVBindingMYZONESDEFINITION() {
		ValidValuesBinding vvb = new ValidValuesBinding();
		List<ZonesDefinition> zonesDefinitions = dictionaryService.getMyPerson().getZonesDefinition();
		// if no data is set for user, try data from coach
		if(zonesDefinitions.size()==0) {
			Persons coach = logicLayer.getZonesLogic().getStandardCoach(dictionaryService.getMyPerson().getId());
			zonesDefinitions = coach.getZonesDefinition();
		}
		for (ZonesDefinition zonesDefinition : zonesDefinitions) {
			vvb.addValidValue(zonesDefinition.getId(), zonesDefinition.getShortcut(), zonesDefinition.getDescription());
		}
		return vvb;
	}



	public ValidValuesBinding getVVBinding(Constants.ValueList valueList, String language, boolean longDescription, String parentKey) {
		// special handling
		if(valueList==Constants.ValueList.MYATHLETES) {
			return getVVBindingMYRESULTS();
		}
		if(valueList==Constants.ValueList.MYATHLETESANDME) {
			return getVVBindingMYRESULTSANDME();
		}
		if(valueList==Constants.ValueList.MYZONESDEFINITION) {
			return getVVBindingMYZONESDEFINITION();
		}
		// standard vvb by sql
		ValidValuesBinding vvb = new ValidValuesBinding();
		List<SValueList> list = sqlExecutorService.getValueList(valueList, language, parentKey);
		for (SValueList entry : list) {
			if (longDescription) {
				vvb.addValidValue(entry.key, entry.descriptionLong);
			} else {
				vvb.addValidValue(entry.key, entry.description);
			}

		}
		return vvb;
	}

	public ValidValuesBinding getVVBinding(Constants.ValueList valueList, String parentKey) {
		String language = Helper.getLanguageServer();
		return getVVBinding(valueList,language, false, parentKey);
	}

	public ValidValuesBinding getVVBinding(Constants.ValueList valueList) {
		ValidValuesBinding vvb = getVVBinding(valueList, null);
		return vvb;
	}

	public ValidValuesBinding getVVBinding(String strValueList) {
		Constants.ValueList valueList = Constants.ValueList.valueOf(strValueList.toUpperCase());
		if(valueList==null) return EMPTYLIST;
		return getVVBinding(valueList);
	}

	public ValidValuesBinding getVVBinding(String strValueList, String parentKey) {
		Constants.ValueList valueList = Constants.ValueList.valueOf(strValueList.toUpperCase());
		if(valueList==null) return EMPTYLIST;
		return getVVBinding(valueList, parentKey);
	}

	public ValidValuesBinding getVVBindingZones(String coachId, String athleteId) {
		ValidValuesBinding vvb = new ValidValuesBinding();
		Zones zoneExample = new Zones();
		ZonesDefinition definitionExample = new ZonesDefinition();
		definitionExample.setCoachId(coachId);
		zoneExample.setAthleteId(athleteId);
		zoneExample.setZonesDefinition(definitionExample);
		List<Zones> zones = daoLayer.getZonesDAO().findByExample(zoneExample);
		for (Zones zone : zones) {
			vvb.addValidValue(zone.getZonesDefinitionId(), zone.getZonesDefinition().getShortcut(), zone.getZonesDefinition().getDescription());
		}
		return vvb;
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}

	public void setLogicLayer(LogicLayer logicLayer) {
		this.logicLayer = logicLayer;
	}
}
