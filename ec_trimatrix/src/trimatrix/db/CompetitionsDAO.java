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
 * Competitions entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.Competitions
 * @author MyEclipse Persistence Tools
 */

public class CompetitionsDAO extends HibernateDaoSupport implements IEntityDAO<Competitions>{
	private static final Log log = LogFactory.getLog(CompetitionsDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Competitions transientInstance) {
		log.debug("saving Competitions instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Competitions persistentInstance) {
		log.debug("deleting Competitions instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Competitions findById(java.lang.String id) {
		log.debug("getting Competitions instance with id: " + id);
		try {
			Competitions instance = (Competitions) getHibernateTemplate().get(
					"trimatrix.db.Competitions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Competitions> findByExample(Competitions instance) {
		log.debug("finding Competitions instance by example");
		try {
			List<Competitions> results = (List<Competitions>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Competitions> findByProperty(String propertyName, Object value) {
		log.debug("finding Competitions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Competitions as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Competitions> findAll() {
		log.debug("finding all Competitions instances");
		try {
			String queryString = "from Competitions";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Competitions merge(IEntityObject detachedInstance) {
		log.debug("merging Competitions instance");
		try {
			Competitions result = (Competitions) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IEntityObject instance) {
		log.debug("attaching dirty Competitions instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IEntityObject instance) {
		log.debug("attaching clean Competitions instance");
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
		log.debug("reloading Competition instance with id: " + id);
		try {
			getHibernateTemplate().load(instance, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}		
	}
	
	public static IEntityDAO<Competitions> getFromApplicationContext(
			ApplicationContext ctx) {
		return (IEntityDAO<Competitions>) ctx.getBean("CompetitionsDAO");
	}
}