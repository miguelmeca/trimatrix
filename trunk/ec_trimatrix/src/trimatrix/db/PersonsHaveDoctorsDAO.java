package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for PersonsHaveDoctors entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.PersonsHaveDoctors
  * @author MyEclipse Persistence Tools 
 */

public class PersonsHaveDoctorsDAO extends HibernateDaoSupport implements IPersonsHaveDoctorsDAO, IRelationDAO  {
    private static final Log log = LogFactory.getLog(PersonsHaveDoctorsDAO.class);


	@Override
	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#save(trimatrix.db.PersonsHaveDoctors)
	 */
    public void save(PersonsHaveDoctors transientInstance) {
        log.debug("saving PersonsHaveDoctors instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#delete(trimatrix.db.PersonsHaveDoctors)
	 */
	public void delete(PersonsHaveDoctors persistentInstance) {
        log.debug("deleting PersonsHaveDoctors instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#findById(java.lang.String)
	 */
    public PersonsHaveDoctors findById( java.lang.String id) {
        log.debug("getting PersonsHaveDoctors instance with id: " + id);
        try {
            PersonsHaveDoctors instance = (PersonsHaveDoctors) getHibernateTemplate()
                    .get("trimatrix.db.PersonsHaveDoctors", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#findByExample(trimatrix.db.PersonsHaveDoctors)
	 */
    @SuppressWarnings("unchecked")
    public List<PersonsHaveDoctors> findByExample(PersonsHaveDoctors instance) {
        log.debug("finding PersonsHaveDoctors instance by example");
        try {
            List<PersonsHaveDoctors> results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#findByProperty(java.lang.String, java.lang.Object)
	 */
    @SuppressWarnings("unchecked")
    public List<PersonsHaveDoctors> findByProperty(String propertyName, Object value) {
      log.debug("finding PersonsHaveDoctors instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PersonsHaveDoctors as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#findAll()
	 */
    @SuppressWarnings("unchecked")
	public List<PersonsHaveDoctors> findAll() {
		log.debug("finding all PersonsHaveDoctors instances");
		try {
			String queryString = "from PersonsHaveDoctors";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#merge(trimatrix.db.PersonsHaveDoctors)
	 */
    public PersonsHaveDoctors merge(PersonsHaveDoctors detachedInstance) {
        log.debug("merging PersonsHaveDoctors instance");
        try {
            PersonsHaveDoctors result = (PersonsHaveDoctors) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#attachDirty(trimatrix.db.PersonsHaveDoctors)
	 */
    public void attachDirty(PersonsHaveDoctors instance) {
        log.debug("attaching dirty PersonsHaveDoctors instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#attachClean(trimatrix.db.PersonsHaveDoctors)
	 */
    public void attachClean(PersonsHaveDoctors instance) {
        log.debug("attaching clean PersonsHaveDoctors instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
	 * @see trimatrix.db.IPersonsHaveDoctors#reload(trimatrix.db.PersonsHaveDoctors)
	 */
    public void reload(PersonsHaveDoctors relation) {
		String id = relation.getId();
		log.debug("reloading PersonsHaveDoctors instance with id: " + id);
		try {
			getHibernateTemplate().load(relation, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}
    
    @SuppressWarnings("unchecked")
	public int deleteByPartners(String partnerId) {
		int count = 0;
    	log.debug("finding PersonsHaveDoctors instance with partner1 or partner2 : "+ partnerId);
          try {
             String queryString = "from PersonsHaveDoctors as model where model.person = '" + partnerId + "' or model.doctor = '" + partnerId + "'";
             List<PersonsHaveDoctors> relations = getHibernateTemplate().find(queryString);
             // delete instance
             for (PersonsHaveDoctors relation : relations) {
            	 delete(relation);
            	 count++;
             }
          } catch (RuntimeException re) {
             log.error("delete by partners failed", re);
             throw re;
          }
          return count;
	}

	public static IPersonsHaveDoctorsDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (IPersonsHaveDoctorsDAO) ctx.getBean("PersonsHaveDoctorsDAO");
	}	
}