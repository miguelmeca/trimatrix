package trimatrix.ui;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.db.Users;
import trimatrix.entities.UserEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.EntitySelectionUI.ISelectionCallback;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.UserDetailUI}")

public class UserDetailUI extends MyWorkpageDispatchedBean implements Serializable, IEntityDetailUI
{
	private final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
	protected ValidValuesBinding m_languagesVvb = getServiceLayer().getValueListBindingService().getVVBinding(Constants.ValueList.LANGUAGE, getDictionary().getLanguage());
    public ValidValuesBinding getLanguagesVvb() { return m_languagesVvb; }
    public void setLanguagesVvb(ValidValuesBinding value) { m_languagesVvb = value; }

    // TODO Change completly to person search
    public void onPersonSearch(ActionEvent event) {
    	PersonSelectionUI personSelectionUI = getPersonSelectionUI();
    	personSelectionUI.prepareCallback(new ISelectionCallback(){
			public void cancel() {
				m_popup.close();				
			}
			public void idSelected(String id) {
				Persons person = (Persons)ENTITYLISTLOGIC.getEntity(Constants.Entity.PERSON, id);
				entity.setPerson(person);
				setPersonDescription(entity);	
				m_popup.close();
			}});    	
    	m_popup = getWorkpage().createModalPopupInWorkpageContext();    	
    	m_popup.open(Constants.Page.PERSONSELECTION.url(), "Personensuche", 800, 600, this);    	
    	// TODO generate routine for centering the popup, this way is not ok!!!
    	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();    	
    	m_popup.setLeft(((int)screenSize.getWidth() - m_popup.getWidth()) / 2);
    	m_popup.setTop(((int)screenSize.getHeight() - m_popup.getHeight()) / 2);
    }

	private EntityDetailUI entityDetailUI;        
    
    private static final String[] MANDATORY_FIELDS = {UserEntity.USER_NAME, UserEntity.EMAIL};
    private static final Set<String> MANDATORY_SET = new HashSet<String>(Arrays.asList(MANDATORY_FIELDS));
	
	private Users entity;
	
	private Constants.Mode mode;
    
    private Map<String, String> values = new HashMap<String, String>();
    private Map<String, String> savedValues = new HashMap<String, String>();
    private Map<String, String> bgpaint = new HashMap<String, String>();
    
	public UserDetailUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		// get wrapping entity detail UI bean
		entityDetailUI = getEntityDetailUI();		
		entityDetailUI.setEntityDetailUI(this);		
        // init data
        init(entityDetailUI.getEntityObject());
    }
    
	protected String m_enabled;
    public String getEnabled() { return m_enabled; }
    public void setEnabled(String value) { m_enabled = value; }
    
    protected boolean m_enabledBool;
    public boolean getEnabledBool() { return m_enabledBool; }
    public void setEnabledBool(boolean value) { m_enabledBool = value; }

    public void init(Object entityObject) {
    	// set entity object
    	this.entity = (Users)entityObject;        	 	
    	// set enabled state and set fields
    	init();
    }
    
    public void init() {
    	// set fields
    	fillMaps();   
    	// get info from wrapping bean
        mode = entityDetailUI.getMode();
        // enabled?
        if (mode == Constants.Mode.SHOW) {
        	m_enabled = Constants.FALSE;
        	m_enabledBool = false;
        } else {
        	m_enabled = Constants.TRUE;
        	m_enabledBool = true;
        }
    }
    
	public void validate() throws MandatoryCheckException, EmailNotValidException {		
		// mandatory check
		for (String name : MANDATORY_FIELDS) {
			String value = values.get(name);	
			if(value!=null) { value = value.trim(); }
			if(MANDATORY_SET.contains(name) && (value == null  || value.length()==0)) {
				throw new MandatoryCheckException(name);		
			}
		}
        // email check
		if(!Dictionary.isEmailValid(values.get(UserEntity.EMAIL))) {
			throw new EmailNotValidException(values.get(values.get(UserEntity.EMAIL)));
		}	
		// fill values to entities properties
		fillEntityProperties();
	}
	
	private void fillEntityProperties() {
		// user_name
		entity.setUserName(values.get(UserEntity.USER_NAME));
		// email
		entity.setEmail(values.get(UserEntity.EMAIL));
		// language
		entity.setLanguageKey(values.get(UserEntity.LANGUAGE));
		// currency
		entity.setCurrencyKey(values.get(UserEntity.CURRENCY));
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
		setPersonDescription(entity);		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}
	private void setPersonDescription(Users user) {
		Persons person = user.getPerson();
		String personDescription = Constants.EMPTY;
		if (person!=null) {
			personDescription = person.toString();
		}
		values.put(UserEntity.PERSON, personDescription);
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.ui.IEntityDetailUI#saveValues()
	 */
	public void saveValues() {
		savedValues = new HashMap<String, String>(values);		
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.ui.IEntityDetailUI#restoreValues()
	 */
	public void restoreValues() {
		values = savedValues;
	}

	public Map<String, String> getValues() {
		return values;
	}
	public Map<String, String> getBgpaint() {
		return bgpaint;
	}
}
