package trimatrix.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import trimatrix.db.DAOLayer;
import trimatrix.entities.AttachmentEntity;
import trimatrix.entities.DoctorEntity;
import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.TestEntity;
import trimatrix.entities.UserEntity;
import trimatrix.relations.IRelationData;
import trimatrix.relations.PersonAttachmentRelation;
import trimatrix.relations.PersonDoctorRelation;
import trimatrix.relations.PersonPersonRelation;
import trimatrix.structures.SFunctionTree;
import trimatrix.structures.SValueList;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

/**
 * @author Meex
 *
 */
public class SQLExecutorService {
	public static final Log logger = LogFactory.getLog(SQLExecutorService.class);
	
	public static final String ID = "ID";
	
	private static final String USERENTITYLISTQUERY = "UserEntityList";
	private static final String PERSONENTITYLISTQUERY = "PersonEntityList";
	private static final String DOCTORENTITYLISTQUERY = "DoctorEntityList";
	private static final String ATTACHMENTENTITYLISTQUERY = "AttachmentEntityList";
	private static final String TESTENTITYLISTQUERY = "TestEntityList";
	private static final String FUNCTIONTREEQUERY = "FunctionTree";
	private static final String LANGUAGEVALUELISTQUERY = "LanguageValueList";
	private static final String SALUTATIONVALUELISTQUERY = "SalutationValueList";
	private static final String LOGONLANGUAGEVALUELISTQUERY = "LogonLanguageValueList";
	private static final String PERSONRELATIONENTITYQUERY = "PersonRelationEntityList";
	private static final String DOCTORRELATIONENTITYQUERY = "DoctorRelationEntityList";
	private static final String ATTACHMENTRELATIONENTITYQUERY = "AttachmentRelationEntityList";
	private static final String PERSONPERSONQUERY = "PersonPersonRelationList";
	private static final String PERSONDOCTORQUERY = "PersonDoctorRelationList";
	private static final String PERSONATTACHMENTQUERY = "PersonAttachmentRelationList";
	private static final String RELTYPSVALUELISTQUERY = "RelTypsValueList";
	private static final String COUNTRYVALUELISTQUERY = "CountryValueList";
	private static final String CATEGORYVALUELISTQUERY = "CategoryValueList";
	private static final String TESTTYPEVALUELISTQUERY = "TestTypeValueList";
	private static final String ENTITIESBYLABELLISTQUERY = "EntitiesByLabelList";
	
	private HibernateTransactionManager transactionManager;
	private Dictionary dictionaryService;
	private DAOLayer daoLayer;
	
	/**
	 * Retrieve functiontree for workplace
	 * @param role_key	role of user
	 * @param lang_key	language
	 * @return functiontree
	 */
	@SuppressWarnings("unchecked")
	public List<SFunctionTree> getFunctionTree(Constants.Role role, String lang_key) {
		List<SFunctionTree> data = new ArrayList<SFunctionTree>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(FUNCTIONTREEQUERY);
		query.setString("p_role_key", role.getName());
		query.setString("p_lang_key", lang_key);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			SFunctionTree datum = new SFunctionTree();
			int i = 0;
			datum.node = (Integer)line[i++];
			datum.parent = (Integer)line[i++];
			datum.order = (Integer)line[i++];
			try {
				datum.key = Constants.FunctionNode.valueOf(((String)line[i++]).toUpperCase());
			} catch (Exception ex) {
				logger.warn(ex.getMessage());
			}		 
			datum.page = (String)line[i++];
			datum.entity = (String)line[i++];
			datum.edit = (Boolean)line[i++];
			datum.create = (Boolean)line[i++];
			datum.delete = (Boolean)line[i++];
			datum.description = (String)line[i++];
			datum.description_long = (String)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<SFunctionTree> getFunctionTree(Constants.Role role) {
		return getFunctionTree(role, dictionaryService.getLanguage());
	}
	
	/**
	 * Retrieve user entities
	 * @param lang_key	language
	 * @param deleted	show deleted
	 * @return user entities
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getUserEntities(String lang_key, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(USERENTITYLISTQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			UserEntity.Data datum = new UserEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];
			datum.user_name = (String)line[i++];
			datum.email = (String)line[i++];	
			datum.language = (String)line[i++];
			datum.currency = (String)line[i++];	
			datum.person = (String)line[i++];
			datum.locked = (Boolean)line[i++];
			datum.initial = (Boolean)line[i++];
			datum.active = (Boolean)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getUserEntities() {
		return getUserEntities(dictionaryService.getLanguage(), false, false);
	}
	
	/**
	 * Retrieve person entities
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return person entities
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getPersonEntities(String lang_key, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(PERSONENTITYLISTQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			PersonEntity.Data datum = new PersonEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];
			datum.salutation = (String)line[i++];
			datum.name_first = (String)line[i++];
			datum.name_last = (String)line[i++];
			datum.email = (String)line[i++];
			datum.sex = (String)line[i++];
			datum.birthdate = (Timestamp)line[i++];
			datum.street = (String)line[i++];
			datum.housenumber = (String)line[i++];
			datum.postcode = (String)line[i++];
			datum.city = (String)line[i++];
			datum.state = (String)line[i++];
			datum.country = (String)line[i++];
			datum.homepage = (String)line[i++];
			datum.telephone = (String)line[i++];
			datum.mobile = (String)line[i++];
			datum.fax = (String)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getPersonEntities() {
		return getPersonEntities(dictionaryService.getLanguage(), false, false);
	}
	
	/**
	 * Retrieve doctor entities
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return doctor entities
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getDoctorEntities(String lang_key, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(DOCTORENTITYLISTQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			DoctorEntity.Data datum = new DoctorEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];		
			datum.name = (String)line[i++];
			datum.street = (String)line[i++];
			datum.housenumber = (String)line[i++];
			datum.postcode = (String)line[i++];
			datum.city = (String)line[i++];
			datum.state = (String)line[i++];
			datum.country = (String)line[i++];
			datum.email = (String)line[i++];
			datum.homepage = (String)line[i++];
			datum.telephone = (String)line[i++];
			datum.mobile = (String)line[i++];
			datum.fax = (String)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getDoctorEntities() {
		return getDoctorEntities(dictionaryService.getLanguage(), false, false);
	}
	
	/**
	 * Retrieve attachment entities
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return attachment entities
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getAttachmentEntities(String lang_key, String parameterName, String parameterValue, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(ATTACHMENTENTITYLISTQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		// handle parameter
		query.setBoolean("p_id_on", false);
		query.setString("p_id", null);
		if (ID.equals(parameterName)) {
			query.setBoolean("p_id_on", true);
			query.setString("p_id", parameterValue);
		}
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			AttachmentEntity.Data datum = new AttachmentEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];	
			datum.category = (String)line[i++];
			datum.description = (String)line[i++];
			datum.owner = (String)line[i++];
			datum.mimetype = (String)line[i++];
			datum.filename = (String)line[i++];
			datum.filesize = (Integer)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getAttachmentEntities() {
		return getAttachmentEntities(dictionaryService.getLanguage(), null, null, false, false);
	}
	
	public List<IEntityData> getAttachmentEntities(String parameterName, String parameterValue) {
		return getAttachmentEntities(dictionaryService.getLanguage(), parameterName, parameterValue, false, false);
	}
	
	/**
	 * Retrieve test entities
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return test entities
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getTestEntities(String lang_key, String parameterName, String parameterValue, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(TESTENTITYLISTQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		// handle parameter
		query.setBoolean("p_person_on", false);
		query.setBoolean("p_coach_on", false);
		query.setBoolean("p_id_on", false);
		query.setString("p_person", null);
		query.setString("p_coach", null);
		query.setString("p_id", null);
		if (TestEntity.PERSON.equals(parameterName)) {
			query.setBoolean("p_person_on", true);
			query.setString("p_person", parameterValue);
		}
		if (TestEntity.COACH.equals(parameterName)) {
			query.setBoolean("p_coach_on", true);
			query.setString("p_coach", parameterValue);
		}
		if (ID.equals(parameterName)) {
			query.setBoolean("p_id_on", true);
			query.setString("p_id", parameterValue);
		}
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			TestEntity.Data datum = new TestEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];		
			datum.person = (String)line[i++];
			datum.doctor = (String)line[i++];
			datum.coach = (String)line[i++];
			datum.type = (String)line[i++];
			datum.date = (Timestamp)line[i++];
			datum.description = (String)line[i++];
			datum.protocol = (Boolean)line[i++];	
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getTestEntities() {
		return getTestEntities(dictionaryService.getLanguage(), null, null, false, false);
	}
	
	public List<IEntityData> getTestEntities(String parameterName, String parameterValue) {
		return getTestEntities(dictionaryService.getLanguage(), parameterName, parameterValue, false, false);
	}
	
	/**
	 * Retrieve persons in a certain relationship
	 * @param person_id
	 * @param relation
	 * @param inverse
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getPersonRelationEntities(String person_id, Constants.Relation relation, boolean inverse, String lang_key, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(PERSONRELATIONENTITYQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		query.setString("p_reltyp", relation.type());
		if (inverse) {
			query.setString("p_partner1", person_id);
			query.setString("p_partner2", null);
		} else {
			query.setString("p_partner1", null);
			query.setString("p_partner2", person_id);
		}
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			PersonEntity.Data datum = new PersonEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];		
			datum.salutation = (String)line[i++];
			datum.name_first = (String)line[i++];
			datum.name_last = (String)line[i++];
			datum.email = (String)line[i++];
			datum.sex = (String)line[i++];
			datum.birthdate = (Timestamp)line[i++];	
			datum.street = (String)line[i++];
			datum.housenumber = (String)line[i++];
			datum.postcode = (String)line[i++];
			datum.city = (String)line[i++];
			datum.state = (String)line[i++];
			datum.country = (String)line[i++];
			datum.homepage = (String)line[i++];
			datum.telephone = (String)line[i++];
			datum.mobile = (String)line[i++];
			datum.fax = (String)line[i++];
			datum.standard = (Boolean)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getPersonRelationEntities(String person_id, Constants.Relation relation, boolean inverse) {
		return getPersonRelationEntities(person_id, relation, inverse, dictionaryService.getLanguage(), false, false);
	}	
			
	/**
	 * Return person to person relations
	 * @param relation
	 * @param lang_key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IRelationData> getPersonPersonRelation(Constants.Relation relation, String lang_key) {
		List<IRelationData> data = new ArrayList<IRelationData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(PERSONPERSONQUERY);
		query.setString("p_lang_key", lang_key);
		query.setString("p_reltyp", relation.type());
		// when base Relation then return all
		if (relation == Constants.Relation.PERSONPERSON) {
			query.setInteger("p_dummy",1);
		} else {
			query.setInteger("p_dummy", 0);
		}
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			PersonPersonRelation.Data datum = new PersonPersonRelation.Data();
			int i = 0;
			datum.id = (String)line[i++];
			datum.partner1 = daoLayer.getPersonsDAO().findById((String)line[i++]);
			datum.description = (String)line[i++];
			datum.description_inverse = (String)line[i++];
			datum.standard = (Boolean)line[i++];
			datum.reltyp = (String)line[i++];
			datum.partner2 = daoLayer.getPersonsDAO().findById((String)line[i++]);			
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IRelationData> getPersonPersonRelation(Constants.Relation relation) {
		return getPersonPersonRelation(relation, dictionaryService.getLanguage());
	}	
	
	/**
	 * Retrieve doctors in a certain relationship
	 * @param partner_id
	 * @param relation
	 * @param inverse
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getDoctorRelationEntities(String person_id, Constants.Relation relation, String lang_key, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(DOCTORRELATIONENTITYQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		query.setString("p_reltyp", relation.type());
		query.setString("p_person", person_id);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			DoctorEntity.Data datum = new DoctorEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];		
			datum.name = (String)line[i++];
			datum.street = (String)line[i++];
			datum.housenumber = (String)line[i++];
			datum.postcode = (String)line[i++];
			datum.city = (String)line[i++];
			datum.state = (String)line[i++];
			datum.country = (String)line[i++];
			datum.email = (String)line[i++];
			datum.homepage = (String)line[i++];
			datum.telephone = (String)line[i++];
			datum.mobile = (String)line[i++];
			datum.fax = (String)line[i++];
			datum.standard = (Boolean)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getDoctorRelationEntities(String person_id, Constants.Relation relation) {
		return getDoctorRelationEntities(person_id, relation, dictionaryService.getLanguage(), false, false);
	}	
	
	/**
	 * Retrieve attachments in a certain relationship
	 * @param partner_id
	 * @param relation
	 * @param inverse
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IEntityData> getAttachmentRelationEntities(String person_id, Constants.Relation relation, String lang_key, boolean deleted, boolean test) {
		List<IEntityData> data = new ArrayList<IEntityData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(ATTACHMENTRELATIONENTITYQUERY);
		query.setString("p_lang_key", lang_key);
		query.setBoolean("p_deleted", deleted);
		query.setBoolean("p_test", test);
		query.setString("p_reltyp", relation.type());
		query.setString("p_person", person_id);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			AttachmentEntity.Data datum = new AttachmentEntity.Data();
			int i = 0;
			datum.id = (String)line[i++];	
			datum.category = (String)line[i++];
			datum.description = (String)line[i++];
			datum.owner = (String)line[i++];
			datum.mimetype = (String)line[i++];
			datum.filename = (String)line[i++];
			datum.filesize = (Integer)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IEntityData> getAttachmentRelationEntities(String person_id, Constants.Relation relation) {
		return getAttachmentRelationEntities(person_id, relation, dictionaryService.getLanguage(), false, false);
	}
	
	/**
	 * Return person to doctor relations
	 * @param relation
	 * @param lang_key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IRelationData> getPersonDoctorRelation(Constants.Relation relation, String lang_key) {
		List<IRelationData> data = new ArrayList<IRelationData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(PERSONDOCTORQUERY);
		query.setString("p_lang_key", lang_key);
		query.setString("p_reltyp", relation.type());
		// when base Relation then return all
		if (relation == Constants.Relation.PERSONDOCTOR) {
			query.setInteger("p_dummy",1);
		} else {
			query.setInteger("p_dummy", 0);
		}
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			PersonDoctorRelation.Data datum = new PersonDoctorRelation.Data();
			int i = 0;
			datum.id = (String)line[i++];
			datum.person = daoLayer.getPersonsDAO().findById((String)line[i++]);
			datum.description = (String)line[i++];
			datum.description_inverse = (String)line[i++];
			datum.standard = (Boolean)line[i++];
			datum.reltyp = (String)line[i++];
			datum.doctor = daoLayer.getDoctorsDAO().findById((String)line[i++]);			
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IRelationData> getPersonDoctorRelation(Constants.Relation relation) {
		return getPersonDoctorRelation(relation, dictionaryService.getLanguage());
	}	
	
	/**
	 * Return person to doctor relations
	 * @param relation
	 * @param lang_key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<IRelationData> getPersonAttachmentRelation(Constants.Relation relation, String lang_key) {
		List<IRelationData> data = new ArrayList<IRelationData>();
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery(PERSONATTACHMENTQUERY);
		query.setString("p_lang_key", lang_key);
		query.setString("p_reltyp", relation.type());
		// when base Relation then return all
		if (relation == Constants.Relation.PERSONATTACHMENT) {
			query.setInteger("p_dummy",1);
		} else {
			query.setInteger("p_dummy", 0);
		}
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			PersonAttachmentRelation.Data datum = new PersonAttachmentRelation.Data();
			int i = 0;
			datum.id = (String)line[i++];
			datum.person = daoLayer.getPersonsDAO().findById((String)line[i++]);
			datum.description = (String)line[i++];
			datum.description_inverse = (String)line[i++];
			datum.standard = (Boolean)line[i++];
			datum.reltyp = (String)line[i++];
			datum.attachment = daoLayer.getAttachmentsDAO().findById((String)line[i++]);			
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	public List<IRelationData> getPersonAttachmentRelation(Constants.Relation relation) {
		return getPersonAttachmentRelation(relation, dictionaryService.getLanguage());
	}	
	
	/**
	 * Retrieve value list
	 * @param valueList
	 * @param lang_key
	 * @return			
	 */
	@SuppressWarnings("unchecked")
	public List<SValueList> getValueList(Constants.ValueList valueList, String lang_key) {
		List<SValueList> list = new ArrayList<SValueList>();
		String namedQuery = null;
		switch (valueList) {
		case LANGUAGE:
			namedQuery = LANGUAGEVALUELISTQUERY;
			break;
		case LOGONLANGUAGE:
			namedQuery = LOGONLANGUAGEVALUELISTQUERY;
			break;
		case SALUTATION:
			namedQuery = SALUTATIONVALUELISTQUERY;
			break;
		case RELTYPS:
			namedQuery = RELTYPSVALUELISTQUERY;
			break;
		case COUNTRY:
			namedQuery = COUNTRYVALUELISTQUERY;
			break;
		case CATEGORY:
			namedQuery = CATEGORYVALUELISTQUERY;
			break;
		case TESTTYPE:
			namedQuery = TESTTYPEVALUELISTQUERY;
			break;	
		default:
			logger.warn("Valuelist not found: " + valueList.name());
			return list;
		}		
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();		
		Query query = session.getNamedQuery(namedQuery);
		query.setString("p_lang_key", lang_key);
		List<Object[]> result = query.list();
		for(Object[] line : result) {
			SValueList entry = new SValueList();
			int i = 0;
			entry.key = (String)line[i++];
			entry.description = (String)line[i++];
			entry.description_long = (String)line[i++];
			list.add(entry);
		}
		session.close();
		return list;
	}
	
	/**
	 * Retrieve all entities and ids for a certain label
	 * @param labelId Label
	 * @param deleted deleted
	 * @return entities which have the label assigned	
	 */
	public Map<Constants.Entity, List<String>> getEntitiesByLabelList(String labelId, boolean deleted) {
		Map<Constants.Entity, List<String>> map = new HashMap<Constants.Entity, List<String>>();		
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();		
		Query query = session.getNamedQuery(ENTITIESBYLABELLISTQUERY);
		query.setString("p_label_id", labelId);
		query.setBoolean("p_deleted", deleted);
		List<Object[]> result = query.list();
		for(Object[] line : result) {			
			try {
				Constants.Entity entity = Constants.Entity.valueOf(((String)line[0]).toUpperCase());
				String entityId = (String)line[1];
				// check if entity already in map
				if(!map.containsKey(entity)) map.put(entity, new ArrayList<String>());
				// add id
				map.get(entity).add(entityId);	
			} catch (Exception ex) {			
				Statusbar.outputError("No or wrong entity", "For list view processing an entity has to be set!");
				continue;
			}
					
		}
		session.close();
		return map;
	}
	
	public Map<Constants.Entity, List<String>> getEntitiesByLabelList(String labelId) {
		return getEntitiesByLabelList(labelId, false); 
	}

	public int deleteAllSwimProtocols(String id) {
		SessionFactory sessionFactory = transactionManager.getSessionFactory();
		Session session = sessionFactory.openSession();		
		Query query = session.createQuery("delete TestsSwimProtocol where id.id = :p_id");
		query.setString("p_id", id);
		return query.executeUpdate (); 		
	}

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}	
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}	

	public void setDaoLayer(DAOLayer daoLayer) {
		this.daoLayer = daoLayer;
	}

	public static SQLExecutorService getFromApplicationContext(ApplicationContext ctx) {
		return (SQLExecutorService) ctx.getBean("sqlExecutorService");
	}
}
