package trimatrix.services;

import java.util.List;

import org.eclnt.jsfserver.elements.util.ValidValuesBinding;

import trimatrix.db.DAOLayer;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.structures.SValueList;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public class ValueListBindingService {
	// constant vvbs
	public static ValidValuesBinding EMPTYLIST = new ValidValuesBinding();
	public static ValidValuesBinding FUNCTIONS = new ValidValuesBinding() {
		{
			addValidValue(Constants.EXP, Constants.EXPONENTIAL);
			addValidValue(Constants.POLY, Constants.POLYNOMIAL);
		}
	};

	private SQLExecutorService sqlExecutorService;
	private Dictionary dictionaryService;
	private DAOLayer daoLayer;

	public ValidValuesBinding getVVBinding(Constants.ValueList valueList, String language, boolean longDescription, String parentKey) {
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
		String language = dictionaryService.getLanguage();
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
}
