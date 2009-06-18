	package trimatrix.ui;

import java.io.Serializable;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.BasePopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.db.Users;
import trimatrix.entities.UserEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;
import trimatrix.utils.MailSender;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.UserDetailUI}")

public class UserDetailUI extends AEntityDetailUI implements Serializable, IEntityDetailUI
{    
	protected ValidValuesBinding languagesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.LANGUAGE);
    public ValidValuesBinding getLanguagesVvb() { return languagesVvb; }
   
	private Users entity;    
    
	public UserDetailUI(IWorkpageDispatcher dispatcher) {		
		super(dispatcher, new String[] {UserEntity.USER_NAME, UserEntity.EMAIL});
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
    	// set state
		setState();
        
    }
    
	public void validate() throws MandatoryCheckException, EmailNotValidException {		
		// mandatory check
		checkMandatory();
        // email check
		if(!Dictionary.isEmailValid((String)values.get(UserEntity.EMAIL))) {
			throw new EmailNotValidException((String)values.get(values.get(UserEntity.EMAIL)));
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
    	m_popup.setTop(BasePopup.POS_CENTER);
    	m_popup.setLeft(BasePopup.POS_CENTER);
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
							String message = "Hello Trimatrix User, \n a new password " + password + " is generated for your user " + entity.getUserName() + ". \n\n regards your Trimatrix Team"; 
							MailSender.postMail(new String[] {receiver}, "New password generated", message );
							Statusbar.outputSuccess("Password successfully send to " + receiver);
						} catch (Exception ex) {
							Statusbar.outputError("Password couldn't be generated/sent!", ex.toString());
						} 							
					}						
				}
		);	
		popup.getModalPopup().setLeft(BasePopup.POS_CENTER);
		popup.getModalPopup().setTop(BasePopup.POS_CENTER);
	}
	
	public void onPersonClicked(ActionEvent event) {
		Statusbar.outputMessage(event.getClass().getName());
	}
}
