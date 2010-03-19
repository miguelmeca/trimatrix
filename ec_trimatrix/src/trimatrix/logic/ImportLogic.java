package trimatrix.logic;

import static trimatrix.utils.Helper.isEmpty;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.DAOLayer;
import trimatrix.db.ImportTemplates;
import trimatrix.db.ImportTemplatesId;
import trimatrix.db.Results;
import trimatrix.db.ResultsTria;
import trimatrix.entities.CompetitionEntity;
import trimatrix.entities.EntityLayer;
import trimatrix.entities.ResultEntity;
import trimatrix.services.ServiceLayer;
import trimatrix.ui.ResultsImportUI;

public class ImportLogic {
	public static final Log logger = LogFactory.getLog(ImportLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	private EntityLayer entityLayer;

	public List<ImportTemplates> getMyTemplates(String entity) {
		ImportTemplatesId id = new ImportTemplatesId();
		id.setEntity(entity);
		id.setPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
		ImportTemplates example = new ImportTemplates(id);
		return daoLayer.getImportTemplatesDAO().findByExample(example);
	}

	public boolean saveTemplate(String entity, String description, int startingRow, int[] mapping) {
		try {
			String strMapping = Arrays.toString(mapping);
			ImportTemplatesId id = new ImportTemplatesId(entity, serviceLayer.getDictionaryService().getMyPerson().getId(), description);
			ImportTemplates template = new ImportTemplates(id);
			template.setStartingRow(startingRow);
			template.setMapping(strMapping);
			daoLayer.getImportTemplatesDAO().merge(template);
			Statusbar.outputSuccess("Template saved!");
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Template not saved!", "Error", ex.toString());
			return false;
		}
	}
	
	public boolean createOrUpdateResult(Competitions competition, String category, ResultsImportUI.GridImportItem item) {
		try {
			Results result = new Results();
			result.setCompetition(competition);
			result.setAthleteId(item.getScoutedAthlete());
			result.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());	
			// check if result already exists?
			List<Results> results = daoLayer.getResultsDAO().findByExample(result);		
			if(!isEmpty(results)) {
				result = results.get(0);
			} else {
				result = entityLayer.getResultEntity().create();
				result.setCompetition(competition);
				result.setAthleteId(item.getScoutedAthlete());
				result.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());	
			}
			result.setFinalPosition(String.valueOf(item.getPosition()));	
			result.setTime(item.getTime());
			// type
			if(CompetitionEntity.TRIATHLON.equals(competition.getType())) {
				ResultsTria tria = daoLayer.getResultsTriaDAO().findById(result.getId());
				if(tria==null) tria = new ResultsTria(result.getId());
				tria.setSwimPosition(String.valueOf(item.getSwimPosition()));
				tria.setSwimSplit(item.getSwimSplit());
				tria.setBikePosition(String.valueOf(item.getBikePosition()));
				tria.setBikeSplit(item.getBikeSplit());
				tria.setRunPosition(String.valueOf(item.getRunPosition()));
				tria.setRunSplit(item.getRunSplit());
				tria.setCategory(category);
				daoLayer.getResultsTriaDAO().merge(tria);
			}		
			daoLayer.getResultsDAO().merge(result);
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Result for athlete " + item.getAthlete() + " not imported!", "Error", ex.toString());
			return false;
		}		
	}
	
	public boolean createOrUpdateCategory(Competitions competition, String category, String bestSwimmer, String bestSwimSplit) {
		try {
			
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Update for category " + category + " failed");
			return false;
		}
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
	
	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}
}
