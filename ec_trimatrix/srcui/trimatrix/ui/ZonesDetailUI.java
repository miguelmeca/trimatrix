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
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.ZonesDefinition;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpage;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass (expressionBase="#{d.ZonesDetailUI}")

public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable {
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();
    public FIXGRIDListBinding<GridZonesItem> getGridZones() { return m_gridZones; }
    public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) { m_gridZones = value; }
    	
    private Set<String> deletedIds = new HashSet<String>();
    
    public ZonesDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// a instance of MyWorkpage
        IWorkpage wp = getWorkpage();
        if (wp instanceof MyWorkpage) {
        	// authorization
        	authorization = ((MyWorkpage)wp).getAuthorization();
        }
        buildGrid();
	}
	
    public class GridZonesItem extends FIXGRIDItem implements java.io.Serializable
    {       	
    	public GridZonesItem() {    		
    		zonesDefinition = new ZonesDefinition(UUID.randomUUID().toString(), getServiceLayer().getDictionaryService().getMyPerson().getId());    		
    	}
    	
    	public GridZonesItem(ZonesDefinition zonesDefinition) {
    		this.zonesDefinition = zonesDefinition;
    	}   	

    	protected ZonesDefinition zonesDefinition;
    	public ZonesDefinition getZonesDefinition() { return zonesDefinition; }

        public double getLactateHigh() { return zonesDefinition.getLactateHigh(); }
        public void setLactateHigh(double value) { zonesDefinition.setLactateHigh(value); }

        public double getLactateLow() { return zonesDefinition.getLactateLow(); }
        public void setLactateLow(double value) { zonesDefinition.setLactateLow(value); }

        public String getDescription() { return zonesDefinition.getDescription(); }
        public void setDescription(String value) { zonesDefinition.setDescription(value); }

        protected String m_token;
        public String getToken() { return m_token; }
        public void setToken(String value) { m_token = value; }

        protected String m_color = "#FFFFFF";
        public String getColor() { return m_color; }
        public void setColor(String value) { 
        	m_color = value;
        	//m_color2 = value;
        }
        
        protected String m_color2;
        public String getColor2() { return m_color2; }
        public void setColor2(String value) { m_color2 = value; }     

        public void onChangeColor(ActionEvent event) {
        	m_color2 = m_color + "FF" ;
        }
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
		for(String id : deletedIds) {
			getDaoLayer().getZonesDefinitionDAO().delete(getDaoLayer().getZonesDefinitionDAO().findById(id));					
		}
		deletedIds.clear();
		// add or change zones
		int index = 0;		
		List<ZonesDefinition> zonesDefinitions = new ArrayList<ZonesDefinition>();
		for (GridZonesItem item : m_gridZones.getItems()) {
			ZonesDefinition zonesDefinition = item.getZonesDefinition();
			zonesDefinition.setSequence(index++);
			getDaoLayer().getZonesDefinitionDAO().merge(zonesDefinition);
			zonesDefinitions.add(zonesDefinition);
		}
		// update zones add global MyPerson instance to be consistent
		getServiceLayer().getDictionaryService().getMyPerson().setZonesDefinition(zonesDefinitions);
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
