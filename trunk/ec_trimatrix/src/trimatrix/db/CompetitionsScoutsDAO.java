package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for CompetitionsScouts entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.CompetitionsScouts
  * @author MyEclipse Persistence Tools 
 */

public class CompetitionsScoutsDAO extends HibernateDaoSupport implements IComplexDAO<CompetitionsScouts, CompetitionsScoutsId>  {
    private static final Log log = LogFactory.getLog(CompetitionsScoutsDAO.class);


	protected void initDao() {
		//do nothing
	}
    
    public void save(CompetitionsScouts transientInstance) {
        log.debug("saving CompetitionsScouts instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(CompetitionsScouts persistentInstance) {
        log.debug("deleting CompetitionsScouts instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CompetitionsScouts findById( trimatrix.db.CompetitionsScoutsId id) {
        log.debug("getting CompetitionsScouts instance with id: " + id);
        try {
            CompetitionsScouts instance = (CompetitionsScouts) getHibernateTemplate()
                    .get("trimatrix.db.CompetitionsScouts", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<CompetitionsScouts> findByExample(CompetitionsScouts instance) {
        log.debug("finding CompetitionsScouts instance by example");
        try {
            List<CompetitionsScouts> results = (List<CompetitionsScouts>) getHibernateTemplate().findByExample(instance); 
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<CompetitionsScouts> findByProperty(String propertyName, Object value) {
      log.debug("finding CompetitionsScouts instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from CompetitionsScouts as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    @SuppressWarnings("unchecked")
	public List<CompetitionsScouts> findAll() {
		log.debug("finding all CompetitionsScouts instances");
		try {
			String queryString = "from CompetitionsScouts";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public CompetitionsScouts merge(CompetitionsScouts detachedInstance) {
        log.debug("merging CompetitionsScouts instance");
        try {
            CompetitionsScouts result = (CompetitionsScouts) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CompetitionsScouts instance) {
        log.debug("attaching dirty CompetitionsScouts instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(CompetitionsScouts instance) {
        log.debug("attaching clean CompetitionsScouts instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	@SuppressWarnings("unchecked")
	public static IComplexDAO<CompetitionsScouts, CompetitionsScoutsId> getFromApplicationContext(ApplicationContext ctx) {
    	return (IComplexDAO<CompetitionsScouts, CompetitionsScoutsId>) ctx.getBean("CompetitionsScoutsDAO");
	}
}