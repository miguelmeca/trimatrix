package trimatrix.ui;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Map.Entry;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.structures.SUserInfo;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.MessageHandler;
import trimatrix.utils.UserTracker;

@CCGenClass (expressionBase="#{d.AdminPanelUI}")

public class AdminPanelUI extends MyWorkpageDispatchedBean implements Serializable {

    public String getLogonMessage() { return MessageHandler.getLogonMessage(); }
    public void setLogonMessage(String value) { MessageHandler.setLogonMessage(value); }

    public boolean getShowLogonMessage() { return MessageHandler.isShowLogonMessage(); }
    public void setShowLogonMessage(boolean value) { MessageHandler.setShowLogonMessage(value); }

    
    protected FIXGRIDListBinding<GridLoggedInUsersItem> m_gridLoggedInUsers = new FIXGRIDListBinding<GridLoggedInUsersItem>();
    public FIXGRIDListBinding<GridLoggedInUsersItem> getGridLoggedInUsers() { return m_gridLoggedInUsers; }
    public void setGridLoggedInUsers(FIXGRIDListBinding<GridLoggedInUsersItem> value) { m_gridLoggedInUsers = value; }

    public class GridLoggedInUsersItem extends FIXGRIDItem implements java.io.Serializable {
    	private String userName;
    	private String clientIP;
    	private HttpSession session;
		public GridLoggedInUsersItem(String userName, String clientIP, HttpSession session) {
			super();
			this.userName = userName;
			this.clientIP = clientIP;
			this.session = session;
		}
		public String getUserName() {
			return userName;
		}
		
		public Date getCreationTime() {
			return new Date(session.getCreationTime());
		}
		
		public Date getLastAccessedTime() {
			return new Date(session.getLastAccessedTime());
		}
		
		public String getClientIP() {
			return clientIP;
		}
		public String getSessionID() {
			return session.getId();
		}   	
		
		public void invalidate() {
			session.invalidate();
			//UserTracker.deleteUser(session);
		}
    }

    public AdminPanelUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		buildGrid();
	}

	public void onRefresh(ActionEvent event) {
		buildGrid();
	}
	
	public void onRestart(ActionEvent event) 
    {
        HttpSessionAccess.getCurrentHttpSession().invalidate();
    }

    public void onRestartSoft(ActionEvent event) 
    {
        getWorkpageContainer().closeAllWorkpages(false,new Runnable() 
        {
            public void run()
            {
                HttpSessionAccess.getCurrentHttpSession().invalidate();
            }
        });
    }
    
    public void onInvalidate(ActionEvent event) {
    	GridLoggedInUsersItem selected = m_gridLoggedInUsers.getSelectedItem();
    	if(selected!=null) {
    		try {
    			selected.invalidate();
			} catch (Exception ex) {
				Statusbar.outputMessage("Session already invalidated!");				
			} finally {
				UserTracker.deleteUser(selected.session);
				buildGrid();
			}    		
    	}
    }

    public int getCountUsers() { return UserTracker.getLoggedInusers(); }
    
    private void buildGrid() {
    	m_gridLoggedInUsers.getItems().clear();
    	Set<Entry<HttpSession, SUserInfo>> loggedInUserSet = UserTracker.getLoggedInUserMap().entrySet();
    	for(Entry<HttpSession, SUserInfo> entry : loggedInUserSet) {
    		m_gridLoggedInUsers.getItems().add(new GridLoggedInUsersItem(entry.getValue().user,entry.getValue().clientIP, entry.getKey()));
    	}
    }
    
    public long getAvailableMemory() 
    { 
        return Runtime.getRuntime().freeMemory(); 
    }

    public long getUsedMemory() 
    { 
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public void onCollectGarbage(ActionEvent event) 
    {
        System.gc();
    }
}
