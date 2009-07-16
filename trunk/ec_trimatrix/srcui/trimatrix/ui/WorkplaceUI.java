package trimatrix.ui;

import java.io.Serializable;
import java.util.List;

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
	private static final String DIVIDERLOCATIONMAX = "150";
	private static final String DIVIDERLOCATIONMIN = "0";
    protected String m_toggleShowHideImage;
   
    protected String m_dividerLocation = DIVIDERLOCATIONMAX;
    public String getDividerLocation() { return m_dividerLocation; }      
    
    protected boolean m_renderLabels;
    public boolean getRenderLabels() { return m_renderLabels; }
    public void setRenderLabels(boolean value) { m_renderLabels = value; }

    protected boolean m_renderCoach;
    public boolean getRenderCoach() { return m_renderCoach; }
    public void setRenderCoach(boolean value) { m_renderCoach = value; }

    protected boolean m_renderAthlete;
    public boolean getRenderAthlete() { return m_renderAthlete; }
    public void setRenderAthlete(boolean value) { m_renderAthlete = value; }

    protected boolean m_renderAdmin;
    public boolean getRenderAdmin() { return m_renderAdmin; }
    public void setRenderAdmin(boolean value) { m_renderAdmin = value; }
       
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
		initialize();
	}
	
	private void initialize() {
		setRenderAdmin(false);
		setRenderCoach(false);
		setRenderAthlete(false);
		List<String> roles = getServiceLayer().getDictionaryService().getMyRoles();
		if(roles==null) return;
		// Athlete
		if(roles.contains(Constants.Role.ATHLETE.getName())) {
			setRenderAthlete(true);
			m_selectedRole = Constants.Role.ATHLETE.getId();
		}
		// Coach
		if(roles.contains(Constants.Role.COACH.getName())) {
			setRenderCoach(true);
			m_selectedRole = Constants.Role.COACH.getId();
		}
		// Admin
		if(roles.contains(Constants.Role.ADMIN.getName())) {
			setRenderAdmin(true);
			m_selectedRole = Constants.Role.ADMIN.getId();
		}
	}	
	
	public String getToggleShowHideImage() 
    {
        if (!m_dividerLocation.equals(DIVIDERLOCATIONMIN))
            return Constants.SIDE_CONTRACT;
        else
            return Constants.SIDE_EXPAND;
    }
	
	public void onShowHideFunctions(ActionEvent event) {
		if (m_dividerLocation.equals(DIVIDERLOCATIONMIN))
            m_dividerLocation = DIVIDERLOCATIONMAX;
        else
            m_dividerLocation = DIVIDERLOCATIONMIN;
	}
	
	public String getLabelsShowHideImage() 
    {
        if (!m_renderLabels)
            return Constants.SIDE_CONTRACT;
        else
            return Constants.SIDE_EXPAND;
    }
	
	public void onShowHideLabels(ActionEvent event) {
		m_renderLabels = !m_renderLabels;
	}
	
}
