package trimatrix.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import trimatrix.entities.IEntityData;
import trimatrix.entities.PersonEntity;
import trimatrix.entities.UserEntity;
import trimatrix.structures.SFunctionTree;
import trimatrix.structures.SValueList;
import trimatrix.utils.Constants;
import trimatrix.utils.Dictionary;

/**
 * @author Meex
 *
 */
public class SQLExecutorService {
	private static final String USERENTITYLISTQUERY = "UserEntityList";
	private static final String PERSONENTITYLISTQUERY = "PersonEntityList";
	private static final String FUNCTIONTREEQUERY = "FunctionTree";
	private static final String LANGUAGEVALUELISTQUERY = "LanguageValueList";
	private static final String LOGONLANGUAGEVALUELISTQUERY = "LogonLanguageValueList";
	private static final String PERSONRELATIONQUERY = "PersonRelationEntityList";
	
	private HibernateTransactionManager transactionManager;
	private Dictionary dictionaryService;
	
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
				Dictionary.logger.warn(ex.getMessage());
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
	 * Retrieve user entities
	 * @param lang_key
	 * @param deleted
	 * @param test
	 * @return
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
			datum.name_first = (String)line[i++];
			datum.name_last = (String)line[i++];
			datum.email = (String)line[i++];
			datum.sex = (String)line[i++];
			datum.birthdate = (Timestamp)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
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
		Query query = session.getNamedQuery(PERSONRELATIONQUERY);
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
			datum.name_first = (String)line[i++];
			datum.name_last = (String)line[i++];
			datum.email = (String)line[i++];
			datum.sex = (String)line[i++];
			datum.birthdate = (Timestamp)line[i++];
			data.add(datum);
		}
		session.close();
		return data;
	}
	
	
	public List<IEntityData> getPersonEntities() {
		return getPersonEntities(dictionaryService.getLanguage(), false, false);
	}
	
	public List<IEntityData> getPersonRelationEntities(String person_id, Constants.Relation relation, boolean inverse) {
		return getPersonRelationEntities(person_id, relation, inverse, dictionaryService.getLanguage(), false, false);
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
		if(valueList==Constants.ValueList.LANGUAGE) {
			namedQuery = LANGUAGEVALUELISTQUERY;
		} else if (valueList==Constants.ValueList.LOGONLANGUAGE) {
			namedQuery = LOGONLANGUAGEVALUELISTQUERY;
		} else {
			Dictionary.logger.warn("Valuelist not found: " + valueList.name());
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

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}	
	
	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public static SQLExecutorService getFromApplicationContext(ApplicationContext ctx) {
		return (SQLExecutorService) ctx.getBean("sqlExecutorService");
	}
}
