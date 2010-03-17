package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ImportTemplates entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see trimatrix.db.ImportTemplates
 * @author MyEclipse Persistence Tools
 */

public class ImportTemplatesDAO extends HibernateDaoSupport implements IImportTemplatesDAO {
	private static final Log log = LogFactory.getLog(ImportTemplatesDAO.class);

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#save(trimatrix.db.ImportTemplates)
	 */
	public void save(ImportTemplates transientInstance) {
		log.debug("saving ImportTemplates instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#delete(trimatrix.db.ImportTemplates)
	 */
	public void delete(ImportTemplates persistentInstance) {
		log.debug("deleting ImportTemplates instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#findById(trimatrix.db.ImportTemplatesId)
	 */
	public ImportTemplates findById(trimatrix.db.ImportTemplatesId id) {
		log.debug("getting ImportTemplates instance with id: " + id);
		try {
			ImportTemplates instance = (ImportTemplates) getHibernateTemplate()
					.get("trimatrix.db.ImportTemplates", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#findByExample(trimatrix.db.ImportTemplates)
	 */
	public List<ImportTemplates> findByExample(ImportTemplates instance) {
		log.debug("finding ImportTemplates instance by example");
		try {
			List<ImportTemplates> results = (List<ImportTemplates>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ImportTemplates instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ImportTemplates as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#findAll()
	 */
	public List findAll() {
		log.debug("finding all ImportTemplates instances");
		try {
			String queryString = "from ImportTemplates";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#merge(trimatrix.db.ImportTemplates)
	 */
	public ImportTemplates merge(ImportTemplates detachedInstance) {
		log.debug("merging ImportTemplates instance");
		try {
			ImportTemplates result = (ImportTemplates) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#attachDirty(trimatrix.db.ImportTemplates)
	 */
	public void attachDirty(ImportTemplates instance) {
		log.debug("attaching dirty ImportTemplates instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see trimatrix.db.IImportTemplatesDAO#attachClean(trimatrix.db.ImportTemplates)
	 */
	public void attachClean(ImportTemplates instance) {
		log.debug("attaching clean ImportTemplates instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IImportTemplatesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IImportTemplatesDAO) ctx.getBean("ImportTemplatesDAO");
	}
}