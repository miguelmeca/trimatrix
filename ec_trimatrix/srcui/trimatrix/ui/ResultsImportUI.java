package trimatrix.ui;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    protected FIXGRIDListBinding<GridImportItem> m_gridImport = new FIXGRIDListBinding<GridImportItem>();
    public FIXGRIDListBinding<GridImportItem> getGridImport() { return m_gridImport; }
    public void setGridImport(FIXGRIDListBinding<GridImportItem> value) { m_gridImport = value; }

    public class GridImportItem extends FIXGRIDItem implements java.io.Serializable {
        private String athlete;
        private PersonsAthlete scoutedAthlete;
        private int position;

        public GridImportItem(String athlete, int position) {
            this.athlete = athlete;
            this.position = position;
        }

        public String getAthlete() {return athlete;}
        public void setAthlete(String athlete) {this.athlete = athlete;}

        public int getPosition() {return position;}
        public void setPosition(int position) {this.position = position;}

        public void onScoutedAthlete(ActionEvent event) {
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

    }


    protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();

    public String getMappingImage() { return Constants.ACCEPT_LIGHT; }

    private Competitions competition;
    public String getCompDesc() { return competition!=null ? competition.toString() : null; }

    private String category;
    public String getCategory() {return category;}

    private String filename;
    public String getFilename() {return filename;}

    private String bestSwimmer;
    private String bestSwimSplit;

    private String bestBiker;
    private String bestBikeSplit;

    private String bestRunner;
    private String bestRunSplit;

    private int startRow = 1;

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

    private List<PersonEntity.Data> scoutedAthletesData;

    private void buildScoutedAthletesData() {
    	List<IEntityData> entitiesData = getLogic().getFunctionTreeLogic().getMyScoutedAthletes();
    	scoutedAthletesData = new ArrayList<PersonEntity.Data>(entitiesData.size());
    	for (IEntityData entityData : entitiesData) {
    		PersonEntity.Data data = (PersonEntity.Data)entityData;
    		scoutedAthletesData.add(data);
        }
    }

    public ResultsImportUI(IWorkpageDispatcher dispatcher) {
        super(dispatcher);
        buildScoutedAthletesData();
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
            idts.addLine(category, "");
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
                    if (row == null) continue;
                    String strPosition = row.getCell(0).getStringCellValue().trim();
                    int position = StringUtils.isNumeric(strPosition) ? Integer.valueOf(strPosition).intValue() : 0;
                    String lastName = row.getCell(2).getStringCellValue();
                    String firstName = row.getCell(3).getStringCellValue();
                    String athlete = firstName + Constants.WHITESPACE + lastName;
                    // add to table
                    m_gridImport.getItems().add(new GridImportItem(athlete, position));
                }
                Statusbar.outputSuccess(m_gridImport.getItems().size() + Constants.WHITESPACE + "Items found!");
            } catch(Exception ex) {
                Statusbar.outputAlert("Error importing File", "Error", ex.toString());
            }
        }
    }

    public void onImport(ActionEvent event) {

    }
}
