package trimatrix.ui;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.workplace.IWorkpageDispatcher;

import trimatrix.db.Entities;
import trimatrix.db.EntitiesDAO;
import trimatrix.structures.SUserInfo;
import trimatrix.ui.utils.MyWorkpageDispatchedBean;
import trimatrix.utils.LockManager;
import trimatrix.utils.MessageHandler;
import trimatrix.utils.UserTracker;

@CCGenClass (expressionBase="#{d.AdminPanelUI}")

public class AdminPanelUI extends MyWorkpageDispatchedBean implements Serializable {
    protected FIXGRIDListBinding<GridLockedEntitiesItem> m_gridLockedEntities = new FIXGRIDListBinding<GridLockedEntitiesItem>();
    public FIXGRIDListBinding<GridLockedEntitiesItem> getGridLockedEntities() { return m_gridLockedEntities; }
    public void setGridLockedEntities(FIXGRIDListBinding<GridLockedEntitiesItem> value) { m_gridLockedEntities = value; }

    public class GridLockedEntitiesItem extends FIXGRIDItem implements java.io.Serializable {
    	private String id;
    	private String entity;
    	private String sessionId;
    	private String user;    	
    	
		public GridLockedEntitiesItem(String id, String entity,
				 String sessionId, String user) {
			this.id = id;
			this.entity = entity;
			this.sessionId = sessionId;
			this.user = user;
		}
		public String getId() {
			return id;
		}
		public String getEntity() {
			return entity;
		}
		public String getSessionId() {
			return sessionId;
		}
		public String getUser() {
			return user;
		}    	
		
		private void unlock() {
			LockManager.unlockEntity(id);
		}
    }

    public void onUnlockEntity(ActionEvent event) {
    	GridLockedEntitiesItem selected = m_gridLockedEntities.getSelectedItem();
    	if(selected!=null) {
    		selected.unlock();    		
    	}
    }

    protected String m_sessionMessage;
    public String getSessionMessage() { return m_sessionMessage; }
    public void setSessionMessage(String value) { m_sessionMessage = value; }

    protected ValidValuesBinding m_vvbSessionIds = new ValidValuesBinding();
    public ValidValuesBinding getVvbSessionIds() { 
    	m_vvbSessionIds.clear();
    	for(GridLoggedInUsersItem item : m_gridLoggedInUsers.getItems()) {
    		m_vvbSessionIds.addValidValue(item.getSessionID(), item.getUserName());
    	}
    	return m_vvbSessionIds; 
    }
    public void setVvbSessionIds(ValidValuesBinding value) { m_vvbSessionIds = value; }

    public void onSetSessionMessage(ActionEvent event) {
    	if(m_sessionId!=null && m_sessionId.length()>0 && m_sessionMessage!=null && m_sessionMessage.length()>0) {
    		MessageHandler.putSessionMessage(m_sessionId, m_sessionMessage);
    	}
    }

    protected String m_sessionId;
    public String getSessionId() { return m_sessionId; }
    public void setSessionId(String value) { m_sessionId = value; }

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
		
		public boolean getMessage() {
			return MessageHandler.isSessionMessageSet(session.getId());
		}
		
		public void invalidate() {
			session.invalidate();
			//UserTracker.deleteUser(session);
		}
    }

    public AdminPanelUI(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		buildGrid();
		buildLockedEntitiesGrid();
	}

	public void onRefresh(ActionEvent event) {
		buildGrid();
	}
	
	public void onRefreshLocks(ActionEvent event) {
		buildLockedEntitiesGrid();
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
				UserTracker.deleteUser(selected.session.getId());
				buildGrid();
			}    		
    	}
    }

    public int getCountUsers() { return UserTracker.getLoggedInusers(); }
    
    private void buildGrid() {
    	m_gridLoggedInUsers.getItems().clear();
    	Set<Entry<String, SUserInfo>> loggedInUserSet = UserTracker.getLoggedInUserMap().entrySet();
    	for(Entry<String, SUserInfo> entry : loggedInUserSet) {
    		m_gridLoggedInUsers.getItems().add(new GridLoggedInUsersItem(entry.getValue().user,entry.getValue().clientIP, entry.getValue().session));
    	}
    }
    
    private void buildLockedEntitiesGrid() {
    	m_gridLockedEntities.getItems().clear();
    	for(Entry<String, String> lock : LockManager.getEntityLockMap().entrySet()) {
    		List<Entities> entities = getDaoLayer().getEntitiesDAO().findByProperty(EntitiesDAO.ID, lock.getKey());
    		String entity = null;
    		if(entities!=null && entities.size()>0) entity = entities.get(0).getId().getEntity();  
    		SUserInfo userInfo = UserTracker.getUserInfo(lock.getValue());
    		String user = null;
    		if(userInfo!=null) user = userInfo.user;
    		GridLockedEntitiesItem item = new GridLockedEntitiesItem(lock.getKey(),entity, lock.getValue(),user);
    		m_gridLockedEntities.getItems().add(item);
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
