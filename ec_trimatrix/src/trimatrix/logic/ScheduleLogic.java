package trimatrix.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.DAOLayer;
import trimatrix.db.DayInfos;
import trimatrix.db.DayInfosId;
import trimatrix.entities.IEntityData;
import trimatrix.services.ServiceLayer;

public class ScheduleLogic {
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;

	public Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal;
	}

	public Date calculateBeginDate(float percentageValue, int scheduleMax, Date beginOfWeek, int day, int startingHour) {
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
		return cal.getTime();
	}

	public Date addDaysToDate(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	public DayInfos getDayInfos(String athleteId, Date date) {
		DayInfosId dayInfosId = new DayInfosId(date, athleteId);
		DayInfos dayInfos = daoLayer.getDayInfosDAO().findById(dayInfosId);
		if(dayInfos==null) dayInfos = new DayInfos(dayInfosId);
		return dayInfos;
	}

	public boolean saveDayInfos(DayInfos dayInfos) {
		try {
			daoLayer.getDayInfosDAO().merge(dayInfos);
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert("Daten konnten nicht gespeichert werden!", "Fehler", ex.toString());
			return false;
		}
	}

	public List<IEntityData> getWeeksSchedule(Date beginOfWeek, String personId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginOfWeek);
		cal.add(Calendar.DAY_OF_WEEK, 6); // Add a week
		Date startHigh = cal.getTime();
		return serviceLayer.getSqlExecutorService().getScheduleEntities(null, personId, new Timestamp(beginOfWeek.getTime()), new Timestamp(startHigh.getTime()) );
	}

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
