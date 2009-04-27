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
import trimatrix.utils.Dictionary;

@SuppressWarnings("serial")
@CCGenClass (expressionBase="#{d.LogonUI}")

public class LogonUI extends MyDispatchedBean implements Serializable
{
	// TODO refactor access structure
	private final LogonLogic LOGONLOGIC = getLogic().getLogonLogic();
	private final Dictionary DICTIONARY = getDictionary();
	protected ValidValuesBinding m_languageVvb = LOGONLOGIC.getLogonLanguages(DICTIONARY.getLanguage());	
	
	public LogonUI(IDispatcher dispatcher)
    {
        super(dispatcher);
    }  

	public void onLogon(ActionEvent event) {
		// TODO integrate certain returncodes for locked, initial and inactive
		if(m_user == null || m_password == null) {
			Statusbar.outputError("Not all mandatory fields filled!");			
			return;
		}
		if(!LOGONLOGIC.logon(m_user, m_password)) {
			Statusbar.outputError("Logon not successfull!");
			return;
		}
		// check if locked
		if(LOGONLOGIC.isUserLocked()) {
			Statusbar.outputError("User is locked!");
			return;
		}
		// Logon succesfull
		if(LOGONLOGIC.isUserInitial()) {
			getAroundUI().setContentPage(Constants.Page.PASSWORD.url());
		} else {
			getAroundUI().setContentPage(Constants.Page.WORKPLACE.url());
		}		
	}
	
    public void onPasswordChange(ActionEvent event) {
    	if(m_user == null || m_password == null) {
			Statusbar.outputError("Not all mandatory fields filled!");
			return;
		}
		if(!LOGONLOGIC.logon(m_user, m_password)) {
			Statusbar.outputError("Logon not successfull!");
			return;
		}
		// Logon succesfull
		getAroundUI().setContentPage(Constants.Page.PASSWORD.url());
    }
	
    public void onLanguage(ActionEvent event) {
    	if(event instanceof BaseActionEventFlush){
    		LOGONLOGIC.changeLanguage(m_language);
    	}    
    	m_languageVvb = LOGONLOGIC.getLogonLanguages(DICTIONARY.getLanguage());	
    }
    
	public ValidValuesBinding getLanguageVvb() { return m_languageVvb; }
    public void setLanguageVvb(ValidValuesBinding value) { m_languageVvb = value; }
    
    protected String m_password;
    public String getPassword() { return m_password; }
    public void setPassword(String value) { m_password = value; }

    protected String m_user;
    public String getUser() { return m_user; }
    public void setUser(String value) { m_user = value; }
    
    protected String m_language;
    public String getLanguage() { return m_language; }
    public void setLanguage(String value) { m_language = value; }

}
