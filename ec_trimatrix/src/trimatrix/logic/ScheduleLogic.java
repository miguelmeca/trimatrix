package trimatrix.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;

import trimatrix.db.DAOLayer;
import trimatrix.db.DayInfos;
import trimatrix.db.DayInfosId;
import trimatrix.db.Schedules;
import trimatrix.db.SchedulesDetail;
import trimatrix.db.UserPreferences;
import trimatrix.db.ZonesDefinition;
import trimatrix.entities.EntityLayer;
import trimatrix.logic.helper.DayInfo;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public class ScheduleLogic {
	public static final Log logger = LogFactory.getLog(ScheduleLogic.class);
	private DAOLayer daoLayer;
	private ServiceLayer serviceLayer;
	private EntityLayer entityLayer;
	private LogicLayer logicLayer;

	public static final Integer RUN = new Integer(1);
	public static final Integer BIKE = new Integer(2);
	public static final Integer SWIM = new Integer(3);
	public static final Long MS_OF_DAY = 86400000L;

	public static final Map<String, Integer> TYPES_WITH_DETAILS = new HashMap<String, Integer>() {
		//Unnamed Block.
		{ put("run", RUN); put("bike", BIKE); put("swim", SWIM);}
	};

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
			Statusbar.outputSuccess(Helper.getMessages("save_success"));
			return true;
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("save_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
			return false;
		}
	}

	public List<Schedules> getWeeksSchedule(Date beginOfWeek, String personId) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginOfWeek);
		cal.add(Calendar.DAY_OF_WEEK, 6); // Add a week
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date startHigh = cal.getTime();
		return serviceLayer.getSqlExecutorService().getSchedules(personId, new Timestamp(beginOfWeek.getTime()), new Timestamp(startHigh.getTime()), false);
	}

	public List<Schedules> getTemplates() {
		Schedules example = new Schedules();
		example.setPersonId(serviceLayer.getDictionaryService().getMyPerson().getId());
		example.setTemplate(true);
		example.setDeleted(false);
		return daoLayer.getSchedulesDAO().findByExample(example);
	}

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

	public Schedules createScheduleFromTemplate(Timestamp from, String personId, String templateId) {
		Schedules schedule = (Schedules)entityLayer.getScheduleEntity().get(templateId);
		String id = UUID.randomUUID().toString();
		schedule.setId(id);
		schedule.setStart(from);
		schedule.setDuration(60L); // 1h in min
		schedule.setPersonId(personId);
		schedule.setTemplate(false);
		return schedule;
	}

	public Schedules createTemplate(String personId) {
		Schedules template = (Schedules)entityLayer.getScheduleEntity().create();
		template.setColor(Constants.WHITE);
		template.setPersonId(personId);
		template.setTemplate(true);
		return template;
	}

	public Schedules copySchedule(String scheduleId, String athleteId, Timestamp from) throws Exception {
		Schedules schedule = (Schedules)entityLayer.getScheduleEntity().get(scheduleId);
		String id = UUID.randomUUID().toString();
		schedule.setId(id);
		schedule.setStart(from);
		schedule.setPersonId(athleteId);
		entityLayer.getScheduleEntity().save(schedule);
		return schedule;
	}

	public int copySchedules(String athleteId, String copyAthleteId, Timestamp from, Timestamp to, Timestamp copy) {
		// if no to date is defined take from date
		if(to==null) to = from;
		// add a day to to date
    	to = new Timestamp(to.getTime() + MS_OF_DAY);
    	// get differnce of from and copy date
    	long timeDiffernce = copy.getTime() - from.getTime();
		List<Schedules> schedules = getSchedulesByQuery(athleteId, from, to);
    	int count = 0;
    	for(Schedules schedule : schedules) {
    		String id = UUID.randomUUID().toString();
    		schedule.setId(id);
    		schedule.setPersonId(copyAthleteId);
    		schedule.setStart(new Timestamp(schedule.getStart().getTime()+timeDiffernce));
    		for(SchedulesDetail detail : schedule.getSchedulesDetail()) {
    			detail.getId().setId(id);
    		}
    		try {
				saveSchedule(schedule);
				count++;
			} catch (Exception e) {
				continue;
			}
    	}
    	return count;
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

	public void deleteAllSchedulesDetail(String id) {
		int size = serviceLayer.getSqlExecutorService().deleteAllSchedulesDetail(id);
		logger.debug(size + " : Schedule details deleted!");
	}

	public static Integer getTypeOrd(String type) {
		Integer ordinal = ScheduleLogic.TYPES_WITH_DETAILS.get(type);
		if(ordinal!=null) return ordinal;
		else return new Integer(0);
	}

	public String getSummary(Schedules schedule) {
		// old logic with printing out details!
//		StringBuffer sb = new StringBuffer();
//		String type = schedule.getType();
//		if(ScheduleLogic.TYPES_WITH_DETAILS.keySet().contains(type)) {
//			List<SchedulesDetail> schedulesDetails = schedule.getSchedulesDetail();
//			for(SchedulesDetail schedulesDetail : schedulesDetails) {
//				if(sb.length()>0) sb.append(Constants.NEWLINE);
//				ZonesDefinition definition = daoLayer.getZonesDefinitionDAO().findById(schedulesDetail.getZoneId());
//				if(definition==null) continue;
//				// special treatment for swim units
//				if(getTypeOrd(type)==ScheduleLogic.SWIM) {
//					sb.append(schedulesDetail.getUnit() + Constants.WHITESPACE + definition.getShortcut());
//				} else {
//					sb.append(schedulesDetail.getDurationTarget() + Constants.WHITESPACE + definition.getShortcut());
//				}
//
//			}
//		} else {
//			if(schedule.getDescription()!=null) sb.append(schedule.getDescription());
//		}
//		return sb.toString();
		return schedule.getDescription()!=null?schedule.getDescription():Constants.EMPTY;
	}

	public DayInfo getDayInfo() {
		UserPreferences preferences = serviceLayer.getDictionaryService().getMyUser().getPreferences();
		return logicLayer.getPreferencesLogic().getDayInfo(preferences.getDayinfos());
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

	public void setLogicLayer(LogicLayer logicLayer) {
		this.logicLayer = logicLayer;
	}

	public List<Schedules> getSchedulesByQuery(String personId, Date from, Date to) {
		return serviceLayer.getSqlExecutorService().getSchedules(personId, from==null?null:new Timestamp(from.getTime()), to==null?null:new Timestamp(to.getTime()), false);
	}
}
