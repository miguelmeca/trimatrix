package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * KLanguages entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.KLanguages
 * @author MyEclipse Persistence Tools
 */

public class KLanguagesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(KLanguagesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(KLanguages transientInstance) {
		log.debug("saving KLanguages instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KLanguages persistentInstance) {
		log.debug("deleting KLanguages instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KLanguages findById(java.lang.String id) {
		log.debug("getting KLanguages instance with id: " + id);
		try {
			KLanguages instance = (KLanguages) getHibernateTemplate().get(
					"trimatrix.db.KLanguages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<KLanguages> findByExample(KLanguages instance) {
		log.debug("finding KLanguages instance by example");
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

	public List<KLanguages> findByProperty(String propertyName, Object value) {
		log.debug("finding KLanguages instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from KLanguages as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<KLanguages> findAll() {
		log.debug("finding all KLanguages instances");
		try {
			String queryString = "from KLanguages";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KLanguages merge(KLanguages detachedInstance) {
		log.debug("merging KLanguages instance");
		try {
			KLanguages result = (KLanguages) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KLanguages instance) {
		log.debug("attaching dirty KLanguages instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KLanguages instance) {
		log.debug("attaching clean KLanguages instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KLanguagesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (KLanguagesDAO) ctx.getBean("KLanguagesDAO");
	}
}