package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Zones
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see trimatrix.db.Zones
 * @author MyEclipse Persistence Tools
 */

public class ZonesDAO extends HibernateDaoSupport implements ISimpleDAO<Zones>{
	private static final Log log = LogFactory.getLog(ZonesDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(Zones transientInstance) {
		log.debug("saving Zones instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Zones persistentInstance) {
		log.debug("deleting Zones instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zones findById(java.lang.String id) {
		log.debug("getting Zones instance with id: " + id);
		try {
			Zones instance = (Zones) getHibernateTemplate().get(
					"trimatrix.db.Zones", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Zones> findByExample(Zones instance) {
		log.debug("finding Zones instance by example");
		try {
			List<Zones> results = (List<Zones>) getHibernateTemplate()
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
	public List<Zones> findByProperty(String propertyName, Object value) {
		log.debug("finding Zones instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Zones as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Zones> findAll() {
		log.debug("finding all Zones instances");
		try {
			String queryString = "from Zones";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Zones merge(Zones detachedInstance) {
		log.debug("merging Zones instance");
		try {
			Zones result = (Zones) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Zones instance) {
		log.debug("attaching dirty Zones instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zones instance) {
		log.debug("attaching clean Zones instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<Zones> getFromApplicationContext(ApplicationContext ctx) {
		return (ISimpleDAO<Zones>) ctx.getBean("ZonesDAO");
	}
}