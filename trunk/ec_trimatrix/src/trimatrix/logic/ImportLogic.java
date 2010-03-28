package trimatrix.logic;

import static trimatrix.utils.Helper.isEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.CompetitionsScoutsId;
import trimatrix.db.DAOLayer;
import trimatrix.db.ImportTemplates;
import trimatrix.db.ImportTemplatesId;
import trimatrix.db.Persons;
import trimatrix.db.Results;
import trimatrix.db.ResultsTria;
import trimatrix.entities.CompetitionEntity;
import trimatrix.entities.EntityLayer;
import trimatrix.logic.helper.Limit;
import trimatrix.services.ServiceLayer;
import trimatrix.ui.ResultsImportUI;

public class ImportLogic {
	public static final Log logger = LogFactory.getLog(ImportLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	private EntityLayer entityLayer;
	private LogicLayer logicLayer;

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

	public boolean createOrUpdateResult(
			Competitions competition, String category,
			String athlete, String scoutedAthlete,
			int position, String time,
			String swimSplit, String runSplit, String bikeSplit,
			int swimPosition, int runPosition, int bikePosition) {
		try {
			Results result = new Results();
			result.setCompetitionId(competition.getId());
			result.setScoutId(serviceLayer.getDictionaryService().getMyPerson().getId());
			result.setAthleteId(scoutedAthlete);
			result.setDeleted(false);
			List<Results> results = daoLayer.getResultsDAO().findByExample(result);
			// check if result already exists?
			if(!isEmpty(results)) {
				result = results.get(0);
			} else {
				result = entityLayer.getResultEntity().create();
				result.setCompetition(competition);
				result.setScout(serviceLayer.getDictionaryService().getMyPerson());
				result.setAthlete((Persons)entityLayer.getPersonEntity().get(scoutedAthlete));
			}
			result.setFinalPosition(String.valueOf(position));
			result.setTime(time);
			// type
			if(CompetitionEntity.TRIATHLON.equals(competition.getType()) ||
			   CompetitionEntity.XTERRA.equals(competition.getType())		) {
				ResultsTria tria = result.getResultsTria();
				if(tria==null) {
					tria = new ResultsTria(result.getId());
					result.setResultsTria(tria);
				}
				tria.setSwimPosition(String.valueOf(swimPosition));
				tria.setSwimSplit(swimSplit);
				tria.setBikePosition(String.valueOf(bikePosition));
				tria.setBikeSplit(bikeSplit);
				tria.setRunPosition(String.valueOf(runPosition));
				tria.setRunSplit(runSplit);
				tria.setCategory(category);
			}
			daoLayer.getResultsDAO().merge(result);
			Statusbar.outputAlert("Result for athlete " + athlete + " imported!");
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Result for athlete " + athlete + " not imported!", "Error", ex.toString());
			return false;
		}
	}

	public boolean createOrUpdateCategory(
			String competitionId, String category,
			boolean importBestSwim, String bestSwimmer, String bestSwimSplit,
			boolean importBestBike, String bestBiker, String bestBikeSplit,
			boolean importBestRun, String bestRunner, String bestRunSplit) {
		try {
			CompetitionsScoutsId id = new CompetitionsScoutsId(competitionId, serviceLayer.getDictionaryService().getMyPerson().getId());
			// check if competition already exists?
			CompetitionsScouts compScout = daoLayer.getCompetitionsScoutsDAO().findById(id);
			if(compScout==null) {
				compScout = new CompetitionsScouts(id);
			}
			// check if category exists
			Limit limit = null;
			List<Limit> limits = logicLayer.getCompetitionLogic().getLimits(compScout.getLimits());
			if(!isEmpty(limits)) {
				for (Limit _limit : limits) {
					if(category.equals(_limit.getCategory())) {
						limit = _limit;
					}
				}
			} else {
				limits = new ArrayList<Limit>(1);
			}
			// when not found add new category
			if(limit==null) {
				limit = new Limit();
				limit.setCategory(category);
				limits.add(limit);
			}
			// change limit
			if(importBestSwim) limit.setSwim(new String[] {bestSwimmer, bestSwimSplit});
			if(importBestBike) limit.setBike(new String[] {bestBiker, bestBikeSplit});
			if(importBestRun) limit.setRun(new String[] {bestRunner, bestRunSplit});
			compScout.setLimits(logicLayer.getCompetitionLogic().buildString(limits));
			daoLayer.getCompetitionsScoutsDAO().merge(compScout);
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

	public void setLogicLayer(LogicLayer logicLayer) {
		this.logicLayer = logicLayer;
	}
}
