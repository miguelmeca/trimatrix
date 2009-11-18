package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetId;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Categories;
import trimatrix.db.Competitions;
import trimatrix.db.CompetitionsScouts;
import trimatrix.db.Results;
import trimatrix.entities.CompetitionEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.CompetitionLogic.Limit;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.CompetitionDetailUI}")
public class CompetitionDetailUI extends AEntityDetailUI implements Serializable {

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

		public Double getGreenAreaHigh() {
			return limit.getLimits()[0];
		}

		public void setGreenAreaHigh(Double greenAreaHigh) {
			limit.getLimits()[0] = greenAreaHigh;
		}

		public Double getRedAreaLow() {
			return limit.getLimits()[1];
		}

		public void setRedAreaLow(Double redAreaLow) {
			limit.getLimits()[1] = redAreaLow;
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
		for (Categories category : getLogic().getCompetitionLogic().getCategories()) {
			idts.addLine(category.getId().getCategory(), "");
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
			Limit[] limits = getLogic().getCompetitionLogic().getLimits(entityCS.getLimits());
			for (Limit limit : limits) {
				m_gridLimits.getItems().add(new GridLimitsItem(limit));
			}
		}
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for (String field : MANDATORY_FIELDS) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
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