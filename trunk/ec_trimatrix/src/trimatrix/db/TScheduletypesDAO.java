package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TScheduletypes entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TScheduletypes
 * @author MyEclipse Persistence Tools
 */

public class TScheduletypesDAO extends HibernateDaoSupport implements ITextDAO<TScheduletypes, TScheduletypesId> {
	private static final Log log = LogFactory.getLog(TScheduletypesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(TScheduletypes transientInstance) {
		log.debug("saving TScheduletypes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TScheduletypes persistentInstance) {
		log.debug("deleting TScheduletypes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TScheduletypes findById(trimatrix.db.TScheduletypesId id) {
		log.debug("getting TScheduletypes instance with id: " + id);
		try {
			TScheduletypes instance = (TScheduletypes) getHibernateTemplate().get("trimatrix.db.TScheduletypes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TScheduletypes> findByExample(TScheduletypes instance) {
		log.debug("finding TScheduletypes instance by example");
		try {
			List<TScheduletypes> results = (List<TScheduletypes>) getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TScheduletypes> findByProperty(String propertyName, Object value) {
		log.debug("finding TScheduletypes instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from TScheduletypes as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TScheduletypes> findAll() {
		log.debug("finding all TScheduletypes instances");
		try {
			String queryString = "from TScheduletypes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TScheduletypes merge(TScheduletypes detachedInstance) {
		log.debug("merging TScheduletypes instance");
		try {
			TScheduletypes result = (TScheduletypes) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TScheduletypes instance) {
		log.debug("attaching dirty TScheduletypes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TScheduletypes instance) {
		log.debug("attaching clean TScheduletypes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@Override
	public TScheduletypes findById(String key, String languageKey) {
		return findById(new TScheduletypesId(key, languageKey));
	}

	public static TScheduletypesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TScheduletypesDAO) ctx.getBean("TScheduletypesDAO");
	}	
}