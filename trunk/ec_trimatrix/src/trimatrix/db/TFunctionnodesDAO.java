package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TFunctionnodes entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TFunctionnodes
 * @author MyEclipse Persistence Tools
 */

public class TFunctionnodesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TFunctionnodesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(TFunctionnodes transientInstance) {
		log.debug("saving TFunctionnodes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TFunctionnodes persistentInstance) {
		log.debug("deleting TFunctionnodes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TFunctionnodes findById(trimatrix.db.TFunctionnodesId id) {
		log.debug("getting TFunctionnodes instance with id: " + id);
		try {
			TFunctionnodes instance = (TFunctionnodes) getHibernateTemplate()
					.get("trimatrix.db.TFunctionnodes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TFunctionnodes instance) {
		log.debug("finding TFunctionnodes instance by example");
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
		log.debug("finding TFunctionnodes instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TFunctionnodes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TFunctionnodes instances");
		try {
			String queryString = "from TFunctionnodes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TFunctionnodes merge(TFunctionnodes detachedInstance) {
		log.debug("merging TFunctionnodes instance");
		try {
			TFunctionnodes result = (TFunctionnodes) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TFunctionnodes instance) {
		log.debug("attaching dirty TFunctionnodes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TFunctionnodes instance) {
		log.debug("attaching clean TFunctionnodes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TFunctionnodesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TFunctionnodesDAO) ctx.getBean("TFunctionnodesDAO");
	}
}