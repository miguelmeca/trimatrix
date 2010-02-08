package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for DayInfos entities.
 			* Transaction control of the save(), update() and delete() operations
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions.
		Each of these methods provides additional information for how to configure it for the desired type of transaction control.
	 * @see trimatrix.db.DayInfos
  * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class DayInfosDAO extends HibernateDaoSupport implements ISimpleDAO<DayInfos>  {
    private static final Log log = LogFactory.getLog(DayInfosDAO.class);


	protected void initDao() {
		//do nothing
	}

    public void save(DayInfos transientInstance) {
        log.debug("saving DayInfos instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

	public void delete(DayInfos persistentInstance) {
        log.debug("deleting DayInfos instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public DayInfos findById( java.lang.String id) {
        log.debug("getting DayInfos instance with id: " + id);
        try {
            DayInfos instance = (DayInfos) getHibernateTemplate()
                    .get("trimatrix.db.DayInfos", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
   
	public List<DayInfos> findByExample(DayInfos instance) {
        log.debug("finding DayInfos instance by example");
        try {
            List<DayInfos> results = (List<DayInfos>) getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<DayInfos> findByProperty(String propertyName, Object value) {
      log.debug("finding DayInfos instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from DayInfos as model where model."
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	public List<DayInfos> findAll() {
		log.debug("finding all DayInfos instances");
		try {
			String queryString = "from DayInfos";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    public DayInfos merge(DayInfos detachedInstance) {
        log.debug("merging DayInfos instance");
        try {
            DayInfos result = (DayInfos) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(DayInfos instance) {
        log.debug("attaching dirty DayInfos instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(DayInfos instance) {
        log.debug("attaching clean DayInfos instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ISimpleDAO<DayInfosDAO> getFromApplicationContext(ApplicationContext ctx) {
    	return (ISimpleDAO<DayInfosDAO>) ctx.getBean("DayInfosDAO");
	}
}