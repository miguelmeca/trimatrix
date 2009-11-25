package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.logic.ZonesLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;

@CCGenClass (expressionBase="#{d.ZonesDetailUI}")

public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable {	
	
	private boolean changeAllowed;
	public boolean getChangeAllowed() { return authorization.change; }
	
	protected boolean renderSaveButton;
	public boolean getRenderSaveButton() { return renderSaveButton; }
	
	protected boolean renderEditButton;
	public boolean getRenderEditButton() { return renderEditButton; }
	
	protected boolean renderCancelButton;
	public boolean getRenderCancelButton() { return renderCancelButton; }	
	
	private Mode mode;
	public Mode getMode() { return mode; }
	private void changeMode(Constants.Mode mode) {
		this.mode = mode;
		if (mode == Constants.Mode.CHANGE) {
			renderEditButton = false;
			renderSaveButton = true;
			renderCancelButton = true;
			
		} 
		if (mode == Constants.Mode.SHOW) {
			renderEditButton = true;
			renderSaveButton = false;
			renderCancelButton = false;
		}
	}	
	
	private Integer distance = 100;
	public Integer getDistance() { return distance; }
	public void setDistance(Integer distance) { this.distance = distance; }
	
	public boolean isEnabled() { return mode==Mode.CHANGE; }
	
	private SAuthorization authorization = SAuthorization.NONE;
		
	public void onEdit(ActionEvent event) {	changeMode(Constants.Mode.CHANGE); }
	
	public void onCancel(ActionEvent event) {
		changeMode(Constants.Mode.SHOW);
		buildGrid();
	}
	
	public void onSave(ActionEvent event) {
	 	for(GridZonesItem item : m_gridZones.getItems()) {
	 		item.save();
	 	}
	 	changeMode(Constants.Mode.SHOW);
	}
	
	public void onCoachClicked(ActionEvent event) {
		if(coach==null) return;
		loadEntityDetailPage(Entity.PERSON, coach.getId(), coach.toString());
	}
	
	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();
    public FIXGRIDListBinding<GridZonesItem> getGridZones() { return m_gridZones; }
    public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) { m_gridZones = value; }
	
    private String personId;
    private PersonsAthlete personsAthlete;
    private Persons coach;
    public String getCoach() { return coach!=null ? coach.toString() : null; } 
    
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
        // get coach
        coach = getLogic().getZonesLogic().getStandardCoach(personId);
        // authorization
        if(coach!=null) authorization = getLogic().getZonesLogic().getAuthorization(coach.getId());
        // set to show
        changeMode(Constants.Mode.SHOW);
		// set authorization
        changeAllowed = true;
        buildGrid();
	}
		
    public class GridZonesItem extends FIXGRIDItem implements java.io.Serializable {
    	private ZonesDefinition definition;
    	private Zones zones;
    	private boolean dirty;
    	
		public GridZonesItem(ZonesLogic.ZoneInfo zonesInfo) {
			this.definition = zonesInfo.getDefinition();
			if(zonesInfo.getZone()!=null) {
				this.zones = zonesInfo.getZone();
			} else {
				this.zones = getLogic().getZonesLogic().createZone(personId, definition.getId());
			}
			dirty = false;
		}
		// immutable fields
		public String getColor() { return definition.getColor(); }
		public String getForeground() { return Helper.getBlackOrWhite(getColor()); }
		public String getShortcut() { return definition.getShortcut(); }
		public Integer getHrLowPrct() { 
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrLow() / 100 : null; 
		}
		public Integer getHrHighPrct() { 
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrHigh() / 100 : null; 
		}
		public String getTimeLow() {
			return Helper.calculateTime((int)(getSpeedLow() * getDistance()), false);
		}
		public String getTimeHigh() {
			return Helper.calculateTime((int)(getSpeedHigh() * getDistance()), false);
		}
		// mutable fields
		public Integer getHrLow() { return zones!=null ? zones.getHrLow() : null; }
		public void setHrLow(Integer hrLow) {
			dirty = true;
			zones.setAutoHr(false);
			zones.setHrLow(hrLow);
		}
		public Integer getHrHigh() { return zones!=null ? zones.getHrHigh() : null; }
		public void setHrHigh(Integer hrHigh) {
			dirty = true;
			zones.setAutoHr(false);
			zones.setHrHigh(hrHigh);
		}
		public Double getSpeedLow() { return zones!=null ? zones.getSpeedLow() : null; }
		public void setSpeedLow(Double speedLow) {
			dirty = true;
			zones.setAutoSpeed(false);
			zones.setSpeedLow(speedLow);
		}
		public Double getSpeedHigh() { return zones!=null ? zones.getSpeedHigh() : null; }
		public void setSpeedHigh(Double speedHigh) {
			dirty = true;
			zones.setAutoSpeed(false);
			zones.setSpeedHigh(speedHigh);
		}			
		
		public void save() {
			if(dirty) {
				dirty = !getLogic().getZonesLogic().saveZone(zones);
			}
		}
    }
	
	private void buildGrid() {
		m_gridZones.getItems().clear();
		if(coach==null) return;
		List<ZonesLogic.ZoneInfo> zonesInfos = getLogic().getZonesLogic().getAthletesZone(personId, coach);
		for(ZonesLogic.ZoneInfo zonesInfo : zonesInfos) {
			m_gridZones.getItems().add(new GridZonesItem(zonesInfo));			
		}
	}
}
