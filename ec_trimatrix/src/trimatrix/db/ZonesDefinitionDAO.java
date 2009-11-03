package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ZonesDefinition entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.ZonesDefinition
 * @author MyEclipse Persistence Tools
 */

public class ZonesDefinitionDAO extends HibernateDaoSupport implements ISimpleDAO<ZonesDefinition>{
	private static final Log log = LogFactory.getLog(ZonesDefinitionDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ZonesDefinition transientInstance) {
		log.debug("saving ZonesDefinition instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ZonesDefinition persistentInstance) {
		log.debug("deleting ZonesDefinition instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ZonesDefinition findById(java.lang.String id) {
		log.debug("getting ZonesDefinition instance with id: " + id);
		try {
			ZonesDefinition instance = (ZonesDefinition) getHibernateTemplate()
					.get("trimatrix.db.ZonesDefinition", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ZonesDefinition> findByExample(ZonesDefinition instance) {
		log.debug("finding ZonesDefinition instance by example");
		try {
			List<ZonesDefinition> results = (List<ZonesDefinition>) getHibernateTemplate()
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
	public List<ZonesDefinition> findByProperty(String propertyName, Object value) {
		log.debug("finding ZonesDefinition instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ZonesDefinition as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ZonesDefinition> findAll() {
		log.debug("finding all ZonesDefinition instances");
		try {
			String queryString = "from ZonesDefinition";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ZonesDefinition merge(ZonesDefinition detachedInstance) {
		log.debug("merging ZonesDefinition instance");
		try {
			ZonesDefinition result = (ZonesDefinition) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ZonesDefinition instance) {
		log.debug("attaching dirty ZonesDefinition instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ZonesDefinition instance) {
		log.debug("attaching clean ZonesDefinition instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<ZonesDefinition> getFromApplicationContext(
			ApplicationContext ctx) {
		return (ISimpleDAO<ZonesDefinition>) ctx.getBean("ZonesDefinitionDAO");
	}
}