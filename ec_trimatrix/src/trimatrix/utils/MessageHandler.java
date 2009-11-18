package trimatrix.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHandler {
	private static volatile boolean showLogonMessage;
	private static String logonMessage;	
	private static Map<String, String> sessionMessagesMap;
	
	static {		
		if(sessionMessagesMap==null) sessionMessagesMap = new ConcurrentHashMap<String, String>();
	}
	
	public static String getSessionMessage() {
		String session = Helper.getSession().getId();
		if(!sessionMessagesMap.containsKey(session)) return null;
		return sessionMessagesMap.remove(session);
	}
	
	public static boolean isSessionMessageSet(String sessionId) {
		return sessionMessagesMap.containsKey(sessionId);
	}
	
	public static void putSessionMessage(String sessionId, String message) {
		sessionMessagesMap.put(sessionId, message);
	}
	
	public static void clearSessionMessage() {
		sessionMessagesMap.clear();
	}
		
	public static boolean isShowLogonMessage() {
		return showLogonMessage;
	}
	public static void setShowLogonMessage(boolean showLogonMessage) {
		MessageHandler.showLogonMessage = showLogonMessage;
	}
	public static String getLogonMessage() {
		return logonMessage;
	}
	public synchronized static void setLogonMessage(String logonMessage) {
		MessageHandler.logonMessage = logonMessage;
	}	
}
