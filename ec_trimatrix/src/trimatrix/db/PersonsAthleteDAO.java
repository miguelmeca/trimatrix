package trimatrix.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for PersonsAthlete entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.PersonsAthlete
  * @author MyEclipse Persistence Tools 
 */

public class PersonsAthleteDAO extends HibernateDaoSupport  {
    private static final Log log = LogFactory.getLog(PersonsAthleteDAO.class);


	protected void initDao() {
		//do nothing
	}
    
    public void save(PersonsAthlete transientInstance) {
        log.debug("saving PersonsAthlete instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PersonsAthlete persistentInstance) {
        log.debug("deleting PersonsAthlete instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PersonsAthlete findById( java.lang.String id) {
        log.debug("getting PersonsAthlete instance with id: " + id);
        try {
            PersonsAthlete instance = (PersonsAthlete) getHibernateTemplate()
                    .get("trimatrix.db.PersonsAthlete", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PersonsAthlete instance) {
        log.debug("finding PersonsAthlete instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding PersonsAthlete instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PersonsAthlete as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	public List findAll() {
		log.debug("finding all PersonsAthlete instances");
		try {
			String queryString = "from PersonsAthlete";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PersonsAthlete merge(PersonsAthlete detachedInstance) {
        log.debug("merging PersonsAthlete instance");
        try {
            PersonsAthlete result = (PersonsAthlete) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PersonsAthlete instance) {
        log.debug("attaching dirty PersonsAthlete instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PersonsAthlete instance) {
        log.debug("attaching clean PersonsAthlete instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static PersonsAthleteDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (PersonsAthleteDAO) ctx.getBean("PersonsAthleteDAO");
	}
}