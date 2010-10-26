package trimatrix.logic;

import static trimatrix.utils.Helper.isEmpty;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.Attachments;
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
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

public class ImportLogic {
	public static enum TYPE {
		COMPETITION, ATHLETE
	}

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

	public List<ImportTemplates> getAllTemplates(String entity) {
		ImportTemplatesId id = new ImportTemplatesId();
		id.setEntity(entity);
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
			Statusbar.outputSuccess(Helper.getMessages("template_success"));
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("template_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
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
			Statusbar.outputAlert(String.format(Helper.getMessages("result_import"), athlete), Helper.getLiteral("info")).setLeftTopReferenceCentered();
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert(String.format(Helper.getMessages("result_import_failure"), athlete), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
			return false;
		}
	}

	public boolean createOrUpdateCategory(
			String competitionId, String category, Attachments resultList, String resultsTemplate,
			String bestSwimmer, String bestSwimSplit, String bestBiker, String bestBikeSplit, String bestRunner, String bestRunSplit) {
		try {
			Competitions competition = daoLayer.getCompetitionsDAO().findById(competitionId);
			competition.setResults(true);
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
			limit.setSwim(new String[] {bestSwimmer, bestSwimSplit});
			limit.setBike(new String[] {bestBiker, bestBikeSplit});
			limit.setRun(new String[] {bestRunner, bestRunSplit});
			limit.setResultsId(resultList.getId());
			limit.setResultsTemplate(resultsTemplate);
			compScout.setLimits(logicLayer.getCompetitionLogic().buildString(limits));
			daoLayer.getAttachmentsDAO().save(resultList);
			daoLayer.getCompetitionsScoutsDAO().merge(compScout);
			daoLayer.getCompetitionsDAO().merge(competition);
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert(String.format(Helper.getMessages("category_update"),category), Helper.getLiteral("error")).setLeftTopReferenceCentered();
			return false;
		}
	}

	public ResultListData readResultExcel(byte[] bytes, ImportTemplates templateObj) throws Exception {
		ResultListData resultData = new ResultListData();
		if (templateObj == null) {
			// TODO translation
			throw new Exception("You need to define a mapping template!");
		}
		// set Mapping
		int startRow = templateObj.getStartingRow();
		String strMapping = templateObj.getMapping();
		strMapping = strMapping.substring(1, strMapping.length() - 1);
		String[] mapping = strMapping.split(", ");
		int rowPosition = Integer.valueOf(mapping[0]);
		int rowTime = Integer.valueOf(mapping[1]);
		int rowAthleteFirstname = Integer.valueOf(mapping[2]);
		int rowAthleteLastname = Integer.valueOf(mapping[3]);
		int rowSwimSplit = Integer.valueOf(mapping[4]);
		int rowBikeSplit = Integer.valueOf(mapping[5]);
		int rowRunSplit = Integer.valueOf(mapping[6]);

		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		// create workbook
		Workbook wb = new HSSFWorkbook(bais);
		// get first worksheet
		Sheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		if (rows <= startRow) {
			// TODO Translation
			throw new Exception(String.format("Start row is %d, but there are only %d rows in the file!", startRow, rows));
		}

		List<String> swimSplits = new ArrayList<String>(rows - startRow);
		List<String> runSplits = new ArrayList<String>(rows - startRow);
		List<String> bikeSplits = new ArrayList<String>(rows - startRow);

		for (int r = startRow-1; r < rows; r++) {
			Row row = sheet.getRow(r);
			// initialize variables
			int position = 0;
			String strPosition;
			String lastName = Constants.EMPTY;
			String firstName = Constants.EMPTY;
			String swimSplit = Constants.EMPTYTIME;
			String bikeSplit = Constants.EMPTYTIME;
			String runSplit = Constants.EMPTYTIME;
			String time = Constants.EMPTYTIME;
			if (row == null)
				continue;
			if (rowPosition > 0) {
				strPosition = row.getCell(rowPosition - 1).toString().trim();
				position = StringUtils.isNumeric(strPosition) ? Integer.valueOf(strPosition).intValue() : 0;
			}
			if (rowAthleteLastname > 0)
				lastName = row.getCell(rowAthleteLastname - 1).toString();
			if (rowAthleteFirstname > 0)
				firstName = row.getCell(rowAthleteFirstname - 1).toString();
			String athletesName = firstName.trim() + Constants.WHITESPACE + lastName.trim();
			if (rowSwimSplit > 0) {
				swimSplit = row.getCell(rowSwimSplit - 1).toString();
				if (position > 0 && !Constants.EMPTYTIME.equals(swimSplit))
					swimSplits.add(swimSplit);
			}
			if (rowBikeSplit > 0) {
				bikeSplit = row.getCell(rowBikeSplit - 1).toString();
				if (position > 0 && !Constants.EMPTYTIME.equals(bikeSplit))
					bikeSplits.add(bikeSplit);
			}
			if (rowRunSplit > 0) {
				runSplit = row.getCell(rowRunSplit - 1).toString();
				if (position > 0 && !Constants.EMPTYTIME.equals(runSplit))
					runSplits.add(runSplit);
			}
			if (rowTime > 0)
				time = row.getCell(rowTime - 1).toString();

			resultData.data.add(new ResultListData.Data(athletesName, position, time, swimSplit, runSplit, bikeSplit));
		}
		// get positions
		Collections.sort(swimSplits);
		Collections.sort(runSplits);
		Collections.sort(bikeSplits);

		for(ResultListData.Data data : resultData.data) {
			data.swimPosition = swimSplits.indexOf(data.swimSplit) + 1;
			if(data.swimPosition==1) {
				resultData.bestSwimmer = data.athlete;
				resultData.bestSwimSplit = data.swimSplit;
				resultData.importBestSwim = true;
			}

			data.runPosition = runSplits.indexOf(data.runSplit) + 1;
			if(data.runPosition==1) {
				resultData.bestRunner = data.athlete;
				resultData.bestRunSplit = data.swimSplit;
				resultData.importBestRun = true;
			}

			data.bikePosition = bikeSplits.indexOf(data.bikeSplit) + 1;
			if(data.bikePosition==1) {
				resultData.bestBiker = data.athlete;
				resultData.bestBikeSplit = data.swimSplit;
				resultData.importBestBike = true;
			}
		}
		return resultData;
	}

	public static class ResultListData {
		public String bestRunner;
		public String bestRunSplit;
		public boolean importBestRun;

		public String bestBiker;
		public String bestBikeSplit;
		public boolean importBestBike;

		public String bestSwimmer;
		public String bestSwimSplit;
		public boolean importBestSwim;

		public List<Data> data = new ArrayList<Data>();

		public static class Data {
			public String athlete;
			public int position;
			public String time;
			public String swimSplit;
			public String runSplit;
			public String bikeSplit;
			public int swimPosition;
			public int runPosition;
			public int bikePosition;

			public Data(String athlete, int position, String time, String swimSplit, String runSplit, String bikeSplit) {
				this.athlete = athlete;
				this.position = position;
				this.time = time;
				this.swimSplit = swimSplit;
				this.runSplit = runSplit;
				this.bikeSplit = bikeSplit;
			}
		}
	}

	public Map<String, ImportTemplates> getTemplatesData(Entity entity) {
		Map<String, ImportTemplates> myTemplates = new HashMap<String, ImportTemplates>();
		List<ImportTemplates> templatesList = getMyTemplates(entity.toString());
        if (isEmpty(templatesList)) return myTemplates;
        myTemplates.clear();
        for (ImportTemplates template : templatesList) {
            myTemplates.put(template.getId().getDescription(), template);
        }
        return myTemplates;
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
