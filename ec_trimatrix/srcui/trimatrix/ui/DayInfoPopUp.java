package trimatrix.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.DayInfos;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;

@CCGenClass(expressionBase = "#{d.DayInfoPopUp}")
public class DayInfoPopUp extends MyWorkpageDispatchedBean implements Serializable {
	private static final int WIDTH = 150; // distance for UI
	public int getWidth() { return WIDTH; }

	public DayInfoPopUp(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}

	protected DayInfos dayInfos;
	public DayInfos getDayInfos() { return dayInfos; }

	public String getSelectedDate() {
		return new SimpleDateFormat("EEEE, d. MMMM yyyy").format(date);
	}

	public void onPreviousDay(ActionEvent event) {
		date = getLogic().getScheduleLogic().addDaysToDate(date, -1);
		refresh();
	}

	public void onNextDay(ActionEvent event) {
		date = getLogic().getScheduleLogic().addDaysToDate(date, 1);
		refresh();
	}

	public interface IPopupCallback {
		public void cancel();
	}

	protected IPopupCallback callback;
	protected String athleteId;
	protected Date date;

	public void prepareCallback(IPopupCallback callback, String athleteId, Date date) {
		this.callback = callback;
		this.athleteId = athleteId;
		this.date = date;
		refresh();
	}

	public void onCancel(ActionEvent event) {
		callback.cancel();
	}

	private void refresh() {
		// get info from db, return a new empty if nothing found
		this.dayInfos = getLogic().getScheduleLogic().getDayInfos(athleteId, date);
	}

	public void onSave(ActionEvent event) {
		getLogic().getScheduleLogic().saveDayInfos(dayInfos);
		refresh();
	}

	public void onPrefill(ActionEvent event) {
		// infos from yesterday
		Date yesterday = getLogic().getScheduleLogic().addDaysToDate(date, -1);
		DayInfos yesterdayInfos = getLogic().getScheduleLogic().getDayInfos(athleteId, yesterday);
		if(yesterdayInfos==null) return;
		// copy only defined values
		this.dayInfos.setRestingHr(yesterdayInfos.getRestingHr());
	}

}
