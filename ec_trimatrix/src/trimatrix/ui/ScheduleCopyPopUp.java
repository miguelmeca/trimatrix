package trimatrix.ui;

import static trimatrix.utils.Helper.isEmpty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Schedules;
import trimatrix.ui.ScheduleUI.ScheduleItem;
import trimatrix.ui.utils.IPopUpCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass (expressionBase="#{d.ScheduleCopyPopUp}")

public class ScheduleCopyPopUp extends MyWorkpageDispatchedBean implements Serializable
{
    public ScheduleCopyPopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

    protected IPopUpCallback callback;
    protected ScheduleItem scheduleItem;

	protected Date m_copyDate = new Date();
    public Date getCopyDate() { return m_copyDate; }
    public void setCopyDate(Date value) { m_copyDate = value; }

    protected String m_copyAthleteId;
    public String getCopyAthleteId() { return m_copyAthleteId; }
    public void setCopyAthleteId(String value) { m_copyAthleteId = value; }

    public void prepareCallback(IPopUpCallback callback, ScheduleItem scheduleItem) {
    	this.callback = callback;
    	this.scheduleItem = scheduleItem;
    }

    public void onCancel(ActionEvent event) {
    	callback.cancel();
    }

    public void onCopy(ActionEvent event) {
    	if(isEmpty(getCopyAthleteId())) {
    		Statusbar.outputAlert("No athlete selected!");
    		return;
    	}
    	Schedules copiedSchedule = null;
    	try {
    		copiedSchedule = getLogic().getScheduleLogic().copySchedule(scheduleItem.getId(), getCopyAthleteId(), new Timestamp(getCopyDate().getTime()));
    		Statusbar.outputSuccess("Schedule was succesfully copied!");
    	} catch (Exception ex) {
    		Statusbar.outputAlert(ex.toString(),"Copy failed!");
    	}
    	callback.ok(copiedSchedule);
    }

}
