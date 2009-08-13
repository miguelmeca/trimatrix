package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Entities entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.Entities
 * @author MyEclipse Persistence Tools
 */

public class EntitiesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(EntitiesDAO.class);

	// property constants

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(Entities transientInstance) {
		log.debug("saving Entities instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Entities persistentInstance) {
		log.debug("deleting Entities instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Entities findById(trimatrix.db.EntitiesId id) {
		log.debug("getting Entities instance with id: " + id);
		try {
			Entities instance = (Entities) getHibernateTemplate().get(
					"trimatrix.db.Entities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Entities instance) {
		log.debug("finding Entities instance by example");
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
		log.debug("finding Entities instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Entities as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Entities instances");
		try {
			String queryString = "from Entities";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Entities merge(Entities detachedInstance) {
		log.debug("merging Entities instance");
		try {
			Entities result = (Entities) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Entities instance) {
		log.debug("attaching dirty Entities instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Entities instance) {
		log.debug("attaching clean Entities instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntitiesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EntitiesDAO) ctx.getBean("EntitiesDAO");
	}
}