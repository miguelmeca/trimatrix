package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UsersHaveRoles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.UsersHaveRoles
 * @author MyEclipse Persistence Tools
 */

public class UsersHaveRolesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UsersHaveRolesDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(UsersHaveRoles transientInstance) {
		log.debug("saving UsersHaveRoles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UsersHaveRoles persistentInstance) {
		log.debug("deleting UsersHaveRoles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UsersHaveRoles findById(trimatrix.db.UsersHaveRolesId id) {
		log.debug("getting UsersHaveRoles instance with id: " + id);
		try {
			UsersHaveRoles instance = (UsersHaveRoles) getHibernateTemplate()
					.get("trimatrix.db.UsersHaveRoles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UsersHaveRoles instance) {
		log.debug("finding UsersHaveRoles instance by example");
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
		log.debug("finding UsersHaveRoles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from UsersHaveRoles as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UsersHaveRoles instances");
		try {
			String queryString = "from UsersHaveRoles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UsersHaveRoles merge(UsersHaveRoles detachedInstance) {
		log.debug("merging UsersHaveRoles instance");
		try {
			UsersHaveRoles result = (UsersHaveRoles) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UsersHaveRoles instance) {
		log.debug("attaching dirty UsersHaveRoles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UsersHaveRoles instance) {
		log.debug("attaching clean UsersHaveRoles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsersHaveRolesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UsersHaveRolesDAO) ctx.getBean("UsersHaveRolesDAO");
	}
}