package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TProfiles entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.TProfiles
  * @author MyEclipse Persistence Tools 
 */

public class TProfilesDAO extends HibernateDaoSupport implements ITextDAO<TProfiles, TProfilesId>  {
    private static final Log log = LogFactory.getLog(TProfilesDAO.class);


	protected void initDao() {
		//do nothing
	}
    
    public void save(TProfiles transientInstance) {
        log.debug("saving TProfiles instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TProfiles persistentInstance) {
        log.debug("deleting TProfiles instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TProfiles findById( trimatrix.db.TProfilesId id) {
        log.debug("getting TProfiles instance with id: " + id);
        try {
            TProfiles instance = (TProfiles) getHibernateTemplate()
                    .get("trimatrix.db.TProfiles", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public TProfiles findById(String key, String languageKey) {
		return findById(new TProfilesId(key, languageKey));
	}
    
    @SuppressWarnings("unchecked")
    public List<TProfiles> findByExample(TProfiles instance) {
        log.debug("finding TProfiles instance by example");
        try {
            List<TProfiles> results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<TProfiles> findByProperty(String propertyName, Object value) {
      log.debug("finding TProfiles instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TProfiles as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	@SuppressWarnings("unchecked")
	public List<TProfiles> findAll() {
		log.debug("finding all TProfiles instances");
		try {
			String queryString = "from TProfiles";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TProfiles merge(TProfiles detachedInstance) {
        log.debug("merging TProfiles instance");
        try {
            TProfiles result = (TProfiles) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TProfiles instance) {
        log.debug("attaching dirty TProfiles instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TProfiles instance) {
        log.debug("attaching clean TProfiles instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TProfilesDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TProfilesDAO) ctx.getBean("TProfilesDAO");
	}
}