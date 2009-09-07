package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TestsProtocol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TestsProtocol
 * @author MyEclipse Persistence Tools
 */

public class TestsProtocolDAO extends HibernateDaoSupport implements ISimpleDAO<TestsProtocol> {
	private static final Log log = LogFactory.getLog(TestsProtocolDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String MODEL = "model";
	public static final String MODEL_LACTATE = "modelLactate";
	public static final String MODEL_SPIRO = "modelSpiro";
	public static final String COUNT_STEPS = "countSteps";
	public static final String LACTATE = "lactate";
	public static final String HR = "hr";
	public static final String O2_ABSORPTION = "o2Absorption";
	public static final String CO2_EMISSION = "co2Emission";
	public static final String RQ = "rq";

	protected void initDao() {
		// do nothing
	}

	public void save(TestsProtocol transientInstance) {
		log.debug("saving TestsProtocol instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TestsProtocol persistentInstance) {
		log.debug("deleting TestsProtocol instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TestsProtocol findById(java.lang.String id) {
		log.debug("getting TestsProtocol instance with id: " + id);
		try {
			TestsProtocol instance = (TestsProtocol) getHibernateTemplate()
					.get("trimatrix.db.TestsProtocol", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestsProtocol> findByExample(TestsProtocol instance) {
		log.debug("finding TestsProtocol instance by example");
		try {
			List<TestsProtocol> results = (List<TestsProtocol>) getHibernateTemplate()
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
	public List<TestsProtocol> findByProperty(String propertyName, Object value) {
		log.debug("finding TestsProtocol instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TestsProtocol as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestsProtocol> findAll() {
		log.debug("finding all TestsProtocol instances");
		try {
			String queryString = "from TestsProtocol";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TestsProtocol merge(TestsProtocol detachedInstance) {
		log.debug("merging TestsProtocol instance");
		try {
			TestsProtocol result = (TestsProtocol) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TestsProtocol instance) {
		log.debug("attaching dirty TestsProtocol instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TestsProtocol instance) {
		log.debug("attaching clean TestsProtocol instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<TestsProtocol> getFromApplicationContext(
			ApplicationContext ctx) {
		return (ISimpleDAO<TestsProtocol>) ctx.getBean("TestsProtocolDAO");
	}
}