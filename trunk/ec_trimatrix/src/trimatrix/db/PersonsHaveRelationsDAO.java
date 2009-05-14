package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

public class PersonsHaveRelationsDAO extends HibernateDaoSupport implements IPersonsHaveRelationsDAO {
	private static final Log log = LogFactory
			.getLog(PersonsHaveRelationsDAO.class);
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
	public void delete(PersonsHaveRelations persistentInstance) {
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
	public List<PersonsHaveRelations> findByExample(PersonsHaveRelations instance) {
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
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findByPartner1(java.lang.Object)
	 */
	public List<PersonsHaveRelations> findByPartner1(Object partner1) {
		return findByProperty(PARTNER1, partner1);
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findByPartner2(java.lang.Object)
	 */
	public List<PersonsHaveRelations> findByPartner2(Object partner2) {
		return findByProperty(PARTNER2, partner2);
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findByReltypKey(java.lang.Object)
	 */
	public List<PersonsHaveRelations> findByReltypKey(Object reltypKey) {
		return findByProperty(RELTYP_KEY, reltypKey);
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveRelationsDAO#findByDefault_(java.lang.Object)
	 */
	public List<PersonsHaveRelations> findByDefault_(Object default_) {
		return findByProperty(DEFAULT_, default_);
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
	public PersonsHaveRelations merge(PersonsHaveRelations detachedInstance) {
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

	public static IPersonsHaveRelationsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IPersonsHaveRelationsDAO) ctx.getBean("PersonsHaveRelationsDAO");
	}
	
	public void reload(PersonsHaveRelations relation) {
		String id = relation.getId();
		log.debug("reloading PersonsHaveRelations instance with id: " + id);
		try {
			getHibernateTemplate().load(relation, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}
	

}