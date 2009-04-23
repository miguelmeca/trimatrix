package trimatrix.ui.utils;

import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.defaultscreens.ModalPopup.IModalPopupListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageDispatchedBean;

import trimatrix.logic.LogicLayer;
import trimatrix.services.ServiceLayer;
import trimatrix.ui.Dispatcher;
import trimatrix.ui.EntityDetailUI;
import trimatrix.ui.EntityListUI;
import trimatrix.ui.IEntityDetailUI;
import trimatrix.ui.PersonSelectionUI;
import trimatrix.ui.UserDetailUI;
import trimatrix.ui.UserSelectionUI;
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
	// dictionary access
	// ------------------------------------------------------------------------
	protected Dictionary getDictionary() {
		return getOwningDispatcher().getDictionary();
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
	
	public UserSelectionUI getUserSelectionUI() {
		return (UserSelectionUI)getOwningDispatcher().getDispatchedBean(UserSelectionUI.class);
	}
	
	public PersonSelectionUI getPersonSelectionUI() {
		return (PersonSelectionUI)getOwningDispatcher().getDispatchedBean(PersonSelectionUI.class);
	}
}
