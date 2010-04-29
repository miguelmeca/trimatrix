package trimatrix.ui.utils;

import org.eclnt.jsfserver.managedbean.DefaultDispatchedBean;
import org.eclnt.jsfserver.managedbean.IDispatcher;

import trimatrix.logic.LogicLayer;
import trimatrix.ui.AroundUI;
import trimatrix.ui.Dispatcher;
import trimatrix.ui.LogonUI;
import trimatrix.ui.PasswordUI;


@SuppressWarnings("serial")
public class MyDispatchedBean extends DefaultDispatchedBean {
	public MyDispatchedBean(IDispatcher dispatcher) {
		super(dispatcher);
	}

	protected MyDispatchedBean m_this = this;

	@Override
	public Dispatcher getOwningDispatcher() {
		return (Dispatcher) super.getOwningDispatcher();
	}
	
	// ------------------------------------------------------------------------
	// logic access
	// ------------------------------------------------------------------------
	protected LogicLayer getLogic() {
		return getOwningDispatcher().getLogicLayer();
	}
	
	// ------------------------------------------------------------------------
	// convenience access
	// ------------------------------------------------------------------------

	public AroundUI getAroundUI() {
		return (AroundUI)getOwningDispatcher().getDispatchedBean(
				AroundUI.class);
	}

	public LogonUI getLogonUI() {
		return (LogonUI)getOwningDispatcher().getDispatchedBean(LogonUI.class);
	}
	
	public PasswordUI getPasswordUI() {
		return (PasswordUI)getOwningDispatcher().getDispatchedBean(PasswordUI.class);
	}	
}
