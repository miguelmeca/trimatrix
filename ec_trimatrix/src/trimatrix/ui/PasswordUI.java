package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.managedbean.IDispatcher;

import trimatrix.logic.LogonLogic;
import trimatrix.ui.utils.MyDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.PasswordUI}")

public class PasswordUI extends MyDispatchedBean implements Serializable
{
	private final LogonLogic LOGONLOGIC = getLogic().getLogonLogic();

	public PasswordUI(IDispatcher dispatcher) {
		super(dispatcher);
	}

	protected String m_newPassword2;
    public String getNewPassword2() { return m_newPassword2; }
    public void setNewPassword2(String value) { m_newPassword2 = value; }

    protected String m_newPassword;
    public String getNewPassword() { return m_newPassword; }
    public void setNewPassword(String value) { m_newPassword = value; }

    public void onChange(ActionEvent event) {
    	if(!m_newPassword.equals(m_newPassword2)) {
    		Statusbar.outputAlert(Helper.getMessages("passwords_different"), Helper.getLiteral("warn")).setLeftTopReferenceCentered();
    		return;
    	}
    	try {
			LOGONLOGIC.changePassword(m_newPassword);
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getMessages("password_change_failure"), Helper.getLiteral("error"), ex.toString()).setLeftTopReferenceCentered();
			return;
		}
    	// Change succesfull
		getAroundUI().setContentPage(Constants.Page.WORKPLACE.getUrl());
    }
}
