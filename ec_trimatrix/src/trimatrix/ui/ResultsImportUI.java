package trimatrix.ui;

import static trimatrix.utils.Helper.isEmpty;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ActionEvent;

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

import trimatrix.db.Attachments;
import trimatrix.db.Competitions;
import trimatrix.db.ImportTemplates;
import trimatrix.db.Persons;
import trimatrix.db.Results;
import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.ResultEntity;
import trimatrix.logic.EntityListLogic;
import trimatrix.logic.ImportLogic;
import trimatrix.structures.SCompetitionResult;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Page;
import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;

@CCGenClass(expressionBase = "#{d.ResultsImportUI}")
public class ResultsImportUI extends MyWorkpageDispatchedBean implements Serializable {

	private BaseActionEventUpload bae;

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

        public GridImportItem(String athlete, PersonEntity.Data scoutedAthlete, int position, String time, String swimSplit, String bikeSplit, String runSplit, int swimPosition, int bikePosition, int runPosition) {
            this.athlete = athlete;
            this.position = position;
            this.time = time;
            this.swimSplit = swimSplit;
            this.bikeSplit = bikeSplit;
            this.runSplit = runSplit;
            this.swimPosition = swimPosition;
            this.bikePosition = bikePosition;
            this.runPosition = runPosition;
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
                swimSplit = HelperTime.correctTimeInput(swimSplit);
            } else if (ResultEntity.RUN.equalsIgnoreCase(clientname)) {
                runSplit = HelperTime.correctTimeInput(runSplit);
            } else if (ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
                bikeSplit = HelperTime.correctTimeInput(bikeSplit);
            } else if (ResultEntity.OVERALL.equalsIgnoreCase(clientname)) {
                time = HelperTime.correctTimeInput(time);
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

    private Map<String, ImportTemplates> myTemplates;

    public void buildTemplatesData() {
        myTemplates = getLogic().getImportLogic().getTemplatesData(ENTITY);
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
        m_popup.open(Page.COMPETITIONSELECTION.getUrl(), Helper.getLiteral("competition_search"), 800, 600, this);
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
            bestSwimSplit = HelperTime.correctTimeInput(bestSwimSplit);
        } else if (ResultEntity.RUN.equalsIgnoreCase(clientname)) {
            bestRunSplit = HelperTime.correctTimeInput(bestRunSplit);
        } else if (ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
            bestBikeSplit = HelperTime.correctTimeInput(bestBikeSplit);
        } else {
            return;
        }
    }

    public void onUploadFile(ActionEvent event) {
        if (event instanceof BaseActionEventUpload) {
        	// mandatory fields
            if (competition == null || isEmpty(category) || isEmpty(template)) {
                Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
                return;
            }

            bae = (BaseActionEventUpload) event;
            // filename without directory structure
            filename = bae.getClientFileName();
            // clear grid
            m_gridImport.getItems().clear();
            // read excel
            try {
                ImportLogic.ResultListData resultListData = getLogic().getImportLogic().readResultExcel(bae.getHexBytes(), myTemplates.get(template));
                for(ImportLogic.ResultListData.Data data : resultListData.data) {
                    GridImportItem item = new GridImportItem(data.athlete, scoutedAthletesData.get(data.athlete), data.position, data.time, data.swimSplit, data.bikeSplit, data.runSplit, data.swimPosition, data.bikePosition, data.runPosition);
                    m_gridImport.getItems().add(item);
                    // mark in grid if a scouted athlete is found
                    if (!isEmpty(item.getScoutedAthlete())) m_gridImport.selectItem(item);

                }

                bestSwimmer = resultListData.bestSwimmer;
                bestSwimSplit = resultListData.bestSwimSplit;

                bestBiker = resultListData.bestBiker;
                bestBikeSplit = resultListData.bestBikeSplit;

                bestRunner = resultListData.bestRunner;
                bestRunSplit = resultListData.bestRunSplit;

                statusMapping = false;
                statusImportData = true;
            } catch (Exception ex) {
                Statusbar.outputAlert(ex.toString(), Helper.getLiteral("error")).setLeftTopReferenceCentered();
            }
        }
    }

    /**
     * Import results into DB
     *
     * @param event
     */
    public void onImport(ActionEvent event) {
        // mandatory fields
        if (competition == null || isEmpty(category) || isEmpty(template)) {
            Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
            return;
        }
        // check if data to import exists / any item marked?
        final Set<GridImportItem> items = m_gridImport.getSelectedItems();
        final Competitions competition = this.competition;

        YESNOPopup ynp = YESNOPopup.createInstance(Helper.getLiteral("import"), Helper.getMessages("result_import_confirm"), new IYesNoCancelListener() {
            public void reactOnCancel() {
            }

            public void reactOnNo() {
            }

            public void reactOnYes() {
                // create resultlist
            	Integer size = bae.getHexBytes().length;
    			// create new entity
    			Attachments resultList = (Attachments)getEntityLayer().getAttachmentEntity().create();
    			resultList.setIntern(true); // mark attachment for internal use
    			resultList.setCategoryKey("results");	// set category fix to result
    			resultList.setDescription(category); // set limits category to description
    			// filename without directory structure
    			String filename = bae.getClientFileName();
    			resultList.setFileName(filename.substring(filename
    					.lastIndexOf(Constants.FILESEPARATOR) + 1));
    			resultList.setFileSize(size);
    			// Mime type detection
    			ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bae
    					.getHexBytes());
    			Collection<?> mimeTypes = MimeUtil.getMimeTypes(byteInputStream);
    			if (mimeTypes.size() > 0) {
    				resultList.setMimeType(((MimeType) mimeTypes.toArray()[0])
    						.toString());
    			} else {
    				resultList.setMimeType(Constants.UNKNOWN_MIME_TYPE);
    			}
    			// Content
    			resultList.setFileContent(bae.getHexBytes());

            	// update competition
                if (!getLogic().getImportLogic().createOrUpdateCategory(competition.getId(), category, resultList, template, bestSwimmer, bestSwimSplit, bestBiker, bestBikeSplit, bestRunner, bestRunSplit)) {
                    Statusbar.outputAlert(Helper.getMessages("competition_update_failure"), Helper.getLiteral("error")).setLeftTopReferenceCentered();
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
                Statusbar.outputAlert("\n" + Helper.getMessages("import_done"), Helper.getLiteral("info"), String.format(Helper.getMessages("success_failure"), success, error))
                        .setLeftTopReferenceCentered();
                // Refresh WPFunctionTree
                reloadFunctionTree();
             // refresh beans
    	     getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(Entity.COMPETITION));
    	     getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(Entity.RESULT));
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
        private boolean isNew;

        public GridImportAthleteItem(Competitions competition, int position, String time, String swimSplit, String bikeSplit, String runSplit, boolean isNew) {
            this.competition = competition;
            this.position = position;
            this.time = time;
            this.swimSplit = swimSplit;
            this.bikeSplit = bikeSplit;
            this.runSplit = runSplit;
            this.isNew = isNew;
        }

        public boolean isNew() { return isNew; }

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
                swimSplit = HelperTime.correctTimeInput(swimSplit);
            } else if (ResultEntity.RUN.equalsIgnoreCase(clientname)) {
                runSplit = HelperTime.correctTimeInput(runSplit);
            } else if (ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
                bikeSplit = HelperTime.correctTimeInput(bikeSplit);
            } else if (ResultEntity.OVERALL.equalsIgnoreCase(clientname)) {
                time = HelperTime.correctTimeInput(time);
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
        m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), Helper.getLiteral("athlete_search"), 800, 600, this);
    }

    public void onLoadAthlete(ActionEvent event) {
        // mandatory fields
        if (athlete == null || Helper.isEmpty(category)) {
            Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
            return;
        }
        // initialize grid
        m_gridImportAthlete.getItems().clear();
        // select all competitions with resultlist
        List<SCompetitionResult> competitions = getLogic().getCompetitionLogic().getMyComeptitionsWithResults(category);
        // build grid
        for (SCompetitionResult competition : competitions) {
            // get template

            ImportTemplates templateObj = myTemplates.get(competition.resultsTemplate);
            if (templateObj == null) {
                // TODO translation
                Statusbar.outputError(String.format("Template %s not found!", competition.resultsTemplate));
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
            try {
                Attachments resultList = getDaoLayer().getAttachmentsDAO().findById(competition.resultsId);
                ImportLogic.ResultListData resultListData = getLogic().getImportLogic().readResultExcel(resultList.getFileContent(), templateObj);
                for(ImportLogic.ResultListData.Data data : resultListData.data) {
                    if (data.athlete.equalsIgnoreCase(getAthleteDescription())) {
                    	// check if there's already a result?
                    	Results result = new Results();
            			result.setCompetitionId(competition.competition.getId());
            			result.setScoutId(getServiceLayer().getDictionaryService().getMyPerson().getId());
            			result.setAthleteId(athlete.getId());
            			result.setDeleted(false);
            			List<Results> results = getDaoLayer().getResultsDAO().findByExample(result);

                        GridImportAthleteItem itemAthlete = new GridImportAthleteItem(competition.competition, data.position, data.time, data.swimSplit, data.bikeSplit, data.runSplit, isEmpty(results));
                        m_gridImportAthlete.getItems().add(itemAthlete);
                        if (isEmpty(results)) m_gridImportAthlete.selectItem(itemAthlete);
                        break;
                    }
                }
            } catch (Exception ex) {
                Statusbar.outputError(ex.toString());
                continue;
            }
        }
        Statusbar.outputAlert(String.format(Helper.getMessages("items_found"), m_gridImportAthlete.getItems().size()), Helper.getLiteral("info")).setLeftTopReferenceCentered();
        if (m_gridImportAthlete.getItems().size() > 0) {
        	statusImportAthleteData = true;
        } else {
        	statusImportAthleteData = false;
        }
    }

    public void onImportAthlete(ActionEvent event) {
        // check preconditions
        if (isEmpty(category)) {
            Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
            return;
        }
        // check if data to import exists / any item marked?
        final Set<GridImportAthleteItem> items = m_gridImportAthlete.getSelectedItems();
        if (isEmpty(items)) {
            Statusbar.outputAlert(Helper.getMessages("import_no_data"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
            return;
        }
        YESNOPopup ynp = YESNOPopup.createInstance(Helper.getLiteral("import"), Helper.getMessages("result_import_confirm"), new IYesNoCancelListener() {
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
                Statusbar.outputAlert("\n" + Helper.getMessages("import_done"), Helper.getLiteral("info"), String.format(Helper.getMessages("success_failure"), success, error))
                        .setLeftTopReferenceCentered();
                // Refresh WPFunctionTree
                reloadFunctionTree();
                // refresh beans
                getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(Entity.RESULT));
            }
        });
        ynp.getModalPopup().setLeftTopReferenceCentered();
    }

}
