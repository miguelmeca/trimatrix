package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TestsTreadmill entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TestsTreadmill
 * @author MyEclipse Persistence Tools
 */

public class TestsTreadmillDAO extends HibernateDaoSupport implements ISimpleDAO<TestsTreadmill> {
	private static final Log log = LogFactory.getLog(TestsTreadmillDAO.class);
	// property constants
	public static final String SPEED_VARIABLE = "speedVariable";
	public static final String INCLINE_VARIABLE = "inclineVariable";
	public static final String SPEED_INIT = "speedInit";
	public static final String SPEED_STEP = "speedStep";
	public static final String INCLINE_INIT = "inclineInit";
	public static final String INCLINE_STEP = "inclineStep";
	public static final String STEP_TIME = "stepTime";

	protected void initDao() {
		// do nothing
	}

	public void save(TestsTreadmill transientInstance) {
		log.debug("saving TestsTreadmill instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TestsTreadmill persistentInstance) {
		log.debug("deleting TestsTreadmill instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TestsTreadmill findById(java.lang.String id) {
		log.debug("getting TestsTreadmill instance with id: " + id);
		try {
			TestsTreadmill instance = (TestsTreadmill) getHibernateTemplate()
					.get("trimatrix.db.TestsTreadmill", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestsTreadmill> findByExample(TestsTreadmill instance) {
		log.debug("finding TestsTreadmill instance by example");
		try {
			List<TestsTreadmill> results = (List<TestsTreadmill>) getHibernateTemplate()
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
	public List<TestsTreadmill> findByProperty(String propertyName, Object value) {
		log.debug("finding TestsTreadmill instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TestsTreadmill as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestsTreadmill> findAll() {
		log.debug("finding all TestsTreadmill instances");
		try {
			String queryString = "from TestsTreadmill";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TestsTreadmill merge(TestsTreadmill detachedInstance) {
		log.debug("merging TestsTreadmill instance");
		try {
			TestsTreadmill result = (TestsTreadmill) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TestsTreadmill instance) {
		log.debug("attaching dirty TestsTreadmill instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TestsTreadmill instance) {
		log.debug("attaching clean TestsTreadmill instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<TestsTreadmill> getFromApplicationContext(
			ApplicationContext ctx) {
		return (ISimpleDAO<TestsTreadmill>) ctx.getBean("TestsTreadmillDAO");
	}
}