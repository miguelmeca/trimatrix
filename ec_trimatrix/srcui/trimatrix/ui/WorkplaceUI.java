package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.OUTLOOKBARITEMComponent;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.WorkplaceUI}")

public class WorkplaceUI extends MyWorkpageDispatchedBean implements Serializable
{       
	protected int m_selectedRole = 0;
    public int getSelectedRole() { return m_selectedRole; }
    public void setSelectedRole(int value) { m_selectedRole = value; }

    public void onSwitchRole(ActionEvent event) {
    	if (event.getSource() instanceof OUTLOOKBARITEMComponent) {
    		OUTLOOKBARITEMComponent item = (OUTLOOKBARITEMComponent)event.getSource();
        	String clientname = item.getAttributeString("clientname");
        	Constants.Role role = Constants.Role.valueOf(clientname.toUpperCase());
        	if (role!=null) {
        		m_selectedRole = role.getId();
        	}
    	}    	
    }


	public WorkplaceUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}
	
}
