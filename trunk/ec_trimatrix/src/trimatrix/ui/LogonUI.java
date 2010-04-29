package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.managedbean.IDispatcher;

import trimatrix.logic.LogonLogic;
import trimatrix.ui.utils.MyDispatchedBean;
import trimatrix.utils.Constants;
import trimatrix.utils.Helper;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.LogonUI}")
public class LogonUI extends MyDispatchedBean implements Serializable {
	private final LogonLogic LOGONLOGIC = getLogic().getLogonLogic();

	public LogonUI(IDispatcher dispatcher) {
		super(dispatcher);
		// set language
		m_language = Helper.getLocale().getLanguage();
	}

	public void onLogon(ActionEvent event) {
		if (m_user == null || m_password == null) {
			Statusbar.outputError("Not all mandatory fields filled!");
			return;
		}
		// logon
		try {
			if (!LOGONLOGIC.logon(m_user, m_password)) {
				Statusbar.outputError("Logon not successful!");
				return;
			}
		} catch (Exception ex) {
			Statusbar.outputAlert(ex.toString(), "Error occured!")
					.setLeftTopReferenceCentered();
			return;
		}

		// check if locked
		if (LOGONLOGIC.isUserLocked()) {
			Statusbar.outputError("User is locked!");
			return;
		}
		// logon successful
		if (LOGONLOGIC.isUserInitial()) {
			getAroundUI().setContentPage(Constants.Page.PASSWORD.getUrl());
		} else {
			getAroundUI().setContentPage(Constants.Page.WORKPLACE.getUrl());
		}
	}

	public void onPasswordChange(ActionEvent event) {
		if (m_user == null || m_password == null) {
			Statusbar.outputError("Not all mandatory fields filled!");
			return;
		}
		try {
			if (!LOGONLOGIC.logon(m_user, m_password)) {
				Statusbar.outputError("Logon not successful!");
				return;
			}
		} catch (Exception ex) {
			Statusbar.outputAlert(ex.toString(), "Error occured!")
					.setLeftTopReferenceCentered();
			return;
		}
		// logon succesfull
		getAroundUI().setContentPage(Constants.Page.PASSWORD.getUrl());
	}

	public void onLanguage(ActionEvent event) {
		if (event instanceof BaseActionEventFlush) {
			LOGONLOGIC.changeLanguage(m_language);
		}
	}

	public ValidValuesBinding getLanguageVvb() {
		return LOGONLOGIC.getLogonLanguages();
	}

	protected String m_password;

	public String getPassword() {
		return m_password;
	}

	public void setPassword(String value) {
		m_password = value;
	}

	protected String m_user;

	public String getUser() {
		return m_user;
	}

	public void setUser(String value) {
		m_user = value;
	}

	protected String m_language;

	public String getLanguage() {
		return m_language;
	}

	public void setLanguage(String value) {
		m_language = value;
	}
}