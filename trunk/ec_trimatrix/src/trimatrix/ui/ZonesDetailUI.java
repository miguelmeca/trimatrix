package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.IWorkpageProcessingEventListener;
import org.eclnt.workplace.WorkpageProcessingEvent;

import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.logic.ZonesLogic;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;

@CCGenClass (expressionBase="#{d.ZonesDetailUI}")

public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable, IWorkpageProcessingEventListener {

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
	 	// refresh beans
        getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(Entity.ZONE));
        // refresh
		buildGrid();
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
		// register listener for events
		getWorkpage().addWorkpageProcessingEventListener(this);
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
		public Integer getHrLowPrctRun() {
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrLowRun() / 100 : null;
		}
		public Integer getHrHighPrctRun() {
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrHighRun() / 100 : null;
		}
		public Integer getHrLowPrctBike() {
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrLowBike() / 100 : null;
		}
		public Integer getHrHighPrctBike() {
			return personsAthlete!=null ? personsAthlete.getMaxHr() * definition.getHrHighBike() / 100 : null;
		}
		public String getTimeLowSwim() {
			return Helper.calculateTime((int)(getSpeedLowSwim() * getDistance()), false);
		}
		public String getTimeHighSwim() {
			return Helper.calculateTime((int)(getSpeedHighSwim() * getDistance()), false);
		}
		// mutable fields
		public Integer getHrLowRun() { return zones!=null ? zones.getHrLowRun() : null; }
		public void setHrLowRun(Integer hrLowRun) {
			dirty = true;
			zones.setAutoHrRun(false);
			zones.setHrLowRun(hrLowRun);
		}
		public Integer getHrHighRun() { return zones!=null ? zones.getHrHighRun() : null; }
		public void setHrHighRun(Integer hrHighRun) {
			dirty = true;
			zones.setAutoHrRun(false);
			zones.setHrHighRun(hrHighRun);
		}

		public Integer getHrLowBike() { return zones!=null ? zones.getHrLowBike() : null; }
		public void setHrLowBike(Integer hrLowBike) {
			dirty = true;
			zones.setAutoHrBike(false);
			zones.setHrLowBike(hrLowBike);
		}
		public Integer getHrHighBike() { return zones!=null ? zones.getHrHighBike() : null; }
		public void setHrHighBike(Integer hrHighBike) {
			dirty = true;
			zones.setAutoHrBike(false);
			zones.setHrHighBike(hrHighBike);
		}

		public Double getSpeedLowSwim() { return zones!=null ? zones.getSpeedLowSwim() : null; }
		public void setSpeedLowSwim(Double speedLow) {
			dirty = true;
			zones.setAutoSpeedSwim(false);
			zones.setSpeedLowSwim(speedLow);
		}
		public Double getSpeedHighSwim() { return zones!=null ? zones.getSpeedHighSwim() : null; }
		public void setSpeedHighSwim(Double speedHighSwim) {
			dirty = true;
			zones.setAutoSpeedSwim(false);
			zones.setSpeedHighSwim(speedHighSwim);
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

	public void processEvent(WorkpageProcessingEvent event) {
		// refresh list
		if (event instanceof WorkpageRefreshEvent) {
			Entity entity = ((WorkpageRefreshEvent)event).getEntity();
			if(Entity.ZONE==entity.getBase()) buildGrid();
		}

	}
}
