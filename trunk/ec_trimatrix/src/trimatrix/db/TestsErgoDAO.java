package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TestsErgo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see trimatrix.db.TestsErgo
 * @author MyEclipse Persistence Tools
 */

public class TestsErgoDAO extends HibernateDaoSupport implements ISimpleDAO<TestsErgo> {
	private static final Log log = LogFactory.getLog(TestsErgoDAO.class);
	// property constants
	public static final String POWER_INIT = "powerInit";
	public static final String POWER_STEP = "powerStep";
	public static final String CADENCE_LOW = "cadenceLow";
	public static final String CADENCE_HIGH = "cadenceHigh";
	public static final String STEP_TIME = "stepTime";

	protected void initDao() {
		// do nothing
	}

	public void save(TestsErgo transientInstance) {
		log.debug("saving TestsErgo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TestsErgo persistentInstance) {
		log.debug("deleting TestsErgo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TestsErgo findById(java.lang.String id) {
		log.debug("getting TestsErgo instance with id: " + id);
		try {
			TestsErgo instance = (TestsErgo) getHibernateTemplate().get(
					"trimatrix.db.TestsErgo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestsErgo> findByExample(TestsErgo instance) {
		log.debug("finding TestsErgo instance by example");
		try {
			List<TestsErgo> results = (List<TestsErgo>) getHibernateTemplate()
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
	public List<TestsErgo>  findByProperty(String propertyName, Object value) {
		log.debug("finding TestsErgo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TestsErgo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TestsErgo> findAll() {
		log.debug("finding all TestsErgo instances");
		try {
			String queryString = "from TestsErgo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TestsErgo merge(TestsErgo detachedInstance) {
		log.debug("merging TestsErgo instance");
		try {
			TestsErgo result = (TestsErgo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TestsErgo instance) {
		log.debug("attaching dirty TestsErgo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TestsErgo instance) {
		log.debug("attaching clean TestsErgo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<TestsErgo> getFromApplicationContext(ApplicationContext ctx) {
		return (ISimpleDAO<TestsErgo>) ctx.getBean("TestsErgoDAO");
	}
}