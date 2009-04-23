package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * KRoles entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.KRoles
 * @author MyEclipse Persistence Tools
 */

public class KRolesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(KRolesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(KRoles transientInstance) {
		log.debug("saving KRoles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KRoles persistentInstance) {
		log.debug("deleting KRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KRoles findById(java.lang.String id) {
		log.debug("getting KRoles instance with id: " + id);
		try {
			KRoles instance = (KRoles) getHibernateTemplate().get(
					"trimatrix.db.KRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KRoles instance) {
		log.debug("finding KRoles instance by example");
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
		log.debug("finding KRoles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from KRoles as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all KRoles instances");
		try {
			String queryString = "from KRoles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KRoles merge(KRoles detachedInstance) {
		log.debug("merging KRoles instance");
		try {
			KRoles result = (KRoles) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KRoles instance) {
		log.debug("attaching dirty KRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KRoles instance) {
		log.debug("attaching clean KRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KRolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (KRolesDAO) ctx.getBean("KRolesDAO");
	}
}