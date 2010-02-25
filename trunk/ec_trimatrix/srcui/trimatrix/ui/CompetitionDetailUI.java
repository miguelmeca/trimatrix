package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.Results;
import trimatrix.entities.CompetitionEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.helper.Limit;
import trimatrix.structures.SListVariant;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.CompetitionDetailUI}")
public class CompetitionDetailUI extends AEntityDetailUI implements Serializable {
	private final static String COMPETITIONLIMITS = "COMPETITIONLIMITS";

	protected FIXGRIDListBinding<GridLimitsItem> m_gridLimits = new FIXGRIDListBinding<GridLimitsItem>();

	public FIXGRIDListBinding<GridLimitsItem> getGridLimits() {
		return m_gridLimits;
	}

	public void setGridLimits(FIXGRIDListBinding<GridLimitsItem> value) {
		m_gridLimits = value;
	}

	public class GridLimitsItem extends FIXGRIDItem implements java.io.Serializable {
		private Limit limit;

		public Limit getLimit() {
			return limit;
		}

		public GridLimitsItem() {
			limit = getLogic().getCompetitionLogic().createLimit();
		}

		public GridLimitsItem(Limit limit) {
			this.limit = limit;
		}

		public String getCategory() {
			return limit.getCategory();
		}

		public void setCategory(String category) {
			limit.setCategory(category);
		}

		public Boolean getSwimsuit() {
			return limit.getSwimsuit();
		}

		public void setSwimsuit(Boolean swimsuit) {
			limit.setSwimsuit(swimsuit);
		}

		public String getCutoffSwim() {
			return limit.getLimits()[0];
		}

		public void setCutoffSwim(String cutoff) {
			// check pattern because its possible to enter time or percentage
			if(!Pattern.compile("(\\d\\d:[0-5]\\d:[0-5]\\d)|(\\d*%)").matcher(cutoff).matches()) {
				cutoff = Helper.correctTimeInput(cutoff);
			}
			// only set value if cutoff is not null or empty
			if(!Helper.isEmpty(cutoff)) limit.getLimits()[0] = cutoff;
		}

		public String getCutoffRun() {
			return limit.getLimits()[1];
		}

		public void setCutoffRun(String cutoff) {
			// check pattern because its possible to enter time or percentage
			if(!Pattern.compile("(\\d\\d:[0-5]\\d:[0-5]\\d)|(\\d*%)").matcher(cutoff).matches()) {
				cutoff = Helper.correctTimeInput(cutoff);
			}
			// only set value if cutoff is not null or empty
			if(!Helper.isEmpty(cutoff)) limit.getLimits()[1] = cutoff;
		}

		public String getSwimAthlet() { return limit.getSwim()[0]; }
		public void setSwimAthlet(String athlet) {limit.getSwim()[0] = athlet; }

		public String getRunAthlet() { return limit.getRun()[0]; }
		public void setRunAthlet(String athlet) {limit.getRun()[0] = athlet; }

		public String getSwimSplit() { return limit.getSwim()[1]; }
		public void setSwimSplit(String split) {
			String corrInput = Helper.correctTimeInput(split);
			if(!Helper.isEmpty(corrInput)) limit.getSwim()[1] = corrInput;
		}

		public String getRunSplit() { return limit.getRun()[1]; }
		public void setRunSplit(String split) {
			String corrInput = Helper.correctTimeInput(split);
			if(!Helper.isEmpty(corrInput)) limit.getRun()[1] = corrInput;
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
		m_gridLimits.getItems().remove(m_gridLimits.getSelectedItem());
	}

	public void onAddLimit(ActionEvent event) {
		m_gridLimits.getItems().add(new GridLimitsItem());
	}

	protected ValidValuesBinding countriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COUNTRY);

	public ValidValuesBinding getCountriesVvb() {
		return countriesVvb;
	}

	protected ValidValuesBinding m_compTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COMPTYPE);

	public ValidValuesBinding getCompTypesVvb() {
		return m_compTypesVvb;
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

	public CompetitionDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] { CompetitionEntity.DESCRIPTION, CompetitionEntity.TYPE, CompetitionEntity.DATE }, true);
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
		entity.setDate((Date) values.get(CompetitionEntity.DATE));
		entity.setAddress((String) values.get(CompetitionEntity.ADDRESS));
		entity.setCountryKey((String) values.get(CompetitionEntity.COUNTRY));
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
		// append ID to Preferences
		for(GridLimitsItem item : m_gridLimits.getItems()) {
			getLogic().getCompetitionLogic().addCategoriesToPreferences(item.getCategory());
		}
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

	/*
	 * You just can edit your own competitions
	 */
	@Override
	public boolean getEnabled() {
		return super.getEnabled() && ENTITYLISTLOGIC.isUserEqualUserLoggedOn(entity.getCreatedBy());
	}
}