package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for Doctors entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.Doctors
  * @author MyEclipse Persistence Tools 
 */

public class DoctorsDAO extends HibernateDaoSupport implements IDoctorsDAO  {
    private static final Log log = LogFactory.getLog(DoctorsDAO.class);
	@Override
	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#save(trimatrix.db.Doctors)
	 */
    public void save(Doctors transientInstance) {
        log.debug("saving Doctors instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#delete(trimatrix.db.Doctors)
	 */
	public void delete(Doctors persistentInstance) {
        log.debug("deleting Doctors instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#findById(java.lang.String)
	 */
    public Doctors findById( java.lang.String id) {
        log.debug("getting Doctors instance with id: " + id);
        try {
            Doctors instance = (Doctors) getHibernateTemplate()
                    .get("trimatrix.db.Doctors", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#findByExample(trimatrix.db.Doctors)
	 */
    @SuppressWarnings("unchecked")
    public List<Doctors> findByExample(Doctors instance) {
        log.debug("finding Doctors instance by example");
        try {
            List<Doctors> results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @SuppressWarnings("unchecked")
    public List<Doctors> findByProperty(String propertyName, Object value) {
      log.debug("finding Doctors instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Doctors as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#findAll()
	 */
    @SuppressWarnings("unchecked")
	public List<Doctors> findAll() {
		log.debug("finding all Doctors instances");
		try {
			String queryString = "from Doctors";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#merge(trimatrix.db.Doctors)
	 */
    public Doctors merge(Doctors detachedInstance) {
        log.debug("merging Doctors instance");
        try {
            Doctors result = (Doctors) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#attachDirty(trimatrix.db.Doctors)
	 */
    public void attachDirty(Doctors instance) {
        log.debug("attaching dirty Doctors instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#attachClean(trimatrix.db.Doctors)
	 */
    public void attachClean(Doctors instance) {
        log.debug("attaching clean Doctors instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IDoctorsDAO#reload(trimatrix.db.Doctors)
	 */
    public void reload(Doctors doctor) {
		String id = doctor.getId();
		log.debug("reloading Doctor instance with id: " + id);
		try {
			getHibernateTemplate().load(doctor, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}

	public static IDoctorsDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (IDoctorsDAO) ctx.getBean("DoctorsDAO");
	}
}