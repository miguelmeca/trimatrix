package trimatrix.db;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TestsSwimProtocol entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see trimatrix.db.TestsSwimProtocol
  * @author MyEclipse Persistence Tools 
 */

public class TestsSwimProtocolDAO extends HibernateDaoSupport implements IComplexDAO<TestsSwimProtocol, TestsSwimProtocolId> {
    private static final Log log = LogFactory.getLog(TestsSwimProtocolDAO.class);


	protected void initDao() {
		//do nothing
	}
    
    public void save(TestsSwimProtocol transientInstance) {
        log.debug("saving TestsSwimProtocol instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TestsSwimProtocol persistentInstance) {
        log.debug("deleting TestsSwimProtocol instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TestsSwimProtocol findById( TestsSwimProtocolId id) {
        log.debug("getting TestsSwimProtocol instance with id: " + id);
        try {
            TestsSwimProtocol instance = (TestsSwimProtocol) getHibernateTemplate()
                    .get("trimatrix.db.TestsSwimProtocol", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public TestsSwimProtocol findById(String id) {
		throw new UnsupportedOperationException();
	}    
    
    @SuppressWarnings("unchecked")
	public List<TestsSwimProtocol> findByExample(TestsSwimProtocol instance) {
        log.debug("finding TestsSwimProtocol instance by example");
        try {
            List<TestsSwimProtocol> results = (List<TestsSwimProtocol>) getHibernateTemplate().findByExample(instance); 
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding TestsSwimProtocol instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TestsSwimProtocol as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    @SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all TestsSwimProtocol instances");
		try {
			String queryString = "from TestsSwimProtocol";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TestsSwimProtocol merge(TestsSwimProtocol detachedInstance) {
        log.debug("merging TestsSwimProtocol instance");
        try {
            TestsSwimProtocol result = (TestsSwimProtocol) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TestsSwimProtocol instance) {
        log.debug("attaching dirty TestsSwimProtocol instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TestsSwimProtocol instance) {
        log.debug("attaching clean TestsSwimProtocol instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	@SuppressWarnings("unchecked")
	public static IComplexDAO<TestsSwimProtocol, TestsSwimProtocolId> getFromApplicationContext(ApplicationContext ctx) {
    	return (IComplexDAO<TestsSwimProtocol, TestsSwimProtocolId>) ctx.getBean("TestsSwimProtocolDAO");
	}	
}