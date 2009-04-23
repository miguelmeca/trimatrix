package trimatrix.utils;

import java.util.ResourceBundle;

public final class Constants {
	// all used jsp pages
	public static enum Page {
		WORKPLACE("/workplace.jsp"),
		LOGON("/logon.jsp"),
		PASSWORD("/password.jsp"),
		ENTITYLIST("/entitylist.jsp"),
		ENTITYDETAIL("/entitydetail.jsp"),
		USERDETAIL("/userdetail.jsp"),
		PERSONDETAIL("/persondetail.jsp"),
		USERSELECTION("/userselection.jsp"),
		PERSONSELECTION("/personselection.jsp");
		private final String url;
		Page(String url) {
			this.url = url;
		}
		public String url(){ return url; }
	}
	// all used entities
	public static enum Entity {
		USER, PERSON
	}
	// all value lists
	public static enum ValueList {
		LANGUAGE, CURRENCY
	}
	
	// modes
	public static enum Mode {
		SHOW, CHANGE, NEW
	}
	
	public static final String NULL = "null";
	public static final String EMPTY = "";
	public static final String TRUE = "true";	
	public static final String FALSE = "false";
	public static final String CENTER = "center";
	public static final String P_ENTITY = "entity";
	public static final String P_MODE = "mode";
	
	
	public static final String BGP_MANDATORY = "mandatory()";
	
	public static final ResourceBundle MAIL_BUNDLE = ResourceBundle.getBundle("mail");	
	public static final String SMTP_AUTH_USER = "smtp_auth_user";
	public static final String SMTP_AUTH_PASS = "smtp_auth_pass";
	public static final String SMTP_HOST_NAME = "smtp_host_name";
	public static final String EMAIL_FROM_ADDRESS = "email_from_address";	
}
