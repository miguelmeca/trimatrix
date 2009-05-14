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
import trimatrix.relations.PersonPersonRelation;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.RelationListUI}")

public class RelationListUI extends MyWorkpageDispatchedBean implements Serializable
{	
	private final RelationListLogic RELATIONLISTLOGIC = getLogic().getRelationListLogic();
	
	private ARRAYGRIDListBinding<MyARRAYGRIDItem> grid = new ARRAYGRIDListBinding<MyARRAYGRIDItem>();
	public ARRAYGRIDListBinding<MyARRAYGRIDItem> getGrid() { return grid; }
	
	private static final Constants.Relation relation = Constants.Relation.COACH;
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
		setMetaData();
		buildData();
	}
	
	private void buildData() {
		// clear list
		grid.getItems().clear();
		// get relations from database
		List<IRelationData> relations = RELATIONLISTLOGIC.getPersonPersonRelations(relation);
		for (IRelationData relation : relations) {
			PersonPersonRelation.Data ppRelation = (PersonPersonRelation.Data)relation;
			MyARRAYGRIDItem item = new MyARRAYGRIDItem(ppRelation.getId());
			String[] values = new String[COLCOUNT];
			values[0] = ppRelation.getPerson1().toString();
			values[1] = ppRelation.description;
			values[2] = ppRelation.getPerson2().toString();
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
    	m_popup.open(Constants.Page.CREATERELATION.url(), "Beziehung anlegen", 400, 300, this); 
	}
	
	public void onRemove(ActionEvent event) {
		final MyARRAYGRIDItem item = (MyARRAYGRIDItem)grid.getSelectedItem();		
		deleteRelation(item);		
	}	
	
	private void deleteRelation(final MyARRAYGRIDItem item) {
		YESNOPopup.createInstance(
				"Confirm deletion", 
				"Do you really want to delete the selected relation?", 
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {	
						if(RELATIONLISTLOGIC.deletePersonPersonRelation(item.id)) {		
							grid.getItems().remove(item);	
							Statusbar.outputSuccess("Relation deleted");											
						} else {
							Statusbar.outputError("Relation could not be deleted!");
						}								
					}						
				}
		);
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
