package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TCountries entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.TCountries
 * @author MyEclipse Persistence Tools
 */

public class TCountriesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TCountriesDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(TCountries transientInstance) {
		log.debug("saving TCountries instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TCountries persistentInstance) {
		log.debug("deleting TCountries instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TCountries findById(trimatrix.db.TCountriesId id) {
		log.debug("getting TCountries instance with id: " + id);
		try {
			TCountries instance = (TCountries) getHibernateTemplate().get(
					"trimatrix.db.TCountries", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TCountries instance) {
		log.debug("finding TCountries instance by example");
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
		log.debug("finding TCountries instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TCountries as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TCountries instances");
		try {
			String queryString = "from TCountries";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TCountries merge(TCountries detachedInstance) {
		log.debug("merging TCountries instance");
		try {
			TCountries result = (TCountries) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TCountries instance) {
		log.debug("attaching dirty TCountries instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TCountries instance) {
		log.debug("attaching clean TCountries instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TCountriesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TCountriesDAO) ctx.getBean("TCountriesDAO");
	}
}