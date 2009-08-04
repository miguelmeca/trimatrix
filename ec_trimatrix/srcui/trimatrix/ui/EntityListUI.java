package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.BasePopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.entities.IEntityData;
import trimatrix.logic.EntityListLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SListVariant;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.EntityListUI}")
public class EntityListUI extends MyWorkpageDispatchedBean implements
		Serializable {

	public final EntityListUI entityList = this;
	private final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
	private List<SGridMetaData> gridMetaData;
	private List<IEntityData> gridData;
	private Constants.Entity entity;
	public Constants.Entity getEntity() { return entity; }
	private SAuthorization authorization;
	private String personId;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }	

	// Constructor
	public EntityListUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get parameters from functiontree
		// get authorization
		String create = getWorkpage().getParam(Constants.CREATE);
		String change = getWorkpage().getParam(Constants.CHANGE);
		String delete = getWorkpage().getParam(Constants.DELETE);
		if(create==null || !create.equals(Constants.TRUE)) create = Constants.FALSE;
		if(change==null || !change.equals(Constants.TRUE)) change = Constants.FALSE;
		if(delete==null || !delete.equals(Constants.TRUE)) delete = Constants.FALSE;
		authorization = new SAuthorization(create, change, delete);	
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
		// set up grid output		
		gridMetaData = ENTITYLISTLOGIC.getGridMetaData(entity);		
		buildData();
	}
	
	// Constructor for LabelSearchResult
	public EntityListUI(IWorkpageDispatcher dispatcher, Constants.Entity entity) {
		super(dispatcher);
		// show no buttons
		authorization = new SAuthorization(Constants.FALSE, Constants.FALSE, Constants.FALSE);	
		this.entity = entity;
		personId = null;
		gridMetaData = ENTITYLISTLOGIC.getGridMetaData(entity);		
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
		String dragKey = Constants.P_ENTITY + ":" + Constants.P_ENTITYLIST;
		StringBuffer xml = new StringBuffer();
		xml.append("<t:fixgrid avoidroundtrips='true' dragsend='" + dragKey + "' rowdragsend='" + dragKey + "' drawoddevenrows='true' objectbinding='#{d.EntityListUI.gridList}' persistid='gridList' bordercolor='C0C0C0' borderheight='1' borderwidth='1' cellselection='true' rowheight='20' width='100%' sbvisibleamount='20'>");
		for (SGridMetaData meta : gridMetaData) {
			// component type checkbox
			boolean isCheckBox = false;			
			if (meta.component == SGridMetaData.Component.CHECKBOX) {
				isCheckBox = true;
			} 
			boolean isCalendarField = false;
			if (meta.component == SGridMetaData.Component.CALENDARFIELD) {
				isCalendarField = true;
			} 
			boolean isIcon = false;
			if (meta.component == SGridMetaData.Component.ICON) {
				isIcon= true;
			} 
			xml.append("<t:gridcol text='" + meta.header
					+ "' align='center' width='" + meta.width + "' sortreference='.{datum." + meta.techname
					+ "}' searchenabled='true'>");
			if (isCheckBox) {
				xml.append("<t:checkbox align='center' selected='.{datum."
						+ meta.techname + "}' enabled='false'/>");
			} else if (isCalendarField) {
				xml.append("<t:calendarfield value='.{datum."
						+ meta.techname + "}' format='date' timezone='CET' enabled='false'/>");
			} else if (isIcon) {
				xml.append("<t:icon image='.{datum."
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
		buildData();
	}

	public void onNew(ActionEvent event) {
		IWorkpageDispatcher wpd = getOwningDispatcher();
		IWorkpageContainer wpc = getWorkpageContainer();
		IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
				null, "New entity", null, true, entityList, authorization);
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
					"Confirm deletion", 
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
			popup.getModalPopup().setLeft(BasePopup.POS_CENTER);
    		popup.getModalPopup().setTop(BasePopup.POS_CENTER);
		} 
	}

	// common build method
	private void buildData() {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity, personId);
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {
			m_gridList.getItems().add(new GridListItem(datum));
		}
		setRowDynamic();
		// load grid state
		loadGridState();
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
			IWorkpageDispatcher wpd = getOwningDispatcher();
			IWorkpageContainer wpc = getWorkpageContainer();
			IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.getUrl(),
					datum.getId(), datum.toString(), null, true, entityList, authorization);			
			wp.setParam(Constants.P_ENTITY, entity.name());
			wp.setParam(Constants.P_MODE, Constants.Mode.SHOW.name());			
			wpc.addWorkpage(wp);
		}

	}

}
