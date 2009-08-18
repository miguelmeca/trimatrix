package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import trimatrix.entities.IEntityObject;

/**
 * A data access object (DAO) providing persistence and search support for Tests
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see trimatrix.db.Tests
 * @author MyEclipse Persistence Tools
 */

public class TestsDAO extends HibernateDaoSupport implements IEntityDAO<Tests> {
	private static final Log log = LogFactory.getLog(TestsDAO.class);
	// property constants
	public static final String PERSON_ID = "personId";
	public static final String DOCTOR_ID = "doctorId";
	public static final String TYPE = "type";
	public static final String DESCRIPTION = "description";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String DELETED = "deleted";
	public static final String TEST = "test";

	protected void initDao() {
		// do nothing
	}

	public void save(Tests transientInstance) {
		log.debug("saving Tests instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tests persistentInstance) {
		log.debug("deleting Tests instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tests findById(java.lang.String id) {
		log.debug("getting Tests instance with id: " + id);
		try {
			Tests instance = (Tests) getHibernateTemplate().get(
					"trimatrix.db.Tests", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tests> findByExample(Tests instance) {
		log.debug("finding Tests instance by example");
		try {
			List<Tests> results = (List<Tests>) getHibernateTemplate()
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
	public List<Tests> findByProperty(String propertyName, Object value) {
		log.debug("finding Tests instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tests as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tests> findAll() {
		log.debug("finding all Tests instances");
		try {
			String queryString = "from Tests";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tests merge(IEntityObject detachedInstance) {
		log.debug("merging Tests instance");
		try {
			Tests result = (Tests) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tests instance) {
		log.debug("attaching dirty Tests instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tests instance) {
		log.debug("attaching clean Tests instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void reload(IEntityObject tests) {
		String id = tests.getId();
		log.debug("reloading Test instance with id: " + id);
		try {
			getHibernateTemplate().load(tests, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}		
	}

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<Tests> getFromApplicationContext(ApplicationContext ctx) {
		return (ISimpleDAO<Tests>) ctx.getBean("TestsDAO");
	}	
}