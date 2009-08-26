package trimatrix.ui;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.PropertyUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.springframework.metadata.commons.CommonsAttributes;

import com.sun.mail.imap.protocol.Status;

import trimatrix.db.Doctors;
import trimatrix.db.Persons;
import trimatrix.db.Tests;
import trimatrix.db.TestsErgo;
import trimatrix.db.TestsProtocol;
import trimatrix.db.TestsSwim;
import trimatrix.db.TestsTreadmill;
import trimatrix.entities.TestEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@CCGenClass (expressionBase="#{d.TestDetailUI}")

public class TestDetailUI extends AEntityDetailUI implements Serializable
{
    protected FIXGRIDListBinding<GridTreadmillItem> m_gridTreadmill = new FIXGRIDListBinding<GridTreadmillItem>();
    public FIXGRIDListBinding<GridTreadmillItem> getGridTreadmill() { return m_gridTreadmill; }
    public void setGridTreadmill(FIXGRIDListBinding<GridTreadmillItem> value) { m_gridTreadmill = value; }

    public class GridTreadmillItem extends FIXGRIDItem implements java.io.Serializable
    {
    }

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
		TestsErgo ergo = entity.getTestsErgo();
		if(ergo!=null) {
			values.put(TestEntity.POWER_INIT, ergo.getPowerInit());
			values.put(TestEntity.POWER_STEP, ergo.getPowerStep());
			values.put(TestEntity.CADENCE_LOW, ergo.getCadenceLow());
			values.put(TestEntity.CADENCE_HIGH, ergo.getCadenceHigh());
			values.put(TestEntity.ERGO_STEP_TIME, ergo.getStepTime());
		}
		
		// treadmill		
		TestsTreadmill treadmill = entity.getTestsTreadmill();
		if(treadmill!=null) {
			values.put(TestEntity.SPEED_VARIABLE, treadmill.getSpeedVariable());
			values.put(TestEntity.SPEED_INIT, treadmill.getSpeedInit());
			values.put(TestEntity.SPEED_STEP, treadmill.getSpeedStep());
			values.put(TestEntity.INCLINE_VARIABLE, treadmill.getInclineVariable());
			values.put(TestEntity.INCLINE_INIT, treadmill.getInclineInit());
			values.put(TestEntity.INCLINE_STEP, treadmill.getInclineStep());
			values.put(TestEntity.TREADMILL_STEP_TIME, treadmill.getStepTime());
		} else {
			// reset checkboxes for gui update
			values.put(TestEntity.INCLINE_VARIABLE, false);
			values.put(TestEntity.SPEED_VARIABLE, false);
		}
		
		// swim
		TestsSwim swim = entity.getTestsSwim();
		if(swim!=null) {
			values.put(TestEntity.DATE2, swim.getDate2());
			values.put(TestEntity.ASSISTANT_NAME, swim.getAssistantName());
			values.put(TestEntity.BATHS, swim.getBaths());
			values.put(TestEntity.POOL, swim.getPool());
			values.put(TestEntity.DISTANCE, swim.getDistance());
			values.put(TestEntity.SPLITS, swim.getSplits());
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
	
	public boolean isTypeEnabled() {
		// type just enabled as long as no protocol is created
		if(!isProtocol() && super.m_enabled) return true;
		return false;
	}
	
	public boolean isProtocol() {
		if(entity.getTestsProtocol()!=null) return true;
		return false;
	}
	
	public void onProtocolCreate(ActionEvent event) {
		// create protocol
		TestsProtocol protocol = new TestsProtocol(entity.getId());
		getDaoLayer().getTestsProtocolDAO().save(protocol);		
		entity.setTestsProtocol(protocol);
    }
	
	@Override
	public boolean isCopyable() {
		// just admins and coaches are able to copy tests
		if(getServiceLayer().getDictionaryService().getMyRoles().contains(Constants.Role.ADMIN.getName()) ||
		   getServiceLayer().getDictionaryService().getMyRoles().contains(Constants.Role.COACH.getName())) return true;
		return false;
	}
	
	@Override
	public String copyEntity() {	
		// create and clone entity
		// TODO move to entity class
		Tests test = new Tests();
		try {
			PropertyUtils.copyProperties(test, entity);
			// TODO copy also specific test data
			// set new ID
			String newId = UUID.randomUUID().toString();
			test.setId(newId);
			// set actual user as coach, normally just coaches are able to create tests
		    entity.setCoach(getServiceLayer().getDictionaryService().getMyPerson());
			// set back created by and at property => is set in save method of entity
		    ENTITYLISTLOGIC.save(Constants.Entity.TEST, test);
			return test.getId();
		} catch (Exception ex) {
			Statusbar.outputAlert(ex.toString(), "Error occured");
			return null;
		}		
	}
	
}
