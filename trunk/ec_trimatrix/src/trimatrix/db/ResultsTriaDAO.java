package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ResultsTria entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.ResultsTria
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
public class ResultsTriaDAO extends HibernateDaoSupport implements ISimpleDAO<ResultsTria>{
	private static final Log log = LogFactory.getLog(ResultsTriaDAO.class);

	protected void initDao() {
		// do nothing
	}

	public void save(ResultsTria transientInstance) {
		log.debug("saving ResultsTria instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ResultsTria persistentInstance) {
		log.debug("deleting ResultsTria instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ResultsTria findById(java.lang.String id) {
		log.debug("getting ResultsTria instance with id: " + id);
		try {
			ResultsTria instance = (ResultsTria) getHibernateTemplate().get(
					"trimatrix.db.ResultsTria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ResultsTria> findByExample(ResultsTria instance) {
		log.debug("finding ResultsTria instance by example");
		try {
			List<ResultsTria> results = (List<ResultsTria>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ResultsTria> findByProperty(String propertyName, Object value) {
		log.debug("finding ResultsTria instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ResultsTria as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ResultsTria> findAll() {
		log.debug("finding all ResultsTria instances");
		try {
			String queryString = "from ResultsTria";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ResultsTria merge(ResultsTria detachedInstance) {
		log.debug("merging ResultsTria instance");
		try {
			ResultsTria result = (ResultsTria) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ResultsTria instance) {
		log.debug("attaching dirty ResultsTria instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ResultsTria instance) {
		log.debug("attaching clean ResultsTria instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ISimpleDAO<ResultsTria> getFromApplicationContext(
			ApplicationContext ctx) {
		return (ISimpleDAO<ResultsTria>) ctx.getBean("ResultsTriaDAO");
	}
}