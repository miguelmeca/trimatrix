package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TTitle entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.TTitle
 * @author MyEclipse Persistence Tools
 */

public class TTitleDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TTitleDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(TTitle transientInstance) {
		log.debug("saving TTitle instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TTitle persistentInstance) {
		log.debug("deleting TTitle instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TTitle findById(java.lang.String id) {
		log.debug("getting TTitle instance with id: " + id);
		try {
			TTitle instance = (TTitle) getHibernateTemplate().get(
					"trimatrix.db.TTitle", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TTitle instance) {
		log.debug("finding TTitle instance by example");
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
		log.debug("finding TTitle instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TTitle as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TTitle instances");
		try {
			String queryString = "from TTitle";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TTitle merge(TTitle detachedInstance) {
		log.debug("merging TTitle instance");
		try {
			TTitle result = (TTitle) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TTitle instance) {
		log.debug("attaching dirty TTitle instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TTitle instance) {
		log.debug("attaching clean TTitle instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TTitleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TTitleDAO) ctx.getBean("TTitleDAO");
	}
}