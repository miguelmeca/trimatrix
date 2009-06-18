package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for ListVariants entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.ListVariants
  * @author MyEclipse Persistence Tools 
 */

public class ListVariantsDAO extends HibernateDaoSupport implements IListVariantsDAO  {
    private static final Log log = LogFactory.getLog(ListVariantsDAO.class);


	@Override
	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#save(trimatrix.db.ListVariants)
	 */
    public void save(ListVariants transientInstance) {
        log.debug("saving ListVariants instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#delete(trimatrix.db.ListVariants)
	 */
	public void delete(ListVariants persistentInstance) {
        log.debug("deleting ListVariants instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#findById(trimatrix.db.ListVariantsId)
	 */
    public ListVariants findById( trimatrix.db.ListVariantsId id) {
        log.debug("getting ListVariants instance with id: " + id);
        try {
            ListVariants instance = (ListVariants) getHibernateTemplate()
                    .get("trimatrix.db.ListVariants", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#findByExample(trimatrix.db.ListVariants)
	 */
    @SuppressWarnings("unchecked")
	public List<ListVariants> findByExample(ListVariants instance) {
        log.debug("finding ListVariants instance by example");
        try {
            List<ListVariants> results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @SuppressWarnings("unchecked")
	public List<ListVariants> findByProperty(String propertyName, Object value) {
      log.debug("finding ListVariants instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ListVariants as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	/* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<ListVariants> findAll() {
		log.debug("finding all ListVariants instances");
		try {
			String queryString = "from ListVariants";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#merge(trimatrix.db.ListVariants)
	 */
    public ListVariants merge(ListVariants detachedInstance) {
        log.debug("merging ListVariants instance");
        try {
            ListVariants result = (ListVariants) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#attachDirty(trimatrix.db.ListVariants)
	 */
    public void attachDirty(ListVariants instance) {
        log.debug("attaching dirty ListVariants instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IListVariantsDAO#attachClean(trimatrix.db.ListVariants)
	 */
    public void attachClean(ListVariants instance) {
        log.debug("attaching clean ListVariants instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static IListVariantsDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (IListVariantsDAO) ctx.getBean("ListVariantsDAO");
	}
}