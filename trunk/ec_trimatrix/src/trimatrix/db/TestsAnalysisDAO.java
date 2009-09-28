package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TestsAnalysis entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.TestsAnalysis
 * @author MyEclipse Persistence Tools
 */

public class TestsAnalysisDAO extends HibernateDaoSupport implements ISimpleDAO<TestsAnalysis>{
	private static final Log log = LogFactory.getLog(TestsAnalysisDAO.class);
	// property constants
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(TestsAnalysis transientInstance) {
		log.debug("saving TestsAnalysis instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TestsAnalysis persistentInstance) {
		log.debug("deleting TestsAnalysis instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TestsAnalysis findById(java.lang.String id) {
		log.debug("getting TestsAnalysis instance with id: " + id);
		try {
			TestsAnalysis instance = (TestsAnalysis) getHibernateTemplate()
					.get("trimatrix.db.TestsAnalysis", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TestsAnalysis> findByExample(TestsAnalysis instance) {
		log.debug("finding TestsAnalysis instance by example");
		try {
			List<TestsAnalysis> results = (List<TestsAnalysis>) getHibernateTemplate()
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
	public List<TestsAnalysis> findByProperty(String propertyName, Object value) {
		log.debug("finding TestsAnalysis instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TestsAnalysis as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestsAnalysis> findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	@SuppressWarnings("unchecked")
	public List<TestsAnalysis> findAll() {
		log.debug("finding all TestsAnalysis instances");
		try {
			String queryString = "from TestsAnalysis";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TestsAnalysis merge(TestsAnalysis detachedInstance) {
		log.debug("merging TestsAnalysis instance");
		try {
			TestsAnalysis result = (TestsAnalysis) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TestsAnalysis instance) {
		log.debug("attaching dirty TestsAnalysis instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TestsAnalysis instance) {
		log.debug("attaching clean TestsAnalysis instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<TestsAnalysis> getFromApplicationContext(
			ApplicationContext ctx) {
		return (ISimpleDAO<TestsAnalysis>) ctx.getBean("TestsAnalysisDAO");
	}
}