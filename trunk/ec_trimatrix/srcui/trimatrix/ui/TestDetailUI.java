package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Doctors;
import trimatrix.db.Persons;
import trimatrix.db.Tests;
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
		super(dispatcher, new String[] {TestEntity.PERSON, TestEntity.TYPE, TestEntity.DOCTOR, TestEntity.DATE}, true);
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
		Statusbar.outputMessage("Type: " + entity.getType());
		if (entity.getType()==null || !entity.getType().equals("ergo")) return false;		
		return true;
	}
	
	public boolean isTreadmill() {
		Statusbar.outputMessage("Type: " + entity.getType());
		if (entity.getType()==null || !entity.getType().equals("treadmill")) return false;
		return true;
	}

}
