package trimatrix.ui;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass (expressionBase="#{d.ZonesDetailUI}")

public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable {
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();
    public FIXGRIDListBinding<GridZonesItem> getGridZones() { return m_gridZones; }
    public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) { m_gridZones = value; }
	
    private Constants.Entity entity;
    private String PersonId;
    
	public ZonesDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get parameters from functiontree
		// get entity
        String strEntity = getWorkpage().getParam(Constants.P_ENTITY);
        try {
        	entity = Constants.Entity.valueOf(strEntity.toUpperCase());
        } catch (Exception ex) {
        	Statusbar.outputError("No or wrong entity set", "For list view processing an entity has to be set by the functiontreenode!");
        	getWorkpageContainer().closeWorkpage(getWorkpage());
        }  
        PersonId = getWorkpage().getId();
        
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
    }
	
	private void buildGrid() {
		getLogic().getZonesLogic().getAthletesZone(getServiceLayer().getDictionaryService().getMyPerson().getId());
		if(getChangeAllowed()) {
			Statusbar.outputMessage("Änderungen erlaubt!");
		} else {
			Statusbar.outputMessage("Änderungen nicht erlaubt!");
		}		
		
	}
}
