package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TTesttypes entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.TTesttypes
 * @author MyEclipse Persistence Tools
 */

public class TTesttypesDAO extends HibernateDaoSupport implements ITextDAO<TTesttypes, TTesttypesId>{
	private static final Log log = LogFactory.getLog(TTesttypesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(TTesttypes transientInstance) {
		log.debug("saving TTesttypes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TTesttypes persistentInstance) {
		log.debug("deleting TTesttypes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TTesttypes findById(trimatrix.db.TTesttypesId id) {
		log.debug("getting TTesttypes instance with id: " + id);
		try {
			TTesttypes instance = (TTesttypes) getHibernateTemplate().get(
					"trimatrix.db.TTesttypes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public TTesttypes findById(String key, String languageKey) {
		return findById(new TTesttypesId(key, languageKey));
	}

	public List<TTesttypes> findByExample(TTesttypes instance) {
		log.debug("finding TTesttypes instance by example");
		try {
			List<TTesttypes> results = (List<TTesttypes>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TTesttypes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TTesttypes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TTesttypes instances");
		try {
			String queryString = "from TTesttypes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TTesttypes merge(TTesttypes detachedInstance) {
		log.debug("merging TTesttypes instance");
		try {
			TTesttypes result = (TTesttypes) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TTesttypes instance) {
		log.debug("attaching dirty TTesttypes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TTesttypes instance) {
		log.debug("attaching clean TTesttypes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TTesttypesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TTesttypesDAO) ctx.getBean("TTesttypesDAO");
	}
}