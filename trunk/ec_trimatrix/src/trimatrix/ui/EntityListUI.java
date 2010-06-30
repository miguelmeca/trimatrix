package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.IWorkpageProcessingEventListener;
import org.eclnt.workplace.WorkpageProcessingEvent;

import trimatrix.entities.IEntityData;
import trimatrix.logic.EntityListLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SListVariant;
import trimatrix.structures.SRange;
import trimatrix.structures.SSearchMetaData;
import trimatrix.ui.utils.IFIXGRIDCallback;
import trimatrix.ui.utils.MyFIXGRIDListBinding;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.SearchRange;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.EntityListUI}")
public class EntityListUI extends MyWorkpageDispatchedBean implements Serializable, IWorkpageProcessingEventListener {

	public final EntityListUI				entityList		= this;
	private final EntityListLogic			ENTITYLISTLOGIC	= getLogic().getEntityListLogic();
	private List<SGridMetaData>				gridMetaData;
	private Map<String, SSearchMetaData>	searchMetaData;
	private List<IEntityData>				gridData;
	private Constants.Entity				entity;
	private List<SRange<?>>					searchRanges	= new ArrayList<SRange<?>>();

	public List<SRange<?>> getSearchRanges() {
		return searchRanges;
	};

	private ValidValuesBinding	searchFieldsVvb	= new ValidValuesBinding();

	public ValidValuesBinding getSearchFieldsVvb() {
		return searchFieldsVvb;
	}

	public Constants.Entity getEntity() {
		return entity;
	}

	private SAuthorization	authorization;
	private String			personId;
	private String			filter;

	public boolean getCreateAllowed() {
		return authorization.create;
	}

	public boolean getDeleteAllowed() {
		return authorization.delete;
	}

	public boolean getChangeAllowed() {
		return authorization.change;
	}

	private boolean	renderButtons;

	public boolean getRenderButtons() {
		return renderButtons;
	}

	public boolean getRenderSearch() {
		return searchMetaData.size() > 0;
	}

	// Constructor
	public EntityListUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// register listener for events
		getWorkpage().addWorkpageProcessingEventListener(this);
		// get parameters from functiontree
		// get authorization
		String create = getWorkpage().getParam(Constants.CREATE);
		String change = getWorkpage().getParam(Constants.CHANGE);
		String delete = getWorkpage().getParam(Constants.DELETE);
		if (create == null || !create.equals(Constants.TRUE)) create = Constants.FALSE;
		if (change == null || !change.equals(Constants.TRUE)) change = Constants.FALSE;
		if (delete == null || !delete.equals(Constants.TRUE)) delete = Constants.FALSE;
		authorization = new SAuthorization(create, change, delete);
		// render buttons
		renderButtons = true;
		// get entity
		String strEntity = getWorkpage().getParam(Constants.P_ENTITY);
		try {
			entity = Constants.Entity.valueOf(strEntity.toUpperCase());
		} catch (Exception ex) {
			// Statusbar.outputAlert(Helper.getMessages("entity_wrong"),
			// Helper.getLiteral("error"),
			// Helper.getMessages("entity_wrong_detail")).setLeftTopReferenceCentered();
			Logger.getRootLogger().error(Helper.getMessages("entity_wrong"));
			getWorkpageContainer().closeWorkpage(getWorkpage());
		}
		// get entity id
		personId = getWorkpage().getParam(Constants.P_PERSON);
		// get filter
		filter = getWorkpage().getParam(Constants.P_FILTER);
		// set up grid output
		gridMetaData = ENTITYLISTLOGIC.getGridMetaData(entity, filter);
		// if base is a relation, add standard column
		if (entity.hasStandard()) {
			gridMetaData.add(0, new SGridMetaData("#{rr.literals.standard}", Constants.STANDARD, SGridMetaData.Component.CHECKBOX));
		}
		// set up search
		searchMetaData = ENTITYLISTLOGIC.getSearchMetaData(entity);
		if (getRenderSearch()) {
			buildSearchFieldsVvb();
			searchRanges.add(searchMetaData.values().iterator().next().getRange()); // init
																					// with
																					// first
																					// value
			setDynamicSearch();
		} else {
			buildData(filter);
		}
	}

	// Constructor for LabelSearchResult
	public EntityListUI(IWorkpageDispatcher dispatcher, Constants.Entity entity, List<String> ids, SAuthorization authorization) {
		super(dispatcher);
		// render no buttons
		renderButtons = false;
		this.authorization = authorization;
		this.entity = entity;
		personId = null;
		gridMetaData = ENTITYLISTLOGIC.getGridMetaData(entity, null);
		buildData(ids);
	}

	//protected FIXGRIDListBinding<GridListItem>	m_gridList	= new FIXGRIDListBinding<GridListItem>(false);	// optimiziation
	protected MyFIXGRIDListBinding<GridListItem>	m_gridList	= new MyFIXGRIDListBinding<GridListItem>(new IFIXGRIDCallback<GridListItem>(){

		@Override
		public GridListItem createItem(int index) {
			if(Helper.isEmpty(gridData)) return null;
			return new GridListItem(gridData.get(index));
		}

	}, false);

	public FIXGRIDListBinding<GridListItem> getGridList() {
		return m_gridList;
	}

	protected ROWDYNAMICCONTENTBinding	m_dynSearchRow	= new ROWDYNAMICCONTENTBinding();

	public ROWDYNAMICCONTENTBinding getDynSearchRow() {
		return m_dynSearchRow;
	}

	public void setDynSearchRow(ROWDYNAMICCONTENTBinding row) {
		m_dynSearchRow = row;
	}

	protected ROWDYNAMICCONTENTBinding	m_dynRow	= new ROWDYNAMICCONTENTBinding();

	public ROWDYNAMICCONTENTBinding getDynRow() {
		return m_dynRow;
	}

	public void setDynRow(ROWDYNAMICCONTENTBinding row) {
		m_dynRow = row;
	}

	private void setRowDynamic() {
		setDynamicSearch();
		setDynamicGrid();
	}

	private void setDynamicGrid() {
		int maxRows = ENTITYLISTLOGIC.getVisibleAmount();
		String dragKey = Constants.P_ENTITY + ":" + Constants.P_ENTITYLIST;
		StringBuffer xml = new StringBuffer();
		xml.append("<t:fixgrid width='100%' avoidroundtrips='true' rowdragsend='" + dragKey
				+ "' drawoddevenrows='true' objectbinding='#{d.EntityListUI.gridList}' cellselection='false' rowheight='20' sbvisibleamount='" + maxRows
				+ "' showemptyrows='false' horizontalscrollmode='autowithresize' selectorcolumn='1'>");
		// selector column
		// xml.append("<t:gridcol columnresizingenabled='false' searchenabled='false' sortenabled='false' width='20'><t:gridrowselector /></t:gridcol>");
		// build dynamic columns
		for (SGridMetaData meta : gridMetaData) {
			// component type checkbox
			boolean isIndividual = false;
			if (meta.component == SGridMetaData.Component.INDIVIDUAL) {
				isIndividual = true;
			}
			boolean isCheckBox = false;
			if (meta.component == SGridMetaData.Component.CHECKBOX) {
				isCheckBox = true;
			}
			boolean isCalendarField = false;
			if (meta.component == SGridMetaData.Component.CALENDARFIELD) {
				isCalendarField = true;
			}
			boolean isFormatedDateTime = false;
			if (meta.component == SGridMetaData.Component.FORMATED_DATETIME) {
				isFormatedDateTime = true;
			}
			boolean isFormatedDouble = false;
			if (meta.component == SGridMetaData.Component.FORMATED_DOUBLE) {
				isFormatedDouble = true;
			}
			boolean isColorField = false;
			if (meta.component == SGridMetaData.Component.COLORFIELD) {
				isColorField = true;
			}
			boolean isIcon = false;
			if (meta.component == SGridMetaData.Component.ICON) {
				isIcon = true;
			}
			xml.append("<t:gridcol text='" + meta.header + "' align='center' width='" + meta.width + "' sortreference='.{datum." + meta.techname + "}' searchenabled='true'>");
			if (isIndividual) xml.append(meta.code);
			else if (isCheckBox) {
				xml.append("<t:checkbox align='center' selected='.{datum." + meta.techname + "}' enabled='false'/>");
			} else if (isCalendarField) {
				xml.append("<t:calendarfield value='.{datum." + meta.techname + "}' format='date' timezone='CET' enabled='false'/>");
			} else if (isFormatedDateTime) {
				xml.append("<t:formattedfield value='.{datum." + meta.techname + "}' format='datetime' formatmask='medium' enabled='false' width='100'/>");
			} else if (isFormatedDouble) {
				xml.append("<t:formattedfield value='.{datum." + meta.techname + "}' format='double' enabled='false' width='100'/>");
			} else if (isColorField) {
				xml.append("<t:label background='.{datum." + meta.techname + "}' focusable='false' width='20' height='20'/>");
			} else if (isIcon) {
				xml.append("<t:icon align='center' image='.{datum." + meta.techname + "}' enabled='true' imageheight='20' imagewidth='20'/>");
			} else {
				xml.append("<t:field text='.{datum." + meta.techname + "}' enabled='false'/>");
			}
			xml.append("</t:gridcol>");
		}
		xml.append("</t:fixgrid>");
		m_dynRow.setContentXml(xml.toString());
	}

	private void setDynamicSearch() {
		if (!getRenderSearch()) return;
		StringBuffer xml = new StringBuffer();
		xml.append("<t:foldablepane rowdistance='5' text='#{rr.literals.search}' width='100%' >");
		for (int index = 0; index < searchRanges.size(); index++) {
			String field = searchRanges.get(index).field;
			xml.append("<t:row>");
			xml
					.append("<t:combobox value='#{d.EntityListUI.searchRanges["
							+ index
							+ "].field}' actionListener='#{d.EntityListUI.onSearchItemChange}' clientname='field' flush='true' validvaluesbinding='#{d.EntityListUI.searchFieldsVvb}' valuetextmode='TEXT' width='150' withnullitem='false' />");
			xml.append("<t:coldistance />");
			xml.append("<t:combobox value='#{d.EntityListUI.searchRanges[" + index
					+ "].operator}' actionListener='#{d.EntityListUI.onSearchItemChange}' clientname='operator' valuetextmode='TEXT' width='100' withnullitem='false' >");
			if (!Helper.isEmpty(field)) {
				SRange.Operator[] operators;
				switch (searchMetaData.get(field).type) {
				case NUMBER:
					operators = SRange.NUMBER_OPERATORS;
					break;
				case VALUE:
					operators = SRange.VALUES_OPERATORS;
					break;
				default: // STRING
					operators = SRange.STRING_OPERATORS;
					break;
				}
				for (SRange.Operator operator : operators) {
					xml.append("<t:comboboxitem text='" + operator.getLiteral() + "' value='" + operator.name() + "' />");
				}
			}
			xml.append("</t:combobox>");
			xml.append("<t:coldistance />");
			if (!Helper.isEmpty(field)) {
				switch (searchMetaData.get(field).type) {
				case NUMBER:
					xml.append("<t:formattedfield format='int' width='200' value='#{d.EntityListUI.searchRanges[" + index + "].value}' />");
					break;
				case VALUE:
					xml
							.append("<t:combobox width='200' value='#{d.EntityListUI.searchRanges[" + index + "].value}' validvaluesbinding='" + searchMetaData.get(field).vvB
									+ "' valuetextmode='TEXT' />");
					break;
				default: // STRING
					xml.append("<t:field width='200' text='#{d.EntityListUI.searchRanges[" + index + "].value}' />");
					break;
				}
			}
			xml.append("<t:coldistance />");
			xml.append("<t:button actionListener='#{d.EntityListUI.onAddSearchItem}' contentareafilled='false' image='/images/icons/search_plus.gif' />");
			if (index > 0) xml.append("<t:button clientname='" + index + "' actionListener='#{d.EntityListUI.onRemoveSearchItem}' contentareafilled='false' image='/images/icons/search_minus.gif' />");
			xml.append("</t:row>");
		}
		xml.append("<t:row>");
		xml.append("<t:button actionListener='#{d.EntityListUI.onSearch}' image='/images/icons/magnifier.png' imageheight='15' text='#{rr.literals.search}' />");
		xml.append("<t:coldistance />");
		xml.append("<t:button actionListener='#{d.EntityListUI.onClear}' image='/images/icons/clear.png' text='#{rr.literals.clear}' />");
		xml.append("</t:row>");
		xml.append("</t:foldablepane>");
		m_dynSearchRow.setContentXml(xml.toString());
	}

	public void onRefresh(ActionEvent event) {
		buildData(filter);
	}

	public void onNew(ActionEvent event) {
		IWorkpageDispatcher wpd = getOwningDispatcher();
		IWorkpageContainer wpc = getWorkpageContainer();
		IWorkpage wp = new MyWorkpage(wpd, Constants.Page.ENTITYDETAIL.getUrl(), null, Helper.getLiteral("new_entity"), null, true, entityList, authorization, null);
		wp.setParam(Constants.P_ENTITY, entity.name());
		wp.setParam(Constants.P_MODE, Constants.Mode.NEW.name());
		wpc.addWorkpage(wp);
	}

	public void onDelete(ActionEvent event) {
		final GridListItem selectedItem = m_gridList.getSelectedItem();
		if (selectedItem == null) {
			Statusbar.outputMessage(Helper.getMessages("no_entry_selected"));
			return;
		}
		final String selectedID = ((IEntityData) selectedItem.getDatum()).getId();
		if (selectedID != null && selectedID.length() > 0) {
			YESNOPopup popup = YESNOPopup.createInstance(String.format(Helper.getMessages("confirm_delete_detail"), selectedItem.getDatum().toString()), Helper.getMessages("confirm_delete"),
					new IYesNoCancelListener() {

						public void reactOnCancel() {}

						public void reactOnNo() {}

						public void reactOnYes() {
							// own entities
							// special treatment for base entities that are not
							// allowed to be in a relation e.g. TESTS
							if (personId == null || entity.getBase().noRelation()) {
								if (ENTITYLISTLOGIC.delete(entity, selectedID)) {
									Statusbar.outputSuccess(Helper.getMessages("delete_success"));
									m_gridList.getItems().remove(selectedItem);
									closeWorkpages(selectedID);
									return;
								}
							} else { // other person entities
								if (ENTITYLISTLOGIC.delete(entity, selectedID, personId)) {
									Statusbar.outputSuccess(Helper.getMessages("relation_delete_success"));
									m_gridList.getItems().remove(selectedItem);
									closeWorkpages(selectedID);
									return;
								}
							}
							Statusbar.outputAlert(Helper.getMessages("delete_failure"), Helper.getLiteral("error")).setLeftTopReferenceCentered();
						}
					});
			popup.getModalPopup().setLeftTopReferenceCentered();
		}
	}

	private void closeWorkpages(String id) {
		IWorkpage workpage = getWorkpageContainer().getWorkpageForId(id);
		if (workpage != null) getWorkpageContainer().closeWorkpage(workpage);
	}

	// common build method
	private void buildData(String filter) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity, personId, filter);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (int i = 0;i<gridData.size();i++) {
			m_gridList.getItems().add(null);
		}
		setRowDynamic();
		// load grid state
		loadGridState();
		// print logic
		setPrintReport();
		Statusbar.outputMessage(String.format(Helper.getMessages("items_found"), gridData.size()));
	}

	private void buildData(SearchRange srange, String filter) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity, srange, filter);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (int i = 0;i<gridData.size();i++) {
			m_gridList.getItems().add(null);
		}
		setRowDynamic();
		// load grid state
		loadGridState();
		// print logic
		setPrintReport();
		Statusbar.outputMessage(String.format(Helper.getMessages("items_found"), gridData.size()));
	}

	// special build method for labelsearch
	public void buildData(List<String> ids) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity, ids);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (int i = 0;i<gridData.size();i++) {
			m_gridList.getItems().add(null);
		}
		setRowDynamic();
		// load grid state
		loadGridState();
		// print logic
		setPrintReport();
		Statusbar.outputMessage(String.format(Helper.getMessages("items_found"), gridData.size()));
	}

	public void saveGridState(ActionEvent event) {
		SListVariant lv = new SListVariant(m_gridList.getColumnsequence(), m_gridList.getModcolumnwidths());
		ENTITYLISTLOGIC.saveGridState(entity, lv);
	}

	private void loadGridState() {
		SListVariant lv = ENTITYLISTLOGIC.loadGridState(entity);
		if (lv == null) return;
		m_gridList.setColumnsequence(lv.columnsSequence);
		m_gridList.setModcolumnwidths(lv.columnsWidth);
	}

	private void setPrintReport() {
		// Print report
		if (report != null) BufferedContentMgr.remove(report);
		report = ENTITYLISTLOGIC.getPrintReport(entity, filter, gridData);
		if (report != null) BufferedContentMgr.add(report);
	}

	public class GridListItem extends FIXGRIDItem implements java.io.Serializable {
		public IEntityData	datum;
		public Object		parent;

		public GridListItem(IEntityData datum) {
			super();
			this.datum = datum;
		}

		public Object getDatum() {
			return datum;
		}

		public void setDatum(IEntityData datum) {
			this.datum = datum;
		}

		@Override
		public void onRowExecute() {
			// Switch to or create entities page
			IWorkpageDispatcher wpd = getOwningDispatcher();
			IWorkpageContainer wpc = getWorkpageContainer();
			IWorkpage wp = wpc.getWorkpageForId(datum.getId());
			if (wp != null) {
				wpc.switchToWorkpage(wp);
				return;
			}
			// Page doesn't exist, create it
			String pageId = datum.getId();
			Mode pageMode = Mode.SHOW;

			switch (entity.getBase()) {
			case COMPETITION:
				// special handling for competitions
				// because page ID has to be unique to resolve right page
				// resolved in EntityDetailUI
				pageId = entity.name() + "#" + pageId;
				break;
			case SCHEDULE:
				// schedules are displayed as modal popup!
				ScheduleUI.ScheduleItem scheduleItem = getScheduleUI().createScheduleItem(datum.getId());
				ScheduleChangePopUp scheduleChangePopUp = getScheduleChangePopUp();
				scheduleChangePopUp.setRenderButtons(false);
				scheduleChangePopUp.prepareCallback(new ScheduleChangePopUp.IScheduleChangePopupCallback() {
					public void cancel() {}

					public void save() {}

					public void delete() {}
				}, scheduleItem);
				m_popup = getWorkpage().createModalPopupInWorkpageContext();
				m_popup.setLeftTopReferenceCentered();
				m_popup.setUndecorated(true);
				m_popup.open(Constants.Page.SCHEDULECHANGEPOPUP.getUrl(), Helper.getLiteral("schedule"), 1024, 768, EntityListUI.this);
				return;
			}
			// if buttons are not rendered details are immutable
			if (!renderButtons) {
				pageId = Constants.FINAL + pageId;
				pageMode = Mode.FINAL;
			}
			String title = (entity.getDescription() + Constants.WHITESPACE + datum.toString()).trim();
			wp = new MyWorkpage(wpd, Constants.Page.ENTITYDETAIL.getUrl(), pageId, title, null, true, entityList, authorization, null);
			wp.setParam(Constants.P_ENTITY, entity.name());
			wp.setParam(Constants.P_MODE, pageMode.name());
			wpc.addWorkpage(wp);
		}

	}

	public void onSearch(ActionEvent event) {
		boolean valueSet = false;
		for (SRange<?> range : searchRanges) {
			if (range.value instanceof String) {
				if (!Helper.isEmpty((String) range.value)) {
					valueSet = true;
					break;
				}
			} else {
				if (range.value != null) {
					valueSet = true;
					break;
				}
			}
		}
		if (valueSet) {
			SearchRange srange = new SearchRange(searchRanges);
			buildData(srange, filter);
		} else {
			buildData(filter);
		}
	}

	public void onClear(ActionEvent event) {
		searchRanges.clear();
		searchRanges.add(searchMetaData.values().iterator().next().getRange()); // init
																				// with
																				// first
																				// value
		m_gridList.getItems().clear();
		setDynamicSearch();
	}

	public void onAddSearchItem(ActionEvent event) {
		searchRanges.add(searchMetaData.values().iterator().next().getRange()); // init
																				// with
																				// first
																				// value
		setDynamicSearch();
	}

	public void onRemoveSearchItem(ActionEvent event) {
		if (searchRanges.size() > 1) {
			String clientname = (String) event.getComponent().getAttributes().get(Constants.CLIENTNAME);
			if (clientname != null) searchRanges.remove(Integer.valueOf(clientname).intValue());
		}
		setDynamicSearch();
	}

	public void onSearchItemChange(ActionEvent event) {
		setDynamicSearch();
	}

	private void buildSearchFieldsVvb() {
		for (SSearchMetaData searchMetaDatum : searchMetaData.values()) {
			searchFieldsVvb.addValidValue(searchMetaDatum.techname, searchMetaDatum.header);
		}
	}

	public void processEvent(WorkpageProcessingEvent event) {
		// refresh list
		if (event instanceof WorkpageRefreshEvent) {
			Entity entity = ((WorkpageRefreshEvent) event).getEntity();
			if (this.entity.getBase() == entity.getBase()) onRefresh(null);
		}
	}
}
