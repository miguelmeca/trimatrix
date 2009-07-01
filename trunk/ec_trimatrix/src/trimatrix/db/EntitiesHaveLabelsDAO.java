package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * EntitiesHaveLabels entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.EntitiesHaveLabels
 * @author MyEclipse Persistence Tools
 */

public class EntitiesHaveLabelsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(EntitiesHaveLabelsDAO.class);
	// property constants
	public static final String ENTITY = "entity";
	public static final String LABEL = "label";
	public static final String PERSON_ID = "personId";

	protected void initDao() {
		// do nothing
	}

	public void save(EntitiesHaveLabels transientInstance) {
		log.debug("saving EntitiesHaveLabels instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EntitiesHaveLabels persistentInstance) {
		log.debug("deleting EntitiesHaveLabels instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EntitiesHaveLabels findById(java.lang.Integer id) {
		log.debug("getting EntitiesHaveLabels instance with id: " + id);
		try {
			EntitiesHaveLabels instance = (EntitiesHaveLabels) getHibernateTemplate()
					.get("trimatrix.db.EntitiesHaveLabels", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EntitiesHaveLabels instance) {
		log.debug("finding EntitiesHaveLabels instance by example");
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
		log.debug("finding EntitiesHaveLabels instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EntitiesHaveLabels as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEntity(Object entity) {
		return findByProperty(ENTITY, entity);
	}

	public List findByLabel(Object label) {
		return findByProperty(LABEL, label);
	}

	public List findByPersonId(Object personId) {
		return findByProperty(PERSON_ID, personId);
	}

	public List findAll() {
		log.debug("finding all EntitiesHaveLabels instances");
		try {
			String queryString = "from EntitiesHaveLabels";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EntitiesHaveLabels merge(EntitiesHaveLabels detachedInstance) {
		log.debug("merging EntitiesHaveLabels instance");
		try {
			EntitiesHaveLabels result = (EntitiesHaveLabels) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EntitiesHaveLabels instance) {
		log.debug("attaching dirty EntitiesHaveLabels instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EntitiesHaveLabels instance) {
		log.debug("attaching clean EntitiesHaveLabels instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntitiesHaveLabelsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EntitiesHaveLabelsDAO) ctx.getBean("EntitiesHaveLabelsDAO");
	}
}