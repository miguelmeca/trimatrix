package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventClientHttpReceive;
import org.eclnt.jsfserver.elements.util.Trigger;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

import trimatrix.db.Doctors;
import trimatrix.entities.DoctorEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.DoctorDetailUI}")
public class DoctorDetailUI extends AEntityDetailUI implements Serializable {
	
	// Google Maps logic
	protected String m_url = "/html/empty.html";
	public String getUrl() {return m_url;}
	public void setUrl(String value) {m_url = value;}

	public void onMapSearch(ActionEvent event) {
		String searchString = (String)values.get(DoctorEntity.POSTCODE) + " " + 
				              (String)values.get(DoctorEntity.CITY) + " " + 
				              (String)values.get(DoctorEntity.STREET) + " " +
				              (String)values.get(DoctorEntity.HOUSENUMBER) + " " +
				              ((String)values.get(DoctorEntity.COUNTRY)).toUpperCase();	
		String longitude = entity.getLongitude()!=null ? entity.getLongitude().toString() : Constants.EMPTY;
		String latitude = entity.getLatitude()!=null ? entity.getLatitude().toString() : Constants.EMPTY;
		String name = (String)values.get(DoctorEntity.NAME);
		m_url = "/html/googlewrapper.html?param=(http://localhost:50055," + searchString.trim() + "," + latitude + "," + longitude + "," + name +")";
	}
	
	public void onClientReceive(ActionEvent event) {
		if (event instanceof BaseActionEventClientHttpReceive) {
			String query = ((BaseActionEventClientHttpReceive) event).getQueryString();
			if(Helper.isEmpty(query)) return;
			// truncate first slash and last bracket
			String[] lat_lng = query.substring(1, query.length()-1).split(":");
			if(lat_lng.length!=2) return;
			try {
				entity.setLatitude(Double.valueOf(lat_lng[0]));
				entity.setLongitude(Double.valueOf(lat_lng[1]));
			} catch (Exception ex) {
				Statusbar.outputError(ex.toString());
			}
		}
	}

	// Mail logic
	protected Trigger sendTrigger = new Trigger();
	public Trigger getSendTrigger() {return sendTrigger;}
	public void onMailSend(ActionEvent event) {sendTrigger.trigger();}

	// URL logic
	protected Trigger browserTrigger = new Trigger();
	public Trigger getBrowserTrigger() {return browserTrigger;}
	public void onShowUrl(ActionEvent event) {browserTrigger.trigger();}
	

	private Doctors entity;

	public DoctorDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher, new String[] { DoctorEntity.NAME });
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();
		entityDetailUI.setEntityDetailUI(this);
		// init data
		init(entityDetailUI.getEntityObject());
	}

	public void init(Object entityObject) {
		// set entity object
		this.entity = (Doctors) entityObject;
		// set enabled state and set fields
		init();
	}

	public void init() {
		// set fields
		fillMaps();
		// load GMap
		onMapSearch(null);
		// set state
		setState();
	}

	public void validate() throws MandatoryCheckException, EmailNotValidException {
		// mandatory check
		checkMandatory();
		// email check

		// email check
		ClassValidator<Doctors> validator = new ClassValidator<Doctors>(Doctors.class);
		String email = (String) values.get(DoctorEntity.EMAIL);
		InvalidValue[] invalidValues = validator.getPotentialInvalidValues(DoctorEntity.EMAIL, email);
		if (invalidValues.length > 0) {
			throw new EmailNotValidException((String) values.get(values.get(DoctorEntity.EMAIL)));
		}
		// fill values to entities properties
		fillEntityProperties();
	}

	private void fillEntityProperties() {
		// name
		entity.setName((String) values.get(DoctorEntity.NAME));
		// address
		entity.setStreet((String) values.get(DoctorEntity.STREET));
		entity.setHousenumber((String) values.get(DoctorEntity.HOUSENUMBER));
		entity.setPostcode((String) values.get(DoctorEntity.POSTCODE));
		entity.setCity((String) values.get(DoctorEntity.CITY));
		entity.setState((String) values.get(DoctorEntity.STATE));
		entity.setCountryKey((String) values.get(DoctorEntity.COUNTRY));
		// communication
		entity.setHomepage((String) values.get(DoctorEntity.HOMEPAGE));
		entity.setEmail((String) values.get(DoctorEntity.EMAIL));
		entity.setTelephone((String) values.get(DoctorEntity.TELEPHONE));
		entity.setMobile((String) values.get(DoctorEntity.MOBILE));
		entity.setFax((String) values.get(DoctorEntity.FAX));
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
		for (String field : MANDATORY_FIELDS) {
			bgpaint.put(field, Constants.BGP_MANDATORY);
		}
	}
}