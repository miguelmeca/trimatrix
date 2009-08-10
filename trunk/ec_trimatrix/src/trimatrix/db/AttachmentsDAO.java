package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import trimatrix.entities.IEntityObject;

/**
 	* A data access object (DAO) providing persistence and search support for Attachments entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.Attachments
  * @author MyEclipse Persistence Tools 
 */

/**
 * @author reich
 *
 */
public class AttachmentsDAO extends HibernateDaoSupport implements IEntityDAO<Attachments>  {
    private static final Log log = LogFactory.getLog(AttachmentsDAO.class);


	@Override
	protected void initDao() {
		//do nothing
	}
    
    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#save(java.lang.Object)
     */
    public void save(Attachments transientInstance) {
        log.debug("saving Attachments instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* (non-Javadoc)
	 * @see trimatrix.db.IDAO#delete(java.lang.Object)
	 */
	public void delete(Attachments persistentInstance) {
        log.debug("deleting Attachments instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#findById(java.lang.String)
     */
    public Attachments findById( java.lang.String id) {
        log.debug("getting Attachments instance with id: " + id);
        try {
            Attachments instance = (Attachments) getHibernateTemplate()
                    .get("trimatrix.db.Attachments", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#findByExample(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public List<Attachments> findByExample(Attachments instance) {
        log.debug("finding Attachments instance by example");
        try {
            List<Attachments> results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#findByProperty(java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public List<Attachments> findByProperty(String propertyName, Object value) {
      log.debug("finding Attachments instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Attachments as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#findAll()
     */
    @SuppressWarnings("unchecked")
	public List<Attachments> findAll() {
		log.debug("finding all Attachments instances");
		try {
			String queryString = "from Attachments";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#merge(java.lang.Object)
     */
    public Attachments merge(IEntityObject detachedInstance) {
        log.debug("merging Attachments instance");
        try {
            Attachments result = (Attachments) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#attachDirty(java.lang.Object)
     */
    public void attachDirty(Attachments instance) {
        log.debug("attaching dirty Attachments instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }    

    /* (non-Javadoc)
     * @see trimatrix.db.IDAO#attachClean(java.lang.Object)
     */
    public void attachClean(Attachments instance) {
        log.debug("attaching clean Attachments instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
	
	/* (non-Javadoc)
	 * @see trimatrix.db.IDAO#reload(java.lang.Object)
	 */
	public void reload(IEntityObject attachments) {
		String id = attachments.getId();
		log.debug("reloading Attachment instance with id: " + id);
		try {
			getHibernateTemplate().load(attachments, id);			
		} catch (RuntimeException re) {
			log.error("load failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static IEntityDAO<Attachments> getFromApplicationContext(ApplicationContext ctx) {
    	return (IEntityDAO<Attachments>) ctx.getBean("AttachmentsDAO");
	}
}