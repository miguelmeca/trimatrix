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
import trimatrix.logic.LogicLayer;
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
	private LogicLayer logicLayer = LogicLayer.getFromApplicationContext(ctx);

	private Locale locale;

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
			Clients.evalJavaScript("alert('Wrong ID!')");
			window.onClose();
			return;
		}
		// set title
		window.setTitle("Trainingseinheit " + athlete.toString());
		// get schedules for today
		dateBox.setValue(scheduleDate);
		// get schedules
		refreshSchedules();
	}

	private void buildGrid(final TYPES type, List scheduleData) {
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
				switch (type) {
				case RUN:
					SchedulesDetail run = (SchedulesDetail)data;
					new Label(run.getDurationTarget()).setParent(row);
					ZonesDefinition zonesDefinition = daoLayer.getZonesDefinitionDAO().findById(run.getZoneId());
					new Label(zonesDefinition.getShortcut()).setParent(row);
			        new Label(run.getHrLow()+"-"+run.getHrHigh()).setParent(row);
			        final Textbox durationAthlete = new Textbox(run.getDurationActual());
			        durationAthlete.addEventListener(Events.ON_CHANGE, new EventListener(){
			        	   public void onEvent(Event arg0) throws Exception{
			        		   durationAthlete.setValue(Helper.correctTimeInput(durationAthlete.getValue()));
			        		}
			        });
			        durationAthlete.setParent(row);
			        if(run.getHrAvg()==null) {
			        	new Intbox().setParent(row);
			        } else {
			        	new Intbox(run.getHrAvg()).setParent(row);
			        }
					break;
				}
			}
		});
		lmList = new ListModelList(scheduleData);
		grid.setModel(lmList);
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

	public void onSave(Event event) {
		if(selectedSchedule==null) return;
		int selectedUnit = units.getSelectedIndex();
		List<SchedulesDetail> schedulesDetails = selectedSchedule.getSchedulesDetail();
		// set attributes
		Long duration = 0L;
		for(int index = 0; index<schedulesDetails.size();index++) {
			SchedulesDetail schedulesDetail = schedulesDetails.get(index);
			Row row = (Row)grid.getRows().getChildren().get(index);
			String athleteDuration = ((Textbox)row.getChildren().get(3)).getValue();
			if(Helper.isEmpty(athleteDuration)) {
				duration += Helper.calculateSeconds(schedulesDetail.getDurationTarget());
			} else {
				duration += Helper.calculateSeconds(athleteDuration);
			}
			schedulesDetail.setDurationActual(athleteDuration);
			Integer hrAvgAthlete = ((Intbox)row.getChildren().get(4)).getValue();
			schedulesDetail.setHrAvg(hrAvgAthlete);
		}
		selectedSchedule.setDuration(duration/60);	//duration is in seconds
		selectedSchedule.setSchedulesDetail(schedulesDetails);
		// load schedule
		try {
			selectedSchedule.setDone(true);
			daoLayer.getSchedulesDAO().merge(selectedSchedule);
			// message success
			try {
				Messagebox.show("Einheit erfolgreich gespeichert!","Info", Messagebox.OK, Messagebox.INFORMATION);
			} catch (Exception ex) {ex.printStackTrace();}
			// refresh schedules
			refreshSchedules();
			// set to actual schedule
			units.setSelectedIndex(selectedUnit);
			onChangeUnit(null);
		} catch (Exception ex) {
			// message error
			try {
				Messagebox.show("Fehler beim Speichern der Einheit aufgetreten!","Fehler", Messagebox.OK, Messagebox.ERROR);
			} catch (Exception ex2) {ex2.printStackTrace();}
		}
    }

	public void onChangeUnit(Event event) {
        Radio selected = units.getSelectedItem();
        Boolean done = (Boolean)selected.getAttribute(DONE);
        selectedSchedule = daoLayer.getSchedulesDAO().findById(selected.getValue());
        // load grid
        List<SchedulesDetail> schedulesDetails = selectedSchedule.getSchedulesDetail();
        buildGrid(TYPES.RUN, schedulesDetails);
        // set right label for save button
        if(done) {
        	btnSave.setLabel("Ändern");
        } else {
        	btnSave.setLabel("Abschliessen");
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
			return Arrays.asList(new String[]{"Dauer", "Zone", "HF", "Dauer (Ist)", "HF (Ist)"});
		}
		return Collections.EMPTY_LIST;
	}
}