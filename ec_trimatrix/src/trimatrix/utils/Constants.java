  package trimatrix.utils;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import trimatrix.entities.IEntityData;
import trimatrix.relations.IRelationData;
import trimatrix.services.SQLExecutorService;
import trimatrix.structures.SGridMetaData;
import trimatrix.structures.SSearchMetaData;

public final class Constants {
	// all used jsp pages
	public static enum Page {
		WORKPLACE("/workplace.jsp","Workplace"),
		ADMINPANEL("/adminpanel.jsp","Amin Konsole"),
		PREFERENCESPANEL("/preferencespanel.jsp","Benutzereinstellungen"),
		LOGON("/logon.jsp", "Logon"),
		PASSWORD("/password.jsp","Passwort ändern"),
		ENTITYLIST("/entitylist.jsp","Entitäten"),
		ENTITYDETAIL("/entitydetail.jsp", "Entitätendetails"),
		USERDETAIL("/userdetail.jsp","Bentuzer"),
		PERSONDETAIL("/persondetail.jsp","Personen"),
		DOCTORDETAIL("/doctordetail.jsp","Ärzte"),
		TESTDETAIL("/testdetail.jsp","Tests"),
		COMPETITIONDETAIL("/competitiondetail.jsp","Wettkämpfe"),
		RESULTDETAIL("/resultdetail.jsp","Ergebnisse"),
		RESULTSIMPORT("/imports/results.jsp", "Import Ergebnisse"),
		ATTACHMENTDETAIL("/attachmentdetail.jsp","Anhänge"),
		ZONESDETAIL("/zonesdetail.jsp", "Trainingsbereiche"),
		ZONESDEFINITION("/zonesdefinition.jsp", "Trainingsbereiche"),
		USERSELECTION("/userselection.jsp", "Benutzersuche"),
		PERSONSELECTION("/personselection.jsp", "Personensuche"),
		DOCTORSELECTION("/doctorselection.jsp", "Ärztesuche"),
		TESTSELECTION("/testselection.jsp", "Testssuche"),
		COMPETITIONSELECTION("/competitionselection.jsp", "Wettkampfsuche"),
		ATTACHMENTSELECTION("/attachmentselection.jsp", "Anhangssuche"),
		RELATIONLIST("/relationlist.jsp", "Beziehungen"),
		CREATERELATION("/createrelation.jsp", "Beziehungsanlage"),
		LABELPOPUP("/labelpopup.jsp", "Labels"),
		LABELCHANGEPOPUP("/popups/labelchange.jsp", "Label ändern"),
		LABELSEARCHRESULT("/labelsearchresult.jsp", "Label Suchergebnis"),
		SCHEDULECHANGEPOPUP("/popups/schedulechange.jsp", "Termin ändern"),
		SCHEDULECOPYPOPUP("/popups/schedulecopy.jsp", "Termin kopieren"),
		TEMPLATECHANGEPOPUP("/popups/templatechange.jsp", "Vorlage ändern"),
		RESULTSLISTPOPUP("/popups/resultslist.jsp", "Ergebnisliste"),
		DAYINFOPOPUP("/popups/dayinfo.jsp", "Tagesinformation"),
		CALENDAR("/calendar.jsp", "Kalendar"),
		REPORTS("/reports.jsp", "Auswertungen");
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
		ZONE(Page.ZONESDETAIL, null, "zoneDetail"),
		ZONEDEFINITION(Page.ZONESDEFINITION, null, "zonesDefinition"),
		USER(Page.USERDETAIL, Page.USERSELECTION, "userEntity"),
		PERSON(Page.PERSONDETAIL, Page.PERSONSELECTION, "personEntity"),
		DOCTOR(Page.DOCTORDETAIL, Page.DOCTORSELECTION, "doctorEntity"),
		TEST(Page.TESTDETAIL, Page.TESTSELECTION, "testEntity", true),
		COMPETITION(Page.COMPETITIONDETAIL, Page.COMPETITIONSELECTION, "competitionEntity"),
		RESULT(Page.RESULTDETAIL, null, "resultEntity"),
		ATTACHMENT(Page.ATTACHMENTDETAIL, Page.ATTACHMENTSELECTION, "attachmentEntity"),
		SCHEDULE(null, null, "scheduleEntity"),
		MYCOACHES(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION, true),
		MYATHLETES(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION, false),
		MYSCOUTEDATHLETES(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION, false),
		SCOUTS(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION, false),
		ATHLETES(PERSON, Page.PERSONDETAIL, Page.PERSONSELECTION, false),
		MYDOCTORS(DOCTOR, Page.DOCTORDETAIL, Page.DOCTORSELECTION, true),
		MYTESTS(TEST, Page.TESTDETAIL, Page.TESTSELECTION, false),
		COACHTESTS(TEST, Page.TESTDETAIL, Page.TESTSELECTION, false),
		MYCOMPETITIONS(COMPETITION, Page.COMPETITIONDETAIL, Page.COMPETITIONSELECTION, false),
		SCOUTCOMPETITIONS(COMPETITION, Page.COMPETITIONDETAIL, Page.COMPETITIONSELECTION, false),
		SCOUTRESULTS(RESULT, Page.RESULTDETAIL, null, false),
		MYRESULTS(RESULT, Page.RESULTDETAIL, null, false),
		MYATTACHMENTS(ATTACHMENT, Page.ATTACHMENTDETAIL, Page.ATTACHMENTSELECTION, false);
		private final Entity baseEntity;
		private final Page detailPage;
		private final Page selectionPage;
		private final boolean hasStandard;
		private final String entityInstance;
		private final boolean noRelation;

		// constructor for base entities
		Entity(Page detailPage, Page selectionPage, String entityInstance) {
			this.baseEntity = null;
			this.detailPage = detailPage;
			this.selectionPage = selectionPage;
			this.hasStandard = false;
			this.noRelation = false;
			this.entityInstance = entityInstance;
		}

		Entity(Page detailPage, Page selectionPage, String entityInstance, boolean noRelation) {
			this.baseEntity = null;
			this.detailPage = detailPage;
			this.selectionPage = selectionPage;
			this.hasStandard = false;
			this.noRelation = noRelation;
			this.entityInstance = entityInstance;
		}


		// constructor for extended entities
		Entity(Entity baseEntity, Page detailPage, Page selectionPage, boolean hasStandard) {
			this.baseEntity = baseEntity;
			this.detailPage = detailPage;
			this.selectionPage = selectionPage;
			this.hasStandard = hasStandard;
			this.noRelation = false;
			this.entityInstance = null;
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
		public boolean hasStandard() {
			return hasStandard;
		}
		public String getEntityInstance() {
			return entityInstance;
		}

		public boolean noRelation() {
			return noRelation;
		}

		public String getDescription() {
			String description = Helper.getLiteral(getBase().entityInstance);
			if(Helper.isEmpty(description)) return Constants.EMPTY;
			// if not found the literal definition is returned and this always starts with a bracket
			if(description.startsWith("{")) return Constants.EMPTY;
			return description;
		}
	}
	// all relationtypes
	public static enum Relation {
		// order is relevant, first all relations which are base relations
		PERSONPERSON(Entity.PERSON, Entity.PERSON, "personPersonRelation"),
		PERSONDOCTOR(Entity.PERSON, Entity.DOCTOR, "personDoctorRelation"),
		PERSONATTACHMENT(Entity.PERSON, Entity.ATTACHMENT, "personAttachmentRelation"),
		PERSONCOMPETITION(Entity.PERSON, Entity.COMPETITION, "personCompetitionRelation"),
		COACH("coach", PERSONPERSON),
		SCOUT("scout", PERSONPERSON),
		DOCTOR("doctor", PERSONDOCTOR),
		ATTACHMENT("attachment", PERSONATTACHMENT),
		COMPETITION("comp", PERSONCOMPETITION),
		COMPETITIONSCOUT("comp_scout", PERSONCOMPETITION);
		private final String type;
		private final Relation baseRelation;
		private final Constants.Entity partner1;
		private final Constants.Entity partner2;
		private final String relationInstance;
		Relation(String type, Relation baseRelation) {
			this.type = type;
			this.baseRelation = baseRelation;
			this.partner1 = null;
			this.partner2 = null;
			this.relationInstance = null;
		}
		Relation(Constants.Entity partner1, Constants.Entity partner2, String relationInstance){
			this.type = null;
			this.baseRelation = null;
			this.partner1 = partner1;
			this.partner2 = partner2;
			this.relationInstance = relationInstance;
		}
		public String type(){ return type; }
		public Relation getBase(){
			if(baseRelation==null) return this;
			return baseRelation;
		};
		public Constants.Entity getPartner1(){
			if(baseRelation==null) return this.partner1;
			return baseRelation.partner1;
		};
		public Constants.Entity getPartner2(){
			if(baseRelation==null) return this.partner2;
			return baseRelation.partner2;
		};
		public String getRelationInstance() {
			return relationInstance;
		}
	}

	// all roles
	public static enum Role {
		ADMIN(0,"admin"), COACH(1,"coach"), ATHLETE(2,"athlete"), SCOUTER(3,"scouter");
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
		LANGUAGE(SQLExecutorService.LANGUAGEVALUELISTQUERY),
		CURRENCY(null),
		LOGONLANGUAGE(SQLExecutorService.LOGONLANGUAGEVALUELISTQUERY),
		SALUTATION(SQLExecutorService.SALUTATIONVALUELISTQUERY),
		RELTYPS(SQLExecutorService.RELTYPSVALUELISTQUERY),
		COUNTRY(SQLExecutorService.COUNTRYVALUELISTQUERY),
		CATEGORY(SQLExecutorService.CATEGORYVALUELISTQUERY),
		TESTTYPE(SQLExecutorService.TESTTYPEVALUELISTQUERY),
		COMPTYPE(SQLExecutorService.COMPTYPEVALUELISTQUERY),
		COMPSUBTYPE(SQLExecutorService.COMPSUBTYPEVALUELISTQUERY ),
		FUNCTIONS(null),
		MYATHLETES(null),
		MYATHLETESANDME(null),
		MYZONESDEFINITION(null),
		SCHEDULETYPE(SQLExecutorService.SCHEDULETYPEVALUELISTQUERY);
		private String namedQuery;
		ValueList(String namedQuery) {
			this.namedQuery = namedQuery;
		}
		public String getNamedQuery() { return namedQuery; }
	}
	// all functionnodes
	public static enum FunctionNode {
		MASTERDATA,
		ATHLETES_OWN, COACHES_OWN, DOCTORS_OWN, ATTACHMENTS_OWN, TESTS_OWN,	PERSON_OWN,	COMPETITIONS_OWN, SCOUTED_OWN,
		USERS_ALL, PERSONS_ALL, DOCTORS_ALL, ATTACHMENTS_ALL, TESTS_ALL, COMPETITIONS_ALL, RESULTS_ALL,
		RELATIONS, RELATION_COACH, RELATION_DOCTOR, RELATION_ATTACHMENT, RELATION_SCOUT,
		TESTS_COACH, ZONES_COACH,
		ZONES_ATHLETE, RESULTS_SCOUT,
		IMPORTS, RESULTS_IMPORT,
		CALENDAR, REPORTS
	}
	// all used profiles for persons
	public static enum Profiles {
		ATHLETE
	}
	// modes
	public static enum Mode {
		SHOW, CHANGE, NEW, COPY, SINGLECHANGE, FINAL
	}

	// authority objects
	public static enum AuthorityObject {
		ZONES
	}

	public static final String NULL = "null";
	public static final String EMPTY = "";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String EMPTYTIME = "00:00:00";
	public static final String EMPTYTIMESHORT = "00:00";
	public static final String WHITESPACE = " ";
	public static final String NEWLINE = "\n";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String CENTER = "center";
	public static final String P_ENTITY = "entity";
	public static final String P_ENTITYLIST = "entitylist";
	public static final String P_LIST = "list";
	public static final String P_PERSON = "person";
	public static final String P_USER = "user";
	public static final String P_LABEL= "label";
	public static final String P_MODE = "mode";
	public static final String P_SCOUTCOMPETITION = "scoutcompetition";
	public static final String P_ATHLETE = "athlete";
	public static final String P_FILTER = "filter";
	public static final String FINAL = "final:";

	public static final String CREATE = "create";
	public static final String CHANGE = "change";
	public static final String DELETE = "delete";
	public static final String ISRELATION = "isrelation";
	public static final String STANDARD = "standard";

	public static final String NO_FILTER = "no_filter";

	public static final String BGP_MANDATORY = "mandatory()";
	// constants for file extension
	public static final String GIF_EXTENSION = "gif";
	// constants for templates
	public static final String TEMPLATE_NEWPASSWORD = "mail/password";
	// constants for paths
	public static final String PATH_MIMEICONS = "/images/mimeicons/";
	public static final String PATH_ICONS = "/images/icons/";
	// constants for smtp
	public static final ResourceBundle MAIL_BUNDLE = ResourceBundle.getBundle("mail");
	public static final String SMTP_AUTH_USER = "smtp_auth_user";
	public static final String SMTP_AUTH_PASS = "smtp_auth_pass";
	public static final String SMTP_HOST_NAME = "smtp_host_name";
	public static final String EMAIL_FROM_ADDRESS = "email_from_address";
	public static final String TYPE_TEXTHTML = "text/html";
	// constants for images
	public static final String SIDE_EXPAND = PATH_ICONS + "application_side_expand.png";
	public static final String SIDE_CONTRACT = PATH_ICONS + "application_side_contract.png";
	public static final String ADD = PATH_ICONS + "add.png";
	public static final String DEFAULT_ICON = PATH_MIMEICONS + "default.gif";
	public static final String DAYINFO_TRUE = PATH_ICONS + "info_green.png";
	public static final String DAYINFO_FALSE = PATH_ICONS + "info_blue.png";
	public static final String ACCEPT = PATH_ICONS + "accept.png";
	public static final String ACCEPT_LIGHT = PATH_ICONS + "accept_light.png";
	// constants for sizes
	public static final Integer MB_1 = new Integer(1024*1024);
	public static final Integer KB_64 = new Integer(1024*64);

	// empty lists
	public static final List<IEntityData> EMPTYENTITYDATA = new ArrayList<IEntityData>();
	public static final List<SGridMetaData> EMPTYMETADATA =  new ArrayList<SGridMetaData>();
	public static final Map<String, SSearchMetaData> EMPTYSEARCHMETADATA =  new HashMap<String, SSearchMetaData>();
	public static final List<IRelationData> EMPTYRELATIONDATA = new ArrayList<IRelationData>();

	public static final String UNKNOWN_MIME_TYPE="application/x-unknown-mime-type";
	public static final String PDF_MIME_TYPE="application/pdf";

	public static final String FILESEPARATOR  = "/"; // seems Cpt.Casa also works with Unix style
	public static final String POINT = ".";

	// colors as string
	public static final String WHITE = "#FFFFFF";
	public static final String BLACK = "#000000";
	public static final String RED = "#FF0000";
	public static final String GREEN = "#00FF00";
	public static final String YELLOW = "#FFFF00";
	public static final String BLUE = "#0000FF";

	// UI parameter
	public static final String CLIENTNAME = "clientname";
	public static final String TEXT = "text";

	// Resources
	public static final String LITERALS = "literals";
	public static final String MESSAGES = "messages";

	// Fonts
	public static final Font FONT_SS_PLAIN_9 = new Font("SansSerif", Font.PLAIN, 9);

	// HTTP paremeters
	public static final String ECLNT_LANGUAGE = "eclnt-language";
	public static final String ECLNT_WIDTH = "eclnt-width";
	public static final String ECLNT_HEIGHT = "eclnt-height";
	public static final String ECLNT_IP = "eclnt-ip";

	// VVB
	public static final String EXP = "exp";
	public static final String POLY = "poly";
	public static final String SPLINE = "spline";
	public static final String NEVILLE = "neville";
	public static final String DIVDIFF = "divdiff";

	public static final String EXPONENTIAL = "Exponential";
	public static final String POLYNOMIAL = "Polynomial";
	public static final String SPLINEINTERPOLATION = "Cubic Spline";
	public static final String NEVILLEINTERPOLATION = "Neville Interpolation";
	public static final String DIVDIFFINTERPOLATION = "Divided Differences";

	// Global Workplace parameters
	public static final int MAXWORKPAGES = 10;

	// Server url
	//public static final String TRIMATRIXURL = "http://cardassia.secitec.net/trimatrix";

	// Animation type
	public static final String HIDETOLEFT = "hidetoleft";
	public static final String HIDETORIGHT = "hidetoright";

	public static final String VERSION = "0.8";
}
