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
 * Schedules entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.Schedules
 * @author MyEclipse Persistence Tools
 */

public class SchedulesDAO extends HibernateDaoSupport implements IEntityDAO<Schedules> {
	private static final Log log = LogFactory.getLog(SchedulesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Schedules transientInstance) {
		log.debug("saving Schedules instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Schedules persistentInstance) {
		log.debug("deleting Schedules instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Schedules findById(java.lang.String id) {
		log.debug("getting Schedules instance with id: " + id);
		try {
			Schedules instance = (Schedules) getHibernateTemplate().get("trimatrix.db.Schedules", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Schedules> findByExample(Schedules instance) {
		log.debug("finding Schedules instance by example");
		try {
			List<Schedules> results = (List<Schedules>) getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Schedules> findByProperty(String propertyName, Object value) {
		log.debug("finding Schedules instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Schedules as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Schedules> findAll() {
		log.debug("finding all Schedules instances");
		try {
			String queryString = "from Schedules";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Schedules merge(IEntityObject detachedInstance) {
		log.debug("merging Schedules instance");
		try {
			Schedules result = (Schedules) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IEntityObject instance) {
		log.debug("attaching dirty Schedules instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IEntityObject instance) {
		log.debug("attaching clean Schedules instance");
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
		log.debug("reloading Schedule instance with id: " + id);
		try {
			getHibernateTemplate().load(instance, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}		
	}

	public static IEntityDAO<Schedules> getFromApplicationContext(ApplicationContext ctx) {
		return (IEntityDAO<Schedules>) ctx.getBean("SchedulesDAO");
	}
}