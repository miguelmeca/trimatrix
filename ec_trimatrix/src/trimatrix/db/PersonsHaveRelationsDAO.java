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
 * PersonsHaveRelations entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.PersonsHaveRelations
 * @author MyEclipse Persistence Tools
 */

public class PersonsHaveRelationsDAO extends HibernateDaoSupport implements IRelationDAO<PersonsHaveRelations> {
	// property constants
	public static final String PARTNER1 = "partner1";
	public static final String PARTNER2 = "partner2";
	public static final String RELTYP_KEY = "reltypKey";
	public static final String DEFAULT_ = "default_";
	
	private static final Log log = LogFactory
			.getLog(PersonsHaveRelationsDAO.class);
	@Override
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#save(trimatrix.db.PersonsHaveRelations)
	 */
	public void save(PersonsHaveRelations transientInstance) {
		log.debug("saving PersonsHaveRelations instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#delete(trimatrix.db.PersonsHaveRelations)
	 */
	public void delete(IRelationObject persistentInstance) {
		log.debug("deleting PersonsHaveRelations instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findById(java.lang.String)
	 */
	public PersonsHaveRelations findById(java.lang.String id) {
		log.debug("getting PersonsHaveRelations instance with id: " + id);
		try {
			PersonsHaveRelations instance = (PersonsHaveRelations) getHibernateTemplate()
					.get("trimatrix.db.PersonsHaveRelations", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findByExample(trimatrix.db.PersonsHaveRelations)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveRelations> findByExample(IRelationObject instance) {
		log.debug("finding PersonsHaveRelations instance by example");
		try {
			List<PersonsHaveRelations> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveRelations> findByProperty(String propertyName, Object value) {
		log.debug("finding PersonsHaveRelations instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PersonsHaveRelations as model where model."
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
	public List<PersonsHaveRelations> findByProperties(Map<String, Object> properties) {
		log.debug("finding PersonsHaveRelations instance with properties");
		try {
			StringBuffer queryString = new StringBuffer("from PersonsHaveRelations as model");
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
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<PersonsHaveRelations> findAll() {
		log.debug("finding all PersonsHaveRelations instances");
		try {
			String queryString = "from PersonsHaveRelations";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#merge(trimatrix.db.PersonsHaveRelations)
	 */
	public PersonsHaveRelations merge(IRelationObject detachedInstance) {
		log.debug("merging PersonsHaveRelations instance");
		try {
			PersonsHaveRelations result = (PersonsHaveRelations) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#attachDirty(trimatrix.db.PersonsHaveRelations)
	 */
	public void attachDirty(PersonsHaveRelations instance) {
		log.debug("attaching dirty PersonsHaveRelations instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#attachClean(trimatrix.db.PersonsHaveRelations)
	 */
	public void attachClean(PersonsHaveRelations instance) {
		log.debug("attaching clean PersonsHaveRelations instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int deleteByPartners(String partnerId) {
		int count = 0;
    	log.debug("finding PersonsHaveRelations instance with partner1 or partner2 : "+ partnerId);
          try {
             String queryString = "from PersonsHaveRelations as model where model.partner1 = '" + partnerId + "' or model.partner2 = '" + partnerId + "'";
             List<PersonsHaveRelations> relations = getHibernateTemplate().find(queryString);
             // delete instance
             for (PersonsHaveRelations relation : relations) {
            	 delete(relation);
            	 count++;
             }
          } catch (RuntimeException re) {
             log.error("delete by partners failed", re);
             throw re;
          }
          return count;
	}
	
	public void reload(IRelationObject relation) {
		String id = relation.getId();
		log.debug("reloading PersonsHaveRelations instance with id: " + id);
		try {
			getHibernateTemplate().load(relation, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static IRelationDAO<PersonsHaveRelations> getFromApplicationContext(
			ApplicationContext ctx) {
		return (IRelationDAO<PersonsHaveRelations>) ctx.getBean("PersonsHaveRelationsDAO");
	}

}