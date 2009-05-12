package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.events.BaseActionEventPopupMenuItem;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDItem;
import org.eclnt.jsfserver.elements.impl.ARRAYGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.logic.RelationListLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass (expressionBase="#{d.RelationListUI}")

public class RelationListUI extends MyWorkpageDispatchedBean implements Serializable
{	
	private final RelationListLogic RELATIONLISTLOGIC = getLogic().getRelationListLogic();
	
	private ARRAYGRIDListBinding<MyARRAYGRIDItem> grid = new ARRAYGRIDListBinding<MyARRAYGRIDItem>();
	public ARRAYGRIDListBinding<MyARRAYGRIDItem> getGrid() { return grid; }
	
	private static final int COLCOUNT = 3;
	private static final String REMOVE = "remove";
	private static final String ADD = "add";
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
	public RelationListUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		initialize();
	}
	
	private void initialize() {		
		buildMetaData();
		buildData();
	}
	
	private void buildMetaData() {
		grid.getItems().clear();
		String[] titles = new String[COLCOUNT];
		String[] widths = new String[COLCOUNT];
		String[] aligns = new String[COLCOUNT];
		
		titles[0] = "Partner 1";
		widths[0] = "100";
		aligns[0] = "center";
		titles[1] = "Beziehung";
		widths[1] = "100";
		aligns[1] = "center";
		titles[2] = "Partner 2";
		widths[2] = "100";
		aligns[2] = "center";
		
		grid.setTitles(titles);
		grid.setWidths(widths);	
		grid.setAligns(aligns);
	}
	
	private void buildData() {
		
	}
	
	public void onRefresh(ActionEvent event) {
		initialize();
	}
	
	public void onAdd(ActionEvent event) {
		MyARRAYGRIDItem item = new MyARRAYGRIDItem();
		String[] values = new String[COLCOUNT];
		String[] backgrounds = new String[COLCOUNT];
		values[0] = "Markus Reich";
		backgrounds[0] = "#FFFFFF";
		values[1] = "ist Trainer von";
		backgrounds[1] = "#FFFFFF";
		values[2] = "Daniela Bucher";
		backgrounds[2] = "#FFFFFF";
		item.setValues(values);		
		item.setBackgrounds(backgrounds);
		grid.getItems().add(item);
		if(event!=null) {
			grid.ensureItemToBeDisplayed(item);
		}
	}
	
	public void onRemove(ActionEvent event) {
		grid.getItems().remove(grid.getSelectedItem());
	}	
	
	public class MyARRAYGRIDItem extends ARRAYGRIDItem {

		@Override
		public void onRowPopupMenuItem(BaseActionEventPopupMenuItem event) {
			super.onRowPopupMenuItem(event);
			// remove item
			if(REMOVE.equalsIgnoreCase(event.getCommand())) {
				grid.getItems().remove(this);
				return;				
			}
			// add item
			if(ADD.equalsIgnoreCase(event.getCommand())) {
				onAdd(null);
				return;				
			}
		}
		
	}
}
