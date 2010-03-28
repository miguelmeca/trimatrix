package trimatrix.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.exceptions.MandatoryCheckException;
import trimatrix.logic.EntityListLogic;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

public abstract class AEntityDetailUI extends MyWorkpageDispatchedBean implements IEntityDetailUI {

	protected final String[] MANDATORY_FIELDS;

	protected Constants.Mode mode;
	protected EntityDetailUI entityDetailUI;
	protected final EntityListLogic ENTITYLISTLOGIC = getLogic().getEntityListLogic();

	protected Map<String, Object> values = new HashMap<String, Object>();
	protected Map<String, String> defaults = new HashMap<String, String>();
    protected Map<String, String> bgpaint = new HashMap<String, String>();

    public AEntityDetailUI(IWorkpageDispatcher dispatcher, String[] mandatory_fields) {
		super(dispatcher);
		MANDATORY_FIELDS = mandatory_fields;
	}

	public AEntityDetailUI(IWorkpageDispatcher dispatcher, String[] mandatory_fields, boolean labelingEnabled) {
		super(dispatcher, labelingEnabled);
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

	protected void checkMandatory(String[] fields) throws MandatoryCheckException {
		for (String name : fields) {
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
        if (mode == Constants.Mode.SHOW || mode == Constants.Mode.FINAL) {
        	m_enabled = false;
        } else {
        	m_enabled = true;
        }
	}

	protected void setDefaults() {
		if(mode == Constants.Mode.NEW) {
			for(Entry<String, String> entry : defaults.entrySet()) {
				// get Default
				String value = getServiceLayer().getDefaultValueBindingService().getDVBinding(entry.getValue());
				if(Helper.isEmpty(value)) continue;
				if(values.containsKey(entry.getKey())) values.put(entry.getKey(), value);
			}
		}
	}

	protected boolean m_enabled;
    public boolean getEnabled() { return m_enabled; }
    public boolean getNotEnabled() {
    	return !m_enabled;
    }

	public Map<String, String> getBgpaint() {
		return bgpaint;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void prepareSave() {};

	public void postSave() {};

	public void postDelete(boolean isDeleted) {};

	public boolean checkEdit() {return true;}

}
