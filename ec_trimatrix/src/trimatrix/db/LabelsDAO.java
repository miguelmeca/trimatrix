package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Labels entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.Labels
 * @author MyEclipse Persistence Tools
 */

public class LabelsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LabelsDAO.class);
	// property constants
	public static final String PERSON_ID = "personId";
	public static final String DESCRIPTION = "description";
	public static final String COLOR = "color";

	protected void initDao() {
		// do nothing
	}

	public void save(Labels transientInstance) {
		log.debug("saving Labels instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Labels persistentInstance) {
		log.debug("deleting Labels instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Labels findById(java.lang.String id) {
		log.debug("getting Labels instance with id: " + id);
		try {
			Labels instance = (Labels) getHibernateTemplate().get(
					"trimatrix.db.Labels", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Labels instance) {
		log.debug("finding Labels instance by example");
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
		log.debug("finding Labels instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Labels as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPersonId(Object personId) {
		return findByProperty(PERSON_ID, personId);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByColor(Object color) {
		return findByProperty(COLOR, color);
	}

	public List findAll() {
		log.debug("finding all Labels instances");
		try {
			String queryString = "from Labels";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Labels merge(Labels detachedInstance) {
		log.debug("merging Labels instance");
		try {
			Labels result = (Labels) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Labels instance) {
		log.debug("attaching dirty Labels instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Labels instance) {
		log.debug("attaching clean Labels instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabelsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LabelsDAO) ctx.getBean("LabelsDAO");
	}
}