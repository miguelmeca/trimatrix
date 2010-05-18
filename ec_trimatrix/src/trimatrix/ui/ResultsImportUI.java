package trimatrix.ui;

import static trimatrix.utils.Helper.calculateSeconds;
import static trimatrix.utils.Helper.correctTimeInput;
import static trimatrix.utils.Helper.isEmpty;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.db.ImportTemplates;
import trimatrix.db.Persons;
import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.ResultEntity;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Page;

import com.google.common.collect.ImmutableSortedSet;

@CCGenClass(expressionBase = "#{d.ResultsImportUI}")
public class ResultsImportUI extends MyWorkpageDispatchedBean implements Serializable {
	private static enum TYPE {
		COMPETITION, ATHLETE
	}
	private static final Entity ENTITY = Entity.RESULT;

	protected FIXGRIDListBinding<GridImportItem> m_gridImport = new FIXGRIDListBinding<GridImportItem>();

	public FIXGRIDListBinding<GridImportItem> getGridImport() {
		return m_gridImport;
	}

	public void setGridImport(FIXGRIDListBinding<GridImportItem> value) {
		m_gridImport = value;
	}

	public class GridImportItem extends FIXGRIDItem implements java.io.Serializable {
		private String athlete;
		private Persons scoutedAthlete;
		private int position;
		private String time;
		private String swimSplit;
		private String runSplit;
		private String bikeSplit;
		private int swimPosition;
		private int runPosition;
		private int bikePosition;

		public GridImportItem(String athlete, PersonEntity.Data scoutedAthlete, int position, String time, String swimSplit, String bikeSplit, String runSplit) {
			this.athlete = athlete;
			this.position = position;
			this.time = time;
			this.swimSplit = swimSplit;
			this.bikeSplit = bikeSplit;
			this.runSplit = runSplit;
			if (scoutedAthlete != null)
				this.scoutedAthlete = getDaoLayer().getPersonsDAO().findById(scoutedAthlete.getId());
		}

		public String getAthlete() {
			return athlete;
		}

		public void setAthlete(String athlete) {
			this.athlete = athlete;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getSwimSplit() {
			return swimSplit;
		}

		public void setSwimSplit(String swimSplit) {
			this.swimSplit = swimSplit;
		}

		public String getRunSplit() {
			return runSplit;
		}

		public void setRunSplit(String runSplit) {
			this.runSplit = runSplit;
		}

		public String getBikeSplit() {
			return bikeSplit;
		}

		public void setBikeSplit(String bikeSplit) {
			this.bikeSplit = bikeSplit;
		}

		public int getSwimPosition() {
			return swimPosition;
		}

		public void setSwimPosition(int swimPosition) {
			this.swimPosition = swimPosition;
		}

		public int getRunPosition() {
			return runPosition;
		}

		public void setRunPosition(int runPosition) {
			this.runPosition = runPosition;
		}

		public int getBikePosition() {
			return bikePosition;
		}

		public void setBikePosition(int bikePosition) {
			this.bikePosition = bikePosition;
		}

		public String getScoutedAthlete() {
			return scoutedAthlete != null ? scoutedAthlete.toString() : null;
		}

		public String getScoutedAthleteId() {
			return scoutedAthlete != null ? scoutedAthlete.getId() : null;
		}

		public void onScoutedAthleteF4(ActionEvent event) {
			IdTextSelection idts = IdTextSelection.createInstance();
			for (PersonEntity.Data data : scoutedAthletesData.values()) {
				idts.addLine(data.getId(), data.toString());
			}
			idts.setCallBack(new ISetId() {
				public void setId(String id) {
					scoutedAthlete = getDaoLayer().getPersonsDAO().findById(id);
				}
			});
			idts.setWithHeader(false);
			idts.setSuppressHeadline(true);
			idts.setRenderTextColumn(true);
			idts.setRenderIdColumn(false);
			idts.setPopupWidth(120);
			idts.setPopupHeight(100);
		}

		public void onTimeFlush(ActionEvent event) {
			// get clientname to separate by source
			String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
			if (ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
				swimSplit = correctTimeInput(swimSplit);
			} else if (ResultEntity.RUN.equalsIgnoreCase(clientname)) {
				runSplit = correctTimeInput(runSplit);
			} else if (ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
				bikeSplit = correctTimeInput(bikeSplit);
			} else if (ResultEntity.OVERALL.equalsIgnoreCase(clientname)) {
				time = correctTimeInput(time);
			} else {
				return;
			}
		}
	}

	protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();

	private Competitions competition;

	public String getCompDesc() {
		return competition != null ? competition.toString() : null;
	}

	private Persons athlete;

	public String getAthleteDescription() {
		if (athlete != null)
			return athlete.toString();
		return Constants.EMPTY;
	}

	public Persons getAthlete() {
		return athlete;
	}

	private String category;

	public String getCategory() {
		return category;
	}

	private String template;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	private String filename;

	public String getFilename() {
		return filename;
	}

	private String bestSwimmer;
	private String bestSwimSplit;

	private String bestBiker;
	private String bestBikeSplit;

	private String bestRunner;
	private String bestRunSplit;

	private int startRow = 1;
	private int rowPosition;
	private int rowTime;
	private int rowAthleteFirstname;
	private int rowAthleteLastname;
	private int rowSwimSplit;
	private int rowBikeSplit;
	private int rowRunSplit;

	private boolean importBestSwim;
	private boolean importBestBike;
	private boolean importBestRun;

	private boolean statusMapping;
	private boolean statusImportData;
	private boolean statusImportAthleteData;

	public ResultsImportUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		buildScoutedAthletesData();
		buildTemplatesData();
		// set initial status
		statusMapping = true;
		statusImportData = false;
		statusImportAthleteData = false;
	}

	public String getBestSwimmer() {
		return bestSwimmer;
	}

	public void setBestSwimmer(String bestSwimmer) {
		this.bestSwimmer = bestSwimmer;
	}

	public String getBestSwimSplit() {
		return bestSwimSplit;
	}

	public void setBestSwimSplit(String bestSwimSplit) {
		this.bestSwimSplit = bestSwimSplit;
	}

	public String getBestBiker() {
		return bestBiker;
	}

	public void setBestBiker(String bestBiker) {
		this.bestBiker = bestBiker;
	}

	public String getBestBikeSplit() {
		return bestBikeSplit;
	}

	public void setBestBikeSplit(String bestBikeSplit) {
		this.bestBikeSplit = bestBikeSplit;
	}

	public String getBestRunner() {
		return bestRunner;
	}

	public void setBestRunner(String bestRunner) {
		this.bestRunner = bestRunner;
	}

	public String getBestRunSplit() {
		return bestRunSplit;
	}

	public void setBestRunSplit(String bestRunSplit) {
		this.bestRunSplit = bestRunSplit;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getRowPosition() {
		return rowPosition;
	}

	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}

	public int getRowTime() {
		return rowTime;
	}

	public void setRowTime(int rowTime) {
		this.rowTime = rowTime;
	}

	public int getRowAthleteFirstname() {
		return rowAthleteFirstname;
	}

	public void setRowAthleteFirstname(int rowAthleteFirstname) {
		this.rowAthleteFirstname = rowAthleteFirstname;
	}

	public int getRowAthleteLastname() {
		return rowAthleteLastname;
	}

	public void setRowAthleteLastname(int rowAthleteLastname) {
		this.rowAthleteLastname = rowAthleteLastname;
	}

	public int getRowSwimSplit() {
		return rowSwimSplit;
	}

	public void setRowSwimSplit(int rowSwimSplit) {
		this.rowSwimSplit = rowSwimSplit;
	}

	public int getRowBikeSplit() {
		return rowBikeSplit;
	}

	public void setRowBikeSplit(int rowBikeSplit) {
		this.rowBikeSplit = rowBikeSplit;
	}

	public int getRowRunSplit() {
		return rowRunSplit;
	}

	public void setRowRunSplit(int rowRunSplit) {
		this.rowRunSplit = rowRunSplit;
	}

	public boolean isImportBestSwim() {
		return importBestSwim;
	}

	public void setImportBestSwim(boolean importBestSwim) {
		this.importBestSwim = importBestSwim;
	}

	public boolean isImportBestBike() {
		return importBestBike;
	}

	public void setImportBestBike(boolean importBestBike) {
		this.importBestBike = importBestBike;
	}

	public boolean isImportBestRun() {
		return importBestRun;
	}

	public void setImportBestRun(boolean importBestRun) {
		this.importBestRun = importBestRun;
	}

	public boolean isStatusMapping() {
		return statusMapping;
	}

	public void setStatusMapping(boolean statusMapping) {
		this.statusMapping = statusMapping;
	}

	public boolean isStatusImportData() {
		return statusImportData;
	}

	public void setStatusImportData(boolean statusImportData) {
		this.statusImportData = statusImportData;
	}

	public boolean isStatusImportAthleteData() {
		return statusImportAthleteData;
	}

	public void setStatusImportAthleteData(boolean statusImportAthleteData) {
		this.statusImportAthleteData = statusImportAthleteData;
	}

	private Map<String, PersonEntity.Data> scoutedAthletesData;

	private void buildScoutedAthletesData() {
		List<IEntityData> entitiesData = getLogic().getFunctionTreeLogic().getMyScoutedAthletes();
		scoutedAthletesData = new HashMap<String, PersonEntity.Data>(entitiesData.size());
		for (IEntityData entityData : entitiesData) {
			PersonEntity.Data data = (PersonEntity.Data) entityData;
			scoutedAthletesData.put(data.toString(), data);
		}
	}

	private Map<String, ImportTemplates> myTemplates = new HashMap<String, ImportTemplates>();

	public void buildTemplatesData() {
		List<ImportTemplates> templatesList = getLogic().getImportLogic().getMyTemplates(ENTITY.toString());
		if (isEmpty(templatesList))
			return;
		myTemplates.clear();
		for (ImportTemplates template : templatesList) {
			myTemplates.put(template.getId().getDescription(), template);
		}
	}

	public void onSaveTemplate(ActionEvent event) {
		int[] mapping = { rowPosition, rowTime, rowAthleteFirstname, rowAthleteLastname, rowSwimSplit, rowBikeSplit, rowRunSplit };
		getLogic().getImportLogic().saveTemplate(ENTITY.toString(), template, startRow, mapping);
		buildTemplatesData();
	}

	/**
	 * Call competition selection pop up
	 *
	 * @param event
	 */
	public void onCompetitionSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Entity.COMPETITION);
		entitySelectionUI.buildData(Entity.SCOUTCOMPETITIONS);

		entitySelectionUI.prepareCallback(new ISelectionCallback() {
			public void cancel() {
				m_popup.close();
			}

			public void selected(String id) {
				competition = (Competitions) ENTITYLISTLOGIC.get(Entity.COMPETITION, id);
				m_popup.close();
			}
		});
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.open(Page.COMPETITIONSELECTION.getUrl(), "Wettkampfsuche", 800, 600, this);
	}

	public void onCompetitionClicked(ActionEvent event) {
		if (competition == null)
			return;
		loadEntityDetailPage(Entity.COMPETITION, competition.getId(), competition.toString());
	}

	/**
	 * F4 Help for categories depending on competition
	 *
	 * @param event
	 */
	public void onCategoryF4(ActionEvent event) {
		IdTextSelection idts = IdTextSelection.createInstance();
		for (String category : getLogic().getCompetitionLogic().getCategories()) {
			idts.addLine(category, Constants.EMPTY);
		}
		idts.setCallBack(new ISetId() {
			public void setId(String id) {
				category = id;
			}
		});
		idts.setWithHeader(false);
		idts.setSuppressHeadline(true);
		idts.setRenderTextColumn(false);
		idts.setPopupWidth(120);
		idts.setPopupHeight(100);
	}

	public void onTemplateF4(ActionEvent event) {
		IdTextSelection idts = IdTextSelection.createInstance();
		for (ImportTemplates template : myTemplates.values()) {
			idts.addLine(template.getId().getDescription(), Constants.EMPTY);
		}
		idts.setCallBack(new ISetId() {
			public void setId(String id) {
				template = id;
				setTemplate();
			}
		});
		idts.setWithHeader(false);
		idts.setSuppressHeadline(true);
		idts.setRenderTextColumn(false);
		idts.setPopupWidth(120);
		idts.setPopupHeight(100);
	}

	private void setTemplate() {
		ImportTemplates templateObj = myTemplates.get(template);
		if (templateObj == null)
			return;
		// set Mapping
		startRow = templateObj.getStartingRow();
		String strMapping = templateObj.getMapping();
		strMapping = strMapping.substring(1, strMapping.length() - 1);
		String[] mapping = strMapping.split(", ");
		rowPosition = Integer.valueOf(mapping[0]);
		rowTime = Integer.valueOf(mapping[1]);
		rowAthleteFirstname = Integer.valueOf(mapping[2]);
		rowAthleteLastname = Integer.valueOf(mapping[3]);
		rowSwimSplit = Integer.valueOf(mapping[4]);
		rowBikeSplit = Integer.valueOf(mapping[5]);
		rowRunSplit = Integer.valueOf(mapping[6]);
	}

	public void onTimeFlush(ActionEvent event) {
		// get clientname to separate by source
		String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
		if (ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
			bestSwimSplit = correctTimeInput(bestSwimSplit);
		} else if (ResultEntity.RUN.equalsIgnoreCase(clientname)) {
			bestRunSplit = correctTimeInput(bestRunSplit);
		} else if (ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
			bestBikeSplit = correctTimeInput(bestBikeSplit);
		} else {
			return;
		}
	}

	public void onUploadFile(ActionEvent event) {
		if (event instanceof BaseActionEventUpload) {
			BaseActionEventUpload bae = (BaseActionEventUpload) event;
			// filename without directory structure
			filename = bae.getClientFileName();
			// clear grid
			m_gridImport.getItems().clear();
			// read excel
			if(!readResultExcel(bae.getHexBytes(), TYPE.COMPETITION, null)) {
				Statusbar.outputAlert("Error importing File", "Error");
			}
		}
	}

	/**
	 * Import results into DB
	 *
	 * @param event
	 */
	public void onImport(ActionEvent event) {
		// check preconditions
		if (competition == null) {
			Statusbar.outputAlert("You have to set a competition!");
			return;
		}
		if (isEmpty(category)) {
			Statusbar.outputAlert("You have to choose a category!");
			return;
		}
		// check if data to import exists / any item marked?
		final Set<GridImportItem> items = m_gridImport.getSelectedItems();
		final Competitions competition = this.competition;
		if (isEmpty(items)) {
			Statusbar.outputAlert("No data to import, or no data selected!");
			return;
		}
		YESNOPopup ynp = YESNOPopup.createInstance("Import Results", "Do you really want to import marked results?", new IYesNoCancelListener() {
			public void reactOnCancel() {
			}

			public void reactOnNo() {
			}

			public void reactOnYes() {
				// update competition
				if (!getLogic().getImportLogic().createOrUpdateCategory(competition.getId(), category, importBestSwim, bestSwimmer, bestSwimSplit, importBestBike, bestBiker, bestBikeSplit,
						importBestRun, bestRunner, bestRunSplit)) {
					Statusbar.outputAlert("Error updating the competition details!\n");
				}
				// update results
				int success = 0;
				int error = 0;
				for (GridImportItem item : items) {
					// create
					if (getLogic().getImportLogic().createOrUpdateResult(competition, category, item.getAthlete(), item.getScoutedAthleteId(), item.getPosition(), item.getTime(), item.getSwimSplit(),
							item.getRunSplit(), item.getBikeSplit(), item.getSwimPosition(), item.getRunPosition(), item.getBikePosition())) {
						success++;
					} else {
						error++;
					}
				}
				// output Result
				Statusbar.outputAlert("\nImport done!", "Import", "Successfull : " + success + "\nErroreous   : " + error);
				// Refresh WPFunctionTree
				reloadFunctionTree();
			}
		});
		ynp.getModalPopup().setLeftTopReferenceCentered();
	}

	// -------------------------------
	// Athletes Import
	// -------------------------------
	protected FIXGRIDListBinding<GridImportAthleteItem> m_gridImportAthlete = new FIXGRIDListBinding<GridImportAthleteItem>();

	public FIXGRIDListBinding<GridImportAthleteItem> getGridImportAthlete() {
		return m_gridImportAthlete;
	}

	public void setGridImportAthlete(FIXGRIDListBinding<GridImportAthleteItem> value) {
		m_gridImportAthlete = value;
	}

	public class GridImportAthleteItem extends FIXGRIDItem implements java.io.Serializable {
		private Competitions competition;
		private int position;
		private String time;
		private String swimSplit;
		private String runSplit;
		private String bikeSplit;
		private int swimPosition;
		private int runPosition;
		private int bikePosition;

		public GridImportAthleteItem(Competitions competition, int position, String time, String swimSplit, String bikeSplit, String runSplit) {
			this.competition = competition;
			this.position = position;
			this.time = time;
			this.swimSplit = swimSplit;
			this.bikeSplit = bikeSplit;
			this.runSplit = runSplit;
		}

		public String getCompetitionDesc() {
			return competition.toString();
		}

		public Competitions getCompetition() {
			return competition;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getSwimSplit() {
			return swimSplit;
		}

		public void setSwimSplit(String swimSplit) {
			this.swimSplit = swimSplit;
		}

		public String getRunSplit() {
			return runSplit;
		}

		public void setRunSplit(String runSplit) {
			this.runSplit = runSplit;
		}

		public String getBikeSplit() {
			return bikeSplit;
		}

		public void setBikeSplit(String bikeSplit) {
			this.bikeSplit = bikeSplit;
		}

		public int getSwimPosition() {
			return swimPosition;
		}

		public void setSwimPosition(int swimPosition) {
			this.swimPosition = swimPosition;
		}

		public int getRunPosition() {
			return runPosition;
		}

		public void setRunPosition(int runPosition) {
			this.runPosition = runPosition;
		}

		public int getBikePosition() {
			return bikePosition;
		}

		public void setBikePosition(int bikePosition) {
			this.bikePosition = bikePosition;
		}

		public void onTimeFlush(ActionEvent event) {
			// get clientname to separate by source
			String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
			if (ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
				swimSplit = correctTimeInput(swimSplit);
			} else if (ResultEntity.RUN.equalsIgnoreCase(clientname)) {
				runSplit = correctTimeInput(runSplit);
			} else if (ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
				bikeSplit = correctTimeInput(bikeSplit);
			} else if (ResultEntity.OVERALL.equalsIgnoreCase(clientname)) {
				time = correctTimeInput(time);
			} else {
				return;
			}
		}
	}

	/**
	 * Call athlete selection pop up
	 *
	 * @param event
	 */
	public void onAthleteSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		entitySelectionUI.buildData(Entity.MYSCOUTEDATHLETES);
		entitySelectionUI.prepareCallback(new ISelectionCallback() {
			public void cancel() {
				m_popup.close();
			}

			public void selected(String id) {
				athlete = (Persons) ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				m_popup.close();
			}
		});
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), "Athletensuche", 800, 600, this);
	}

	public void onLoadAthlete(ActionEvent event) {
		// check preconditions
		if (athlete == null) {
			Statusbar.outputError("No athlete selected!");
			return;
		}
		// initialize grid
		m_gridImportAthlete.getItems().clear();
		// select all competitions with resultlist
		List<Competitions> competitions = getLogic().getCompetitionLogic().getMyComeptitionsWithResults();
		// build grid
		for (Competitions competition : competitions) {
			// get template
			ImportTemplates templateObj = myTemplates.get(competition.getResultsTemplate());
			if (templateObj == null) {
				// TODO template not found handle error
				continue;
			}
			// set Mapping
			startRow = templateObj.getStartingRow();
			String strMapping = templateObj.getMapping();
			strMapping = strMapping.substring(1, strMapping.length() - 1);
			String[] mapping = strMapping.split(", ");
			rowPosition = Integer.valueOf(mapping[0]);
			rowTime = Integer.valueOf(mapping[1]);
			rowAthleteFirstname = Integer.valueOf(mapping[2]);
			rowAthleteLastname = Integer.valueOf(mapping[3]);
			rowSwimSplit = Integer.valueOf(mapping[4]);
			rowBikeSplit = Integer.valueOf(mapping[5]);
			rowRunSplit = Integer.valueOf(mapping[6]);
			// load excel
			if(!readResultExcel(competition.getResults().getFileContent(), TYPE.ATHLETE, competition)) {
				// TODO athlete not found
				continue;
			}
		}
		Statusbar.outputAlert(m_gridImportAthlete.getItems().size() + Constants.WHITESPACE + "Items found!");
		if(m_gridImportAthlete.getItems().size()>0) {
			statusImportData = true;
		} else {
			statusImportData = false;
		}
	}

	public void onImportAthlete(ActionEvent event) {
		// check preconditions
		if (isEmpty(category)) {
			Statusbar.outputAlert("You have to choose a category!");
			return;
		}
		// check if data to import exists / any item marked?
		final Set<GridImportAthleteItem> items = m_gridImportAthlete.getSelectedItems();
		if (isEmpty(items)) {
			Statusbar.outputAlert("No data to import, or no data selected!");
			return;
		}
		YESNOPopup ynp = YESNOPopup.createInstance("Import Results", "Do you really want to import marked results?", new IYesNoCancelListener() {
			public void reactOnCancel() {
			}

			public void reactOnNo() {
			}

			public void reactOnYes() {
				// update results
				int success = 0;
				int error = 0;
				for (GridImportAthleteItem item : items) {
					// create
					if (getLogic().getImportLogic().createOrUpdateResult(item.getCompetition(), category, getAthleteDescription(), getAthlete().getId(), item.getPosition(), item.getTime(),
							item.getSwimSplit(), item.getRunSplit(), item.getBikeSplit(), item.getSwimPosition(), item.getRunPosition(), item.getBikePosition())) {
						success++;
					} else {
						error++;
					}
				}
				// output Result
				Statusbar.outputAlert("\nImport done!", "Import", "Successfull : " + success + "\nErroreous   : " + error);
				// Refresh WPFunctionTree
				reloadFunctionTree();
			}
		});
		ynp.getModalPopup().setLeftTopReferenceCentered();
	}

	private boolean readResultExcel(byte[] bytes, TYPE type, Competitions competition) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			// create workbook
			HSSFWorkbook wb = new HSSFWorkbook(bais);
			// get first worksheet
			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows <= startRow) {
				return false;
			}
			List<String> swimSplits = new ArrayList<String>(rows-startRow);
			List<String> runSplits = new ArrayList<String>(rows-startRow);
			List<String> bikeSplits = new ArrayList<String>(rows-startRow);
			String swimSplitAhtlete = null;
			String runSplitAhtlete = null;
			String bikeSplitAhtlete = null;
			boolean foundAthlete = false;
			GridImportAthleteItem itemAthlete = null;
			for (int r = startRow; r < rows; r++) {
				HSSFRow row = sheet.getRow(r);
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
				String athlete = firstName.trim() + Constants.WHITESPACE + lastName.trim();
				PersonEntity.Data scoutedAthlete = scoutedAthletesData.get(athlete);
				if (rowSwimSplit > 0) {
					swimSplit = row.getCell(rowSwimSplit - 1).toString();
					if (position > 0 && !Constants.EMPTYTIME.equals(time)) swimSplits.add(swimSplit);
				}
				if (rowBikeSplit > 0) {
					bikeSplit = row.getCell(rowBikeSplit - 1).toString();
					if (position > 0 && !Constants.EMPTYTIME.equals(time)) bikeSplits.add(bikeSplit);
				}
				if (rowRunSplit > 0) {
					runSplit = row.getCell(rowRunSplit - 1).toString();
					if (position > 0 && !Constants.EMPTYTIME.equals(time)) runSplits.add(runSplit);
				}
				if (rowTime > 0)
					time = row.getCell(rowTime - 1).toString();
				// handling different for types
				if(type==TYPE.COMPETITION) {
					m_gridImport.getItems().add(new GridImportItem(athlete, scoutedAthlete, position, time, swimSplit, bikeSplit, runSplit));
				} else if (type==TYPE.ATHLETE) {
					if(athlete.equalsIgnoreCase(getAthleteDescription())) {
						swimSplitAhtlete = swimSplit;
						runSplitAhtlete = runSplit;
						bikeSplitAhtlete = bikeSplit;
						itemAthlete = new GridImportAthleteItem(competition, position, time, swimSplit, bikeSplit, runSplit);
						m_gridImportAthlete.getItems().add(itemAthlete);
						foundAthlete = true;
					}
				}
			}
			// handling different for types
			if(type==TYPE.ATHLETE && foundAthlete) {
				Collections.sort(swimSplits);
				itemAthlete.setSwimPosition(swimSplits.indexOf(swimSplitAhtlete)+1);
				Collections.sort(runSplits);
				itemAthlete.setRunPosition(runSplits.indexOf(runSplitAhtlete)+1);
				Collections.sort(bikeSplits);
				itemAthlete.setBikePosition(bikeSplits.indexOf(bikeSplitAhtlete)+1);
			} else if(type==TYPE.COMPETITION) {
				// get best values by sorting, filter out DNF and DSQ
				List<GridImportItem> itemsCopy = new ArrayList<GridImportItem>();
				for (GridImportItem item : m_gridImport.getItems()) {
					// mark all entries, where a scouted athlete was found
					if (!isEmpty(item.getScoutedAthlete()))
						m_gridImport.selectItem(item);
					if (item.getPosition() == 0 || Constants.EMPTYTIME.equals(item.getTime()))
						continue;
					itemsCopy.add(item);
				}
				// sort for swim desc
				if (rowSwimSplit > 0) {
					Collections.sort(itemsCopy, new Comparator<GridImportItem>() {
						public int compare(GridImportItem o1, GridImportItem o2) {
							int seconds1 = calculateSeconds(o1.getSwimSplit());
							int seconds2 = calculateSeconds(o2.getSwimSplit());
							if (seconds1 == seconds2)
								return 0;
							if (seconds1 > seconds2)
								return 1;
							return -1;
						}
					});
					// set swim position
					int position = 1;
					for (GridImportItem item : itemsCopy) {
						item.setSwimPosition(position++);
					}
					// set best
					bestSwimmer = itemsCopy.get(0).getAthlete();
					bestSwimSplit = itemsCopy.get(0).getSwimSplit();
					importBestSwim = true;
				}
				// sort for bike desc
				if (rowBikeSplit > 0) {
					Collections.sort(itemsCopy, new Comparator<GridImportItem>() {
						public int compare(GridImportItem o1, GridImportItem o2) {
							int seconds1 = calculateSeconds(o1.getBikeSplit());
							int seconds2 = calculateSeconds(o2.getBikeSplit());
							if (seconds1 == seconds2)
								return 0;
							if (seconds1 > seconds2)
								return 1;
							return -1;
						}
					});
					// set bike position
					int position = 1;
					for (GridImportItem item : itemsCopy) {
						item.setBikePosition(position++);
					}
					// set best
					bestBiker = itemsCopy.get(0).getAthlete();
					bestBikeSplit = itemsCopy.get(0).getBikeSplit();
					importBestBike = true;
				}
				// sort for run desc
				if (rowRunSplit > 0) {
					Collections.sort(itemsCopy, new Comparator<GridImportItem>() {
						public int compare(GridImportItem o1, GridImportItem o2) {
							int seconds1 = calculateSeconds(o1.getRunSplit());
							int seconds2 = calculateSeconds(o2.getRunSplit());
							if (seconds1 == seconds2)
								return 0;
							if (seconds1 > seconds2)
								return 1;
							return -1;
						}
					});
					// set run position
					int position = 1;
					for (GridImportItem item : itemsCopy) {
						item.setRunPosition(position++);
					}
					// set best
					bestRunner = itemsCopy.get(0).getAthlete();
					bestRunSplit = itemsCopy.get(0).getRunSplit();
					importBestRun = true;
				}
				// set status
				statusMapping = false;
				statusImportData = true;
				Statusbar.outputSuccess(m_gridImport.getItems().size() + Constants.WHITESPACE + "Items found!");
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
