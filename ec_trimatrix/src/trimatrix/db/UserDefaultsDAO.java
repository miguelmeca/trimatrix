package trimatrix.db;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserDefaults entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see trimatrix.db.UserDefaults
 * @author MyEclipse Persistence Tools
 */

public class UserDefaultsDAO extends HibernateDaoSupport implements ISimpleDAO<UserDefaults> {
	private static final Log log = LogFactory.getLog(UserDefaultsDAO.class);

	public void save(UserDefaults transientInstance) {
		log.debug("saving UserDefaults instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserDefaults persistentInstance) {
		log.debug("deleting UserDefaults instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserDefaults findById(java.lang.String id) {
		log.debug("getting UserDefaults instance with id: " + id);
		try {
			UserDefaults instance = (UserDefaults) getHibernateTemplate().get("trimatrix.db.UserDefaults", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<UserDefaults> findByExample(UserDefaults instance) {
		log.debug("finding UserDefaults instance by example");
		try {
			List<UserDefaults> results = (List<UserDefaults>) getSession().createCriteria("trimatrix.db.UserDefaults").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserDefaults instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from UserDefaults as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserDefaults instances");
		try {
			String queryString = "from UserDefaults";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserDefaults merge(UserDefaults detachedInstance) {
		log.debug("merging UserDefaults instance");
		try {
			UserDefaults result = (UserDefaults) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserDefaults instance) {
		log.debug("attaching dirty UserDefaults instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserDefaults instance) {
		log.debug("attaching clean UserDefaults instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<UserDefaults> getFromApplicationContext(ApplicationContext ctx) {
		return (ISimpleDAO<UserDefaults>) ctx.getBean("UserDefaultsDAO");
	}
}