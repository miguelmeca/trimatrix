package trimatrix.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.ZonesDefinition;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.ZonesDefinitionUI}")

public class ZonesDefinitionUI extends MyWorkpageDispatchedBean implements Serializable {
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();
    public FIXGRIDListBinding<GridZonesItem> getGridZones() { return m_gridZones; }
    public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) { m_gridZones = value; }
    	
    private Set<String> deletedIds = new HashSet<String>();
    
    public ZonesDefinitionUI(IWorkpageDispatcher dispatcher) {
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
        buildGrid();
	}
	
    public class GridZonesItem extends FIXGRIDItem implements java.io.Serializable
    {       	
    	public GridZonesItem() {      		
    		zonesDefinition = new ZonesDefinition(UUID.randomUUID().toString(), getServiceLayer().getDictionaryService().getMyPerson().getId());    		
    		// when null the value transfer from UI to bean doesn't work
    		zonesDefinition.setColor(Constants.WHITE);
    	}
    	
    	public GridZonesItem(ZonesDefinition zonesDefinition) {
    		this.zonesDefinition = zonesDefinition;
    	}   	

    	protected ZonesDefinition zonesDefinition;
    	public ZonesDefinition getZonesDefinition() { return zonesDefinition; }
      
    }
    
    private void buildGrid() {    	
    	m_gridZones.getItems().clear();
    	// load data from db
    	List<ZonesDefinition> zonesDefinitions = getServiceLayer().getDictionaryService().getMyPerson().getZonesDefinition();
    	for(ZonesDefinition zonesDefinition : zonesDefinitions) {
    		m_gridZones.getItems().add(new GridZonesItem(zonesDefinition));
    	}
    	
    }
    
    public void onAddZone(ActionEvent event) {    	
    	m_gridZones.getItems().add(new GridZonesItem());	
    }
    
    public void onDeleteZone(ActionEvent event) {
    	deletedIds.add(m_gridZones.getSelectedItem().getZonesDefinition().getId());
    	m_gridZones.getItems().remove(m_gridZones.getSelectedItem());
    }
	
	public void onSave(ActionEvent event) {
		// first delete marked zones
		getLogic().getZonesLogic().deleteZones(deletedIds);
		deletedIds.clear();
		// add or change zones
		int index = 0;		
		List<ZonesDefinition> zonesDefinitions = new ArrayList<ZonesDefinition>();
		for (GridZonesItem item : m_gridZones.getItems()) {
			ZonesDefinition zonesDefinition = item.getZonesDefinition();
			zonesDefinition.setSequence(index++);				
			zonesDefinitions.add(zonesDefinition);
		}		
		getLogic().getZonesLogic().updateZones(zonesDefinitions);
		// refresh
		buildGrid();
	}

    public void onShiftDown(ActionEvent event) {
    	GridZonesItem item = m_gridZones.getSelectedItem();
    	int index = m_gridZones.getItems().indexOf(item)+1;    	
    	if(index<m_gridZones.getItems().size()) {
    		m_gridZones.getItems().remove(item);
    		m_gridZones.getItems().add(index, item);    	
    	}
    }

    public void onShiftUp(ActionEvent event) {
    	GridZonesItem item = m_gridZones.getSelectedItem();
    	int index = m_gridZones.getItems().indexOf(item)-1;
    	if(index>=0) {
    		m_gridZones.getItems().remove(item);
    		m_gridZones.getItems().add(index, item); 
    	}
    }

    

}
