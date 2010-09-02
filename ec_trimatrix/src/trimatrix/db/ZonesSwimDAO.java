package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ZonesSwim entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @see trimatrix.db.ZonesSwim
 * @author MyEclipse Persistence Tools
 */

public class ZonesSwimDAO extends HibernateDaoSupport implements IComplexDAO<ZonesSwim, ZonesSwimId>{
	private static final Log log = LogFactory.getLog(ZonesSwimDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ZonesSwim transientInstance) {
		log.debug("saving ZonesSwim instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ZonesSwim persistentInstance) {
		log.debug("deleting ZonesSwim instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ZonesSwim findById(trimatrix.db.ZonesSwimId id) {
		log.debug("getting ZonesSwim instance with id: " + id);
		try {
			ZonesSwim instance = (ZonesSwim) getHibernateTemplate().get("trimatrix.db.ZonesSwim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ZonesSwim> findByExample(ZonesSwim instance) {
		log.debug("finding ZonesSwim instance by example");
		try {
			List<ZonesSwim> results = (List<ZonesSwim>) getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ZonesSwim instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ZonesSwim as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ZonesSwim instances");
		try {
			String queryString = "from ZonesSwim";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ZonesSwim merge(ZonesSwim detachedInstance) {
		log.debug("merging ZonesSwim instance");
		try {
			ZonesSwim result = (ZonesSwim) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ZonesSwim instance) {
		log.debug("attaching dirty ZonesSwim instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ZonesSwim instance) {
		log.debug("attaching clean ZonesSwim instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IComplexDAO<ZonesSwim, ZonesSwimId> getFromApplicationContext(ApplicationContext ctx) {
		return (IComplexDAO<ZonesSwim, ZonesSwimId>) ctx.getBean("ZonesSwimDAO");
	}
}