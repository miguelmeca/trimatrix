package trimatrix.ui;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoListener;
import org.eclnt.jsfserver.elements.events.BaseActionEventPopupMenuItem;
import org.eclnt.jsfserver.elements.impl.BUTTONComponent;
import org.eclnt.jsfserver.elements.impl.OUTLOOKBARITEMComponent;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.Workpage;

import trimatrix.db.EntitiesHaveLabels;
import trimatrix.db.Labels;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.WorkplaceUI}")

public class WorkplaceUI extends MyWorkpageDispatchedBean implements Serializable
{    
	private static final String DIVIDERLOCATIONMAX = "150";
	private static final String DIVIDERLOCATIONMIN = "0";
    //protected String m_toggleShowHideImage;
   
    protected String m_dividerLocation = DIVIDERLOCATIONMAX;
    public String getDividerLocation() { return m_dividerLocation; }      
    
    protected boolean m_renderLabels = true;
    public boolean getRenderLabels() { return m_renderLabels; }
    public void setRenderLabels(boolean value) { m_renderLabels = value; }

    protected boolean m_renderCoach = false;
    public boolean getRenderCoach() { return m_renderCoach; }
    public void setRenderCoach(boolean value) { m_renderCoach = value; }

    protected boolean m_renderAthlete = false;
    public boolean getRenderAthlete() { return m_renderAthlete; }
    public void setRenderAthlete(boolean value) { m_renderAthlete = value; }

    protected boolean m_renderAdmin = true;
    public boolean getRenderAdmin() { return m_renderAdmin; }
    public void setRenderAdmin(boolean value) { m_renderAdmin = value; }
    
    protected boolean m_renderScouter = false;
    public boolean getRenderScouter() { return m_renderScouter; }
    public void setRenderScouter(boolean value) { m_renderScouter = value; }
         
	protected int m_selectedRole = 0;
    public int getSelectedRole() { return m_selectedRole; }
    public void setSelectedRole(int value) { m_selectedRole = value; }
    
    private static final String DELETELABEL = "delete";
    private static final String CHANGELABEL = "change";
    public String getDeleteLabelCommand() { return DELETELABEL; }
    public String getChangeLabelCommand() { return CHANGELABEL; }

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
		setRenderScouter(false);
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
		// Scouter
		if(roles.contains(Constants.Role.SCOUTER.getName())) {
			setRenderScouter(true);
			m_selectedRole = Constants.Role.SCOUTER.getId();
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
	
	public void onHandleLabels(ActionEvent event) {
		// get label id
		if (!(event.getSource() instanceof BUTTONComponent)) return;
     	BUTTONComponent button =(BUTTONComponent)event.getSource();
    	String label_id = button.getAttributeValueAsString(Constants.CLIENTNAME);
    	final Labels label = getDaoLayer().getLabelsDAO().findById(label_id);
		// handle popupmenue
		if (event instanceof BaseActionEventPopupMenuItem) {
			BaseActionEventPopupMenuItem bae = (BaseActionEventPopupMenuItem)event;
			String command = bae.getCommand();			
			// delete label
			if(DELETELABEL.equals(command)) {				
				// check if relation to entity exist
				final List<EntitiesHaveLabels> relations = getDaoLayer().getEntitiesHaveLabelsDAO().findByLabel(label_id);
				// popup
				YESNOPopup.createInstance ( "Delete label", "For label " + label.getDescription() + " there exist " + relations.size() + " relations to entities, sure to delete label?",
						new IYesNoListener() {
							public void reactOnNo()	{
								return;
							}
							public void reactOnYes() {
								// delete all relations
								if(relations.size() > 0) {				
									for(EntitiesHaveLabels relation:relations) {
										getDaoLayer().getEntitiesHaveLabelsDAO().delete(relation);
										refreshLabels();
									}
								}
								// delete label		
								if(label!=null) {
									getDaoLayer().getLabelsDAO().delete(label);
									Statusbar.outputMessage("Delete Label " + label.getDescription());
								}								
							}
					});	
			}
			// change label
			if(CHANGELABEL.equals(command)) {
				LabelChangePopUp labelChangePopUp = getLabelChangePopUp();
				labelChangePopUp.prepareCallback(new LabelChangePopUp.IPopupCallback(){
					public void cancel() {
						m_popup.close();						
					}					
				});
				if(!labelChangePopUp.setLabel(label_id)) {
					Helper.logger.error("Label " + label_id + " not correct!");
					return;
				}			
				m_popup = getWorkpage().createModalPopupInWorkpageContext();  			
		    	m_popup.open(Constants.Page.LABELCHANGEPOPUP.getUrl(), "Label ändern",350, 175, this); 
		    	//m_popup.setUndecorated(true);
			}		
			
		} else {
			// Standard click navigate to search result
			IWorkpageDispatcher wpd = getOwningDispatcher();
			IWorkpageContainer wpc = getWorkpageContainer();
			IWorkpage wp = new Workpage( wpd, Constants.Page.LABELSEARCHRESULT.getUrl(), label_id, label.getDescription(), null, true);			
			wp.setParam(Constants.P_LABEL, label_id);	
			wpc.addWorkpage(wp);
		}
	}	
	
	// ------------------------------------------------------------------------
	// logic for label overview
	// ------------------------------------------------------------------------	
	
	protected ROWDYNAMICCONTENTBinding m_labels = new ROWDYNAMICCONTENTBinding();
	public ROWDYNAMICCONTENTBinding getLabels() { setLabelRowDynamic(); return m_labels; }
    public void setLabels(ROWDYNAMICCONTENTBinding value) { m_labels = value; }
    
    @Override
	public void setLabelRowDynamic() { 		
    	StringBuffer xml = new StringBuffer();
    	// get all labels for logged in person
		List<Labels> labels = getLogic().getLabelLogic().getLabelsByPerson(getServiceLayer().getDictionaryService().getMyPerson().getId());
		xml.append("<t:pane>");
		for(Labels label:labels) {
			// get inverted color for font
			Color background = Color.decode(label.getColor());
			String fontColor = Helper.getBlackOrWhite(background);		

			xml.append("<t:row>");
			xml.append("<t:button clientname='" + label.getId() + "' actionListener='#{d.WorkplaceUI.onHandleLabels}' contentareafilled='false' bgpaint='roundedrectangle(0,0,100%,100%,5,5," + label.getColor() + ")' stylevariant='WP_ISOLATEDWORKPAGE' popupmenu='LABEL' foreground ='" + fontColor + "' font='size:10;weight:bold' text='"+ label.getDescription() +"' width = '120' />");
			xml.append("</t:row>");
			xml.append("<t:rowdistance />");
		}
		xml.append("</t:pane>");
		m_labels.setContentXml(xml.toString());
	}
	
}
