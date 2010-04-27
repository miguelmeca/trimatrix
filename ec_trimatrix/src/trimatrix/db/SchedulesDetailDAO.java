package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SchedulesDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see trimatrix.db.SchedulesDetail
 * @author MyEclipse Persistence Tools
 */

public class SchedulesDetailDAO extends HibernateDaoSupport implements IComplexDAO<SchedulesDetail, SchedulesDetailId> {
	private static final Log log = LogFactory.getLog(SchedulesDetailDAO.class);

	public void save(SchedulesDetail transientInstance) {
		log.debug("saving SchedulesDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SchedulesDetail persistentInstance) {
		log.debug("deleting SchedulesDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SchedulesDetail findById(trimatrix.db.SchedulesDetailId id) {
		log.debug("getting SchedulesDetail instance with id: " + id);
		try {
			SchedulesDetail instance = (SchedulesDetail) getHibernateTemplate().get("trimatrix.db.SchedulesDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchedulesDetail> findByExample(SchedulesDetail instance) {
		log.debug("finding SchedulesDetail instance by example");
		try {
			List<SchedulesDetail> results = (List<SchedulesDetail>) getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchedulesDetail instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from SchedulesDetail as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SchedulesDetail> findAll() {
		log.debug("finding all SchedulesDetail instances");
		try {
			String queryString = "from SchedulesDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SchedulesDetail merge(SchedulesDetail detachedInstance) {
		log.debug("merging SchedulesDetail instance");
		try {
			SchedulesDetail result = (SchedulesDetail) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SchedulesDetail instance) {
		log.debug("attaching dirty SchedulesDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SchedulesDetail instance) {
		log.debug("attaching clean SchedulesDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static IComplexDAO<SchedulesDetail, SchedulesDetailId> getFromApplicationContext(ApplicationContext ctx) {
    	return (IComplexDAO<SchedulesDetail, SchedulesDetailId>) ctx.getBean("SchedulesDetailDAO");
	}
}