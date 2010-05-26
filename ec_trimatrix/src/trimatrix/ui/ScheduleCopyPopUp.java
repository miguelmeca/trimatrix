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
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.ScheduleCopyPopUp}")

public class ScheduleCopyPopUp extends MyWorkpageDispatchedBean implements Serializable {

    public ScheduleCopyPopUp(IWorkpageDispatcher dispatcher) {
        super(dispatcher);
    }

    private static enum Type {
        SINGLE, MULTI
    }

    protected IPopUpCallback callback;
    protected ScheduleItem scheduleItem;
    protected String athleteId;

    protected Type type = Type.SINGLE;
    public boolean isSingleCopy() {return type==Type.SINGLE;}
    public boolean isMultiCopy() {return type==Type.MULTI;}

    protected Date m_fromDate = new Date();
    public Date getFromDate() { return m_fromDate; }
    public void setFromDate(Date value) { m_fromDate = value; }

    protected Date m_toDate = new Date();
    public Date getToDate() { return m_toDate; }
    public void setToDate(Date value) { m_toDate = value; }

    protected Date m_copyDate = new Date();
    public Date getCopyDate() { return m_copyDate; }
    public void setCopyDate(Date value) { m_copyDate = value; }

    protected String m_copyAthleteId;
    public String getCopyAthleteId() { return m_copyAthleteId; }
    public void setCopyAthleteId(String value) { m_copyAthleteId = value; }

    public void prepareCallback(IPopUpCallback callback, ScheduleItem scheduleItem) {
        this.callback = callback;
        this.scheduleItem = scheduleItem;
        this.type = Type.SINGLE;
    }

    public void prepareCallback(IPopUpCallback callback, String athleteId) {
        this.callback = callback;
        this.athleteId = athleteId;
        this.type = Type.MULTI;
    }

    public void onCancel(ActionEvent event) {
        callback.cancel();
    }

    public void onCopy(ActionEvent event) {
        if(!checkMandatory()) return;
        if(isSingleCopy()) {
        	Schedules copiedSchedule = null;
            try {
                copiedSchedule = getLogic().getScheduleLogic().copySchedule(scheduleItem.getId(), getCopyAthleteId(), new Timestamp(getCopyDate().getTime()));
                Statusbar.outputSuccess(Helper.getMessages("schedule_copy_success"));
            } catch (Exception ex) {
                Statusbar.outputAlert(Helper.getMessages("schedule_copy_failure"),Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
            }
            callback.ok(copiedSchedule);
        } else if(isMultiCopy()) {
        	int count = getLogic().getScheduleLogic().copySchedules(athleteId, getCopyAthleteId(), new Timestamp(getFromDate().getTime()), new Timestamp(getToDate().getTime()), new Timestamp(getCopyDate().getTime()));
        	if(count>0) {
        		Statusbar.outputSuccess(String.format(Helper.getMessages("schedule_copy"),count));
        	} else {
        		Statusbar.outputAlert(Helper.getMessages("schedule_no_copy"), Helper.getLiteral("info")).setLeftTopReferenceCentered();
        	}
        	callback.ok(null);
        }
    }

    private boolean checkMandatory() {
        if(isEmpty(getCopyAthleteId())) {
            Statusbar.outputAlert(Helper.getMessages("athlete_missing"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
            return false;
        }
        if(getCopyDate()==null) {
        	Statusbar.outputAlert(Helper.getMessages("target_date_missing"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
            return false;
        }
        // special check for multi
        if(isMultiCopy()) {
            if(getFromDate()==null) {
            	Statusbar.outputAlert(Helper.getMessages("source_date_missing"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
                return false;
            }
        }
        return true;
    }

}
