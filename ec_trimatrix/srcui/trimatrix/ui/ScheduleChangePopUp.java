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

import trimatrix.db.ZonesDefinition;
import trimatrix.logic.helper.ScheduleRun;
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
    	List<ScheduleRun> runList = getLogic().getScheduleLogic().getScheduleRuns(scheduleItem.getDetails());
    	gridRun.getItems().clear();
    	for(ScheduleRun item : runList) {
    		gridRun.getItems().add(new GridRunItem(item));
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
    	List<ScheduleRun> runList = new ArrayList<ScheduleRun>(gridRun.getItems().size());
    	boolean failure = false;
    	// mandatory checks
    	if(isTemplate() && isEmpty(getTemplateName())) failure = true;
    	if(isEmpty(getType())) failure = true;
    	// if necessary check subitems
    	if(!failure) {
    		for(GridRunItem item : gridRun.getItems()) {
        		ScheduleRun scheduleRun = item.getScheduleRun();
        		// check mandatory fields
        		if(isEmpty(scheduleRun.getDuration()) ||
        		   isEmpty(scheduleRun.getZone())) {
        			failure = true;
        			break;
        		}
        		runList.add(scheduleRun);
        	}
    	}
    	// exit if mandatory check fails
    	if(failure) {
    		Statusbar.outputAlert("Not all mandatory fields set!");
    		return;
    	}
    	// save schedule
    	scheduleItem.setDetails(getLogic().getScheduleLogic().buildString(runList));
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
    	ScheduleRun scheduleRun;
    	public ScheduleRun getScheduleRun() { return scheduleRun; }

    	public GridRunItem() {
    		scheduleRun = new ScheduleRun();
    	}

		public GridRunItem(ScheduleRun scheduleRun) {
			this.scheduleRun = scheduleRun;
		}

		public void onTimeFlush(ActionEvent event) {
	        // get clientname to separate by source
	        String clientname = (String) event.getComponent().getAttributes().get(CLIENTNAME);
	        if(isEmpty(clientname)) return;
	        if(clientname.startsWith(DURATION)) {
	        	scheduleRun.setDuration(correctTimeInput(scheduleRun.getDuration()));
	        } else if(clientname.startsWith(DURATIONATHLETE)) {
	        	scheduleRun.setDurationAthlete(correctTimeInput(scheduleRun.getDurationAthlete()));
	        } else {
	            return;
	        }
			recalculateEnd();
	    }

		public void onChangeIntensity(ActionEvent event) {
			// get zone
			ZonesDefinition definition = getDaoLayer().getZonesDefinitionDAO().findById(scheduleRun.getZone());
			if(definition==null) return;
			// set parameter
			scheduleRun.setLactateLow(definition.getLactateLow());
			scheduleRun.setLactateHigh(definition.getLactateHigh());
			scheduleRun.setHrLow(definition.getHrLow());
			scheduleRun.setHrHigh(definition.getHrHigh());
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
			int durationAthlete = calculateSeconds(item.getScheduleRun().getDurationAthlete());
			duration += durationAthlete>0 ? durationAthlete : calculateSeconds(item.getScheduleRun().getDuration());
		}
		scheduleItem.setDuration(duration/60);
	}

}
