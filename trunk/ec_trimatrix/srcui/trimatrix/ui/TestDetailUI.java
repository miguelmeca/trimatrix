package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Doctors;
import trimatrix.db.Persons;
import trimatrix.db.Tests;
import trimatrix.db.TestsErgo;
import trimatrix.db.TestsSwim;
import trimatrix.db.TestsTreadmill;
import trimatrix.entities.TestEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.TestDetailUI}")

public class TestDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{
    public void onDoctorSearch(ActionEvent event) {
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.DOCTOR);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Doctors doctor = (Doctors)ENTITYLISTLOGIC.get(Constants.Entity.DOCTOR, id);
				entity.setDoctor(doctor);
				setDoctorDescription(entity);	
				m_popup.close();
			}});    	
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();    
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.DOCTORSELECTION.getUrl(), Helper.getLiteral("doctor_search"), 800, 600, this);
    }

    public void onAthleteSearch(ActionEvent event) {
    	IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Persons person = (Persons)ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				entity.setAthlete(person);
				setAthleteDescription(entity);	
				m_popup.close();
			}});    	
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();    
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), Helper.getLiteral("athlete_search"), 800, 600, this);
    }
    
    public void onTypeChange(ActionEvent event) {
    	entity.setType((String)values.get(TestEntity.TYPE));
    }
    
	private Tests entity;	
	
	public TestDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {TestEntity.PERSON, TestEntity.TYPE, TestEntity.DATE}, true);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
        // labeling
        setLabelRowDynamic();
	}
	
	protected ValidValuesBinding m_testTypesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.TESTTYPE);
    public ValidValuesBinding getTestTypesVvb() { return m_testTypesVvb; }
	
    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Tests)entityObject;        	 	
    	// set enabled state and set fields
    	init();		
	}
	
    public void init() {
    	// set fields
    	fillMaps();   
    	// set state
    	setState();		
	}
	
    @Override
	public void prepareSave() {
		// ergo
    	if(entity.getTestsErgo()==null && isErgo()) {
			// create ergo
			TestsErgo ergo = new TestsErgo(entity.getId());
			getDaoLayer().getTestsErgoDAO().save(ergo);		
			entity.setTestsErgo(ergo);
			// delete treadmill
			if(entity.getTestsTreadmill()!=null) {
				getDaoLayer().getTestsTreadmillDAO().delete(entity.getTestsTreadmill()); 
				entity.setTestsTreadmill(null);
			}
			// delete swim
			if(entity.getTestsSwim()!=null) {
				getDaoLayer().getTestsSwimDAO().delete(entity.getTestsSwim()); 
				entity.setTestsSwim(null);
			}			
			return;
		} 
    	// treadmill
    	if(entity.getTestsTreadmill()==null && isTreadmill()) {
			// create treadmill
			TestsTreadmill treadmill = new TestsTreadmill(entity.getId());
			getDaoLayer().getTestsTreadmillDAO().save(treadmill);		
			entity.setTestsTreadmill(treadmill);
			// delete ergo
			if(entity.getTestsErgo()!=null) {
				getDaoLayer().getTestsErgoDAO().delete(entity.getTestsErgo()); 
				entity.setTestsErgo(null);
			}
			// delete swim
			if(entity.getTestsSwim()!=null) {
				getDaoLayer().getTestsSwimDAO().delete(entity.getTestsSwim()); 
				entity.setTestsSwim(null);
			}			
			return;
		} 
    	// swim
    	if(entity.getTestsSwim()==null && isSwim()) {
			// create swim
			TestsSwim swim = new TestsSwim(entity.getId());
			getDaoLayer().getTestsSwimDAO().save(swim);		
			entity.setTestsSwim(swim);
			// delete treadmill
			if(entity.getTestsTreadmill()!=null) {
				getDaoLayer().getTestsTreadmillDAO().delete(entity.getTestsTreadmill()); 
				entity.setTestsTreadmill(null);
			}
			// delete ergo
			if(entity.getTestsErgo()!=null) {
				getDaoLayer().getTestsErgoDAO().delete(entity.getTestsErgo()); 
				entity.setTestsErgo(null);
			}			
			return;
		} 
	}

	public void validate() throws MandatoryCheckException,
			EmailNotValidException {
    	// mandatory check
		checkMandatory();
		// fill values to entities properties
		fillEntityProperties();		
	}
    
    private void fillEntityProperties() {
		// detail
		entity.setDescription((String)values.get(TestEntity.DESCRIPTION));
		Timestamp timestamp = null;
		Date date = (Date)values.get(TestEntity.DATE);
		if (date!=null) {
			timestamp = new Timestamp(date.getTime());
		} 
		entity.setDate(timestamp);
		entity.setType((String)values.get(TestEntity.TYPE));
		setAthleteDescription(entity);
		setDoctorDescription(entity);
		// ergo		
		if(isErgo()) {
			TestsErgo ergo = entity.getTestsErgo();
			ergo.setPowerInit((Integer)values.get(TestEntity.POWER_INIT));
			ergo.setPowerStep((Integer)values.get(TestEntity.POWER_STEP));
			ergo.setCadenceLow((Integer)values.get(TestEntity.CADENCE_LOW));
			ergo.setCadenceHigh((Integer)values.get(TestEntity.CADENCE_HIGH));
			ergo.setStepTime((String)values.get(TestEntity.ERGO_STEP_TIME));			
		}
		// treadmill
		if(isTreadmill()) {
			TestsTreadmill treadmill = entity.getTestsTreadmill();
			treadmill.setSpeedVariable((Boolean)values.get(TestEntity.SPEED_VARIABLE));
			treadmill.setSpeedInit((Double)values.get(TestEntity.SPEED_INIT));
			treadmill.setSpeedStep((Double)values.get(TestEntity.SPEED_STEP));
			treadmill.setInclineVariable((Boolean)values.get(TestEntity.INCLINE_VARIABLE));
			treadmill.setInclineInit((Integer)values.get(TestEntity.INCLINE_INIT));
			treadmill.setInclineStep((Integer)values.get(TestEntity.INCLINE_STEP));
		}
		// swim
		if(isSwim()) {
			TestsSwim swim = entity.getTestsSwim();
			swim.setAssistantName((String)values.get(TestEntity.ASSISTANT_NAME));
			swim.setBaths((String)values.get(TestEntity.BATHS));
			swim.setPool((String)values.get(TestEntity.POOL));
			Timestamp timestamp2 = null;
			Date date2 = (Date)values.get(TestEntity.DATE2);
			if (date2!=null) {
				timestamp2 = new Timestamp(date2.getTime());
			} 
			swim.setDate2(timestamp2);
			swim.setDistance((Integer)values.get(TestEntity.DISTANCE));
			swim.setSplits((Integer)values.get(TestEntity.SPLITS));
		}
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(TestEntity.DESCRIPTION, entity.getDescription());
		Date date = null;
		Timestamp timestamp = entity.getDate();
		if (timestamp!=null) {
			date =  new Date(timestamp.getTime());
		} 
		values.put(TestEntity.DATE, date);
		values.put(TestEntity.TYPE, entity.getType());
		setAthleteDescription(entity);	
		setDoctorDescription(entity);	
		
		// ergo
		if(isErgo()) {
			values.put(TestEntity.POWER_INIT, entity.getTestsErgo().getPowerInit());
			values.put(TestEntity.POWER_STEP, entity.getTestsErgo().getPowerStep());
			values.put(TestEntity.CADENCE_LOW, entity.getTestsErgo().getCadenceLow());
			values.put(TestEntity.CADENCE_HIGH, entity.getTestsErgo().getCadenceHigh());
			values.put(TestEntity.ERGO_STEP_TIME, entity.getTestsErgo().getStepTime());
		}
		
		// treadmill
		if(isTreadmill()) {
			values.put(TestEntity.SPEED_VARIABLE, entity.getTestsTreadmill().getSpeedVariable());
			values.put(TestEntity.SPEED_INIT, entity.getTestsTreadmill().getSpeedInit());
			values.put(TestEntity.SPEED_STEP, entity.getTestsTreadmill().getSpeedStep());
			values.put(TestEntity.INCLINE_VARIABLE, entity.getTestsTreadmill().getInclineVariable());
			values.put(TestEntity.INCLINE_INIT, entity.getTestsTreadmill().getInclineInit());
			values.put(TestEntity.INCLINE_STEP, entity.getTestsTreadmill().getInclineStep());
			values.put(TestEntity.ERGO_STEP_TIME, entity.getTestsTreadmill().getStepTime());
		}
		
		// swim
		if(isSwim()) {
			values.put(TestEntity.DATE2, entity.getTestsSwim().getDate2());
			values.put(TestEntity.ASSISTANT_NAME, entity.getTestsSwim().getAssistantName());
			values.put(TestEntity.BATHS, entity.getTestsSwim().getBaths());
			values.put(TestEntity.POOL, entity.getTestsSwim().getPool());
			values.put(TestEntity.DISTANCE, entity.getTestsSwim().getDistance());
			values.put(TestEntity.SPLITS, entity.getTestsSwim().getSplits());
		}		
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	private void setAthleteDescription(Tests test) {
		Persons athlete = test.getAthlete();
		String athleteDescription = Constants.EMPTY;
		if (athlete!=null) {
			athleteDescription = athlete.toString();
		}
		values.put(TestEntity.PERSON, athleteDescription);
	}
	
	private void setDoctorDescription(Tests test) {
		Doctors doctor = test.getDoctor();
		String doctorDescription = Constants.EMPTY;
		if (doctor!=null) {
			doctorDescription = doctor.toString();
		}
		values.put(TestEntity.DOCTOR, doctorDescription);
	}
	
	public boolean isErgo() {
		if (entity.getType()==null || !entity.getType().equals("ergo")) return false;		
		return true;
	}
	
	public boolean isTreadmill() {
		if (entity.getType()==null || !entity.getType().equals("treadmill")) return false;
		return true;
	}
	
	public boolean isSwim() {
		if (entity.getType()==null || !entity.getType().equals("swim")) return false;
		return true;
	}

}
