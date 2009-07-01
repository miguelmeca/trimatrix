package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * KFunctionnodes entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.KFunctionnodes
 * @author MyEclipse Persistence Tools
 */

public class KFunctionnodesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(KFunctionnodesDAO.class);
	// property constants
	public static final String PAGE = "page";
	public static final String ENTITY = "entity";
	public static final String EDIT = "edit";
	public static final String CREATE = "create";
	public static final String DELETE = "delete";

	protected void initDao() {
		// do nothing
	}

	public void save(KFunctionnodes transientInstance) {
		log.debug("saving KFunctionnodes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KFunctionnodes persistentInstance) {
		log.debug("deleting KFunctionnodes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public KFunctionnodes findById(java.lang.String id) {
		log.debug("getting KFunctionnodes instance with id: " + id);
		try {
			KFunctionnodes instance = (KFunctionnodes) getHibernateTemplate()
					.get("trimatrix.db.KFunctionnodes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KFunctionnodes instance) {
		log.debug("finding KFunctionnodes instance by example");
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
		log.debug("finding KFunctionnodes instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from KFunctionnodes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPage(Object page) {
		return findByProperty(PAGE, page);
	}

	public List findByEntity(Object entity) {
		return findByProperty(ENTITY, entity);
	}

	public List findByEdit(Object edit) {
		return findByProperty(EDIT, edit);
	}

	public List findByCreate(Object create) {
		return findByProperty(CREATE, create);
	}

	public List findByDelete(Object delete) {
		return findByProperty(DELETE, delete);
	}

	public List findAll() {
		log.debug("finding all KFunctionnodes instances");
		try {
			String queryString = "from KFunctionnodes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KFunctionnodes merge(KFunctionnodes detachedInstance) {
		log.debug("merging KFunctionnodes instance");
		try {
			KFunctionnodes result = (KFunctionnodes) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KFunctionnodes instance) {
		log.debug("attaching dirty KFunctionnodes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KFunctionnodes instance) {
		log.debug("attaching clean KFunctionnodes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KFunctionnodesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (KFunctionnodesDAO) ctx.getBean("KFunctionnodesDAO");
	}
}