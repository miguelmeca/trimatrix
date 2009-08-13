package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TCategories entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.TCategories
  * @author MyEclipse Persistence Tools 
 */

public class TCategoriesDAO extends HibernateDaoSupport implements ITextDAO<TCategories, TCategoriesId> {
    private static final Log log = LogFactory.getLog(TCategoriesDAO.class);


	@Override
	protected void initDao() {
		//do nothing
	}
    
    public void save(TCategories transientInstance) {
        log.debug("saving TCategories instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TCategories persistentInstance) {
        log.debug("deleting TCategories instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TCategories findById( trimatrix.db.TCategoriesId id) {
        log.debug("getting TCategories instance with id: " + id);
        try {
            TCategories instance = (TCategories) getHibernateTemplate()
                    .get("trimatrix.db.TCategories", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public TCategories findById(String key, String languageKey) {
		return findById(new TCategoriesId(key, languageKey));
	}
        
    @SuppressWarnings("unchecked")
	public List<TCategories> findByExample(TCategories instance) {
        log.debug("finding TCategories instance by example");
        try {
            List<TCategories> results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<TCategories> findByProperty(String propertyName, Object value) {
      log.debug("finding TCategories instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TCategories as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    @SuppressWarnings("unchecked")
	public List<TCategories> findAll() {
		log.debug("finding all TCategories instances");
		try {
			String queryString = "from TCategories";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TCategories merge(TCategories detachedInstance) {
        log.debug("merging TCategories instance");
        try {
            TCategories result = (TCategories) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TCategories instance) {
        log.debug("attaching dirty TCategories instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TCategories instance) {
        log.debug("attaching clean TCategories instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	@SuppressWarnings("unchecked")
	public static ITextDAO<TCategories, TCategoriesId> getFromApplicationContext(ApplicationContext ctx) {
    	return (ITextDAO<TCategories, TCategoriesId>) ctx.getBean("TCategoriesDAO");
	}
}