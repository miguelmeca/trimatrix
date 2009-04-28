package trimatrix.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

public class AEntityDetailUI extends MyWorkpageDispatchedBean {

	protected final String[] MANDATORY_FIELDS;

	protected Constants.Mode mode;  
	protected EntityDetailUI entityDetailUI; 
	protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();
	
	protected Map<String, Object> values = new HashMap<String, Object>();
    protected Map<String, String> bgpaint = new HashMap<String, String>();
	
	public AEntityDetailUI(IWorkpageDispatcher dispatcher, String[] mandatory_fields) {
		super(dispatcher);
		MANDATORY_FIELDS = mandatory_fields;
	}
	
	protected void checkMandatory() throws MandatoryCheckException {
		for (String name : MANDATORY_FIELDS) {
			Object value = values.get(name);	
			if (value == null) { throw new MandatoryCheckException(name);	}
			if (value instanceof String) {				
				String strValue = (String)value;
				if(strValue.trim().length()==0) {
					throw new MandatoryCheckException(name);		
				}	
			}
		}
	}
	
	protected void setState() {
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
	
	protected String m_enabled;
    public String getEnabled() { return m_enabled; }
    public String getNotEnabled() { 
    	if (m_enabled.equals(Constants.TRUE)) {
        	return Constants.FALSE;
    	} else {
    		return Constants.TRUE;
    	}
    }
    public void setEnabled(String value) { m_enabled = value; }
    
    
    
    protected boolean m_enabledBool;
    public boolean getEnabledBool() { return m_enabledBool; }
    public void setEnabledBool(boolean value) { m_enabledBool = value; }
	
	public Map<String, String> getBgpaint() {
		return bgpaint;
	}
	
	public Map<String, Object> getValues() {
		return values;
	}

}
