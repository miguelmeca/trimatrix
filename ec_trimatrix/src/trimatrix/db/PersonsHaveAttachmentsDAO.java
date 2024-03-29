package trimatrix.db;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import trimatrix.relations.IRelationObject;

/**
 * A data access object (DAO) providing persistence and search support for
 * PersonsHaveAttachments entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.PersonsHaveAttachments
 * @author MyEclipse Persistence Tools
 */

public class PersonsHaveAttachmentsDAO extends HibernateDaoSupport implements IRelationDAO<PersonsHaveAttachments> {
	// property constants
	public static final String PERSON = "person";
	public static final String ATTACHMENT = "attachment";
	public static final String RELTYP_KEY = "reltypKey";
	public static final String STANDARD = "standard";
	
	private static final Log log = LogFactory
			.getLog(PersonsHaveAttachmentsDAO.class);
	@Override
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#save(trimatrix.db.PersonsHaveAttachments)
	 */
	public void save(PersonsHaveAttachments transientInstance) {
		log.debug("saving PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#delete(trimatrix.db.PersonsHaveAttachments)
	 */
	public void delete(IRelationObject persistentInstance) {
		log.debug("deleting PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findById(java.lang.String)
	 */
	public PersonsHaveAttachments findById(java.lang.String id) {
		log.debug("getting PersonsHaveAttachments instance with id: " + id);
		try {
			PersonsHaveAttachments instance = (PersonsHaveAttachments) getHibernateTemplate()
					.get("trimatrix.db.PersonsHaveAttachments", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByExample(trimatrix.db.PersonsHaveAttachments)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findByExample(IRelationObject instance) {
		log.debug("finding PersonsHaveAttachments instance by example");
		try {
			List<PersonsHaveAttachments> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findByProperty(String propertyName, Object value) {
		log.debug("finding PersonsHaveAttachments instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PersonsHaveAttachments as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IRelationDAO#findByProperties(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findByProperties(Map<String, Object> properties) {
		log.debug("finding PersonsHaveAttachments instance with properties");
		try {
			StringBuffer queryString = new StringBuffer("from PersonsHaveAttachments as model where");
			StringBuffer whereString = new StringBuffer();
			for(String key : properties.keySet()) {
				if(whereString.length()==0) {
					whereString.append(" where");
				} else {
					whereString.append(" and");
				}
				whereString.append(" model." + key + "=?");
			}			
			queryString.append(whereString);
			queryString.append(" order by model.standard desc");
			return getHibernateTemplate().find(queryString.toString(), properties.values().toArray());
		} catch (RuntimeException re) {
			log.error("find by properties name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveAttachments> findAll() {
		log.debug("finding all PersonsHaveAttachments instances");
		try {
			String queryString = "from PersonsHaveAttachments";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#merge(trimatrix.db.PersonsHaveAttachments)
	 */
	public PersonsHaveAttachments merge(IRelationObject detachedInstance) {
		log.debug("merging PersonsHaveAttachments instance");
		try {
			PersonsHaveAttachments result = (PersonsHaveAttachments) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#attachDirty(trimatrix.db.PersonsHaveAttachments)
	 */
	public void attachDirty(PersonsHaveAttachments instance) {
		log.debug("attaching dirty PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#attachClean(trimatrix.db.PersonsHaveAttachments)
	 */
	public void attachClean(PersonsHaveAttachments instance) {
		log.debug("attaching clean PersonsHaveAttachments instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveAttachmentsDAO#reload(trimatrix.db.PersonsHaveAttachments)
	 */
	public void reload(IRelationObject relation) {
		String id = relation.getId();
		log.debug("reloading PersonsHaveAttachments instance with id: " + id);
		try {
			getHibernateTemplate().load(relation, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public int deleteByPartners(String partnerId) {
		int count = 0;
    	log.debug("finding PersonsHaveAttachments instance with partner1 or partner2 : "+ partnerId);
          try {
             String queryString = "from PersonsHaveAttachments as model where model.person = '" + partnerId + "' or model.attachment = '" + partnerId + "'";
             List<PersonsHaveAttachments> relations = getHibernateTemplate().find(queryString);
             // delete instance
             for (PersonsHaveAttachments relation : relations) {
            	 delete(relation);
            	 count++;
             }
          } catch (RuntimeException re) {
             log.error("delete by partners failed", re);
             throw re;
          }
          return count;
	}
	
	@SuppressWarnings("unchecked")
	public static IRelationDAO<PersonsHaveAttachments> getFromApplicationContext(
			ApplicationContext ctx) {
		return (IRelationDAO<PersonsHaveAttachments>) ctx
				.getBean("PersonsHaveAttachmentsDAO");
	}
}