package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.PersonsAthlete;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.logic.ZonesLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.ZonesDetailUI}")

public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable {
	
	private SAuthorization authorization;
	public boolean getCreateAllowed() { return authorization.create; }
	public boolean getDeleteAllowed() { return authorization.delete; }
	public boolean getChangeAllowed() { return authorization.change; }
	
	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();
    public FIXGRIDListBinding<GridZonesItem> getGridZones() { return m_gridZones; }
    public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) { m_gridZones = value; }
	
    private String personId;
    private PersonsAthlete personsAthlete;
    
	public ZonesDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get parameters from functiontree
		// get entity
        personId = getWorkpage().getParam(Constants.P_PERSON);
        if(personId==null || personId.length()==0) {
        	Statusbar.outputError("No or wrong entity set", "For list view processing an entity has to be set by the functiontreenode!");
        	getWorkpageContainer().closeWorkpage(getWorkpage());
        }
        // get athlete info
        personsAthlete = getLogic().getZonesLogic().getPersonsAthlete(personId);
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
		
    public class GridZonesItem extends FIXGRIDItem implements java.io.Serializable {
    	private ZonesDefinition definition;
    	private Zones zones;
    	
		public GridZonesItem(ZonesLogic.ZoneInfo zonesInfo) {
			this.definition = zonesInfo.getDefinition();
			if(zonesInfo.getZone()!=null) this.zones = zonesInfo.getZone();
		}
		public String getColor() { return definition.getColor(); }
		public String getForeground() { return Helper.getBlackOrWhite(getColor()); }
		public String getShortcut() { return definition.getShortcut(); }
		public Integer getHrLow() { return zones!=null ? zones.getHrLow() : null; }
		public Integer getHrHigh() { return zones!=null ? zones.getHrHigh() : null; }
		public Double getSpeedLow() { return zones!=null ? zones.getSpeedLow() : null; }
		public Double getSpeedHigh() { return zones!=null ? zones.getSpeedHigh() : null; }
		public Integer getHrLowPrct() { 
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrLow() / 100 : null; 
		}
		public Integer getHrHighPrct() { 
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrHigh() / 100 : null; 
		}	
    }
	
	private void buildGrid() {
		m_gridZones.getItems().clear();
		List<ZonesLogic.ZoneInfo> zonesInfos = getLogic().getZonesLogic().getAthletesZone(personId);
		for(ZonesLogic.ZoneInfo zonesInfo : zonesInfos) {
			m_gridZones.getItems().add(new GridZonesItem(zonesInfo));			
		}
	}
}
