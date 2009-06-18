package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TRoles entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.TRoles
 * @author MyEclipse Persistence Tools
 */

public class TRolesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TRolesDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(TRoles transientInstance) {
		log.debug("saving TRoles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TRoles persistentInstance) {
		log.debug("deleting TRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TRoles findById(trimatrix.db.TRolesId id) {
		log.debug("getting TRoles instance with id: " + id);
		try {
			TRoles instance = (TRoles) getHibernateTemplate().get(
					"trimatrix.db.TRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TRoles instance) {
		log.debug("finding TRoles instance by example");
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
		log.debug("finding TRoles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TRoles as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TRoles instances");
		try {
			String queryString = "from TRoles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TRoles merge(TRoles detachedInstance) {
		log.debug("merging TRoles instance");
		try {
			TRoles result = (TRoles) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TRoles instance) {
		log.debug("attaching dirty TRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TRoles instance) {
		log.debug("attaching clean TRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TRolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TRolesDAO) ctx.getBean("TRolesDAO");
	}
}