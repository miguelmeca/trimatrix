package trimatrix.ui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.events.BaseActionEventUpload;
import org.eclnt.jsfserver.elements.util.Trigger;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

import trimatrix.db.Persons;
import trimatrix.db.PersonsAthlete;
import trimatrix.db.PersonsHaveRelations;
import trimatrix.entities.PersonEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.relations.IRelationObject;
import trimatrix.utils.Constants;
import trimatrix.utils.Constants.Entity;
import trimatrix.utils.Constants.Relation;
import trimatrix.utils.Constants.Role;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.PersonDetailUI}")

public class PersonDetailUI extends AEntityDetailUI implements Serializable
{
	protected Trigger sendTrigger = new Trigger();
    public Trigger getSendTrigger() { return sendTrigger; }

    public void onMailSend(ActionEvent event) {
    	sendTrigger.trigger();
    }

    protected Trigger browserTrigger = new Trigger();
    public Trigger getBrowserTrigger() { return browserTrigger; }

    public void onShowUrl(ActionEvent event) {
    	browserTrigger.trigger();
    }

	// #{d.WorkplaceUI.renderAdmin}
	private final static String BORDER = "#808080";

    private boolean athlete;
    public boolean isAthlete() { return athlete; }
    public void setAthlete(boolean value) { athlete = value; };

    public boolean isAdmin() {
    	return getServiceLayer().getDictionaryService().getMyRoles().contains(Constants.Role.ADMIN.getName());
    }

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
    	setProfiles();
    	// set fields
    	fillMaps();
    	// set state
    	setState();
    }

    private void setProfiles() {
    	athlete = false;
    	if(entity.getProfileAthlete()!=null) athlete = true;
    }

	@Override
	public void prepareSave() {
		// In case of a coach relation the athlete should also be created with athlete profile
		if(getEntityDetailUI().getEntity()==Entity.MYATHLETES) {
			athlete = true;
		}
		// case  => profil not set yet => create
		if(entity.getProfileAthlete()==null && athlete) {
			// TODO put into logic layer
			PersonsAthlete athlete = new PersonsAthlete(entity.getId());
			getDaoLayer().getPersonAthleteDAO().save(athlete);
			entity.setProfileAthlete(athlete);
			return;
		}
		// case 2 => profil already set => delete
		if(entity.getProfileAthlete()!=null && !athlete) {
			// TODO put into logic layer
			PersonsAthlete athlete = entity.getProfileAthlete();
			getDaoLayer().getPersonAthleteDAO().delete(athlete);
			entity.setProfileAthlete(null);
			return;
		}
	}

	@Override
	public void postSave() {
		/**
		* Special handling for athletes created by a scout or coach.
		* In those cases the relation should be created additionally.
		**/
		if(mode==Constants.Mode.NEW || mode==Constants.Mode.COPY) {
			String relationType = null;
			Entity entity = getEntityDetailUI().getEntity();
			switch (entity) {
			case MYATHLETES:
				relationType = Constants.Relation.COACH.type();
				break;
			case MYSCOUTEDATHLETES:
				relationType = Constants.Relation.SCOUT.type();
				break;
			default:
				return; // entity not relevant => abort
			}
			// entity relevant
			// TODO put into logic layer
			IRelationObject relationObject = getLogic().getRelationListLogic().create(Constants.Relation.PERSONPERSON);
			relationObject.setPartner1(this.entity.getId());	// Athlete
			relationObject.setPartner2(getServiceLayer().getDictionaryService().getMyPerson().getId());
			relationObject.setReltypKey(relationType);
			getLogic().getRelationListLogic().save(Constants.Relation.PERSONPERSON, relationObject);
			// update relevant function tree
			if(entity==Constants.Entity.MYATHLETES) reloadFunctionTree(Role.COACH);
			if(entity==Constants.Entity.MYSCOUTEDATHLETES) reloadFunctionTree(Role.SCOUTER);
		}
	}

	@Override
	public void postDelete(boolean isDeleted) {
		// if person is deleted, the relation is already deleted
		if(!isDeleted) {
			String relationType = null;
			Entity entity = getEntityDetailUI().getEntity();
			switch (getEntityDetailUI().getEntity()) {
			case MYATHLETES:
				relationType = Constants.Relation.COACH.type();
				break;
			case MYSCOUTEDATHLETES:
				relationType = Constants.Relation.SCOUT.type();
				break;
			default:
				return; // entity not relevant => abort
			}
			// TODO put into logic layer
			PersonsHaveRelations phr = new PersonsHaveRelations();
			phr.setPartner1(this.entity.getId());
			phr.setPartner2(getServiceLayer().getDictionaryService().getMyPerson().getId());
			phr.setReltypKey(relationType);
			List<PersonsHaveRelations> relations = getDaoLayer().getPersonsHaveRelationsDAO().findByExample(phr);
			if(relations!=null && relations.size()>0) {
				getLogic().getRelationListLogic().delete(Relation.COACH, relations.get(0).getId());
			}
			// update relevant function tree
			if(entity==Constants.Entity.MYATHLETES) reloadFunctionTree(Role.COACH);
			if(entity==Constants.Entity.MYSCOUTEDATHLETES) reloadFunctionTree(Role.SCOUTER);
		}
	}

	public void validate() throws MandatoryCheckException, EmailNotValidException {
		// mandatory check
		checkMandatory();
        // email check
		ClassValidator<Persons> validator = new ClassValidator<Persons>(Persons.class);
		String email = (String)values.get(PersonEntity.EMAIL);
		InvalidValue[] invalidValues = validator.getPotentialInvalidValues(PersonEntity.EMAIL, email);
		if(invalidValues.length>0) {
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
		// athlete profil
		PersonsAthlete athlete = entity.getProfileAthlete();
		if(athlete!=null) {
			athlete.setHeight((Double)values.get(PersonEntity.HEIGHT));
			athlete.setHeightUnit((String)values.get(PersonEntity.HEIGHT_UNIT));
			athlete.setWeight((Double)values.get(PersonEntity.WEIGHT));
			athlete.setWeightUnit((String)values.get(PersonEntity.WEIGHT_UNIT));
			athlete.setMaxHr((Integer)values.get(PersonEntity.MAX_HR));
			athlete.setRestingHr((Integer)values.get(PersonEntity.RESTING_HR));

//			String value = (String)values.get(PersonEntity.VO2_MAX);
//			athlete.setVo2Max(Integer.valueOf(value));

			athlete.setVo2Max((Integer)values.get(PersonEntity.VO2_MAX));
		}
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

		// athlete profil
		PersonsAthlete athlete = entity.getProfileAthlete();
		if(athlete!=null) {
			values.put(PersonEntity.HEIGHT, athlete.getHeight());
			values.put(PersonEntity.HEIGHT_UNIT, athlete.getHeightUnit());
			values.put(PersonEntity.WEIGHT, athlete.getWeight());
			values.put(PersonEntity.WEIGHT_UNIT, athlete.getWeightUnit());
			values.put(PersonEntity.MAX_HR, athlete.getMaxHr());
			values.put(PersonEntity.RESTING_HR, athlete.getRestingHr());
			values.put(PersonEntity.VO2_MAX, athlete.getVo2Max());
		}

		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}
	}

	public String getPicture() {
		try {
			byte[] bytes = entity.getPicture();
			return ValueManager.encodeHexString(bytes);
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