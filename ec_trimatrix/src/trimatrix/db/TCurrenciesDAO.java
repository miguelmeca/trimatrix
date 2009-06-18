package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TCurrencies entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TCurrencies
 * @author MyEclipse Persistence Tools
 */

public class TCurrenciesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TCurrenciesDAO.class);

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(TCurrencies transientInstance) {
		log.debug("saving TCurrencies instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TCurrencies persistentInstance) {
		log.debug("deleting TCurrencies instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TCurrencies findById(trimatrix.db.TCurrenciesId id) {
		log.debug("getting TCurrencies instance with id: " + id);
		try {
			TCurrencies instance = (TCurrencies) getHibernateTemplate().get(
					"trimatrix.db.TCurrencies", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TCurrencies instance) {
		log.debug("finding TCurrencies instance by example");
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
		log.debug("finding TCurrencies instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TCurrencies as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TCurrencies instances");
		try {
			String queryString = "from TCurrencies";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TCurrencies merge(TCurrencies detachedInstance) {
		log.debug("merging TCurrencies instance");
		try {
			TCurrencies result = (TCurrencies) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TCurrencies instance) {
		log.debug("attaching dirty TCurrencies instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TCurrencies instance) {
		log.debug("attaching clean TCurrencies instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TCurrenciesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TCurrenciesDAO) ctx.getBean("TCurrenciesDAO");
	}
}