package trimatrix.ui;

import static trimatrix.utils.Helper.isEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Attachments;
import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.ImportTemplates;
import trimatrix.db.Results;
import trimatrix.entities.CompetitionEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.ImportLogic;
import trimatrix.logic.helper.Limit;
import trimatrix.structures.SListVariant;
import trimatrix.ui.ResultsImportUI.GridImportItem;
import trimatrix.ui.utils.IPopUpCallback2;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.CompetitionDetailUI}")
public class CompetitionDetailUI extends AEntityDetailUI implements Serializable {

    private final static String COMPETITIONLIMITS = "COMPETITIONLIMITS";

    private List<String> resultsToDelete = new ArrayList<String>();

    private List<Attachments> resultsToSave = new ArrayList<Attachments>();

    protected FIXGRIDListBinding<GridLimitsItem> m_gridLimits = new FIXGRIDListBinding<GridLimitsItem>();

    public FIXGRIDListBinding<GridLimitsItem> getGridLimits() {
        return m_gridLimits;
    }

    public void setGridLimits(FIXGRIDListBinding<GridLimitsItem> value) {
        m_gridLimits = value;
    }

    public class GridLimitsItem extends FIXGRIDItem implements java.io.Serializable {
        private boolean isNew;
        private Limit limit;
        public Limit getLimit() {return limit;}

        public GridLimitsItem() {
            isNew = true;
            limit = getLogic().getCompetitionLogic().createLimit();
            // check defaulting
            String strTolerance = getServiceLayer().getDefaultValueBindingService().getDVBinding("tolerance");
            if(!isEmpty(strTolerance) && StringUtils.isNumeric(strTolerance)) {
                Integer tolerance = Integer.valueOf(strTolerance);
                limit.getTolerances()[0] = tolerance;	//swim
                limit.getTolerances()[1] = tolerance;	//run
                limit.getTolerances()[2] = tolerance;	//bike
            }
        }

        public GridLimitsItem(Limit limit) {
            isNew = false;
            this.limit = limit;
        }

        public String getCategory() {return limit.getCategory();}
        public void setCategory(String category) {limit.setCategory(category);}

        public Boolean getSwimsuit() {return limit.getSwimsuit();}
        public void setSwimsuit(Boolean swimsuit) {limit.setSwimsuit(swimsuit);}

        public String getCutoffSwim() {return limit.getLimits()[0];}
        public void setCutoffSwim(String cutoff) {
            // check pattern because its possible to enter time or percentage
            if(!Pattern.compile("(\\d\\d:[0-5]\\d:[0-5]\\d)|(\\d*%)").matcher(cutoff).matches()) {
                cutoff = HelperTime.correctTimeInput(cutoff);
            }
            // only set value if cutoff is not null or empty
            if(!isEmpty(cutoff)) limit.getLimits()[0] = cutoff;
        }

        public String getCutoffRun() {return limit.getLimits()[1];}
        public void setCutoffRun(String cutoff) {
            // check pattern because its possible to enter time or percentage
            if(!Pattern.compile("(\\d\\d:[0-5]\\d:[0-5]\\d)|(\\d*%)").matcher(cutoff).matches()) {
                cutoff = HelperTime.correctTimeInput(cutoff);
            }
            // only set value if cutoff is not null or empty
            if(!isEmpty(cutoff)) limit.getLimits()[1] = cutoff;
        }

        public String getCutoffBike() {return limit.getLimits()[2];}
        public void setCutoffBike(String cutoff) {
            // check pattern because its possible to enter time or percentage
            if(!Pattern.compile("(\\d\\d:[0-5]\\d:[0-5]\\d)|(\\d*%)").matcher(cutoff).matches()) {
                cutoff = HelperTime.correctTimeInput(cutoff);
            }
            // only set value if cutoff is not null or empty
            if(!isEmpty(cutoff)) limit.getLimits()[2] = cutoff;
        }

        public String getSwimAthlet() { return limit.getSwim()[0]; }
        public void setSwimAthlet(String athlet) {limit.getSwim()[0] = athlet; }

        public String getRunAthlet() { return limit.getRun()[0]; }
        public void setRunAthlet(String athlet) {limit.getRun()[0] = athlet; }

        public String getBikeAthlet() { return limit.getBike()[0]; }
        public void setBikeAthlet(String athlet) {limit.getBike()[0] = athlet; }

        public String getSwimSplit() { return limit.getSwim()[1]; }
        public void setSwimSplit(String split) {
            String corrInput = HelperTime.correctTimeInput(split);
            if(!isEmpty(corrInput)) limit.getSwim()[1] = corrInput;
        }

        public String getRunSplit() { return limit.getRun()[1]; }
        public void setRunSplit(String split) {
            String corrInput = HelperTime.correctTimeInput(split);
            if(!isEmpty(corrInput)) limit.getRun()[1] = corrInput;
        }

        public String getBikeSplit() { return limit.getBike()[1]; }
        public void setBikeSplit(String split) {
            String corrInput = HelperTime.correctTimeInput(split);
            if(!isEmpty(corrInput)) limit.getBike()[1] = corrInput;
        }

        public Integer getTolSwim() {return limit.getTolerances()[0];}
        public void setTolSwim(Integer tol) {
            limit.getTolerances()[0] = tol;
        }

        public Integer getTolRun() {return limit.getTolerances()[1];}
        public void setTolRun(Integer tol) {
            limit.getTolerances()[1] = tol;
        }

        public Integer getTolBike() {return limit.getTolerances()[2];}
        public void setTolBike(Integer tol) {
            limit.getTolerances()[2] = tol;
        }
        /**
         * F4 Help for category fieldcombo Values are selected from view
         * categories
         *
         * @param event
         */
        public void onCategoryF4(ActionEvent event) {
            getCategoryF4(this);
        }

        public void onAddResultList(ActionEvent event) {
            // open popup
            ResultsListPopUp resultsListPopUp = getResultsListPopUp();
            resultsListPopUp.prepareCallback(new IPopUpCallback2() {

                @Override
                public void ok(Object object, Object object2) {
                    // remember the id to check if we have to delete the list
                    String resultId = limit.getResultsId();
                    Attachments resultList = (Attachments)object;
                    if(resultList!=null) resultsToSave.add(resultList);
                    limit = (Limit)object2;
                    // check deletion
                    if(resultId!=null && limit.getResultsId()==null) resultsToDelete.add(resultId);
                    m_popup.close();
                }

                @Override
                public void cancel() {
                	m_popup.close();
                }
            }, entity, limit);
            m_popup = getWorkpage().createModalPopupInWorkpageContext();
            m_popup.setLeftTopReferenceCentered();
            m_popup.setUndecorated(true);
            m_popup.open(Constants.Page.RESULTSLISTPOPUP.getUrl(), Helper.getLiteral("resultlist"), 520, 160, CompetitionDetailUI.this);
        }

        public String getResultsListIcon() {
            if(Helper.isEmpty(limit.getResultsId())) {
                return Constants.ADD;
            } else {
                return Constants.ACCEPT;
            }
        }

        public boolean isResultListActive() {
            return !Helper.isEmpty(limit.getCategory()) && getEnabled();
        }

        public boolean isCategoryChangeable() {
            return Helper.isEmpty(limit.getResultsId()) && getEnabled();
        }
    }

    public void onChangeType(ActionEvent event) {
        // hard wired defaulting
        String type = (String)values.get(CompetitionEntity.TYPE);
        if(CompetitionEntity.TRIATHLON.equals(type)) {
            values.put(CompetitionEntity.SUBTYPE, "olympic"); //olympic distance
        } else if(CompetitionEntity.XTERRA.equals(type)) {
            values.put(CompetitionEntity.SUBTYPE, "short"); //shortdistance
        }
    }

    public void getCategoryF4(final GridLimitsItem item) {
        IdTextSelection idts = IdTextSelection.createInstance();
        for (String category : getLogic().getCompetitionLogic().getCategories()) {
            idts.addLine(category, "");
        }
        idts.setCallBack(new ISetId() {
            public void setId(String id) {
                item.setCategory(id);
            }
        });
        idts.setWithHeader(false);
        idts.setSuppressHeadline(true);
        idts.setRenderTextColumn(false);
        idts.setPopupWidth(120);
        idts.setPopupHeight(100);
    }

    public void onRemoveLimit(ActionEvent event) {
        GridLimitsItem selectedItem = m_gridLimits.getSelectedItem();
        if(selectedItem==null) return;
        String resultsId = selectedItem.getLimit().getResultsId();
        resultsToDelete.add(resultsId);
        m_gridLimits.getItems().remove(selectedItem);
    }

    public void onAddLimit(ActionEvent event) {
        m_gridLimits.getItems().add(new GridLimitsItem());
    }

    public void onCreateResults(ActionEvent event) {
    	StringBuffer log = new StringBuffer();
    	List<IEntityData> entitiesData = getLogic().getFunctionTreeLogic().getMyScoutedAthletes();
    	Map<String, PersonEntity.Data> scoutedAthletesData = new HashMap<String, PersonEntity.Data>(entitiesData.size());
        for (IEntityData entityData : entitiesData) {
            PersonEntity.Data data = (PersonEntity.Data) entityData;
            scoutedAthletesData.put(data.toString(), data);
        }
        Map<String, ImportTemplates> myTemplates = getLogic().getImportLogic().getTemplatesData(Entity.RESULT);

    	for(GridLimitsItem item : getGridLimits().getItems()) {
            String resultId = item.getLimit().getResultsId();
            if(Helper.isEmpty(resultId)) continue;
            Attachments resultList = getDaoLayer().getAttachmentsDAO().findById(resultId);
            try {
            	ImportLogic.ResultListData resultListData = getLogic().getImportLogic().readResultExcel(resultList.getFileContent(), myTemplates.get(item.getLimit().getResultsTemplate()));
                for(ImportLogic.ResultListData.Data data : resultListData.data) {
                	PersonEntity.Data scoutedAthlete = scoutedAthletesData.get(data.athlete);
                    if (scoutedAthlete==null) continue;
                    getLogic().getImportLogic().createOrUpdateResult(entity, item.getLimit().getCategory(), data.athlete, scoutedAthlete.getId(), data.position, data.time, data.swimSplit,
                            data.runSplit, data.bikeSplit, data.swimPosition, data.runPosition, data.bikePosition);
                }
            } catch (Exception ex) {
            	log.append(ex.toString() + Constants.NEWLINE);
			}
        }
    	Statusbar.outputAlert("\n" + Helper.getMessages("import_done"), Helper.getLiteral("info"), log.toString()).setLeftTopReferenceCentered();
    }

    public boolean isResultList() {
    	return entity.getResults();
    }

    protected ValidValuesBinding m_compTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COMPTYPE);
    public ValidValuesBinding getCompTypesVvb() {return m_compTypesVvb;}

    protected String type;

    protected ValidValuesBinding m_compSubtypesVvb;
    public ValidValuesBinding getCompSubtypesVvb() {
        // lazy loading
        String type = (String) values.get(CompetitionEntity.TYPE);
        if(isEmpty(type)) return null;
        if(!type.equals(this.type)) m_compSubtypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COMPSUBTYPE, type);
        return m_compSubtypesVvb;
    }

    public boolean isMyCompetition() {
        if (entityDetailUI.getEntity() == Entity.SCOUTCOMPETITIONS)
            return true;
        return false;
    }

    public boolean isTypeEnabled() {
        Results result = new Results();
        result.setCompetitionId(entity.getId());
        List<Results> results = getDaoLayer().getResultsDAO().findByExample(result);
        if ((results == null || results.size() == 0) && getEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    private Competitions entity;
    private CompetitionsScouts entityCS;
    //private Attachments resultList;


    public CompetitionDetailUI(IWorkpageDispatcher dispatcher) {
        super(dispatcher, new String[] { CompetitionEntity.DESCRIPTION, CompetitionEntity.TYPE, CompetitionEntity.DATE }, true);
        // define defaults mapping
        defaults.put(CompetitionEntity.TYPE, "comptype");
        defaults.put(CompetitionEntity.SUBTYPE, "compsubtype");
        // get wrapping entity detail UI bean
        entityDetailUI = getEntityDetailUI();
        entityDetailUI.setEntityDetailUI(this);
        // init data
        init(entityDetailUI.getEntityObject());
        // labeling
        setLabelRowDynamic();
    }

    public void init(Object entityObject) {
        // set entity object
        entity = (Competitions) entityObject;
        // set entity Competition Scout object
        entityCS = ENTITYLISTLOGIC.getCompetitionScouts(entity.getId());
        // set enabled state and set fields
        init();
    }

    public void init() {
        // set fields
        fillMaps();
        // set state
        setState();
        // set defaults
        setDefaults();
        // reset list for resultlists to delete
        resultsToDelete.clear();
    }

    public void validate() throws MandatoryCheckException, EmailNotValidException {
        // mandatory check
        checkMandatory();
        // fill values to entities properties
        fillEntityProperties();
        // save scout part, always at the end of validation!!
        if (entityCS != null) {
            getLogic().getCompetitionLogic().saveCompetitionScouts(entityCS);
        }
    }

    private void fillEntityProperties() {
        entity.setDescription((String) values.get(CompetitionEntity.DESCRIPTION));
        entity.setType((String) values.get(CompetitionEntity.TYPE));
        entity.setSubtype((String) values.get(CompetitionEntity.SUBTYPE));
        entity.setDate((Date) values.get(CompetitionEntity.DATE));
        entity.setAddress((String) values.get(CompetitionEntity.ADDRESS));
        entity.setCountryKey((String) values.get(CompetitionEntity.COUNTRY));
        // result list
        boolean existsResult = false;
        for (GridLimitsItem item : m_gridLimits.getItems()) {
            if(!Helper.isEmpty(item.getLimit().getResultsId())) {
            	existsResult = true;
            	break;
            }
        }
        entity.setResults(existsResult);
        // create CS entity if relevant;
        if (isMyCompetition() && entityCS == null) {
            if (getLogic().getFunctionTreeLogic().createCompetitionScout(entity.getId()))
                entityCS = ENTITYLISTLOGIC.getCompetitionScouts(entity.getId());
        }
        // scout part
        if (entityCS != null) {
            List<Limit> limits = new ArrayList<Limit>();
            for (GridLimitsItem item : m_gridLimits.getItems()) {
                limits.add(item.getLimit());
            }
            entityCS.setLimits(getLogic().getCompetitionLogic().buildString(limits));
        }
    }

    private void fillMaps() {
        // add values of fields
        values.clear();
        values.put(CompetitionEntity.DESCRIPTION, entity.getDescription());
        values.put(CompetitionEntity.TYPE, entity.getType());
        values.put(CompetitionEntity.SUBTYPE, entity.getSubtype());
        values.put(CompetitionEntity.DATE, entity.getDate());
        values.put(CompetitionEntity.ADDRESS, entity.getAddress());
        values.put(CompetitionEntity.COUNTRY, entity.getCountryKey());
        // scout part
        m_gridLimits.getItems().clear();
        if (entityCS != null) {
            List<Limit> limits = getLogic().getCompetitionLogic().getLimits(entityCS.getLimits());
            for (Limit limit : limits) {
                m_gridLimits.getItems().add(new GridLimitsItem(limit));
            }
            // load grid state
            loadGridState();
        }
        // add bgpaint of fields
        bgpaint.clear();
        // mandatory fields
        for (String field : MANDATORY_FIELDS) {
            bgpaint.put(field, Constants.BGP_MANDATORY);
        }
    }

    @Override
    public void postSave() {
        super.postSave();
        // append ID to Preferences and
        for(GridLimitsItem item : m_gridLimits.getItems()) {
            getLogic().getCompetitionLogic().addCategoriesToPreferences(item.getCategory());
        }
        // save result lists
        for(Attachments resultList : resultsToSave) {
            getDaoLayer().getAttachmentsDAO().save(resultList);
        }
        resultsToSave.clear();

        // delete result lists
        for(String resultsId : resultsToDelete) {
            Attachments resultList = getDaoLayer().getAttachmentsDAO().findById(resultsId);
            getDaoLayer().getAttachmentsDAO().delete(resultList);
        }
        resultsToDelete.clear();
    }

    public void saveGridState(ActionEvent event) {
        SListVariant lv = new SListVariant(m_gridLimits.getColumnsequence(), m_gridLimits.getModcolumnwidths());
        ENTITYLISTLOGIC.saveGridState(Constants.P_LIST, COMPETITIONLIMITS, lv);
    }

    private void loadGridState() {
        SListVariant lv = ENTITYLISTLOGIC.loadGridState(Constants.P_LIST, COMPETITIONLIMITS);
        if(lv==null) return;
        m_gridLimits.setColumnsequence(lv.columnsSequence);
        m_gridLimits.setModcolumnwidths(lv.columnsWidth);
    }

    @Override
    public void postDelete(boolean isDeleted) {
        // mark all attached resultlists as deleted
        for(GridLimitsItem item : getGridLimits().getItems()) {
            String resultId = item.getLimit().getResultsId();
            if(Helper.isEmpty(resultId)) continue;
            Attachments resultList = getDaoLayer().getAttachmentsDAO().findById(resultId);
            getDaoLayer().getAttachmentsDAO().delete(resultList);
        }
    }

    /*
     * You just can edit your own competitions
     */
    @Override
    public boolean getEnabled() {
        return super.getEnabled() && ENTITYLISTLOGIC.isUserEqualUserLoggedOn(entity.getCreatedBy());
    }

}