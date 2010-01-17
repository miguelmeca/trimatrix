package trimatrix.ui;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.events.BaseActionEventDrop;
import org.eclnt.jsfserver.elements.events.BaseActionEventPopupMenuItem;
import org.eclnt.jsfserver.elements.impl.SCHEDULEComponent;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@CCGenClass(expressionBase = "#{d.ScheduleUI}")
public class ScheduleUI extends MyWorkpageDispatchedBean implements Serializable {
    protected int m_weekNumber;
    public int getWeekNumber() { return m_weekNumber; }
    public void setWeekNumber(int value) { m_weekNumber = value; }

	Date m_beginOfWeek;
	Calendar calendar;
	
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
	
	public void setDay0(SCHEDULEComponent value) { if (m_day0 == value) return; m_day0 = value; updateSchedule(0); }
    public void setDay1(SCHEDULEComponent value) { if (m_day1 == value) return; m_day1 = value; updateSchedule(1); }
    public void setDay2(SCHEDULEComponent value) { if (m_day2 == value) return; m_day2 = value; updateSchedule(2); }
    public void setDay3(SCHEDULEComponent value) { if (m_day3 == value) return; m_day3 = value; updateSchedule(3); }
    public void setDay4(SCHEDULEComponent value) { if (m_day4 == value) return; m_day4 = value; updateSchedule(4); }
    public void setDay5(SCHEDULEComponent value) { if (m_day5 == value) return; m_day5 = value; updateSchedule(5); }
    public void setDay6(SCHEDULEComponent value) { if (m_day6 == value) return; m_day6 = value; updateSchedule(6); }
	
	public ScheduleProcessor getSp0() { return m_day0Processor; }
    public ScheduleProcessor getSp1() { return m_day1Processor; }
    public ScheduleProcessor getSp2() { return m_day2Processor; }
    public ScheduleProcessor getSp3() { return m_day3Processor; }
    public ScheduleProcessor getSp4() { return m_day4Processor; }
    public ScheduleProcessor getSp5() { return m_day5Processor; }
    public ScheduleProcessor getSp6() { return m_day6Processor; }

	public ScheduleUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		calendar = getLogic().getScheduleLogic().getCalendar();
		m_beginOfWeek = calendar.getTime();
		m_weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
		refresh();
	}
	
	private void refresh() {
		
	}
	
	private void updateSchedule(int day)
    {
        switch (day) 
        {
            case 0: updateSchedule(m_day0,0); break;
            case 1: updateSchedule(m_day1,1); break;
            case 2: updateSchedule(m_day2,2); break;
            case 3: updateSchedule(m_day3,3); break;
            case 4: updateSchedule(m_day4,4); break;
            case 5: updateSchedule(m_day5,5); break;
            case 6: updateSchedule(m_day6,6); break;
        }
    }
	
	 private void updateSchedule(SCHEDULEComponent schedule, int day)
	    {
	        if (schedule == null)
	            return;
	        schedule.getChildren().clear();
	        int dayOfWeek = DAYSOFWEEK[day];
	        int counter = -1;
//	        for (WrappedCalendarItem item: m_items) 
//	        {
//	            counter++;
//	            if (item.getCalendarWeekDay() != dayOfWeek) continue;
//	            SCHEDULEITEMComponentTag st = new SCHEDULEITEMComponentTag();
//	            st.setScheduleleft(item.getFromInMinutes()+"");
////	            st.setSchedulewidth(item.getDurationInMinutes()+"");
//	            st.setSchedulewidth("#{d.ScheduleUI.items["+counter+"].durationInMinutes}");
//	            st.setText(item.i_calendarItem.getName());
//	            st.setBgpaint(ResourceManager.getRuntimeInstance().readProperty("constants","itemSchedule"));
//	            st.setBorder("#C0C0C0");
//	            st.setPopupmenu("SCHEDULEITEM");
//	            st.setActionListener("#{d.ScheduleUI.items["+counter+"].onScheduleItemAction}");
//	            st.setDragsend("schedule:"+counter);
////	            st.setFlushonselect("true");
//	            st.setInvokeevent("doubleclick");
//	            schedule.getChildren().add(st.createBaseComponent());
//	        }
	    }

	public String[] getDayInfos() {
		String[] result = new String[7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(m_beginOfWeek);
		for (int i = 0; i < 7; i++) {
			result[i] = Constants.EMPTY + cal.get(Calendar.DAY_OF_MONTH) + Constants.POINT + (cal.get(Calendar.MONTH) + 1) + Constants.POINT;
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return result;
	}

	public class ScheduleProcessor implements Serializable {
		int day;

		public ScheduleProcessor(int day) {
			this.day = day;
		}

		public void onScheduleAction(ActionEvent event) {
			if (event instanceof BaseActionEventPopupMenuItem) {

			} else if (event instanceof BaseActionEventDrop) {

			}
		}
	}
}
