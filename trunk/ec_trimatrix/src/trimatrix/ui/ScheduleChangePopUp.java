package trimatrix.ui;

import static trimatrix.utils.Constants.CLIENTNAME;
import static trimatrix.utils.Helper.calculateSeconds;
import static trimatrix.utils.Helper.calculateTime;
import static trimatrix.utils.Helper.correctTimeInput;
import static trimatrix.utils.Helper.correctTimeInput2;
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
import trimatrix.logic.ScheduleLogic;
import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Helper;

@CCGenClass(expressionBase = "#{d.ScheduleChangePopUp}")
public class ScheduleChangePopUp extends MyWorkpageDispatchedBean implements Serializable {
	private static final String DURATION_TARGET = "durationTarget_"; // add underscore because it's used in grid
	private static final String DURATION_ACTUAL = "durationActual_"; // add underscore because it's used in grid

	private static final String TIME_LOW = "timeLow";
	private static final String TIME_HIGH = "timeHigh";
	private static final String TIME_AVG = "timeAvg";

	private boolean renderButtons = true;
	public boolean isRenderButtons() {return renderButtons;}
	public void setRenderButtons(boolean renderButtons) {this.renderButtons = renderButtons;}

	private boolean isGridDirty;

	public ScheduleChangePopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher, true); // enable labeling
	}

	public interface IScheduleChangePopupCallback {
    	public void cancel();
    	public void save();
    	public void delete();
    }

    protected IScheduleChangePopupCallback callback;
    protected ScheduleItem scheduleItem;

    public void init() {
    	// build schedule details
    	gridDetail.getItems().clear();
    	for(SchedulesDetail schedulesDetail : scheduleItem.getSchedulesDetail()) {
    		gridDetail.getItems().add(new GridDetailItem(schedulesDetail));
    	}
    	isGridDirty = false;
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
    	// manually set entity id for label handling
    	setEntityId(scheduleItem.getId());
    	init();
    }

    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    /**
     * Save unit incl. mandatory field checks
     * @param event
     */
    public void onSave(ActionEvent event) {
    	// build run schedule string
    	List<SchedulesDetail> schedulesDetails = new ArrayList<SchedulesDetail>(gridDetail.getItems().size());
    	boolean failure = false;
    	// mandatory checks
    	if(isTemplate() && isEmpty(getTemplateName())) failure = true;
    	if(isEmpty(getType())) failure = true;
    	// if necessary check subitems
    	if(!failure) {
    		for(GridDetailItem item : gridDetail.getItems()) {
    			SchedulesDetail schedulesDetail = item.getScheduleDetail();
        		// check mandatory fields
    			// swim units are differnt to bike and run!
    			if(getTypeOrd()==ScheduleLogic.SWIM) {
    				if(isEmpty(schedulesDetail.getUnit())     ||
    				   isEmpty(schedulesDetail.getDistance()) ||
    	    		   isEmpty(schedulesDetail.getZoneId()) ) {
    					failure = true;
    	    		    break;
    				}
    			} else {
    				if(isEmpty(schedulesDetail.getDurationTarget()) ||
    		           isEmpty(schedulesDetail.getZoneId())) {
    		        	failure = true;
    		        	break;
    		        }
    			}
        		schedulesDetails.add(schedulesDetail);
        	}
    	}
    	// exit if mandatory check fails
    	if(failure) {
    		Statusbar.outputAlert(Helper.getMessages("mandatory"), Helper.getLiteral("warn"));
    		return;
    	}
    	// save schedule
    	// scheduleItem.setDetails(getLogic().getScheduleLogic().buildString(runList));
    	scheduleItem.setSchedulesDetail(schedulesDetails);
    	if (isGridDirty) {
			getLogic().getScheduleLogic().deleteAllSchedulesDetail(scheduleItem.getId());
			isGridDirty = false;
		}
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
		GridDetailItem item = new GridDetailItem();
		gridDetail.getItems().add(item);
	}

	public void onRemoveItem(ActionEvent ae) {
		GridDetailItem selected = gridDetail.getSelectedItem();
		if (selected == null) return;
		// only set dirty if an already persisted item is removed
		if(selected.getScheduleDetail().getId()!=null) isGridDirty = true;
		gridDetail.getItems().remove(selected);
		recalculateEnd();
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

	public boolean isDetailRelevant() {
		return ScheduleLogic.TYPES_WITH_DETAILS.keySet().contains(getType());
	}

	public boolean isDetailFilled() {
		return gridDetail.getItems().size()>0;
	}

	public boolean getEnableType() {
		return isCreatorsSight() && !isDetailFilled();
	}

	/**
	 * An ordinal number is necessary to control the fixgrid rendering in the UI directly
	 * @return ordinal number of type, when detail not relevant 0 is returned
	 */
	public Integer getTypeOrd() {
		return ScheduleLogic.getTypeOrd(getType());
	}

    // ------------------------------------------------------------------------
	// logic for schedules
	// ------------------------------------------------------------------------
    protected FIXGRIDListBinding<GridDetailItem> gridDetail = new FIXGRIDListBinding<GridDetailItem>();
    public FIXGRIDListBinding<GridDetailItem> getGridDetail() {return gridDetail;}
	public void setGridDetail(FIXGRIDListBinding<GridDetailItem> gridDetail) {this.gridDetail = gridDetail;}

	public class GridDetailItem extends FIXGRIDItem implements java.io.Serializable {
    	SchedulesDetail scheduleDetail;
    	public SchedulesDetail getScheduleDetail() { return scheduleDetail; }

    	public GridDetailItem() {
    		scheduleDetail = new SchedulesDetail();
    	}

		public GridDetailItem(SchedulesDetail scheduleDetail) {
			this.scheduleDetail = scheduleDetail;
			if(!isTemplate()) prefillZones();
		}

		public void onTimeFlush(ActionEvent event) {
	        // get clientname to separate by source
	        String clientname = (String) event.getComponent().getAttributes().get(CLIENTNAME);
	        if(isEmpty(clientname)) return;
	        if(clientname.startsWith(DURATION_TARGET)) {
	        	scheduleDetail.setDurationTarget(correctTimeInput(scheduleDetail.getDurationTarget()));
	        } else if(clientname.startsWith(DURATION_ACTUAL)) {
	        	scheduleDetail.setDurationActual(correctTimeInput(scheduleDetail.getDurationActual()));
	        } else if(clientname.startsWith(TIME_LOW)) {
	        	scheduleDetail.setTimeLow(correctTimeInput2(scheduleDetail.getTimeLow()));
	        } else if(clientname.startsWith(TIME_HIGH)) {
	        	scheduleDetail.setTimeHigh(correctTimeInput2(scheduleDetail.getTimeHigh()));
	        } else if(clientname.startsWith(TIME_AVG)) {
	        	scheduleDetail.setTimeAvg(correctTimeInput2(scheduleDetail.getTimeAvg()));
	        } else {
	            return;
	        }
			recalculateEnd();
	    }

		public void onChangeIntensity(ActionEvent event) {
			// set back all properties
			scheduleDetail.setLactateLow(null);
			scheduleDetail.setLactateHigh(null);
			scheduleDetail.setHrLow(null);
			scheduleDetail.setHrHigh(null);
			// get zones definition
			ZonesDefinition definition = getDaoLayer().getZonesDefinitionDAO().findById(scheduleDetail.getZoneId());
			if(definition==null) return;
			// set definition relevant properties
			scheduleDetail.setLactateLow(definition.getLactateLow());
			scheduleDetail.setLactateHigh(definition.getLactateHigh());
			// no handling when in template mode
			if(isTemplate()) return;
			// get athletes zone
			Zones example = new Zones();
			example.setAthleteId(scheduleItem.getPersonId());
			example.setZonesDefinitionId(scheduleDetail.getZoneId());
			List<Zones> zones = getDaoLayer().getZonesDAO().findByExample(example);
			if(isEmpty(zones)) return; // no match!
			// normally just one match!
			Zones zone = zones.get(0);
			// set zone relevant properties
			scheduleDetail.setHrLow(zone.getHrLowRun());
			scheduleDetail.setHrHigh(zone.getHrHighRun());
			// TODO Check case where HR is calculated by max HR
		}

		private void prefillZones() {
			// when coming from a template values are by default empty
			if(scheduleDetail.getHrLow()==null && scheduleDetail.getHrHigh()==null) {
				// get athletes zone
				Zones example = new Zones();
				example.setAthleteId(scheduleItem.getPersonId());
				example.setZonesDefinitionId(scheduleDetail.getZoneId());
				List<Zones> zones = getDaoLayer().getZonesDAO().findByExample(example);
				if(isEmpty(zones)) return; // no match!
				// normally just one match!
				Zones zone = zones.get(0);
				// set zone relevant properties
				scheduleDetail.setHrLow(zone.getHrLowRun());
				scheduleDetail.setHrHigh(zone.getHrHighRun());
			}
		}
    }

	/**
	 * Recalculate the duration, loop through the grid items, if
	 * a corrected duration (by athlete) exists take this, otherwise take
	 * the planned duration
	 */
	private void recalculateEnd() {
		// no calculation for swim units
		if(getTypeOrd()==ScheduleLogic.SWIM) return;
		long duration = 0;
		for(GridDetailItem item : gridDetail.getItems()) {
			int durationActual = calculateSeconds(item.getScheduleDetail().getDurationActual());
			duration += durationActual>0 ? durationActual : calculateSeconds(item.getScheduleDetail().getDurationTarget());
		}
		scheduleItem.setDuration(duration/60);
	}

	public void onChangeType(ActionEvent event) {

	}

}
