package trimatrix.ui;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.UserTracker;

@CCGenClass (expressionBase="#{d.AdminPanelUI}")

public class AdminPanelUI extends MyWorkpageDispatchedBean implements Serializable {
    public AdminPanelUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		m_countUsers = UserTracker.getLoggedInusers();
	}

	public void onRefresh(ActionEvent event) {
		m_countUsers = UserTracker.getLoggedInusers();
	}

    protected int m_countUsers;
    public int getCountUsers() { return m_countUsers; }
    public void setCountUsers(int value) { m_countUsers = value; }

}
