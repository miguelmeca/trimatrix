package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventPopupMenuItem;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDItem;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.logic.RelationListLogic;
import trimatrix.relations.IRelationData;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.RelationListUI}")

public class RelationListUI extends MyWorkpageDispatchedBean implements Serializable
{	
	private final RelationListLogic RELATIONLISTLOGIC = getLogic().getRelationListLogic();
	
	private ARRAYGRIDListBinding<MyARRAYGRIDItem> grid = new ARRAYGRIDListBinding<MyARRAYGRIDItem>();
	public ARRAYGRIDListBinding<MyARRAYGRIDItem> getGrid() { return grid; }
	
	private static Constants.Relation relation;
	private static final int COLCOUNT = 3;
	private static final String REMOVE = "remove";
	private static final String ADD = "add";
	
	private static final String[] titles = {"Partner 1", "Beziehung", "Partner 2"};
	private static final String[] widths = {"100","100","100"};
	private static final String[] aligns = {"center", "center", "center"};
	private static final String[] formats = {"string", "string", "string"};
	private static final String[] backgrounds = {"#FFFFFF", "#FFFFFF", "#FFFFFF"};
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
	public RelationListUI(IWorkpageDispatcher dispatcher) {
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
			relation = Constants.Relation.valueOf(strEntity.toUpperCase());
		} catch (Exception ex) {			
			Statusbar.outputError("No or wrong entity set", "For relation list view processing an entity has to be set by the functiontreenode!");
			getWorkpageContainer().closeWorkpage(getWorkpage());
		}		
		// set up grid output
		setMetaData();
		buildData();
	}
	
	private void buildData() {
		// clear list
		grid.getItems().clear();
		// get relations from database
		List<IRelationData> relations = RELATIONLISTLOGIC.getData(relation);
		for (IRelationData relation : relations) {
			//PersonPersonRelation.Data ppRelation = (PersonPersonRelation.Data)relation;
			MyARRAYGRIDItem item = new MyARRAYGRIDItem(relation.getId());
			String[] values = new String[COLCOUNT];
			values[0] = relation.getPartner1().toString();
			values[1] = relation.getDescription();
			values[2] = relation.getPartner2().toString();
			item.setValues(values);				
			item.setBackgrounds(backgrounds);
			grid.getItems().add(item);
		}
	}
	
	private void setMetaData() {
		grid.getItems().clear();
		grid.setTitles(titles);
		grid.setWidths(widths);	
		grid.setAligns(aligns);
		grid.setFormats(formats);
	}	
	
	public void onRefresh(ActionEvent event) {
		buildData();
	}
	
	public void onAdd(ActionEvent event) {
		CreateRelationUI createRelationUI = getCreateRelationUI();
		createRelationUI.setRelationType(relation);
		createRelationUI.prepareCallback(new CreateRelationUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void ok() {
				m_popup.close();	
				buildData();
			}
		});		
		m_popup = getWorkpage().createModalPopupInWorkpageContext();    
		m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.CREATERELATION.getUrl(), "Beziehung anlegen", 400, 300, this); 
	}
	
	public void onRemove(ActionEvent event) {
		final MyARRAYGRIDItem item = (MyARRAYGRIDItem)grid.getSelectedItem();		
		deleteRelation(item);		
	}	
	
	private void deleteRelation(final MyARRAYGRIDItem item) {
		YESNOPopup popup = YESNOPopup.createInstance(
				"Confirm deletion", 
				"Do you really want to delete the selected relation?", 
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {	
						if(RELATIONLISTLOGIC.delete(relation, item.id)) {		
							grid.getItems().remove(item);	
							Statusbar.outputSuccess("Relation deleted");											
						} else {
							Statusbar.outputError("Relation could not be deleted!");
						}								
					}						
				}
		);
		popup.getModalPopup().setLeftTopReferenceCentered();
	}
		
	public class MyARRAYGRIDItem extends ARRAYGRIDItem {
		private String id;
		
		private MyARRAYGRIDItem() {
			throw new IllegalAccessError();
		};
		public MyARRAYGRIDItem(String id) {
			this.id = id;
		}
		
		@Override
		public void onRowPopupMenuItem(BaseActionEventPopupMenuItem event) {
			super.onRowPopupMenuItem(event);
			// remove item
			if(REMOVE.equalsIgnoreCase(event.getCommand())) {
				deleteRelation(this);
				return;				
			}
			// add item
			if(ADD.equalsIgnoreCase(event.getCommand())) {
				onAdd(null);
				return;				
			}
		}

		public String getId() {
			return id;
		}		
	}	
}
