package trimatrix.utils;

public class MessageHandler {
	static boolean showLogonMessage;
	static String logonMessage;
	
	public static boolean isShowLogonMessage() {
		return showLogonMessage;
	}
	public static void setShowLogonMessage(boolean showLogonMessage) {
		MessageHandler.showLogonMessage = showLogonMessage;
	}
	public static String getLogonMessage() {
		return logonMessage;
	}
	public static void setLogonMessage(String logonMessage) {
		MessageHandler.logonMessage = logonMessage;
	}	
}
