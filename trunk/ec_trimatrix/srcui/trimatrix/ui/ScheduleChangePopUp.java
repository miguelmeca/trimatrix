package trimatrix.ui;

import static trimatrix.utils.Constants.CLIENTNAME;
import static trimatrix.utils.Helper.calculateSeconds;
import static trimatrix.utils.Helper.calculateTime;
import static trimatrix.utils.Helper.correctTimeInput;
import static trimatrix.utils.Helper.isEmpty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.SchedulesDetail;
import trimatrix.db.Zones;
import trimatrix.db.ZonesDefinition;
import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass(expressionBase = "#{d.ScheduleChangePopUp}")
public class ScheduleChangePopUp extends MyWorkpageDispatchedBean implements Serializable {
	private static final String DURATION = "duration_";	// add underscore because it's used in grid
	private static final String DURATIONATHLETE = "durationAthlete_"; // add underscore because it's used in grid

	public ScheduleChangePopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	public interface IScheduleChangePopupCallback {
    	public void cancel();
    	public void save();
    	public void delete();
    }

    protected IScheduleChangePopupCallback callback;
    protected ScheduleItem scheduleItem;

    public void init() {
    	// build run schedules
    	//List<ScheduleRun> runList = getLogic().getScheduleLogic().getScheduleRuns(scheduleItem.getDetails());
    	gridRun.getItems().clear();
    	for(SchedulesDetail schedulesDetail : scheduleItem.getSchedulesDetail()) {
    		gridRun.getItems().add(new GridRunItem(schedulesDetail));
    	}
    }

    public Boolean getDone() { return scheduleItem.getDone(); }
	public void setDone(Boolean done) { scheduleItem.setDone(done); }

    public String getColor() { return scheduleItem.getColor(); }
    public void setColor(String color) { scheduleItem.setColor(color); }

    public String getType() { return scheduleItem.getType(); }
    public void setType(String type) { scheduleItem.setType(type); }

    public String getText() { return scheduleItem.getDescription(); }
    public void setText(String text) { scheduleItem.setDescription(text); }

    public Date getStart() { return scheduleItem.getStart(); }
    public void setStart(Date start) { scheduleItem.setStart(new Timestamp(start.getTime())); }

    public String getDuration() { return calculateTime(scheduleItem.getDuration().intValue()*60, true); }
    public void setDuration(String duration) { scheduleItem.setDuration(calculateSeconds(duration).longValue()/60); }

    public String getTemplateName() { return scheduleItem.getTemplateName(); }
    public void setTemplateName(String templateName) { scheduleItem.setTemplateName(templateName); }

    public void prepareCallback(IScheduleChangePopupCallback callback, ScheduleItem scheduleItem) {
    	this.callback = callback;
    	this.scheduleItem = scheduleItem;
    	init();
    }

    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    public void onSave(ActionEvent event) {
    	// build run schedule string
    	List<SchedulesDetail> schedulesDetails = new ArrayList<SchedulesDetail>(gridRun.getItems().size());
    	boolean failure = false;
    	// mandatory checks
    	if(isTemplate() && isEmpty(getTemplateName())) failure = true;
    	if(isEmpty(getType())) failure = true;
    	// if necessary check subitems
    	if(!failure) {
    		for(GridRunItem item : gridRun.getItems()) {
    			SchedulesDetail schedulesDetail = item.getScheduleRun();
        		// check mandatory fields
        		if(isEmpty(schedulesDetail.getDurationTarget()) ||
        		   isEmpty(schedulesDetail.getZoneId())) {
        			failure = true;
        			break;
        		}
        		schedulesDetails.add(schedulesDetail);
        	}
    	}
    	// exit if mandatory check fails
    	if(failure) {
    		Statusbar.outputAlert("Not all mandatory fields set!");
    		return;
    	}
    	// save schedule
    	// scheduleItem.setDetails(getLogic().getScheduleLogic().buildString(runList));
    	scheduleItem.setSchedulesDetail(schedulesDetails);
    	callback.save();
    }

    public void onDelete(ActionEvent ae) {
    	YESNOPopup popup = YESNOPopup.createInstance(
				"Confirm deletion",
				"Do you really want to delete this schedule?",
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {
						callback.delete();
					}
				}
		);
		popup.getModalPopup().setLeftTopReferenceCentered();
    }

    /**
     * Set unit to done, save and leave popup
     * @param ae
     */
    public void onSetDone(ActionEvent ae) {
    	setDone(true);
    	callback.save();
	}

    public void onAddItem(ActionEvent ae) {
		GridRunItem item = new GridRunItem();
		gridRun.getItems().add(item);
	}

	public void onRemoveItem(ActionEvent ae) {
		FIXGRIDItem selected = gridRun.getSelectedItem();
		if (selected == null) return;
		gridRun.getItems().remove(selected);
	}

	public boolean isPersonsSight() {
		return scheduleItem.getPersonId().equals(getServiceLayer().getDictionaryService().getMyPerson().getId());
	}

	public boolean isCreatorsSight() {
		return scheduleItem.getCreatorId().equals(getServiceLayer().getDictionaryService().getMyUser().getId());
	}

	public boolean isTemplate() {
		return scheduleItem.getTemplate();
	}

    // ------------------------------------------------------------------------
	// logic for run schedules
	// ------------------------------------------------------------------------
    protected FIXGRIDListBinding<GridRunItem> gridRun = new FIXGRIDListBinding<GridRunItem>();
    public FIXGRIDListBinding<GridRunItem> getGridRun() {return gridRun;}
	public void setGridRun(FIXGRIDListBinding<GridRunItem> gridRun) {this.gridRun = gridRun;}

	public class GridRunItem extends FIXGRIDItem implements java.io.Serializable {
    	SchedulesDetail scheduleRun;
    	public SchedulesDetail getScheduleRun() { return scheduleRun; }

    	public GridRunItem() {
    		scheduleRun = new SchedulesDetail();
    	}

		public GridRunItem(SchedulesDetail scheduleRun) {
			this.scheduleRun = scheduleRun;
			if(!isTemplate()) prefillZones();
		}

		public void onTimeFlush(ActionEvent event) {
	        // get clientname to separate by source
	        String clientname = (String) event.getComponent().getAttributes().get(CLIENTNAME);
	        if(isEmpty(clientname)) return;
	        if(clientname.startsWith(DURATION)) {
	        	scheduleRun.setDurationTarget(correctTimeInput(scheduleRun.getDurationTarget()));
	        } else if(clientname.startsWith(DURATIONATHLETE)) {
	        	scheduleRun.setDurationActual(correctTimeInput(scheduleRun.getDurationActual()));
	        } else {
	            return;
	        }
			recalculateEnd();
	    }

		public void onChangeIntensity(ActionEvent event) {
			// set back all properties
			scheduleRun.setLactateLow(null);
			scheduleRun.setLactateHigh(null);
			scheduleRun.setHrLow(null);
			scheduleRun.setHrHigh(null);
			// get zones definition
			ZonesDefinition definition = getDaoLayer().getZonesDefinitionDAO().findById(scheduleRun.getZoneId());
			if(definition==null) return;
			// set definition relevant properties
			scheduleRun.setLactateLow(definition.getLactateLow());
			scheduleRun.setLactateHigh(definition.getLactateHigh());
			// no handling when in template mode
			if(isTemplate()) return;
			// get athletes zone
			Zones example = new Zones();
			example.setAthleteId(scheduleItem.getPersonId());
			example.setZonesDefinitionId(scheduleRun.getZoneId());
			List<Zones> zones = getDaoLayer().getZonesDAO().findByExample(example);
			if(isEmpty(zones)) return; // no match!
			// normally just one match!
			Zones zone = zones.get(0);
			// set zone relevant properties
			scheduleRun.setHrLow(zone.getHrLowRun());
			scheduleRun.setHrHigh(zone.getHrHighRun());
			// TODO Check case where HR is calculated by max HR
		}

		private void prefillZones() {
			// when coming from a template values are by default empty
			if(scheduleRun.getHrLow()==null && scheduleRun.getHrHigh()==null) {
				// get athletes zone
				Zones example = new Zones();
				example.setAthleteId(scheduleItem.getPersonId());
				example.setZonesDefinitionId(scheduleRun.getZoneId());
				List<Zones> zones = getDaoLayer().getZonesDAO().findByExample(example);
				if(isEmpty(zones)) return; // no match!
				// normally just one match!
				Zones zone = zones.get(0);
				// set zone relevant properties
				scheduleRun.setHrLow(zone.getHrLowRun());
				scheduleRun.setHrHigh(zone.getHrHighRun());
			}
		}
    }

	/**
	 * Recalculate the duration, loop through the grid items, if
	 * a corrected duration (by athlete) exists take this, otherwise take
	 * the planned duration
	 */
	private void recalculateEnd() {
		long duration = 0;
		for(GridRunItem item : gridRun.getItems()) {
			int durationActual = calculateSeconds(item.getScheduleRun().getDurationActual());
			duration += durationActual>0 ? durationActual : calculateSeconds(item.getScheduleRun().getDurationActual());
		}
		scheduleItem.setDuration(duration/60);
	}

}
