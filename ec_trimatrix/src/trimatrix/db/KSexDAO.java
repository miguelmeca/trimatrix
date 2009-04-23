package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for KSex
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see trimatrix.db.KSex
 * @author MyEclipse Persistence Tools
 */

public class KSexDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(KSexDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(KSex transientInstance) {
		log.debug("saving KSex instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KSex persistentInstance) {
		log.debug("deleting KSex instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KSex findById(java.lang.String id) {
		log.debug("getting KSex instance with id: " + id);
		try {
			KSex instance = (KSex) getHibernateTemplate().get(
					"trimatrix.db.KSex", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KSex instance) {
		log.debug("finding KSex instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding KSex instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from KSex as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all KSex instances");
		try {
			String queryString = "from KSex";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KSex merge(KSex detachedInstance) {
		log.debug("merging KSex instance");
		try {
			KSex result = (KSex) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KSex instance) {
		log.debug("attaching dirty KSex instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KSex instance) {
		log.debug("attaching clean KSex instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KSexDAO getFromApplicationContext(ApplicationContext ctx) {
		return (KSexDAO) ctx.getBean("KSexDAO");
	}
}