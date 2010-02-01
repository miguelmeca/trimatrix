package trimatrix.logic;

import java.util.Calendar;
import java.util.Date;

import trimatrix.db.DAOLayer;
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

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
