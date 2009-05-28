package trimatrix.ui.utils;

import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.defaultscreens.ModalPopup.IModalPopupListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDispatchedBean;

import trimatrix.db.DAOLayer;
import trimatrix.logic.LogicLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.ui.AttachmentSelectionUI;
import trimatrix.ui.CreateRelationUI;
import trimatrix.ui.Dispatcher;
import trimatrix.ui.DoctorSelectionUI;
import trimatrix.ui.EntityDetailUI;
import trimatrix.ui.EntityListUI;
import trimatrix.ui.EntitySelectionUI;
import trimatrix.ui.IEntityDetailUI;
import trimatrix.ui.PersonSelectionUI;
import trimatrix.ui.UserDetailUI;
import trimatrix.ui.UserSelectionUI;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

public class MyWorkpageDispatchedBean extends WorkpageDispatchedBean implements IModalPopupListener{

	public MyWorkpageDispatchedBean(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
	}
		
	public Dispatcher getOwningDispatcher() {
		return (Dispatcher) super.getOwningDispatcher();
	}
	
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
}
