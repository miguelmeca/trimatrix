package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAuthorizations entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TAuthorizations
 * @author MyEclipse Persistence Tools
 */

public class TAuthorizationsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TAuthorizationsDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(TAuthorizations transientInstance) {
		log.debug("saving TAuthorizations instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TAuthorizations persistentInstance) {
		log.debug("deleting TAuthorizations instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TAuthorizations findById(trimatrix.db.TAuthorizationsId id) {
		log.debug("getting TAuthorizations instance with id: " + id);
		try {
			TAuthorizations instance = (TAuthorizations) getHibernateTemplate()
					.get("trimatrix.db.TAuthorizations", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TAuthorizations instance) {
		log.debug("finding TAuthorizations instance by example");
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
		log.debug("finding TAuthorizations instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TAuthorizations as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TAuthorizations instances");
		try {
			String queryString = "from TAuthorizations";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TAuthorizations merge(TAuthorizations detachedInstance) {
		log.debug("merging TAuthorizations instance");
		try {
			TAuthorizations result = (TAuthorizations) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TAuthorizations instance) {
		log.debug("attaching dirty TAuthorizations instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TAuthorizations instance) {
		log.debug("attaching clean TAuthorizations instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TAuthorizationsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TAuthorizationsDAO) ctx.getBean("TAuthorizationsDAO");
	}
}