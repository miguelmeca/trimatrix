	package trimatrix.ui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

import trimatrix.db.KRoles;
import trimatrix.db.Persons;
import trimatrix.db.Users;
import trimatrix.entities.UserEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;
import trimatrix.utils.MailSender;
import trimatrix.utils.Constants.Entity;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.UserDetailUI}")

public class UserDetailUI extends AEntityDetailUI implements Serializable
{    
	protected ValidValuesBinding languagesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.LANGUAGE);
    public ValidValuesBinding getLanguagesVvb() { return languagesVvb; }
   
	private Users entity;    
    
	protected boolean isAdmin;
	public boolean isAdmin() {return isAdmin;}	
	public void setAdmin(boolean isAdmin) {	this.isAdmin = isAdmin;	}

	protected boolean isCoach;
	public boolean isCoach() {return isCoach;}
	public void setCoach(boolean isCoach) {	this.isCoach = isCoach;	}

	protected boolean isAthlete;
	public boolean isAthlete() {return isAthlete;}	
	public void setAthlete(boolean isAthlete) {	this.isAthlete = isAthlete;	}
	
	protected boolean isScouter;
	public boolean isScouter() {return isScouter;}	
	public void setScouter(boolean isScouter) {	this.isScouter = isScouter;	}
	
	public UserDetailUI(IWorkpageDispatcher dispatcher) {		
		super(dispatcher, new String[] {UserEntity.USER_NAME, UserEntity.EMAIL, UserEntity.PERSON});
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
    }
    
    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Users)entityObject;        	 	
    	// set enabled state and set fields
    	init();
    }    
    
	public void init() {
		fillMaps();
		fillRoles();
    	// set state
		setState();
        
    }
    
	public void validate() throws MandatoryCheckException, EmailNotValidException {		
		// mandatory check
		checkMandatory();
        // email check
		ClassValidator<Users> validator = new ClassValidator<Users>(Users.class);
		String email = (String)values.get(UserEntity.EMAIL);
		InvalidValue[] invalidValues = validator.getPotentialInvalidValues(UserEntity.EMAIL, email);
		if(invalidValues.length>0) {
			throw new EmailNotValidException((String)values.get(values.get(UserEntity.EMAIL)));
		}	
		// check if at least one role selected
		if(!isAdmin && !isCoach && !isAthlete && !isScouter) {
			throw new MandatoryCheckException("roles");
		}		
		// fill values to entities properties
		fillEntityProperties();
	}	
	
	private void fillEntityProperties() {
		// user_name
		entity.setUserName((String)values.get(UserEntity.USER_NAME));
		// email
		entity.setEmail((String)values.get(UserEntity.EMAIL));
		// language
		entity.setLanguageKey((String)values.get(UserEntity.LANGUAGE));
		// currency
		entity.setCurrencyKey((String)values.get(UserEntity.CURRENCY));
		// active
		entity.setActive((Boolean)values.get(UserEntity.ACTIVE));
		// set roles
		setRoles();
	}
	
	/**
	 * Write entity properties to map for ui handling 
	 */
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(UserEntity.USER_NAME, entity.getUserName());
		values.put(UserEntity.LANGUAGE, entity.getLanguageKey());
		values.put(UserEntity.CURRENCY, entity.getCurrencyKey());		
		values.put(UserEntity.EMAIL, entity.getEmail());	
		values.put(UserEntity.ACTIVE, entity.getActive());
		values.put(UserEntity.INITIAL, entity.getInitial());
		values.put(UserEntity.LOCKED, entity.getLocked());		
		setPersonDescription(entity);		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	
	/**
	 * Get roles for specific user
	 */
	public void fillRoles() {
		isAdmin = false;
		isCoach = false;
		isAthlete = false;
		isScouter = false;
		Set<KRoles> roles = entity.getRoles();
		for (KRoles role : roles) {
			if(role.getKey().equals(Constants.Role.ADMIN.getName())) isAdmin = true;
			if(role.getKey().equals(Constants.Role.COACH.getName())) isCoach= true;
			if(role.getKey().equals(Constants.Role.ATHLETE.getName())) isAthlete = true;
			if(role.getKey().equals(Constants.Role.SCOUTER.getName())) isScouter = true;
		}
	}

	/**
	 * Set specific roles
	 */
	private void setRoles() {
		Set<KRoles> roles = new HashSet<KRoles>();
		if (isAdmin) roles.add(new KRoles(Constants.Role.ADMIN.getName()));
		if (isCoach) roles.add(new KRoles(Constants.Role.COACH.getName()));
		if (isAthlete) roles.add(new KRoles(Constants.Role.ATHLETE.getName()));
		if (isScouter) roles.add(new KRoles(Constants.Role.SCOUTER.getName()));
		entity.setRoles(roles);
	}
	
	/**
	 * Get description for selected person
	 * @param user
	 */
	private void setPersonDescription(Users user) {
		Persons person = user.getPerson();
		String personDescription = Constants.EMPTY;
		if (person!=null) {
			personDescription = person.toString();
		}
		values.put(UserEntity.PERSON, personDescription);
	}	
	
	/**
	 * Call person selection pop up
	 * @param event
	 */
	public void onPersonSearch(ActionEvent event) {
		IEntitySelectionUI entitySelectionUI = getEntitySelectionUI(Constants.Entity.PERSON);
		entitySelectionUI.buildData(Entity.PERSON);
       	entitySelectionUI.prepareCallback(new EntitySelectionUI.ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Persons person = (Persons)ENTITYLISTLOGIC.get(Constants.Entity.PERSON, id);
				entity.setPerson(person);
				setPersonDescription(entity);	
				m_popup.close();
			}});    	
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();  
    	m_popup.setLeftTopReferenceCentered();
    	m_popup.open(Constants.Page.PERSONSELECTION.getUrl(), "Personensuche", 800, 600, this);    	
    }
	
	public void onPersonRemove(ActionEvent event) {
		entity.setPerson(null);
		setPersonDescription(entity);	
	}
	
	public void onSendGeneratedPassword(ActionEvent event) {
		YESNOPopup popup = YESNOPopup.createInstance(
				"Generate password", 
				"Do you really want to generate a new password and send this to the user?", 
				new IYesNoCancelListener(){

					public void reactOnCancel() {}

					public void reactOnNo() {}

					public void reactOnYes() {						
						String password = UUID.randomUUID().toString().substring(24);
						entity.setInitial(true);
						entity.setLocked(false);
						entity.setUserHash(password);
						try {
							validate();
							ENTITYLISTLOGIC.save(Constants.Entity.USER, entity);
							String receiver = entity.getEmail();
							// get template
							String message = Helper.readFileAsString(Constants.TEMPLATE_NEWPASSWORD);
							// replace variables
							message = message.replace("%name%", entity.getPerson().toString());
							message = message.replace("%user%", entity.getUserName());
							message = message.replace("%pass%", password);
							message = message.replace("%url%", Constants.TRIMATRIXURL);							
							//String message = "Hello Trimatrix User, \n a new password " + password + " is generated for your user " + entity.getUserName() + ". \n\n regards your Trimatrix Team"; 
							MailSender.postMail(new String[] {receiver}, "Welcome to Trimatrix", message, Constants.TYPE_TEXTHTML, null);
							Statusbar.outputSuccess("Password successfully send to " + receiver);
						} catch (Exception ex) {
							Statusbar.outputError("Password couldn't be generated/sent!", ex.toString());
						} 							
					}						
				}
		);	
		popup.getModalPopup().setLeftTopReferenceCentered();
	}
	
	public void onPersonClicked(ActionEvent event) {
		Statusbar.outputMessage(event.getClass().getName());
	}
}
