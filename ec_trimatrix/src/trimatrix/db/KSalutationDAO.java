package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * KSalutation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.KSalutation
 * @author MyEclipse Persistence Tools
 */

public class KSalutationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(KSalutationDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(KSalutation transientInstance) {
		log.debug("saving KSalutation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KSalutation persistentInstance) {
		log.debug("deleting KSalutation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KSalutation findById(java.lang.String id) {
		log.debug("getting KSalutation instance with id: " + id);
		try {
			KSalutation instance = (KSalutation) getHibernateTemplate().get(
					"trimatrix.db.KSalutation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KSalutation instance) {
		log.debug("finding KSalutation instance by example");
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
		log.debug("finding KSalutation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from KSalutation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all KSalutation instances");
		try {
			String queryString = "from KSalutation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KSalutation merge(KSalutation detachedInstance) {
		log.debug("merging KSalutation instance");
		try {
			KSalutation result = (KSalutation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KSalutation instance) {
		log.debug("attaching dirty KSalutation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KSalutation instance) {
		log.debug("attaching clean KSalutation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KSalutationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (KSalutationDAO) ctx.getBean("KSalutationDAO");
	}
}