package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.UserEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.PersonDetailUI}")

public class PersonDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{
	private Persons entity;	
    
	public PersonDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {PersonEntity.NAME_LAST});
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
    }

    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Persons)entityObject;        	 	
    	// set enabled state and set fields
    	init();
    }
    
    public void init() {
    	// set fields
    	fillMaps();   
    	// set state
    	setState();
    }    

	public void validate() throws MandatoryCheckException, EmailNotValidException {		
		// mandatory check
		checkMandatory();
        // email check
		if(!Dictionary.isEmailValid((String)values.get(PersonEntity.EMAIL))) {
			throw new EmailNotValidException((String)values.get(values.get(PersonEntity.EMAIL)));
		}	
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		// first name
		entity.setNameFirst((String)values.get(PersonEntity.NAME_FIRST));
		// last name 
		entity.setNameLast((String)values.get(PersonEntity.NAME_LAST));
		// email
		entity.setEmail((String)values.get(PersonEntity.EMAIL));
		// birthdate	
		Timestamp birthdate = null;
		Date date = (Date)values.get(PersonEntity.BIRTHDATE);
		if (date!=null) {
			birthdate = new Timestamp(date.getTime());
		} 
		entity.setBirthdate(birthdate);
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(PersonEntity.NAME_FIRST, entity.getNameFirst());
		values.put(PersonEntity.NAME_LAST, entity.getNameLast());
		values.put(PersonEntity.EMAIL, entity.getEmail());	
		Date birthdate = null;
		Timestamp timestamp = entity.getBirthdate();
		if (timestamp!=null) {
			birthdate =  new Date(timestamp.getTime());
		} 
		values.put(PersonEntity.BIRTHDATE, birthdate);
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	public String getPicture() {
		try {
			return Dictionary.getHexString(entity.getPicture());
		} catch (Exception ex) {
			return Constants.EMPTY;
		}		
	}
	
	 public void onUploadImage(ActionEvent event) {
		 if (event instanceof BaseActionEventUpload) {
			 BaseActionEventUpload bae = (BaseActionEventUpload)event;
			 entity.setPicture(bae.getHexBytes());
		 }		 
	 }	
	 
	 public void onRemoveImage(ActionEvent event) {
		 entity.setPicture(null);
	 }	
}