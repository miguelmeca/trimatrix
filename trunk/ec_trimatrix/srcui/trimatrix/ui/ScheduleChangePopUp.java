package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.ui.TestDetailUI.AGridItem;
import trimatrix.ui.TestDetailUI.GridErgoItem;
import trimatrix.ui.TestDetailUI.GridSwimItem;
import trimatrix.ui.TestDetailUI.GridTreadmillItem;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

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

    public String getColor() { return scheduleItem.getColor(); }
    public void setColor(String color) { scheduleItem.setColor(color); }

    public String getType() { return scheduleItem.getType(); }
    public void setType(String type) { scheduleItem.setType(type); }

    public String getText() { return scheduleItem.getDescription(); }
    public void setText(String text) { scheduleItem.setDescription(text); }

    public Date getStart() { return scheduleItem.getStart(); }
    public void setStart(Date start) { scheduleItem.setStart(new Timestamp(start.getTime())); }

    public Date getEnd() { return scheduleItem.getEnd(); }
    public void setEnd(Date end) { scheduleItem.setEnd(new Timestamp(end.getTime())); }

    public void prepareCallback(IPopupCallback callback, ScheduleItem scheduleItem) {
    	this.callback = callback;
    	this.scheduleItem = scheduleItem;
    }

    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    public void onSave(ActionEvent event) {
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
    	String duration;
		String zone;
		Double lactateLow;
	    Double lactateHigh;
	    Integer hrLow;
	    Integer hrHigh;
	    String comment;

		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getZone() {
			return zone;
		}
		public void setZone(String zone) {
			this.zone = zone;
		}
		public Double getLactateLow() {
			return lactateLow;
		}
		public void setLactateLow(Double lactateLow) {
			this.lactateLow = lactateLow;
		}
		public Double getLactateHigh() {
			return lactateHigh;
		}
		public void setLactateHigh(Double lactateHigh) {
			this.lactateHigh = lactateHigh;
		}
		public Integer getHrLow() {
			return hrLow;
		}
		public void setHrLow(Integer hrLow) {
			this.hrLow = hrLow;
		}
		public Integer getHrHigh() {
			return hrHigh;
		}
		public void setHrHigh(Integer hrHigh) {
			this.hrHigh = hrHigh;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
    }

}
