package trimatrix.ui;

import static trimatrix.utils.Helper.correctTimeInput;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.ZonesDefinition;
import trimatrix.logic.helper.ScheduleRun;
import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Helper;

@CCGenClass(expressionBase = "#{d.ScheduleChangePopUp}")
public class ScheduleChangePopUp extends MyWorkpageDispatchedBean implements Serializable {

	public ScheduleChangePopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	public interface IPopupCallback {
    	public void cancel();

    	public void save();
    }

    protected IPopupCallback callback;
    protected ScheduleItem scheduleItem;

    public void init() {
    	// build run schedules
    	List<ScheduleRun> runList = getLogic().getScheduleLogic().getScheduleRuns(scheduleItem.getDetails());
    	gridRun.getItems().clear();
    	for(ScheduleRun item : runList) {
    		gridRun.getItems().add(new GridRunItem(item));
    	}
    }

    public String getColor() { return scheduleItem.getColor(); }
    public void setColor(String color) { scheduleItem.setColor(color); }

    public String getType() { return scheduleItem.getType(); }
    public void setType(String type) { scheduleItem.setType(type); }

    public String getText() { return scheduleItem.getDescription(); }
    public void setText(String text) { scheduleItem.setDescription(text); }

    public Date getStart() { return scheduleItem.getStart(); }
    public void setStart(Date start) { scheduleItem.setStart(new Timestamp(start.getTime())); }

    public String getDuration() { return Helper.calculateTime(scheduleItem.getDuration().intValue()*60, true); }
    public void setDuration(String duration) { scheduleItem.setDuration(Helper.calculateSeconds(duration).longValue()/60); }

    public void prepareCallback(IPopupCallback callback, ScheduleItem scheduleItem) {
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
    	for(GridRunItem item : gridRun.getItems()) {
    		runList.add(item.getScheduleRun());
    	}
    	scheduleItem.setDetails(getLogic().getScheduleLogic().buildString(runList));
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
		public String getDuration() {
			return scheduleRun.getDuration();
		}
		public void setDuration(String duration) {
			scheduleRun.setDuration(duration);
		}
		public String getZone() {
			return scheduleRun.getZone();
		}
		public void setZone(String zone) {
			scheduleRun.setZone(zone);
		}
		public Double getLactateLow() {
			return scheduleRun.getLactateLow();
		}
		public void setLactateLow(Double lactateLow) {
			scheduleRun.setLactateLow(lactateLow);
		}
		public Double getLactateHigh() {
			return scheduleRun.getLactateHigh();
		}
		public void setLactateHigh(Double lactateHigh) {
			scheduleRun.setLactateHigh(lactateHigh);
		}
		public Integer getHrLow() {
			return scheduleRun.getHrLow();
		}
		public void setHrLow(Integer hrLow) {
			scheduleRun.setHrLow(hrLow);
		}
		public Integer getHrHigh() {
			return scheduleRun.getHrHigh();
		}
		public void setHrHigh(Integer hrHigh) {
			scheduleRun.setHrHigh(hrHigh);
		}
		public String getComment() {
			return scheduleRun.getComment();
		}
		public void setComment(String comment) {
			scheduleRun.setComment(comment);
		}

		public void onTimeFlush(ActionEvent event) {
	        // get clientname to separate by source
//	        String clientname = (String) event.getComponent().getAttributes().get(CLIENTNAME);
//	        if(ResultEntity.SWIM.equalsIgnoreCase(clientname)) {
//	            swimSplit = correctTimeInput(swimSplit);
//	        } else if(ResultEntity.RUN.equalsIgnoreCase(clientname)) {
//	            runSplit = correctTimeInput(runSplit);
//	        } else if(ResultEntity.BIKE.equalsIgnoreCase(clientname)) {
//	            bikeSplit = correctTimeInput(bikeSplit);
//	        } else if(ResultEntity.OVERALL.equalsIgnoreCase(clientname)) {
//	            time = correctTimeInput(time);
//	        } else {
//	            return;
//	        }
			scheduleRun.setDuration(correctTimeInput(scheduleRun.getDuration()));
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

	private void recalculateEnd() {
		long duration = 0;
		for(GridRunItem item : gridRun.getItems()) {
			duration += Helper.calculateSeconds(item.getDuration());
		}
		scheduleItem.setDuration(duration/60);
	}

}
