package trimatrix.logic;

import java.util.Calendar;

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

	public void setServiceLayer(ServiceLayer serviceLayer) {
		this.serviceLayer = serviceLayer;
	}

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}
}
