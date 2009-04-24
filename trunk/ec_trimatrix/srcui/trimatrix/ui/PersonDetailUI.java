package trimatrix.ui;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Persons;
import trimatrix.entities.PersonEntity;
import trimatrix.exceptions.EmailNotValidException;
import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.PersonDetailUI}")

public class PersonDetailUI extends MyWorkpageDispatchedBean implements Serializable, IEntityDetailUI
{
	private EntityDetailUI entityDetailUI;	
        
    private static final String[] MANDATORY_FIELDS = {PersonEntity.NAME_LAST};
    private static final Set<String> MANDATORY_SET = new HashSet<String>(Arrays.asList(MANDATORY_FIELDS));
	
	private Persons entity;
	
	private Constants.Mode mode;
    
    private Map<String, String> values = new HashMap<String, String>();
    private Map<String, String> savedValues = new HashMap<String, String>();
    private Map<String, String> bgpaint = new HashMap<String, String>();
    
	public PersonDetailUI(IWorkpageDispatcher dispatcher) {
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
    	this.entity = (Persons)entityObject;        	 	
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
        } else {
        	m_enabled = Constants.TRUE;
        }
    }
    
	public void validate() throws MandatoryCheckException, EmailNotValidException{
		String value = null;
		String name = null;
		// first name
		name  = PersonEntity.NAME_FIRST;
		value = values.get(name);	
		if(value!=null) { value = value.trim(); }
		entity.setNameFirst(value);
		if(MANDATORY_SET.contains(name) && (value == null  || value.length()==0)) {
			throw new MandatoryCheckException(name);		
		}
		// last name
		name  = PersonEntity.NAME_LAST;
		value = values.get(name);	
		if(value!=null) { value = value.trim(); }
		entity.setNameLast(value);
		if(MANDATORY_SET.contains(name) && (value == null  || value.length()==0)) {
			throw new MandatoryCheckException(name);		
		}
		// email
		name  = PersonEntity.EMAIL;
		value = values.get(name);	
		if(value!=null) { value = value.trim(); }
		entity.setEmail(value);
		if(MANDATORY_SET.contains(name) && (value == null  || value.length()==0)) {
			throw new MandatoryCheckException(name);		
		}		
		if(!Dictionary.isEmailValid(value)) {
			throw new EmailNotValidException(value);
		}
	}
	
	private void fillMaps() {
		// add values of fields
		values.clear();
		values.put(PersonEntity.NAME_FIRST, entity.getNameFirst());
		values.put(PersonEntity.NAME_LAST, entity.getNameLast());
		values.put(PersonEntity.EMAIL, entity.getEmail());
		
		// add bgpaint of fields
		bgpaint.clear();
		// mandatory fields
		for(String field : MANDATORY_FIELDS){
			bgpaint.put(field,Constants.BGP_MANDATORY);
		}		
	}

	public Map<String, String> getValues() {
		return values;
	}
	public Map<String, String> getBgpaint() {
		return bgpaint;
	}
	
	public String getPicture() {
		// TODO return dummy picture
		if(entity==null) { return Constants.EMPTY; }
		return entity.getPicture().toString();
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
}
