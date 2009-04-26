package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
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
import trimatrix.structures.SGridMetaData;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.EntityListUI}")
public class EntityListUI extends MyWorkpageDispatchedBean implements
		Serializable {
	public final EntityListUI entityList = this;
	private final EntityListLogic ENTITYLISTLOGIC = getLogic()
			.getEntityListLogic();
	private List<SGridMetaData> gridMetaData;
	private List<IEntityData> gridData;
	private Constants.Entity entity;

	// Constructor
	public EntityListUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get parameters from functiontree
		String strEntity = getWorkpage().getParam(Constants.P_ENTITY);
		try {
			entity = Constants.Entity.valueOf(strEntity.toUpperCase());
		} catch (Exception ex) {			
			Statusbar.outputError("No or wrong entity set", "For list view processing an entity has to be set by the functiontreenode!");
			getWorkpageContainer().closeWorkpage(getWorkpage());
		}
		gridMetaData = ENTITYLISTLOGIC.getGridMetaData(entity);		
		buildData();
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
		StringBuffer xml = new StringBuffer();
		xml
				.append("<t:fixgrid avoidroundtrips='true' drawoddevenrows='true' objectbinding='#{d.EntityListUI.gridList}' width='100%' height='100%' sbvisibleamount='25'>");
		for (SGridMetaData meta : gridMetaData) {
			// component type checkbox
			boolean isCheckBox = false;
			if (meta.component == SGridMetaData.Component.CHECKBOX) {
				isCheckBox = true;
			}
			xml.append("<t:gridcol text='" + meta.header
					+ "' width='100' sortreference='.{datum." + meta.techname
					+ "}' searchenabled='true'>");
			if (isCheckBox) {
				xml.append("<t:checkbox align='center' selected='.{datum."
						+ meta.techname + "}' enabled='false'/>");
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
		IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.url(),
				null, "New entity", null, true, entityList);
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
			YESNOPopup.createInstance(
					"Confirm deletion", 
					"Do you really want to delete the selected entity?", 
					new IYesNoCancelListener(){

						public void reactOnCancel() {}

						public void reactOnNo() {}

						public void reactOnYes() {
							if(ENTITYLISTLOGIC.deleteEntity(entity, selectedID)) {
								Statusbar.outputSuccess("Entity successfully deleted!");
								m_gridList.getItems().remove(selectedItem);
							} else {
								Statusbar.outputError("Entity could not be deleted!");
							}							
						}						
					}
			);			
		} 
	}

	private void buildData() {
		// load entities from database
		gridData = ENTITYLISTLOGIC.getData(entity);	
		// rebuild grid list
		m_gridList.getItems().clear();
		for (IEntityData datum : gridData) {
			m_gridList.getItems().add(new GridListItem(datum));
		}
		setRowDynamic();
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
			IWorkpage wp = new MyWorkpage( wpd, Constants.Page.ENTITYDETAIL.url(),
					datum.getId(), datum.toString(), null, true, entityList);
			wp.setParam(Constants.P_ENTITY, entity.name());
			wp.setParam(Constants.P_MODE, Constants.Mode.SHOW.name());			
			wpc.addWorkpage(wp);
		}

	}

}
