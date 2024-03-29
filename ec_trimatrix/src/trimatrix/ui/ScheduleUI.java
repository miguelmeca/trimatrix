package trimatrix.ui;

import static trimatrix.utils.Helper.isEmpty;

import java.awt.Color;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;

import javax.faces.event.ActionEvent;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.math.NumberUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.bufferedcontent.BufferedContentMgr;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventDrop;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.events.BaseActionEventInvoke;
import org.eclnt.jsfserver.elements.events.BaseActionEventPopupMenuItem;
import org.eclnt.jsfserver.elements.events.BaseActionEventScheduleSizeChanged;
import org.eclnt.jsfserver.elements.impl.BUTTONComponent;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.SCHEDULEComponent;
import org.eclnt.jsfserver.elements.impl.SCHEDULEITEMComponentTag;
import org.eclnt.jsfserver.elements.util.Trigger;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDefaultLifecycleListener;

import trimatrix.db.DayInfos;
import trimatrix.db.Persons;
import trimatrix.db.Schedules;
import trimatrix.db.SchedulesDetail;
import trimatrix.db.SchedulesDetailId;
import trimatrix.db.ZonesDefinition;
import trimatrix.logic.ScheduleLogic;
import trimatrix.reports.excel.CalendarOverview;
import trimatrix.services.TranslationService;
import trimatrix.ui.utils.IPopUpCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.HelperTime;

@CCGenClass(expressionBase = "#{d.ScheduleUI}")

public class ScheduleUI extends MyWorkpageDispatchedBean implements
		Serializable {
    protected int m_tabIndex;
    public int getTabIndex() { return m_tabIndex; }
    public void setTabIndex(int value) { m_tabIndex = value; }


	private static enum SCHEDULETYPES {
		RUN, BIKE, SWIM, PRIVATE
	}

    public void onChangeAthlete(ActionEvent event) {
    	refresh();
    	// clear agenda
    	getGridAgenda().getItems().clear();
    }

    public boolean isTrainer() {
    	return getServiceLayer().getDictionaryService().getMyRoles().contains(Constants.Role.COACH.getName());
    }

    protected String athleteID;
    public String getAthleteID() {return athleteID;}
	public void setAthleteID(String athleteID) {this.athleteID = athleteID;}

	protected static final long DURATION = 3600000; // in ms
	public static final int STARTINGHOUR = 5;
	public static final int NUMBEROFBLOCKS = 36;

	public int getNumberOfBlocks() {
		return NUMBEROFBLOCKS;
	}

	public int getStartingHour() {
		return STARTINGHOUR;
	}

	protected final int SCHEDULEMAX = NUMBEROFBLOCKS * 30; // 30 minutes blocks

	public int getScheduleMax() {
		return SCHEDULEMAX;
	}

	Trigger animationTrigger = new Trigger();

	public Trigger getAnimationTrigger() {
		return animationTrigger;
	}

	String animationType = null;

	public String getAnimationType() {
		return animationType;
	}

	Calendar calendar;

	private Date getBeginOfWeek() {
		return calendar.getTime();
	}

	public int getWeekNumber() {
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	ScheduleItem selectedScheduleItem = null;

	private void selectScheduleItem(ScheduleItem item) {
		selectedScheduleItem = item;
	}

	int[] DAYSOFWEEK = new int[] { Calendar.MONDAY, Calendar.TUESDAY,
			Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY,
			Calendar.SATURDAY, Calendar.SUNDAY };

	SCHEDULEComponent m_day0;
	SCHEDULEComponent m_day1;
	SCHEDULEComponent m_day2;
	SCHEDULEComponent m_day3;
	SCHEDULEComponent m_day4;
	SCHEDULEComponent m_day5;
	SCHEDULEComponent m_day6;
	ScheduleProcessor m_day0Processor = new ScheduleProcessor(0);
	ScheduleProcessor m_day1Processor = new ScheduleProcessor(1);
	ScheduleProcessor m_day2Processor = new ScheduleProcessor(2);
	ScheduleProcessor m_day3Processor = new ScheduleProcessor(3);
	ScheduleProcessor m_day4Processor = new ScheduleProcessor(4);
	ScheduleProcessor m_day5Processor = new ScheduleProcessor(5);
	ScheduleProcessor m_day6Processor = new ScheduleProcessor(6);

	public void setDay0(SCHEDULEComponent value) {
		if (m_day0 == value)
			return;
		m_day0 = value;
		refreshSchedule(0);
	}

	public void setDay1(SCHEDULEComponent value) {
		if (m_day1 == value)
			return;
		m_day1 = value;
		refreshSchedule(1);
	}

	public void setDay2(SCHEDULEComponent value) {
		if (m_day2 == value)
			return;
		m_day2 = value;
		refreshSchedule(2);
	}

	public void setDay3(SCHEDULEComponent value) {
		if (m_day3 == value)
			return;
		m_day3 = value;
		refreshSchedule(3);
	}

	public void setDay4(SCHEDULEComponent value) {
		if (m_day4 == value)
			return;
		m_day4 = value;
		refreshSchedule(4);
	}

	public void setDay5(SCHEDULEComponent value) {
		if (m_day5 == value)
			return;
		m_day5 = value;
		refreshSchedule(5);
	}

	public void setDay6(SCHEDULEComponent value) {
		if (m_day6 == value)
			return;
		m_day6 = value;
		refreshSchedule(6);
	}

	public ScheduleProcessor getSp0() {
		return m_day0Processor;
	}

	public ScheduleProcessor getSp1() {
		return m_day1Processor;
	}

	public ScheduleProcessor getSp2() {
		return m_day2Processor;
	}

	public ScheduleProcessor getSp3() {
		return m_day3Processor;
	}

	public ScheduleProcessor getSp4() {
		return m_day4Processor;
	}

	public ScheduleProcessor getSp5() {
		return m_day5Processor;
	}

	public ScheduleProcessor getSp6() {
		return m_day6Processor;
	}

	public String getRunHours() {
		return getHours(ScheduleLogic.RUNSET);
	}

	public String getBikeHours() {
		return getHours(ScheduleLogic.BIKESET);
	}

	public String getSwimHours() {
		return getHours(ScheduleLogic.SWIMSET);
	}

	public String getTotalHours() {
		return getHours(ScheduleLogic.TRISET);
	}

	public String getRunHoursBgpaint() {
		return getBgpaintForStatistic(ScheduleLogic.RUNSET);
	}

	public String getBikeHoursBgpaint() {
		return getBgpaintForStatistic(ScheduleLogic.BIKESET);
	}

	public String getSwimHoursBgpaint() {
		return getBgpaintForStatistic(ScheduleLogic.SWIMSET);
	}

	public String getTotalHoursBgpaint() {
		return getBgpaintForStatistic(ScheduleLogic.TRISET);
	}

	private String getHours(Set<Integer> scheduleTypes) {
		Long duration = 0L;
		for (ScheduleItem item : getScheduleItems()) {
			if(scheduleTypes.contains(ScheduleLogic.getTypeOrd(item.getType()))) {
				duration += item.getDuration();
			}
		}
		return Helper.round(duration.doubleValue()/60, 1).toString();
	}

	/**
	 * Creates a map with a summation of schedule details durations by zone.
	 * Key of map is the sequence:color combination as string, there's an
	 * additional entry with key EMPTY which contains the sum.
	 * A TreeMap is used for natural sorting of the key
	 * @param scheduleType
	 * @return
	 */
	private Map<String, Integer> getColorDurationMap(Set<Integer> scheduleTypes) {
		Map<String, Integer> colorDurationMap = new TreeMap<String, Integer>(); // TreeMap for sorting!
		int totalDuration = 0;
		for (ScheduleItem item : getScheduleItems()) {
			if(scheduleTypes.contains(ScheduleLogic.getTypeOrd(item.getType()))) {
				for(SchedulesDetail detail : item.getSchedulesDetail()) {
					ZonesDefinition definition = getDaoLayer().getZonesDefinitionDAO().findById(detail.getZoneId());
					String key = definition.getSequence() + Constants.COLON + definition.getColor();
					String duration = null;
					Integer durationInSeconds = null;
					if(ScheduleLogic.getTypeOrd(item.getType())==ScheduleLogic.SWIM) {
						double factor = detail.getTotalDistance() / detail.getDistance();
						duration =  detail.getTimeHigh();
						if(!Helper.isEmpty(detail.getTimeAvg())) duration = detail.getTimeAvg();
						durationInSeconds = (int)Math.round(factor * HelperTime.calculateSeconds(duration));
					} else {
						duration = detail.getDurationTarget();
						if(!Helper.isEmpty(detail.getDurationActual())) duration = detail.getDurationActual();
						durationInSeconds = HelperTime.calculateSeconds(duration);
					}
					totalDuration += durationInSeconds;
					if(colorDurationMap.containsKey(key)) {
						colorDurationMap.put(key, colorDurationMap.get(key) + durationInSeconds);
					} else {
						colorDurationMap.put(key, durationInSeconds);
					}
				}
			}
		}
		colorDurationMap.put(Constants.EMPTY, totalDuration);
		return colorDurationMap;
	}

	private String getBgpaintForStatistic(Set<Integer> scheduleTypes) {
		StringBuilder bgpaint = new StringBuilder();
		final String TEMPLATE = "rectangle({0}%,0%,{1}%,100%,{2})";
		Map<String, Integer> colorDurationMap = getColorDurationMap(scheduleTypes);
		Integer totalDuration = colorDurationMap.get(Constants.EMPTY);
		colorDurationMap.remove(Constants.EMPTY);
		Double totalPercentage = 100d / totalDuration;
		Integer lastPercentage = new Integer(0);
		for(Entry<String, Integer> entry : colorDurationMap.entrySet()) {
			String color = entry.getKey().split(Constants.COLON)[1];
			Integer percentage = (int)(totalPercentage * entry.getValue());
			bgpaint.append(TEMPLATE.replace("{0}", lastPercentage.toString()).replace("{1}", percentage.toString()).replace("{2}", color));
			bgpaint.append(";");
			lastPercentage += percentage;
		}
		return bgpaint.toString();
	}

	public ScheduleUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		calendar = getLogic().getScheduleLogic().getCalendar();
		// set report
//		report = new PerformanceChart(null, Helper.getLocale());
//		BufferedContentMgr.add(report);
		// set close operation - to remove report from memory
		getWorkpage().addLifecycleListener(
				new WorkpageDefaultLifecycleListener() {
					public void reactOnDestroyed() {
						super.reactOnDestroyed();
						BufferedContentMgr.remove(report);
					}
				});
		// set calendar to actual user
		setAthleteID(getServiceLayer().getDictionaryService().getMyPerson().getId());
		setSearchAthleteId(getAthleteID());
		// refresh UI
		refresh();
	}

	/**
	 * Load schedule entities from database
	 */
	private void update() {
		scheduleItems.clear();
		List<Schedules> schedules = getLogic().getScheduleLogic().getWeeksSchedule(getBeginOfWeek(), getAthleteID());
		if(Helper.isEmpty(schedules)) return;
		for(Schedules schedule : schedules) {
			scheduleItems.add(new ScheduleItem(schedule));
		}
	}

//	private void buildAthletesZones() {
//		vvbZones = getServiceLayer().getValueListBindingService().getVVBindingZones(getServiceLayer().getDictionaryService().getMyPerson().getId(), getAthleteID());
//	}

	private void refresh() {
		update();
		refreshAllSchedules();
//		buildAthletesZones();
	}

	private void refreshAllSchedules() {
		for (int i = 0; i < 7; i++)
			refreshSchedule(i);
	}

	private void refreshSchedule(int day) {
		switch (day) {
		case 0:
			refreshSchedule(m_day0, 0);
			break;
		case 1:
			refreshSchedule(m_day1, 1);
			break;
		case 2:
			refreshSchedule(m_day2, 2);
			break;
		case 3:
			refreshSchedule(m_day3, 3);
			break;
		case 4:
			refreshSchedule(m_day4, 4);
			break;
		case 5:
			refreshSchedule(m_day5, 5);
			break;
		case 6:
			refreshSchedule(m_day6, 6);
			break;
		}
	}

	private void refreshSchedule(SCHEDULEComponent schedule, int day) {
		if (schedule == null)
			return;
		schedule.getChildren().clear();
		int dayOfWeek = DAYSOFWEEK[day];
		int counter = -1;
		for (ScheduleItem item : getScheduleItems()) {
			counter++;
			String expressionBase = getWorkpage().getDispatcher()
					.getExpressionBase().replace("}", ".");
			String background = item.getColor();
			String foreground = Helper.getBlackOrWhite(background);
			// schedule at this day
			if (item.getStartWeekDay() != dayOfWeek)	continue;
			// schedule no template
			if (item.getTemplate()) continue;
			// build scheduleComponent
			SCHEDULEITEMComponentTag st = new SCHEDULEITEMComponentTag();
			st.setScheduleleft(item.getStartInMinutes() + "");
			st.setSchedulewidth(expressionBase + "ScheduleUI.scheduleItems["
					+ counter + "].duration}");
			st.setText("\n" + getLogic().getScheduleLogic().getSummary(item.schedule));
			String icon = (item.getDone()==null || item.getDone()==false) ?  Constants.ACCEPT_LIGHT : Constants.ACCEPT;
			st.setBgpaint("image(0,0,/images/icons/accept.png,lefttop);roundedborder(0,0,100%,100%,10,10," + background
					+ ",2);rectangle(0,0,100%,16," + background
					+ ");write(20,0," + item.getTypeDesc() + ",12," + foreground
					+ ",bold,lefttop);image(0,0," + icon + ",lefttop)");
			st.setBackground(item.getColor() + "60"); // Add Transparency
			st.setForeground(foreground);
			st.setPopupmenu("SCHEDULEITEM");
			st.setActionListener(expressionBase + "ScheduleUI.scheduleItems["
					+ counter + "].onScheduleItemAction}");
			st.setDragsend("schedule:" + item.getId());
			// st.setFlushonselect("true");
			st.setInvokeevent("doubleclick");
			schedule.getChildren().add(st.createBaseComponent());
		}
	}

	public String getActualDate() {
		return Helper.formatDate(new Date(), "EEEE, d. MMMM yyyy");
	}

	public String[] getDay() {
		String[] result = new String[7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginOfWeek());
		for (int i = 0; i < 7; i++) {
			result[i] = Constants.EMPTY + cal.get(Calendar.DAY_OF_MONTH)
					+ Constants.POINT + (cal.get(Calendar.MONTH) + 1)
					+ Constants.POINT;
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return result;
	}


	public void onNextWeek(ActionEvent event) {
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		refresh();
		animationTrigger.trigger();
		animationType = Constants.HIDETOLEFT;
	}

	public void onPreviousWeek(ActionEvent event) {
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		refresh();
		animationTrigger.trigger();
		animationType = Constants.HIDETORIGHT;
	}

	public void onMoveToday(ActionEvent event) {
		calendar = getLogic().getScheduleLogic().getCalendar();
		refresh();
	}

	public void onDateAction(ActionEvent ae) {
		if (ae instanceof BaseActionEventFlush) {
			if (date != null) {
				calendar.setTime(date);
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				refresh();
			}
		}
	}

	public void onDayInfo(ActionEvent ae) {
		// clientname contains the days offset from begin of week
		Integer days;
		try {
			days = Integer.valueOf((String)ae.getComponent().getAttributes().get(Constants.CLIENTNAME));
		} catch (Exception ex) {
			Statusbar.outputError(Helper.getMessages("day_not_found"), ex.toString());
			return;
		}

		Date date = getLogic().getScheduleLogic().addDaysToDate(getBeginOfWeek(), days);
		DayInfoPopUp dayInfoPopUp = getDayInfoPopUp();
		dayInfoPopUp.prepareCallback(
				new IPopUpCallback() {
					public void cancel() {
						m_popup.close();
						refresh();
					}
					public void ok(Object object) {}
				}, athleteID, date, getLogic().getScheduleLogic().getDayInfo(athleteID));
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.setUndecorated(true);
		m_popup.open(Constants.Page.DAYINFOPOPUP.getUrl(), Helper.getLiteral("day_info"),
				0, 0, ScheduleUI.this);
	}

	public class ScheduleProcessor implements Serializable {
		int day;

		public ScheduleProcessor(int day) {
			this.day = day;
		}

		public void onScheduleAction(ActionEvent event) {
			// creation of a new schedule through context menue
			if (event instanceof BaseActionEventPopupMenuItem) {
				BaseActionEventPopupMenuItem bae = (BaseActionEventPopupMenuItem) event;
				Timestamp from = getLogic().getScheduleLogic().calculateBeginDate(
						bae.getPercentageVerticalAsFloat(), SCHEDULEMAX,
						getBeginOfWeek(), day, STARTINGHOUR);
				// add to list of visible items
				Schedules schedule = getLogic().getScheduleLogic().createSchedule(from, athleteID);
				ScheduleItem scheduleItem = new ScheduleItem(schedule);
				scheduleItems.add(scheduleItem);
				refreshSchedule(day);
				// open in popup
				scheduleItem.openInPopup(false);
			} else if (event instanceof BaseActionEventDrop) {
				BaseActionEventDrop bae = (BaseActionEventDrop) event;
				Timestamp from = getLogic().getScheduleLogic().calculateBeginDate(
						bae.getPercentageVerticalAsFloat(), SCHEDULEMAX,
						getBeginOfWeek(), day, STARTINGHOUR);
				String dragInfo = bae.getDragInfo();
				if (dragInfo.startsWith("schedule:")) {
					String[] dragInfos = dragInfo.split(Constants.COLON);
					// new or existing schedule item
					if (dragInfos[1].startsWith("template")) {
						// add to list of visible items
						Schedules schedule = getLogic().getScheduleLogic().createScheduleFromTemplate(from, athleteID, dragInfos[2]);
						ScheduleItem scheduleItem = new ScheduleItem(schedule);
						scheduleItems.add(scheduleItem);
						refresh();
						// open in popup
						scheduleItem.openInPopup(false);
					} else {
						// check for intersection
						for(ScheduleItem item : scheduleItems) {
							// schedule at this day
							if(item.getStartWeekDay() != DAYSOFWEEK[day]) continue;
							if((from.after(item.getStart()) && from.before(item.getEnd())) || from.equals(item.getStart()) ) {
								Statusbar.outputAlert(Helper.getMessages("schedule_intersection"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
								return;
							}
						}
						String id = dragInfos[1];
						try {
							getLogic().getScheduleLogic().changeStart(id, from);
							refresh();
						} catch (Exception ex) {
							Statusbar.outputAlert(Helper.getMessages("start_change_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
						}
					}
				}
			}
		}
	}

	public class ScheduleItem {

		private Schedules schedule;

		// disable constructor
		private ScheduleItem() {}

		public ScheduleItem(Schedules schedule) {
			this.schedule = schedule;
		}

		public void saveSchedule() throws Exception {
			// set ids and sequence of details
			int sequence = 1;
			for(SchedulesDetail schedulesDetail : schedule.getSchedulesDetail()) {
				SchedulesDetailId id = new SchedulesDetailId(schedule.getId(), sequence++);
				schedulesDetail.setId(id);
			}
			schedule = getLogic().getScheduleLogic().saveSchedule(schedule);
		}

		public boolean deleteSchedule() throws Exception {
			return getLogic().getScheduleLogic().deleteSchedule(schedule.getId());
		}

		public void copySchedule() throws Exception {

		}

		public String getId() {return schedule.getId();}

		public String getTypeDesc() {
			return getServiceLayer().getTranslationService().getDescription(getType(), TranslationService.TYPE.SCHEDULETYPES);
		}

		public String getPersonId() {return schedule.getPersonId();}

		public String getPersonDesc() {
			Persons person =  getDaoLayer().getPersonsDAO().findById(getPersonId());
			return person==null?Constants.EMPTY:person.toString();
		}

		public String getCreatorId() {return schedule.getCreatedBy();}

		public Boolean getDone() { return schedule.getDone(); }
		public void setDone(Boolean done) { schedule.setDone(done); }

		public String getType() {return schedule.getType();}
		public void setType(String type) { schedule.setType(type); }

		public Timestamp getStart() {return schedule.getStart();}
		public void setStart(Timestamp start) {schedule.setStart(start);}

		public Timestamp getEnd() {return schedule.getEnd();}
		public void setEnd(Timestamp end) { schedule.setDuration(end.getTime() - getStart().getTime()); }

		public Long getDuration() { return schedule.getDuration(); }
		public void setDuration(Long duration) { schedule.setDuration(duration); }

		public String getColor() {return schedule.getColor();}
		public void setColor(String color) {schedule.setColor(color);}

		public String getDescription() {return schedule.getDescription();}
		public void setDescription(String description) {schedule.setDescription(description);}

		public String getComment() {return schedule.getComment();}
		public void setComment(String comment) {schedule.setComment(comment);}

		public String getTemplateName() {return schedule.getTemplateName();}
		public void setTemplateName(String templateName) { schedule.setTemplateName(templateName);}

		public List<SchedulesDetail> getSchedulesDetail() {return schedule.getSchedulesDetail();}
		public void setSchedulesDetail(List<SchedulesDetail> schedulesDetail) {schedule.setSchedulesDetail(schedulesDetail);}

		/**
		 * Build summary string for schedule detail in calendar view
		 * @return summary string
		 */
//		public String getSummary() {
//			StringBuffer sb = new StringBuffer();
//			if(ScheduleLogic.TYPES_WITH_DETAILS.keySet().contains(getType())) {
//				List<SchedulesDetail> schedulesDetails = schedule.getSchedulesDetail();
//				for(SchedulesDetail schedulesDetail : schedulesDetails) {
//					if(sb.length()>0) sb.append(Constants.NEWLINE);
//					ZonesDefinition definition = getDaoLayer().getZonesDefinitionDAO().findById(schedulesDetail.getZoneId());
//					if(definition==null) continue;
//					// special treatment for swim units
//					if(ScheduleLogic.getTypeOrd(getType())==ScheduleLogic.SWIM) {
//						sb.append(schedulesDetail.getUnit() + Constants.WHITESPACE + definition.getShortcut());
//					} else {
//						sb.append(schedulesDetail.getDurationTarget() + Constants.WHITESPACE + definition.getShortcut());
//					}
//
//				}
//			} else {
//				//sb.append(getDescription());
//			}
//			return sb.toString();
//		}

		public Boolean getTemplate() {return schedule.getTemplate();}
		public void setTemplate(Boolean template) {schedule.setTemplate(template);}

		public Boolean getTest() {return schedule.getTest();}
		public void setTest(Boolean test) {schedule.setTest(test);}

		public Boolean getDeleted() {return schedule.getDeleted();}
		public void setDeleted(Boolean deleted) {schedule.setDeleted(deleted);}

		public int getStartWeekDay() {
			return getLogic().getScheduleLogic().getWeekDay(getStart());
		}

		public long getStartInMinutes() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getStart());
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, STARTINGHOUR);
			Date startingHour = cal.getTime();
			return (getStart().getTime() - startingHour.getTime()) / 60000;
		}

		public void onScheduleItemAction(ActionEvent event) {
			if (event instanceof BaseActionEventPopupMenuItem) {
				BaseActionEventPopupMenuItem bae = (BaseActionEventPopupMenuItem) event;
				if (bae.getCommand().equals("cmdRemove")) {
					YESNOPopup.createInstance(Helper.getMessages("delete_data"),
		                    Helper.getMessages("confirm_delete"),
							new YESNOPopup.IYesNoListener() {
								public void reactOnNo() { return; }

								public void reactOnYes() {
									ScheduleItem scheduleItem;
									try {
										scheduleItem = (ScheduleItem) getClass().getDeclaredField("this$1").get(this);
										if(getLogic().getScheduleLogic().deleteSchedule(scheduleItem.getId())) {
											Statusbar.outputSuccess(Helper.getMessages("delete_success"));
											refresh();
										} else {
											Statusbar.outputAlert(Helper.getMessages("delete_failure"), Helper.getLiteral("error")).setLeftTopReferenceCentered();
										}
									} catch (Exception ex) {
										Statusbar.outputAlert(Helper.getMessages("delete_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
									}
								}
							});
				} else if (bae.getCommand().equals("cmdEdit")) {
					openInPopup(false);
				} else if (bae.getCommand().equals("cmdCopy")) {
					openCopyPopup();
				}
			} else if (event instanceof BaseActionEventScheduleSizeChanged) {
				// right size duration
				Long duration = getDuration();
				duration = ((duration + 7) / 15) * 15;
				try {
					getLogic().getScheduleLogic().changeDuration(getId(), duration);
					refresh();
				} catch (Exception ex) {
					Statusbar.outputAlert(Helper.getMessages("duration_change_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
				}
			} else if (event instanceof BaseActionEventFlush) {
				selectScheduleItem(this);
			} else if (event instanceof BaseActionEventInvoke) {	// schedule clicked
				openInPopup(false);
			}
		}

		private void openCopyPopup() {
			selectScheduleItem(this);
			ScheduleCopyPopUp scheduleCopyPopUp = getScheduleCopyPopUp();
			scheduleCopyPopUp.prepareCallback(new IPopUpCallback() {

				@Override
				public void ok(Object object) {
					m_popup.close();
					// open copied schedule
					if(object!=null) {
						ScheduleItem scheduleItem = new ScheduleItem((Schedules)object);
						scheduleItem.openInPopup(false);
					}
					refresh();
				}

				@Override
				public void cancel() {
					m_popup.close();
					refresh();
				}
			}, this);
			m_popup = getWorkpage().createModalPopupInWorkpageContext();
			m_popup.setLeftTopReferenceCentered();
			m_popup.setUndecorated(true);
			m_popup.open(Constants.Page.SCHEDULECOPYPOPUP.getUrl(), Helper.getLiteral("copy"), 0, 0, ScheduleUI.this);
		}


		private void openInPopup(boolean template) {
			selectScheduleItem(this);
			ScheduleChangePopUp scheduleChangePopUp = getScheduleChangePopUp();
			scheduleChangePopUp.setRenderButtons(true);
			scheduleChangePopUp.prepareCallback(
					new ScheduleChangePopUp.IScheduleChangePopupCallback() {
						public void cancel() {
							m_popup.close();
							refresh();
						}

						public void save() {
							try {
								saveSchedule();
								m_popup.close();
							} catch (Exception ex) {
								Statusbar.outputAlert(Helper.getMessages("save_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
							} finally {
								refresh();
							}
						}

						public void delete() {
							try {
								if(deleteSchedule()) {
									Statusbar.outputSuccess(Helper.getMessages("delete_success"));
								} else {
									Statusbar.outputAlert(Helper.getMessages("delete_failure"), Helper.getLiteral("error")).setLeftTopReferenceCentered();
								}
								m_popup.close();
								refresh();
							} catch (Exception ex) {
								Statusbar.outputAlert(Helper.getMessages("delete_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
							}
						}
					}, this);
			m_popup = getWorkpage().createModalPopupInWorkpageContext();
			m_popup.setLeftTopReferenceCentered();
			m_popup.setUndecorated(true);
			String title = Helper.getLiteral("schedule");
			if(template) title = Helper.getLiteral("template");
			m_popup.open(Constants.Page.SCHEDULECHANGEPOPUP.getUrl(), title, 1200, 800, ScheduleUI.this);
		}
	}

	private Map dayInfo = new DaysMap();
	public Map getDayInfo(){ return dayInfo;};

	private class DaysMap extends HashedMap {
		@Override
		public Object get(Object key) {
			// key represents days offset [0-6]
			if(!NumberUtils.isNumber((String)key)) return null;
			Integer day = Integer.valueOf((String)key);
			if(day<0 || day>6) return null;
			Date date = getLogic().getScheduleLogic().addDaysToDate(getBeginOfWeek(), day);
			DayInfos dayInfo = getLogic().getScheduleLogic().getDayInfos(getAthleteID(), date);
			return dayInfo.hashCode()==DayInfos.EMPTYHASH ? Constants.DAYINFO_FALSE : Constants.DAYINFO_TRUE;
		}

	}

	// ------------------------------------------------------------------------
	// logic for templates view
	// ------------------------------------------------------------------------

	protected ROWDYNAMICCONTENTBinding m_Templates = new ROWDYNAMICCONTENTBinding();
    public ROWDYNAMICCONTENTBinding getTemplates() { setTemplatesRowDynamic(); return m_Templates; }

    public void setTemplatesRowDynamic() {
    	StringBuffer xml = new StringBuffer();
    	// get all templates for logged in person
		List<Schedules> templates = getLogic().getScheduleLogic().getTemplates();
		xml.append("<t:pane>");
		for(Schedules template:templates) {
			// get inverted color for font
			Color background = Color.decode(template.getColor());
			String fontColor = Helper.getBlackOrWhite(background);

			xml.append("<t:row>");
			xml.append("<t:button clientname='" + template.getId() + "' align='center' actionListener='#{d.ScheduleUI.onHandleTemplates}' dragsend='schedule:template:" + template.getId() + "' contentareafilled='false' bgpaint='roundedrectangle(0,0,100%,100%,5,5," + template.getColor() + ")' stylevariant='WP_ISOLATEDWORKPAGE' foreground ='" + fontColor + "' font='size:10;weight:bold' text='"+ template.getTemplateName() +"' width = '140' />");
			xml.append("</t:row>");
			xml.append("<t:rowdistance />");
		}
		xml.append("</t:pane>");
		m_Templates.setContentXml(xml.toString());
	}

    public void onHandleTemplates(ActionEvent ae) {
    	if (!(ae.getSource() instanceof BUTTONComponent)) return;
    	// get id
     	BUTTONComponent button =(BUTTONComponent)ae.getSource();
    	String templateId = button.getAttributeValueAsString(Constants.CLIENTNAME);
    	// load template
    	Schedules template = getLogic().getScheduleLogic().getSchedule(templateId);
		ScheduleItem templateItem = new ScheduleItem(template);
		// open in popup
		templateItem.openInPopup(true);
    }

    public void onCreateTemplate(ActionEvent event) {
		// create a template
    	Schedules template = null;
    	if (event instanceof BaseActionEventDrop) {
    		BaseActionEventDrop bae = (BaseActionEventDrop) event;
			String dragInfo = bae.getDragInfo();
			if (dragInfo.startsWith("schedule:")) {
				String[] dragInfos = dragInfo.split(Constants.COLON);
				template = getLogic().getScheduleLogic().getSchedule(dragInfos[1]);
				if(template!=null) {
					// set new GUID to copy schedule!
					template.setId(UUID.randomUUID().toString());
					template.setPersonId(getServiceLayer().getDictionaryService().getMyPerson().getId());
					template.setTemplate(true);
				}
			}
    	}
    	// if no DND create new template
    	if(template==null) template = getLogic().getScheduleLogic().createTemplate(getServiceLayer().getDictionaryService().getMyPerson().getId());
		ScheduleItem templateItem = new ScheduleItem(template);
		// open in popup
		templateItem.openInPopup(true);
	}

    public void onCopySchedules(ActionEvent event) {
		ScheduleCopyPopUp scheduleCopyPopUp = getScheduleCopyPopUp();
		scheduleCopyPopUp.prepareCallback(new IPopUpCallback() {
			@Override
			public void ok(Object object) {
				m_popup.close();
				refresh();
			}

			@Override
			public void cancel() {
				m_popup.close();
				refresh();
			}
		}, getAthleteID());
		m_popup = getWorkpage().createModalPopupInWorkpageContext();
		m_popup.setLeftTopReferenceCentered();
		m_popup.setUndecorated(true);
		m_popup.open(Constants.Page.SCHEDULECOPYPOPUP.getUrl(), Helper.getLiteral("copy"), 0, 0, ScheduleUI.this);
    }

    public void onDeleteSchedules(ActionEvent event) {
    	YESNOPopup popup = YESNOPopup.createInstance(
				String.format(Helper.getMessages("confirm_delete_detail"), Helper.getLiteral("week") + Constants.WHITESPACE + getWeekNumber()),
                Helper.getMessages("confirm_delete"),
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {
						// TODO delete weeek
						boolean isDeleted = true;
						for(ScheduleItem scheduleItem : scheduleItems) {
							try {
								scheduleItem.deleteSchedule();
							} catch (Exception ex) {
								isDeleted = false;
							}
						}
						if(isDeleted) {
							Statusbar.outputSuccess(Helper.getMessages("delete_success"));
						} else {
							Statusbar.outputError(Helper.getMessages("delete_failure"));
						}
						refresh();
					}
				}
		);
		popup.getModalPopup().setLeftTopReferenceCentered();
    }

    public ScheduleItem createScheduleItem(String id) {
    	return new ScheduleItem(getLogic().getScheduleLogic().getSchedule(id));
    }

    // ------------------------------------------------------------------------
	// logic for agenda pane
	// ------------------------------------------------------------------------

    protected FIXGRIDListBinding<GridAgendaItem> m_gridAgenda = new FIXGRIDListBinding<GridAgendaItem>();
	public FIXGRIDListBinding<GridAgendaItem> getGridAgenda() {return m_gridAgenda;}
	public void setGridAgenda(FIXGRIDListBinding<GridAgendaItem> value) {m_gridAgenda = value;}

	public class GridAgendaItem extends FIXGRIDItem implements
			java.io.Serializable {
		private ScheduleItem scheduleItem;

		public ScheduleItem getScheduleItem() {
			return scheduleItem;
		}

		public void setScheduleItem(ScheduleItem scheduleItem) {
			this.scheduleItem = scheduleItem;
		}

		public GridAgendaItem(ScheduleItem scheduleItem) {
			this.scheduleItem = scheduleItem;
		}

		@Override
		public void onRowExecute() {
			scheduleItem.openInPopup(false);
		}
	}

	/* Selection criterias */
	private String searchAthleteId = null;
	public String getSearchAthleteId() {return searchAthleteId;}
	public void setSearchAthleteId(String searchAthleteId) {this.searchAthleteId = searchAthleteId;}

	private Date fromDate = null;
	public Date getFromDate() {return fromDate;}
	public void setFromDate(Date fromDate) {this.fromDate = fromDate;}

	private Date toDate = null;
	public Date getToDate() {return toDate;}
	public void setToDate(Date toDate) {this.toDate = toDate;}

	public void onClearAgendaSearch(ActionEvent ae) {
		searchAthleteId = null;
		fromDate = null;
		toDate = null;
		// clear resultset
		getGridAgenda().getItems().clear();
	}

	public void onAgendaSearch(ActionEvent ae) {
		getGridAgenda().getItems().clear();
		List<Schedules> schedules = getLogic().getScheduleLogic().getSchedulesByQuery(getSearchAthleteId(), getFromDate(), getToDate());
		if(isEmpty(schedules)) {
			Statusbar.outputAlert(Helper.getMessages("no_entry_found"), Helper.getLiteral("info")).setLeftTopReferenceCentered();
			return;
		}
		for(Schedules schedule : schedules) {
			getGridAgenda().getItems().add(new GridAgendaItem(new ScheduleItem(schedule)));
		}
	}

	// ------------------------------------------------------------------------
	// logic for reporting
	// ------------------------------------------------------------------------
	private Trigger downloadTrigger = new Trigger();
	public Trigger getDownloadTrigger() {return downloadTrigger;}

	public void onPrintReport(ActionEvent ae) {
        // Print report
        if(report!=null) BufferedContentMgr.remove(report);
        report = new CalendarOverview(getServiceLayer(), getDaoLayer(), Helper.getLocale(), getWeekNumber(), getDay(), getScheduleItems());
        if(report!=null) BufferedContentMgr.add(report);
        downloadTrigger.trigger();
    }

	// ------------------------------------------------------------------------
	// logic for mobile
	// ------------------------------------------------------------------------
	private Trigger browserTrigger = new Trigger();
	public Trigger getBrowserTrigger() { return browserTrigger; }

	private String mobileUrl;
	public String getMobileUrl() { return mobileUrl; }

	public void onMobile(ActionEvent ae) {
		mobileUrl = Helper.getTrimatrixUrl() + "/zul/schedule.zul?id=" + getAthleteID();
		browserTrigger.trigger();
	}
}
