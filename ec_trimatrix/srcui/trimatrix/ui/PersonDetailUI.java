package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.entities.PersonEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.PersonDetailUI}")

public class PersonDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{
	private final static String BORDER = "#808080";
	
	protected ValidValuesBinding salutationsVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.SALUTATION);
    public ValidValuesBinding getSalutationsVvb() { return salutationsVvb; }
	
    protected ValidValuesBinding countriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COUNTRY);
    public ValidValuesBinding getCountriesVvb() { return countriesVvb; }
    
    public String getBorder() {
    	if(entity.getPicture() == null || entity.getPicture().length == 0) {
    		return BORDER;
    	}
    	return Constants.EMPTY;
    }
    
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
		// personal
		entity.setSalutationKey((String)values.get(PersonEntity.SALUTATION));
		entity.setNameFirst((String)values.get(PersonEntity.NAME_FIRST));
		entity.setNameLast((String)values.get(PersonEntity.NAME_LAST));
		Timestamp birthdate = null;
		Date date = (Date)values.get(PersonEntity.BIRTHDATE);
		if (date!=null) {
			birthdate = new Timestamp(date.getTime());
		} 
		entity.setBirthdate(birthdate);
		// address
		entity.setStreet((String)values.get(PersonEntity.STREET));
		entity.setHousenumber((String)values.get(PersonEntity.HOUSENUMBER));
		entity.setPostcode((String)values.get(PersonEntity.POSTCODE));
		entity.setCity((String)values.get(PersonEntity.CITY));
		entity.setState((String)values.get(PersonEntity.STATE));
		entity.setCountryKey((String)values.get(PersonEntity.COUNTRY));
		// communication
		entity.setHomepage((String)values.get(PersonEntity.HOMEPAGE));
		entity.setEmail((String)values.get(PersonEntity.EMAIL));
		entity.setTelephone((String)values.get(PersonEntity.TELEPHONE));
		entity.setMobile((String)values.get(PersonEntity.MOBILE));
		entity.setFax((String)values.get(PersonEntity.FAX));
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(PersonEntity.SALUTATION, entity.getSalutationKey());
		values.put(PersonEntity.NAME_FIRST, entity.getNameFirst());
		values.put(PersonEntity.NAME_LAST, entity.getNameLast());
		values.put(PersonEntity.EMAIL, entity.getEmail());	
		Date birthdate = null;
		Timestamp timestamp = entity.getBirthdate();
		if (timestamp!=null) {
			birthdate =  new Date(timestamp.getTime());
		} 
		values.put(PersonEntity.BIRTHDATE, birthdate);
		values.put(PersonEntity.STREET, entity.getStreet());
		values.put(PersonEntity.HOUSENUMBER, entity.getHousenumber());
		values.put(PersonEntity.POSTCODE, entity.getPostcode());
		values.put(PersonEntity.CITY, entity.getCity());
		values.put(PersonEntity.STATE, entity.getState());
		values.put(PersonEntity.COUNTRY, entity.getCountryKey());
		values.put(PersonEntity.HOMEPAGE, entity.getHomepage());
		values.put(PersonEntity.TELEPHONE, entity.getTelephone());
		values.put(PersonEntity.MOBILE, entity.getMobile());
		values.put(PersonEntity.FAX, entity.getFax());
		
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