package trimatrix.ui;

import static trimatrix.utils.Constants.CLIENTNAME;
import static trimatrix.utils.Helper.isEmpty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.DYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.IWorkpageProcessingEventListener;
import org.eclnt.workplace.WorkpageProcessingEvent;

import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.db.ZonesSwim;
import trimatrix.db.ZonesSwimId;
import trimatrix.logic.ZonesLogic;
import trimatrix.logic.ZonesLogic.IndividualSwimZoneInfo;
import trimatrix.structures.SAuthorization;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.ui.utils.WorkpageRefreshEvent;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Mode;

@CCGenClass(expressionBase = "#{d.ZonesDetailUI}")
public class ZonesDetailUI extends MyWorkpageDispatchedBean implements Serializable, IWorkpageProcessingEventListener {

	public boolean getChangeAllowed() {
		return authorization.change;
	}

	protected boolean renderSaveButton;

	public boolean getRenderSaveButton() {
		return renderSaveButton;
	}

	protected boolean renderEditButton;

	public boolean getRenderEditButton() {
		return renderEditButton;
	}

	protected boolean renderCancelButton;

	public boolean getRenderCancelButton() {
		return renderCancelButton;
	}

	private Mode mode;

	public Mode getMode() {
		return mode;
	}

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

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public boolean isEnabled() {
		return mode == Mode.CHANGE;
	}

	private SAuthorization authorization = SAuthorization.NONE;

	public void onEdit(ActionEvent event) {
		changeMode(Constants.Mode.CHANGE);
	}

	public void onCancel(ActionEvent event) {
		changeMode(Constants.Mode.SHOW);
		buildGrids();
	}

	public void onSave(ActionEvent event) {
		// delete process has to be at first place!
		boolean deleteOk = true;
		for (IndividualSwimZonesItem item : IndividualSwimZonesItemsToDelete) {
			if (!getLogic().getZonesLogic().deleteZonesSwim(item.zonesSwim.getId()))
				deleteOk = false;
		}
		if (deleteOk) {
			IndividualSwimZonesItemsToDelete.clear();
		} else {
			Statusbar.outputAlert(Helper.getMessages("ind_zones_delete_failure"), Helper.getLiteral("error")).setLeftTopReferenceCentered();
		}
		for (GridZonesItem item : m_gridZones.getItems()) {
			item.save();
		}
		for (String distance : getIndividualSwimZonesMap().keySet()) {
			for (IndividualSwimZonesItem item : getIndividualSwimZonesMap().get(distance).getItems()) {
				item.save();
			}
		}
		changeMode(Constants.Mode.SHOW);
		// refresh beans
		getWorkpage().throwWorkpageProcessingEvent(new WorkpageRefreshEvent(Entity.ZONE));
		// refresh
		buildGrids();
	}

	public void onCoachClicked(ActionEvent event) {
		if (coach == null)
			return;
		loadEntityDetailPage(Entity.PERSON, coach.getId(), coach.toString());
	}

	public void onRefresh(ActionEvent event) {
		buildGrids();
	}

	public void onAddIndividualZone(ActionEvent event) {
		if (getDistance() == null || getDistance() == 0)
			return;
		if (getIndividualSwimZonesMap().containsKey(getDistance().toString()))
			return;
		individualSwimZonesMap.put(getDistance().toString(), buildIndividualSwimZone(getDistance()));
		setIndividualSwimZonesDynamic();
	}

	protected FIXGRIDListBinding<GridZonesItem> m_gridZones = new FIXGRIDListBinding<GridZonesItem>();

	public FIXGRIDListBinding<GridZonesItem> getGridZones() {
		return m_gridZones;
	}

	public void setGridZones(FIXGRIDListBinding<GridZonesItem> value) {
		m_gridZones = value;
	}

	private String personId;
	private PersonsAthlete personsAthlete;
	private Persons coach;

	public String getCoach() {
		return coach != null ? coach.toString() : null;
	}

	public ZonesDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// register listener for events
		getWorkpage().addWorkpageProcessingEventListener(this);
		// get parameters from functiontree
		// get entity
		personId = getWorkpage().getParam(Constants.P_PERSON);
		if (personId == null || personId.length() == 0) {
			// Statusbar.outputAlert(Helper.getMessages("entity_wrong"),
			// Helper.getLiteral("error"),
			// Helper.getMessages("entity_wrong_detail")).setLeftTopReferenceCentered();
			Logger.getRootLogger().error(Helper.getMessages("entity_wrong"));
			getWorkpageContainer().closeWorkpage(getWorkpage());
		}
		// get athlete info
		personsAthlete = getLogic().getZonesLogic().getPersonsAthlete(personId);
		// get coach
		coach = getLogic().getZonesLogic().getStandardCoach(personId);
		// authorization
		if (coach != null)
			authorization = getLogic().getZonesLogic().getAuthorization(coach.getId());
		// set to show
		changeMode(Constants.Mode.SHOW);
		buildGrids();
		setIndividualSwimZonesDynamic();
	}

	public class GridZonesItem extends FIXGRIDItem implements java.io.Serializable {
		private ZonesDefinition definition;

		public ZonesDefinition getZonesDefinition() {
			return definition;
		}

		private Zones zones;
		private boolean dirty;

		public GridZonesItem(ZonesLogic.ZoneInfo zonesInfo) {
			this.definition = zonesInfo.getDefinition();
			if (zonesInfo.getZone() != null) {
				this.zones = zonesInfo.getZone();
			} else {
				this.zones = getLogic().getZonesLogic().createZone(personId, definition.getId());
			}
			dirty = false;
		}

		// immutable fields
		public String getColor() {
			return definition.getColor();
		}

		public String getForeground() {
			return Helper.getBlackOrWhite(getColor());
		}

		public String getShortcut() {
			return definition.getShortcut();
		}

		public Integer getHrLowPrctRun() {
			return personsAthlete != null ? personsAthlete.getMaxHr() * definition.getHrLowRun() / 100 : null;
		}

		public Integer getHrHighPrctRun() {
			return personsAthlete != null ? personsAthlete.getMaxHr() * definition.getHrHighRun() / 100 : null;
		}

		public Integer getHrLowPrctBike() {
			return personsAthlete != null ? personsAthlete.getMaxHr() * definition.getHrLowBike() / 100 : null;
		}

		public Integer getHrHighPrctBike() {
			return personsAthlete != null ? personsAthlete.getMaxHr() * definition.getHrHighBike() / 100 : null;
		}

		public String getTimeLowSwim() {
			return getTimeLowSwim(getDistance());
		}

		public String getTimeLowSwim(Integer distance) {
			if (getSpeedLowSwim() == null)
				return Constants.EMPTY;
			return HelperTime.calculateTime(getSpeedLowSwim() * distance, false);
		}

		public String getTimeHighSwim() {
			return getTimeHighSwim(getDistance());
		}

		public String getTimeHighSwim(Integer distance) {
			if (getSpeedHighSwim() == null)
				return Constants.EMPTY;
			return HelperTime.calculateTime(getSpeedHighSwim() * distance, false);
		}

		// mutable fields
		public Integer getHrLowRun() {
			return zones != null ? zones.getHrLowRun() : null;
		}

		public void setHrLowRun(Integer hrLowRun) {
			dirty = true;
			zones.setAutoHrRun(false);
			zones.setHrLowRun(hrLowRun);
		}

		public Integer getHrHighRun() {
			return zones != null ? zones.getHrHighRun() : null;
		}

		public void setHrHighRun(Integer hrHighRun) {
			dirty = true;
			zones.setAutoHrRun(false);
			zones.setHrHighRun(hrHighRun);
		}

		public Integer getHrTargetLowRun() {
			return zones != null ? zones.getHrTargetLowRun() : null;
		}

		public void setHrTargetLowRun(Integer hrTargetLowRun) {
			dirty = true;
			zones.setAutoHrRun(false);
			zones.setHrTargetLowRun(hrTargetLowRun);
		}

		public Integer getHrTargetHighRun() {
			return zones != null ? zones.getHrTargetHighRun() : null;
		}

		public void setHrTargetHighRun(Integer hrTargetHighRun) {
			dirty = true;
			zones.setAutoHrRun(false);
			zones.setHrTargetHighRun(hrTargetHighRun);
		}

		public Integer getHrLowBike() {
			return zones != null ? zones.getHrLowBike() : null;
		}

		public void setHrLowBike(Integer hrLowBike) {
			dirty = true;
			zones.setAutoHrBike(false);
			zones.setHrLowBike(hrLowBike);
		}

		public Integer getHrHighBike() {
			return zones != null ? zones.getHrHighBike() : null;
		}

		public void setHrHighBike(Integer hrHighBike) {
			dirty = true;
			zones.setAutoHrBike(false);
			zones.setHrHighBike(hrHighBike);
		}

		public Integer getHrTargetLowBike() {
			return zones != null ? zones.getHrTargetLowBike() : null;
		}

		public void setHrTargetLowBike(Integer hrTargetLowBike) {
			dirty = true;
			zones.setAutoHrBike(false);
			zones.setHrTargetLowBike(hrTargetLowBike);
		}

		public Integer getHrTargetHighBike() {
			return zones != null ? zones.getHrTargetHighBike() : null;
		}

		public void setHrTargetHighBike(Integer hrTargetHighBike) {
			dirty = true;
			zones.setAutoHrBike(false);
			zones.setHrTargetHighBike(hrTargetHighBike);
		}

		public Double getSpeedLowSwim() {
			return zones != null ? zones.getSpeedLowSwim() : null;
		}

		public void setSpeedLowSwim(Double speedLow) {
			dirty = true;
			zones.setAutoSpeedSwim(false);
			zones.setSpeedLowSwim(speedLow);
		}

		public Double getSpeedHighSwim() {
			return zones != null ? zones.getSpeedHighSwim() : null;
		}

		public void setSpeedHighSwim(Double speedHighSwim) {
			dirty = true;
			zones.setAutoSpeedSwim(false);
			zones.setSpeedHighSwim(speedHighSwim);
		}

		public void save() {
			if (dirty) {
				dirty = !getLogic().getZonesLogic().saveZone(zones);
			}
		}
	}

	private void buildGrids() {
		m_gridZones.getItems().clear();
		if (coach == null)
			return;
		List<ZonesLogic.ZoneInfo> zonesInfos = getLogic().getZonesLogic().getAthletesZone(personId, coach);
		for (ZonesLogic.ZoneInfo zonesInfo : zonesInfos) {
			m_gridZones.getItems().add(new GridZonesItem(zonesInfo));
		}
		setIndividualSwimZonesMap(); // select data
		setIndividualSwimZonesDynamic(); // build xml output
	}

	// ------------------------------------------------------------------------
	// individual zones logic
	// ------------------------------------------------------------------------
	private Set<IndividualSwimZonesItem> IndividualSwimZonesItemsToDelete = new HashSet<IndividualSwimZonesItem>();
	protected DYNAMICCONTENTBinding m_individualSwimZones = new DYNAMICCONTENTBinding();

	public DYNAMICCONTENTBinding getIndividualSwimZones() {
		return m_individualSwimZones;
	}

	private void setIndividualSwimZonesDynamic() {
		StringBuffer xml = new StringBuffer();
		xml.append("<t:pane rowdistance='5' >");
		SortedSet<String> sortedDistances = new TreeSet<String>(individualSwimZonesMap.keySet());
		int counterLimit = 3;
		int counter = 0;
		for (String distance : sortedDistances) {
			if (counter == 0)
				xml.append("<t:row>");
			xml.append("<t:foldablepane rowdistance='5' text='#{rr.literals.individual_swim_zones} " + distance + " m' >");
			xml.append("<t:row>");
			xml.append("<t:fixgrid avoidroundtrips='true' horizontalscrollmode='autowithresize' objectbinding='#{d.ZonesDetailUI.individualSwimZonesMap." + distance
					+ "}' sbvisibleamount='10' showemptyrows='false' >");
			xml.append("<t:gridcol align='center' searchenabled='false' sortenabled='false' text='#{rr.literals.area}' width='100' >");
			xml.append("<t:field align='center' background='.{color}' enabled='false' font='size:14;weight:bold' foreground='.{foreground}' text='.{shortcut}' width='100' />");
			xml.append("</t:gridcol>");
			xml.append("<t:gridcol searchenabled='false' sortenabled='false' text='#{rr.literals.time_low}' width='100' >");
			xml.append("<t:field align='right!' enabled='#{d.ZonesDetailUI.enabled}' text='.{timeLow}' width='100%' userhint='mm:ss,zzz' />");
			xml.append("</t:gridcol>");
			xml.append("<t:gridcol searchenabled='false' sortenabled='false' text='#{rr.literals.time_high}' width='100' >");
			xml.append("<t:field align='right!' enabled='#{d.ZonesDetailUI.enabled}' text='.{timeHigh}' width='100%' userhint='mm:ss,zzz' />");
			xml.append("</t:gridcol>");
			xml.append("</t:fixgrid>");
			xml.append("</t:row>");
			xml.append("<t:row>");
			xml
					.append("<t:button clientname='"
							+ distance
							+ "' image='/images/icons/delete.png' imageheight='15' text='#{rr.literals.delete}' enabled='#{d.ZonesDetailUI.enabled}' actionListener='#{d.ZonesDetailUI.onDeleteIndividualSwimZone}' />");
			xml.append("<t:coldistance />");
			xml
					.append("<t:button clientname='"
							+ distance
							+ "' image='/images/icons/refresh.png' imageheight='15' text='#{rr.literals.apply_again}' enabled='#{d.ZonesDetailUI.enabled}' actionListener='#{d.ZonesDetailUI.onApplyAgainIndividualSwimZone}' />");
			xml.append("</t:row>");
			xml.append("</t:foldablepane>");
			xml.append("<t:coldistance />");
			counter++;
			if (counter == counterLimit) {
				xml.append("</t:row>");
				counter = 0;
			}
		}
		if (counter != 0)
			xml.append("</t:row>");
		xml.append("</t:pane>");
		m_individualSwimZones.setContentXml(xml.toString());
	}

	protected Map<String, FIXGRIDListBinding<IndividualSwimZonesItem>> individualSwimZonesMap = new HashMap<String, FIXGRIDListBinding<IndividualSwimZonesItem>>();

	public Map<String, FIXGRIDListBinding<IndividualSwimZonesItem>> getIndividualSwimZonesMap() {
		return individualSwimZonesMap;
	}

	private void setIndividualSwimZonesMap() {
		individualSwimZonesMap.clear();
		Map<Integer, List<IndividualSwimZoneInfo>> individualSwimZoneInfoMap = getLogic().getZonesLogic().getIndividualSwimZones(personId, coach);
		for (Integer distance : individualSwimZoneInfoMap.keySet()) {
			FIXGRIDListBinding<IndividualSwimZonesItem> gridIndividualSwimZones = new FIXGRIDListBinding<IndividualSwimZonesItem>();
			for (IndividualSwimZoneInfo zone : individualSwimZoneInfoMap.get(distance)) {
				gridIndividualSwimZones.getItems().add(new IndividualSwimZonesItem(zone, false));
			}
			individualSwimZonesMap.put(distance.toString(), gridIndividualSwimZones);
		}
	}

	public class IndividualSwimZonesItem extends FIXGRIDItem implements java.io.Serializable {
		private ZonesDefinition definition;
		private ZonesSwim zonesSwim;
		private Integer distance;
		private boolean dirty;

		public IndividualSwimZonesItem(ZonesLogic.IndividualSwimZoneInfo individualSwimZoneInfo, boolean dirty) {
			this.definition = individualSwimZoneInfo.getDefinition();
			this.distance = individualSwimZoneInfo.getDistance();
			if (individualSwimZoneInfo.getZonesSwim() != null) {
				this.zonesSwim = individualSwimZoneInfo.getZonesSwim();
			} else {
				this.zonesSwim = getLogic().getZonesLogic().createZonesSwim(distance, personId, definition.getId());
			}
			this.dirty = dirty;
		}

		// immutable fields
		public String getColor() {
			return definition.getColor();
		}

		public String getForeground() {
			return Helper.getBlackOrWhite(getColor());
		}

		public String getShortcut() {
			return definition.getShortcut();
		}

		public String getTimeLow() {
			return zonesSwim.getTimeLow();
		}

		public void setTimeLow(String timeLow) {
			dirty = true;
			zonesSwim.setTimeLow(HelperTime.correctTimeInputShort(timeLow, true));
		}

		public String getTimeHigh() {
			return zonesSwim.getTimeHigh();
		}

		public void setTimeHigh(String timeHigh) {
			dirty = true;
			zonesSwim.setTimeHigh(HelperTime.correctTimeInputShort(timeHigh, true));
		}

		public void save() {
			if (dirty) {
				dirty = !getLogic().getZonesLogic().saveIndividualSwimZone(zonesSwim);
			}
		}
	}

	public void onDeleteIndividualSwimZone(ActionEvent event) {
		// get clientname to separate by source
		String distance = (String) event.getComponent().getAttributes().get(CLIENTNAME);
		if (isEmpty(distance))
			return;
		deleteIndividualSwimZones(distance);
		setIndividualSwimZonesDynamic();
	}

	private void deleteIndividualSwimZones(String distance) {
		IndividualSwimZonesItemsToDelete.addAll(getIndividualSwimZonesMap().get(distance).getItems());
		getIndividualSwimZonesMap().remove(distance);
	}

	public void onApplyAgainIndividualSwimZone(ActionEvent event) {
		// get clientname to separate by source
		String distance = (String) event.getComponent().getAttributes().get(CLIENTNAME);
		if (isEmpty(distance))
			return;
		deleteIndividualSwimZones(distance);
		individualSwimZonesMap.put(distance, buildIndividualSwimZone(Integer.valueOf(distance)));
		setIndividualSwimZonesDynamic();
	}

	public FIXGRIDListBinding<IndividualSwimZonesItem> buildIndividualSwimZone(Integer distance) {
		FIXGRIDListBinding<IndividualSwimZonesItem> gridIndividualSwimZones = new FIXGRIDListBinding<IndividualSwimZonesItem>();
		for (GridZonesItem item : getGridZones().getItems()) {
			ZonesSwimId zonesSwimId = new ZonesSwimId(distance, personId, item.getZonesDefinition().getId());
			ZonesSwim zonesSwim = new ZonesSwim(zonesSwimId, item.getTimeLowSwim(distance), item.getTimeHighSwim(distance));
			IndividualSwimZoneInfo zone = getLogic().getZonesLogic().createIndividualSwimZoneInfo(item.getZonesDefinition(), zonesSwim, distance);
			gridIndividualSwimZones.getItems().add(new IndividualSwimZonesItem(zone, true));
		}
		return gridIndividualSwimZones;
	}

	public void processEvent(WorkpageProcessingEvent event) {
		// refresh list
		if (event instanceof WorkpageRefreshEvent) {
			// reload coach to get right values (e.g. color)!
			coach = getLogic().getZonesLogic().getStandardCoach(personId);
			Entity entity = ((WorkpageRefreshEvent) event).getEntity();
			if (Entity.ZONE == entity.getBase())
				buildGrids();
		}

	}
}
