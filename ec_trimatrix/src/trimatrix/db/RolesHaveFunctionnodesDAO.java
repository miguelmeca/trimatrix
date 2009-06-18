package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * RolesHaveFunctionnodes entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.RolesHaveFunctionnodes
 * @author MyEclipse Persistence Tools
 */

public class RolesHaveFunctionnodesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(RolesHaveFunctionnodesDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(RolesHaveFunctionnodes transientInstance) {
		log.debug("saving RolesHaveFunctionnodes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RolesHaveFunctionnodes persistentInstance) {
		log.debug("deleting RolesHaveFunctionnodes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RolesHaveFunctionnodes findById(
			trimatrix.db.RolesHaveFunctionnodesId id) {
		log.debug("getting RolesHaveFunctionnodes instance with id: " + id);
		try {
			RolesHaveFunctionnodes instance = (RolesHaveFunctionnodes) getHibernateTemplate()
					.get("trimatrix.db.RolesHaveFunctionnodes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RolesHaveFunctionnodes instance) {
		log.debug("finding RolesHaveFunctionnodes instance by example");
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
		log.debug("finding RolesHaveFunctionnodes instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RolesHaveFunctionnodes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all RolesHaveFunctionnodes instances");
		try {
			String queryString = "from RolesHaveFunctionnodes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RolesHaveFunctionnodes merge(RolesHaveFunctionnodes detachedInstance) {
		log.debug("merging RolesHaveFunctionnodes instance");
		try {
			RolesHaveFunctionnodes result = (RolesHaveFunctionnodes) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RolesHaveFunctionnodes instance) {
		log.debug("attaching dirty RolesHaveFunctionnodes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RolesHaveFunctionnodes instance) {
		log.debug("attaching clean RolesHaveFunctionnodes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RolesHaveFunctionnodesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RolesHaveFunctionnodesDAO) ctx
				.getBean("RolesHaveFunctionnodesDAO");
	}
}