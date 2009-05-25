package trimatrix.ui;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Doctors;
import trimatrix.entities.DoctorEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.DoctorDetailUI}")

public class DoctorDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{
	private Doctors entity;	
    
	public DoctorDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] {DoctorEntity.NAME});
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
    }

    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Doctors)entityObject;        	 	
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
		if(!Dictionary.isEmailValid((String)values.get(DoctorEntity.EMAIL))) {
			throw new EmailNotValidException((String)values.get(values.get(DoctorEntity.EMAIL)));
		}	
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		// name
		entity.setName((String)values.get(DoctorEntity.NAME));
		// TODO set all fields
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(DoctorEntity.NAME, entity.getName());
				
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}	
}