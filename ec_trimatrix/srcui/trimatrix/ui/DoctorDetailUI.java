package trimatrix.ui;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

import trimatrix.db.Doctors;
import trimatrix.db.Users;
import trimatrix.entities.DoctorEntity;
import trimatrix.entities.UserEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.DoctorDetailUI}")

public class DoctorDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{
	protected ValidValuesBinding countriesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.COUNTRY);
    public ValidValuesBinding getCountriesVvb() { return countriesVvb; }
	
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
		
		// email check
		ClassValidator<Doctors> validator = new ClassValidator<Doctors>(Doctors.class);
		String email = (String)values.get(DoctorEntity.EMAIL);
		InvalidValue[] invalidValues = validator.getPotentialInvalidValues(DoctorEntity.EMAIL, email);
		if(invalidValues.length>0) {
			throw new EmailNotValidException((String)values.get(values.get(DoctorEntity.EMAIL)));
		}
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		// name
		entity.setName((String)values.get(DoctorEntity.NAME));
		// address
		entity.setStreet((String)values.get(DoctorEntity.STREET));
		entity.setHousenumber((String)values.get(DoctorEntity.HOUSENUMBER));
		entity.setPostcode((String)values.get(DoctorEntity.POSTCODE));
		entity.setCity((String)values.get(DoctorEntity.CITY));
		entity.setState((String)values.get(DoctorEntity.STATE));
		entity.setCountryKey((String)values.get(DoctorEntity.COUNTRY));
		// communication
		entity.setHomepage((String)values.get(DoctorEntity.HOMEPAGE));
		entity.setEmail((String)values.get(DoctorEntity.EMAIL));
		entity.setTelephone((String)values.get(DoctorEntity.TELEPHONE));
		entity.setMobile((String)values.get(DoctorEntity.MOBILE));
		entity.setFax((String)values.get(DoctorEntity.FAX));
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(DoctorEntity.NAME, entity.getName());
		values.put(DoctorEntity.EMAIL, entity.getEmail());	
		values.put(DoctorEntity.STREET, entity.getStreet());
		values.put(DoctorEntity.HOUSENUMBER, entity.getHousenumber());
		values.put(DoctorEntity.POSTCODE, entity.getPostcode());
		values.put(DoctorEntity.CITY, entity.getCity());
		values.put(DoctorEntity.STATE, entity.getState());
		values.put(DoctorEntity.COUNTRY, entity.getCountryKey());
		values.put(DoctorEntity.HOMEPAGE, entity.getHomepage());
		values.put(DoctorEntity.TELEPHONE, entity.getTelephone());
		values.put(DoctorEntity.MOBILE, entity.getMobile());
		values.put(DoctorEntity.FAX, entity.getFax());				
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}	
}