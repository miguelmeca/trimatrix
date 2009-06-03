package trimatrix.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import trimatrix.entities.IEntityData;

public final class Constants {
	// all used jsp pages
	public static enum Page {
		WORKPLACE("/workplace.jsp","Workplace"),
		LOGON("/logon.jsp", "Logon"),
		PASSWORD("/password.jsp","Passwort ändern"),
		ENTITYLIST("/entitylist.jsp","Entitäten"),
		ENTITYDETAIL("/entitydetail.jsp", "Entitätendetails"),
		USERDETAIL("/userdetail.jsp","Bentuzer"),
		PERSONDETAIL("/persondetail.jsp","Personen"),
		DOCTORDETAIL("/doctordetail.jsp","Ärzte"),
		ATTACHMENTDETAIL("/attachmentdetail.jsp","Anhänge"),
		USERSELECTION("/userselection.jsp", "Benutzersuche"),
		PERSONSELECTION("/personselection.jsp", "Personensuche"),
		DOCTORSELECTION("/doctorselection.jsp", "Ärztesuche"),
		ATTACHMENTSELECTION("/attachmentselection.jsp", "Anhangssuche"),
		RELATIONLIST("/relationlist.jsp", "Beziehungen"),
		CREATERELATION("/createrelation.jsp", "Beziehungsanlage");
		private final String url;
		private final String description;
		Page(String url, String description) {
			this.url = url;
			this.description = description;
		}
		public String getUrl(){ return url; }
		public String getDescription(){ return description; }
	}	
	// all used entities
	public static enum Entity {
		// order is relevant, first all entities which are base entities
		USER(null, Page.USERDETAIL, Page.USERSELECTION), 
		PERSON(null, Page.PERSONDETAIL, Page.PERSONSELECTION), 
		DOCTOR(null, Page.DOCTORDETAIL, Page.DOCTORSELECTION),
		ATTACHMENT(null, Page.ATTACHMENTDETAIL, Page.ATTACHMENTSELECTION),
		MYCOACHES(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION), 
		MYATHLETES(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION),
		MYDOCTORS(DOCTOR, Page.DOCTORDETAIL, Page.DOCTORSELECTION),
		MYATTACHMENTS(ATTACHMENT, Page.ATTACHMENTDETAIL, Page.ATTACHMENTSELECTION);
		private final Entity baseEntity;
		private final Page detailPage;
		private final Page selectionPage;
		Entity(Entity baseEntity, Page detailPage, Page selectionPage) {
			this.baseEntity = baseEntity;
			this.detailPage = detailPage;
			this.selectionPage = selectionPage;
		}
		public Entity getBase() { 
			if(baseEntity==null) return this; 
			return baseEntity;
		};
		public Page getDetailPage() {
			return detailPage;
		}
		public Page getSelectionPage() {
			return selectionPage;
		}
	}
	// all relationtypes
	public static enum Relation {
		// order is relevant, first all relations which are base relations
		PERSONPERSON(Entity.PERSON, Entity.PERSON),
		PERSONDOCTOR(Entity.PERSON, Entity.DOCTOR),
		PERSONATTACHMENT(Entity.PERSON, Entity.ATTACHMENT),
		COACH("coach", PERSONPERSON),
		DOCTOR("doctor", PERSONDOCTOR),
		ATTACHMENT("attachment", PERSONATTACHMENT);
		private final String type;
		private final Relation baseRelation;
		private final Constants.Entity partner1;
		private final Constants.Entity partner2;
		Relation(String type, Relation baseRelation) {
			this.type = type;
			this.baseRelation = baseRelation;
			this.partner1 = null;
			this.partner2 = null;
		}
		Relation(Constants.Entity partner1, Constants.Entity partner2){
			this.type = null;
			this.baseRelation = null;
			this.partner1 = partner1;
			this.partner2 = partner2;
		}
		public String type(){ return type; }
		public Relation getBase(){ 
			if(baseRelation==null) return this; 
			return baseRelation;
		};
		public Constants.Entity getParnter1(){ 
			if(baseRelation==null) return this.partner1; 
			return baseRelation.partner1;
		};
		public Constants.Entity getParnter2(){ 
			if(baseRelation==null) return this.partner2; 
			return baseRelation.partner2;
		};
	}
	// all roles
	public static enum Role {
		ADMIN(0,"admin"), COACH(1,"coach"), ATHLETE(2,"athlete");
		private int id;
		private String name;
		Role(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int getId() { return id; }
		public String getName() { return name; }
	}
	// all value lists
	public static enum ValueList {
		LANGUAGE, CURRENCY, LOGONLANGUAGE, SALUTATION, RELTYPS, COUNTRY, CATEGORY
	}	
	// all functionnodes
	public static enum FunctionNode {
		ATHLETES_OWN, COACHES_OWN, DOCTORS_OWN, ATTACHMENTS_OWN
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
	
	public static final String CREATE = "create";
	public static final String CHANGE = "change";
	public static final String DELETE = "delete";
		
	public static final String BGP_MANDATORY = "mandatory()";	
	// constants for smtp	
	public static final ResourceBundle MAIL_BUNDLE = ResourceBundle.getBundle("mail");	
	public static final String SMTP_AUTH_USER = "smtp_auth_user";
	public static final String SMTP_AUTH_PASS = "smtp_auth_pass";
	public static final String SMTP_HOST_NAME = "smtp_host_name";
	public static final String EMAIL_FROM_ADDRESS = "email_from_address";	
	// constants for images
	public static final String SIDE_EXPAND = "/images/icons/application_side_expand.png";
	public static final String SIDE_CONTRACT = "/images/icons/application_side_contract.png";
	
	public static final List<IEntityData> EMPTYENTITYLIST = new ArrayList<IEntityData>();
	
	public static final String UNKNOWN_MIME_TYPE="application/x-unknown-mime-type";

	public static final String FILESEPARATOR  = "/"; // seems Cpt.Casa also works with Unix style
}
