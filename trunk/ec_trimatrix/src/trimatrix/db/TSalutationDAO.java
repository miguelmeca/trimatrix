package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TSalutation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TSalutation
 * @author MyEclipse Persistence Tools
 */

public class TSalutationDAO extends HibernateDaoSupport implements ITSalutationDAO {
	private static final Log log = LogFactory.getLog(TSalutationDAO.class);

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#save(trimatrix.db.TSalutation)
	 */
	public void save(TSalutation transientInstance) {
		log.debug("saving TSalutation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#delete(trimatrix.db.TSalutation)
	 */
	public void delete(TSalutation persistentInstance) {
		log.debug("deleting TSalutation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#findById(trimatrix.db.TSalutationId)
	 */
	public TSalutation findById(trimatrix.db.TSalutationId id) {
		log.debug("getting TSalutation instance with id: " + id);
		try {
			TSalutation instance = (TSalutation) getHibernateTemplate().get(
					"trimatrix.db.TSalutation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#findById(java.lang.String, java.lang.String)
	 */
	public TSalutation findById(String key, String languageKey) {
		return findById(new TSalutationId(key, languageKey));
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#findByExample(trimatrix.db.TSalutation)
	 */
	@SuppressWarnings("unchecked")
	public List<TSalutation> findByExample(TSalutation instance) {
		log.debug("finding TSalutation instance by example");
		try {
			List<TSalutation> results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<TSalutation> findByProperty(String propertyName, Object value) {
		log.debug("finding TSalutation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TSalutation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TSalutation> findAll() {
		log.debug("finding all TSalutation instances");
		try {
			String queryString = "from TSalutation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#merge(trimatrix.db.TSalutation)
	 */
	public TSalutation merge(TSalutation detachedInstance) {
		log.debug("merging TSalutation instance");
		try {
			TSalutation result = (TSalutation) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#attachDirty(trimatrix.db.TSalutation)
	 */
	public void attachDirty(TSalutation instance) {
		log.debug("attaching dirty TSalutation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.ITSalutationDAO#attachClean(trimatrix.db.TSalutation)
	 */
	public void attachClean(TSalutation instance) {
		log.debug("attaching clean TSalutation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ITSalutationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ITSalutationDAO) ctx.getBean("TSalutationDAO");
	}
}