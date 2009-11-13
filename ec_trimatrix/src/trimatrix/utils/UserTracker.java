package trimatrix.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import trimatrix.structures.SUserInfo;

public class UserTracker {
	public static final Log logger = LogFactory.getLog(UserTracker.class);
	private static Map<HttpSession, SUserInfo> loggedInUserMap;
	
	static {
		loggedInUserMap = new HashMap<HttpSession, SUserInfo>();
	}

	public static void addUser(String username) {
		HttpSession session = Helper.getSession();
		if(loggedInUserMap.containsKey(session)) {
			logger.error(session.getId() + " : This session is already in use!");
			return;
		} 
		loggedInUserMap.put(session, new SUserInfo(username, Helper.getClientIP()));
	}

	public static void deleteUser(HttpSession session) {
		if(loggedInUserMap.containsKey(session)) {
			loggedInUserMap.remove(session);
		}		
	}

	public static Integer getLoggedInusers() {
		return loggedInUserMap.size();
	}
	
	public static Map<HttpSession, SUserInfo> getLoggedInUserMap() {
		return loggedInUserMap;
	}
	
}
