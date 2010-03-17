package trimatrix.ui;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.db.ImportTemplates;
import trimatrix.db.PersonsAthlete;
import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.ResultEntity;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

@CCGenClass (expressionBase="#{d.ResultsImportUI}")

public class ResultsImportUI extends MyWorkpageDispatchedBean implements Serializable {
	private static final Constants.Entity ENTITY = Constants.Entity.RESULT;

    protected FIXGRIDListBinding<GridImportItem> m_gridImport = new FIXGRIDListBinding<GridImportItem>();
    public FIXGRIDListBinding<GridImportItem> getGridImport() { return m_gridImport; }
    public void setGridImport(FIXGRIDListBinding<GridImportItem> value) { m_gridImport = value; }

    public class GridImportItem extends FIXGRIDItem implements java.io.Serializable {
        private String athlete;
        private PersonsAthlete scoutedAthlete;
        private int position;
        private String time;
        private String swimSplit;
        private String runSplit;
        private String bikeSplit;

        public GridImportItem(String athlete, int position, String time, String swimSplit, String bikeSplit, String runSplit) {
            this.athlete = athlete;
            this.position = position;
            this.time = time;
            this.swimSplit = swimSplit;
            this.bikeSplit = bikeSplit;
            this.runSplit = runSplit;
        }

        public String getAthlete() {return athlete;}
        public void setAthlete(String athlete) {this.athlete = athlete;}

        public int getPosition() {return position;}
        public void setPosition(int position) {this.position = position;}

        public String getTime() {return time;}
		public void setTime(String time) {this.time = time;}

		public String getSwimSplit() {return swimSplit;}
		public void setSwimSplit(String swimSplit) {this.swimSplit = swimSplit;}

		public String getRunSplit() {return runSplit;}
		public void setRunSplit(String runSplit) {this.runSplit = runSplit;}

		public String getBikeSplit() {return bikeSplit;}
		public void setBikeSplit(String bikeSplit) {this.bikeSplit = bikeSplit;}

		public void onScoutedAthleteF4(ActionEvent event) {
            IdTextSelection idts = IdTextSelection.createInstance();
            for (PersonEntity.Data data : scoutedAthletesData) {
                idts.addLine(data.getId(), data.toString());
            }
            idts.setCallBack(new ISetId() {
                public void setId(String id) {
                    scoutedAthlete = getDaoLayer().getPersonAthleteDAO().findById(id);
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
	        if(ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
	            swimSplit = Helper.correctTimeInput(swimSplit);
	        } else if(ResultEntity.RUN.equalsIgnoreCase(clientname)) {
	            runSplit = Helper.correctTimeInput(runSplit);
	        } else if(ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
	            bikeSplit = Helper.correctTimeInput(bikeSplit);
	        } else if(ResultEntity.OVERALL.equalsIgnoreCase(clientname)) {
	            time = Helper.correctTimeInput(time);
	        } else {
	            return;
	        }
	    }

    }


    protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();

    private Competitions competition;
    public String getCompDesc() { return competition!=null ? competition.toString() : null; }

    private String category;
    public String getCategory() {return category;}

    private String template;
    public String getTemplate() {return template;}
	public void setTemplate(String template) {this.template = template;}

	private String filename;
    public String getFilename() {return filename;}

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

    public ResultsImportUI(IWorkpageDispatcher dispatcher) {
        super(dispatcher);
        buildScoutedAthletesData();
        buildTemplatesData();
        // set initial status
        statusMapping = true;
        statusImportData = false;
    }

    public String getBestSwimmer() {return bestSwimmer;}
    public void setBestSwimmer(String bestSwimmer) {this.bestSwimmer = bestSwimmer;}

    public String getBestSwimSplit() {return bestSwimSplit;}
    public void setBestSwimSplit(String bestSwimSplit) {this.bestSwimSplit = bestSwimSplit;}

    public String getBestBiker() {return bestBiker;}
    public void setBestBiker(String bestBiker) {this.bestBiker = bestBiker;}

    public String getBestBikeSplit() {return bestBikeSplit;}
    public void setBestBikeSplit(String bestBikeSplit) {this.bestBikeSplit = bestBikeSplit;}

    public String getBestRunner() {return bestRunner;}
    public void setBestRunner(String bestRunner) {this.bestRunner = bestRunner;}

    public String getBestRunSplit() {return bestRunSplit;}
    public void setBestRunSplit(String bestRunSplit) {this.bestRunSplit = bestRunSplit;}

    public int getStartRow() {return startRow;}
	public void setStartRow(int startRow) {this.startRow = startRow;}

	public int getRowPosition() {return rowPosition;}
	public void setRowPosition(int rowPosition) {this.rowPosition = rowPosition;}

	public int getRowTime() {return rowTime;}
	public void setRowTime(int rowTime) {this.rowTime = rowTime;}

	public int getRowAthleteFirstname() {return rowAthleteFirstname;}
	public void setRowAthleteFirstname(int rowAthleteFirstname) {this.rowAthleteFirstname = rowAthleteFirstname;}

	public int getRowAthleteLastname() {return rowAthleteLastname;}
	public void setRowAthleteLastname(int rowAthleteLastname) {this.rowAthleteLastname = rowAthleteLastname;}

	public int getRowSwimSplit() {return rowSwimSplit;}
	public void setRowSwimSplit(int rowSwimSplit) {this.rowSwimSplit = rowSwimSplit;}

	public int getRowBikeSplit() {return rowBikeSplit;}
	public void setRowBikeSplit(int rowBikeSplit) {this.rowBikeSplit = rowBikeSplit;}

	public int getRowRunSplit() {return rowRunSplit;}
	public void setRowRunSplit(int rowRunSplit) {this.rowRunSplit = rowRunSplit;}

	public boolean isImportBestSwim() {return importBestSwim;}
	public void setImportBestSwim(boolean importBestSwim) {this.importBestSwim = importBestSwim;}

	public boolean isImportBestBike() {return importBestBike;}
	public void setImportBestBike(boolean importBestBike) {this.importBestBike = importBestBike;}

	public boolean isImportBestRun() {return importBestRun;}
	public void setImportBestRun(boolean importBestRun) {this.importBestRun = importBestRun;}

	public boolean isStatusMapping() {return statusMapping;}
	public void setStatusMapping(boolean statusMapping) {this.statusMapping = statusMapping;}

	public boolean isStatusImportData() {return statusImportData;}
	public void setStatusImportData(boolean statusImportData) {this.statusImportData = statusImportData;}

	private List<PersonEntity.Data> scoutedAthletesData;

    private void buildScoutedAthletesData() {
    	List<IEntityData> entitiesData = getLogic().getFunctionTreeLogic().getMyScoutedAthletes();
    	scoutedAthletesData = new ArrayList<PersonEntity.Data>(entitiesData.size());
    	for (IEntityData entityData : entitiesData) {
    		PersonEntity.Data data = (PersonEntity.Data)entityData;
    		scoutedAthletesData.add(data);
        }
    }

    private Map<String, ImportTemplates> myTemplates = new HashMap<String, ImportTemplates>();

    public void buildTemplatesData() {
    	List<ImportTemplates> templatesList = getLogic().getImportLogic().getMyTemplates(ENTITY.toString());
    	if(Helper.isEmpty(templatesList)) return;
    	for(ImportTemplates template : templatesList) {
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
     * @param event
     */
    public void onCompetitionSearch(ActionEvent event) {
        IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.COMPETITION);
        entitySelectionUI.buildData(Entity.SCOUTCOMPETITIONS);

           entitySelectionUI.prepareCallback(new ISelectionCallback(){
            public void cancel() {
                m_popup.close();
            }
            public void selected(String id) {
                competition = (Competitions)ENTITYLISTLOGIC.get(Constants.Entity.COMPETITION, id);
                m_popup.close();
            }});
        m_popup = getWorkpage().createModalPopupInWorkpageContext();
        m_popup.setLeftTopReferenceCentered();
        m_popup.open(Constants.Page.COMPETITIONSELECTION.getUrl(), "Wettkampfsuche", 800, 600, this);
    }

    /**
     * F4 Help for categories depending on competition
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
     	for(ImportTemplates template : myTemplates.values()) {
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
    	if(templateObj==null) return;
    	// set Mapping
    	startRow = templateObj.getStartingRow();
    	String strMapping = templateObj.getMapping();
    	strMapping = strMapping.substring(1, strMapping.length()-1);
    	String[] mapping = strMapping.split(", ");
    	rowPosition = Integer.valueOf(mapping[0]);
    	rowTime = Integer.valueOf(mapping[1]);
    	rowAthleteFirstname = Integer.valueOf(mapping[2]);
    	rowAthleteLastname = Integer.valueOf(mapping[3]);
    	rowSwimSplit  = Integer.valueOf(mapping[4]);
    	rowBikeSplit = Integer.valueOf(mapping[5]);
    	rowRunSplit = Integer.valueOf(mapping[6]);
    }

    public void onTimeFlush(ActionEvent event) {
        // get clientname to separate by source
        String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
        if(ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
            bestSwimSplit = Helper.correctTimeInput(bestSwimSplit);
        } else if(ResultEntity.RUN.equalsIgnoreCase(clientname)) {
            bestRunSplit = Helper.correctTimeInput(bestRunSplit);
        } else if(ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
            bestBikeSplit = Helper.correctTimeInput(bestBikeSplit);
        } else {
            return;
        }
    }

    public void onUploadFile(ActionEvent event) {
        if (event instanceof BaseActionEventUpload) {
            try {
                BaseActionEventUpload bae = (BaseActionEventUpload) event;
                // filename without directory structure
                filename = bae.getClientFileName();
                // Content
                ByteArrayInputStream bais = new ByteArrayInputStream(bae.getHexBytes());
                // create workbook
                HSSFWorkbook wb = new HSSFWorkbook(bais);
                // get first worksheet
                HSSFSheet sheet = wb.getSheetAt(0);
                int rows = sheet.getPhysicalNumberOfRows();
                if(rows<=startRow) {
                    Statusbar.outputError("No rows to import!");
                    return;
                }
                // clear grid
                m_gridImport.getItems().clear();
                for (int r = startRow; r < rows; r++) {
                    HSSFRow row = sheet.getRow(r);
                    // initialize variables
                    int position = 0;
                    String strPosition;
                    String lastName = Constants.EMPTY;
                    String firstName = Constants.EMPTY;
                    String swimSplit = "00:00:00";
                    String bikeSplit = "00:00:00";
                    String runSplit = "00:00:00";
                    String time = "00:00:00";
                    if (row == null) continue;
                    if(rowPosition>0) {
                    	strPosition = row.getCell(rowPosition).getStringCellValue().trim();
                    	position = StringUtils.isNumeric(strPosition) ? Integer.valueOf(strPosition).intValue() : 0;
                    }
                    if(rowAthleteLastname>0) lastName = row.getCell(rowAthleteLastname).getStringCellValue();
                    if(rowAthleteFirstname>0)  firstName = row.getCell(rowAthleteFirstname).getStringCellValue();
                    String athlete = firstName + Constants.WHITESPACE + lastName;
                    if(rowSwimSplit>0) swimSplit = row.getCell(rowSwimSplit).getStringCellValue();
                    if(rowBikeSplit>0) bikeSplit = row.getCell(rowBikeSplit).getStringCellValue();
                    if(rowRunSplit>0) runSplit = row.getCell(rowRunSplit).getStringCellValue();
                    if(rowTime>0) runSplit = row.getCell(rowTime).getStringCellValue();
                    // add to table
                    m_gridImport.getItems().add(new GridImportItem(athlete.trim(), position, time, swimSplit, bikeSplit, runSplit));
                }
                // get best values by sorting
                List<GridImportItem> items = m_gridImport.getItems();
                // sort for swim desc
                if(rowSwimSplit>0) {
                	Collections.sort(items, new Comparator<GridImportItem>() {
    					public int compare(GridImportItem o1, GridImportItem o2) {
    						int seconds1 = Helper.calculateSeconds(o1.getSwimSplit());
    						int seconds2 = Helper.calculateSeconds(o2.getSwimSplit());
    						if(seconds1==seconds2) return 0;
    						if(seconds1<seconds2) return 1;
    						return -1;
    					}
    				});
                    bestSwimmer = items.get(0).getAthlete();
                    bestSwimSplit = items.get(0).getSwimSplit();
                    importBestSwim = true;
                }
                Statusbar.outputSuccess(m_gridImport.getItems().size() + Constants.WHITESPACE + "Items found!");
                // set status
                statusMapping = false;
                statusImportData = true;
            } catch(Exception ex) {
                Statusbar.outputAlert("Error importing File", "Error", ex.toString());
            }
        }
    }

    public void onImport(ActionEvent event) {

    }
}
