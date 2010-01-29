package trimatrix.ui;

import java.io.Serializable;
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
		// TODO Auto-generated constructor stub
	}
	
	public interface IPopupCallback {
    	public void cancel();
    }
    
    protected IPopupCallback callback;
    protected ScheduleItem scheduleItem;
    
    public String getColor() { return scheduleItem.getColor(); }
    public void setColor(String color) { scheduleItem.setColor(color); }
    
    public String getType() { return scheduleItem.getType(); }
    public void setType(String type) { scheduleItem.setType(type); }
    
    public String getText() { return scheduleItem.getText(); }
    public void setText(String text) { scheduleItem.setText(text); }
    
    public Date getFrom() { return scheduleItem.getFrom(); }
    public void setFrom(Date from) { scheduleItem.setFrom(from); }
    
    public Date getTo() { return scheduleItem.getTo(); }
    public void setTo(Date to) { scheduleItem.setTo(to); }    

    public void prepareCallback(IPopupCallback callback, ScheduleItem scheduleItem) {
    	this.callback = callback;
    	this.scheduleItem = scheduleItem;
    }
    
    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }
    
    public void onOk(ActionEvent event) {
    	callback.cancel();
    }

}
