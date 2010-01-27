package trimatrix.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.elements.events.BaseActionEventDrop;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.events.BaseActionEventInvoke;
import org.eclnt.jsfserver.elements.events.BaseActionEventPopupMenuItem;
import org.eclnt.jsfserver.elements.events.BaseActionEventScheduleSizeChanged;
import org.eclnt.jsfserver.elements.impl.SCHEDULEComponent;
import org.eclnt.jsfserver.elements.impl.SCHEDULEITEMComponentTag;
import org.eclnt.jsfserver.elements.util.Trigger;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass(expressionBase = "#{d.ScheduleUI}")
public class ScheduleUI extends MyWorkpageDispatchedBean implements Serializable {
    public void onCopySchedules(ActionEvent event) {}

    public void onCreateTemplate(ActionEvent event) {}

	protected final int DURATION = 3600000; // in ms
	protected final int STARTINGHOUR = 6;
	
	protected final int NUMBEROFBLOCKS = 24;

	public int getNumberOfBlocks() {
		return NUMBEROFBLOCKS;
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
	
	ScheduleUI scheduleUI = this;

	List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();	
	public List<ScheduleItem> getScheduleItems() {return scheduleItems;}

	ScheduleItem selectedScheduleItem = null;
	private void selectScheduleItem(ScheduleItem item) {selectedScheduleItem = item;}
    
	int[] DAYSOFWEEK = new int[] { Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY };

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

	public ScheduleUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		calendar = getLogic().getScheduleLogic().getCalendar();
		refresh();
	}

	private void refresh() {
		refreshAllSchedules();
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
		if (schedule == null) return;
		schedule.getChildren().clear();
		int dayOfWeek = DAYSOFWEEK[day];
		int counter = -1;
		for (ScheduleItem item : scheduleItems) {
			counter++;			
			String expressionBase = getWorkpage().getDispatcher().getExpressionBase().replace("}", ".");
			if (item.getFromWeekDay() != dayOfWeek)	continue;
			SCHEDULEITEMComponentTag st = new SCHEDULEITEMComponentTag();
			st.setScheduleleft(item.getFromInMinutes() + "");
			st.setSchedulewidth(expressionBase + "ScheduleUI.scheduleItems[" + counter + "].durationInMinutes}");
			st.setText(item.getText());
			//st.setBgpaint(ResourceManager.getRuntimeInstance().readProperty("constants", "itemSchedule"));
			st.setBgpaint("roundedborder(0,0,100%,100%,10,10,#000000,2)");
			// rectangle(0,0,100,20,#FF0000);rectangle(0,20,100,100,#FF000020);write(5,0,9.00 - 10.00,lefttop);write(5,20,Beschreibung,lefttop)
			//st.setBorder("#C0C0C0");
			st.setBackground(item.getColor());
			st.setForeground(Helper.getBlackOrWhite(item.getColor()));
			st.setPopupmenu("SCHEDULEITEM");
			st.setActionListener(expressionBase + "ScheduleUI.scheduleItems[" + counter + "].onScheduleItemAction}");
			st.setDragsend("schedule:" + counter);
			// st.setFlushonselect("true");
			st.setInvokeevent("doubleclick");
			schedule.getChildren().add(st.createBaseComponent());
		}
	}
	
	public String getActualDate() {
		return new SimpleDateFormat("EEEE, d. MMMM yyyy").format(new Date());
	}

	public String[] getDayInfos() {
		String[] result = new String[7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginOfWeek());
		for (int i = 0; i < 7; i++) {
			result[i] = Constants.EMPTY + cal.get(Calendar.DAY_OF_MONTH) + Constants.POINT + (cal.get(Calendar.MONTH) + 1) + Constants.POINT;
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
			}
		}
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
				Date from = getLogic().getScheduleLogic().calculateBeginDate(bae.getPercentageVerticalAsFloat(), SCHEDULEMAX, getBeginOfWeek(), day, STARTINGHOUR);
				Date to = new Date(from.getTime() + DURATION);
				// add to list of visible items
                ScheduleItem scheduleItem = new ScheduleItem(from, to, Constants.BLUE, "Neuer Termin"); 
                scheduleItems.add(scheduleItem);
                refreshSchedule(day);
                // open in popup
                scheduleItem.openInPopup();
			} else if (event instanceof BaseActionEventDrop) {
				BaseActionEventDrop bae = (BaseActionEventDrop)event;
                Date from = getLogic().getScheduleLogic().calculateBeginDate(bae.getPercentageVerticalAsFloat(), SCHEDULEMAX, getBeginOfWeek(), day, STARTINGHOUR);
                String dragInfo = bae.getDragInfo();
                if (dragInfo.startsWith("schedule:")) {
                	String[] dragInfos = dragInfo.split(":");
                	// new or existing schedule item
                	if(dragInfos[1].equalsIgnoreCase("new")) {
                		Date to = new Date(from.getTime() + DURATION);
        				// add to list of visible items
                        ScheduleItem scheduleItem = new ScheduleItem(from, to, Constants.BLUE, "Neuer Termin"); 
                        scheduleItems.add(scheduleItem);
                        refreshSchedule(day);
                        // open in popup
                        scheduleItem.openInPopup();
                	} else {
                		int index = new Integer(bae.getDragInfo().substring("schedule:".length())).intValue();
                        ScheduleItem scheduleItem = scheduleItems.get(index);
                        long duration = scheduleItem.getDuration();
                        scheduleItem.setFrom(from);
                        scheduleItem.setTo(new Date(from.getTime()+duration));
                        refresh();
                	}                    
                }
			}
		}
	}

	public class ScheduleItem {
		Date from;
		public Date getFrom() { return from; }
		public void setFrom(Date from) { this.from = from; }
		
		Date to;
		public Date getTo() { return to; }
		public void setTo(Date to) { this.to = to; }
		
		String color;
		public String getColor() {return color;}
		public void setColor(String color) {this.color = color;}

		String text;
		public String getText() {return text;}
		public void setText(String text) {this.text = text;}

		public ScheduleItem(Date from, Date to, String color, String text) {
			this.from = from;
			this.to = to;
			this.color = color;
			this.text = text;
		}

		public int getFromWeekDay() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(from);
			return cal.get(Calendar.DAY_OF_WEEK);
		}

		public long getFromInMinutes() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(from);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR_OF_DAY, STARTINGHOUR);
			Date startingHour = cal.getTime();
			return (from.getTime() - startingHour.getTime()) / 60000;
		}

		public long getDurationInMinutes() {
			return getDuration() / 60000;
		}
		
		public long getDuration() {
			return to.getTime() - from.getTime();
		}

		public void setDurationInMinutes(long value) {
			to = new Date(from.getTime() + value * 60000);
		}

		public void onScheduleItemAction(ActionEvent event) {
			if (event instanceof BaseActionEventPopupMenuItem) {
				BaseActionEventPopupMenuItem bae = (BaseActionEventPopupMenuItem) event;
				if (bae.getCommand().equals("cmdRemove")) {
					YESNOPopup.createInstance("Removing Schedule Item", "Do you really want to remove the selected item?", new YESNOPopup.IYesNoListener() {
						public void reactOnNo() {
						}

						public void reactOnYes() {
							Statusbar.outputMessage("Item removed!");							
							try {
								ScheduleItem this_ = (ScheduleItem)getClass().getDeclaredField("this$1").get(this);
								scheduleItems.remove(this_);
								refreshAllSchedules();
							} catch (Exception ex) {
								Statusbar.outputError("Error removing schedule item!", ex.toString());
							} 							
						}
					});
				}
			} else if (event instanceof BaseActionEventScheduleSizeChanged) {
				// right size duration
				long duration = getDurationInMinutes();
				duration = ((duration + 7) / 15) * 15;
				setDurationInMinutes(duration);
				// TODO Update entity
			} else if (event instanceof BaseActionEventFlush) {
				selectScheduleItem(this);
			} else if (event instanceof BaseActionEventInvoke) {
				openInPopup();
			}
		}

		private void openInPopup() {
			selectScheduleItem(this);
			ScheduleChangePopUp scheduleChangePopUp = getScheduleChangePopUp();
			scheduleChangePopUp.prepareCallback(new ScheduleChangePopUp.IPopupCallback(){
				public void cancel() {
					m_popup.close();	
					refresh();
				}					
			}, this);
			m_popup = getWorkpage().createModalPopupInWorkpageContext();    
	       	m_popup.setLeftTopReferenceCentered();
	       	m_popup.setUndecorated(true);
	       	m_popup.open(Constants.Page.SCHEDULECHANGEPOPUP.getUrl(), "Termin", 400, 300, scheduleUI);			
		}		
	}
}
