package trimatrix.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import com.twolattes.json.Json;
import com.twolattes.json.Marshaller;
import com.twolattes.json.TwoLattes;

import trimatrix.db.DAOLayer;
import trimatrix.db.DayInfos;
import trimatrix.db.DayInfosId;
import trimatrix.db.Schedules;
import trimatrix.entities.EntityLayer;
import trimatrix.logic.helper.Limit;
import trimatrix.logic.helper.ScheduleRun;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public class ScheduleLogic {
	public static final Log logger = LogFactory.getLog(ScheduleLogic.class);
	private static final Marshaller<ScheduleRun> runMarshaller = TwoLattes.createMarshaller(ScheduleRun.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	private EntityLayer entityLayer;

	public Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal;
	}

	public int getWeekDay(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public Timestamp calculateBeginDate(float percentageValue, int scheduleMax, Date beginOfWeek, int day, int startingHour) {
		long beginInMinutes = Math.round(percentageValue * scheduleMax / 100f);
		// round on quarter hour
		beginInMinutes = ((beginInMinutes + 7) / 15) * 15;
		int hour = (int) (startingHour + beginInMinutes / 60);
		int minute = (int) (beginInMinutes % 60);
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginOfWeek);
		cal.add(Calendar.DAY_OF_WEEK, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		return new Timestamp(cal.getTimeInMillis());
	}

	public Date addDaysToDate(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	public Date addSecondsToDate(Date date, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	public DayInfos getDayInfos(String athleteId, Date date) {
		DayInfosId dayInfosId = new DayInfosId(date, athleteId);
		DayInfos dayInfos = daoLayer.getDayInfosDAO().findById(dayInfosId);
		if(dayInfos==null) dayInfos = new DayInfos(dayInfosId);
		return dayInfos;
	}

	public boolean existDayInfo(String athleteId, Date date) {
		DayInfosId dayInfosId = new DayInfosId(date, athleteId);
		DayInfos dayInfos = daoLayer.getDayInfosDAO().findById(dayInfosId);
		return dayInfos!=null;
	}

	public boolean saveDayInfos(DayInfos dayInfos) {
		try {
			daoLayer.getDayInfosDAO().merge(dayInfos);
			Statusbar.outputSuccess("Daten erfolgreich gespeichert!");
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Daten konnten nicht gespeichert werden!", "Fehler", ex.toString());
			return false;
		}
	}

	public List<Schedules> getWeeksSchedule(Date beginOfWeek, String personId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginOfWeek);
		cal.add(Calendar.DAY_OF_WEEK, 6); // Add a week
		Date startHigh = cal.getTime();
		return serviceLayer.getSqlExecutorService().getSchedules(personId, new Timestamp(beginOfWeek.getTime()), new Timestamp(startHigh.getTime()), false);
	}

//	public Schedules getSchedule(String id) {
//		return (Schedules) entityLayer.getScheduleEntity().get(id);
//	}

	public boolean deleteSchedule(String scheduleId) {
		return entityLayer.getScheduleEntity().delete(scheduleId);
	}

	public Schedules createSchedule(Timestamp from, String personId) {
		Schedules schedule = (Schedules)entityLayer.getScheduleEntity().create();
		schedule.setStart(from);
		schedule.setDuration(60L); // 1h in min
		schedule.setColor(Constants.WHITE);
		schedule.setPersonId(personId);
		schedule.setTemplate(false);
		return schedule;
	}

	public Schedules saveSchedule(Schedules schedule) throws Exception {
		return (Schedules)entityLayer.getScheduleEntity().save(schedule);
	}

	public Schedules getSchedule(String scheduleId) {
		return (Schedules) entityLayer.getScheduleEntity().get(scheduleId);
	}

	public void changeDuration(String scheduleId, long duration) throws Exception {
		Schedules schedule = (Schedules) entityLayer.getScheduleEntity().get(scheduleId);
		if(schedule!=null) {
			schedule.setDuration(duration);
			entityLayer.getScheduleEntity().save(schedule);
		}
	}

	public int getDifference(Date dateLow, Date dateHigh) {
		long diffMsec = dateHigh.getTime() - dateLow.getTime();
		return (int) (Math.abs(diffMsec) / 1000);
	}

	public void changeStart(String scheduleId, Timestamp start) throws Exception {
		Schedules schedule = (Schedules) entityLayer.getScheduleEntity().get(scheduleId);
		if(schedule!=null) {
			schedule.setStart(start);
			entityLayer.getScheduleEntity().save(schedule);
		}
	}

	public String buildString(List<ScheduleRun> runList) {
		return runMarshaller.marshallList(runList).toString();
    }

	public List<ScheduleRun> getScheduleRuns(String value) {
		if(Helper.isEmpty(value)) return Collections.EMPTY_LIST;
		Json.Array array = (Json.Array)Json.fromString(value);
		return runMarshaller.unmarshallList(array);
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public void setEntityLayer(EntityLayer entityLayer) {
		this.entityLayer = entityLayer;
	}
}
