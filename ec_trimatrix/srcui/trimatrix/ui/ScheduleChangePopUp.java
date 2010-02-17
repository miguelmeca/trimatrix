package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass(expressionBase = "#{d.ScheduleChangePopUp}")
public class ScheduleChangePopUp extends MyWorkpageDispatchedBean implements Serializable {

	public ScheduleChangePopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	public interface IPopupCallback {
    	public void cancel();

    	public void ok();
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

    public void onOk(ActionEvent event) {
    	callback.ok();
    }

}
