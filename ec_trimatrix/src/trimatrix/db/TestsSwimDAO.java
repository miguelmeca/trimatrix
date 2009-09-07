package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TestsSwim entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.TestsSwim
  * @author MyEclipse Persistence Tools 
 */

public class TestsSwimDAO extends HibernateDaoSupport implements ISimpleDAO<TestsSwim>  {
    private static final Log log = LogFactory.getLog(TestsSwimDAO.class);


	protected void initDao() {
		//do nothing
	}
    
    public void save(TestsSwim transientInstance) {
        log.debug("saving TestsSwim instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TestsSwim persistentInstance) {
        log.debug("deleting TestsSwim instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TestsSwim findById( java.lang.String id) {
        log.debug("getting TestsSwim instance with id: " + id);
        try {
            TestsSwim instance = (TestsSwim) getHibernateTemplate()
                    .get("trimatrix.db.TestsSwim", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<TestsSwim> findByExample(TestsSwim instance) {
        log.debug("finding TestsSwim instance by example");
        try {
            List<TestsSwim> results = (List<TestsSwim>) getHibernateTemplate().findByExample(instance); 
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<TestsSwim> findByProperty(String propertyName, Object value) {
      log.debug("finding TestsSwim instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TestsSwim as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    @SuppressWarnings("unchecked")
	public List<TestsSwim> findAll() {
		log.debug("finding all TestsSwim instances");
		try {
			String queryString = "from TestsSwim";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TestsSwim merge(TestsSwim detachedInstance) {
        log.debug("merging TestsSwim instance");
        try {
            TestsSwim result = (TestsSwim) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TestsSwim instance) {
        log.debug("attaching dirty TestsSwim instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TestsSwim instance) {
        log.debug("attaching clean TestsSwim instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	@SuppressWarnings("unchecked")
	public static ISimpleDAO<TestsSwim> getFromApplicationContext(ApplicationContext ctx) {
    	return (ISimpleDAO<TestsSwim>) ctx.getBean("TestsSwimDAO");
	}
}