package trimatrix.ui.utils;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.defaultscreens.ModelessPopup;
import org.eclnt.jsfserver.defaultscreens.ModalPopup.IModalPopupListener;
import org.eclnt.jsfserver.elements.impl.BUTTONComponent;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.util.DefaultModelessPopupListener;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDispatchedBean;

import trimatrix.db.DAOLayer;
import trimatrix.db.Labels;
import trimatrix.logic.LogicLayer;
import trimatrix.relations.RelationLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.ui.AttachmentSelectionUI;
import trimatrix.ui.CreateRelationUI;
import trimatrix.ui.Dispatcher;
import trimatrix.ui.DoctorSelectionUI;
import trimatrix.ui.EntityDetailUI;
import trimatrix.ui.EntityListUI;
import trimatrix.ui.EntitySelectionUI;
import trimatrix.ui.IEntityDetailUI;
import trimatrix.ui.LabelChangePopUp;
import trimatrix.ui.LabelPopUpUI;
import trimatrix.ui.PersonSelectionUI;
import trimatrix.ui.UserDetailUI;
import trimatrix.ui.UserSelectionUI;
import trimatrix.ui.WorkplaceUI;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public class MyWorkpageDispatchedBean extends WorkpageDispatchedBean implements IModalPopupListener{	
	
	public MyWorkpageDispatchedBean(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		labelingEnabled = false;		
	}		
	
	public MyWorkpageDispatchedBean(IWorkpageDispatcher dispatcher, boolean labelingEnabled) {
		super(dispatcher);
		this.labelingEnabled = labelingEnabled;
		// get expression base by annotation
	    CCGenClass ccgenClass = getClass().getAnnotation(CCGenClass.class);	    
    	if(ccgenClass!=null) expressionBase = ccgenClass.expressionBase().replace('}', '.');
    	// initialize Label row
    	if(labelingEnabled) setLabelRowDynamic();
	}
	
	@Override
	public Dispatcher getOwningDispatcher() {
		return (Dispatcher) super.getOwningDispatcher();
	}
		
	// ------------------------------------------------------------------------
	// logic for label functionality
	// ------------------------------------------------------------------------	
	private String expressionBase;
	private boolean labelingEnabled;	
	public boolean isLabelingEnabled() { return labelingEnabled; }
	
	protected ROWDYNAMICCONTENTBinding m_labelRow = new ROWDYNAMICCONTENTBinding();
	public ROWDYNAMICCONTENTBinding getLabelRow() { return m_labelRow; }
    public void setLabelRow(ROWDYNAMICCONTENTBinding value) { m_labelRow = value; }
	
	/**
	 * Refresh the pages which have labeling functionality
	 */
	public void refreshLabels() {
		List<IWorkpage> workpages = getWorkpageContainer().getAllWorkpages();
		for (IWorkpage workpage : workpages) {
			Map<Class<?>,Object> mapDispatcher = (Map)workpage.getDispatcher();
			for(Object bean : mapDispatcher.values()) {
				if(bean instanceof MyWorkpageDispatchedBean) {
					MyWorkpageDispatchedBean dispatchedBean = (MyWorkpageDispatchedBean)bean;
					if(dispatchedBean.isLabelingEnabled()) {
						dispatchedBean.setLabelRowDynamic();
					}
				}
			}
		}
	}
	
	/**
	 * OBSOLET
	 * Recursive walk through all dispatchers and check
	 * if labeling is enabled, then refresh labels
	 * @param dispatcher
	 */
	private void refreshLabels(IDispatcher dispatcher) {
		Map<Class<?>,Object> mapDispatcher = (Map)dispatcher;
		for(Object bean : mapDispatcher.values()) {
			// if bean is dispatcher then do recursion
			if(bean instanceof IDispatcher) {
				refreshLabels(dispatcher);
			// if bean is dispatched bean then go further
			} else if(bean instanceof MyWorkpageDispatchedBean) {
				MyWorkpageDispatchedBean dispatchedBean = (MyWorkpageDispatchedBean)bean;
				if(dispatchedBean.isLabelingEnabled()) {
					dispatchedBean.setLabelRowDynamic();
				}
			}
		}
	}
    
    public void onLabelDelete(ActionEvent event) {
		// return if labeling functionality is not set
		if(!labelingEnabled) return;
		// return if entity id is not set
		String entityID = getWorkpage().getId();
		if(entityID==null||entityID.length()==0) return;
    	BUTTONComponent button =(BUTTONComponent)event.getSource();
    	String label_id = button.getAttributeValueAsString(Constants.CLIENTNAME);
    	getLogic().getLabelLogic().deleteLabelRelation(entityID, label_id);
    	setLabelRowDynamic();        	
    }
	
	protected void setLabelRowDynamic() { 
		// return if labeling functionality is not set
		if(!labelingEnabled) return;
		// return if entity id is not set
		String entityID = getWorkpage().getId();
		if(entityID==null||entityID.length()==0) return;
		// build dynamic row with labels
		StringBuffer xml = new StringBuffer();
		List<Labels> labels = getLogic().getLabelLogic().getLabelsByEntity(entityID);
		for(Labels label:labels) {
			// get inverted color for font
			Color background = Color.decode(label.getColor());
			String fontColor = Dictionary.getBlackOrWhite(background);		

			xml.append("<t:button bgpaint='roundedrectangle(0,0,100%,100%,10,10," + label.getColor() + ");rectangle(10,0,100%,100%," + label.getColor() + ")' stylevariant='WP_ISOLATEDWORKPAGE' foreground ='" + fontColor + "' font='weight:bold' text='"+ label.getDescription()  +"' />");
			xml.append("<t:coldistance width='1' />");
			xml.append("<t:button actionListener='" + expressionBase + "onLabelDelete}' clientname='" + label.getId() + "' bgpaint='rectangle(0,0,100%-10,100%," + label.getColor() + ");roundedrectangle(0,0,100%,100%,10,10," + label.getColor() + ")' foreground ='" + fontColor + "' font='weight:bold' stylevariant='WP_ISOLATEDWORKPAGE' text='X' />");
			xml.append("<t:coldistance />");
		}
		xml.append("<t:button actionListener='" + expressionBase + "onLabelSearch}' contentareafilled='false' image='/images/icons/magnifier.png' text='Label' id='LABELSEARCH' />");
		m_labelRow.setContentXml(xml.toString());
	}	
	 
	public void onLabelSearch(ActionEvent event) {
		// return if labeling functionality is not set
		if(!labelingEnabled) return;
		// return if entity id is not set
		String entityID = getWorkpage().getId();
		if(entityID==null||entityID.length()==0) return;
	   	final ModelessPopup popup = getOwningDispatcher().createModelessPopup();  
	   	LabelPopUpUI labelPopUpUI = getLabelPopUpUI();
	   	labelPopUpUI.setEntityID(entityID);
	   	labelPopUpUI.initialize();
	   	labelPopUpUI.prepareCallback(new LabelPopUpUI.IApplyingCallback(){
			public void apply() {
				setLabelRowDynamic();				
				popup.close();
			}
		});		
	   	popup.open(Constants.Page.LABELPOPUP.getUrl(), "Label", 250, 300, new DefaultModelessPopupListener(popup)); 
	   	popup.setUndecorated(true);
	   	popup.setLefTopReferenceComponentIdBottom("LABELSEARCH");
	   	popup.setCloseonclickoutside(true);
	} 
	 
	
	// ------------------------------------------------------------------------
	// logic for PopUp
	// ------------------------------------------------------------------------	
	protected ModalPopup m_popup;

	public void reactOnPopupClosedByUser() {
		if (m_popup != null)
			m_popup.close();
	}
	
	// ------------------------------------------------------------------------
	// logic access
	// ------------------------------------------------------------------------
	protected LogicLayer getLogic() {
		return getOwningDispatcher().getLogicLayer();
	}
	
	// ------------------------------------------------------------------------
	// service access
	// ------------------------------------------------------------------------
	protected ServiceLayer getServiceLayer() {
		return getOwningDispatcher().getServiceLayer();
	}	
	
	// ------------------------------------------------------------------------
	// relation access
	// ------------------------------------------------------------------------
	protected RelationLayer getRelationLayer() {
		return getOwningDispatcher().getRelationLayer();
	}	
	
	// ------------------------------------------------------------------------
	// dao access
	// ------------------------------------------------------------------------
	protected DAOLayer getDaoLayer() {
		return getOwningDispatcher().getDaoLayer();
	}	

	// ------------------------------------------------------------------------
	// convenience access
	// ------------------------------------------------------------------------

	public IEntityDetailUI getUserDetailUI() {
		return (UserDetailUI) getOwningDispatcher().getDispatchedBean(
				UserDetailUI.class);
	}
	
	public EntityDetailUI getEntityDetailUI() {
		return (EntityDetailUI) getOwningDispatcher().getDispatchedBean(
				EntityDetailUI.class);
	}
	
	public EntityListUI getEntityListUI() {
		return (EntityListUI) getOwningDispatcher().getDispatchedBean(EntityListUI.class);
	}
	
	public EntitySelectionUI getEntitySelectionUI(Constants.Entity entity) {
		if (entity == Constants.Entity.USER) return (EntitySelectionUI)getOwningDispatcher().getDispatchedBean(UserSelectionUI.class);
		if (entity == Constants.Entity.PERSON) return (EntitySelectionUI)getOwningDispatcher().getDispatchedBean(PersonSelectionUI.class);
		if (entity == Constants.Entity.DOCTOR) return (EntitySelectionUI)getOwningDispatcher().getDispatchedBean(DoctorSelectionUI.class);
		if (entity == Constants.Entity.ATTACHMENT) return (EntitySelectionUI)getOwningDispatcher().getDispatchedBean(AttachmentSelectionUI.class);
		
		Dictionary.logger.warn("For entity " + entity.name() + " no SelectionUI available!");
		return null;
	}
	
	public CreateRelationUI getCreateRelationUI() {
		return (CreateRelationUI)getOwningDispatcher().getDispatchedBean(CreateRelationUI.class);
	}
	
	public LabelPopUpUI getLabelPopUpUI() {
		return (LabelPopUpUI)getOwningDispatcher().getDispatchedBean(LabelPopUpUI.class);
	}
	
	public LabelChangePopUp getLabelChangePopUp() {
		return (LabelChangePopUp)getOwningDispatcher().getDispatchedBean(LabelChangePopUp.class);
	}
	
	public WorkplaceUI getWorkplaceUI() {
		return (WorkplaceUI)getOwningDispatcher().getDispatchedBean(WorkplaceUI.class);
	}
}