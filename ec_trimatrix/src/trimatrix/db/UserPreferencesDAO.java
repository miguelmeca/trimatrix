package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserPreferences entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.UserPreferences
 * @author MyEclipse Persistence Tools
 */

public class UserPreferencesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UserPreferencesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(UserPreferences transientInstance) {
		log.debug("saving UserPreferences instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserPreferences persistentInstance) {
		log.debug("deleting UserPreferences instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserPreferences findById(java.lang.String id) {
		log.debug("getting UserPreferences instance with id: " + id);
		try {
			UserPreferences instance = (UserPreferences) getHibernateTemplate().get("trimatrix.db.UserPreferences", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UserPreferences> findByExample(UserPreferences instance) {
		log.debug("finding UserPreferences instance by example");
		try {
			List<UserPreferences> results = (List<UserPreferences>) getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserPreferences instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from UserPreferences as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserPreferences instances");
		try {
			String queryString = "from UserPreferences";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserPreferences merge(UserPreferences detachedInstance) {
		log.debug("merging UserPreferences instance");
		try {
			UserPreferences result = (UserPreferences) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserPreferences instance) {
		log.debug("attaching dirty UserPreferences instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserPreferences instance) {
		log.debug("attaching clean UserPreferences instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserPreferencesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserPreferencesDAO) ctx.getBean("UserPreferencesDAO");
	}
}