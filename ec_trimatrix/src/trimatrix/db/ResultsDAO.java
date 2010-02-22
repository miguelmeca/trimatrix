package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import trimatrix.entities.IEntityObject;

/**
 * A data access object (DAO) providing persistence and search support for
 * Results entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @see trimatrix.db.Results
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
public class ResultsDAO extends HibernateDaoSupport implements IEntityDAO<Results>{
	private static final Log log = LogFactory.getLog(ResultsDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Results transientInstance) {
		log.debug("saving Results instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Results persistentInstance) {
		log.debug("deleting Results instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Results findById(java.lang.String id) {
		log.debug("getting Results instance with id: " + id);
		try {
			Results instance = (Results) getHibernateTemplate().get(
					"trimatrix.db.Results", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Results> findByExample(Results instance) {
		log.debug("finding Results instance by example");
		try {
			List<Results> results = (List<Results>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Results> findByProperty(String propertyName, Object value) {
		log.debug("finding Results instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Results as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Results> findAll() {
		log.debug("finding all Results instances");
		try {
			String queryString = "from Results";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Results merge(IEntityObject detachedInstance) {
		log.debug("merging Results instance");
		try {
			Results result = (Results) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IEntityObject instance) {
		log.debug("attaching dirty Results instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IEntityObject instance) {
		log.debug("attaching clean Results instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void reload(IEntityObject instance) {
		String id = instance.getId();
		log.debug("reloading Result instance with id: " + id);
		try {
			getHibernateTemplate().load(instance, id);
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}

	public static IEntityDAO<Results> getFromApplicationContext(ApplicationContext ctx) {
		return (IEntityDAO<Results>) ctx.getBean("ResultsDAO");
	}
}