package trimatrix.zul;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.web.servlet.xel.RequestContexts;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import trimatrix.db.DAOLayer;
import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.Schedules;
import trimatrix.db.SchedulesDetail;
import trimatrix.db.ZonesDefinition;
import trimatrix.entities.IEntityData;
import trimatrix.entities.ScheduleEntity;
import trimatrix.services.ServiceLayer;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public class ScheduleZUL extends GenericAutowireComposer {
	private static final String ID = "id";
	private static final String DONE = "done";
	private static enum TYPES {
		RUN, BIKE, SWIM
	}

	// get context for Spring DI
	private static ApplicationContext ctx;
	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	private DAOLayer daoLayer = DAOLayer.getFromApplicationContext(ctx);
	private ServiceLayer serviceLayer = ServiceLayer.getFromApplicationContext(ctx);

	private Locale locale;
	private ResourceBundle literals;
	private ResourceBundle messages;

    private Datebox dateBox;
    private Radiogroup units;
    private Grid grid;
    private ListModelList lmList;
    private Button btnSave;

    private Persons athlete;
    private Schedules selectedSchedule;
    private Date scheduleDate = new Date();
    private List<IEntityData> schedules;

    @Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// cast comp to window
		Window window = (Window)comp;
		// get locale from request for I18N
		locale = RequestContexts.getCurrent().getRequest().getLocale();
		literals = ResourceBundle.getBundle("trimatrix.resources.literals", locale);
		messages = ResourceBundle.getBundle("trimatrix.resources.messages", locale);
		// check if id is passed
		if(param.containsKey(ID)) {
			String[] ids = (String[])param.get(ID);
			if(ids!=null && ids.length==1) {
				// check if ID is a valid athlete ID
				athlete = daoLayer.getPersonsDAO().findById(ids[0]);
				// check if person is athlete too, if not reset athlete
				if(athlete!=null) {
					PersonsAthlete athleteProfile = athlete.getProfileAthlete();
					if(athleteProfile==null) athlete = null;
				}
			}
		}
		// when no athlete set leave app
		if(athlete==null) {
			//Executions.forward("/zul/timeout.zul");
			Clients.evalJavaScript("alert(" + messages.getString("wrong_user_id") + ")");
			window.onClose();
			return;
		}
		// set title
		window.setTitle(literals.getString("training_units") + Constants.WHITESPACE + athlete.toString());
		// get schedules for today
		dateBox.setValue(scheduleDate);
		// get schedules
		refreshSchedules();
	}

	private void buildGrid(final TYPES type, List<SchedulesDetail> scheduleData) {
		// build header
		Columns columns = grid.getColumns();
		if (columns.getChildren() == null) return;
		columns.detach();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("columns", createHeader(type));
		grid.appendChild(Executions.createComponents("columns.zul", grid, params));
		// items
		grid.setRowRenderer(new RowRenderer() {
			public void render(Row row, Object data) throws Exception {
				if (data == null) return;
				SchedulesDetail detail = (SchedulesDetail)data;
				switch (type) {
				case RUN:
					new Label(detail.getDurationTarget()).setParent(row);
					ZonesDefinition zonesDefinitionRun = daoLayer.getZonesDefinitionDAO().findById(detail.getZoneId());
					new Label(zonesDefinitionRun.getShortcut()).setParent(row);
			        new Label(detail.getHrLow()+"-"+detail.getHrHigh()).setParent(row);
			        final Textbox durationAthleteRun = new Textbox(detail.getDurationActual());
			        durationAthleteRun.addEventListener(Events.ON_CHANGE, new EventListener(){
			        	   public void onEvent(Event arg0) throws Exception{
			        		   durationAthleteRun.setValue(Helper.correctTimeInput(durationAthleteRun.getValue()));
			        		}
			        });
			        durationAthleteRun.setParent(row);
			        if(detail.getHrAvg()==null) {
			        	new Intbox().setParent(row);
			        } else {
			        	new Intbox(detail.getHrAvg()).setParent(row);
			        }
					break;
				case BIKE:
					new Label(detail.getDurationTarget()).setParent(row);
					ZonesDefinition zonesDefinitionBike = daoLayer.getZonesDefinitionDAO().findById(detail.getZoneId());
					new Label(zonesDefinitionBike.getShortcut()).setParent(row);
			        new Label(detail.getHrLow()+"-"+detail.getHrHigh()).setParent(row);
			        new Label(detail.getPower()==null?Constants.EMPTY:detail.getPower().toString()).setParent(row);
			        new Label(detail.getCadence()==null?Constants.EMPTY:detail.getCadence().toString()).setParent(row);
			        final Textbox durationAthleteBike = new Textbox(detail.getDurationActual());
			        durationAthleteBike.addEventListener(Events.ON_CHANGE, new EventListener(){
			        	   public void onEvent(Event arg0) throws Exception{
			        		   durationAthleteBike.setValue(Helper.correctTimeInput(durationAthleteBike.getValue()));
			        		}
			        });
			        durationAthleteBike.setParent(row);
			        if(detail.getHrAvg()==null) {
			        	new Intbox().setParent(row);
			        } else {
			        	new Intbox(detail.getHrAvg()).setParent(row);
			        }
					break;
				case SWIM:
					new Label(detail.getUnit()).setParent(row);
					new Label(detail.getDistance()==null?Constants.EMPTY:detail.getDistance().toString()).setParent(row);
					ZonesDefinition zonesDefinitionSwim = daoLayer.getZonesDefinitionDAO().findById(detail.getZoneId());
					new Label(zonesDefinitionSwim.getShortcut()).setParent(row);
			        new Label(detail.getTimeLow()+"-"+detail.getTimeHigh()).setParent(row);
			        final Textbox timeAvgSwim = new Textbox(detail.getTimeAvg());
			        timeAvgSwim.addEventListener(Events.ON_CHANGE, new EventListener(){
			        	   public void onEvent(Event arg0) throws Exception{
			        		   timeAvgSwim.setValue(Helper.correctTimeInput2(timeAvgSwim.getValue()));
			        		}
			        });
			        timeAvgSwim.setParent(row);
					break;
				default:
					break;
				}
			}
		});
		lmList = new ListModelList(scheduleData);
		grid.setModel(lmList);
		grid.setVisible(true);
	}

	public void onPrevious(Event event) {
		scheduleDate = addDaysToDate(scheduleDate , -1);
		refreshSchedules();
		dateBox.setValue(scheduleDate);
    }

	public void onNext(Event event) {
		scheduleDate = addDaysToDate(scheduleDate , 1);
		refreshSchedules();
		dateBox.setValue(scheduleDate);
    }

	public void onDate(Event event) {
		scheduleDate = dateBox.getValue();
		refreshSchedules();
    }

	public void onSave(Event event) {
		if(selectedSchedule==null) return;
		int selectedUnit = units.getSelectedIndex();
		List<SchedulesDetail> schedulesDetails = selectedSchedule.getSchedulesDetail();
		// set attributes
		Long duration = 0L;
		TYPES type = null;
        try {
        	type = TYPES.valueOf(selectedSchedule.getType().toUpperCase());
        } catch (Exception ex) {}  // ignore exception, because type is null checked
        if(type!=null) {
        	for(int index = 0; index<schedulesDetails.size();index++) {
    			SchedulesDetail schedulesDetail = schedulesDetails.get(index);
    			Row row = (Row)grid.getRows().getChildren().get(index);
    			switch (type) {
				case RUN:
					String athleteDurationRun = ((Textbox)row.getChildren().get(3)).getValue();
	    			if(Helper.isEmpty(athleteDurationRun)) {
	    				duration += Helper.calculateSeconds(schedulesDetail.getDurationTarget());
	    			} else {
	    				duration += Helper.calculateSeconds(athleteDurationRun);
	    			}
	    			schedulesDetail.setDurationActual(athleteDurationRun);
	    			Integer hrAvgAthleteRun = ((Intbox)row.getChildren().get(4)).getValue();
	    			schedulesDetail.setHrAvg(hrAvgAthleteRun);
					break;
				case BIKE:
					String athleteDurationBike = ((Textbox)row.getChildren().get(5)).getValue();
	    			if(Helper.isEmpty(athleteDurationBike)) {
	    				duration += Helper.calculateSeconds(schedulesDetail.getDurationTarget());
	    			} else {
	    				duration += Helper.calculateSeconds(athleteDurationBike);
	    			}
	    			schedulesDetail.setDurationActual(athleteDurationBike);
	    			Integer hrAvgAthleteBike = ((Intbox)row.getChildren().get(6)).getValue();
	    			schedulesDetail.setHrAvg(hrAvgAthleteBike);
					break;
				case SWIM:
					String timeAvgSwim = ((Textbox)row.getChildren().get(4)).getValue();
					schedulesDetail.setTimeAvg(timeAvgSwim);
					break;
				default:
					break;
				}
    		}
    		if(duration>0L) selectedSchedule.setDuration(duration/60);	//duration is in seconds
    		selectedSchedule.setSchedulesDetail(schedulesDetails);
        }
		// load schedule
		try {
			selectedSchedule.setDone(true);
			daoLayer.getSchedulesDAO().merge(selectedSchedule);
			// message success
			try {
				Messagebox.show(messages.getString("save_success"),literals.getString("info"), Messagebox.OK, Messagebox.INFORMATION);
			} catch (Exception ex) {ex.printStackTrace();}
			// refresh schedules
			refreshSchedules();
			// set to actual schedule
			units.setSelectedIndex(selectedUnit);
			onChangeUnit(null);
		} catch (Exception ex) {
			// message error
			try {
				Messagebox.show(messages.getString("save_failure"),literals.getString("error"), Messagebox.OK, Messagebox.ERROR);
			} catch (Exception ex2) {ex2.printStackTrace();}
		}
    }

	public void onChangeUnit(Event event) {
        Radio selected = units.getSelectedItem();
        Boolean done = (Boolean)selected.getAttribute(DONE);
        selectedSchedule = daoLayer.getSchedulesDAO().findById(selected.getValue());
        // load grid
        grid.setVisible(false);
        List<SchedulesDetail> schedulesDetails = selectedSchedule.getSchedulesDetail();
        TYPES type = null;
        try {
        	type = TYPES.valueOf(selectedSchedule.getType().toUpperCase());
        } catch (Exception ex) {}  // ignore exception, because type is null checked
        if(type!=null && schedulesDetails.size()>0) buildGrid(type, schedulesDetails);
        // set right label for save button
        if(done) {
        	btnSave.setLabel(literals.getString("change"));
        } else {
        	btnSave.setLabel(literals.getString("finish"));
        }
        // when done and no details don't show button
        if(schedulesDetails.size()==0 && done) {
        	btnSave.setVisible(false);
        } else {
        	btnSave.setVisible(true);
        }
    }

	private Date addDaysToDate(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, day);
		return cal.getTime();
	}

	private void refreshSchedules() {
		schedules = serviceLayer.getSqlExecutorService().getScheduleEntities(locale.getLanguage(), null, athlete.getId(), getStart(), getEnd(), false, false);
		selectedSchedule = null;
		if(Helper.isEmpty(schedules)) {
			grid.setVisible(false);
			btnSave.setVisible(false);
		} else {
			grid.setVisible(true);
			btnSave.setVisible(true);
		}
		buildUnits();
	}

	private void buildUnits() {
		// reset radiogroup
		units.getChildren().clear();
		if(Helper.isEmpty(schedules)) return;
		for(IEntityData data : schedules) {
			ScheduleEntity.Data schedule = (ScheduleEntity.Data)data;
			Boolean done = schedule.getDone()==null ? false : schedule.getDone();
			Radio unit = new Radio();
			unit.setValue(schedule.getId());
			unit.setLabel(schedule.getType());
			unit.setAttribute(DONE, done);
			if(done) {
				unit.setImage(Constants.ACCEPT);
			} else {
				unit.setImage(Constants.ACCEPT_LIGHT);
			}
			units.getChildren().add(unit);
		}
		// select first item
		units.setSelectedIndex(0);
		onChangeUnit(null);
	}

	private Timestamp getStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(scheduleDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}

	private Timestamp getEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(scheduleDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return new Timestamp(cal.getTimeInMillis());
	}

	private List<String> createHeader(TYPES type) {
		switch (type) {
		case RUN:
			return Arrays.asList(new String[]{literals.getString("duration"), literals.getString("zone"), literals.getString("hr"), literals.getString("duration") + " (" + literals.getString("debit") + ")", literals.getString("hr") + " (" + literals.getString("debit") + ")"});
		case BIKE:
			return Arrays.asList(new String[]{literals.getString("duration"), literals.getString("zone"), literals.getString("hr"), literals.getString("power"), literals.getString("tf"), literals.getString("duration") + " (" + literals.getString("debit") + ")", literals.getString("hr") + " (" + literals.getString("debit") + ")"});
		case SWIM:
			return Arrays.asList(new String[]{literals.getString("unit"), "Distanz (m)", literals.getString("zone"), "~" + literals.getString("target_time"), "~" + literals.getString("target_time") + " (" + literals.getString("debit") + ")"});
		}
		return Collections.EMPTY_LIST;
	}
}