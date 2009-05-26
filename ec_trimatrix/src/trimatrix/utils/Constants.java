package trimatrix.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import trimatrix.entities.IEntityData;

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
		DOCTORDETAIL("/doctordetail.jsp"),
		USERSELECTION("/userselection.jsp"),
		PERSONSELECTION("/personselection.jsp"),
		RELATIONLIST("/relationlist.jsp"),
		CREATERELATION("/createrelation.jsp");
		private final String url;
		Page(String url) {
			this.url = url;
		}
		public String url(){ return url; }
	}	
	// all used entities
	public static enum Entity {
		// order is relevant, first all entities which are base entities
		USER(null, Page.USERDETAIL), 
		PERSON(null, Page.PERSONDETAIL), 
		DOCTOR(null, Page.DOCTORDETAIL),
		MYCOACHES(Entity.PERSON, Page.PERSONDETAIL), 
		MYATHLETES(Entity.PERSON, Page.PERSONDETAIL),
		MYDOCTORS(Entity.DOCTOR, Page.PERSONDETAIL);
		private final Entity baseEntity;
		private final Page detailPage;
		Entity(Entity baseEntity, Page detailPage) {
			this.baseEntity = baseEntity;
			this.detailPage = detailPage;
		}
		public Entity getBase() { 
			if(baseEntity==null) return this; 
			return baseEntity;
		};
		public Page getDetailPage() {
			return detailPage;
		}
	}
	// all relationtypes
	public static enum Relation {
		// order is relevant, first all relations which are base relations
		PERSONPERSON(Constants.Entity.PERSON, Constants.Entity.PERSON),
		PERSONDOCTOR(Constants.Entity.PERSON, Constants.Entity.DOCTOR),
		COACH("coach", PERSONPERSON),
		DOCTOR("doctor", PERSONDOCTOR);
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
		LANGUAGE, CURRENCY, LOGONLANGUAGE, SALUTATION, RELTYPS, COUNTRY	
	}	
	// all functionnodes
	public static enum FunctionNode {
		ATHLETES_OWN
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
}
