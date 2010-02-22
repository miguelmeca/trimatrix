package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
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
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.EntityListUI}")
public class EntityListUI extends MyWorkpageDispatchedBean implements
		Serializable, IWorkpageProcessingEventListener {

	public final EntityListUI entityList = this;
	private final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
	private List<SGridMetaData> gridMetaData;
	private List<IEntityData> gridData;
	private Constants.Entity entity;
	public Constants.Entity getEntity() { return entity; }
	private SAuthorization authorization;
	private String personId;
	private String filter;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	private boolean renderButtons;
	public boolean getRenderButtons() { return renderButtons; }

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
		if(create==null || !create.equals(Constants.TRUE)) create = Constants.FALSE;
		if(change==null || !change.equals(Constants.TRUE)) change = Constants.FALSE;
		if(delete==null || !delete.equals(Constants.TRUE)) delete = Constants.FALSE;
		authorization = new SAuthorization(create, change, delete);
		// render buttons
		renderButtons = true;
		// get entity
		String strEntity = getWorkpage().getParam(Constants.P_ENTITY);
		try {
			entity = Constants.Entity.valueOf(strEntity.toUpperCase());
		} catch (Exception ex) {
			Statusbar.outputError("No or wrong entity set", "For list view processing an entity has to be set by the functiontreenode!");
			getWorkpageContainer().closeWorkpage(getWorkpage());
		}
		// get entity id
		personId = getWorkpage().getParam(Constants.P_PERSON);
		// get filter
		filter = getWorkpage().getParam(Constants.P_FILTER);
		// set up grid output
		gridMetaData = ENTITYLISTLOGIC.getGridMetaData(entity, filter);
		// if base is a relation, add standard column
		if(entity.hasStandard()) {
			gridMetaData.add(0, new SGridMetaData("#{rr.literals.standard}", Constants.STANDARD, SGridMetaData.Component.CHECKBOX));
		}
		buildData(filter);
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

	protected FIXGRIDListBinding<GridListItem> m_gridList = new FIXGRIDListBinding<GridListItem>(
			false); // optimiziation active

	public FIXGRIDListBinding<GridListItem> getGridList() {
		return m_gridList;
	}

	public void setGridList(FIXGRIDListBinding<GridListItem> value) {
		m_gridList = value;
	}

	protected ROWDYNAMICCONTENTBinding m_dynRow = new ROWDYNAMICCONTENTBinding();

	public ROWDYNAMICCONTENTBinding getDynRow() {
		return m_dynRow;
	}

	public void setDynRow(ROWDYNAMICCONTENTBinding row) {
		m_dynRow = row;
	}

	private void setRowDynamic() {
		int maxRows = ENTITYLISTLOGIC.getVisibleAmount();
		String dragKey = Constants.P_ENTITY + ":" + Constants.P_ENTITYLIST;
		StringBuffer xml = new StringBuffer();
		xml.append("<t:fixgrid width='100%' avoidroundtrips='true' rowdragsend='" + dragKey + "' drawoddevenrows='true' objectbinding='#{d.EntityListUI.gridList}' cellselection='false' rowheight='20' sbvisibleamount='" + maxRows + "' showemptyrows='false' horizontalscrollmode='autowithresize' selectorcolumn='1'>");
		// selector column
		//xml.append("<t:gridcol columnresizingenabled='false' searchenabled='false' sortenabled='false' width='20'><t:gridrowselector /></t:gridcol>");
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
				isIcon= true;
			}
			xml.append("<t:gridcol text='" + meta.header
					+ "' align='center' width='" + meta.width + "' sortreference='.{datum." + meta.techname
					+ "}' searchenabled='true'>");
			if (isIndividual) xml.append(meta.code);
			else if (isCheckBox) {
				xml.append("<t:checkbox align='center' selected='.{datum."
						+ meta.techname + "}' enabled='false'/>");
			} else if (isCalendarField) {
				xml.append("<t:calendarfield value='.{datum."
						+ meta.techname + "}' format='date' timezone='CET' enabled='false'/>");
			} else if (isFormatedDateTime) {
				xml.append("<t:formattedfield value='.{datum."
						+ meta.techname + "}' format='datetime' formatmask='medium' enabled='false' width='100'/>");
			} else if (isFormatedDouble) {
				xml.append("<t:formattedfield value='.{datum."
						+ meta.techname + "}' format='double' enabled='false' width='100'/>");
			} else if (isColorField) {
				xml.append("<t:label background='.{datum."
						+ meta.techname + "}' focusable='false' width='20' height='20'/>");
			} else if (isIcon) {
				xml.append("<t:icon align='center' image='.{datum."
						+ meta.techname + "}' enabled='true' imageheight='20' imagewidth='20'/>");
			} else {
				xml.append("<t:field text='.{datum." + meta.techname
						+ "}' enabled='false'/>");
			}
			xml.append("</t:gridcol>");
		}
		xml.append("</t:fixgrid>");
		m_dynRow.setContentXml(xml.toString());
	}

	public void onRefresh(ActionEvent event) {
		buildData(filter);
	}

	public void onNew(ActionEvent event) {
		IWorkpageDispatcher wpd = getOwningDispatcher();
		IWorkpageContainer wpc = getWorkpageContainer();
		IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
				null, "New entity", null, true, entityList, authorization, null);
		wp.setParam(Constants.P_ENTITY, entity.name());
		wp.setParam(Constants.P_MODE, Constants.Mode.NEW.name());
		wpc.addWorkpage(wp);
	}

	public void onDelete(ActionEvent event) {
		final GridListItem selectedItem = m_gridList.getSelectedItem();
		if(selectedItem == null) {
			Statusbar.outputMessage("No entity selected!");
			return;
		}
		final String selectedID = ((IEntityData)selectedItem.getDatum()).getId();
		if(selectedID!=null && selectedID.length()>0) {
			YESNOPopup popup = YESNOPopup.createInstance(
					"Confirm deletion" + Constants.WHITESPACE + selectedItem.getDatum().toString(),
					"Do you really want to delete the selected entity?",
					new IYesNoCancelListener(){

						public void reactOnCancel() {}

						public void reactOnNo() {}

						public void reactOnYes() {
							// own entities
							if(personId==null) {
								if(ENTITYLISTLOGIC.delete(entity, selectedID)) {
									Statusbar.outputSuccess("Entity successfully deleted!");
									m_gridList.getItems().remove(selectedItem);
									return;
								}
							} else {	// other person entities
								if(ENTITYLISTLOGIC.delete(entity, selectedID, personId)) {
									Statusbar.outputSuccess("Relation successfully deleted!");
									m_gridList.getItems().remove(selectedItem);
									return;
								}
							}
							Statusbar.outputError("Entity/Relation could not be deleted!");
						}
					}
			);
			popup.getModalPopup().setLeftTopReferenceCentered();
		}
	}

	// common build method
	private void buildData(String filter) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity, personId, filter);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {
			m_gridList.getItems().add(new GridListItem(datum));
		}
		setRowDynamic();
		// load grid state
		loadGridState();
		// print logic
		setPrintReport();
	}

	// special build method for labelsearch
	public void buildData(List<String> ids) {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity, ids);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {
			m_gridList.getItems().add(new GridListItem(datum));
		}
		setRowDynamic();
		// load grid state
		loadGridState();
		// print logic
		setPrintReport();
	}

	public void saveGridState(ActionEvent event) {
		SListVariant lv = new SListVariant(m_gridList.getColumnsequence(), m_gridList.getModcolumnwidths());
		ENTITYLISTLOGIC.saveGridState(entity, lv);
	}

	private void loadGridState() {
		SListVariant lv = ENTITYLISTLOGIC.loadGridState(entity);
		if(lv==null) return;
		m_gridList.setColumnsequence(lv.columnsSequence);
		m_gridList.setModcolumnwidths(lv.columnsWidth);
	}

	private void setPrintReport() {
		// Print report
		if(report!=null) BufferedContentMgr.remove(report);
		report = ENTITYLISTLOGIC.getPrintReport(entity, filter, gridData);
		if(report!=null) BufferedContentMgr.add(report);
	}

	public class GridListItem extends FIXGRIDItem implements
			java.io.Serializable {
		public IEntityData datum;
		public Object parent;

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
			if(wp != null) {
				wpc.switchToWorkpage(wp);
				return;
			}
			// Page doesn't exist, create it
			String pageId = datum.getId();
			Mode pageMode = Mode.SHOW;
			// special handling for competitions
			// because page ID has to be unique to resolve right page
			// resolved in EntityDetailUI
			if(entity.getBase().equals(Entity.COMPETITION)) {
				pageId = entity.name() + "#" + pageId;
			}
			// if buttons are not rendered details are immutable
			if(!renderButtons) {
				pageId = Constants.FINAL + pageId;
				pageMode = Mode.FINAL;
			}
			String title = (entity.getDescription() + Constants.WHITESPACE + datum.toString()).trim();
			wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
					pageId, title, null, true, entityList, authorization, null);
			wp.setParam(Constants.P_ENTITY, entity.name());
			wp.setParam(Constants.P_MODE, pageMode.name());
			wpc.addWorkpage(wp);
		}

	}

	public void processEvent(WorkpageProcessingEvent event) {
		// refresh list
		if (event instanceof WorkpageRefreshEvent) {
			Entity entity = ((WorkpageRefreshEvent)event).getEntity();
			if(this.entity.getBase()==entity.getBase()) onRefresh(null);
		}
	}
}
